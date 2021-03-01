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

package droidsafe.runtime;

import java.util.Random;

//Droidsafe Imports
import droidsafe.annotations.*;
import droidsafe.helpers.DSUtils;
import android.app.ContextImpl;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Application;
import android.app.Dialog;
import android.app.admin.DeviceAdminReceiver;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.view.MotionEvent;
import com.google.android.maps.MapActivity;

/**
 * This class will simulate the android runtime system by making any calls or creating 
 * any globals required.
 * 
 * 
 * @author mgordon
 *
 */
public class DroidSafeAndroidRuntime {
    public static boolean control = new Random().nextBoolean();
    public static int switchControl = new Random().nextInt();
    public static int runtimeInteger  = new Random().nextInt();
    public static float runtimeFloat = new Random().nextFloat();
    public static Context context = new ContextImpl();

    /** make public so that other context's can grab this! */
    public static Application mApplication;

    /** Hold all taint to written files in the application */
    public static int FILE_SYSTEM_TAINT;
    
    /**
     * create any associated state and call init methods on an activity
     * 
     * call any life cycle events for the activity
     * 
     * @param activity
     */
    
    @DSVerified
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    public static void modelActivity(android.app.Activity activity) {
        if (mApplication != null)
            activity.setApplication(mApplication);

        Bundle b = new Bundle();
        activity.performCreate(b, context);

        activity.onAttachedToWindow();
        activity.onWindowFocusChanged(true);

        // Call all public methods with no params
        activity.onBackPressed();
        activity.onContentChanged();
        activity.onCreateDescription();
        activity.onLowMemory();
        activity.onRetainNonConfigurationInstance();
        //do not call this automatically because it leads to a crazy long path of crap
        //activity.onSearchRequested();
        activity.onUserInteraction();

        //TODO: DOES THIS MAKE SENSE? 
        //We should not change method's visiblity
        /*
        activity.onCreateDialog(0);
        activity.onCreateDialog(0, new Bundle());
        activity.onPrepareDialog(0, new Dialog(context));
        */
        
        /*
        //TODO: WHAT ABOUT A REAL MENU?  We moved to Activity's droidsafeOnOtherHook
        activity.onCreateOptionsMenu(null);
        activity.onPrepareOptionsMenu(null);
        activity.onCreateContextMenu(null, null, null);
        activity.onOptionsItemSelected(null);
        activity.onContextItemSelected(null);
        activity.dispatchTouchEvent(new MotionEvent());

        //activity.droidsafeOnKeyEvents();
        */
        activity.onConfigurationChanged(new Configuration());
        activity.droidsafeOnSavedInstanceState(b);
        
        activity.droidsafeOnResume();
        activity.droidsafeOnPause();
        
        ////////////////
        //Callback hooks specific for SubActivity classes
        //All subclass of Activity should implment this in the model
        //so that their callback will be called
        //Map, listview, expandblelist, etc...
        activity.droidsafeSubActivityCallbackHook();

        activity.droidsafeOnStop();

        activity.droidsafeOnRestart();
      
        activity.droidsafeOnDestroy();

        activity.droidsafePerformRestoreInstanceState(b);
        
        activity.onDetachedFromWindow();
        //Calls for MapActivity from mapping library
    }
    
    @DSVerified
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    public static void modelService(android.app.Service service) {
        if (mApplication != null)
            service.setApplication(mApplication);

        service.droidsafeAttachContext(context);

        service.onCreate();
        for (IntentFilter filter : service.__ds__intentFilters) {
            
            Intent intent = service.__ds__registerIntentFilter(filter);
            
            mApplication.__ds__intentsFromFilter.add(intent);

            service.onBind(intent);
            service.onRebind(intent);
            service.onStart(intent, DSUtils.FAKE_INT);
            service.onTaskRemoved(intent);
            service.onStartCommand(intent, DSUtils.FAKE_INT, DSUtils.FAKE_INT);
            service.onUnbind(intent);
            if (service instanceof IntentService) {
                ((IntentService) service).__ds__onHandleIntent(intent);
            }
        }
        service.droidsafeOnSubServiceHook();
        service.onConfigurationChanged(new Configuration());
        service.onLowMemory();
        service.onTrimMemory(0);
        service.stopSelf(0);
        service.onDestroy();
    }
    
    //NOTE: active commands (query, delete, ..) are called to try to 
    //invoke provider code.  Real access happens in the Client code
    //and we need to conntect the Android ContentProviderClient with this.....
    @DSVerified
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    public static void modelContentProvider(android.content.ContentProvider contentProvider) {
        contentProvider.droidsafeAttachContext(context);
        contentProvider.onCreate();
        contentProvider.onConfigurationChanged(new Configuration());
        contentProvider.onLowMemory();
        contentProvider.onTrimMemory(0);
        // Its not clear if we could figure out some of the value for these parameters
        contentProvider.query(null, null, null, null, null);
        contentProvider.insert(null, null);
        contentProvider.update(null, null, null, null);
        contentProvider.delete(null, null, null);
        contentProvider.getType(null);
    }

    @DSVerified
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    public static void modelBroadCastReceiver(BroadcastReceiver receiver) {   
        if (mApplication != null) {
            receiver.setApplication(mApplication);
            // callback receiver
            if (receiver instanceof android.app.admin.DeviceAdminReceiver) {
                DeviceAdminReceiver ar = (DeviceAdminReceiver) receiver;
                Intent appIntent = mApplication.droidsafeGetIntent();
                ar.onDisableRequested(context, appIntent);
                ar.onDisabled(context, appIntent);
                ar.onEnabled(context, appIntent);
                ar.onPasswordChanged(context, appIntent);
                ar.onPasswordExpiring(context, appIntent);
                ar.onPasswordFailed(context, appIntent);
                ar.onPasswordSucceeded(context, appIntent);
            }

            if (receiver instanceof android.appwidget.AppWidgetProvider) {
                AppWidgetProvider aw = (AppWidgetProvider)receiver;
                Intent appIntent = mApplication.droidsafeGetIntent();
                aw.onReceive(context, appIntent);
                aw.onEnabled(context);
                aw.onDisabled(context);
                int[] appWidgetIds = new int[1];
                appWidgetIds[0] = DSUtils.FAKE_INT;
                aw.onUpdate(context, AppWidgetManager.getInstance(context), appWidgetIds);
                aw.onDeleted(context, appWidgetIds);
            }
        }
    }
    
    public static void modelApplication(android.app.Application app) {
        mApplication = app;
        
        
        app.droidsafeOnCreate();
        app.droidsafeOnTerminate();
        app.droidsafeOnEverythingElse();
    }

    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static String getUnmodeledTaintString(String str) {
        return str;
    }

    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static int getUnmodeledTaintInt() {
        return 0;
    }
        
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static short getUnmodeledTaintShort() {
        return 0;
    }
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()    
    public static byte getUnmodeledTaintByte() {
        return 0;
    }    
    
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static boolean getUnmodeledTaintBoolean() {
        return true;
    }
    
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static char getUnmodeledTaintChar() {
        return 0;
    }
    
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static float getUnmodeledTaintFloat() {
        return 0;
    }
    
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static double getUnmodeledTaintDouble() {
        return 0;
    }
    
    
    @DSSource(DSSourceKind.UNMODELED)
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    @DSVerified()
    public static long getUnmodeledTaintLong() {        
        return 0;
    }
}
