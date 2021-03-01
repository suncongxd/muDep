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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/io/AbstractSessionOutputBuffer.java $
 * $Revision: 652091 $
 * $Date: 2008-04-29 13:41:07 -0700 (Tue, 29 Apr 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.http.impl.io;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

public abstract class AbstractSessionOutputBuffer implements SessionOutputBuffer {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.241 -0500", hash_original_field = "FDC8CF5A8884568840B214A8BCF86783", hash_generated_field = "CA476214CA171474EF752CDA85E22F31")

    private static final byte[] CRLF = new byte[] {HTTP.CR, HTTP.LF};
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.244 -0500", hash_original_field = "2F7BF5AB23B3C8B81910B9AFFC0C9C4E", hash_generated_field = "3BDE2E6C07EF32014555ADD4B032FE25")
    
    private static final int MAX_CHUNK = 256;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.246 -0500", hash_original_field = "F8DA3B27259A7CA8E9C9C224A6A7290E", hash_generated_field = "2BD7176B9CB0EEA2FAA19D55872406A1")
    
    private OutputStream outstream;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.248 -0500", hash_original_field = "2D83C5E4D1BB2B49287194516D03B007", hash_generated_field = "C6CA0CD2A463E4556B9FE48D8551672D")

    private ByteArrayBuffer buffer;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.250 -0500", hash_original_field = "64A133D796EAE83898A5E51E6D434FEF", hash_generated_field = "76BAEB11C3552BFB23E0B4B99A533DB1")
        
    private String charset = HTTP.US_ASCII;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.253 -0500", hash_original_field = "1268E551E2D06835242E1DD85742F5B8", hash_generated_field = "9303ABFC1EF5CEEB446DE036D38A5721")

    private boolean ascii = true;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.255 -0500", hash_original_field = "80498DD6FD825DCA7A9A3D886278D44D", hash_generated_field = "D608E8E883C0ADFB9BB241D869189B8C")
    
    private HttpTransportMetricsImpl metrics;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:36.461 -0400", hash_original_method = "83EACBB816713C51C4560064650A6E91", hash_generated_method = "83EACBB816713C51C4560064650A6E91")
    public AbstractSessionOutputBuffer ()
    {
        //Synthesized constructor
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.257 -0500", hash_original_method = "D2A1D17455BD2E03E126CE24DA4B94C7", hash_generated_method = "A489957DB5225CA29D103BD21374FD89")
    
protected void init(final OutputStream outstream, int buffersize, final HttpParams params) {
        if (outstream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        }
        if (buffersize <= 0) {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        }
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.outstream = outstream;
        this.buffer = new ByteArrayBuffer(buffersize);
        this.charset = HttpProtocolParams.getHttpElementCharset(params); 
        this.ascii = this.charset.equalsIgnoreCase(HTTP.US_ASCII)
                     || this.charset.equalsIgnoreCase(HTTP.ASCII);
        this.metrics = new HttpTransportMetricsImpl();
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.260 -0500", hash_original_method = "882208160FD161C7E0064D0E60BE1443", hash_generated_method = "E26E9242DA2F983D5E5E74C15738DA30")
    
protected void flushBuffer() throws IOException {
        int len = this.buffer.length();
        if (len > 0) {
            this.outstream.write(this.buffer.buffer(), 0, len);
            this.buffer.clear();
            this.metrics.incrementBytesTransferred(len);
        }
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.262 -0500", hash_original_method = "82CAE825041564C42525435895C12BF6", hash_generated_method = "F06FB92C515C953105F99E2D5E657AD3")
    
public void flush() throws IOException {
        flushBuffer();
        this.outstream.flush();
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.265 -0500", hash_original_method = "859D231B5A717C0A7AB5BB92D8C812A2", hash_generated_method = "226E9B07F6F9B5AD687F76AF9CDA485C")
    
public void write(final byte[] b, int off, int len) throws IOException {
        if (b == null) {
            return;
        }
        // Do not want to buffer largish chunks
        // if the byte array is larger then MAX_CHUNK
        // write it directly to the output stream
        if (len > MAX_CHUNK || len > this.buffer.capacity()) {
            // flush the buffer
            flushBuffer();
            // write directly to the out stream
            this.outstream.write(b, off, len);
            this.metrics.incrementBytesTransferred(len);
        } else {
            // Do not let the buffer grow unnecessarily
            int freecapacity = this.buffer.capacity() - this.buffer.length();
            if (len > freecapacity) {
                // flush the buffer
                flushBuffer();
            }
            // buffer
            this.buffer.append(b, off, len);
        }
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.267 -0500", hash_original_method = "58EC072EF9ABE32C0299240E0A15E9B9", hash_generated_method = "C6F9736FB9314783CC3397CD7C88D738")
    
public void write(final byte[] b) throws IOException {
        if (b == null) {
            return;
        }
        write(b, 0, b.length);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.269 -0500", hash_original_method = "3A892F1772116DEABF593C96C9FE9CC0", hash_generated_method = "DB5C346B42B4C8FEBB99EFF4B15D72C0")
    
public void write(int b) throws IOException {
        if (this.buffer.isFull()) {
            flushBuffer();
        }
        this.buffer.append(b);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.271 -0500", hash_original_method = "028F93C5D719EA2685E11E6920D1D339", hash_generated_method = "D44EDD0415F75C6822725E34DAB51B5D")
    
public void writeLine(final String s) throws IOException {
        if (s == null) {
            return;
        }
        if (s.length() > 0) {
            write(s.getBytes(this.charset));
        }
        write(CRLF);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.273 -0500", hash_original_method = "58771B165B2EA74A4951928351773B83", hash_generated_method = "6697F379DC4539377917F4DE60D4FE65")
    
public void writeLine(final CharArrayBuffer s) throws IOException {
        if (s == null) {
            return;
        }
        if (this.ascii) {
            int off = 0;
            int remaining = s.length();
            while (remaining > 0) {
                int chunk = this.buffer.capacity() - this.buffer.length();
                chunk = Math.min(chunk, remaining);
                if (chunk > 0) {
                    this.buffer.append(s, off, chunk);
                }
                if (this.buffer.isFull()) {
                    flushBuffer();
                }
                off += chunk;
                remaining -= chunk;
            }
        } else {
            // This is VERY memory inefficient, BUT since non-ASCII charsets are 
            // NOT meant to be used anyway, there's no point optimizing it
            byte[] tmp = s.toString().getBytes(this.charset);
            write(tmp);
        }
        write(CRLF);
    }
    
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:42.277 -0500", hash_original_method = "E82E77AA369AC2B118042C614F644F5C", hash_generated_method = "0F09664C8D045EB8CE43D8B36FB12762")
    
public HttpTransportMetrics getMetrics() {
        return this.metrics;
    }
}

