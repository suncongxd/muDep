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


package android.net;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class LocalSocketAddress {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.193 -0500", hash_original_field = "BF45F7481B8091DE3CBF80E94F7F940B", hash_generated_field = "531F96E2AEBFB44CD229EC4CB1F012B0")

    private  String name;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.195 -0500", hash_original_field = "97EEF6D093E8D4D72F6F6C736C241B6E", hash_generated_field = "8F55F62DFCC7A64A0BD865276D82A096")

    private  Namespace namespace;

    /**
     * Creates an instance with a given name.
     *
     * @param name non-null name
     * @param namespace namespace the name should be created in.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.198 -0500", hash_original_method = "ACE50F43918FAC0899CFC1784787037A", hash_generated_method = "8C0AFF252F65474A11A5C25366700AE9")
    
public LocalSocketAddress(String name, Namespace namespace) {
        this.name = name;
        this.namespace = namespace;
    }

    /**
     * Creates an instance with a given name in the {@link Namespace#ABSTRACT}
     * namespace
     *
     * @param name non-null name
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.200 -0500", hash_original_method = "66D42DD819FBC28DB7D788D131A117E8", hash_generated_method = "9B80B691C4A2A542D633248CFD36020D")
    
public LocalSocketAddress(String name) {
        this(name,Namespace.ABSTRACT);
    }

    /**
     * Retrieves the string name of this address
     * @return string name
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.202 -0500", hash_original_method = "7070E6AABEDCBA653834DDC8CF79A47C", hash_generated_method = "50860709F8CC9FE19B604881E59FBA54")
    
public String getName()
    {
        return name;
    }

    /**
     * Returns the namespace used by this address.
     *
     * @return non-null a namespace
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.204 -0500", hash_original_method = "F383712D804FA1C6C6A9CA72960DBCA2", hash_generated_method = "81D3BAF7554AD06232886A6E215FEBDC")
    
public Namespace getNamespace() {
        return namespace;
    }
    
    public enum Namespace {
        ABSTRACT(0),
        RESERVED(1),
        FILESYSTEM(2);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.184 -0500", hash_original_field = "53E65FEF28E32C7EDCD1B07B4F5A0DE3", hash_generated_field = "D0FAB3735D60E909B66356CA0D9609D4")

        private int id;
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.187 -0500", hash_original_method = "5B0BC8DECCEEA5A4B859D0AECE510289", hash_generated_method = "5B0BC8DECCEEA5A4B859D0AECE510289")
            
Namespace (int id) {
            this.id = id;
        }

        /**
         * @return int constant shared with native code
         */
        /*package*/ @DSComment("Package priviledge")
        @DSBan(DSCat.DEFAULT_MODIFIER)
        @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.189 -0500", hash_original_method = "9E2F8CA3AB278410BC9A2501C7D5731F", hash_generated_method = "9E2F8CA3AB278410BC9A2501C7D5731F")
            
int getId() {
            return id;
        }
    }
    
}

