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
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Vladimir N. Molotkov
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.utils;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class Array {

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.950 -0500", hash_original_method = "FA034E4EBC074D0021BF09B925B81D45", hash_generated_method = "378E379754A9699AAC53073DD4B4E1A8")
    
public static String getBytesAsString(byte[] data) {
        StringBuilder result = new StringBuilder(data.length * 3);
        for (int i = 0; i < data.length; ++i) {
            result.append(Byte.toHexString(data[i], false));
            result.append(' ');
        }
        return result.toString();
    }

    /**
     * Represents <code>array</code> as <code>String</code>
     * for printing. Array length can be up to 32767
     *
     * @param array to be represented as <code>String</code>
     *
     * @return <code>String</code> representation of the <code>array</code>
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.953 -0500", hash_original_method = "A9B6ED6B9ACE659EADD4FA05AC05EB96", hash_generated_method = "BB852DBF9E1E9E06A227AF0903B04500")
    
public static String toString(byte[] array, String prefix) {
        // Prefixes to be added to the offset values
        // in <code>String toString(byte[], String)</code> method
        final String[] offsetPrefix = {
                "",
                "000",
                "00",
                "0",
                ""
        };
        StringBuilder sb = new StringBuilder();
        StringBuilder charForm = new StringBuilder();
        int i=0;
        for (i=0; i<array.length; i++) {
            if (i%16 == 0) {
                sb.append(prefix);
                // put offset
                String offset = Integer.toHexString(i);
                sb.append(offsetPrefix[offset.length()]);
                sb.append(offset);
                // clear char form for new line
                charForm.delete(0, charForm.length());
            }
            // put delimiter
            sb.append(' ');
            // put current byte
            sb.append(Byte.toHexString(array[i], false));
            // form character representation part
            int currentByte = (0xff & array[i]);
            char currentChar = (char)(currentByte & 0xffff);
            // FIXME if needed (how to distinguish PRINTABLE chars?)
            charForm.append(
                    (Character.isISOControl(currentChar) ? '.' : currentChar));
            // Add additional delimiter for each 8 values
            if ((i+1)%8 == 0) {
                sb.append(' ');
            }
            // Add character representation for each line
            if ((i+1)%16 == 0) {
                sb.append(' ');
                sb.append(charForm.toString());
                sb.append('\n');
            }
        }
        // form last line
        if (i%16 != 0) {
            int ws2add = 16 - i%16;
            for (int j=0; j<ws2add; j++) {
                sb.append("   ");
            }
            if (ws2add > 8) {
                sb.append(' ');
            }
            sb.append("  ");
            sb.append(charForm.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    // No instances of this class
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.947 -0500", hash_original_method = "27C967B98A825ABE55DF7CE292BE2D15", hash_generated_method = "6D2788CE118EA61713C3B72F25526FFC")
    
private Array() {
    }
    
}

