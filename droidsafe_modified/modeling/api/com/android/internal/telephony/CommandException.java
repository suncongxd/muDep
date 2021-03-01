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


package com.android.internal.telephony;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.util.Log;





public class CommandException extends RuntimeException {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:17.401 -0500", hash_original_method = "7B4AFAAC8EB612842A78BF3BAF671903", hash_generated_method = "D23204A182913A2BF15910A1336B0B1F")
    
public static CommandException
    fromRilErrno(int ril_errno) {
        switch(ril_errno) {
            case RILConstants.SUCCESS:                       return null;
            case RILConstants.RIL_ERRNO_INVALID_RESPONSE:
                return new CommandException(Error.INVALID_RESPONSE);
            case RILConstants.RADIO_NOT_AVAILABLE:
                return new CommandException(Error.RADIO_NOT_AVAILABLE);
            case RILConstants.GENERIC_FAILURE:
                return new CommandException(Error.GENERIC_FAILURE);
            case RILConstants.PASSWORD_INCORRECT:
                return new CommandException(Error.PASSWORD_INCORRECT);
            case RILConstants.SIM_PIN2:
                return new CommandException(Error.SIM_PIN2);
            case RILConstants.SIM_PUK2:
                return new CommandException(Error.SIM_PUK2);
            case RILConstants.REQUEST_NOT_SUPPORTED:
                return new CommandException(Error.REQUEST_NOT_SUPPORTED);
            case RILConstants.OP_NOT_ALLOWED_DURING_VOICE_CALL:
                return new CommandException(Error.OP_NOT_ALLOWED_DURING_VOICE_CALL);
            case RILConstants.OP_NOT_ALLOWED_BEFORE_REG_NW:
                return new CommandException(Error.OP_NOT_ALLOWED_BEFORE_REG_NW);
            case RILConstants.SMS_SEND_FAIL_RETRY:
                return new CommandException(Error.SMS_FAIL_RETRY);
            case RILConstants.SIM_ABSENT:
                return new CommandException(Error.SIM_ABSENT);
            case RILConstants.SUBSCRIPTION_NOT_AVAILABLE:
                return new CommandException(Error.SUBSCRIPTION_NOT_AVAILABLE);
            case RILConstants.MODE_NOT_SUPPORTED:
                return new CommandException(Error.MODE_NOT_SUPPORTED);
            case RILConstants.FDN_CHECK_FAILURE:
                return new CommandException(Error.FDN_CHECK_FAILURE);
            case RILConstants.ILLEGAL_SIM_OR_ME:
                return new CommandException(Error.ILLEGAL_SIM_OR_ME);
            default:
                Log.e("GSM", "Unrecognized RIL errno " + ril_errno);
                return new CommandException(Error.INVALID_RESPONSE);
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:17.392 -0500", hash_original_field = "064D28A84A3F8926DB08B53ABB141B0C", hash_generated_field = "237634D9088F176545E1DA209B5F69AC")

    private Error e;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:17.397 -0500", hash_original_method = "AE462A80EC1EE4B5A1CC7ED5CD5F752D", hash_generated_method = "C603DE2B60C70D57E1BFCFB67FA68B97")
    
public CommandException(Error e) {
        super(e.toString());
        this.e = e;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:17.403 -0500", hash_original_method = "279F774637579DF8D6F317F53F6621B2", hash_generated_method = "2B8120D1B1348FED461B07E5FB9C4A92")
    
public Error getCommandError() {
        return e;
    }

    
    public enum Error {
        INVALID_RESPONSE,
        RADIO_NOT_AVAILABLE,
        GENERIC_FAILURE,
        PASSWORD_INCORRECT,
        SIM_PIN2,
        SIM_PUK2,
        REQUEST_NOT_SUPPORTED,
        OP_NOT_ALLOWED_DURING_VOICE_CALL,
        OP_NOT_ALLOWED_BEFORE_REG_NW,
        SMS_FAIL_RETRY,
        SIM_ABSENT,
        SUBSCRIPTION_NOT_AVAILABLE,
        MODE_NOT_SUPPORTED,
        FDN_CHECK_FAILURE,
        ILLEGAL_SIM_OR_ME,
    }

    
}

