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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Base class for value analysis object models.
 *
 * @author dpetters
 */
public abstract class VAModel {
    
    public static final String INVALIDATED = "<INVALIDATED>";

    /**
     * An object may be invalidated if we cannot soundly resolve it.
     */
    protected boolean invalidated = false;
    
    /**
     * Invalidate the model - we couldn't soundly resolve everything.
     */
    public void  invalidate(){
        this.invalidated = true;
    }  
    
    /**
     * @returns whether this model was fully resolved
     */
    public boolean invalidated() {
        return this.invalidated;
    }

    /**
     * @returns a simple printout of the results
     */
    public abstract String toStringSimple();

    /**
     * @returns a detailed printout of the results
     */
    public abstract String toStringDetailed();

    /**
     * @returns a well-formatted (pretty!) detailed printout of the results, indented at 
     * the given level.
     */
    public abstract String toStringPretty(int level) ;

//    public String toStringPretty() {
//        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
//        JsonParser jp = new JsonParser();
//        try {
//            JsonElement je = jp.parse(this.toStringDetailed());
//            return gson.toJson(je).replace("\"", ""); 
//            //return unescapeUnicode(gson.toJson(je).replace("\"", "")); 
//        } catch (JsonSyntaxException e) {
//            System.out.println("Malformed JSON: " + this.toStringDetailed());
//            System.out.println(e.toString());
//        }
//        return null;
//    }

    /**
     * @returns a well-formatted (pretty!) detailed printout of the results
     */
    public String toStringPretty() {
        return toStringPretty(0);
    }

    /**
     * @returns a detailed printout of the results
     */
    public String toString() {
        return this.toStringDetailed();
        //is this really necessary?
        //return this.toStringDetailed().replace("\"", "");
    }

}
