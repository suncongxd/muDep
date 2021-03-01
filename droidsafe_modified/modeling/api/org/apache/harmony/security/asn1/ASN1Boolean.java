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
* @author Vladimir N. Molotkov, Stepan M. Mishura
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.asn1;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;

public final class ASN1Boolean extends ASN1Primitive {

    /**
     * Returns ASN.1 Boolean type default implementation
     *
     * The default implementation works with encoding
     * that is represented as Boolean object.
     *
     * @return ASN.1 Boolean type default implementation
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.514 -0500", hash_original_method = "D432504F1C834D9908D88CC415EEDF53", hash_generated_method = "90B125D538BA019B71FDE7D679B840FD")
    
public static ASN1Boolean getInstance() {
        return ASN1;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.508 -0500", hash_original_field = "90CE8DADE0AD498583C2910DD6CAC4A3", hash_generated_field = "7128E6C29F8DA081B0F9E7466343FAEE")

    private static final ASN1Boolean ASN1 = new ASN1Boolean();

    /**
     * Constructs ASN.1 Boolean type
     *
     * The constructor is provided for inheritance purposes
     * when there is a need to create a custom ASN.1 Boolean type.
     * To get a default implementation it is recommended to use
     * getInstance() method.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.511 -0500", hash_original_method = "FD99504F4E1A7A3268A56063556CF13F", hash_generated_method = "9775D2BBED0F49B99812D077BDD49BEC")
    
public ASN1Boolean() {
        super(TAG_BOOLEAN);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.517 -0500", hash_original_method = "4CABEEE4334687B876309AFB865CC60D", hash_generated_method = "40B9146BAD6C82589C7418E0AFC76366")
    
public Object decode(BerInputStream in) throws IOException {
        in.readBoolean();

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * Extracts Boolean object from BER input stream.
     *
     * @param in - BER input stream
     * @return java.lang.Boolean object
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.519 -0500", hash_original_method = "61E80FB6E60B72FAF41ECC8283C8390E", hash_generated_method = "68FD17F8CA2219EA7226BC974AB34363")
    
@Override public Object getDecodedObject(BerInputStream in) throws IOException {
        if (in.buffer[in.contentOffset] == 0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.521 -0500", hash_original_method = "6CB7A1753C32BDECF173E6F0AF328B6D", hash_generated_method = "28FDD2C5216CE89F94DCA00542AB6353")
    
public void encodeContent(BerOutputStream out) {
        out.encodeBoolean();
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:51.523 -0500", hash_original_method = "E10D3150F01479FE85EBF9E119EE1844", hash_generated_method = "0BF1AD8908E90553E0B28989C8772B96")
    
public void setEncodingContent(BerOutputStream out) {
        out.length = 1;
    }
}

