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
 * $HeadURL:https://svn.apache.org/repos/asf/jakarta/httpcomponents/trunk/coyote-httpconnector/src/java/org/apache/http/tcconnector/UnsupportedHttpVersionException.java $
 * $Revision:379772 $
 * $Date:2006-02-22 14:52:29 +0100 (Wed, 22 Feb 2006) $
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


package org.apache.http;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import org.apache.http.ProtocolException;

/**
 * Indicates an unsupported version of the HTTP protocol.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision:379772 $
 */
public class UnsupportedHttpVersionException extends ProtocolException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:34.590 -0400", hash_original_field = "20D255F93BAB972BF8726DF8579954F4", hash_generated_field = "2B8E9AE9899844B796B14B7E1612D17E")


    private static final long serialVersionUID = -1348448090193107031L;

    
    /**
     * Creates an exception without a detail message.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:34.590 -0400", hash_original_method = "897EA381EE264C3094CE5319C9F99681", hash_generated_method = "06C758325B1ABEF170F91057AD03AC1D")
    
public UnsupportedHttpVersionException() {
        super();
    }

    /**
     * Creates an exception with the specified detail message.
     * 
     * @param message The exception detail message 
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 14:59:34.591 -0400", hash_original_method = "25C8EDE7956B3FAF53379C2102242C38", hash_generated_method = "F2AA305B8BDA4CEFE944759A6C288841")
    
public UnsupportedHttpVersionException(final String message) {
        super(message);
    }

}
