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


package java.lang;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Process {
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:47.423 -0400", hash_original_method = "F1A8A517686D0631650544C6E6120854", hash_generated_method = "F1A8A517686D0631650544C6E6120854")
    public Process ()
    {
        //Synthesized constructor
    }

    /**
     * Terminates this process and closes any associated streams.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.950 -0500", hash_original_method = "73111F72F4AB0474EB2CFBD7E4AF4E1A", hash_generated_method = "FF0E335625027EFCFF9FC16BC675F34B")
    
public abstract void destroy();

    /**
     * Returns the exit value of the native process represented by this object.
     * It is available only when the native process has terminated.
     *
     * @return the exit value of this process.
     * @throws IllegalThreadStateException
     *             if this process has not terminated.
     */
    @DSSource({DSSourceKind.OS_PROCESS})
    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.953 -0500", hash_original_method = "1EAA7B24F2B50944782A0CE60B3B8E75", hash_generated_method = "647CE9E2E70D98F5A5572B4451C53331")
    
public abstract int exitValue();

    /**
     * Returns an input stream that is connected to the error stream
     * <em>(stderr)</em> of the native process represented by this object.
     *
     * @return the input stream to read from the error stream associated with
     *         the native process.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.955 -0500", hash_original_method = "FB318E2B3F5B4555B4080C28AE106FDF", hash_generated_method = "DFAA796C158DC4EA4430C868D9AB59F0")
    
public abstract InputStream getErrorStream();

    /**
     * Returns an input stream that is connected to the standard output stream
     * <em>(stdout)</em> of the native process represented by this object.
     *
     * @return the input stream to read from the output stream associated with
     *         the native process.
     */
    
    @DSSource({DSSourceKind.OS_PROCESS})
    @DSComment("Abstract Method")
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.958 -0500", hash_original_method = "422D86606304C3F8D9976A0E2284F13F", hash_generated_method = "556020BF6265A6C5614B1CFEE9E2FBA9")
    
public abstract InputStream getInputStream();

    /**
     * Returns an output stream that is connected to the standard input stream
     * <em>(stdin)</em> of the native process represented by this object.
     *
     * @return the output stream to write to the input stream associated with
     *         the native process.
     */
    @DSSource({DSSourceKind.OS_PROCESS})
    @DSComment("Abstract Method")
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.960 -0500", hash_original_method = "4331060E74BEB71DFC1779059CAED65C", hash_generated_method = "EB193755F22F39A086883299C85DF0E2")
    
public abstract OutputStream getOutputStream();

    /**
     * Causes the calling thread to wait for the native process associated with
     * this object to finish executing.
     *
     * @return the exit value of the native process being waited on.
     * @throws InterruptedException
     *             if the calling thread is interrupted.
     */
    @DSSource({DSSourceKind.OS_PROCESS})
    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:14.962 -0500", hash_original_method = "1FBBA10249C9A6533D34170DE8056745", hash_generated_method = "691F1C6425EB7CF64F1CD4B894D399D7")
    
public abstract int waitFor() throws InterruptedException;
    
}

