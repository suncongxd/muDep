/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 */

package droidsafe.analyses.value;

import droidsafe.analyses.pta.PTABridge;
import droidsafe.analyses.value.UnknownVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import droidsafe.utils.SootUtils;
import droidsafe.main.Config;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import soot.jimple.toolkits.pta.IAllocNode;
import soot.jimple.spark.pag.StringConstantByMethod;
import soot.jimple.spark.pag.StringConstantNode;
import soot.ArrayType;
import soot.RefLikeType;
import soot.RefType;
import soot.SootClass;
import soot.SootField;
import soot.Type;

/**
 * Base class for value analysis object models.
 *
 * @author dpetters
 */
public abstract class RefVAModel extends VAModel {

    /**
     * Every model is tied to an allocNode (one-to-one). 
     */
    protected IAllocNode node;

    private static final Logger logger = LoggerFactory.getLogger(RefVAModel.class);

    private static final UnknownVAModel unknownValue = new UnknownVAModel();

    /** 
     * Flag that gets set to true when the model is being printed.
     * Used to avoid infinite loops when printing due to chains of models being values of fields of other models.
     */
    private boolean beingPrinted = false;

    public RefVAModel(IAllocNode an){
        this.node = an;
    }

    /**
     * @returns null if the value of the field could be anything
     *          set of VAModels for the values of the field otherwise
     */
    public Set<VAModel> getFieldVAModels(SootField sootField) {
        String errorMsg = this.getAllocNode() + "'s field " + sootField + " got assigned the value 'unknown' because ";
        Set<VAModel> fieldVAModels = new HashSet<VAModel>();
        Type fieldType = sootField.getType();
        if((fieldType instanceof ArrayType || fieldType instanceof RefType) && 
                !ValueAnalysis.handleAsPrimType(fieldType)) {
            IAllocNode wholeObjectAN = this.getAllocNode();
            Set<? extends IAllocNode> allocNodes = PTABridge.v().getPTSet(wholeObjectAN, sootField);
            if (fieldType instanceof ArrayType) {
                Set<IAllocNode> arrayElementAllocNodes = new HashSet<IAllocNode>();
                for(IAllocNode arrayAllocNode : allocNodes) {
                    arrayElementAllocNodes.addAll(PTABridge.v().getPTSetOfArrayElement(arrayAllocNode));
                }
                allocNodes = arrayElementAllocNodes;
            }
            if(allocNodes.size() > 0){
                for(IAllocNode allocNode : allocNodes) {
                    if(PTABridge.isStringConstant(allocNode)) {
                        StringVAModel stringVAModel = new StringVAModel();
                        stringVAModel.addValue(PTABridge.getValueOfStringConstant(allocNode));
                        fieldVAModels.add(stringVAModel);
                    } else {
                        VAModel vaModel = ValueAnalysis.v().getResult(allocNode);
                        if(vaModel != null) {
                            fieldVAModels.add(vaModel);
                        } else {
                            ValueAnalysis.logError(errorMsg + " there was no model for its objects (perhaps the objects' class isn't annotated with DSVAModeled?).");
                            fieldVAModels.add(unknownValue);
                        }
                    }

                }
            }
        } else {
            Class<?> c = this.getClass();
            try {
                Field field = c.getField(sootField.getName());
                try {
                    Object fieldObject = field.get(this);
                    PrimVAModel fieldObjectPrimVAModel = (PrimVAModel)fieldObject;
                    if(fieldObjectPrimVAModel.invalidated() || fieldObjectPrimVAModel.values.size() > 0) {
                        fieldVAModels.add(fieldObjectPrimVAModel); 
                    }
                } catch(IllegalAccessException e) {
                    ValueAnalysis.logError(errorMsg + " its values couldn't be retrieved: " + e.toString());
                    fieldVAModels.add(unknownValue);
                }
            } catch(NoSuchFieldException e) {
                ValueAnalysis.logError(errorMsg + " its values couldn't be retrieved: " + e.toString());
                fieldVAModels.add(unknownValue);
                ValueAnalysis.logError("Available field using getFields:");                                              
                for(Field fld : c.getFields()) {                                                                         
                    ValueAnalysis.logError(fld.toString());                                                             
                }                                                                                                        
                ValueAnalysis.logError("Available fields using getDeclaredFields:");                                     
                for(Field fld : c.getDeclaredFields()) {                                                                 
                    ValueAnalysis.logError(fld.toString());                                                             
                }
            }
        }
        return fieldVAModels;
    }

    /**
     * Returns the id of the AllocNode if the model has one.
     * @returns id as String
     */
    public String getId() {
        String id = "";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(this.getAllocNode().toString());
        if(m.find()) {
            id += m.group();
        }
        return "#" + id;
    }

    /**
     * @returns AllocNode that corresponds to this model.
     */
    public IAllocNode getAllocNode(){
        return node;
    }

    /**
     * @returns a string representation of this object model.
     */
    @Override
    public String toStringSimple() {
        String str = "{\"";
        str += this.getClass().getName().substring(ValueAnalysis.MODEL_PACKAGE_PREFIX.length());
        str += "\":";
        if(beingPrinted) {
            str += "\"RECURSIVE\"";
        } else {
            beingPrinted = true;
            str += "{" + this.fieldsString(false) + "}";
            beingPrinted = false;
        }
        str += "}";
        return str.replace("\"", "");
    }

    /**
     * @returns a string representation of this object model.
     */
    @Override
    public String toStringDetailed() {
        String str = "{\"va-modeled-";
        str += this.getClass().getName().substring(ValueAnalysis.MODEL_PACKAGE_PREFIX.length());
        str += " " + this.getId() + "\": ";
        if(beingPrinted) {
            str += "\"RECURSIVE\"";
        } else {
            beingPrinted = true;
            str += "{" + this.fieldsString(true) + "}";
            beingPrinted = false;
        }
        return str + "}";
    }

    /**
     * @returns a well-formatted (pretty!) detailed printout of the results, indented at 
     * the given level
     */
    public String toStringPretty(int level) {
        StringBuffer buf = new StringBuffer();

	if (level > Config.v().VA_PRINTING_LEVEL)
	    return buf.toString();

        String indent = "\n" + VAUtils.indent(level);
        buf.append(indent);
        buf.append(this.getClass().getName().substring(ValueAnalysis.MODEL_PACKAGE_PREFIX.length()));
        buf.append(": ");
        if(beingPrinted) {
            buf.append("<RECURSIVE>");
        } else {
            beingPrinted = true;
            // buf.append("{");
            buf.append(this.fieldsStringPretty(level + 1));
            // buf.append("}");
            beingPrinted = false;
        }
        return buf.toString();
    }


    /**
     * @returns the SootClass for this object model.
     */ 
    public SootClass getSootClass() {
        // We only model objects, so the type should always be RefType
        return ((RefType)this.getAllocNode().getType()).getSootClass();
    }

    /**
     * Return a string of the resolved field values for this modeled object.
     */
    private String fieldsString(boolean detailed) {
        String fieldsString = "";
        Set<String> fieldStrings = new HashSet<String>();
        if (this.invalidated) {
            fieldsString += "\"" + INVALIDATED + "\"";
        } else {
            int cumStrLen = 0;
            for(SootField sootField : getFieldsToDisplay(this.getSootClass())) {
                Set<VAModel> vaModels = this.getFieldVAModels(sootField);
                String fieldString = "";
                if(vaModels == null) {
                    fieldString += INVALIDATED;
                } else if(vaModels.size() >= 0 && vaModels.size() < 100){
                    // using which we call getFieldVAModels to get a list of of object models
                    if(vaModels.size() > 1) fieldString += "[";
                    Set<String> objectModelStrings = new HashSet<String>();
                    for(VAModel vaModel : vaModels){
                        // TODO: figure out why this can be null
                        if(vaModel != null) {
                            // for each field object model, we call its toString, unless the object model is the same
                            // one we are trying to print out (to avoid a toString infinite loop)
                            String str = "";
                            if(this==vaModel) {                                                                           
                                str = "\"itself\"";
                                objectModelStrings.add(str);
                            } else {
                                // for each object model we call its tostring method
                                if(detailed) {
                                    str = vaModel.toStringDetailed();
                                } else {
                                    str = vaModel.toStringSimple();
                                }
                                objectModelStrings.add(str);
                            }
                        }
                    }
                    fieldString += StringUtils.join(objectModelStrings.toArray(), ", ");
                    if(vaModels.size() > 1) fieldString += "]";
                } else {
                    fieldString += "\"too many values\"";
                }
                int length = fieldString.length();
                cumStrLen += length;
                if(length > 0 && cumStrLen  < 1000) {
                    fieldStrings.add("\"" + sootField.getName() + "\":" + fieldString);
                }

            }
            fieldsString += StringUtils.join(fieldStrings.toArray(), ", ");
        }
        return fieldsString;
    }

    /**
     * Return a well-formatted (pretty!) string of the resolved field values for this modeled object.
     */
    private String fieldsStringPretty(int level) {	
	if (level > Config.v().VA_PRINTING_LEVEL)
	    return "<additional depth not shown>";

        if (this.invalidated) {
            return INVALIDATED;
        } else {
            StringBuffer buf = new StringBuffer();
            String indent = "\n" + VAUtils.indent(level);
            boolean firstField = true;
            for (SootField sootField : getFieldsToDisplay(this.getSootClass())) {
                Set<VAModel> vaModels = this.getFieldVAModels(sootField);
                if (vaModels == null || vaModels.size() > 0) {
                    if (firstField)
                        firstField = false;
                    else
                        buf.append(",");
                    buf.append(indent);
                    buf.append(sootField.getName());
                    buf.append(": ");
                    if (vaModels == null) {
                        buf.append(INVALIDATED);
                    } else {
                        // using which we call getFieldVAModels to get a list of of object models
                        // if(vaModels.size() > 1) buf.append("{");
                        boolean firstValue = true;
                        for (VAModel vaModel : vaModels) {
                            // TODO: figure out why this can be null
                            if(vaModel != null) {
                                if (firstValue)
                                    firstValue = false;
                                else
                                    buf.append(",");
                                // for each field object model, we call its toString, unless the object model is the same
                                // one we are trying to print out (to avoid a toString infinite loop)
                                if(this==vaModel) {                                                                           
                                    buf.append("<itself>");
                                } else {
                                    buf.append(vaModel.toStringPretty(level + 1));
                                }
                            }
                        }
                        // if(vaModels.size() > 1) buf.append("}");
                    }
                }
            }
            return buf.toString();
        }
    }
    
    public Set<SootField> getFields() {
        return getFieldsToDisplay(this.getSootClass());
    }

    public static Set<SootField> getFieldsToDisplay(SootClass sootClassParam) {
        // the result
        Set<SootField> fieldsToDisplay = new HashSet<SootField>();

        // we want to display not only values of fields from this class, but also any parent class in the hierarchy
        Set<SootClass> classesInHierarchy = new HashSet<SootClass>();
        classesInHierarchy.add(sootClassParam);
        classesInHierarchy.addAll(SootUtils.getParents(sootClassParam));

        // we will filter classes and fields by what is annotated as security sensitive in the api model
        Map<SootClass, Set<SootField>> classesAndFieldsVAShouldModel = ValueAnalysis.v().getClassesAndFieldsToModel();
        // go through all fields in the hierarchy, filtering down to only those that should get displayed 
        for(SootClass sootClass : classesInHierarchy) {
            if(classesAndFieldsVAShouldModel.containsKey(sootClass)) {
                Set<SootField> fieldsVAShouldModel = classesAndFieldsVAShouldModel.get(sootClass);
                for(SootField sootField : sootClass.getFields()) {
                    if(fieldsVAShouldModel.contains(sootField))
                        fieldsToDisplay.add(sootField);
                }
            }
        }
        return fieldsToDisplay;
    }
}
