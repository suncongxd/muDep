/* Licensed to the Apache Software Foundation (ASF) under one or more
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


package java.util;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class IllegalFormatWidthException extends IllegalFormatException {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:37.240 -0500", hash_original_field = "BB235F7F1E3D7C609ED5F3B7A49E37D1", hash_generated_field = "53D64A015A46BFFFE5AD78E919F8231E")

    private static final long serialVersionUID = 16660902L;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:37.243 -0500", hash_original_field = "AEACAB535AADFDE31EE65802EF536C5F", hash_generated_field = "23C6668234F9AB5AD401877AE4904DE1")

    private  int w;

    /**
     * Constructs a new {@code IllegalFormatWidthException} with specified
     * width.
     *
     * @param w
     *           the width.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:37.246 -0500", hash_original_method = "3E51700FF2AA6FFF2196939892FEBA05", hash_generated_method = "A377C7A207FACB2198B1E0C4BA5D4AC2")
    
public IllegalFormatWidthException(int w) {
        this.w = w;
    }

    /**
     * Returns the width associated with the exception.
     *
     * @return the width.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:37.248 -0500", hash_original_method = "E748E6398AAC681AB6A0D4D76FADF497", hash_generated_method = "5B32195AB029CCD2947D5B7684AE4DC5")
    
public int getWidth() {
        return w;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:37.251 -0500", hash_original_method = "DC0A01255F859E7DE489CAE4D4B39EB6", hash_generated_method = "51CAA030EF1AD911E3450AE2654EE45E")
    
@Override
    public String getMessage() {
        return Integer.toString(w);
    }
}

