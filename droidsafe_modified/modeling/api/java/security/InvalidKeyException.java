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

public class InvalidKeyException extends KeyException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:57.074 -0500", hash_original_field = "7D02BB54068F6E8190B642ABA2FFE661", hash_generated_field = "AE7F6B4A6D2DED1A2EC0CFA8D091C8F7")

    private static final long serialVersionUID = 5698479920593359816L;

    /**
     * Constructs a new instance of {@code InvalidKeyException} with the given
     * message.
     *
     * @param msg
     *            the detail message for this exception.
     */
    @DSComment("no suspicious activity, just creating an object")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:57.077 -0500", hash_original_method = "BC12CDCCA39AEA87BF36441977671D95", hash_generated_method = "62FC9239100455C6FBFDC2B11C764A5C")
    
public InvalidKeyException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new instance of {@code InvalidKeyException}.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:57.080 -0500", hash_original_method = "4C9DBAC0C74B6F235E7CED5E494EF3EA", hash_generated_method = "3B49F4FEA53B220CB72AB94C4102A3C9")
    
public InvalidKeyException() {
    }

    /**
     * Constructs a new instance of {@code InvalidKeyException} with the given
     * message and the cause.
     *
     * @param message
     *            the detail message for this exception.
     * @param cause
     *            the exception which is the cause for this exception.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:57.084 -0500", hash_original_method = "F48B209D0258F085C8D5998E4F2F8DB1", hash_generated_method = "E46660CE8A569591011A91D6CEAA0540")
    
public InvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new instance of {@code InvalidKeyException} with the cause.
     *
     * @param cause
     *            the exception which is the cause for this exception.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:57.086 -0500", hash_original_method = "F8B65E52926150DDFBD4B64691704A27", hash_generated_method = "45D40710659E41B7B0B9E2C51AC66853")
    
public InvalidKeyException(Throwable cause) {
        super(cause);
    }
}

