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


package libcore.net.http;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

final class RetryableOutputStream extends AbstractHttpOutputStream {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.085 -0500", hash_original_field = "136FDC88CA742E83C109AD31983DA2BF", hash_generated_field = "D8A77E2E2DC8CA16CE4A344FA1118F72")

    private  int limit;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.087 -0500", hash_original_field = "5B891CF437E7C28CDACFA0315335EFAA", hash_generated_field = "311998AF11DA0C9AD3FE931CF69A5C33")

    private  ByteArrayOutputStream content;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.090 -0500", hash_original_method = "A832DB079A79DDBD6BA954E86DE3CFE0", hash_generated_method = "C160383339CD23C2EB800543D7FEB927")
    
public RetryableOutputStream(int limit) {
        this.limit = limit;
        this.content = new ByteArrayOutputStream(limit);
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.093 -0500", hash_original_method = "ACF7E60D1B2648BBC92003365E201CCA", hash_generated_method = "1F009154985A291FDCB976FF76C38E99")
    
public RetryableOutputStream() {
        this.limit = -1;
        this.content = new ByteArrayOutputStream();
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.095 -0500", hash_original_method = "01295B07B18E8DACBE5B67B8ED73AB45", hash_generated_method = "CAA57D29E89B67611D095D7DF9C3D773")
    
@Override public synchronized void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        if (content.size() < limit) {
            throw new IOException("content-length promised "
                    + limit + " bytes, but received " + content.size());
        }
    }

    @DSSpec(DSCat.IO)
    @DSSink({DSSinkKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.098 -0500", hash_original_method = "E1F3BEFD89D2B57927FA9AF72B0F2441", hash_generated_method = "1CB3ED1636ECEADDD866DBB78357D3E2")
    
@Override public synchronized void write(byte[] buffer, int offset, int count)
            throws IOException {
        checkNotClosed();
        Arrays.checkOffsetAndCount(buffer.length, offset, count);
        if (limit != -1 && content.size() > limit - count) {
            throw new IOException("exceeded content-length limit of " + limit + " bytes");
        }
        content.write(buffer, offset, count);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.101 -0500", hash_original_method = "D6DA405C75C9B4A91F36F560BEF2D916", hash_generated_method = "FE91EA0708A0A997565867A1304F2EBA")
    
public synchronized int contentLength() throws IOException {
        close();
        return content.size();
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:37.103 -0500", hash_original_method = "13EC0747B327446249F03815D15BF368", hash_generated_method = "E60703C4EBDB22BBCBC5BC9258CE5D1D")
    
public void writeToSocket(OutputStream socketOut) throws IOException  {
        content.writeTo(socketOut);
    }
    
}

