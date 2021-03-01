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


package org.apache.harmony.xnet.provider.jsse;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class SSLInputStream extends InputStream {
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:32.274 -0400", hash_original_method = "B3907DD288EBA16F858ADC5177372075", hash_generated_method = "B3907DD288EBA16F858ADC5177372075")
    public SSLInputStream ()
    {
        //Synthesized constructor
    }

    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.834 -0500", hash_original_method = "475CB5F8D51E2295C86383D238A46C23", hash_generated_method = "A14CBA2E40F9D346E57F87809CDFD7B6")
    
@Override
    public abstract int available() throws IOException;

    /**
     * Reads the following byte value. Note that in the case of
     * reaching of the end of the data this methods throws the
     * exception, not return -1. The type of exception depends
     * on implementation. It was done for simplifying and speeding
     * up of processing of such cases.
     * @see org.apache.harmony.xnet.provider.jsse.SSLStreamedInput#read()
     * @see org.apache.harmony.xnet.provider.jsse.SSLBufferedInput#read()
     * @see org.apache.harmony.xnet.provider.jsse.HandshakeIODataStream#read()
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSComment("Abstract Method")
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.836 -0500", hash_original_method = "F11529017A51516EAF2106297C9899F8", hash_generated_method = "7F0F5BB3DEF099052AAEB4B2BD53966C")
    
@Override
    public abstract int read() throws IOException;

    /**
     * Reads and returns uint8 value.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.838 -0500", hash_original_method = "4E61964AC41EB7FF0B969F3A0D2A867E", hash_generated_method = "9308816E13C91086AF4BCA475DE16226")
    
public int readUint8() throws IOException {
        return read() & 0x00FF;
    }

    /**
     * Reads and returns uint16 value.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.841 -0500", hash_original_method = "0E243896410AF2472245902D7F89ED82", hash_generated_method = "3E80CA2668D94CA566E71E1AE1A80246")
    
public int readUint16() throws IOException {
        return (read() << 8) | (read() & 0x00FF);
    }

    /**
     * Reads and returns uint24 value.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.843 -0500", hash_original_method = "B71B56A9E36B8A8CD4C9FE0969EFEC79", hash_generated_method = "2802FE296682C8E17AE7D17D953D39E5")
    
public int readUint24() throws IOException {
        return (read() << 16) | (read() << 8) | (read() & 0x00FF);
    }

    /**
     * Reads and returns uint32 value.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.846 -0500", hash_original_method = "18D59391AE7948DA8080AA965DB6CE79", hash_generated_method = "DD3878EF0EBDE1C5D44194A626548B24")
    
public long readUint32() throws IOException {
        return (read() << 24) | (read() << 16)
              | (read() << 8) | (read() & 0x00FF);
    }

    /**
     * Reads and returns uint64 value.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.848 -0500", hash_original_method = "85B7C4B5BFF5E58A56D479C7E3A9AABB", hash_generated_method = "E389CAEA9FD43B57DCB9368BC2AE8648")
    
public long readUint64() throws IOException {
        long hi = readUint32();
        long lo = readUint32();
        return (hi << 32) | lo;
    }

    /**
     * Returns the vector of opaque values of specified length;
     * @param length - the length of the vector to be read.
     * @return the read data
     * @throws IOException if read operation could not be finished.
     */
    @DSSpec(DSCat.IO)
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.850 -0500", hash_original_method = "22A07573F54A4BDDEECBBE8A8D080BB9", hash_generated_method = "4E7CF5D85F0893BC29378DE35D5500BC")
    
public byte[] read(int length) throws IOException {
        byte[] res = new byte[length];
        for (int i=0; i<length; i++) {
            res[i] = (byte) read();
        }
        return res;
    }

    @DSSpec(DSCat.IO)
    @DSSource({DSSourceKind.IO})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:06.853 -0500", hash_original_method = "50426CF7D6642F19D28119E1D947BED8", hash_generated_method = "6BC2CEF740D0BDFCA8A3015759885D03")
    
@Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read_b;
        int i = 0;
        do {
            if ((read_b = read()) == -1) {
                return (i == 0) ? -1 : i;
            }
            b[off+i] = (byte) read_b;
            i++;
        } while ((available() != 0) && (i<len));
        return i;
    }
    
}

