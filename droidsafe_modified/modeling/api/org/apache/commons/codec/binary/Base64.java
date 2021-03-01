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
 * Copyright 2001-2004 The Apache Software Foundation.
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


package org.apache.commons.codec.binary;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Base64 implements BinaryEncoder, BinaryDecoder {

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.942 -0500", hash_original_method = "A822EBC4FB6E69A8F1BB99F6026DF203", hash_generated_method = "42463F56890B4DE57C93AFF61468CC38")
    @DSVerified
    
private static boolean isBase64(byte octect) {
        if (octect == PAD) {
            return true;
        } else if (base64Alphabet[octect] == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Tests a given byte array to see if it contains
     * only valid characters within the Base64 alphabet.
     *
     * @param arrayOctect byte array to test
     * @return true if all bytes are valid characters in the Base64
     *         alphabet or if the byte array is empty; false, otherwise
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.945 -0500", hash_original_method = "9B3201AE5D07B0FE82D467B91B0DEBA5", hash_generated_method = "879B54130E22C02EF54785AE920934C3")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
public static boolean isArrayByteBase64(byte[] arrayOctect) {

        arrayOctect = discardWhitespace(arrayOctect);

        int length = arrayOctect.length;
        if (length == 0) {
            // shouldn't a 0 length array be valid base64 data?
            // return false;
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!isBase64(arrayOctect[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encodes binary data using the base64 algorithm but
     * does not chunk the output.
     *
     * @param binaryData binary data to encode
     * @return Base64 characters
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.947 -0500", hash_original_method = "4494B29E35A945C6922BB1D438D582EC", hash_generated_method = "8B6A51FCB99EE75C98201BBFFB4CEC76")
    
public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    /**
     * Encodes binary data using the base64 algorithm and chunks
     * the encoded output into 76 character blocks
     *
     * @param binaryData binary data to encode
     * @return Base64 characters chunked in 76 character blocks
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.949 -0500", hash_original_method = "68A658D6A5BAAA4F48B195A7B0E88D46", hash_generated_method = "5F228E07FEAE93D40C07AA6B1EDC541E")
    
public static byte[] encodeBase64Chunked(byte[] binaryData) {
        return encodeBase64(binaryData, true);
    }

    /**
     * Encodes binary data using the base64 algorithm, optionally
     * chunking the output into 76 character blocks.
     *
     * @param binaryData Array containing binary data to encode.
     * @param isChunked if isChunked is true this encoder will chunk
     *                  the base64 output into 76 character blocks
     * @return Base64-encoded data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.959 -0500", hash_original_method = "574B1997394313A9CBB50913816A2452", hash_generated_method = "F7A59FE591A255BCF961218A4165471C")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        int lengthDataBits = binaryData.length * EIGHTBIT;
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        byte encodedData[] = null;
        int encodedDataLength = 0;
        int nbrChunks = 0;

        if (fewerThan24bits != 0) {
            //data not divisible by 24 bit
            encodedDataLength = (numberTriplets + 1) * 4;
        } else {
            // 16 or 8 bit
            encodedDataLength = numberTriplets * 4;
        }

        encodedData = new byte[encodedDataLength];
        encodedData.addTaint(isChunked);

        encodedData.addTaint(binaryData.getTaint());
        return encodedData;
    }

    /**
     * Decodes Base64 data into octects
     *
     * @param base64Data Byte array containing Base64 data
     * @return Array containing decoded data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.963 -0500", hash_original_method = "87C77E071BDF35E1C91859D8F7A3EF45", hash_generated_method = "24B5D2DD8D4071995BF1156D8E7C81EE")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
    public static byte[] decodeBase64(byte[] base64Data) {
        // RFC 2045 requires that we discard ALL non-Base64 characters
        base64Data = discardNonBase64(base64Data);

        // handle the edge case, so we don't have to worry about it later
        if (base64Data.length == 0) {
            return new byte[0];
        }

        int numberQuadruple = base64Data.length / FOURBYTE;
        
        byte[] decodedData = new byte[numberQuadruple];
        decodedData.addTaint(base64Data.getTaint());
        return decodedData;
    }
    
    /**
     * Discards any whitespace from a base-64 encoded block.
     *
     * @param data The base-64 encoded data to discard the whitespace
     * from.
     * @return The data, less whitespace (see RFC 2045).
     */
    @DSComment("Package priviledge")
    @DSSafe(DSCat.UTIL_FUNCTION)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.965 -0500", hash_original_method = "9BCC1A7457934BC771EBD3626A0DE18B", hash_generated_method = "3CBB1FFB63A50C335D002AC2738F9D2B")
    
static byte[] discardWhitespace(byte[] data) {
        byte groomedData[] = new byte[data.length];
        int bytesCopied = 0;
        
        for (int i = 0; i < data.length; i++) {
            switch (data[i]) {
            case (byte) ' ' :
            case (byte) '\n' :
            case (byte) '\r' :
            case (byte) '\t' :
                    break;
            default:
                    groomedData[bytesCopied++] = data[i];
            }
        }

        byte packedData[] = new byte[bytesCopied];

        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);

        return packedData;
    }

    /**
     * Discards any characters outside of the base64 alphabet, per
     * the requirements on page 25 of RFC 2045 - "Any characters
     * outside of the base64 alphabet are to be ignored in base64
     * encoded data."
     *
     * @param data The base-64 encoded data to groom
     * @return The data, less non-base64 characters (see RFC 2045).
     */
    @DSComment("Package priviledge")
    @DSSafe(DSCat.UTIL_FUNCTION)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.968 -0500", hash_original_method = "94A8BA1BB14E20FE8AAE958A036048A6", hash_generated_method = "03DE6BA56337AAD75057F043E8AE104E")
    
static byte[] discardNonBase64(byte[] data) {
        byte groomedData[] = new byte[data.length];
        int bytesCopied = 0;

        for (int i = 0; i < data.length; i++) {
            if (isBase64(data[i])) {
                groomedData[bytesCopied++] = data[i];
            }
        }

        byte packedData[] = new byte[bytesCopied];

        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);

        return packedData;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.914 -0500", hash_original_field = "3417D36C255C0A706906CC312C6DD207", hash_generated_field = "785A63382D1643559C7DD1D6491301B2")

    static final int CHUNK_SIZE = 76;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.916 -0500", hash_original_field = "EF1EF80F225DB0E3DBB64CFBECF87EA0", hash_generated_field = "DA2ED5E12D62BE4EE0706244E3772052")

    static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.918 -0500", hash_original_field = "3166F2C3484696622891054ECC9C314D", hash_generated_field = "536185EBD18970FB64E03E6E55F3CD95")

    static final int BASELENGTH = 255;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.921 -0500", hash_original_field = "FCEF32062C3359C0B11C2FD23EF33C67", hash_generated_field = "7C87871A009E7F7C30EDE4A2E1248EE4")

    static final int LOOKUPLENGTH = 64;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.923 -0500", hash_original_field = "C26BDE6871D9E0AC8EBF3B7ED730A19E", hash_generated_field = "0614664A7DBCACC8E8866D5A2B16925A")

    static final int EIGHTBIT = 8;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.925 -0500", hash_original_field = "C8F805AB5701352C5DC504F79E19F64B", hash_generated_field = "49E6A1B0C093832AEB47951289FC205F")

    static final int SIXTEENBIT = 16;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.928 -0500", hash_original_field = "2F09F83DB29CD46D2021B5F990FF7F04", hash_generated_field = "3EEFAF834B22ACD4CF3C2951310D9CF3")

    static final int TWENTYFOURBITGROUP = 24;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.930 -0500", hash_original_field = "3C45E4CFEEF26E0F0B27D88F356D5311", hash_generated_field = "01F8FEEA5A073F54FCDC8A7EF0A80BE7")

    static final int FOURBYTE = 4;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.932 -0500", hash_original_field = "7F95232D00582C0AE1B15B064FCBEBA3", hash_generated_field = "72625668F2920D54B73E40ED89C74952")

    static final int SIGN = -128;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.934 -0500", hash_original_field = "1EA38367D236402519BFC161ADFB09AE", hash_generated_field = "7DDF70FA82DA2839AC5F2B0F41202425")

    static final byte PAD = (byte) '=';
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.937 -0500", hash_original_field = "A620F336585F07DACADC3B2EAEEA8CA4", hash_generated_field = "CEC8D3A40438DA9D3BA0DE1C5BBCB916")

    // lookup for base64 chars
    private static byte[] base64Alphabet = new byte[BASELENGTH];
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.940 -0500", hash_original_field = "3DD9B58A8F71D585F98AD00789EAA8EC", hash_generated_field = "5F6F4EBEDC3FFA3D977AF8A987ABD629")

    private static byte[] lookUpBase64Alphabet = new byte[LOOKUPLENGTH];

    @DSSafe(DSCat.UTIL_FUNCTION)
    public static byte[] encode(byte[] b, int offset, int len) {
        // TODO Auto-generated method stub
        byte[] ret = new byte[len];
        ret.addTaint(b.getTaint());
        ret.addTaint(offset);
        ret.addTaint(len);
        return ret;
    }
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:26.644 -0400", hash_original_method = "2DB9D16143059D09A54A3BB31C55E28D", hash_generated_method = "2DB9D16143059D09A54A3BB31C55E28D")
    public Base64 ()
    {
        //Synthesized constructor
    }
    
    @DSSafe(DSCat.SAFE_OTHERS)
    public Base64(int lineLength, byte[] separator) {
        addTaint(lineLength);
        addTaint(separator.getTaint());
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    public boolean hasData() {
        return getTaintBoolean();
    }

    /**
     * Decodes an Object using the base64 algorithm.  This method
     * is provided in order to satisfy the requirements of the
     * Decoder interface, and will throw a DecoderException if the
     * supplied object is not of type byte[].
     *
     * @param pObject Object to decode
     * @return An object (of type byte[]) containing the 
     *         binary data which corresponds to the byte[] supplied.
     * @throws DecoderException if the parameter supplied is not
     *                          of type byte[]
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.951 -0500", hash_original_method = "27928DA5608E3EA81ACF98D07D1CDAA8", hash_generated_method = "51462A14CCF991407A4464C8FB796E4C")
    @DSSafe(DSCat.UTIL_FUNCTION)
public Object decode(Object pObject) throws DecoderException {
        if (!(pObject instanceof byte[])) {
            throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
        }
        return decode((byte[]) pObject);
    }

    /**
     * Decodes a byte[] containing containing
     * characters in the Base64 alphabet.
     *
     * @param pArray A byte array containing Base64 character data
     * @return a byte array containing binary data
     */
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.953 -0500", hash_original_method = "E9A5741B84FE67E582817B8B79597420", hash_generated_method = "C17AE6177C5668A0F0E5EE932AEF930C")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
public byte[] decode(byte[] pArray) {
        return decodeBase64(pArray);
    }

    // Implementation of the Encoder Interface

    /**
     * Encodes an Object using the base64 algorithm.  This method
     * is provided in order to satisfy the requirements of the
     * Encoder interface, and will throw an EncoderException if the
     * supplied object is not of type byte[].
     *
     * @param pObject Object to encode
     * @return An object (of type byte[]) containing the 
     *         base64 encoded data which corresponds to the byte[] supplied.
     * @throws EncoderException if the parameter supplied is not
     *                          of type byte[]
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.970 -0500", hash_original_method = "D5F82E7B8136CE61250AF5358F628FEE", hash_generated_method = "7692D23DABDAD6D43E67AD377007F3FC")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
public Object encode(Object pObject) throws EncoderException {
        if (!(pObject instanceof byte[])) {
            throw new EncoderException(
                "Parameter supplied to Base64 encode is not a byte[]");
        }
        return encode((byte[]) pObject);
    }
    static {
        for (int i = 0; i < BASELENGTH; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }
        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;
        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) ('A' + i);
        }
        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('a' + j);
        }
        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('0' + j);
        }
        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';
    }

    /**
     * Encodes a byte[] containing binary data, into a byte[] containing
     * characters in the Base64 alphabet.
     *
     * @param pArray a byte array containing binary data
     * @return A byte array containing only Base64 character data
     */
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:52.972 -0500", hash_original_method = "D3EC39ECED0CD0D362D80F2CDBB3BB21", hash_generated_method = "067C5861BBE1F6A7C3E18A7150299C2B")
    @DSVerified
    @DSSafe(DSCat.UTIL_FUNCTION)
public byte[] encode(byte[] pArray) {
        return encodeBase64(pArray, false);
    }

    @DSSafe(DSCat.UTIL_FUNCTION)
    public void setInitialBuffer(byte[] b, int offset, int len) {
        // TODO Auto-generated method stub
        addTaint(b.getTaint());
        addTaint(offset);
        addTaint(len);
    }

    @DSSafe(DSCat.UTIL_FUNCTION)
    public int readResults(byte[] b, int offset, int len) {
        // TODO Auto-generated method stub
        b.addTaint(getTaint());
        b.addTaint(offset);
        b.addTaint(len);
        return b.getTaintInt();
    }

    @DSSafe(DSCat.UTIL_FUNCTION)
    public byte[] decode(byte[] b, int offset, int len) {
        // TODO Auto-generated method stub
        byte[] ret = new byte[len];
        ret.addTaint(b.getTaint());
        ret.addTaint(offset);
        ret.addTaint(len);
        return ret; 
    }
    
    @DSSafe(DSCat.UTIL_FUNCTION)
    public int avail() {
        return getTaintInt();
    }
    
}

