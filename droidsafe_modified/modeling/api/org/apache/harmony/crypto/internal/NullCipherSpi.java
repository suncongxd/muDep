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
* @author Boris V. Kuznetsov
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.crypto.internal;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

public class NullCipherSpi extends CipherSpi {
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:26.842 -0400", hash_original_method = "FDE7F108401C679AA53E658D57386CA0", hash_generated_method = "FDE7F108401C679AA53E658D57386CA0")
    public NullCipherSpi ()
    {
        //Synthesized constructor
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.224 -0500", hash_original_method = "706F671562845F3E8688B6825B7BB7B5", hash_generated_method = "8A59D12D04958FD14942567757AFA471")
    
@Override
    public void engineSetMode(String arg0) throws NoSuchAlgorithmException {
        // Do nothing
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.227 -0500", hash_original_method = "F467B7E30ACE18A6916FD5129C6E9D6D", hash_generated_method = "74304B597BF2AC777BEC6296D87DD7C6")
    
@Override
    public void engineSetPadding(String arg0) throws NoSuchPaddingException {
        // Do nothing
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.229 -0500", hash_original_method = "AA3F68511E1542DF3672139FD15FAFBD", hash_generated_method = "D054151FC3568D4E1A0158AE65A0B0B8")
    
@Override
    public int engineGetBlockSize() {
        return 1;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.231 -0500", hash_original_method = "F5B40F368FE57EF79892E9499088FA98", hash_generated_method = "C738CD23E37BB7BA3D7AAAB52E5AA7E2")
    
@Override
    public int engineGetOutputSize(int inputLen) {
        return inputLen;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.233 -0500", hash_original_method = "D58BACA19EEF36A04C3196887019A068", hash_generated_method = "BDB824DF00BD122FB17876D14A988FB5")
    
@Override
    public byte[] engineGetIV() {
        return new byte[8]; // compatible with RI
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.237 -0500", hash_original_method = "5ACBCE845ECFAD3699BFF222B46DB669", hash_generated_method = "4F7E4B30E140B1ABC62F50839FC28BBC")
    
@Override
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.239 -0500", hash_original_method = "B46A0FD4E768F51B64954CE33560D103", hash_generated_method = "B9F33B0D1B802BD47B178B9C54C07022")
    
@Override
    public void engineInit(int opmode, Key key, SecureRandom random)
            throws InvalidKeyException {
        // Do nothing
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.241 -0500", hash_original_method = "17F5EBD605D037DD2AE1BC60DE6A1892", hash_generated_method = "A5977EF7DA70444F029FC1E0BD189E36")
    
@Override
    public void engineInit(int opmode, Key key, AlgorithmParameterSpec params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.244 -0500", hash_original_method = "34E3729604DBD7C3DE21E55F909979C4", hash_generated_method = "0F434D35DD7E6941AF3AF57751FBEB5F")
    
@Override
    public void engineInit(int opmode, Key key, AlgorithmParameters params,
            SecureRandom random) throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        // Do nothing
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.246 -0500", hash_original_method = "09310CB0118160B29BD7A01C1B70DD45", hash_generated_method = "876B5C7D236A242654AFA7ACF557477B")
    
@Override
    public byte[] engineUpdate(byte[] input, int inputOffset, int inputLen) {
        if (input == null) {
            return null;
        }
        byte[] result = new byte[inputLen];
        System.arraycopy(input, inputOffset, result, 0, inputLen);
        return result;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.249 -0500", hash_original_method = "60A1BA3728F191B40038A2CE01896EE6", hash_generated_method = "49B4C087B502F6A39237A2CA15BA7A2C")
    
@Override
    public int engineUpdate(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException {
        if (input == null) {
            return 0;
        }
        System.arraycopy(input, inputOffset, output, outputOffset, inputLen);
        return inputLen;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.252 -0500", hash_original_method = "52F18BDADF6C5DE31B16AD29ECF185DD", hash_generated_method = "18A7508DDC7A7B6B35593399C308F05B")
    
@Override
    public int engineUpdate(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        if (input == null || output == null) {
            throw new NullPointerException();
        }
        int result = input.limit() - input.position();
        try {
            output.put(input);
        } catch (java.nio.BufferOverflowException e) {
            throw new ShortBufferException("output buffer too small");
        }
        return result;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.254 -0500", hash_original_method = "BACBD39F75A04196004D2E06D99366CD", hash_generated_method = "1BDA81D1CD594544D674FD738AB71FF9")
    
@Override
    public byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen)
            throws IllegalBlockSizeException, BadPaddingException {
        if (input == null) {
            return null;
        }
        return engineUpdate(input, inputOffset, inputLen);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.257 -0500", hash_original_method = "C78AB3EC778278E6CB8FDDB4DDEF61A0", hash_generated_method = "CCC292E255140BBCA0A08F2D42C85F6A")
    
@Override
    public int engineDoFinal(byte[] input, int inputOffset, int inputLen,
            byte[] output, int outputOffset) throws ShortBufferException,
            IllegalBlockSizeException, BadPaddingException {
        int result = engineUpdate(input, inputOffset, inputLen, output,
                outputOffset);
        return result;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.259 -0500", hash_original_method = "245FFB9EDA02A1B2DEFFE10111DCBE72", hash_generated_method = "72A8A89BC1DCBB1B0EBD763FC486C460")
    
@Override
    public int engineDoFinal(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegalBlockSizeException,
            BadPaddingException {
        return engineUpdate(input, output);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.262 -0500", hash_original_method = "39FE6C15BFF89C7EBC497DBB2542410E", hash_generated_method = "008ED380AABB6A09A57DAF2D0402DDAA")
    
@Override
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        throw new UnsupportedOperationException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.264 -0500", hash_original_method = "6B4157CFE5E357119D9C5A035C31A1F5", hash_generated_method = "17188DD687A18A9CF7C373E5750EE1C8")
    
@Override
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm,
            int wrappedKeyType) throws InvalidKeyException,
            NoSuchAlgorithmException {
        throw new UnsupportedOperationException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.267 -0500", hash_original_method = "3103A3DFE966B970FCF19F267AD5457A", hash_generated_method = "3941EA3D9F39EFE93A9BC56AA2061C3E")
    
@Override
    public int engineGetKeySize(Key key) throws InvalidKeyException {
        throw new UnsupportedOperationException();
    }
    
}

