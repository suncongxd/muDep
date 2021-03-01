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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.x509;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;

public final class AccessDescription {
    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:28.767 -0400", hash_original_field = "0862F44506F85070A10D10975AA6FE0E", hash_generated_field = "FF02B80E4F6E40830B394661F3CF20D9")

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Oid.getInstance(),
            GeneralName.ASN1 }) {

        @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.810 -0500", hash_original_method = "1D159E6CCAE0893CDD0658CA6C34CFD2", hash_generated_method = "7F6DBA94211427DFB6406F56E96D2B8A")
        
@Override protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new AccessDescription(
                    ObjectIdentifier.toString((int[]) values[0]),
                    (GeneralName) values[1], in.getEncoded());
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.812 -0500", hash_original_method = "7BC355E37B89E837034CC3EA45D5612B", hash_generated_method = "813F58CD78847B537F5B5B96C5C2F8DB")
        
@Override protected void getValues(Object object, Object[] values) {
            AccessDescription ad = (AccessDescription) object;
            values[0] = ObjectIdentifier.toIntArray(ad.accessMethod);
            values[1] = ad.accessLocation;
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.794 -0500", hash_original_field = "F6CEA4CBA517C4992221907FB85242CD", hash_generated_field = "248BF95BB7065D90F1AFE45C45CFFDF8")

    private  String accessMethod;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.797 -0500", hash_original_field = "8D2EADF673C8E0117D2F09C395E4A7D9", hash_generated_field = "B893404BBB8A0BF472D8CE3A64890544")

    private  GeneralName accessLocation;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.799 -0500", hash_original_field = "B36A946B5145D992E53209BC6743765D", hash_generated_field = "ACB189C73E1A6432570001B3B9D3D516")

    private byte[] encoding;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.801 -0500", hash_original_method = "FB49FC0C1C39F6A3E5A7CF2545478BA4", hash_generated_method = "33E6883F4A012F90A26EF68EA8241D6A")
    
private AccessDescription(String accessMethod, GeneralName accessLocation, byte[] encoding) {
        this.accessMethod = accessMethod;
        this.accessLocation = accessLocation;
        this.encoding = encoding;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 AccessDescription.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.803 -0500", hash_original_method = "8CF73AB8FE0E45F61A0A453F52513BE8", hash_generated_method = "5349B65E746CD014BDA35ADFF927FD5B")
    
public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:58.806 -0500", hash_original_method = "4D59D0B3E432FF02219BDDB6D479EFFA", hash_generated_method = "2DFF6E9DDD53A4912B6979243FF3FE23")
    
@Override public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n-- AccessDescription:");
        res.append("\naccessMethod:  ");
        res.append(accessMethod);
        res.append("\naccessLocation:  ");
        res.append(accessLocation);
        res.append("\n-- AccessDescription END\n");
        return res.toString();
    }
}

