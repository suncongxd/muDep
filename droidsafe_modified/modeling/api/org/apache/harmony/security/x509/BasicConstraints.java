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
import java.math.BigInteger;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

public final class BasicConstraints extends ExtensionValue {
    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:28.845 -0400", hash_original_field = "08F29BA7DFA287FEFE128BE2F01D2233", hash_generated_field = "8C6491AD1E19D1D98E0A71A1D7A0B4B0")

    public static final ASN1Type ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Boolean.getInstance(), ASN1Integer.getInstance() }) {
        {
            setDefault(Boolean.FALSE, 0);
            setOptional(1);
        }

        @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.477 -0500", hash_original_method = "C98349FCBC509D9B5E2186CE1C87C57F", hash_generated_method = "B66EE4A4596F6ED85DA04FC094547D35")
        
public Object getDecodedObject(BerInputStream in) throws IOException {
            return in.content;
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.479 -0500", hash_original_method = "A1992AD437B5BD7EBE036BB635CECD5B", hash_generated_method = "77AA4D547272B40792A6559B61157478")
        
protected void getValues(Object object, Object[] values) {
            Object[] array = (Object[]) object;
            values[0] = array[0];
            values[1] = ((BigInteger) array[1]).toByteArray();
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.460 -0500", hash_original_field = "AB6282E6A316D6E21FB0AD3AFC9DA417", hash_generated_field = "6CCB782E85B2E142297EC5A5C8505CE2")

    private boolean ca = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.463 -0500", hash_original_field = "9AD509756B4BD6A6D64C446D8CB5D0ED", hash_generated_field = "C2B982A8CAC8FF9BE25F7B9D92A21175")

    private int pathLenConstraint = Integer.MAX_VALUE;

    /**
     * Creates the extension object on the base of its encoded form.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.466 -0500", hash_original_method = "98F3193EB5FEEA682D1EF037C6D0CBF1", hash_generated_method = "0094AF36B5E659EC475EF5D18369F294")
    
public BasicConstraints(byte[] encoding) throws IOException {
        super(encoding);
        Object[] values = (Object[]) ASN1.decode(encoding);
        ca = (Boolean) values[0];
        if (values[1] != null) {
            pathLenConstraint = new BigInteger((byte[]) values[1]).intValue();
        }
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.468 -0500", hash_original_method = "BA19E2BECC620F0A37756A3C3C0D942D", hash_generated_method = "71593070597BC1EB6C5B74063E78A764")
    
public int getPathLenConstraint() {
        return pathLenConstraint;
    }

    /**
     * Returns the encoded form of the object.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.470 -0500", hash_original_method = "946B6F20121D8D093231FCFBEB08839B", hash_generated_method = "8461DBA1A5D45C67C79BFC47B35D2B2F")
    
public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(new Object[]{ca, BigInteger.valueOf(pathLenConstraint) });
        }
        return encoding;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:57.472 -0500", hash_original_method = "13B672A3A148C28AA73E910809626D2C", hash_generated_method = "298A8D6901C26F55484D56FDA2FF09C1")
    
public void dumpValue(StringBuilder sb, String prefix) {
        sb.append(prefix).append("BasicConstraints [\n").append(prefix)
            .append("  CA: ").append(ca)
            .append("\n  ").append(prefix).append("pathLenConstraint: ")
            .append(pathLenConstraint).append('\n').append(prefix)
            .append("]\n");
    }
    // orphaned legacy method
    public Object getDecodedObject(BerInputStream in) throws IOException {
            return in.content;
        }
    
    // orphaned legacy method
    protected void getValues(Object object, Object[] values) {
            Object[] array = (Object[]) object;
            values[0] = array[0];
            values[1] = ((BigInteger) array[1]).toByteArray();
        }
    
}

