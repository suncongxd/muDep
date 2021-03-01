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
* @author Alexey V. Varlamov, Stepan M. Mishura
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.asn1;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public final class BitString {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.845 -0500", hash_original_field = "B8CA03B1D4CC2487E29E89EEB5EF2F9B", hash_generated_field = "AEC1F0C6EBA822C84ED8236810752976")

    private static final byte[] SET_MASK = { (byte) 128, 64, 32, 16, 8, 4, 2, 1 };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.848 -0500", hash_original_field = "B7965366C621DE185175E5DCDFC59D5B", hash_generated_field = "3E26BC5C80F6AADE58A6D4789BE0B931")

    private static final byte[] RESET_MASK = { 0x7f, (byte) 0xbf, (byte) 0xdf,
            (byte) 0xef, (byte) 0xf7, (byte) 0xfb, (byte) 0xfd, (byte) 0xfe, };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.850 -0500", hash_original_field = "AB97A4156FC1CC1DAF26375194010FF1", hash_generated_field = "1395852C401E496F85701A0A9D82F003")

    public  byte[] bytes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.852 -0500", hash_original_field = "83B59A14DF77F932A19E40814BC16A0D", hash_generated_field = "21A6E7EB9F35414374A81AF4C0F0F758")

    public  int unusedBits;

    /**
     * @param bytes array of bytes that represents bit string,
     *                including unused bits
     * @param unusedBits number of unused bits
     * @throws IllegalArgumentException - if parameters are invalid
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.855 -0500", hash_original_method = "4AD7912E7289EAB288F9F3EC6E0E1363", hash_generated_method = "EE9C41073E608FCBEE4B1B05C5EC355F")
    
public BitString(byte[] bytes, int unusedBits) {
        // constraints are set according X.690
        if (unusedBits < 0 || unusedBits > 7) {
            throw new IllegalArgumentException("Number of unused bits MUST be in range 0-7");
        }

        if (bytes.length == 0 && unusedBits != 0) {
            throw new IllegalArgumentException("For empty bit string unused bits MUST be 0");
        }

        this.bytes = bytes;
        this.unusedBits = unusedBits;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.857 -0500", hash_original_method = "8BDC421B437B146AA72D15C02ACDA1CF", hash_generated_method = "C42A2C7F6F57C05786832763BB386D55")
    
public BitString(boolean[] values) {
        unusedBits = values.length % 8;
        int size = values.length / 8;
        if (unusedBits != 0) {
            size++;
        }
        bytes = new byte[size];
        for (int i = 0; i < values.length; i++) {
            setBit(i, values[i]);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.860 -0500", hash_original_method = "286435896BFB2C2EB12779836BDE2B3F", hash_generated_method = "BD350ACA3161978859F1057654FA1E42")
    
public boolean getBit(int bit) {
        int offset = bit % 8;
        int index = bit / 8;
        return (bytes[index] & SET_MASK[offset]) != 0;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.862 -0500", hash_original_method = "64B37589A76FCD4AF41D04B8A51CCBFB", hash_generated_method = "61747BB9A7962BDC40FA5FD7DA9E3CB4")
    
public void setBit(int bit, boolean value) {
        int offset = bit % 8;
        int index = bit / 8;
        if (value) {
            bytes[index] |= SET_MASK[offset];
        } else {
            bytes[index] &= RESET_MASK[offset];
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.865 -0500", hash_original_method = "6591A9422CBA64DB7D3ADDA84BA94652", hash_generated_method = "7519CBE701372A31CC0A42C2CCF6D086")
    
public boolean[] toBooleanArray() {
        boolean[] result = new boolean[bytes.length * 8 - unusedBits];
        for (int i = 0; i < result.length; i++) {
            result[i] = getBit(i);
        }
        return result;
    }
}

