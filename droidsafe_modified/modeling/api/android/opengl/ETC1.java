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
 * Copyright (C) 2009 The Android Open Source Project
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


package android.opengl;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.nio.Buffer;

import droidsafe.helpers.DSUtils;

public class ETC1 {
    
    public static void encodeBlock(Buffer in, int validPixelMask, Buffer out) {
        out.addTaint(in.getTaint());
        out.addTaint(validPixelMask);
    }
    
    public static void decodeBlock(Buffer in, Buffer out) {
        out.addTaint(in.getTaint());
    }
    
    public static int getEncodedDataSize(int width, int height) {
        return (width + height);
    }
    
    public static void encodeImage(Buffer in, int width, int height,
            int pixelSize, int stride, Buffer out) {
    }
    
    public static void decodeImage(Buffer in, Buffer out,
            int width, int height, int pixelSize, int stride) {
    }
    
    public static void formatHeader(Buffer header, int width, int height) {
    }
    
    @DSComment("OpenGL ETC1")
    @DSSafe(DSCat.GRAPHICS)
    public static boolean isValid(Buffer header) {
        return header.getTaintBoolean();
    }
    
    @DSComment("OpenGL ETC1")
    @DSSafe(DSCat.GRAPHICS)
    public static int getWidth(Buffer header) {
        return header.getTaintInt();
    }
    
    @DSComment("OpenGL ETC1")
    @DSSafe(DSCat.GRAPHICS)
    public static int getHeight(Buffer header) {
        return header.getTaintInt();
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:09.140 -0500", hash_original_field = "3FD41814A44CD87686FFB634677A1BB4", hash_generated_field = "3863B19F8A17DA2305AA0B46C80C0681")

    public static final int ENCODED_BLOCK_SIZE = 8;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:09.142 -0500", hash_original_field = "868B2FAE7C9AACD98DB11CE9061A4C5D", hash_generated_field = "CD937CC0221DEB1D6DE4D19C9EDDAAB1")

    public static final int DECODED_BLOCK_SIZE = 48;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:09.145 -0500", hash_original_field = "4330A902963344A92CD80D34B202D4A7", hash_generated_field = "887BE6CF1F15429C9D844A213EBC78E4")

    public static final int ETC_PKM_HEADER_SIZE = 16;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:09.147 -0500", hash_original_field = "8E26BB05348CD082DAF5BF6C8F86D198", hash_generated_field = "FFF4172237392812166125A04B3CE565")

    public static final int ETC1_RGB8_OES = 0x8D64;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:23.897 -0400", hash_original_method = "A91C782DE4E2E5AE629C67E54AACAB60", hash_generated_method = "A91C782DE4E2E5AE629C67E54AACAB60")
    public ETC1 ()
    {
        //Synthesized constructor
    }
}

