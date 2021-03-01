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
 * Copyright (C) 2010 The Android Open Source Project
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





public class NetworkConfig {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.440 -0500", hash_original_field = "BF45F7481B8091DE3CBF80E94F7F940B", hash_generated_field = "D29EB809CD7E712070B86A449A5F2E82")

    public String name;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.442 -0500", hash_original_field = "961B4204667A4AE2FF8DD374E6728ADE", hash_generated_field = "6AC5CE4BE311ED1283E9BD812937901E")

    public int type;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.444 -0500", hash_original_field = "D4F3AEBD976514419B19C0290B5C43F5", hash_generated_field = "B76BEC06EF6E298953D8260875E82B36")

    public int radio;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.446 -0500", hash_original_field = "4AD9F2E37B35E313CD211FBAFDADA238", hash_generated_field = "748E38F248BB72B76836AB36AB4B68BF")

    public int priority;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.448 -0500", hash_original_field = "85A36E15B393E4A779791D3CC08EAF51", hash_generated_field = "3D480C8B39054F9E4BA1016E57A71468")

    public boolean dependencyMet;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.451 -0500", hash_original_field = "EA7609F1A70D1515FA54D0A2B26281DD", hash_generated_field = "3DE9E6C805B41A267911B0D9515BC99E")

    public int restoreTime;

    /**
     * input string from config.xml resource.  Uses the form:
     * [Connection name],[ConnectivityManager connection type],
     * [associated radio-type],[priority],[dependencyMet]
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.453 -0500", hash_original_method = "93F95FF083791513BC80A25A69B4AF8A", hash_generated_method = "A9C5AF9FFCCAC1EA0C2118B06CC55E47")
    
public NetworkConfig(String init) {
        String fragments[] = init.split(",");
        name = fragments[0].trim().toLowerCase();
        type = Integer.parseInt(fragments[1]);
        radio = Integer.parseInt(fragments[2]);
        priority = Integer.parseInt(fragments[3]);
        restoreTime = Integer.parseInt(fragments[4]);
        dependencyMet = Boolean.parseBoolean(fragments[5]);
    }

    /**
     * Indicates if this network is supposed to be default-routable
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:17.456 -0500", hash_original_method = "474B729266EEB37673AA204F699AE5C3", hash_generated_method = "8FF7E0C05148FA772D148D0C2C6164D0")
    
public boolean isDefault() {
        return (type == radio);
    }

    
}

