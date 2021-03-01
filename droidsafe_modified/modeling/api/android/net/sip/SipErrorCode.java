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


package android.net.sip;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public class SipErrorCode {

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.960 -0500", hash_original_method = "4F9BFA28170DB7CB0E6F645850B92FEE", hash_generated_method = "58EB78A50ECE703708D7F8B99BE4EC5E")
    
public static String toString(int errorCode) {
        switch (errorCode) {
            case NO_ERROR:
                return "NO_ERROR";
            case SOCKET_ERROR:
                return "SOCKET_ERROR";
            case SERVER_ERROR:
                return "SERVER_ERROR";
            case TRANSACTION_TERMINTED:
                return "TRANSACTION_TERMINTED";
            case CLIENT_ERROR:
                return "CLIENT_ERROR";
            case TIME_OUT:
                return "TIME_OUT";
            case INVALID_REMOTE_URI:
                return "INVALID_REMOTE_URI";
            case PEER_NOT_REACHABLE:
                return "PEER_NOT_REACHABLE";
            case INVALID_CREDENTIALS:
                return "INVALID_CREDENTIALS";
            case IN_PROGRESS:
                return "IN_PROGRESS";
            case DATA_CONNECTION_LOST:
                return "DATA_CONNECTION_LOST";
            case CROSS_DOMAIN_AUTHENTICATION:
                return "CROSS_DOMAIN_AUTHENTICATION";
            case SERVER_UNREACHABLE:
                return "SERVER_UNREACHABLE";
            default:
                return "UNKNOWN";
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.930 -0500", hash_original_field = "3619BBC96356F263661221785A00D6E9", hash_generated_field = "B8303B3F0D63DDF614BF107FEB77E81A")

    public static final int NO_ERROR = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.933 -0500", hash_original_field = "D49C55F18BAE95281A3EA181A1A4BAA5", hash_generated_field = "3FE643A93687B79CE239885C0B039B3C")

    public static final int SOCKET_ERROR = -1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.935 -0500", hash_original_field = "30F6DF224E5397A526A03F6A96F97625", hash_generated_field = "5942F1332FFC86CC3D93376E7AB29235")

    public static final int SERVER_ERROR = -2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.937 -0500", hash_original_field = "E6A911E1DEE32B6D8F5A5F43BEB6F1BB", hash_generated_field = "B9761118F4CEC715AAD861A4CEC9700D")

    public static final int TRANSACTION_TERMINTED = -3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.940 -0500", hash_original_field = "BD7F6573F8C4D82C10D1965259DBF14F", hash_generated_field = "38C40191FF07615E5787E2AAD16ABBAC")

    public static final int CLIENT_ERROR = -4;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.942 -0500", hash_original_field = "FD13FF29F684E2BC88BF50BFCEB95716", hash_generated_field = "BFAA93BD971FB59C7F2E5A426DFB0872")

    public static final int TIME_OUT = -5;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.944 -0500", hash_original_field = "EB4971936CE79FEE594874D0C00C37AB", hash_generated_field = "67AD595ABEB7CE4EA0455FB2C74C0D7C")

    public static final int INVALID_REMOTE_URI = -6;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.946 -0500", hash_original_field = "00496F435A7553AEFDD11624EAE42F38", hash_generated_field = "590AC2FC9A168726A854CA96CA9634F6")

    public static final int PEER_NOT_REACHABLE = -7;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.948 -0500", hash_original_field = "1A0601EBC8A20244AB6191E6E43C6AE1", hash_generated_field = "CC7636B075E6496DC1A61A8AAA978EE6")

    public static final int INVALID_CREDENTIALS = -8;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.951 -0500", hash_original_field = "22C9147E48D0E75EA46158A7C1985014", hash_generated_field = "29D02A663E324073BD3BBC3D3A49E0E5")

    public static final int IN_PROGRESS = -9;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.953 -0500", hash_original_field = "A7BB9D8C636690D6105C979FC37DADC2", hash_generated_field = "181D182287340C7AF7B39DC638DFE7D5")

    public static final int DATA_CONNECTION_LOST = -10;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.955 -0500", hash_original_field = "A2CA4FC76BF70CAC17C3BB39D2CFF426", hash_generated_field = "1ACA4BB723D6D9F1FDFEFC0CABD7AF64")

    public static final int CROSS_DOMAIN_AUTHENTICATION = -11;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.958 -0500", hash_original_field = "785DC35D640B9BFD2F3D72B500FA2E52", hash_generated_field = "A9745BBC56423EAFF521CF9C37D85FE5")

    public static final int SERVER_UNREACHABLE = -12;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:27.963 -0500", hash_original_method = "298DBB0089D5BDD0947985D624D66502", hash_generated_method = "41D8C4AB970CAF35B49ADA29C4DBFC2E")
    
private SipErrorCode() {
    }
}

