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
import java.nio.charset.Charsets;

public abstract class ASN1StringType extends ASN1Type {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.890 -0500", hash_original_field = "A89249C0DB33716E14D2EC6C52F43AA9", hash_generated_field = "984AE0F2D8C4D8BA4C7B183A8976F815")

    // TODO: check decoded/encoded characters
    public static final ASN1StringType BMPSTRING = new ASN1StringType(
            TAG_BMPSTRING) {
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.893 -0500", hash_original_field = "BC1D2F4F52649BE6BF8DB654BD8CC32D", hash_generated_field = "46FCF84077C5B6BDE8192C5E63FCFA67")

    public static final ASN1StringType IA5STRING = new ASN1StringType(
            TAG_IA5STRING) {
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.896 -0500", hash_original_field = "AA5B1153CC892E195321875E6BD00676", hash_generated_field = "66D25C1EA76D6D409D30D03BF74F1CFE")

    public static final ASN1StringType GENERALSTRING = new ASN1StringType(
            TAG_GENERALSTRING) {
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.899 -0500", hash_original_field = "DA26F9B7CD0D683F9B9C17F8B107F97D", hash_generated_field = "B89ED27D3783F77EA167D653EB03C9B5")

    public static final ASN1StringType PRINTABLESTRING = new ASN1StringType(
            TAG_PRINTABLESTRING) {
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.902 -0500", hash_original_field = "213723189950C2853220016AF99EA289", hash_generated_field = "B293D72E13A2C763BCC69EF45A60C3AE")

    public static final ASN1StringType TELETEXSTRING = new ASN1StringType(
            TAG_TELETEXSTRING) {
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.905 -0500", hash_original_field = "1E59FC96DA1E52DA4699ED3D3103D637", hash_generated_field = "B02FC64488CFAC76F7DB04EA74E308B7")

    public static final ASN1StringType UNIVERSALSTRING = new ASN1StringType(
            TAG_UNIVERSALSTRING) {
    };
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:33.642 -0400", hash_original_field = "4C261A036B603BB0A966CDA0E25AA46C", hash_generated_field = "8325E7625E0332A172357D8E029897AE")

    public static final ASN1StringType UTF8STRING = new ASN1StringType(TAG_UTF8STRING) {
        @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.908 -0500", hash_original_method = "8F8B3D16DE176F56B0B78AD50FD6AF2B", hash_generated_method = "CAA4C189C585C3F94A2B1E85AD51AE97")
        
@Override public Object getDecodedObject(BerInputStream in) throws IOException {
            return new String(in.buffer, in.contentOffset, in.length, Charsets.UTF_8);
        }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.929 -0500", hash_original_method = "138CA2E3F6E6A00430513A77732291AD", hash_generated_method = "127D3CEC10D0772A37022EF0498A886C")
    
public void setEncodingContent(BerOutputStream out) {
        byte[] bytes = ((String) out.content).getBytes(Charsets.UTF_8);
        out.content = bytes;
        out.length = bytes.length;
    }
    };

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.915 -0500", hash_original_method = "7B031F7F0A5A04AD815BCBD7B272BFA2", hash_generated_method = "C60094C1F7662E3A8860D1C44C67CD60")
    
public ASN1StringType(int tagNumber) {
        super(tagNumber);
    }

    /**
     * Tests provided identifier.
     *
     * @param identifier identifier to be verified
     * @return true if identifier correspond to primitive or constructed
     *     identifier of this ASN.1 string type, otherwise false
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.918 -0500", hash_original_method = "C1B77E79BF2CF152F3FF34DFE43DC50B", hash_generated_method = "6636A5B331B75E51FECFC6E1DA920798")
    
public final boolean checkTag(int identifier) {
        return this.id == identifier || this.constrId == identifier;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.920 -0500", hash_original_method = "3BFD309C154715F9457C75DB61750061", hash_generated_method = "1EC5B346ED2CF44EF74824EE84F03C48")
    
public Object decode(BerInputStream in) throws IOException {
        in.readString(this);

        if (in.isVerify) {
            return null;
        }
        return getDecodedObject(in);
    }

    /**
     * Extracts String object from BER input stream.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.922 -0500", hash_original_method = "2F8FDB092DEF849FEC2EBC30268CC3AE", hash_generated_method = "498C7F4DEDB3DA422A8089786F26B7DF")
    
public Object getDecodedObject(BerInputStream in) throws IOException {
        /* To ensure we get the correct encoding on non-ASCII platforms, specify
           that we wish to convert from ASCII to the default platform encoding */
        return new String(in.buffer, in.contentOffset, in.length, Charsets.ISO_8859_1);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.925 -0500", hash_original_method = "56680739FC748952853149E66560E94E", hash_generated_method = "6DBE43E8C1D34645AEF0A710F73133F9")
    
public void encodeASN(BerOutputStream out) {
        out.encodeTag(id);
        encodeContent(out);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:52.927 -0500", hash_original_method = "10029CCA141FCEE9BD14EC9FEEE7A0BB", hash_generated_method = "E0295BA97DD0DBF746A015C66365D51C")
    
public void encodeContent(BerOutputStream out) {
        out.encodeString();
    }
        
@DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:27.425 -0400", hash_original_method = "138CA2E3F6E6A00430513A77732291AD", hash_generated_method = "BFBE76DFFA47FA40E7E1BFB51B23707F")
    public void setEncodingContent(BerOutputStream out) {
        addTaint(out.getTaint());
        byte[] bytes = ((String) out.content).getBytes(Charsets.UTF_8);
        out.content = bytes;
        out.length = bytes.length;
        // ---------- Original Method ----------
        //byte[] bytes = ((String) out.content).getBytes(Charsets.UTF_8);
        //out.content = bytes;
        //out.length = bytes.length;
    }
}

