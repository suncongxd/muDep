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
* @author Alexander V. Esin
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.x501;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;

import org.apache.harmony.security.asn1.ASN1StringType;
import org.apache.harmony.security.asn1.DerInputStream;

public final class AttributeValue {

    /**
     * Checks if the string is PrintableString (see X.680)
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.069 -0500", hash_original_method = "0119351D8B397CC03524377768328668", hash_generated_method = "694F8F6CDA78A953478E162FD1FBA255")
    
private static boolean isPrintableString(String str) {
        for (int i = 0; i< str.length(); ++i) {
            char ch = str.charAt(i);
            if (!(ch == 0x20
            || ch >= 0x27 && ch<= 0x29 // '()
            || ch >= 0x2B && ch<= 0x3A // +,-./0-9:
            || ch == '='
            || ch == '?'
            || ch >= 'A' && ch<= 'Z'
            || ch >= 'a' && ch<= 'z')) {
                return false;
            }
        }
        return true;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.043 -0500", hash_original_field = "84B201F12B93C4A22935FF8AEB825853", hash_generated_field = "D85F8A65E9DE43097BB265479C87E73B")

    public  boolean wasEncoded;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.045 -0500", hash_original_field = "C2F83787DCFDB0BEF15AD79E38B15E12", hash_generated_field = "4A17972559F62AB2783311336A9D4582")

    public String escapedString;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.047 -0500", hash_original_field = "5DA0D0DEEDF14DDA1194714C7CBCD3AD", hash_generated_field = "6CF85B6901CE5996CC9D91D793C48B7A")

    private String hexString;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.049 -0500", hash_original_field = "75DDD831AA6BEDC0081548B34B0142BC", hash_generated_field = "917AECB67DEB49506F6054710716A2C2")

    private int tag = -1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.051 -0500", hash_original_field = "B26EA4441AC976CE9D513DB2C8639526", hash_generated_field = "8DF314849D9BDCEAE78F4B8DFF0C1E45")

    public byte[] encoded;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.054 -0500", hash_original_field = "AB97A4156FC1CC1DAF26375194010FF1", hash_generated_field = "1395852C401E496F85701A0A9D82F003")

    public byte[] bytes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.055 -0500", hash_original_field = "60A47098D83F88DA9997B0BA3591E851", hash_generated_field = "439798B0B0B82905E05B97B14950EE49")

    public boolean hasQE;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.058 -0500", hash_original_field = "3F1856C6499EED7D4325FD9564A51E14", hash_generated_field = "B06E71DF7A343E20C8542F4E7F9F2A51")

    public String rawString;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.060 -0500", hash_original_method = "A5E243B4900F23BDC01508FC8D249E26", hash_generated_method = "927496E81DBFAA5431F59DF74E7FB345")
    
public AttributeValue(String parsedString, boolean hasQorE) {
        wasEncoded = false;

        this.hasQE = hasQorE;

        this.rawString = parsedString;
        this.escapedString = makeEscaped(rawString);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.063 -0500", hash_original_method = "5A85F043C6F0B70EF97E2EBE9C6D742A", hash_generated_method = "1F804262F26858324941205B87C784D2")
    
public AttributeValue(String hexString, byte[] encoded) {
        wasEncoded = true;

        this.hexString = hexString;
        this.encoded = encoded;

        try {
            DerInputStream in = new DerInputStream(encoded);

            tag = in.tag;

            if (DirectoryString.ASN1.checkTag(tag)) {
                // has string representation
                this.rawString = (String) DirectoryString.ASN1.decode(in);
                this.escapedString = makeEscaped(rawString);
            } else {
                this.rawString = hexString;
                this.escapedString = hexString;
            }
        } catch (IOException e) {
            IllegalArgumentException iae = new IllegalArgumentException(); //FIXME message
            iae.initCause(e);
            throw iae;
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.066 -0500", hash_original_method = "B45EBCED75AEC87D8B9060BD1DB130BC", hash_generated_method = "19C0FE8CD225B697D894EF61A2EC6714")
    
public AttributeValue(String rawString, byte[] encoded, int tag) {
        wasEncoded = true;

        this.encoded = encoded;
        this.tag = tag;

        if (rawString == null) {
            this.rawString = getHexString();
            this.escapedString = hexString;
        } else {
            this.rawString = rawString;
            this.escapedString = makeEscaped(rawString);
        }
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.072 -0500", hash_original_method = "01F8BD1A541A09C15BC5DA5B1F02995A", hash_generated_method = "CC77EF1B72A571133314D3BEE05D8B2B")
    
public int getTag() {
        if (tag == -1) {
            tag = isPrintableString(rawString)
                    ? ASN1StringType.PRINTABLESTRING.id
                    : ASN1StringType.UTF8STRING.id;
        }
        return tag;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.075 -0500", hash_original_method = "2B88DEF802689DAD6E450E66D9E740A5", hash_generated_method = "950FD46BFC3ADB9C126FD6DEDF460343")
    
public String getHexString() {
        if (hexString == null) {
            if (!wasEncoded) {
                //FIXME optimize me: what about reusable OutputStream???
                encoded = isPrintableString(rawString)
                        ? ASN1StringType.PRINTABLESTRING.encode(rawString)
                        : ASN1StringType.UTF8STRING.encode(rawString);
            }

            StringBuilder buf = new StringBuilder(encoded.length * 2 + 1);
            buf.append('#');

            for (int i = 0, c; i < encoded.length; i++) {
                c = (encoded[i] >> 4) & 0x0F;
                if (c < 10) {
                    buf.append((char) (c + 48));
                } else {
                    buf.append((char) (c + 87));
                }

                c = encoded[i] & 0x0F;
                if (c < 10) {
                    buf.append((char) (c + 48));
                } else {
                    buf.append((char) (c + 87));
                }
            }
            hexString = buf.toString();
        }
        return hexString;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.077 -0500", hash_original_method = "8E668EEB627F91F8E1893DDB676A8040", hash_generated_method = "C56B3C3666CF8EAD999597FF1B5EA941")
    
public void appendQEString(StringBuilder sb) {
        sb.append('"');
        if (hasQE) {
            char c;
            for (int i = 0; i < rawString.length(); i++) {
                c = rawString.charAt(i);
                if (c == '"' || c == '\\') {
                    sb.append('\\');
                }
                sb.append(c);
            }
        } else {
            sb.append(rawString);
        }
        sb.append('"');
    }

    /**
     * Escapes:
     * 1) chars ",", "+", """, "\", "<", ">", ";" (RFC 2253)
     * 2) chars "#", "=" (required by RFC 1779)
     * 3) a space char at the beginning or end
     * 4) according to the requirement to be RFC 1779 compatible:
     *    '#' char is escaped in any position
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.079 -0500", hash_original_method = "63614CD0FCED93BBCAC192EC682E4A51", hash_generated_method = "A76D80282249E7E9D8EEBB57A30E4E4A")
    
private String makeEscaped(String name) {
        int length = name.length();
        if (length == 0) {
            return name;
        }
        StringBuilder buf = new StringBuilder(length * 2);

        for (int index = 0; index < length; index++) {
            char ch = name.charAt(index);
            switch (ch) {
            case ' ':
                if (index == 0 || index == (length - 1)) {
                    // escape first or last space
                    buf.append('\\');
                }
                buf.append(' ');
                break;

            case '"':
            case '\\':
                hasQE = true;
                buf.append('\\');
                buf.append(ch);
                break;

            case ',':
            case '+':
            case '<':
            case '>':
            case ';':
            case '#': // required by RFC 1779
            case '=': // required by RFC 1779
                buf.append('\\');
                buf.append(ch);
                break;

            default:
                buf.append(ch);
                break;
            }
        }

        return buf.toString();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:55.082 -0500", hash_original_method = "2CE0F91F50A3484CAFB1D45D122355E5", hash_generated_method = "922787D982E1F3EE9E57257108ADAB40")
    
public String makeCanonical() {
        int length = rawString.length();
        if (length == 0) {
            return rawString;
        }
        StringBuilder buf = new StringBuilder(length * 2);

        int index = 0;
        if (rawString.charAt(0) == '#') {
            buf.append('\\');
            buf.append('#');
            index++;
        }

        int bufLength;
        for (; index < length; index++) {
            char ch = rawString.charAt(index);

            switch (ch) {
            case ' ':
                bufLength = buf.length();
                if (bufLength == 0 || buf.charAt(bufLength - 1) == ' ') {
                    break;
                }
                buf.append(' ');
                break;

            case '"':
            case '\\':
            case ',':
            case '+':
            case '<':
            case '>':
            case ';':
                buf.append('\\');

            default:
                buf.append(ch);
            }
        }

        //remove trailing spaces
        for (bufLength = buf.length() - 1; bufLength > -1
                && buf.charAt(bufLength) == ' '; bufLength--) {
        }
        buf.setLength(bufLength + 1);

        return buf.toString();
    }
    
}

