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
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

public final class ProcessingInstructionImpl extends LeafNodeImpl implements ProcessingInstruction {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.362 -0500", hash_original_field = "2951DDF7509EE06D6F75A4DFB645109B", hash_generated_field = "728A0F8A0D39D47E29A782F5DE5F964F")

    private String target;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.364 -0500", hash_original_field = "B399DB9D51E3D670436439FBE17773A0", hash_generated_field = "2B992449EA610E50B67A821419D7EE9E")

    private String data;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.366 -0500", hash_original_method = "87437044DD20821D3A88B8D33A6B0848", hash_generated_method = "EC65D0A2EF668B6D696478BC0B4AAD7F")
    
ProcessingInstructionImpl(DocumentImpl document, String target, String data) {
        super(document);
        this.target = target; // TODO: validate that target is well-formed
        this.data = data;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.368 -0500", hash_original_method = "CA0E91FD462850CF9A2412442DB68D19", hash_generated_method = "1B47FDB82CBDDF81DD3E02B974723BE6")
    
public String getData() {
        return data;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.371 -0500", hash_original_method = "44DCC451FA6F3CA18022A718FA0AE60D", hash_generated_method = "7C945CF086237D52018411452B0E2757")
    
@Override
    public String getNodeName() {
        return target;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.374 -0500", hash_original_method = "3B7DB8AF476DCFC46150583715993452", hash_generated_method = "79C64D9180062E1618C10884865008E5")
    
@Override
    public short getNodeType() {
        return Node.PROCESSING_INSTRUCTION_NODE;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.376 -0500", hash_original_method = "26741C8FC3CFA5C9E2871448A384408A", hash_generated_method = "55A3B990051B9A0017EAF6026083BE8F")
    
@Override
    public String getNodeValue() {
        return data;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.378 -0500", hash_original_method = "067DF5651A33C6E6A710DDDF2900842F", hash_generated_method = "EE99245634FBABC2EE661780F993FC49")
    
public String getTarget() {
        return target;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.380 -0500", hash_original_method = "985529340F1B642B4E22D59984257994", hash_generated_method = "BE68596F492041F849CE9EC9D7118802")
    
public void setData(String data) throws DOMException {
        this.data = data;
    }
    
}

