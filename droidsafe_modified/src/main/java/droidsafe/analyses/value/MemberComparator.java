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

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.TypeDeclaration;

import java.util.Comparator;
import java.util.List;

public class MemberComparator implements Comparator<BodyDeclaration> {
    
    public static final int FIELD = 0;
    public static final int CONSTRUCTOR = 1;
    public static final int METHOD = 2;
    public static final int TYPE = 3;
    public static final int UNKNOWN = -1;

    @Override
    public int compare(BodyDeclaration member1, BodyDeclaration member2) {
        int kind1 = memberKind(member1);
        int kind2 = memberKind(member2);
        int kindComp = kind1 - kind2;
        if (kindComp != 0) return kindComp;
        if (kind1 == FIELD) {
            String name1 = ((FieldDeclaration)member1).getVariables().get(0).getId().getName();
            String name2 = ((FieldDeclaration)member2).getVariables().get(0).getId().getName();
            return name1.compareTo(name2);
        }
        if (kind1 == CONSTRUCTOR) {
            List<Parameter> params1 = ((ConstructorDeclaration)member1).getParameters();
            List<Parameter> params2 = ((ConstructorDeclaration)member2).getParameters();
            return compareParams(params1, params2);
        }
        if (kind1 == METHOD) {
            String name1 = ((MethodDeclaration)member1).getName();
            String name2 = ((MethodDeclaration)member2).getName();
            int nameComp = name1.compareTo(name2);
            if (nameComp != 0) return nameComp;
            List<Parameter> params1 = ((MethodDeclaration)member1).getParameters();
            List<Parameter> params2 = ((MethodDeclaration)member2).getParameters();
            return compareParams(params1, params2);
        }
        if (kind1 == TYPE) {
            String name1 = ((TypeDeclaration)member1).getName();
            String name2 = ((TypeDeclaration)member2).getName();
            return name1.compareTo(name2);
        }
        return -1;
    }
    
    private int compareParams(List<Parameter> params1, List<Parameter> params2) {
        if (params1 == null) {
            return (params2 == null) ? 0 : -1;
        }
        if (params2 == null)
            return 1;
        int len1 = params1.size();
        int len2 = params2.size();
        int lenComp = len1 - len2;
        if (lenComp != 0) return lenComp;
        for (int i = 0; i < len1; i++) {
            String type1 = params1.get(i).getType().toString();
            String type2 = params2.get(i).getType().toString();
            int typeComp = type1.compareTo(type2);
            if (typeComp != 0) return typeComp;
        }
        return 0;
    }

    private int memberKind(BodyDeclaration member) {
        if (member instanceof FieldDeclaration)
            return FIELD;
        if (member instanceof ConstructorDeclaration)
            return CONSTRUCTOR;
        if (member instanceof MethodDeclaration)
            return METHOD;
        if (member instanceof TypeDeclaration)
            return TYPE;
        return UNKNOWN;
    }

}
