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
 * Copyright (C) 2011 The Android Open Source Project
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
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMLocator;
import org.w3c.dom.Node;

public final class DOMErrorImpl implements DOMError {
    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.882 -0400", hash_original_field = "9F933224B2AAA0DFFB143BC6D8928978", hash_generated_field = "966D54A811D8CFE5E01A60999E418189")

    private static final DOMLocator NULL_DOM_LOCATOR = new DOMLocator() {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.881 -0400", hash_original_method = "B226EF391D7A5788D2A6B61A29A598E0", hash_generated_method = "5DA5637A847F7AE9184CEAA4895A9079")
        public int getLineNumber() {
            int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2078389753 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_2078389753;
            
        }
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.881 -0400", hash_original_method = "8861CD3D81AD120598D5847E998DCDDD", hash_generated_method = "EBFE671DC655AF943474BB2E67D8CBE7")
        public int getColumnNumber() {
            int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_113900983 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_113900983;
            
        }
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.881 -0400", hash_original_method = "05B54428E2337843F9A27998024BB4FC", hash_generated_method = "3C44385CD6EE799E6690F7F8643E50DB")
        public int getByteOffset() {
            int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1300430371 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1300430371;
            
        }
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.882 -0400", hash_original_method = "971AF81D7B5A736C60DA276A39DEF629", hash_generated_method = "641E94AEEC7A047E600E18A787A2360D")
        public int getUtf16Offset() {
            int varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1651457179 = getTaintInt();
            return varFA7153F7ED1CB6C0FCF2FFB2FAC21748_1651457179;
            
        }
        
        @DSSource({DSSourceKind.NETWORK})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.882 -0400", hash_original_method = "BD213E5A838EDE8A4D1403172904A1AB", hash_generated_method = "5254BCC77657250B9A05F6256FC455A2")
        public Node getRelatedNode() {
            Node varB4EAC82CA7396A68D541C85D26508E83_2051227868 = null; 
            varB4EAC82CA7396A68D541C85D26508E83_2051227868 = null;
            varB4EAC82CA7396A68D541C85D26508E83_2051227868.addTaint(getTaint()); 
            return varB4EAC82CA7396A68D541C85D26508E83_2051227868;
            
        }
        
        @DSSource({DSSourceKind.NETWORK})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-06-28 14:15:35.882 -0400", hash_original_method = "08DC8B98EA7E10D6197A6D233921DA55", hash_generated_method = "CD4F23A9BDE71DF49DFCB648FC57E9F7")
        public String getUri() {
            String varB4EAC82CA7396A68D541C85D26508E83_1385380363 = null; 
            varB4EAC82CA7396A68D541C85D26508E83_1385380363 = null;
            varB4EAC82CA7396A68D541C85D26508E83_1385380363.addTaint(getTaint()); 
            return varB4EAC82CA7396A68D541C85D26508E83_1385380363;
            
        }
        
};
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.988 -0500", hash_original_field = "8D297224D60E4F2A4D69C1F1E4183FEA", hash_generated_field = "F6245D3BBEE7E5AD850161E037644184")

    private  short severity;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.990 -0500", hash_original_field = "1B03D0EF3E71EB350936336F82D5E0C5", hash_generated_field = "AB2C9E2EF2C4EBDBF6BF18A679B45B62")

    private  String type;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.992 -0500", hash_original_method = "D93952774FCD9E5EB0D2AAFF03FA31D6", hash_generated_method = "E68DAC8017C976EA07E07B2CF0803FC8")
    
public DOMErrorImpl(short severity, String type) {
        this.severity = severity;
        this.type = type;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.995 -0500", hash_original_method = "486D9FD618449ECBCAAA947A01B6396E", hash_generated_method = "4409A54EDA0D5EAD1A7F56DAE8D7CC0D")
    
public short getSeverity() {
        return severity;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:22.997 -0500", hash_original_method = "736EB773CAD78737D003DEF14371364B", hash_generated_method = "408F57AA41E2F6C2B398418935F53B99")
    
public String getMessage() {
        return type;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:23.000 -0500", hash_original_method = "0E0B07C7C3039087C9D268CAF8DACC19", hash_generated_method = "375DB45064AFC367CAD6F27C4CB89612")
    
public String getType() {
        return type;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:23.002 -0500", hash_original_method = "69DC7C3C45CF145B81BFA491DBEA3D67", hash_generated_method = "AC8FC4F97FEE75D77977DB72AB311E83")
    
public Object getRelatedException() {
        return null;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:23.004 -0500", hash_original_method = "2E051F1CC67E124CEB94F15E64801D89", hash_generated_method = "77DACDE0FAEBC7449E9F05D781B5201B")
    
public Object getRelatedData() {
        return null;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:23.006 -0500", hash_original_method = "5FDC1F3E63CCD7CD3042D9A19CD94FF4", hash_generated_method = "F4ED7AFB9391AD93A8637C83535B7335")
    
public DOMLocator getLocation() {
        return NULL_DOM_LOCATOR;
    }
}

