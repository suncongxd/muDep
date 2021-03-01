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

public class BerOutputStream {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.353 -0500", hash_original_field = "B26EA4441AC976CE9D513DB2C8639526", hash_generated_field = "8DF314849D9BDCEAE78F4B8DFF0C1E45")

    public byte[] encoded;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.356 -0500", hash_original_field = "B7E810BF01B52122CB927525A0CA4721", hash_generated_field = "22BBBB3BDBDCD622FED3EBF1A70B4EC5")

    protected int offset;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.359 -0500", hash_original_field = "136C4DD872F8065EECD33F6CD4E7556E", hash_generated_field = "1125768121756783FBBAD7831AFCFE31")

    public int length;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.361 -0500", hash_original_field = "D0B8E49E3D027D4128A52EE40E051281", hash_generated_field = "57FED7E8E8FF2184EEF7EFF7F0B770B6")

    public Object content;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:27.816 -0400", hash_original_method = "91962B6D78B996175B89159DB336CF3A", hash_generated_method = "91962B6D78B996175B89159DB336CF3A")
    public BerOutputStream ()
    {
        //Synthesized constructor
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.364 -0500", hash_original_method = "C86EAD42221FC89550F7365AC0B9ACF2", hash_generated_method = "D63CD6FFCEA7482D7B61BF80EE61A14F")
    
public final void encodeTag(int tag) {
        encoded[offset++] = (byte) tag; //FIXME long form?

        if (length > 127) { //long form
            int eLen = length >> 8;
            byte numOctets = 1;
            for (; eLen > 0; eLen = eLen >> 8) {
                numOctets++;
            }

            encoded[offset] = (byte) (numOctets | 0x80);
            offset++;

            eLen = length;
            int numOffset = offset + numOctets - 1;
            for (int i = 0; i < numOctets; i++, eLen = eLen >> 8) {
                encoded[numOffset - i] = (byte) eLen; //FIXME long value?
            }
            offset += numOctets;
        } else { //short form
            encoded[offset++] = (byte) length;
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.366 -0500", hash_original_method = "EA25C8AA6C461721569737ADD747FC16", hash_generated_method = "C7D760A8C12078F7F8936F09214E86F5")
    
public void encodeANY() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.368 -0500", hash_original_method = "5C094D2C250154036897578CBC75AFD1", hash_generated_method = "49A6EF30A88B2FCCA1FFCA7F5F965281")
    
public void encodeBitString() {
        //FIXME check encoding
        BitString bStr = (BitString) content;
        encoded[offset] = (byte) bStr.unusedBits;
        System.arraycopy(bStr.bytes, 0, encoded, offset + 1, length - 1);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.370 -0500", hash_original_method = "E8ACAA21C20B23BBCED2827068B00143", hash_generated_method = "6208FEF4EF027BE052E9DEE2E3BB272F")
    
public void encodeBoolean() {
        if ((Boolean) content) {
            encoded[offset] = (byte) 0xFF;
        } else {
            encoded[offset] = 0x00;
        }
        offset++;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.372 -0500", hash_original_method = "E802EB76E66355F21E1DFC6D16124774", hash_generated_method = "A79A2A8C0A0711132DEFD4D7EF0BA131")
    
public void encodeChoice(ASN1Choice choice) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.375 -0500", hash_original_method = "292D7E5BD8ED56974441257C805288C7", hash_generated_method = "D49047F98F042E4A1FA695671FD688E4")
    
public void encodeExplicit(ASN1Explicit explicit) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.377 -0500", hash_original_method = "2F89F53709F36ECD4B32D0929F0F5EAE", hash_generated_method = "B50176E93D60B5D0F9731F20E48DDB1A")
    
public void encodeGeneralizedTime() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.379 -0500", hash_original_method = "1CD5409EC89A1F8D9E3008915332B968", hash_generated_method = "A3B8141A5EE3A0F899882FD71DBE0A87")
    
public void encodeUTCTime() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.382 -0500", hash_original_method = "6878EDB9468F416E1691597293C87648", hash_generated_method = "377952B08CBDD3251A08D419820A16B6")
    
public void encodeInteger() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.384 -0500", hash_original_method = "9339BB7E7D4F4A4E178E3D3139642B19", hash_generated_method = "A05EEE79401A83DCB44D6481F0004958")
    
public void encodeOctetString() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.387 -0500", hash_original_method = "51F7D06C1A64E7B90214CEB0BC04A311", hash_generated_method = "4ECD17F46FADD47352E2921B6A9F9AEF")
    
public void encodeOID() {

        int[] oid = (int[]) content;

        int oidLen = length;

        // all subidentifiers except first
        int elem;
        for (int i = oid.length - 1; i > 1; i--, oidLen--) {
            elem = oid[i];
            if (elem > 127) {
                encoded[offset + oidLen - 1] = (byte) (elem & 0x7F);
                elem = elem >> 7;
                for (; elem > 0;) {
                    oidLen--;
                    encoded[offset + oidLen - 1] = (byte) (elem | 0x80);
                    elem = elem >> 7;
                }
            } else {
                encoded[offset + oidLen - 1] = (byte) elem;
            }
        }

        // first subidentifier
        elem = oid[0] * 40 + oid[1];
        if (elem > 127) {
            encoded[offset + oidLen - 1] = (byte) (elem & 0x7F);
            elem = elem >> 7;
            for (; elem > 0;) {
                oidLen--;
                encoded[offset + oidLen - 1] = (byte) (elem | 0x80);
                elem = elem >> 7;
            }
        } else {
            encoded[offset + oidLen - 1] = (byte) elem;
        }

        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.390 -0500", hash_original_method = "D323E1EB3DEEC7478D240465668C5921", hash_generated_method = "A4213DDE2C78C4CA92BCADE50F3BB286")
    
public void encodeSequence(ASN1Sequence sequence) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.392 -0500", hash_original_method = "8F8FA1CFCDC711CAAAA81FD323F60610", hash_generated_method = "A34A62C2FFFBC07CAF5E9DD975449C6E")
    
public void encodeSequenceOf(ASN1SequenceOf sequenceOf) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.394 -0500", hash_original_method = "C800AB149478B24214827D5CC5B7FA3B", hash_generated_method = "2E6A406A64CA246C31EEEA2A61B1F7CA")
    
public void encodeSet(ASN1Set set) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.396 -0500", hash_original_method = "5BA1B115D777FEA0793A44B700946129", hash_generated_method = "1B77CA08585047A6B62F0FC4FA6791EE")
    
public void encodeSetOf(ASN1SetOf setOf) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.399 -0500", hash_original_method = "C20EE97623962055A396212EF514666C", hash_generated_method = "25E01BFDD7448A3E556E43E3A72D145D")
    
public void encodeString() {
        System.arraycopy(content, 0, encoded, offset, length);
        offset += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.401 -0500", hash_original_method = "3B2CD397D1827B3F55A2B2B2B09D5CEC", hash_generated_method = "0DF13BB513BE53A2FDDAD89E2F45E40D")
    
public void getChoiceLength(ASN1Choice choice) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.403 -0500", hash_original_method = "D74AD9AD6B17DC6BC5FAEB15F22A4F28", hash_generated_method = "BFF09AD54996D10E5359559FC70D010B")
    
public void getExplicitLength(ASN1Explicit sequence) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.406 -0500", hash_original_method = "C51A0127E3C370A72526C726CD92E293", hash_generated_method = "406A868EFF5F13874DF66B0DA18E8633")
    
public void getSequenceLength(ASN1Sequence sequence) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.408 -0500", hash_original_method = "02ACE8DD7FB1180FD62EB5624B511B64", hash_generated_method = "75B9FE9486167BC45F8B4740C87C39CB")
    
public void getSequenceOfLength(ASN1SequenceOf sequence) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.410 -0500", hash_original_method = "2106F07F5CB10EE3DAB9C7BD5AA5C772", hash_generated_method = "D9529A0E7B851A06DB8A6CFED951E6D1")
    
public void getSetLength(ASN1Set set) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:50.412 -0500", hash_original_method = "AAF1C08C7F71173806810BED7152C887", hash_generated_method = "A4360EEEBFA49FBAE7B61A697055C6E6")
    
public void getSetOfLength(ASN1SetOf setOf) {
        throw new RuntimeException("Is not implemented yet"); //FIXME
    }
    
}

