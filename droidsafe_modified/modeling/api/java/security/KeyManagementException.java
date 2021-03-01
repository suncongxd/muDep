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


package java.security;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class KeyManagementException extends KeyException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:01.124 -0500", hash_original_field = "5DA0FDFCFAEF68F94D1E0975944FA356", hash_generated_field = "89D5F685E2DD3F83FC2D5B9AF7510AA0")

    private static final long serialVersionUID = 947674216157062695L;

    /**
     * Constructs a new instance of {@code KeyManagementException} with the
     * given message.
     *
     * @param msg
     *            the detail message for this exception.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:01.127 -0500", hash_original_method = "C1C9437036F95280A4529C1CBB03A96B", hash_generated_method = "5C411873DF962B2B8665B98F5C37F85D")
    
public KeyManagementException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new instance of {@code KeyManagementException}.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:01.130 -0500", hash_original_method = "DE152C48BD78964F95EBFDDBEC69C54C", hash_generated_method = "D419DAACF538AAFDCEEA52FBEAAE46D0")
    
public KeyManagementException() {
    }

    /**
     * Constructs a new instance of {@code KeyManagementException} with the
     * given message and the cause.
     *
     * @param message
     *            the detail message for this exception.
     * @param cause
     *            the exception which is the cause for this exception.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:01.133 -0500", hash_original_method = "81AAB74590EA3C5396EE9D20F7BBE3DD", hash_generated_method = "8E6E8085F5DFB6B67B4BCD2515126F68")
    
public KeyManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new instance of {@code KeyManagementException} with the
     * cause.
     *
     * @param cause
     *            the exception which is the cause for this exception.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:01.136 -0500", hash_original_method = "E6EDC076BA6A403E961006D69F90ADCE", hash_generated_method = "65AE01DE9E61249AAA650A61466C6D12")
    
public KeyManagementException(Throwable cause) {
        super(cause);
    }
}

