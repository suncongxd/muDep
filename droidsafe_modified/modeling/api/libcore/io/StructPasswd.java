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


package libcore.io;

// Droidsafe Imports
import droidsafe.annotations.*;
import droidsafe.helpers.*;

import droidsafe.runtime.*;

public final class StructPasswd {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.222 -0500", hash_original_field = "CFE17EA3D561F193F72BEDC0E8CA1BFF", hash_generated_field = "E07C1468FB550ED4AB19E2FD56BE95B9")

    public String pw_name;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.224 -0500", hash_original_field = "251EFFFF6D3B101BCDF6653A388F7022", hash_generated_field = "E2C54B2FF765C8E127CD9C05E35E9973")

    public int pw_uid;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.226 -0500", hash_original_field = "7B1C6975C700CDC9BD3ADD83BEBEBF53", hash_generated_field = "EE0B4D699F4E3D30AD7F1B4B74709978")

    public int pw_gid;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.228 -0500", hash_original_field = "88D42802A7EADAB13D035B2CEE7E4BF0", hash_generated_field = "9D1C72CD8FA5E02BC421F4F0C5A5FEBB")

    public String pw_dir;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.231 -0500", hash_original_field = "07E3231F6A301CB872B7C3D7062B3895", hash_generated_field = "FAAAF6C5111C71231817F976E3E59543")

    public String pw_shell;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:26.234 -0500", hash_original_method = "EB3B16ED172FAF9B32CFE20692469A46", hash_generated_method = "E4C2AFD347510DD423D4C419F226AB2E")
    
public StructPasswd(String pw_name, int pw_uid, int pw_gid, String pw_dir, String pw_shell) {
        this.pw_name = pw_name;
        this.pw_uid = pw_uid;
        this.pw_gid = pw_gid;
        this.pw_dir = pw_dir;
        this.pw_shell = pw_shell;
    }

    
}

