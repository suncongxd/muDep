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


package java.nio.charset;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.UTFDataFormatException;
import java.nio.ByteOrder;

import libcore.io.Memory;
import libcore.io.SizeOf;

public class ModifiedUtf8 {
    /**
     * Decodes a byte array containing <i>modified UTF-8</i> bytes into a string.
     *
     * <p>Note that although this method decodes the (supposedly impossible) zero byte to U+0000,
     * that's what the RI does too.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:00.677 -0500", hash_original_method = "5393979ADC62E28ADD27F66A98DE94B1", hash_generated_method = "E5BF9B5D16592C07021AAD713B61E5BA")
    
public static String decode(byte[] in, char[] out, int offset, int utfSize) throws UTFDataFormatException {
        int count = 0, s = 0, a;
        while (count < utfSize) {
            if ((out[s] = (char) in[offset + count++]) < '\u0080') {
                s++;
            } else if (((a = out[s]) & 0xe0) == 0xc0) {
                if (count >= utfSize) {
                    throw new UTFDataFormatException("bad second byte at " + count);
                }
                int b = in[offset + count++];
                if ((b & 0xC0) != 0x80) {
                    throw new UTFDataFormatException("bad second byte at " + (count - 1));
                }
                out[s++] = (char) (((a & 0x1F) << 6) | (b & 0x3F));
            } else if ((a & 0xf0) == 0xe0) {
                if (count + 1 >= utfSize) {
                    throw new UTFDataFormatException("bad third byte at " + (count + 1));
                }
                int b = in[offset + count++];
                int c = in[offset + count++];
                if (((b & 0xC0) != 0x80) || ((c & 0xC0) != 0x80)) {
                    throw new UTFDataFormatException("bad second or third byte at " + (count - 2));
                }
                out[s++] = (char) (((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F));
            } else {
                throw new UTFDataFormatException("bad byte at " + (count - 1));
            }
        }
        return new String(out, 0, s);
    }

    /**
     * Returns the number of bytes the modified UTF-8 representation of 's' would take. Note
     * that this is just the space for the bytes representing the characters, not the length
     * which precedes those bytes, because different callers represent the length differently,
     * as two, four, or even eight bytes. If {@code shortLength} is true, we'll throw an
     * exception if the string is too long for its length to be represented by a short.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:00.680 -0500", hash_original_method = "27037B6AE773D08320C8E234F08B9D01", hash_generated_method = "37460BD83B24AD5DC5FD245D5ADED94E")
    
public static long countBytes(String s, boolean shortLength) throws UTFDataFormatException {
        long result = 0;
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            char ch = s.charAt(i);
            if (ch != 0 && ch <= 127) { // U+0000 uses two bytes.
                ++result;
            } else if (ch <= 2047) {
                result += 2;
            } else {
                result += 3;
            }
            if (shortLength && result > 65535) {
                throw new UTFDataFormatException("String more than 65535 UTF bytes long");
            }
        }
        return result;
    }

    /**
     * Encodes the <i>modified UTF-8</i> bytes corresponding to string {@code s} into the
     * byte array {@code dst}, starting at the given {@code offset}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:00.683 -0500", hash_original_method = "08FFAD61125D208C57B7F98D1BD8BCF0", hash_generated_method = "69D46A38D913DE91F841F8B0969B189B")
    
public static void encode(byte[] dst, int offset, String s) {
        final int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch != 0 && ch <= 127) { // U+0000 uses two bytes.
                dst[offset++] = (byte) ch;
            } else if (ch <= 2047) {
                dst[offset++] = (byte) (0xc0 | (0x1f & (ch >> 6)));
                dst[offset++] = (byte) (0x80 | (0x3f & ch));
            } else {
                dst[offset++] = (byte) (0xe0 | (0x0f & (ch >> 12)));
                dst[offset++] = (byte) (0x80 | (0x3f & (ch >> 6)));
                dst[offset++] = (byte) (0x80 | (0x3f & ch));
            }
        }
    }

    /**
     * Returns an array containing the <i>modified UTF-8</i> form of {@code s}, using a
     * big-endian 16-bit length. Throws UTFDataFormatException if {@code s} is too long
     * for a two-byte length.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:00.686 -0500", hash_original_method = "C3E6BB1DD42E4EBE7B80ABC48FCD6E53", hash_generated_method = "33B14CD4DA61BCF290C46EBC50DE517C")
    
public static byte[] encode(String s) throws UTFDataFormatException {
        int utfCount = (int) ModifiedUtf8.countBytes(s, true);
        byte[] result = new byte[SizeOf.SHORT + utfCount];
        Memory.pokeShort(result, 0, (short) utfCount, ByteOrder.BIG_ENDIAN);
        ModifiedUtf8.encode(result, SizeOf.SHORT, s);
        return result;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:00.688 -0500", hash_original_method = "64489454DCC17A9858C56DA9699AAAE7", hash_generated_method = "BD4DD56CDB226A8A143AE67AC1075BF1")
    
private ModifiedUtf8() {
    }
    
}

