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
 * Copyright (C) 2006 The Android Open Source Project
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


package com.android.internal.telephony.gsm;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;

import com.android.internal.telephony.DataConnection;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneBase;
import com.android.internal.telephony.RILConstants;
import com.android.internal.telephony.RetryManager;

public class GsmDataConnection extends DataConnection {

    /**
     * Create the connection object
     *
     * @param phone the Phone
     * @param id the connection id
     * @param rm the RetryManager
     * @return GsmDataConnection that was created.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.602 -0500", hash_original_method = "3F81F4D6513F732B569C05DC90B32139", hash_generated_method = "C8E2544D3D4A91601DCA092D1A682824")
    
static GsmDataConnection makeDataConnection(PhoneBase phone, int id, RetryManager rm) {
        synchronized (mCountLock) {
            mCount += 1;
        }
        GsmDataConnection gsmDc = new GsmDataConnection(phone, "GsmDC-" + mCount, id, rm);
        gsmDc.start();
        if (DBG) gsmDc.log("Made " + gsmDc.getName());
        return gsmDc;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.592 -0500", hash_original_field = "28DECCA9F494133CD6F61BCA99A50ECA", hash_generated_field = "D922E4732DF1A8050A7A55C2D62EC423")

    private static final String LOG_TAG = "GSM";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.594 -0500", hash_original_field = "44E14CB3E13357C148A024CEA0362E03", hash_generated_field = "4B86B7A6BB3954D8A0D6116E4CC1760F")

    protected int mProfileId = RILConstants.DATA_PROFILE_DEFAULT;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.596 -0500", hash_original_field = "0C3FDE3A59FEDCE4BD7DF7A037E129AF", hash_generated_field = "FEA7396BF99CD16BDCDC29FABBFA8C84")

    protected String mActiveApnType = Phone.APN_TYPE_DEFAULT;
    //***** Constructor
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.599 -0500", hash_original_method = "D7C479FF4503363D3DB56494C7CC433A", hash_generated_method = "5297674045A18508A4297AD57B8A3E35")
    
private GsmDataConnection(PhoneBase phone, String name, int id, RetryManager rm) {
        super(phone, name, id, rm);
    }

    /**
     * Begin setting up a data connection, calls setupDataCall
     * and the ConnectionParams will be returned with the
     * EVENT_SETUP_DATA_CONNECTION_DONE AsyncResul.userObj.
     *
     * @param cp is the connection parameters
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.605 -0500", hash_original_method = "B9699B49BCC7654BF74B38BF9D177791", hash_generated_method = "C075718CC53F5984DFE5F216D9E6DD1C")
    
@Override
    protected
    void onConnect(ConnectionParams cp) {
        mApn = cp.apn;

        if (DBG) log("Connecting to carrier: '" + mApn.carrier
                + "' APN: '" + mApn.apn
                + "' proxy: '" + mApn.proxy + "' port: '" + mApn.port);

        createTime = -1;
        lastFailTime = -1;
        lastFailCause = FailCause.NONE;

        // msg.obj will be returned in AsyncResult.userObj;
        Message msg = obtainMessage(EVENT_SETUP_DATA_CONNECTION_DONE, cp);
        msg.obj = cp;

        int authType = mApn.authType;
        if (authType == -1) {
            authType = (mApn.user != null) ? RILConstants.SETUP_DATA_AUTH_PAP_CHAP :
                RILConstants.SETUP_DATA_AUTH_NONE;
        }

        String protocol;
        if (phone.getServiceState().getRoaming()) {
            protocol = mApn.roamingProtocol;
        } else {
            protocol = mApn.protocol;
        }

        phone.mCM.setupDataCall(
                Integer.toString(getRadioTechnology(RILConstants.SETUP_DATA_TECH_GSM)),
                Integer.toString(mProfileId),
                mApn.apn, mApn.user, mApn.password,
                Integer.toString(authType),
                protocol, msg);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.607 -0500", hash_original_method = "A22DEA53C24C8CD549338E7FA6E130EF", hash_generated_method = "C7A38740072EC66B3FB0961E902CE8E8")
    
public void setProfileId(int profileId) {
        mProfileId = profileId;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.610 -0500", hash_original_method = "F4D053905D2BF654ACB2CC18D573B985", hash_generated_method = "6B1707CDF475C85B362C7245C61A4BBE")
    
public int getProfileId() {
        return mProfileId;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.612 -0500", hash_original_method = "49E60EF07FF1E574F2B5B752F6A3111C", hash_generated_method = "4445A9FEFD53F6BCB64C4E5A8E6E3744")
    
public void setActiveApnType(String apnType) {
        mActiveApnType = apnType;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.614 -0500", hash_original_method = "E1755C32D3A976D9153A9C990E44F94A", hash_generated_method = "2277A0516A81E66C30686641937D6FA7")
    
@Override
    public String toString() {
        return "State=" + getCurrentState().getName() + " Apn=" + mApn +
               " create=" + createTime + " lastFail=" + lastFailTime +
               " lastFailCause=" + lastFailCause;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.617 -0500", hash_original_method = "0657EF2F9C80323FA50A03320BF221A9", hash_generated_method = "893D99BFFF3CC572284D6550B690D8F4")
    
@Override
    protected boolean isDnsOk(String[] domainNameServers) {
        if (NULL_IP.equals(domainNameServers[0]) && NULL_IP.equals(domainNameServers[1])
                && !phone.isDnsCheckDisabled()) {
            // Work around a race condition where QMI does not fill in DNS:
            // Deactivate PDP and let DataConnectionTracker retry.
            // Do not apply the race condition workaround for MMS APN
            // if Proxy is an IP-address.
            // Otherwise, the default APN will not be restored anymore.
            if (!mApn.types[0].equals(Phone.APN_TYPE_MMS)
                || !isIpAddress(mApn.mmsProxy)) {
                log(String.format(
                        "isDnsOk: return false apn.types[0]=%s APN_TYPE_MMS=%s isIpAddress(%s)=%s",
                        mApn.types[0], Phone.APN_TYPE_MMS, mApn.mmsProxy,
                        isIpAddress(mApn.mmsProxy)));
                return false;
            }
        }
        return true;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.620 -0500", hash_original_method = "E3E03901000B497B61536E572BF51316", hash_generated_method = "55AC2223A3FC018EA3E9CC8DDDECA33D")
    
@Override
    protected void log(String s) {
        Log.d(LOG_TAG, "[" + getName() + "] " + s);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.622 -0500", hash_original_method = "0EBFCAAE2D7BDF5375234415A0DA2D8D", hash_generated_method = "6C2314413E27DF48C4616BF6EFFB3358")
    
private boolean isIpAddress(String address) {
        if (address == null) return false;

        return Patterns.IP_ADDRESS.matcher(address).matches();
    }
}

