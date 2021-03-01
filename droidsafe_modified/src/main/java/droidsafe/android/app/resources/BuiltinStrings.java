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
 */

package droidsafe.android.app.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import droidsafe.main.Config;

/**
 * This class deal-with android Built-in Strings
 * @author Nguyen Nguyen
 *
 */
public class BuiltinStrings {
    private final static Logger logger = LoggerFactory.getLogger(Resources.class);
    static BuiltinStrings builtin = null;
        
    /**
     * singleton access method
     * @return
     */
    public static BuiltinStrings v() { 
        if (builtin == null)
            builtin = new BuiltinStrings();
        return builtin; 
    }
    
    private Map<Integer, String> idToName = new HashMap<Integer, String>();
    private Map<String, Set<RString>> nameToValueSet = new HashMap<String, Set<RString>>();
    
    /**
     * load built-in strings
     */
    public void loadBuiltinStrings() {
        loadBuiltinStrings("us");
    }
    
    public Map<String, Set<RString>> getNameToValueSet() { 
        return nameToValueSet; 
    }
    
    public Map<Integer, String> getIdToNameMap() { 
        return idToName; 
    }
    
    /**
     * load builtin strings for a given locale
     * @param localeSuffix
     */
    private void loadBuiltinStrings(String localeSuffix) {
        
        logger.info("Loading builtin strings for locale {} ", localeSuffix);

        String path = Config.v().ANDROID_LIB_DIR.getPath() + File.separator + "res/values";
        if (localeSuffix != null && !localeSuffix.isEmpty() && !localeSuffix.equalsIgnoreCase("US"))
            path = path + "-" + localeSuffix;
        
        path = path + File.separator + "strings.xml";
        XmlFile xmlFile = new XmlFile(path);
        processStrings(xmlFile);
    }
    
    /**
     * private constructor to initialize default strings
     */
    private BuiltinStrings() {
        /* setup default string ID, to name */
        idToName.put(new Integer(0x010400b7), "android.R.string.BaMmi");
        idToName.put(new Integer(0x010400c3), "android.R.string.CLIRDefaultOffNextCallOff");
        idToName.put(new Integer(0x010400c2), "android.R.string.CLIRDefaultOffNextCallOn");
        idToName.put(new Integer(0x010400c1), "android.R.string.CLIRDefaultOnNextCallOff");
        idToName.put(new Integer(0x010400c0), "android.R.string.CLIRDefaultOnNextCallOn");
        idToName.put(new Integer(0x010400c5), "android.R.string.CLIRPermanent");
        idToName.put(new Integer(0x010400b5), "android.R.string.CfMmi");
        idToName.put(new Integer(0x010400b3), "android.R.string.ClipMmi");
        idToName.put(new Integer(0x010400b4), "android.R.string.ClirMmi");
        idToName.put(new Integer(0x010400be), "android.R.string.CndMmi");
        idToName.put(new Integer(0x010400ba), "android.R.string.CnipMmi");
        idToName.put(new Integer(0x010400bb), "android.R.string.CnirMmi");
        idToName.put(new Integer(0x010400b6), "android.R.string.CwMmi");
        idToName.put(new Integer(0x010400bf), "android.R.string.DndMmi");
        idToName.put(new Integer(0x0104025d), "android.R.string.Midnight");
        idToName.put(new Integer(0x0104025b), "android.R.string.Noon");
        idToName.put(new Integer(0x010400b9), "android.R.string.PinMmi");
        idToName.put(new Integer(0x010400b8), "android.R.string.PwdMmi");
        idToName.put(new Integer(0x010400c6), "android.R.string.RestrictedChangedTitle");
        idToName.put(new Integer(0x010400ca), "android.R.string.RestrictedOnAll");
        idToName.put(new Integer(0x010400c7), "android.R.string.RestrictedOnData");
        idToName.put(new Integer(0x010400c8), "android.R.string.RestrictedOnEmergency");
        idToName.put(new Integer(0x010400c9), "android.R.string.RestrictedOnNormal");
        idToName.put(new Integer(0x010400bd), "android.R.string.RuacMmi");
        idToName.put(new Integer(0x010400bc), "android.R.string.ThreeWCMmi");
        idToName.put(new Integer(0x01040010), "android.R.string.VideoView_error_button");
        idToName.put(new Integer(0x01040015), "android.R.string.VideoView_error_text_invalid_progressive_playback");
        idToName.put(new Integer(0x01040011), "android.R.string.VideoView_error_text_unknown");
        idToName.put(new Integer(0x01040012), "android.R.string.VideoView_error_title");
        idToName.put(new Integer(0x01040079), "android.R.string.abbrev_month");
        idToName.put(new Integer(0x01040078), "android.R.string.abbrev_month_day");
        idToName.put(new Integer(0x01040074), "android.R.string.abbrev_month_day_year");
        idToName.put(new Integer(0x0104007a), "android.R.string.abbrev_month_year");
        idToName.put(new Integer(0x010402c8), "android.R.string.accessibility_compound_button_selected");
        idToName.put(new Integer(0x010402c9), "android.R.string.accessibility_compound_button_unselected");
        idToName.put(new Integer(0x010402bb), "android.R.string.activity_list_empty");
        idToName.put(new Integer(0x010402a9), "android.R.string.adb_active_notification_message");
        idToName.put(new Integer(0x010402a8), "android.R.string.adb_active_notification_title");
        idToName.put(new Integer(0x01040265), "android.R.string.addToDictionary");
        idToName.put(new Integer(0x01040271), "android.R.string.aerr_application");
        idToName.put(new Integer(0x01040272), "android.R.string.aerr_process");
        idToName.put(new Integer(0x01040270), "android.R.string.aerr_title");
        idToName.put(new Integer(0x0104026c), "android.R.string.alwaysUse");
        idToName.put(new Integer(0x01040062), "android.R.string.am");
        idToName.put(new Integer(0x0104010e), "android.R.string.android_system_label");
        idToName.put(new Integer(0x01040274), "android.R.string.anr_activity_application");
        idToName.put(new Integer(0x01040275), "android.R.string.anr_activity_process");
        idToName.put(new Integer(0x01040276), "android.R.string.anr_application_process");
        idToName.put(new Integer(0x01040277), "android.R.string.anr_process");
        idToName.put(new Integer(0x01040273), "android.R.string.anr_title");
        idToName.put(new Integer(0x010400ad), "android.R.string.badPin");
        idToName.put(new Integer(0x010400ae), "android.R.string.badPuk");
        idToName.put(new Integer(0x01040229), "android.R.string.battery_low_percent_format");
        idToName.put(new Integer(0x01040228), "android.R.string.battery_low_subtitle");
        idToName.put(new Integer(0x01040227), "android.R.string.battery_low_title");
        idToName.put(new Integer(0x0104022a), "android.R.string.battery_low_why");
        idToName.put(new Integer(0x01040226), "android.R.string.battery_status_charging");
        idToName.put(new Integer(0x01040225), "android.R.string.battery_status_text_percent_format");
        idToName.put(new Integer(0x01040244), "android.R.string.beforeOneMonthDurationPast");
        idToName.put(new Integer(0x0104009d), "android.R.string.byteShort");
        idToName.put(new Integer(0x01040000), "android.R.string.cancel");
        idToName.put(new Integer(0x010402ae), "android.R.string.candidates_style");
        idToName.put(new Integer(0x0104026a), "android.R.string.capital_off");
        idToName.put(new Integer(0x01040269), "android.R.string.capital_on");
        idToName.put(new Integer(0x010400e2), "android.R.string.cfTemplateForwarded");
        idToName.put(new Integer(0x010400e3), "android.R.string.cfTemplateForwardedTime");
        idToName.put(new Integer(0x010400e1), "android.R.string.cfTemplateNotForwarded");
        idToName.put(new Integer(0x010400e4), "android.R.string.cfTemplateRegistered");
        idToName.put(new Integer(0x010400e5), "android.R.string.cfTemplateRegisteredTime");
        idToName.put(new Integer(0x0104026e), "android.R.string.chooseActivity");
        idToName.put(new Integer(0x0104026d), "android.R.string.clearDefaultHintMsg");
        idToName.put(new Integer(0x010400f6), "android.R.string.contentServiceSync");
        idToName.put(new Integer(0x010400f7), "android.R.string.contentServiceSyncNotificationTitle");
        idToName.put(new Integer(0x010400f8), "android.R.string.contentServiceTooManyDeletesNotificationDesc");
        idToName.put(new Integer(0x01040001), "android.R.string.copy");
        idToName.put(new Integer(0x01040263), "android.R.string.copyAll");
        idToName.put(new Integer(0x01040002), "android.R.string.copyUrl");
        idToName.put(new Integer(0x010402c7), "android.R.string.create_contact_using");
        idToName.put(new Integer(0x01040003), "android.R.string.cut");
        idToName.put(new Integer(0x01040262), "android.R.string.cutAll");
        idToName.put(new Integer(0x01040255), "android.R.string.daily");
        idToName.put(new Integer(0x0104007c), "android.R.string.date1_date2");
        idToName.put(new Integer(0x01040087), "android.R.string.date1_time1_date2_time2");
        idToName.put(new Integer(0x01040071), "android.R.string.date_and_time");
        idToName.put(new Integer(0x01040072), "android.R.string.date_time");
        idToName.put(new Integer(0x01040290), "android.R.string.date_time_set");
        idToName.put(new Integer(0x01040248), "android.R.string.day");
        idToName.put(new Integer(0x0104004b), "android.R.string.day_of_week_long_friday");
        idToName.put(new Integer(0x01040047), "android.R.string.day_of_week_long_monday");
        idToName.put(new Integer(0x0104004c), "android.R.string.day_of_week_long_saturday");
        idToName.put(new Integer(0x01040046), "android.R.string.day_of_week_long_sunday");
        idToName.put(new Integer(0x0104004a), "android.R.string.day_of_week_long_thursday");
        idToName.put(new Integer(0x01040048), "android.R.string.day_of_week_long_tuesday");
        idToName.put(new Integer(0x01040049), "android.R.string.day_of_week_long_wednesday");
        idToName.put(new Integer(0x01040052), "android.R.string.day_of_week_medium_friday");
        idToName.put(new Integer(0x0104004e), "android.R.string.day_of_week_medium_monday");
        idToName.put(new Integer(0x01040053), "android.R.string.day_of_week_medium_saturday");
        idToName.put(new Integer(0x0104004d), "android.R.string.day_of_week_medium_sunday");
        idToName.put(new Integer(0x01040051), "android.R.string.day_of_week_medium_thursday");
        idToName.put(new Integer(0x0104004f), "android.R.string.day_of_week_medium_tuesday");
        idToName.put(new Integer(0x01040050), "android.R.string.day_of_week_medium_wednesday");
        idToName.put(new Integer(0x01040059), "android.R.string.day_of_week_short_friday");
        idToName.put(new Integer(0x01040055), "android.R.string.day_of_week_short_monday");
        idToName.put(new Integer(0x0104005a), "android.R.string.day_of_week_short_saturday");
        idToName.put(new Integer(0x01040054), "android.R.string.day_of_week_short_sunday");
        idToName.put(new Integer(0x01040058), "android.R.string.day_of_week_short_thursday");
        idToName.put(new Integer(0x01040056), "android.R.string.day_of_week_short_tuesday");
        idToName.put(new Integer(0x01040057), "android.R.string.day_of_week_short_wednesday");
        idToName.put(new Integer(0x01040060), "android.R.string.day_of_week_shortest_friday");
        idToName.put(new Integer(0x0104005c), "android.R.string.day_of_week_shortest_monday");
        idToName.put(new Integer(0x01040061), "android.R.string.day_of_week_shortest_saturday");
        idToName.put(new Integer(0x0104005b), "android.R.string.day_of_week_shortest_sunday");
        idToName.put(new Integer(0x0104005f), "android.R.string.day_of_week_shortest_thursday");
        idToName.put(new Integer(0x0104005d), "android.R.string.day_of_week_shortest_tuesday");
        idToName.put(new Integer(0x0104005e), "android.R.string.day_of_week_shortest_wednesday");
        idToName.put(new Integer(0x01040249), "android.R.string.days");
        idToName.put(new Integer(0x0104027b), "android.R.string.debug");
        idToName.put(new Integer(0x01040005), "android.R.string.defaultMsisdnAlphaTag");
        idToName.put(new Integer(0x01040004), "android.R.string.defaultVoiceMailAlphaTag");
        idToName.put(new Integer(0x01040291), "android.R.string.default_permission_group");
        idToName.put(new Integer(0x0104009c), "android.R.string.default_text_encoding");
        idToName.put(new Integer(0x010402c6), "android.R.string.dial_number_using");
        idToName.put(new Integer(0x01040014), "android.R.string.dialog_alert_title");
        idToName.put(new Integer(0x01040266), "android.R.string.editTextMenuTitle");
        idToName.put(new Integer(0x0104025f), "android.R.string.elapsed_time_short_format_h_mm_ss");
        idToName.put(new Integer(0x0104025e), "android.R.string.elapsed_time_short_format_mm_ss");
        idToName.put(new Integer(0x010400a4), "android.R.string.ellipsis");
        idToName.put(new Integer(0x01040201), "android.R.string.emergency_call_dialog_number_for_display");
        idToName.put(new Integer(0x01040006), "android.R.string.emptyPhoneNumber");
        idToName.put(new Integer(0x01040254), "android.R.string.every_weekday");
        idToName.put(new Integer(0x010402b6), "android.R.string.ext_media_badremoval_notification_message");
        idToName.put(new Integer(0x010402b5), "android.R.string.ext_media_badremoval_notification_title");
        idToName.put(new Integer(0x010402b0), "android.R.string.ext_media_checking_notification_message");
        idToName.put(new Integer(0x010402af), "android.R.string.ext_media_checking_notification_title");
        idToName.put(new Integer(0x010402b2), "android.R.string.ext_media_nofs_notification_message");
        idToName.put(new Integer(0x010402b1), "android.R.string.ext_media_nofs_notification_title");
        idToName.put(new Integer(0x010402ba), "android.R.string.ext_media_nomedia_notification_message");
        idToName.put(new Integer(0x010402b9), "android.R.string.ext_media_nomedia_notification_title");
        idToName.put(new Integer(0x010402b8), "android.R.string.ext_media_safe_unmount_notification_message");
        idToName.put(new Integer(0x010402b7), "android.R.string.ext_media_safe_unmount_notification_title");
        idToName.put(new Integer(0x010402b4), "android.R.string.ext_media_unmountable_notification_message");
        idToName.put(new Integer(0x010402b3), "android.R.string.ext_media_unmountable_notification_title");
        idToName.put(new Integer(0x010402a7), "android.R.string.extmedia_format_button_format");
        idToName.put(new Integer(0x010402a6), "android.R.string.extmedia_format_message");
        idToName.put(new Integer(0x010402a5), "android.R.string.extmedia_format_title");
        idToName.put(new Integer(0x0104022b), "android.R.string.factorytest_failed");
        idToName.put(new Integer(0x0104022d), "android.R.string.factorytest_no_action");
        idToName.put(new Integer(0x0104022c), "android.R.string.factorytest_not_system");
        idToName.put(new Integer(0x0104022e), "android.R.string.factorytest_reboot");
        idToName.put(new Integer(0x010402ac), "android.R.string.fast_scroll_alphabet");
        idToName.put(new Integer(0x010402ad), "android.R.string.fast_scroll_numeric_alphabet");
        idToName.put(new Integer(0x010400e6), "android.R.string.fcComplete");
        idToName.put(new Integer(0x010400e7), "android.R.string.fcError");
        idToName.put(new Integer(0x010400a3), "android.R.string.fileSizeSuffix");
        idToName.put(new Integer(0x01040278), "android.R.string.force_close");
        idToName.put(new Integer(0x010402bf), "android.R.string.gadget_host_error_inflating");
        idToName.put(new Integer(0x010400a0), "android.R.string.gigabyteShort");
        idToName.put(new Integer(0x01040105), "android.R.string.global_action_lock");
        idToName.put(new Integer(0x01040106), "android.R.string.global_action_power_off");
        idToName.put(new Integer(0x01040109), "android.R.string.global_action_silent_mode_off_status");
        idToName.put(new Integer(0x01040108), "android.R.string.global_action_silent_mode_on_status");
        idToName.put(new Integer(0x01040107), "android.R.string.global_action_toggle_silent_mode");
        idToName.put(new Integer(0x01040104), "android.R.string.global_actions");
        idToName.put(new Integer(0x0104010c), "android.R.string.global_actions_airplane_mode_off_status");
        idToName.put(new Integer(0x0104010b), "android.R.string.global_actions_airplane_mode_on_status");
        idToName.put(new Integer(0x0104010a), "android.R.string.global_actions_toggle_airplane_mode");
        idToName.put(new Integer(0x01040296), "android.R.string.googlewebcontenthelper_loading");
        idToName.put(new Integer(0x0104024a), "android.R.string.hour");
        idToName.put(new Integer(0x0104021f), "android.R.string.hour_ampm");
        idToName.put(new Integer(0x01040220), "android.R.string.hour_cap_ampm");
        idToName.put(new Integer(0x01040067), "android.R.string.hour_minute_24");
        idToName.put(new Integer(0x01040068), "android.R.string.hour_minute_ampm");
        idToName.put(new Integer(0x01040069), "android.R.string.hour_minute_cap_ampm");
        idToName.put(new Integer(0x0104024b), "android.R.string.hours");
        idToName.put(new Integer(0x010400e9), "android.R.string.httpError");
        idToName.put(new Integer(0x010400ec), "android.R.string.httpErrorAuth");
        idToName.put(new Integer(0x01040007), "android.R.string.httpErrorBadUrl");
        idToName.put(new Integer(0x010400ee), "android.R.string.httpErrorConnect");
        idToName.put(new Integer(0x010400f2), "android.R.string.httpErrorFailedSslHandshake");
        idToName.put(new Integer(0x010400f3), "android.R.string.httpErrorFile");
        idToName.put(new Integer(0x010400f4), "android.R.string.httpErrorFileNotFound");
        idToName.put(new Integer(0x010400ef), "android.R.string.httpErrorIO");
        idToName.put(new Integer(0x010400ea), "android.R.string.httpErrorLookup");
        idToName.put(new Integer(0x010400e8), "android.R.string.httpErrorOk");
        idToName.put(new Integer(0x010400ed), "android.R.string.httpErrorProxyAuth");
        idToName.put(new Integer(0x010400f1), "android.R.string.httpErrorRedirectLoop");
        idToName.put(new Integer(0x010400f0), "android.R.string.httpErrorTimeout");
        idToName.put(new Integer(0x010400f5), "android.R.string.httpErrorTooManyRequests");
        idToName.put(new Integer(0x010400eb), "android.R.string.httpErrorUnsupportedAuthScheme");
        idToName.put(new Integer(0x01040008), "android.R.string.httpErrorUnsupportedScheme");
        idToName.put(new Integer(0x010402c5), "android.R.string.ime_action_default");
        idToName.put(new Integer(0x010402c4), "android.R.string.ime_action_done");
        idToName.put(new Integer(0x010402c0), "android.R.string.ime_action_go");
        idToName.put(new Integer(0x010402c3), "android.R.string.ime_action_next");
        idToName.put(new Integer(0x010402c1), "android.R.string.ime_action_search");
        idToName.put(new Integer(0x010402c2), "android.R.string.ime_action_send");
        idToName.put(new Integer(0x01040264), "android.R.string.inputMethod");
        idToName.put(new Integer(0x010400b0), "android.R.string.invalidPin");
        idToName.put(new Integer(0x01040232), "android.R.string.js_dialog_before_unload");
        idToName.put(new Integer(0x01040230), "android.R.string.js_dialog_title");
        idToName.put(new Integer(0x01040231), "android.R.string.js_dialog_title_default");
        idToName.put(new Integer(0x01040200), "android.R.string.keyguard_label_text");
        idToName.put(new Integer(0x010401fe), "android.R.string.keyguard_password_enter_pin_code");
        idToName.put(new Integer(0x010401ff), "android.R.string.keyguard_password_wrong_pin_code");
        idToName.put(new Integer(0x0104009e), "android.R.string.kilobyteShort");
        idToName.put(new Integer(0x010402aa), "android.R.string.locale_replacement");
        idToName.put(new Integer(0x01040202), "android.R.string.lockscreen_carrier_default");
        idToName.put(new Integer(0x0104020b), "android.R.string.lockscreen_charged");
        idToName.put(new Integer(0x01040207), "android.R.string.lockscreen_emergency_call");
        idToName.put(new Integer(0x01040216), "android.R.string.lockscreen_failed_attempts_almost_glogin");
        idToName.put(new Integer(0x01040218), "android.R.string.lockscreen_forgot_pattern_button_text");
        idToName.put(new Integer(0x0104021a), "android.R.string.lockscreen_glogin_instructions");
        idToName.put(new Integer(0x0104021e), "android.R.string.lockscreen_glogin_invalid_input");
        idToName.put(new Integer(0x0104021c), "android.R.string.lockscreen_glogin_password_hint");
        idToName.put(new Integer(0x0104021d), "android.R.string.lockscreen_glogin_submit_button");
        idToName.put(new Integer(0x01040219), "android.R.string.lockscreen_glogin_too_many_attempts");
        idToName.put(new Integer(0x0104021b), "android.R.string.lockscreen_glogin_username_hint");
        idToName.put(new Integer(0x01040205), "android.R.string.lockscreen_instructions_when_pattern_disabled");
        idToName.put(new Integer(0x01040204), "android.R.string.lockscreen_instructions_when_pattern_enabled");
        idToName.put(new Integer(0x0104020c), "android.R.string.lockscreen_low_battery");
        idToName.put(new Integer(0x0104020f), "android.R.string.lockscreen_missing_sim_instructions");
        idToName.put(new Integer(0x0104020e), "android.R.string.lockscreen_missing_sim_message");
        idToName.put(new Integer(0x0104020d), "android.R.string.lockscreen_missing_sim_message_short");
        idToName.put(new Integer(0x01040210), "android.R.string.lockscreen_network_locked_message");
        idToName.put(new Integer(0x01040208), "android.R.string.lockscreen_pattern_correct");
        idToName.put(new Integer(0x01040206), "android.R.string.lockscreen_pattern_instructions");
        idToName.put(new Integer(0x01040209), "android.R.string.lockscreen_pattern_wrong");
        idToName.put(new Integer(0x0104020a), "android.R.string.lockscreen_plugged_in");
        idToName.put(new Integer(0x01040203), "android.R.string.lockscreen_screen_locked");
        idToName.put(new Integer(0x01040213), "android.R.string.lockscreen_sim_locked_message");
        idToName.put(new Integer(0x01040212), "android.R.string.lockscreen_sim_puk_locked_instructions");
        idToName.put(new Integer(0x01040211), "android.R.string.lockscreen_sim_puk_locked_message");
        idToName.put(new Integer(0x01040214), "android.R.string.lockscreen_sim_unlock_progress_dialog_message");
        idToName.put(new Integer(0x01040217), "android.R.string.lockscreen_too_many_failed_attempts_countdown");
        idToName.put(new Integer(0x01040215), "android.R.string.lockscreen_too_many_failed_attempts_dialog_message");
        idToName.put(new Integer(0x01040268), "android.R.string.low_internal_storage_view_text");
        idToName.put(new Integer(0x01040267), "android.R.string.low_internal_storage_view_title");
        idToName.put(new Integer(0x010400f9), "android.R.string.low_memory");
        idToName.put(new Integer(0x010400fa), "android.R.string.me");
        idToName.put(new Integer(0x0104009f), "android.R.string.megabyteShort");
        idToName.put(new Integer(0x01040242), "android.R.string.menu_delete_shortcut_label");
        idToName.put(new Integer(0x01040241), "android.R.string.menu_enter_shortcut_label");
        idToName.put(new Integer(0x01040240), "android.R.string.menu_space_shortcut_label");
        idToName.put(new Integer(0x0104025c), "android.R.string.midnight");
        idToName.put(new Integer(0x0104024c), "android.R.string.minute");
        idToName.put(new Integer(0x0104024d), "android.R.string.minutes");
        idToName.put(new Integer(0x010400af), "android.R.string.mismatchPin");
        idToName.put(new Integer(0x010400ac), "android.R.string.mmiComplete");
        idToName.put(new Integer(0x010400a5), "android.R.string.mmiError");
        idToName.put(new Integer(0x010401fd), "android.R.string.mobileEmailTypeName");
        idToName.put(new Integer(0x01040076), "android.R.string.month");
        idToName.put(new Integer(0x01040075), "android.R.string.month_day");
        idToName.put(new Integer(0x0104006f), "android.R.string.month_day_year");
        idToName.put(new Integer(0x01040025), "android.R.string.month_long_april");
        idToName.put(new Integer(0x01040029), "android.R.string.month_long_august");
        idToName.put(new Integer(0x0104002d), "android.R.string.month_long_december");
        idToName.put(new Integer(0x01040023), "android.R.string.month_long_february");
        idToName.put(new Integer(0x01040022), "android.R.string.month_long_january");
        idToName.put(new Integer(0x01040028), "android.R.string.month_long_july");
        idToName.put(new Integer(0x01040027), "android.R.string.month_long_june");
        idToName.put(new Integer(0x01040024), "android.R.string.month_long_march");
        idToName.put(new Integer(0x01040026), "android.R.string.month_long_may");
        idToName.put(new Integer(0x0104002c), "android.R.string.month_long_november");
        idToName.put(new Integer(0x0104002b), "android.R.string.month_long_october");
        idToName.put(new Integer(0x0104002a), "android.R.string.month_long_september");
        idToName.put(new Integer(0x01040019), "android.R.string.month_long_standalone_april");
        idToName.put(new Integer(0x0104001d), "android.R.string.month_long_standalone_august");
        idToName.put(new Integer(0x01040021), "android.R.string.month_long_standalone_december");
        idToName.put(new Integer(0x01040017), "android.R.string.month_long_standalone_february");
        idToName.put(new Integer(0x01040016), "android.R.string.month_long_standalone_january");
        idToName.put(new Integer(0x0104001c), "android.R.string.month_long_standalone_july");
        idToName.put(new Integer(0x0104001b), "android.R.string.month_long_standalone_june");
        idToName.put(new Integer(0x01040018), "android.R.string.month_long_standalone_march");
        idToName.put(new Integer(0x0104001a), "android.R.string.month_long_standalone_may");
        idToName.put(new Integer(0x01040020), "android.R.string.month_long_standalone_november");
        idToName.put(new Integer(0x0104001f), "android.R.string.month_long_standalone_october");
        idToName.put(new Integer(0x0104001e), "android.R.string.month_long_standalone_september");
        idToName.put(new Integer(0x01040031), "android.R.string.month_medium_april");
        idToName.put(new Integer(0x01040035), "android.R.string.month_medium_august");
        idToName.put(new Integer(0x01040039), "android.R.string.month_medium_december");
        idToName.put(new Integer(0x0104002f), "android.R.string.month_medium_february");
        idToName.put(new Integer(0x0104002e), "android.R.string.month_medium_january");
        idToName.put(new Integer(0x01040034), "android.R.string.month_medium_july");
        idToName.put(new Integer(0x01040033), "android.R.string.month_medium_june");
        idToName.put(new Integer(0x01040030), "android.R.string.month_medium_march");
        idToName.put(new Integer(0x01040032), "android.R.string.month_medium_may");
        idToName.put(new Integer(0x01040038), "android.R.string.month_medium_november");
        idToName.put(new Integer(0x01040037), "android.R.string.month_medium_october");
        idToName.put(new Integer(0x01040036), "android.R.string.month_medium_september");
        idToName.put(new Integer(0x0104003d), "android.R.string.month_shortest_april");
        idToName.put(new Integer(0x01040041), "android.R.string.month_shortest_august");
        idToName.put(new Integer(0x01040045), "android.R.string.month_shortest_december");
        idToName.put(new Integer(0x0104003b), "android.R.string.month_shortest_february");
        idToName.put(new Integer(0x0104003a), "android.R.string.month_shortest_january");
        idToName.put(new Integer(0x01040040), "android.R.string.month_shortest_july");
        idToName.put(new Integer(0x0104003f), "android.R.string.month_shortest_june");
        idToName.put(new Integer(0x0104003c), "android.R.string.month_shortest_march");
        idToName.put(new Integer(0x0104003e), "android.R.string.month_shortest_may");
        idToName.put(new Integer(0x01040044), "android.R.string.month_shortest_november");
        idToName.put(new Integer(0x01040043), "android.R.string.month_shortest_october");
        idToName.put(new Integer(0x01040042), "android.R.string.month_shortest_september");
        idToName.put(new Integer(0x01040077), "android.R.string.month_year");
        idToName.put(new Integer(0x01040257), "android.R.string.monthly");
        idToName.put(new Integer(0x0104023e), "android.R.string.more_item_label");
        idToName.put(new Integer(0x010400b1), "android.R.string.needPuk");
        idToName.put(new Integer(0x010400b2), "android.R.string.needPuk2");
        idToName.put(new Integer(0x01040009), "android.R.string.no");
        idToName.put(new Integer(0x0104026f), "android.R.string.noApplications");
        idToName.put(new Integer(0x01040293), "android.R.string.no_permissions");
        idToName.put(new Integer(0x01040103), "android.R.string.no_recent_tasks");
        idToName.put(new Integer(0x0104025a), "android.R.string.noon");
        idToName.put(new Integer(0x0104006c), "android.R.string.numeric_date");
        idToName.put(new Integer(0x0104006d), "android.R.string.numeric_date_format");
        idToName.put(new Integer(0x0104006e), "android.R.string.numeric_date_template");
        idToName.put(new Integer(0x0104007d), "android.R.string.numeric_md1_md2");
        idToName.put(new Integer(0x01040082), "android.R.string.numeric_md1_time1_md2_time2");
        idToName.put(new Integer(0x0104007f), "android.R.string.numeric_mdy1_mdy2");
        idToName.put(new Integer(0x01040084), "android.R.string.numeric_mdy1_time1_mdy2_time2");
        idToName.put(new Integer(0x01040083), "android.R.string.numeric_wday1_md1_time1_wday2_md2_time2");
        idToName.put(new Integer(0x0104007e), "android.R.string.numeric_wday1_md1_wday2_md2");
        idToName.put(new Integer(0x01040081), "android.R.string.numeric_wday1_mdy1_time1_wday2_mdy2_time2");
        idToName.put(new Integer(0x01040080), "android.R.string.numeric_wday1_mdy1_wday2_mdy2");
        idToName.put(new Integer(0x0104000a), "android.R.string.ok");
        idToName.put(new Integer(0x01040243), "android.R.string.oneMonthDurationPast");
        idToName.put(new Integer(0x0104023c), "android.R.string.open_permission_deny");
        idToName.put(new Integer(0x010400ab), "android.R.string.passwordIncorrect");
        idToName.put(new Integer(0x0104000b), "android.R.string.paste");
        idToName.put(new Integer(0x010401a0), "android.R.string.permdesc_accessCoarseLocation");
        idToName.put(new Integer(0x0104019e), "android.R.string.permdesc_accessFineLocation");
        idToName.put(new Integer(0x0104019a), "android.R.string.permdesc_accessLocationExtraCommands");
        idToName.put(new Integer(0x01040198), "android.R.string.permdesc_accessMockLocation");
        idToName.put(new Integer(0x010401d8), "android.R.string.permdesc_accessNetworkState");
        idToName.put(new Integer(0x010401a2), "android.R.string.permdesc_accessSurfaceFlinger");
        idToName.put(new Integer(0x010401e2), "android.R.string.permdesc_accessWifiState");
        idToName.put(new Integer(0x01040158), "android.R.string.permdesc_backup");
        idToName.put(new Integer(0x01040156), "android.R.string.permdesc_batteryStats");
        idToName.put(new Integer(0x010401c2), "android.R.string.permdesc_bindGadget");
        idToName.put(new Integer(0x01040166), "android.R.string.permdesc_bindInputMethod");
        idToName.put(new Integer(0x010401ea), "android.R.string.permdesc_bluetooth");
        idToName.put(new Integer(0x010401e8), "android.R.string.permdesc_bluetoothAdmin");
        idToName.put(new Integer(0x010401ac), "android.R.string.permdesc_brick");
        idToName.put(new Integer(0x0104014c), "android.R.string.permdesc_broadcastPackageRemoved");
        idToName.put(new Integer(0x0104014e), "android.R.string.permdesc_broadcastSmsReceived");
        idToName.put(new Integer(0x0104018a), "android.R.string.permdesc_broadcastSticky");
        idToName.put(new Integer(0x01040150), "android.R.string.permdesc_broadcastWapPush");
        idToName.put(new Integer(0x010401ba), "android.R.string.permdesc_callPhone");
        idToName.put(new Integer(0x010401bc), "android.R.string.permdesc_callPrivileged");
        idToName.put(new Integer(0x010401aa), "android.R.string.permdesc_camera");
        idToName.put(new Integer(0x010401e0), "android.R.string.permdesc_changeBackgroundDataSetting");
        idToName.put(new Integer(0x0104017e), "android.R.string.permdesc_changeComponentState");
        idToName.put(new Integer(0x0104013e), "android.R.string.permdesc_changeConfiguration");
        idToName.put(new Integer(0x010401de), "android.R.string.permdesc_changeNetworkState");
        idToName.put(new Integer(0x010401e6), "android.R.string.permdesc_changeWifiMulticastState");
        idToName.put(new Integer(0x010401e4), "android.R.string.permdesc_changeWifiState");
        idToName.put(new Integer(0x010401c0), "android.R.string.permdesc_checkinProperties");
        idToName.put(new Integer(0x01040178), "android.R.string.permdesc_clearAppCache");
        idToName.put(new Integer(0x01040170), "android.R.string.permdesc_clearAppUserData");
        idToName.put(new Integer(0x010401da), "android.R.string.permdesc_createNetworkSockets");
        idToName.put(new Integer(0x01040172), "android.R.string.permdesc_deleteCacheFiles");
        idToName.put(new Integer(0x0104016e), "android.R.string.permdesc_deletePackages");
        idToName.put(new Integer(0x010401ca), "android.R.string.permdesc_devicePower");
        idToName.put(new Integer(0x0104017c), "android.R.string.permdesc_diagnostic");
        idToName.put(new Integer(0x010401ec), "android.R.string.permdesc_disableKeyguard");
        idToName.put(new Integer(0x01040144), "android.R.string.permdesc_dump");
        idToName.put(new Integer(0x01040128), "android.R.string.permdesc_expandStatusBar");
        idToName.put(new Integer(0x010401cc), "android.R.string.permdesc_factoryTest");
        idToName.put(new Integer(0x010401b6), "android.R.string.permdesc_flashlight");
        idToName.put(new Integer(0x01040142), "android.R.string.permdesc_forceBack");
        idToName.put(new Integer(0x010401d6), "android.R.string.permdesc_getAccounts");
        idToName.put(new Integer(0x01040174), "android.R.string.permdesc_getPackageSize");
        idToName.put(new Integer(0x01040138), "android.R.string.permdesc_getTasks");
        idToName.put(new Integer(0x010401b8), "android.R.string.permdesc_hardware_test");
        idToName.put(new Integer(0x01040162), "android.R.string.permdesc_injectEvents");
        idToName.put(new Integer(0x0104019c), "android.R.string.permdesc_installLocationProvider");
        idToName.put(new Integer(0x01040176), "android.R.string.permdesc_installPackages");
        idToName.put(new Integer(0x0104015a), "android.R.string.permdesc_internalSystemWindow");
        idToName.put(new Integer(0x010401be), "android.R.string.permdesc_locationUpdates");
        idToName.put(new Integer(0x01040160), "android.R.string.permdesc_manageAppTokens");
        idToName.put(new Integer(0x010401d2), "android.R.string.permdesc_masterClear");
        idToName.put(new Integer(0x010401a6), "android.R.string.permdesc_modifyAudioSettings");
        idToName.put(new Integer(0x010401c4), "android.R.string.permdesc_modifyPhoneState");
        idToName.put(new Integer(0x010401b2), "android.R.string.permdesc_mount_format_filesystems");
        idToName.put(new Integer(0x010401b0), "android.R.string.permdesc_mount_unmount_filesystems");
        idToName.put(new Integer(0x0104016c), "android.R.string.permdesc_persistentActivity");
        idToName.put(new Integer(0x010402bd), "android.R.string.permdesc_pkgUsageStats");
        idToName.put(new Integer(0x0104012a), "android.R.string.permdesc_processOutgoingCalls");
        idToName.put(new Integer(0x01040194), "android.R.string.permdesc_readCalendar");
        idToName.put(new Integer(0x0104018c), "android.R.string.permdesc_readContacts");
        idToName.put(new Integer(0x010401f8), "android.R.string.permdesc_readDictionary");
        idToName.put(new Integer(0x010401a4), "android.R.string.permdesc_readFrameBuffer");
        idToName.put(new Integer(0x01040235), "android.R.string.permdesc_readHistoryBookmarks");
        idToName.put(new Integer(0x01040164), "android.R.string.permdesc_readInputState");
        idToName.put(new Integer(0x0104017a), "android.R.string.permdesc_readLogs");
        idToName.put(new Integer(0x01040192), "android.R.string.permdesc_readOwnerData");
        idToName.put(new Integer(0x010401c6), "android.R.string.permdesc_readPhoneState");
        idToName.put(new Integer(0x01040132), "android.R.string.permdesc_readSms");
        idToName.put(new Integer(0x010401ee), "android.R.string.permdesc_readSyncSettings");
        idToName.put(new Integer(0x010401f2), "android.R.string.permdesc_readSyncStats");
        idToName.put(new Integer(0x010401ae), "android.R.string.permdesc_reboot");
        idToName.put(new Integer(0x01040188), "android.R.string.permdesc_receiveBootCompleted");
        idToName.put(new Integer(0x0104012e), "android.R.string.permdesc_receiveMms");
        idToName.put(new Integer(0x0104012c), "android.R.string.permdesc_receiveSms");
        idToName.put(new Integer(0x01040136), "android.R.string.permdesc_receiveWapPush");
        idToName.put(new Integer(0x010401a8), "android.R.string.permdesc_recordAudio");
        idToName.put(new Integer(0x0104013a), "android.R.string.permdesc_reorderTasks");
        idToName.put(new Integer(0x01040140), "android.R.string.permdesc_restartPackages");
        idToName.put(new Integer(0x0104014a), "android.R.string.permdesc_runSetActivityWatcher");
        idToName.put(new Integer(0x010401fc), "android.R.string.permdesc_sdcardWrite");
        idToName.put(new Integer(0x01040130), "android.R.string.permdesc_sendSms");
        idToName.put(new Integer(0x01040154), "android.R.string.permdesc_setAlwaysFinish");
        idToName.put(new Integer(0x0104015e), "android.R.string.permdesc_setAnimationScale");
        idToName.put(new Integer(0x0104013c), "android.R.string.permdesc_setDebugApp");
        idToName.put(new Integer(0x01040168), "android.R.string.permdesc_setOrientation");
        idToName.put(new Integer(0x01040180), "android.R.string.permdesc_setPreferredApplications");
        idToName.put(new Integer(0x01040152), "android.R.string.permdesc_setProcessLimit");
        idToName.put(new Integer(0x010401d4), "android.R.string.permdesc_setTimeZone");
        idToName.put(new Integer(0x010401ce), "android.R.string.permdesc_setWallpaper");
        idToName.put(new Integer(0x010401d0), "android.R.string.permdesc_setWallpaperHints");
        idToName.put(new Integer(0x01040146), "android.R.string.permdesc_shutdown");
        idToName.put(new Integer(0x0104016a), "android.R.string.permdesc_signalPersistentProcesses");
        idToName.put(new Integer(0x01040126), "android.R.string.permdesc_statusBar");
        idToName.put(new Integer(0x01040148), "android.R.string.permdesc_stopAppSwitches");
        idToName.put(new Integer(0x010401f4), "android.R.string.permdesc_subscribedFeedsRead");
        idToName.put(new Integer(0x010401f6), "android.R.string.permdesc_subscribedFeedsWrite");
        idToName.put(new Integer(0x0104015c), "android.R.string.permdesc_systemAlertWindow");
        idToName.put(new Integer(0x010401b4), "android.R.string.permdesc_vibrate");
        idToName.put(new Integer(0x010401c8), "android.R.string.permdesc_wakeLock");
        idToName.put(new Integer(0x010401dc), "android.R.string.permdesc_writeApnSettings");
        idToName.put(new Integer(0x01040196), "android.R.string.permdesc_writeCalendar");
        idToName.put(new Integer(0x0104018e), "android.R.string.permdesc_writeContacts");
        idToName.put(new Integer(0x010401fa), "android.R.string.permdesc_writeDictionary");
        idToName.put(new Integer(0x01040186), "android.R.string.permdesc_writeGservices");
        idToName.put(new Integer(0x01040237), "android.R.string.permdesc_writeHistoryBookmarks");
        idToName.put(new Integer(0x01040190), "android.R.string.permdesc_writeOwnerData");
        idToName.put(new Integer(0x01040184), "android.R.string.permdesc_writeSecureSettings");
        idToName.put(new Integer(0x01040182), "android.R.string.permdesc_writeSettings");
        idToName.put(new Integer(0x01040134), "android.R.string.permdesc_writeSms");
        idToName.put(new Integer(0x010401f0), "android.R.string.permdesc_writeSyncSettings");
        idToName.put(new Integer(0x0104011a), "android.R.string.permgroupdesc_accounts");
        idToName.put(new Integer(0x01040110), "android.R.string.permgroupdesc_costMoney");
        idToName.put(new Integer(0x01040122), "android.R.string.permgroupdesc_developmentTools");
        idToName.put(new Integer(0x0104011c), "android.R.string.permgroupdesc_hardwareControls");
        idToName.put(new Integer(0x01040116), "android.R.string.permgroupdesc_location");
        idToName.put(new Integer(0x01040112), "android.R.string.permgroupdesc_messages");
        idToName.put(new Integer(0x01040118), "android.R.string.permgroupdesc_network");
        idToName.put(new Integer(0x01040114), "android.R.string.permgroupdesc_personalInfo");
        idToName.put(new Integer(0x0104011e), "android.R.string.permgroupdesc_phoneCalls");
        idToName.put(new Integer(0x01040124), "android.R.string.permgroupdesc_storage");
        idToName.put(new Integer(0x01040120), "android.R.string.permgroupdesc_systemTools");
        idToName.put(new Integer(0x01040119), "android.R.string.permgrouplab_accounts");
        idToName.put(new Integer(0x0104010f), "android.R.string.permgrouplab_costMoney");
        idToName.put(new Integer(0x01040121), "android.R.string.permgrouplab_developmentTools");
        idToName.put(new Integer(0x0104011b), "android.R.string.permgrouplab_hardwareControls");
        idToName.put(new Integer(0x01040115), "android.R.string.permgrouplab_location");
        idToName.put(new Integer(0x01040111), "android.R.string.permgrouplab_messages");
        idToName.put(new Integer(0x01040117), "android.R.string.permgrouplab_network");
        idToName.put(new Integer(0x01040113), "android.R.string.permgrouplab_personalInfo");
        idToName.put(new Integer(0x0104011d), "android.R.string.permgrouplab_phoneCalls");
        idToName.put(new Integer(0x01040123), "android.R.string.permgrouplab_storage");
        idToName.put(new Integer(0x0104011f), "android.R.string.permgrouplab_systemTools");
        idToName.put(new Integer(0x01040292), "android.R.string.permissions_format");
        idToName.put(new Integer(0x0104019f), "android.R.string.permlab_accessCoarseLocation");
        idToName.put(new Integer(0x0104019d), "android.R.string.permlab_accessFineLocation");
        idToName.put(new Integer(0x01040199), "android.R.string.permlab_accessLocationExtraCommands");
        idToName.put(new Integer(0x01040197), "android.R.string.permlab_accessMockLocation");
        idToName.put(new Integer(0x010401d7), "android.R.string.permlab_accessNetworkState");
        idToName.put(new Integer(0x010401a1), "android.R.string.permlab_accessSurfaceFlinger");
        idToName.put(new Integer(0x010401e1), "android.R.string.permlab_accessWifiState");
        idToName.put(new Integer(0x01040157), "android.R.string.permlab_backup");
        idToName.put(new Integer(0x01040155), "android.R.string.permlab_batteryStats");
        idToName.put(new Integer(0x010401c1), "android.R.string.permlab_bindGadget");
        idToName.put(new Integer(0x01040165), "android.R.string.permlab_bindInputMethod");
        idToName.put(new Integer(0x010401e9), "android.R.string.permlab_bluetooth");
        idToName.put(new Integer(0x010401e7), "android.R.string.permlab_bluetoothAdmin");
        idToName.put(new Integer(0x010401ab), "android.R.string.permlab_brick");
        idToName.put(new Integer(0x0104014b), "android.R.string.permlab_broadcastPackageRemoved");
        idToName.put(new Integer(0x0104014d), "android.R.string.permlab_broadcastSmsReceived");
        idToName.put(new Integer(0x01040189), "android.R.string.permlab_broadcastSticky");
        idToName.put(new Integer(0x0104014f), "android.R.string.permlab_broadcastWapPush");
        idToName.put(new Integer(0x010401b9), "android.R.string.permlab_callPhone");
        idToName.put(new Integer(0x010401bb), "android.R.string.permlab_callPrivileged");
        idToName.put(new Integer(0x010401a9), "android.R.string.permlab_camera");
        idToName.put(new Integer(0x010401df), "android.R.string.permlab_changeBackgroundDataSetting");
        idToName.put(new Integer(0x0104017d), "android.R.string.permlab_changeComponentState");
        idToName.put(new Integer(0x0104013d), "android.R.string.permlab_changeConfiguration");
        idToName.put(new Integer(0x010401dd), "android.R.string.permlab_changeNetworkState");
        idToName.put(new Integer(0x010401e5), "android.R.string.permlab_changeWifiMulticastState");
        idToName.put(new Integer(0x010401e3), "android.R.string.permlab_changeWifiState");
        idToName.put(new Integer(0x010401bf), "android.R.string.permlab_checkinProperties");
        idToName.put(new Integer(0x01040177), "android.R.string.permlab_clearAppCache");
        idToName.put(new Integer(0x0104016f), "android.R.string.permlab_clearAppUserData");
        idToName.put(new Integer(0x010401d9), "android.R.string.permlab_createNetworkSockets");
        idToName.put(new Integer(0x01040171), "android.R.string.permlab_deleteCacheFiles");
        idToName.put(new Integer(0x0104016d), "android.R.string.permlab_deletePackages");
        idToName.put(new Integer(0x010401c9), "android.R.string.permlab_devicePower");
        idToName.put(new Integer(0x0104017b), "android.R.string.permlab_diagnostic");
        idToName.put(new Integer(0x010401eb), "android.R.string.permlab_disableKeyguard");
        idToName.put(new Integer(0x01040143), "android.R.string.permlab_dump");
        idToName.put(new Integer(0x01040127), "android.R.string.permlab_expandStatusBar");
        idToName.put(new Integer(0x010401cb), "android.R.string.permlab_factoryTest");
        idToName.put(new Integer(0x010401b5), "android.R.string.permlab_flashlight");
        idToName.put(new Integer(0x01040141), "android.R.string.permlab_forceBack");
        idToName.put(new Integer(0x010401d5), "android.R.string.permlab_getAccounts");
        idToName.put(new Integer(0x01040173), "android.R.string.permlab_getPackageSize");
        idToName.put(new Integer(0x01040137), "android.R.string.permlab_getTasks");
        idToName.put(new Integer(0x010401b7), "android.R.string.permlab_hardware_test");
        idToName.put(new Integer(0x01040161), "android.R.string.permlab_injectEvents");
        idToName.put(new Integer(0x0104019b), "android.R.string.permlab_installLocationProvider");
        idToName.put(new Integer(0x01040175), "android.R.string.permlab_installPackages");
        idToName.put(new Integer(0x01040159), "android.R.string.permlab_internalSystemWindow");
        idToName.put(new Integer(0x010401bd), "android.R.string.permlab_locationUpdates");
        idToName.put(new Integer(0x0104015f), "android.R.string.permlab_manageAppTokens");
        idToName.put(new Integer(0x010401d1), "android.R.string.permlab_masterClear");
        idToName.put(new Integer(0x010401a5), "android.R.string.permlab_modifyAudioSettings");
        idToName.put(new Integer(0x010401c3), "android.R.string.permlab_modifyPhoneState");
        idToName.put(new Integer(0x010401b1), "android.R.string.permlab_mount_format_filesystems");
        idToName.put(new Integer(0x010401af), "android.R.string.permlab_mount_unmount_filesystems");
        idToName.put(new Integer(0x0104016b), "android.R.string.permlab_persistentActivity");
        idToName.put(new Integer(0x010402bc), "android.R.string.permlab_pkgUsageStats");
        idToName.put(new Integer(0x01040129), "android.R.string.permlab_processOutgoingCalls");
        idToName.put(new Integer(0x01040193), "android.R.string.permlab_readCalendar");
        idToName.put(new Integer(0x0104018b), "android.R.string.permlab_readContacts");
        idToName.put(new Integer(0x010401f7), "android.R.string.permlab_readDictionary");
        idToName.put(new Integer(0x010401a3), "android.R.string.permlab_readFrameBuffer");
        idToName.put(new Integer(0x01040234), "android.R.string.permlab_readHistoryBookmarks");
        idToName.put(new Integer(0x01040163), "android.R.string.permlab_readInputState");
        idToName.put(new Integer(0x01040179), "android.R.string.permlab_readLogs");
        idToName.put(new Integer(0x01040191), "android.R.string.permlab_readOwnerData");
        idToName.put(new Integer(0x010401c5), "android.R.string.permlab_readPhoneState");
        idToName.put(new Integer(0x01040131), "android.R.string.permlab_readSms");
        idToName.put(new Integer(0x010401ed), "android.R.string.permlab_readSyncSettings");
        idToName.put(new Integer(0x010401f1), "android.R.string.permlab_readSyncStats");
        idToName.put(new Integer(0x010401ad), "android.R.string.permlab_reboot");
        idToName.put(new Integer(0x01040187), "android.R.string.permlab_receiveBootCompleted");
        idToName.put(new Integer(0x0104012d), "android.R.string.permlab_receiveMms");
        idToName.put(new Integer(0x0104012b), "android.R.string.permlab_receiveSms");
        idToName.put(new Integer(0x01040135), "android.R.string.permlab_receiveWapPush");
        idToName.put(new Integer(0x010401a7), "android.R.string.permlab_recordAudio");
        idToName.put(new Integer(0x01040139), "android.R.string.permlab_reorderTasks");
        idToName.put(new Integer(0x0104013f), "android.R.string.permlab_restartPackages");
        idToName.put(new Integer(0x01040149), "android.R.string.permlab_runSetActivityWatcher");
        idToName.put(new Integer(0x010401fb), "android.R.string.permlab_sdcardWrite");
        idToName.put(new Integer(0x0104012f), "android.R.string.permlab_sendSms");
        idToName.put(new Integer(0x01040153), "android.R.string.permlab_setAlwaysFinish");
        idToName.put(new Integer(0x0104015d), "android.R.string.permlab_setAnimationScale");
        idToName.put(new Integer(0x0104013b), "android.R.string.permlab_setDebugApp");
        idToName.put(new Integer(0x01040167), "android.R.string.permlab_setOrientation");
        idToName.put(new Integer(0x0104017f), "android.R.string.permlab_setPreferredApplications");
        idToName.put(new Integer(0x01040151), "android.R.string.permlab_setProcessLimit");
        idToName.put(new Integer(0x010401d3), "android.R.string.permlab_setTimeZone");
        idToName.put(new Integer(0x010401cd), "android.R.string.permlab_setWallpaper");
        idToName.put(new Integer(0x010401cf), "android.R.string.permlab_setWallpaperHints");
        idToName.put(new Integer(0x01040145), "android.R.string.permlab_shutdown");
        idToName.put(new Integer(0x01040169), "android.R.string.permlab_signalPersistentProcesses");
        idToName.put(new Integer(0x01040125), "android.R.string.permlab_statusBar");
        idToName.put(new Integer(0x01040147), "android.R.string.permlab_stopAppSwitches");
        idToName.put(new Integer(0x010401f3), "android.R.string.permlab_subscribedFeedsRead");
        idToName.put(new Integer(0x010401f5), "android.R.string.permlab_subscribedFeedsWrite");
        idToName.put(new Integer(0x0104015b), "android.R.string.permlab_systemAlertWindow");
        idToName.put(new Integer(0x010401b3), "android.R.string.permlab_vibrate");
        idToName.put(new Integer(0x010401c7), "android.R.string.permlab_wakeLock");
        idToName.put(new Integer(0x010401db), "android.R.string.permlab_writeApnSettings");
        idToName.put(new Integer(0x01040195), "android.R.string.permlab_writeCalendar");
        idToName.put(new Integer(0x0104018d), "android.R.string.permlab_writeContacts");
        idToName.put(new Integer(0x010401f9), "android.R.string.permlab_writeDictionary");
        idToName.put(new Integer(0x01040185), "android.R.string.permlab_writeGservices");
        idToName.put(new Integer(0x01040236), "android.R.string.permlab_writeHistoryBookmarks");
        idToName.put(new Integer(0x0104018f), "android.R.string.permlab_writeOwnerData");
        idToName.put(new Integer(0x01040183), "android.R.string.permlab_writeSecureSettings");
        idToName.put(new Integer(0x01040181), "android.R.string.permlab_writeSettings");
        idToName.put(new Integer(0x01040133), "android.R.string.permlab_writeSms");
        idToName.put(new Integer(0x010401ef), "android.R.string.permlab_writeSyncSettings");
        idToName.put(new Integer(0x01040294), "android.R.string.perms_hide");
        idToName.put(new Integer(0x01040295), "android.R.string.perms_show_all");
        idToName.put(new Integer(0x010400a2), "android.R.string.petabyteShort");
        idToName.put(new Integer(0x01040063), "android.R.string.pm");
        idToName.put(new Integer(0x010400fb), "android.R.string.power_dialog");
        idToName.put(new Integer(0x01040100), "android.R.string.power_off");
        idToName.put(new Integer(0x0104023f), "android.R.string.prepend_shortcut_label");
        idToName.put(new Integer(0x01040245), "android.R.string.preposition_for_date");
        idToName.put(new Integer(0x01040246), "android.R.string.preposition_for_time");
        idToName.put(new Integer(0x01040247), "android.R.string.preposition_for_year");
        idToName.put(new Integer(0x01040259), "android.R.string.relative_time");
        idToName.put(new Integer(0x01040279), "android.R.string.report");
        idToName.put(new Integer(0x01040285), "android.R.string.ringtone_default");
        idToName.put(new Integer(0x01040286), "android.R.string.ringtone_default_with_actual");
        idToName.put(new Integer(0x01040288), "android.R.string.ringtone_picker_title");
        idToName.put(new Integer(0x01040287), "android.R.string.ringtone_silent");
        idToName.put(new Integer(0x01040289), "android.R.string.ringtone_unknown");
        idToName.put(new Integer(0x010400d3), "android.R.string.roamingText0");
        idToName.put(new Integer(0x010400d4), "android.R.string.roamingText1");
        idToName.put(new Integer(0x010400dd), "android.R.string.roamingText10");
        idToName.put(new Integer(0x010400de), "android.R.string.roamingText11");
        idToName.put(new Integer(0x010400df), "android.R.string.roamingText12");
        idToName.put(new Integer(0x010400d5), "android.R.string.roamingText2");
        idToName.put(new Integer(0x010400d6), "android.R.string.roamingText3");
        idToName.put(new Integer(0x010400d7), "android.R.string.roamingText4");
        idToName.put(new Integer(0x010400d8), "android.R.string.roamingText5");
        idToName.put(new Integer(0x010400d9), "android.R.string.roamingText6");
        idToName.put(new Integer(0x010400da), "android.R.string.roamingText7");
        idToName.put(new Integer(0x010400db), "android.R.string.roamingText8");
        idToName.put(new Integer(0x010400dc), "android.R.string.roamingText9");
        idToName.put(new Integer(0x010400e0), "android.R.string.roamingTextSearching");
        idToName.put(new Integer(0x0104010d), "android.R.string.safeMode");
        idToName.put(new Integer(0x01040096), "android.R.string.same_month_md1_md2");
        idToName.put(new Integer(0x0104008e), "android.R.string.same_month_md1_time1_md2_time2");
        idToName.put(new Integer(0x01040099), "android.R.string.same_month_mdy1_mdy2");
        idToName.put(new Integer(0x01040092), "android.R.string.same_month_mdy1_time1_mdy2_time2");
        idToName.put(new Integer(0x01040090), "android.R.string.same_month_wday1_md1_time1_wday2_md2_time2");
        idToName.put(new Integer(0x01040097), "android.R.string.same_month_wday1_md1_wday2_md2");
        idToName.put(new Integer(0x01040094), "android.R.string.same_month_wday1_mdy1_time1_wday2_mdy2_time2");
        idToName.put(new Integer(0x01040095), "android.R.string.same_month_wday1_mdy1_wday2_mdy2");
        idToName.put(new Integer(0x0104008b), "android.R.string.same_year_md1_md2");
        idToName.put(new Integer(0x0104008d), "android.R.string.same_year_md1_time1_md2_time2");
        idToName.put(new Integer(0x01040098), "android.R.string.same_year_mdy1_mdy2");
        idToName.put(new Integer(0x01040091), "android.R.string.same_year_mdy1_time1_mdy2_time2");
        idToName.put(new Integer(0x0104008f), "android.R.string.same_year_wday1_md1_time1_wday2_md2_time2");
        idToName.put(new Integer(0x0104008c), "android.R.string.same_year_wday1_md1_wday2_md2");
        idToName.put(new Integer(0x01040093), "android.R.string.same_year_wday1_mdy1_time1_wday2_mdy2_time2");
        idToName.put(new Integer(0x0104009a), "android.R.string.same_year_wday1_mdy1_wday2_mdy2");
        idToName.put(new Integer(0x01040233), "android.R.string.save_password_label");
        idToName.put(new Integer(0x01040238), "android.R.string.save_password_message");
        idToName.put(new Integer(0x0104023b), "android.R.string.save_password_never");
        idToName.put(new Integer(0x01040239), "android.R.string.save_password_notnow");
        idToName.put(new Integer(0x0104023a), "android.R.string.save_password_remember");
        idToName.put(new Integer(0x010400ff), "android.R.string.screen_lock");
        idToName.put(new Integer(0x0104000c), "android.R.string.search_go");
        idToName.put(new Integer(0x0104024e), "android.R.string.second");
        idToName.put(new Integer(0x0104024f), "android.R.string.seconds");
        idToName.put(new Integer(0x0104000d), "android.R.string.selectAll");
        idToName.put(new Integer(0x01040260), "android.R.string.selectText");
        idToName.put(new Integer(0x0104028a), "android.R.string.select_character");
        idToName.put(new Integer(0x010402ab), "android.R.string.select_input_method");
        idToName.put(new Integer(0x0104027c), "android.R.string.sendText");
        idToName.put(new Integer(0x010400cc), "android.R.string.serviceClassData");
        idToName.put(new Integer(0x010400cf), "android.R.string.serviceClassDataAsync");
        idToName.put(new Integer(0x010400d0), "android.R.string.serviceClassDataSync");
        idToName.put(new Integer(0x010400cd), "android.R.string.serviceClassFAX");
        idToName.put(new Integer(0x010400d2), "android.R.string.serviceClassPAD");
        idToName.put(new Integer(0x010400d1), "android.R.string.serviceClassPacket");
        idToName.put(new Integer(0x010400ce), "android.R.string.serviceClassSMS");
        idToName.put(new Integer(0x010400cb), "android.R.string.serviceClassVoice");
        idToName.put(new Integer(0x010400a8), "android.R.string.serviceDisabled");
        idToName.put(new Integer(0x010400a6), "android.R.string.serviceEnabled");
        idToName.put(new Integer(0x010400a7), "android.R.string.serviceEnabledFor");
        idToName.put(new Integer(0x010400aa), "android.R.string.serviceErased");
        idToName.put(new Integer(0x010400c4), "android.R.string.serviceNotProvisioned");
        idToName.put(new Integer(0x010400a9), "android.R.string.serviceRegistered");
        idToName.put(new Integer(0x0104009b), "android.R.string.short_format_month");
        idToName.put(new Integer(0x01040102), "android.R.string.shutdown_confirm");
        idToName.put(new Integer(0x01040101), "android.R.string.shutdown_progress");
        idToName.put(new Integer(0x010400fc), "android.R.string.silent_mode");
        idToName.put(new Integer(0x0104028b), "android.R.string.sms_control_default_app_name");
        idToName.put(new Integer(0x0104028d), "android.R.string.sms_control_message");
        idToName.put(new Integer(0x0104028f), "android.R.string.sms_control_no");
        idToName.put(new Integer(0x0104028c), "android.R.string.sms_control_title");
        idToName.put(new Integer(0x0104028e), "android.R.string.sms_control_yes");
        idToName.put(new Integer(0x01040221), "android.R.string.status_bar_clear_all_button");
        idToName.put(new Integer(0x01040224), "android.R.string.status_bar_latest_events_title");
        idToName.put(new Integer(0x01040222), "android.R.string.status_bar_no_notifications_title");
        idToName.put(new Integer(0x01040223), "android.R.string.status_bar_ongoing_events_title");
        idToName.put(new Integer(0x01040261), "android.R.string.stopSelectingText");
        idToName.put(new Integer(0x010400a1), "android.R.string.terabyteShort");
        idToName.put(new Integer(0x0104023d), "android.R.string.text_copied");
        idToName.put(new Integer(0x0104007b), "android.R.string.time1_time2");
        idToName.put(new Integer(0x01040073), "android.R.string.time_date");
        idToName.put(new Integer(0x01040070), "android.R.string.time_of_day");
        idToName.put(new Integer(0x0104008a), "android.R.string.time_wday");
        idToName.put(new Integer(0x01040088), "android.R.string.time_wday_date");
        idToName.put(new Integer(0x01040065), "android.R.string.today");
        idToName.put(new Integer(0x01040066), "android.R.string.tomorrow");
        idToName.put(new Integer(0x010400fe), "android.R.string.turn_off_radio");
        idToName.put(new Integer(0x010400fd), "android.R.string.turn_on_radio");
        idToName.put(new Integer(0x010402be), "android.R.string.tutorial_double_tap_to_zoom_message_short");
        idToName.put(new Integer(0x0104006a), "android.R.string.twelve_hour_time_format");
        idToName.put(new Integer(0x0104006b), "android.R.string.twenty_four_hour_time_format");
        idToName.put(new Integer(0x0104000e), "android.R.string.unknownName");
        idToName.put(new Integer(0x0104000f), "android.R.string.untitled");
        idToName.put(new Integer(0x01040299), "android.R.string.usb_storage_button_mount");
        idToName.put(new Integer(0x0104029a), "android.R.string.usb_storage_button_unmount");
        idToName.put(new Integer(0x0104029b), "android.R.string.usb_storage_error_message");
        idToName.put(new Integer(0x01040298), "android.R.string.usb_storage_message");
        idToName.put(new Integer(0x0104029d), "android.R.string.usb_storage_notification_message");
        idToName.put(new Integer(0x0104029c), "android.R.string.usb_storage_notification_title");
        idToName.put(new Integer(0x010402a2), "android.R.string.usb_storage_stop_button_mount");
        idToName.put(new Integer(0x010402a3), "android.R.string.usb_storage_stop_button_unmount");
        idToName.put(new Integer(0x010402a4), "android.R.string.usb_storage_stop_error_message");
        idToName.put(new Integer(0x010402a1), "android.R.string.usb_storage_stop_message");
        idToName.put(new Integer(0x0104029f), "android.R.string.usb_storage_stop_notification_message");
        idToName.put(new Integer(0x0104029e), "android.R.string.usb_storage_stop_notification_title");
        idToName.put(new Integer(0x010402a0), "android.R.string.usb_storage_stop_title");
        idToName.put(new Integer(0x01040297), "android.R.string.usb_storage_title");
        idToName.put(new Integer(0x01040282), "android.R.string.volume_alarm");
        idToName.put(new Integer(0x01040281), "android.R.string.volume_bluetooth_call");
        idToName.put(new Integer(0x01040280), "android.R.string.volume_call");
        idToName.put(new Integer(0x0104027e), "android.R.string.volume_music");
        idToName.put(new Integer(0x0104027f), "android.R.string.volume_music_hint_playing_through_bluetooth");
        idToName.put(new Integer(0x01040283), "android.R.string.volume_notification");
        idToName.put(new Integer(0x0104027d), "android.R.string.volume_ringtone");
        idToName.put(new Integer(0x01040284), "android.R.string.volume_unknown");
        idToName.put(new Integer(0x0104027a), "android.R.string.wait");
        idToName.put(new Integer(0x01040085), "android.R.string.wday1_date1_time1_wday2_date2_time2");
        idToName.put(new Integer(0x01040086), "android.R.string.wday1_date1_wday2_date2");
        idToName.put(new Integer(0x01040089), "android.R.string.wday_date");
        idToName.put(new Integer(0x0104022f), "android.R.string.web_user_agent");
        idToName.put(new Integer(0x01040250), "android.R.string.week");
        idToName.put(new Integer(0x01040256), "android.R.string.weekly");
        idToName.put(new Integer(0x01040251), "android.R.string.weeks");
        idToName.put(new Integer(0x0104026b), "android.R.string.whichApplication");
        idToName.put(new Integer(0x01040252), "android.R.string.year");
        idToName.put(new Integer(0x01040258), "android.R.string.yearly");
        idToName.put(new Integer(0x01040253), "android.R.string.years");
        idToName.put(new Integer(0x01040013), "android.R.string.yes");
        idToName.put(new Integer(0x01040064), "android.R.string.yesterday");
    } 
    
    void processStrings(XmlFile xmlFile) {
        logger.info("Processing values from {}", xmlFile);

        BaseElement baseElement = new BaseElement(xmlFile.getDocumentElement(), null);
        List<Node> children = baseElement.gather_children();

        // process strings
        // IMPORTANT: this must execute fully before we process string-arrays because some string values in a string array
        // can be string names. We want to substitute the values in instead but don't have them all until this finishes
        for(int i = 0; i < children.size(); ++i){
            Element element = (Element)children.get(i);
            String tagName = element.getTagName();

            if (tagName.equals("string")){
                Node firstChild = element.getFirstChild();
                if (firstChild != null) {
                    logger.debug("firstChild {} ", firstChild);
                    String stringValue = firstChild.getNodeValue();
                    // create an instance of our internal representation of the android string - RString
                    RString rString = null;
                    try{
                        rString = new RString(element, xmlFile, stringValue);
                    } catch (InvalidPropertiesFormatException e) {
                        logger.error("String {} is not formatted correctly in {} : {}", element, xmlFile, e);
                        continue;
                    }
                    // the name automatically gets assigned during xml parsing
                    String stringName = "android.R.string." + rString.name;
                    
                    logger.debug("Adding a string name to string value mapping: ({}:{})", 
                            stringName, stringValue);

                    Set<RString> valueSet = nameToValueSet.get(stringName);

                    if (valueSet == null) {
                        valueSet = new HashSet<RString>();
                        nameToValueSet.put(stringName, valueSet);
                    }
                    valueSet.add(rString);
                }
            }
        }
    }
}
