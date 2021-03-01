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
 * 
 * 
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.luni.lang.reflect;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

public final class ImplForWildcard implements WildcardType {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:26.980 -0400", hash_original_field = "F46925D3540A6CF061C3B56DAECD636F", hash_generated_field = "09ECEF7959A04D8A2E7315F105554DF2")

    private ListOfTypes extendsBound;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:26.980 -0400", hash_original_field = "A91DCB3D1DA33DA1DB1124AD9DBFEA92", hash_generated_field = "DC1616B3F5683830A9032F213015D66B")

    private ListOfTypes superBound;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.492 -0500", hash_original_method = "377AE596AF1F05D61C997E0097B90D78", hash_generated_method = "C8F8A1B2960B1828FB1127520B88EDFA")
    
public ImplForWildcard(ListOfTypes extendsBound, ListOfTypes superBound) {
        this.extendsBound = extendsBound;
        this.superBound = superBound;
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.494 -0500", hash_original_method = "0E02033FF7243902E994038D555D1587", hash_generated_method = "BB94CA458237C8C2E2434E55F9FB1023")
    
public Type[] getLowerBounds() throws TypeNotPresentException,
            MalformedParameterizedTypeException {
        return superBound.getResolvedTypes().clone();
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.496 -0500", hash_original_method = "F21086AF3EFC8DD73AF90E899FA34F74", hash_generated_method = "E84DCEC095B58839AF75892129776AD0")
    
public Type[] getUpperBounds() throws TypeNotPresentException,
            MalformedParameterizedTypeException {
        return extendsBound.getResolvedTypes().clone();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.499 -0500", hash_original_method = "4E6CE2D91E2326D588B30852F56501A7", hash_generated_method = "4A352F930EE47E6476A484BAFAE8648B")
    
@Override
    public boolean equals(Object o) {
        if(!(o instanceof WildcardType)) {
            return false;
        }
        WildcardType that = (WildcardType) o;
        return Arrays.equals(getLowerBounds(), that.getLowerBounds()) &&
                Arrays.equals(getUpperBounds(), that.getUpperBounds());
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.501 -0500", hash_original_method = "336948550805F35B42E4F5770BA4CC44", hash_generated_method = "79D9A791951F72E5DCE8CE8F5710A63B")
    
@Override
    public int hashCode() {
        return 31 * Arrays.hashCode(getLowerBounds()) +
                Arrays.hashCode(getUpperBounds());
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.504 -0500", hash_original_method = "93865AC448C11A5C9C4A3B3F15C62698", hash_generated_method = "DB1D4E5C0F8E9CD5943A61F7242A18F1")
    
@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("?");
        if ((extendsBound.length() == 1 && extendsBound.getResolvedTypes()[0] != Object.class)
                || extendsBound.length() > 1) {
            sb.append(" extends ").append(extendsBound);
        } else if (superBound.length() > 0) {
            sb.append(" super ").append(superBound);
        }
        return sb.toString();
    }
    
}

