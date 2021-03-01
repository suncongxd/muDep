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
 * Copyright (C) 2006 The Android Open Source Project
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


package com.google.android.gles_jni;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL;

public class EGLContextImpl extends EGLContext {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.717 -0500", hash_original_field = "10175383E59D6C3A32BFD1FFB183EA20", hash_generated_field = "1753C0D2BD5296C308BB3830FFA0D688")

    private GLImpl mGLContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.719 -0500", hash_original_field = "3DAFF8299804F9171A0188AA3FAD9E2C", hash_generated_field = "3DAFF8299804F9171A0188AA3FAD9E2C")

    int mEGLContext;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.723 -0500", hash_original_method = "FEF1413AB5716E2F3C8722DC6F3C138C", hash_generated_method = "49431909C3BFF6880BA0C278707F348C")
    
public EGLContextImpl(int ctx) {
        mEGLContext = ctx;
        mGLContext = new GLImpl();
    }
 
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.725 -0500", hash_original_method = "128F5F8CB79090E8F4914FDDEA205182", hash_generated_method = "3A3307A1C5AFA7A42119E9C3BC08F29E")
    
@Override
    public GL getGL() {
        return mGLContext;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.728 -0500", hash_original_method = "BBF69D7F309BD2893B1B10431DBDEB60", hash_generated_method = "451D87C3A7E4627D0D30A7C5DCEFFB0D")
    
@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EGLContextImpl that = (EGLContextImpl) o;

        return mEGLContext == that.mEGLContext;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:16.730 -0500", hash_original_method = "64133BD5554848E690D4A8A4234F010E", hash_generated_method = "BF745D86327796CAAB4A576B9C63877F")
    
@Override
    public int hashCode() {
        return mEGLContext;
    }
    
}

