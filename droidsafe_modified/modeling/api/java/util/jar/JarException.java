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
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.util.jar;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.util.zip.ZipException;

/**
 * This runtime exception is thrown when a problem occurs while reading a JAR
 * file.
 */
public class JarException extends ZipException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:57.099 -0400", hash_original_field = "A7210338BC92ECEBCB6D8CCE92C63311", hash_generated_field = "A48860069F1F718FA86C378985B88BDE")


    private static final long serialVersionUID = 7159778400963954473L;

    /**
     * Constructs a new {@code JarException} instance.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:57.099 -0400", hash_original_method = "DCFA58EF40362CB1E945FEC9479F1EEC", hash_generated_method = "848DFF85BD14A211D7BCDA4A2DA7ECDD")
    
public JarException() {
    }

    /**
     * Constructs a new {@code JarException} instance with the specified
     * message.
     *
     * @param detailMessage
     *            the detail message for the exception.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:57.100 -0400", hash_original_method = "FB6337731B58C1ABF03C2302BC0C531B", hash_generated_method = "4270A35B5400AF33F7A2EFD131597F74")
    
public JarException(String detailMessage) {
        super(detailMessage);
    }
}
