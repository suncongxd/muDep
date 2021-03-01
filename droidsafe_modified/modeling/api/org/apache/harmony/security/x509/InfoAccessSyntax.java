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
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

public final class InfoAccessSyntax extends ExtensionValue {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.076 -0500", hash_original_method = "A1A286E994DF633FF07FDB3976ABD65A", hash_generated_method = "5445AE814B2EA0430DC5726319443538")
    
public static InfoAccessSyntax decode(byte[] encoding) throws IOException {
        return ((InfoAccessSyntax) ASN1.decode(encoding));
    }
    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:29.403 -0400", hash_original_field = "E611BBC850EAC486F3CEF9633B2E6F73", hash_generated_field = "5FA1DEAA374D887C0615B0A6E5AC612B")

    public static final ASN1Type ASN1 = new ASN1SequenceOf(AccessDescription.ASN1) {
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.084 -0500", hash_original_method = "128D0B05BCF58CF172653E030B1C2EFF", hash_generated_method = "B4E3120067DF9D9250709199117195B4")
        
@Override public Object getDecodedObject(BerInputStream in) throws IOException {
            return new InfoAccessSyntax((List<?>) in.content, in.getEncoded());
        }

        @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.087 -0500", hash_original_method = "138F8598CC0F155B5334CA1116D2BA97", hash_generated_method = "D6EBE0F5BDD303E9296F74797AD7C04D")
        
@Override public Collection getValues(Object object) {
            return ((InfoAccessSyntax) object).accessDescriptions;
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.068 -0500", hash_original_field = "C15882074F5EBA96FEB6691B74B48D2D", hash_generated_field = "677A1C50FB50E227670E2B82296409DA")

    private  List<?> accessDescriptions;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.071 -0500", hash_original_method = "4CEC0F2DDF7912449CE6AD3ED26DAD30", hash_generated_method = "50DBF6515DAFDB8326FC23F23E52A946")
    
private InfoAccessSyntax(List<?> accessDescriptions, byte[] encoding) throws IOException {
        if (accessDescriptions == null || accessDescriptions.isEmpty()) {
            throw new IOException("AccessDescriptions list is null or empty");
        }
        this.accessDescriptions = accessDescriptions;
        this.encoding = encoding;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 InfoAccessSyntax.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.073 -0500", hash_original_method = "8CF73AB8FE0E45F61A0A453F52513BE8", hash_generated_method = "552F71A862C62513AAC32037F4D2FC1B")
    
@Override public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.078 -0500", hash_original_method = "CB86A2DBB1FE1A1292A1258597C50E5F", hash_generated_method = "8365B884387567A25282273919992E31")
    
@Override public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("\n---- InfoAccessSyntax:");
        if (accessDescriptions != null) {
            for (Object accessDescription : accessDescriptions) {
                res.append('\n');
                res.append(accessDescription);
            }
        }
        res.append("\n---- InfoAccessSyntax END\n");
        return res.toString();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:56.081 -0500", hash_original_method = "781E3C6C6D65C7307F0C093EFF5048AD", hash_generated_method = "B9CD2E89C521415C8934799965D8549B")
    
@Override public void dumpValue(StringBuilder sb, String prefix) {
        sb.append(prefix).append("AccessDescriptions:\n");
        if (accessDescriptions == null || accessDescriptions.isEmpty()) {
            sb.append("NULL\n");
        } else {
            for (Object accessDescription : accessDescriptions) {
                sb.append(accessDescription.toString());
            }
        }
    }
}

