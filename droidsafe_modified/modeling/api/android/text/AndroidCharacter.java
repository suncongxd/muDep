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


package android.text;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

import droidsafe.helpers.DSUtils;

public class AndroidCharacter {
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static void getDirectionalities(char[] src, byte[] dest,
                                                  int count) {
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static int getEastAsianWidth(char input) {
                return input;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static void getEastAsianWidths(char[] src, int start,
                                                 int count, byte[] dest) {
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static boolean mirror(char[] text, int start, int count) {
                return (((text[0] + text.getTaintInt() + start + count)) == 1);
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static char getMirror(char ch) {
        return ch;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.670 -0500", hash_original_field = "2BEB84B214627D9549964994B4BB5258", hash_generated_field = "98DECEB35F8226006D9E5F1A18C84ABF")

    public static final int EAST_ASIAN_WIDTH_NEUTRAL = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.673 -0500", hash_original_field = "1466459EB4A130BA8D7791282F0C2CD2", hash_generated_field = "B788AEF90FD61851C3B407DF2E123649")

    public static final int EAST_ASIAN_WIDTH_AMBIGUOUS = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.676 -0500", hash_original_field = "4F36059EF4CBDCEA11570E65471E1678", hash_generated_field = "D05E77E189C53407D5D0AA6A0DA0744F")

    public static final int EAST_ASIAN_WIDTH_HALF_WIDTH = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.679 -0500", hash_original_field = "1EAFBE3E4A5CB19EA1B2CBCCCB422D7B", hash_generated_field = "6C2E77B7FF4C3F7740D29FCA73C3E47D")

    public static final int EAST_ASIAN_WIDTH_FULL_WIDTH = 3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.682 -0500", hash_original_field = "561DAE2829B7383E2338F2820D56D801", hash_generated_field = "7BC3398C60B13E1CAE358A0D2B17DAB4")

    public static final int EAST_ASIAN_WIDTH_NARROW = 4;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:12.684 -0500", hash_original_field = "C06928A2D2A6B08B2D4FCF2ACAABF949", hash_generated_field = "BF1AF0A4B120941E4601D2BB087123B2")

    public static final int EAST_ASIAN_WIDTH_WIDE = 5;
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.365 -0400", hash_original_method = "9F50F98AF92A1A34E78D985413026D5F", hash_generated_method = "9F50F98AF92A1A34E78D985413026D5F")
    public AndroidCharacter ()
    {
        //Synthesized constructor
    }
}

