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
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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


package org.apache.commons.io.input;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Proxy stream that closes and discards the underlying stream as soon as the
 * end of input has been reached or when the stream is explicitly closed.
 * Not even a reference to the underlying stream is kept after it has been
 * closed, so any allocated in-memory buffers can be freed even if the
 * client application still keeps a reference to the proxy stream.
 * <p>
 * This class is typically used to release any resources related to an open
 * stream as soon as possible even if the client application (by not explicitly
 * closing the stream when no longer needed) or the underlying stream (by not
 * releasing resources once the last byte has been read) do not do that.
 *
 * @version $Id: AutoCloseInputStream.java 1304052 2012-03-22 20:55:29Z ggregory $
 * @since 1.4
 */
public class AutoCloseInputStream extends ProxyInputStream {

    /**
     * Creates an automatically closing proxy for the given input stream.
     *
     * @param in underlying input stream
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.949 -0400", hash_original_method = "C01D66AAAB14EA4E4DF6A9979EF6E5B0", hash_generated_method = "50350992DEB69163C1BBFA74E423D508")
    
public AutoCloseInputStream(InputStream in) {
        super(in);
    }

    /**
     * Closes the underlying input stream and replaces the reference to it
     * with a {@link ClosedInputStream} instance.
     * <p>
     * This method is automatically called by the read methods when the end
     * of input has been reached.
     * <p>
     * Note that it is safe to call this method any number of times. The original
     * underlying input stream is closed and discarded only once when this
     * method is first called.
     *
     * @throws IOException if the underlying input stream can not be closed
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.950 -0400", hash_original_method = "E9909FB3F008581898CAEA5C0FAA4CC7", hash_generated_method = "AD27A0E1490B7AAE63FA12653B6C2C3E")
    
@Override
    public void close() throws IOException {
        in.close();
        in = new ClosedInputStream();
    }

    /**
     * Automatically closes the stream if the end of stream was reached.
     *
     * @param n number of bytes read, or -1 if no more bytes are available
     * @throws IOException if the stream could not be closed
     * @since 2.0
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.950 -0400", hash_original_method = "1A943425CA4F660D14F7D119D0C80385", hash_generated_method = "C892A85E8A4B335D66BDBCBB39D56DF6")
    
@Override
    protected void afterRead(int n) throws IOException {
        if (n == -1) {
            close();
        }
    }

    /**
     * Ensures that the stream is closed before it gets garbage-collected.
     * As mentioned in {@link #close()}, this is a no-op if the stream has
     * already been closed.
     * @throws Throwable if an error occurs
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.951 -0400", hash_original_method = "2E21A04735F7B7DFBA8D51C21EAA3D5C", hash_generated_method = "478223465B2967B81B6B25A7047A7AB5")
    
@Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

}
