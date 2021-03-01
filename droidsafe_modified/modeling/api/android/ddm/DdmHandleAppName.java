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


package android.ddm;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.nio.ByteBuffer;

import org.apache.harmony.dalvik.ddmc.Chunk;
import org.apache.harmony.dalvik.ddmc.ChunkHandler;
import org.apache.harmony.dalvik.ddmc.DdmServer;

import android.util.Log;

public class DdmHandleAppName extends ChunkHandler {

    /**
     * Register for the messages we're interested in.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.166 -0500", hash_original_method = "789B5085E0E3F0644FA7F1CD116FCAD9", hash_generated_method = "9BEA173807ADFA497EA149EC2DE7C825")
    
public static void register() {}

    /**
     * Set the application name.  Called when we get named, which may be
     * before or after DDMS connects.  For the latter we need to send up
     * an APNM message.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.176 -0500", hash_original_method = "77DC2DB817712B783B461CB38FE2F87D", hash_generated_method = "B91A0671D34C16AAB83C358350A9136A")
    
public static void setAppName(String name) {
        if (name == null || name.length() == 0)
            return;

        mAppName = name;

        // if DDMS is already connected, send the app name up
        sendAPNM(name);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.178 -0500", hash_original_method = "1616F246970763497051049B272382DF", hash_generated_method = "2DCB4E49DA3205D78CB0EE5EC1FA2ADF")
    
public static String getAppName() {
        return mAppName;
    }

    /*
     * Send an APNM (APplication NaMe) chunk.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.180 -0500", hash_original_method = "C2C3236E5684F32FE2D4F7ADF24D2EBA", hash_generated_method = "666E62AAF6257E804BDD664EBAFAEF35")
    
private static void sendAPNM(String appName) {
        if (false)
            Log.v("ddm", "Sending app name");

        ByteBuffer out = ByteBuffer.allocate(4 + appName.length()*2);
        out.order(ChunkHandler.CHUNK_ORDER);
        out.putInt(appName.length());
        putString(out, appName);

        Chunk chunk = new Chunk(CHUNK_APNM, out);
        DdmServer.sendChunk(chunk);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.157 -0500", hash_original_field = "B3B238C6C020226FD7379F4860ED072B", hash_generated_field = "A00FA52EEEF4E9D0FDBABD362F30DDE4")

    public static final int CHUNK_APNM = type("APNM");
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.159 -0500", hash_original_field = "FACFB8C3EAB633D869097F7C5EDD785A", hash_generated_field = "13D874CFFECF1B13A69B63BC4FD01026")

    private volatile static String mAppName = "";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.162 -0500", hash_original_field = "1124B7354B7ADD7CACB5B26FCF7B663F", hash_generated_field = "CB6FB73A6BC364A591CA00981BD0CE53")

    private static DdmHandleAppName mInstance = new DdmHandleAppName();

    /* singleton, do not instantiate */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.164 -0500", hash_original_method = "D8F21F6A35B29C82CC84858F5D50C10E", hash_generated_method = "714E45DF5D6BD22E3F028526E485CEE1")
    
private DdmHandleAppName() {}

    /**
     * Called when the DDM server connects.  The handler is allowed to
     * send messages to the server.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.168 -0500", hash_original_method = "FF1AB110B94FCC8AEFA697D75FFC57DD", hash_generated_method = "B371B1A5EBF86980C6290FA035BAE684")
    
public void connected() {}

    /**
     * Called when the DDM server disconnects.  Can be used to disable
     * periodic transmissions or clean up saved state.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.170 -0500", hash_original_method = "7ECE87C8E0B6AC612BF4B05EB3BE89DA", hash_generated_method = "5061C51BA9AB55A45A330545ECB336D2")
    
public void disconnected() {}

    /**
     * Handle a chunk of data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:22.172 -0500", hash_original_method = "9322B8B253BE8A74DE6E5A2942EC5C84", hash_generated_method = "5328B5B0EE5413B4A356B5BF1F2BF7E6")
    
public Chunk handleChunk(Chunk request) {
        return null;
    }
}

