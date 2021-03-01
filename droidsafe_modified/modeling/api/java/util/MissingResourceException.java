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


package java.util;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class MissingResourceException extends RuntimeException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:47.663 -0500", hash_original_field = "F9CDA1B5AA02E56E897904F77B01B427", hash_generated_field = "31A19D95C3D0097D3CB81CFACA273934")

    private static final long serialVersionUID = -4876345176062000401L;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:10.313 -0400", hash_original_field = "6F66E878C62DB60568A3487869695820", hash_generated_field = "CA6BE7A479C5F3E68F3687DBA3A4A48F")

    String className;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:10.313 -0400", hash_original_field = "3C6E0B8A9C15224A8228B9A98CA1531D", hash_generated_field = "FFE7EE17DFA37A68070A0F0495753B29")

    String key;

    /**
     * Constructs a new {@code MissingResourceException} with the stack trace,
     * message, the class name of the resource bundle and the name of the
     * missing resource filled in.
     *
     * @param detailMessage
     *           the detail message for the exception.
     * @param className
     *           the class name of the resource bundle.
     * @param resourceName
     *           the name of the missing resource.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:47.668 -0500", hash_original_method = "0B71E2B2311543F4039A5E1A5B0E2A4E", hash_generated_method = "32A1888B08676057F0BDB6652245D2C0")
    
public MissingResourceException(String detailMessage, String className,
            String resourceName) {
        super(detailMessage);
        this.className = className;
        key = resourceName;
    }

    /**
     * Returns the class name of the resource bundle from which a resource could
     * not be found, or in the case of a missing resource, the name of the
     * missing resource bundle.
     *
     * @return the class name of the resource bundle.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:47.671 -0500", hash_original_method = "8415940D3A1E5651F883E391EC68DA41", hash_generated_method = "887A92DB5095808C84C58B108DAB1C1A")
    
public String getClassName() {
        return className;
    }

    /**
     * Returns the name of the missing resource, or an empty string if the
     * resource bundle is missing.
     *
     * @return the name of the missing resource.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:47.673 -0500", hash_original_method = "C39C8C9D5BC81C2FE68967748762171F", hash_generated_method = "864E9B692EEA007ED2B72251C0EF6A1F")
    
public String getKey() {
        return key;
    }
}

