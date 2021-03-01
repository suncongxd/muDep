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


package com.android.internal.telephony.gsm;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import com.android.internal.telephony.IccServiceTable;

public final class UsimServiceTable extends IccServiceTable {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.718 -0500", hash_original_method = "817BD5CF49BC23E6643D1124A4E1BE41", hash_generated_method = "150542268E0F45E4B0E90D2995ECAF23")
    
public UsimServiceTable(byte[] table) {
        super(table);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.721 -0500", hash_original_method = "DBA3BE1184DAC743D00CD4A8E44903BD", hash_generated_method = "6A590D4EAB7DD9114E1A9B7AE87BB34A")
    
public boolean isAvailable(UsimService service) {
        return super.isAvailable(service.ordinal());
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.723 -0500", hash_original_method = "E87A72CCD83C43A988523404C93A686A", hash_generated_method = "B571DFE3EED0772F1CF5DFD247BE4C20")
    
@Override
    protected String getTag() {
        return "UsimServiceTable";
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:32.725 -0500", hash_original_method = "5896C19C7C6B06EFB443A2DEA834E8BC", hash_generated_method = "9AF79B41A56042B3A8942DF1FC353844")
    
@Override
    protected Object[] getValues() {
        return UsimService.values();
    }
    
    public enum UsimService {
        PHONEBOOK,
        FDN,                                
        FDN_EXTENSION,                      
        SDN,                                
        SDN_EXTENSION,                      
        BDN,                                
        BDN_EXTENSION,                      
        OUTGOING_CALL_INFO,
        INCOMING_CALL_INFO,
        SM_STORAGE,
        SM_STATUS_REPORTS,
        SM_SERVICE_PARAMS,
        ADVICE_OF_CHARGE,
        CAP_CONFIG_PARAMS_2,
        CB_MESSAGE_ID,
        CB_MESSAGE_ID_RANGES,
        GROUP_ID_LEVEL_1,
        GROUP_ID_LEVEL_2,
        SPN,                                
        USER_PLMN_SELECT,
        MSISDN,
        IMAGE,
        LOCALISED_SERVICE_AREAS,
        EMLPP,                              
        EMLPP_AUTO_ANSWER,
        RFU,
        GSM_ACCESS,
        DATA_DL_VIA_SMS_PP,
        DATA_DL_VIA_SMS_CB,
        CALL_CONTROL_BY_USIM,
        MO_SMS_CONTROL_BY_USIM,
        RUN_AT_COMMAND,
        IGNORED_1,
        ENABLED_SERVICES_TABLE,
        APN_CONTROL_LIST,
        DEPERSONALISATION_CONTROL_KEYS,
        COOPERATIVE_NETWORK_LIST,
        GSM_SECURITY_CONTEXT,
        CPBCCH_INFO,
        INVESTIGATION_SCAN,
        MEXE,
        OPERATOR_PLMN_SELECT,
        HPLMN_SELECT,
        EXTENSION_5,                        
        PLMN_NETWORK_NAME,
        OPERATOR_PLMN_LIST,
        MBDN,                               
        MWI_STATUS,                         
        CFI_STATUS,                         
        IGNORED_2,
        SERVICE_PROVIDER_DISPLAY_INFO,
        MMS_NOTIFICATION,
        MMS_NOTIFICATION_EXTENSION,         
        GPRS_CALL_CONTROL_BY_USIM,
        MMS_CONNECTIVITY_PARAMS,
        NETWORK_INDICATION_OF_ALERTING,
        VGCS_GROUP_ID_LIST,
        VBS_GROUP_ID_LIST,
        PSEUDONYM,
        IWLAN_USER_PLMN_SELECT,
        IWLAN_OPERATOR_PLMN_SELECT,
        USER_WSID_LIST,
        OPERATOR_WSID_LIST,
        VGCS_SECURITY,
        VBS_SECURITY,
        WLAN_REAUTH_IDENTITY,
        MM_STORAGE,
        GBA,                                
        MBMS_SECURITY,
        DATA_DL_VIA_USSD,
        EQUIVALENT_HPLMN,
        TERMINAL_PROFILE_AFTER_UICC_ACTIVATION,
        EQUIVALENT_HPLMN_PRESENTATION,
        LAST_RPLMN_SELECTION_INDICATION,
        OMA_BCAST_PROFILE,
        GBA_LOCAL_KEY_ESTABLISHMENT,
        TERMINAL_APPLICATIONS,
        SPN_ICON,
        PLMN_NETWORK_NAME_ICON,
        USIM_IP_CONNECTION_PARAMS,
        IWLAN_HOME_ID_LIST,
        IWLAN_EQUIVALENT_HPLMN_PRESENTATION,
        IWLAN_HPLMN_PRIORITY_INDICATION,
        IWLAN_LAST_REGISTERED_PLMN,
        EPS_MOBILITY_MANAGEMENT_INFO,
        ALLOWED_CSG_LISTS_AND_INDICATIONS,
        CALL_CONTROL_ON_EPS_PDN_CONNECTION_BY_USIM,
        HPLMN_DIRECT_ACCESS,
        ECALL_DATA,
        OPERATOR_CSG_LISTS_AND_INDICATIONS,
        SM_OVER_IP,
        CSG_DISPLAY_CONTROL,
        IMS_COMMUNICATION_CONTROL_BY_USIM,
        EXTENDED_TERMINAL_APPLICATIONS,
        UICC_ACCESS_TO_IMS,
        NAS_CONFIG_BY_USIM
    }
    
}

