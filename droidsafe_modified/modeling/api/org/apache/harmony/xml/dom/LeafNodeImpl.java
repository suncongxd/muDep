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
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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


package org.apache.harmony.xml.dom;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import org.w3c.dom.Node;

public abstract class LeafNodeImpl extends NodeImpl {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.509 -0500", hash_original_field = "5449DEEA97DBC636B0C94C7706497CE1", hash_generated_field = "5449DEEA97DBC636B0C94C7706497CE1")

    InnerNodeImpl parent;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.512 -0500", hash_original_field = "8BD524D6245D998B3BBC44EB9313082E", hash_generated_field = "8BD524D6245D998B3BBC44EB9313082E")

    int index;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.515 -0500", hash_original_method = "C37CADF0F64FF24C3BED74AC237BFE15", hash_generated_method = "C37CADF0F64FF24C3BED74AC237BFE15")
    
LeafNodeImpl(DocumentImpl document) {
        super(document);
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.517 -0500", hash_original_method = "DC63B22231FC6D38D6E19E3A9D1523CF", hash_generated_method = "FC6A26CA5BCFEAD3D28518BEEF4E3F1F")
    
public Node getNextSibling() {
        if (parent == null || index + 1 >= parent.children.size()) {
            return null;
        }

        return parent.children.get(index + 1);
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.519 -0500", hash_original_method = "B8A0C756618E449548951ECCC8542C8B", hash_generated_method = "80CA5A8B8CC11D591E8F27794BDD67E0")
    
public Node getParentNode() {
        return parent;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.522 -0500", hash_original_method = "8D9655D90408A316224292865014DAE6", hash_generated_method = "D5AA48845384734749CBC849D4A69B98")
    
public Node getPreviousSibling() {
        if (parent == null || index == 0) {
            return null;
        }

        return parent.children.get(index - 1);
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:21.524 -0500", hash_original_method = "4FE9AF789BEC71BD17F719913A9768F5", hash_generated_method = "4FE9AF789BEC71BD17F719913A9768F5")
    
boolean isParentOf(Node node) {
        return false;
    }
    
}

