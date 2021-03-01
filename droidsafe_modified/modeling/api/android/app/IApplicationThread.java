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


package android.app;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.FileDescriptor;
import java.util.List;
import java.util.Map;

import android.content.ComponentName;
import android.content.IIntentReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.CompatibilityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IApplicationThread extends IInterface {
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void schedulePauseActivity(IBinder token, boolean finished, boolean userLeaving,
            int configChanges) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleStopActivity(IBinder token, boolean showWindow,
            int configChanges) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleWindowVisibility(IBinder token, boolean showWindow) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleSleeping(IBinder token, boolean sleeping) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleResumeActivity(IBinder token, boolean isForward) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleSendResult(IBinder token, List<ResultInfo> results) throws RemoteException;
    void scheduleLaunchActivity(Intent intent, IBinder token, int ident,
            ActivityInfo info, Configuration curConfig, CompatibilityInfo compatInfo,
            Bundle state, List<ResultInfo> pendingResults,
    		List<Intent> pendingNewIntents, boolean notResumed, boolean isForward,
    		String profileName, ParcelFileDescriptor profileFd, boolean autoStopProfiler)
    		throws RemoteException;
    void scheduleRelaunchActivity(IBinder token, List<ResultInfo> pendingResults,
            List<Intent> pendingNewIntents, int configChanges,
            boolean notResumed, Configuration config) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleNewIntent(List<Intent> intent, IBinder token) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleDestroyActivity(IBinder token, boolean finished,
            int configChanges) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleReceiver(Intent intent, ActivityInfo info, CompatibilityInfo compatInfo,
            int resultCode, String data, Bundle extras, boolean sync) throws RemoteException;
    static final int BACKUP_MODE_INCREMENTAL = 0;
    static final int BACKUP_MODE_FULL = 1;
    static final int BACKUP_MODE_RESTORE = 2;
    static final int BACKUP_MODE_RESTORE_FULL = 3;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleCreateBackupAgent(ApplicationInfo app, CompatibilityInfo compatInfo,
            int backupMode) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleDestroyBackupAgent(ApplicationInfo app, CompatibilityInfo compatInfo)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleCreateService(IBinder token, ServiceInfo info,
            CompatibilityInfo compatInfo) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleBindService(IBinder token,
            Intent intent, boolean rebind) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleUnbindService(IBinder token,
            Intent intent) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleServiceArgs(IBinder token, boolean taskRemoved, int startId,
            int flags, Intent args) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleStopService(IBinder token) throws RemoteException;
    static final int DEBUG_OFF = 0;
    static final int DEBUG_ON = 1;
    static final int DEBUG_WAIT = 2;
    void bindApplication(String packageName, ApplicationInfo info, List<ProviderInfo> providers,
            ComponentName testName, String profileName, ParcelFileDescriptor profileFd,
            boolean autoStopProfiler, Bundle testArguments, IInstrumentationWatcher testWatcher,
            int debugMode, boolean restrictedBackupMode, boolean persistent,
            Configuration config, CompatibilityInfo compatInfo, Map<String, IBinder> services,
            Bundle coreSettings) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleExit() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleSuicide() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void requestThumbnail(IBinder token) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleConfigurationChanged(Configuration config) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void updateTimeZone() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void clearDnsCache() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void setHttpProxy(String proxy, String port, String exclList) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void processInBackground() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void dumpService(FileDescriptor fd, IBinder servicetoken, String[] args)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleRegisteredReceiver(IIntentReceiver receiver, Intent intent,
            int resultCode, String data, Bundle extras, boolean ordered, boolean sticky)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleLowMemory() throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleActivityConfigurationChanged(IBinder token) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void profilerControl(boolean start, String path, ParcelFileDescriptor fd, int profileType)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void dumpHeap(boolean managed, String path, ParcelFileDescriptor fd)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void setSchedulingGroup(int group) throws RemoteException;
    void getMemoryInfo(Debug.MemoryInfo outInfo) throws RemoteException;
    static final int PACKAGE_REMOVED = 0;
    static final int EXTERNAL_STORAGE_UNAVAILABLE = 1;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void dispatchPackageBroadcast(int cmd, String[] packages) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleCrash(String msg) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void dumpActivity(FileDescriptor fd, IBinder servicetoken, String prefix, String[] args)
            throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void setCoreSettings(Bundle coreSettings) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void updatePackageCompatibilityInfo(String pkg, CompatibilityInfo info) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void scheduleTrimMemory(int level) throws RemoteException;
    Debug.MemoryInfo dumpMemInfo(FileDescriptor fd, boolean checkin, boolean all,
            String[] args) throws RemoteException;
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    void dumpGfxInfo(FileDescriptor fd, String[] args) throws RemoteException;

    String descriptor = "android.app.IApplicationThread";

    int SCHEDULE_PAUSE_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION;
    int SCHEDULE_STOP_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+2;
    int SCHEDULE_WINDOW_VISIBILITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+3;
    int SCHEDULE_RESUME_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+4;
    int SCHEDULE_SEND_RESULT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+5;
    int SCHEDULE_LAUNCH_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+6;
    int SCHEDULE_NEW_INTENT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+7;
    int SCHEDULE_FINISH_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+8;
    int SCHEDULE_RECEIVER_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+9;
    int SCHEDULE_CREATE_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+10;
    int SCHEDULE_STOP_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+11;
    int BIND_APPLICATION_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+12;
    int SCHEDULE_EXIT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+13;
    int REQUEST_THUMBNAIL_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+14;
    int SCHEDULE_CONFIGURATION_CHANGED_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+15;
    int SCHEDULE_SERVICE_ARGS_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+16;
    int UPDATE_TIME_ZONE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+17;
    int PROCESS_IN_BACKGROUND_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+18;
    int SCHEDULE_BIND_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+19;
    int SCHEDULE_UNBIND_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+20;
    int DUMP_SERVICE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+21;
    int SCHEDULE_REGISTERED_RECEIVER_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+22;
    int SCHEDULE_LOW_MEMORY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+23;
    int SCHEDULE_ACTIVITY_CONFIGURATION_CHANGED_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+24;
    int SCHEDULE_RELAUNCH_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+25;
    int SCHEDULE_SLEEPING_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+26;
    int PROFILER_CONTROL_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+27;
    int SET_SCHEDULING_GROUP_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+28;
    int SCHEDULE_CREATE_BACKUP_AGENT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+29;
    int SCHEDULE_DESTROY_BACKUP_AGENT_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+30;
    int GET_MEMORY_INFO_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+31;
    int SCHEDULE_SUICIDE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+32;
    int DISPATCH_PACKAGE_BROADCAST_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+33;
    int SCHEDULE_CRASH_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+34;
    int DUMP_HEAP_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+35;
    int DUMP_ACTIVITY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+36;
    int CLEAR_DNS_CACHE_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+37;
    int SET_HTTP_PROXY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+38;
    int SET_CORE_SETTINGS_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+39;
    int UPDATE_PACKAGE_COMPATIBILITY_INFO_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+40;
    int SCHEDULE_TRIM_MEMORY_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+41;
    int DUMP_MEM_INFO_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+42;
    int DUMP_GFX_INFO_TRANSACTION = IBinder.FIRST_CALL_TRANSACTION+43;
}
