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
import java.util.List;

import android.os.ServiceManager;

public class IccPhoneBookInterfaceManagerProxy extends IIccPhoneBook.Stub {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.354 -0500", hash_original_field = "7DE39F705C65CB46BA0CEB685F37564C", hash_generated_field = "35E1FDA72D4E9A11410B474286E4687E")

    private IccPhoneBookInterfaceManager mIccPhoneBookInterfaceManager;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.357 -0500", hash_original_method = "2AD2EA1697680350CF72D11537A6D219", hash_generated_method = "F8231FE04BE5DD33A69A519DC9197161")
    
public IccPhoneBookInterfaceManagerProxy(IccPhoneBookInterfaceManager
            iccPhoneBookInterfaceManager) {
        mIccPhoneBookInterfaceManager = iccPhoneBookInterfaceManager;
        if(ServiceManager.getService("simphonebook") == null) {
            ServiceManager.addService("simphonebook", this);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.360 -0500", hash_original_method = "D73EC83F9CAB72AF50214ABBB6029AE3", hash_generated_method = "C05E14B026867055FA90F8BF080D31FD")
    
public void setmIccPhoneBookInterfaceManager(
            IccPhoneBookInterfaceManager iccPhoneBookInterfaceManager) {
        this.mIccPhoneBookInterfaceManager = iccPhoneBookInterfaceManager;
    }

    @DSSink({DSSinkKind.CONTACT_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.362 -0500", hash_original_method = "5DDF0C0E89F34CB1A8CBBC902747CC21", hash_generated_method = "71433CD6839811CCC14FA0B747612628")
    
public boolean
    updateAdnRecordsInEfBySearch (int efid,
            String oldTag, String oldPhoneNumber,
            String newTag, String newPhoneNumber,
            String pin2) throws android.os.RemoteException {
        return mIccPhoneBookInterfaceManager.updateAdnRecordsInEfBySearch(
                efid, oldTag, oldPhoneNumber, newTag, newPhoneNumber, pin2);
    }

    @DSSink({DSSinkKind.CONTACT_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.364 -0500", hash_original_method = "9AD4878D7C5B3598149BA0FF3B75DA5E", hash_generated_method = "2BB198223BD68D33F98096ED43A85C0C")
    
public boolean
    updateAdnRecordsInEfByIndex(int efid, String newTag,
            String newPhoneNumber, int index, String pin2) throws android.os.RemoteException {
        return mIccPhoneBookInterfaceManager.updateAdnRecordsInEfByIndex(efid,
                newTag, newPhoneNumber, index, pin2);
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.366 -0500", hash_original_method = "1CAFC4CE677429A2AF90987C4E8DEFFE", hash_generated_method = "3BE8329F69EE86FDEEB3C00B73CC018F")
    
public int[] getAdnRecordsSize(int efid) throws android.os.RemoteException {
        return mIccPhoneBookInterfaceManager.getAdnRecordsSize(efid);
    }

    @DSSource({DSSourceKind.CONTACT_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:10.368 -0500", hash_original_method = "B2C428BBF650EA5A57A68B0456CE8B82", hash_generated_method = "5BAF5E056CADE4584F4027E591CB3155")
    
public List<AdnRecord> getAdnRecordsInEf(int efid) throws android.os.RemoteException {
        return mIccPhoneBookInterfaceManager.getAdnRecordsInEf(efid);
    }
    
}

