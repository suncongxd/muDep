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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class ImplForType implements ParameterizedType {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:25.988 -0500", hash_original_field = "898CF09C9F13FDD87C219645175BB6C1", hash_generated_field = "22B7A856AFAF9B03C52198C74550F785")

    private  ListOfTypes args;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:25.990 -0500", hash_original_field = "9B25D80910CD57BA044E4C908B1494CF", hash_generated_field = "E62DDD05FB8DDFF2C9E5C831739CC012")

    private  ImplForType ownerType0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:25.992 -0500", hash_original_field = "6C10B280723D23125710A5B374AC90E1", hash_generated_field = "C3BFD692F6CB73635125C60E59B255FE")

    private Type ownerTypeRes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:25.995 -0500", hash_original_field = "43B8CB3AFBB309D419BF8BF7ADA912C8", hash_generated_field = "8834091542F8527562CD5739509025DB")

    private Class rawType;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:25.997 -0500", hash_original_field = "734822B034B60EAE90B43356BA102FA1", hash_generated_field = "7AA47D60CFFACFECE0651E3279DC0A79")

    private  String rawTypeName;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.006 -0500", hash_original_field = "50E7BE27A72C15B735AFB72AFA6F9C78", hash_generated_field = "628BC99287A8F7D9E696F2E87A1B1CC4")

    private ClassLoader loader;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.008 -0500", hash_original_method = "23B26279F59FD2756996C3E4E28C143C", hash_generated_method = "931AAC621CEF93AA134B7AF7581311CB")
    
public ImplForType(ImplForType ownerType, String rawTypeName,
            ListOfTypes args, ClassLoader loader) {
        this.ownerType0 = ownerType;
        this.rawTypeName = rawTypeName;
        this.args = args;
        this.loader = loader;
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.011 -0500", hash_original_method = "467C0CA3F26F8ADD49B2C117FFDDE5B5", hash_generated_method = "33FDA3A2CE3AAA9B3851D022DD546ECA")
    
public Type[] getActualTypeArguments() {
        // ASSUMPTION: args is never null!!!
        return args.getResolvedTypes().clone();
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.013 -0500", hash_original_method = "607EB162392181215E5C2613EAFAE402", hash_generated_method = "17909C7E64462095813ED1B781EFFC23")
    
public Type getOwnerType() {
        if (ownerTypeRes == null) {
            if (ownerType0 != null) {
                ownerTypeRes = ownerType0.getResolvedType();
            } else {
                ownerTypeRes = getRawType().getDeclaringClass();
            }
        }
        return ownerTypeRes;
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.015 -0500", hash_original_method = "8C14A8DC9EE4FE0D18F10F9D13D7DE4A", hash_generated_method = "6F5E6EB400A4167EA3B6C46FC56C5A64")
    
public Class getRawType() {
        if (rawType == null) {
            // Here the actual loading of the class has to be performed and the
            // Exceptions have to be re-thrown TypeNotPresent...
            // How to deal with member (nested) classes?
            try {
                rawType = Class.forName(rawTypeName, false, loader);
            } catch (ClassNotFoundException e) {
                throw new TypeNotPresentException(rawTypeName, e);
            }
        }
        return rawType;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.017 -0500", hash_original_method = "62DEF1F574FB703889CA718D111580A0", hash_generated_method = "62DEF1F574FB703889CA718D111580A0")
    
Type getResolvedType() {
        if (args.getResolvedTypes().length == 0) {
            return getRawType();
        } else {
            return this;
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:26.020 -0500", hash_original_method = "6D877CB8043423D6A95AF1E4F8482A09", hash_generated_method = "E0216B7F50AF807374976755B7848F40")
    
@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rawTypeName);
        if (args.length() > 0) {
            sb.append("<").append(args).append(">");
        }
        return sb.toString();
    }
    
}

