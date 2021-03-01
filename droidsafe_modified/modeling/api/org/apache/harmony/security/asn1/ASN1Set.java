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

/**
* @author Vladimir N. Molotkov, Stepan M. Mishura
* @version $Revision$
*/


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.asn1;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.io.IOException;

/**
 * This class represents ASN.1 Set type.
 *
 * @see <a href="http://asn1.elibel.tm.fr/en/standards/index.htm">ASN.1</a>
 */
public final class ASN1Set extends ASN1TypeCollection {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-29 15:18:51.157 -0500", hash_original_method = "0897B7DA4088FF7BFB3995B74D34DECF", hash_generated_method = "4B9374206B22243939C0773BD305B137")
    public ASN1Set(ASN1Type[] type) {
        super(TAG_SET, type);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-29 15:18:51.159 -0500", hash_original_method = "8F65725A4738CA13FD5D044E3BB4BEAF", hash_generated_method = "9AB1043E3B57392B71BA54924945449A")
    public Object decode(BerInputStream in) throws IOException {
        in.readSet(this);

        if(in.isVerify){
            return null;
        }
        return getDecodedObject(in);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-29 15:18:51.161 -0500", hash_original_method = "2D6E6AAE33497F44440DD13BE5D2CBE1", hash_generated_method = "47A821D18986F18042E3B4C77613E39E")
    public final void encodeContent(BerOutputStream out) {
        out.encodeSet(this);
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-29 15:18:51.163 -0500", hash_original_method = "62CA35C05045F2F590FB1A8FC47ABB46", hash_generated_method = "A212AB4DA23B5EFF40A5501960F496F5")
    public final void setEncodingContent(BerOutputStream out) {
        out.getSetLength(this);
    }
}
