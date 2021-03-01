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
 * Copyright (C) 2010 The Android Open Source Project
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


package java.lang;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public final class IntegralToString {

    /**
     * Equivalent to Integer.toString(i, radix).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.174 -0500", hash_original_method = "84A07FBCA269378A9147D6530126000E", hash_generated_method = "CF6A398FCDDC219326C41A995D5477D8")
    
    public static String intToString(int i, int radix) {
        String str = new String();
        str.addTaint(i);
        str.addTaint(radix);
        return str;
    }

    /**
     * Equivalent to Integer.toString(i).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.176 -0500", hash_original_method = "B1B4D5F6DCA8EF0F22789512858C9AFE", hash_generated_method = "B3ECD37125ED3587B94ECCC77B8D6BA4")
    
    public static String intToString(int i) {
        String str = new String();
        str.addTaint(i);
        return str;
        //return convertInt(null, i);
    }

    /**
     * Equivalent to sb.append(Integer.toString(i)).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.179 -0500", hash_original_method = "2E283002697DCA5F88CA6E1957785306", hash_generated_method = "BFF96DDFE121937C2DE0C7BAC3CC2780")
    
public static void appendInt(AbstractStringBuilder sb, int i) {
        convertInt(sb, i);
    }

    /**
     * Returns the string representation of i and leaves sb alone if sb is null.
     * Returns null and appends the string representation of i to sb if sb is non-null.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.183 -0500", hash_original_method = "18F04DADB801BEC6FD2BF5A5509D8E24", hash_generated_method = "E79DB73A536926B98FC83AF3DDCD828E")
    
private static String convertInt(AbstractStringBuilder sb, int i) {
        String str = new String();
        str.addTaint(i);
        
        if (sb != null) {
            sb.append0(str);
            return null;
        } else {
            return str;
        }
    }

    /**
     * Equivalent to Long.toString(v, radix).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.186 -0500", hash_original_method = "37B28104B332535CD9A4BDBCC29D5030", hash_generated_method = "B837EAB259A841BED217DC0BA5724CFC")
    
    public static String longToString(long v, int radix) {
        String str = new String();
        str.addTaint(v);
        str.addTaint(radix);
        return str;
    }

    /**
     * Equivalent to Long.toString(l).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.188 -0500", hash_original_method = "153B7EAD25B0FBAAC892ABA7B6CC50EA", hash_generated_method = "07305E55971D5721CD811119E18E7393")
    
public static String longToString(long l) {
        String str = new String();
        str.addTaint(l);
        
        return str;

    }

    /**
     * Equivalent to sb.append(Long.toString(l)).
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.191 -0500", hash_original_method = "1A7366BC9F5E7AAA65E998C16248FAE8", hash_generated_method = "47A45A0DE2D83C625D0B819AC9C77DF2")
    
public static void appendLong(AbstractStringBuilder sb, long l) {
        convertLong(sb, l);
    }

    /**
     * Returns the string representation of n and leaves sb alone if sb is null.
     * Returns null and appends the string representation of n to sb if sb is non-null.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.195 -0500", hash_original_method = "335087D7FF3CE8ACF704F016666ED5E4", hash_generated_method = "9C2CF14366EC4FEC5E2510809E61CCA2")
    
private static String convertLong(AbstractStringBuilder sb, long n) {
        String str = new String();
        str.addTaint(n);

        if (sb != null) {
            sb.append0(str);
            return null;
        } else {
            return str;
        }
    }

    /**
     * Inserts the unsigned decimal integer represented by n into the specified
     * character array starting at position cursor.  Returns the index after
     * the last character inserted (i.e., the value to pass in as cursor the
     * next time this method is called). Note that n is interpreted as a large
     * positive integer (not a negative integer) if its sign bit is set.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.197 -0500", hash_original_method = "AA8AE34ED725E04DA1882B7CEAEB718F", hash_generated_method = "C5EE79131CBB996273FBE7D43CDE847F")
    
private static int intIntoCharArray(char[] buf, int cursor, int n) {
        buf[0] = (char)cursor;
        buf[0] = (char)n;
        return cursor;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.201 -0500", hash_original_method = "A41E5D3875136344B237460B8AD39DE0", hash_generated_method = "BF9029A36E87AD6C0F7646EBDC2AD21F")
    
public static String intToBinaryString(int i) {
        String str = new String();
        str.addTaint(i);
        return str;
        /*
        int bufLen = 32;  // Max number of binary digits in an int
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        do {
            buf[--cursor] = DIGITS[i & 1];
        }  while ((i >>>= 1) != 0);

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.203 -0500", hash_original_method = "425709456D43055F08B99D956BED133A", hash_generated_method = "19DE5B453C600CEADCEE9910F20C6563")
    
public static String longToBinaryString(long v) {
        String str = new String();
        str.addTaint(v);
        return str;
        /*
        int i = (int) v;
        if (v >= 0 && i == v) {
            return intToBinaryString(i);
        }

        int bufLen = 64;  // Max number of binary digits in a long
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        do {
            buf[--cursor] = DIGITS[((int) v) & 1];
        }  while ((v >>>= 1) != 0);

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.206 -0500", hash_original_method = "25742899540A4F6BF48504885A2F05E0", hash_generated_method = "47163F3DAA1C79F199346566712562F1")
    
public static StringBuilder appendByteAsHex(StringBuilder sb, byte b, boolean upperCase) {
        sb.append(b);
        sb.addTaint(upperCase);
        return sb;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.208 -0500", hash_original_method = "010E15EE749C468A15543203E9BC66F2", hash_generated_method = "65341427BE591FEECBDD3EBCD1A98B15")
    
public static String byteToHexString(byte b, boolean upperCase) {
        String str = new String();
        str.addTaint(b);
        return str;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.211 -0500", hash_original_method = "744A47F287A2754D655F5FDE5DEB2E5D", hash_generated_method = "8851B88F67110322BF450F54F6CBA037")
    
    public static String bytesToHexString(byte[] bytes, boolean upperCase) {
        String str = new String();
        str.addTaint(bytes[0]);
        return str;
        /*
        char[] digits = upperCase ? UPPER_CASE_DIGITS : DIGITS;
        char[] buf = new char[bytes.length * 2];
        int c = 0;
        for (byte b : bytes) {
            buf[c++] = digits[(b >> 4) & 0xf];
            buf[c++] = digits[b & 0xf];
        }
        return new String(buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.213 -0500", hash_original_method = "53C9BEADC911A160577C54E5E3F03689", hash_generated_method = "1B231099D778F3AA3ECB08BDBE1B4A8A")
    
public static String intToHexString(int i, boolean upperCase, int minWidth) {
        String str = new String();
        str.addTaint(i);
        str.addTaint(minWidth);
        str.addTaint(upperCase);
        return str;
        
        /*
        int bufLen = 8;  // Max number of hex digits in an int
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        char[] digits = upperCase ? UPPER_CASE_DIGITS : DIGITS;
        do {
            buf[--cursor] = digits[i & 0xf];
        } while ((i >>>= 4) != 0 || (bufLen - cursor < minWidth));

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.216 -0500", hash_original_method = "40D5415945BB8116A305EC0340CF03D3", hash_generated_method = "AC3B506B8F65F32340C42CCE255AD773")
    
public static String longToHexString(long v) {
        String str = new String();
        str.addTaint(v);
        return str;
        /*
        int i = (int) v;
        if (v >= 0 && i == v) {
            return intToHexString(i, false, 0);
        }

        int bufLen = 16;  // Max number of hex digits in a long
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        do {
            buf[--cursor] = DIGITS[((int) v) & 0xF];
        } while ((v >>>= 4) != 0);

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.219 -0500", hash_original_method = "45CC1A585F589498B158F1646F8FCD50", hash_generated_method = "491C057DAC348EAD6D01733B5670B1D3")
    
public static String intToOctalString(int i) {
        String str = new String();
        str.addTaint(i);
        return str;

        /*
        int bufLen = 11;  // Max number of octal digits in an int
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        do {
            buf[--cursor] = DIGITS[i & 7];
        } while ((i >>>= 3) != 0);

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.222 -0500", hash_original_method = "0B792E8186913E3E66D8EB47F2B35BC9", hash_generated_method = "CD5497C1B1686D3D197A7F06DC143795")
    
public static String longToOctalString(long v) {
        String str = new String();
        str.addTaint(v);
        return str;

        /*
        int i = (int) v;
        if (v >= 0 && i == v) {
            return intToOctalString(i);
        }
        int bufLen = 22;  // Max number of octal digits in a long
        char[] buf = new char[bufLen];
        int cursor = bufLen;

        do {
            buf[--cursor] = DIGITS[((int) v) & 7];
        } while ((v >>>= 3) != 0);

        return new String(cursor, bufLen - cursor, buf);
        */
    }

    /**
     * Returns a string composed of the specified characters. Note that the
     * autoboxing does *not* result in an extra copy of the char array: we are
     * using a package-private string constructor that incorporates the
     * "autoboxing array" into the new string.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.224 -0500", hash_original_method = "F1C6410A467B286589D07AFCC53374B5", hash_generated_method = "49D2D4BE49B87B4FD1B9513189C9E32D")
    
    private static String stringOf(char... args) {
        return new String(0, args.length, args);
    }
    
    /*
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:50.236 -0400", hash_original_field = "AB280E60F0CF35D4F468078DFDA43178", hash_generated_field = "425773BE567620BA8858A8C9A806C3AB")

        private static final ThreadLocal<char[]> BUFFER = new ThreadLocal<char[]>() {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:14:50.236 -0400", hash_original_method = "2EBCA8DA39A849AFE3E3749C7F49BA79", hash_generated_method = "DD3C6EC86D330D1AF7830345AF558804")
        @Override
        protected char[] initialValue() {
            char[] var401101FA2F200BBC59DD27CE1AD00AD7_83035988 = (new char[20]);
            char[] var50607924ABD4C17119BAF3A1CE41C0EC_66101806 = {getTaintChar()};
            return var50607924ABD4C17119BAF3A1CE41C0EC_66101806;
            
        }
        
};

@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.151 -0500", hash_original_field = "37DFB5C87B09E2D3A46A9A888D8120E2", hash_generated_field = "3F7A7621FDECF4B1F00F7F02BB032983")

    private static final String[] SMALL_NONNEGATIVE_VALUES = new String[100];
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.154 -0500", hash_original_field = "FFDF6514E961275EBE2DC63ABCA79370", hash_generated_field = "0F2C6CD52D3AC35204AE87E543B933FE")

    private static final String[] SMALL_NEGATIVE_VALUES = new String[100];
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.157 -0500", hash_original_field = "F311DA27D751D5FFD831BBA6CCCE30CC", hash_generated_field = "F4F0B5078F2B36CA1654493CC02FBE1B")

    private static final char[] TENS = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.160 -0500", hash_original_field = "F4BBEFA84DF5357F6D7EA1D47F1A573A", hash_generated_field = "C40FC9CE2E1863E8D99B8E9E8459B998")

    private static final char[] ONES = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.163 -0500", hash_original_field = "0703CCE2B73E1DC5A6EA665244A4D9F8", hash_generated_field = "7ECA72DEBCDA6EB92131D3D0F3D8765E")

    private static final char[] MOD_10_TABLE = {
        0, 1, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 8, 9, 0
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.165 -0500", hash_original_field = "FD7EDE0EC664D36F57D4A03087EFD371", hash_generated_field = "0F077AEAC9BBB0ADF3D0D4E6AACE2DF1")

    private static final char[] DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.168 -0500", hash_original_field = "0BAC015C4EE9B0B2ADAFBCE97C96378D", hash_generated_field = "C31924D8CB8753379AD2B96C86ABBAE8")

    private static final char[] UPPER_CASE_DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:21.171 -0500", hash_original_method = "DF20FC0B1F395B5ECC5581BA0BD3AC0B", hash_generated_method = "F9A5116623A8F121F9E7894C48ABCCA4")
    */    
@DSSafe(DSCat.SAFE_OTHERS)
    private IntegralToString() {
    }
}

