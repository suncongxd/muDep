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
 * Copyright (C) 2008 The Android Open Source Project
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


package com.android.internal.telephony;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class SmsAddress {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.418 -0500", hash_original_field = "153E90B6A360B28E5ED013710DF79449", hash_generated_field = "56A4F1D6B084373CE43CD27D5809A080")

    // and C.S0005-D table 2.7.1.3.2.4-2
    public static final int TON_UNKNOWN = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.420 -0500", hash_original_field = "AD25FC4464A12FC07A86A5DE9C00573B", hash_generated_field = "7C4DD7039CD935B4355F3220600CCA84")

    public static final int TON_INTERNATIONAL = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.423 -0500", hash_original_field = "1DBA0620B03A1E785D13CC0B0E68D693", hash_generated_field = "47E9E4937C9A711F1A1BC88F0FCCD413")

    public static final int TON_NATIONAL = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.425 -0500", hash_original_field = "BA1C6B81B16DFB6CE5FCED407B08FE6A", hash_generated_field = "14594A4D7ED15988204D1F95A3FD6427")

    public static final int TON_NETWORK = 3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.427 -0500", hash_original_field = "4CF42791E7B492FA28ACFAFD988D6469", hash_generated_field = "87F6B3B2B6CFA7E2EC850536FE8A80CD")

    public static final int TON_SUBSCRIBER = 4;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.430 -0500", hash_original_field = "EED0F25744B36B2CB1BE78C74D49155A", hash_generated_field = "3D86FAA129E3D7F7E09EF64145E92966")

    public static final int TON_ALPHANUMERIC = 5;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.432 -0500", hash_original_field = "28309F117F93B5D0E1D8F99EBC1641EC", hash_generated_field = "D3D6BEF2BB43AEB214BFC3146F4F95F4")

    public static final int TON_ABBREVIATED = 6;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.435 -0500", hash_original_field = "B7A40B0EBCA8C1E2D256C431365D258C", hash_generated_field = "5E46769E3138C30BA726827EC521CE4C")

    public int ton;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.436 -0500", hash_original_field = "815EECE17AF4BC840D4544A9ECB9EACD", hash_generated_field = "94F51CF62708E9C4165D74975525630C")

    public String address;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.439 -0500", hash_original_field = "94C70AED0AD9CAC6169C5E9A91DE4382", hash_generated_field = "F4D84AFDC233D2206E605DACECAD70BE")

    public byte[] origBytes;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:19.107 -0400", hash_original_method = "B8327960B9CD15772A1562BB89169838", hash_generated_method = "B8327960B9CD15772A1562BB89169838")
    public SmsAddress ()
    {
        //Synthesized constructor
    }

    /**
     * Returns the address of the SMS message in String form or null if unavailable
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.441 -0500", hash_original_method = "EAD8B5BB0DBAF5034E5C9FE6C7B0909A", hash_generated_method = "16A98D98BF22E2226CA4E552EBC9B9DF")
    
public String getAddressString() {
        return address;
    }

    /**
     * Returns true if this is an alphanumeric address
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.443 -0500", hash_original_method = "364284D6387231DFE8BEB00860E244AE", hash_generated_method = "94FCF7D6584B29E11994CD05B47D594C")
    
public boolean isAlphanumeric() {
        return ton == TON_ALPHANUMERIC;
    }

    /**
     * Returns true if this is a network address
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.445 -0500", hash_original_method = "9A297062C2D2EDFA0EA27742D0F1F520", hash_generated_method = "4702E1F913E3FD9A825CCD513A01703B")
    
public boolean isNetworkSpecific() {
        return ton == TON_NETWORK;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.448 -0500", hash_original_method = "62AA39399884507545860A1B63B7D677", hash_generated_method = "2C67065E43BF65AA6DA40EFF68BB5E06")
    
public boolean couldBeEmailGateway() {
        // Some carriers seems to send email gateway messages in this form:
        // from: an UNKNOWN TON, 3 or 4 digits long, beginning with a 5
        // PID: 0x00, Data coding scheme 0x03
        // So we just attempt to treat any message from an address length <= 4
        // as an email gateway

        return address.length() <= 4;
    }
}

