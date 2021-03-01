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


package org.apache.commons.io.output;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Closed output stream. This stream throws an exception on all attempts to
 * write something to the stream.
 * <p>
 * Typically uses of this class include testing for corner cases in methods
 * that accept an output stream and acting as a sentinel value instead of
 * a {@code null} output stream.
 *
 * @version $Id: ClosedOutputStream.java 1307459 2012-03-30 15:11:44Z ggregory $
 * @since 1.4
 */
public class ClosedOutputStream extends OutputStream {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.281 -0400", hash_original_field = "FDDB99F3C5B5017156E01515BF094B47", hash_generated_field = "8BD9C88FE3DAF296E746B8872518A91D")

    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = new ClosedOutputStream();

    /**
     * Throws an {@link IOException} to indicate that the stream is closed.
     *
     * @param b ignored
     * @throws IOException always thrown
     */
    @DSSink({DSSinkKind.IO})
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:52.282 -0400", hash_original_method = "E42F4A3BC6073DDBB791DEFDBE00CF19", hash_generated_method = "EDCF2CDCD8BA67F98AEC7FCBDEA8704E")
    
@Override
    public void write(int b) throws IOException {
        throw new IOException("write(" + b + ") failed: stream is closed");
    }

}
