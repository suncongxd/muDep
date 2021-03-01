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


package android.webkit;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.File;
import java.io.InputStream;

import android.app.ActivityManager;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import droidsafe.helpers.DSUtils;

class JniUtil {

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.850 -0500", hash_original_method = "CE1013634620B3768FD958A950E1B58A", hash_generated_method = "B56A40EEE5A15444DF5667572ABD8E2F")
    
private static void checkInitialized() {
        if (sContext == null) {
            throw new IllegalStateException("Call CookieSyncManager::createInstance() or create a webview before using this class");
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.852 -0500", hash_original_method = "0CF05CDD233B4B3F349BCF9F7F323794", hash_generated_method = "760F2E763394D15BD2C5AEDBAC16835A")
    
protected static synchronized void setContext(Context context) {
        if (sContext != null) {
            return;
        }

        sContext = context.getApplicationContext();
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.854 -0500", hash_original_method = "71DD9FA52CE59CC40EEE95C3BDF1AEDE", hash_generated_method = "EE27CEA95F159D07E0109EDDC7E481D6")
    
protected static synchronized Context getContext() {
        return sContext;
    }

    /**
     * Called by JNI. Gets the application's database directory, excluding the trailing slash.
     * @return String The application's database directory
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.857 -0500", hash_original_method = "BDCE265A36C4AFED3EAB38552F391761", hash_generated_method = "099E02A53C35A11F01AD6E6E2C5CBA61")
    
private static synchronized String getDatabaseDirectory() {
        checkInitialized();

        if (sDatabaseDirectory == null) {
            sDatabaseDirectory = sContext.getDatabasePath("dummy").getParent();
        }

        return sDatabaseDirectory;
    }

    /**
     * Called by JNI. Gets the application's cache directory, excluding the trailing slash.
     * @return String The application's cache directory
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.859 -0500", hash_original_method = "84ED6FF2EF62C223EC6BEC024344219B", hash_generated_method = "8B65163FCC11F20AFFCE1D86C0AE1CD3")
    
private static synchronized String getCacheDirectory() {
        checkInitialized();

        if (sCacheDirectory == null) {
            File cacheDir = sContext.getCacheDir();
            if (cacheDir == null) {
                sCacheDirectory = "";
            } else {
                sCacheDirectory = cacheDir.getAbsolutePath();
            }
        }

        return sCacheDirectory;
    }

    /**
     * Called by JNI. Calculates the size of an input stream by reading it.
     * @return long The size of the stream
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.864 -0500", hash_original_method = "9098E303492EB41E4D0C87452F661A58", hash_generated_method = "824C2C4C74F4FF3981DEA2E998C81CE6")
    
private static synchronized long contentUrlSize(String url) {
        // content://
        if (url.startsWith(ANDROID_CONTENT)) {
            try {
                // Strip off mimetype, for compatibility with ContentLoader.java
                // If we don't do this, we can fail to load Gmail attachments,
                // because the URL being loaded doesn't exactly match the URL we
                // have permission to read.
                int mimeIndex = url.lastIndexOf('?');
                if (mimeIndex != -1) {
                    url = url.substring(0, mimeIndex);
                }
                Uri uri = Uri.parse(url);
                InputStream is = sContext.getContentResolver().openInputStream(uri);
                byte[] buffer = new byte[1024];
                int n;
                long size = 0;
                try {
                    while ((n = is.read(buffer)) != -1) {
                        size += n;
                    }
                } finally {
                    is.close();
                }
                return size;
            } catch (Exception e) {
                Log.e(LOGTAG, "Exception: " + url);
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * Called by JNI.
     *
     * @return  Opened input stream to content
     * TODO: Make all content loading use this instead of BrowserFrame.java
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.868 -0500", hash_original_method = "398C6FE70AB96657FEF885B5148F4235", hash_generated_method = "DE25C8942CA27F8B6AE59DA6BCB1DAC2")
    
private static synchronized InputStream contentUrlStream(String url) {
        // content://
        if (url.startsWith(ANDROID_CONTENT)) {
            try {
                // Strip off mimetype, for compatibility with ContentLoader.java
                // If we don't do this, we can fail to load Gmail attachments,
                // because the URL being loaded doesn't exactly match the URL we
                // have permission to read.
                int mimeIndex = url.lastIndexOf('?');
                if (mimeIndex != -1) {
                    url = url.substring(0, mimeIndex);
                }
                Uri uri = Uri.parse(url);
                return sContext.getContentResolver().openInputStream(uri);
            } catch (Exception e) {
                Log.e(LOGTAG, "Exception: " + url);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Returns true if we're using the Chromium HTTP stack.
     *
     * TODO: Remove this if/when we permanently switch to the Chromium HTTP stack
     * http:/b/3118772
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.870 -0500", hash_original_method = "C3125C30E3D98768E8F5C485FA3194CB", hash_generated_method = "EA1580208D6B423B3C4AF18EA717A072")
    
static boolean useChromiumHttpStack() {
        if (sUseChromiumHttpStack == null) {
            sUseChromiumHttpStack = nativeUseChromiumHttpStack();
        }
        return sUseChromiumHttpStack;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.872 -0500", hash_original_method = "09A3334F38F82D4EA8C4BFF15EDAC335", hash_generated_method = "8C73DB2ED38A051384A36F3D8E73D07D")
    
private static synchronized String getAutofillQueryUrl() {
        checkInitialized();
        // If the device has not checked in it won't have pulled down the system setting for the
        // Autofill Url. In that case we will not make autofill server requests.
        return Settings.Secure.getString(sContext.getContentResolver(),
                Settings.Secure.WEB_AUTOFILL_QUERY_URL);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.875 -0500", hash_original_method = "0DF0DD4E6F03810B72E6695CC1275287", hash_generated_method = "99E89068ABB1A16EC1CCCCA711964F09")
    
private static boolean canSatisfyMemoryAllocation(long bytesRequested) {
        checkInitialized();
        ActivityManager manager = (ActivityManager) sContext.getSystemService(
                Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memInfo);
        long leftToAllocate = memInfo.availMem - memInfo.threshold;
        return !memInfo.lowMemory && bytesRequested < leftToAllocate;
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static boolean nativeUseChromiumHttpStack() {
        return DSUtils.UNKNOWN_BOOLEAN;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.836 -0500", hash_original_field = "CF5103981B618784F76950E4558FDBCC", hash_generated_field = "061362C112C980EB4954480FBAFBE378")

    private static final String LOGTAG = "webkit";
    
    static {
        System.loadLibrary("webcore");
        System.loadLibrary("chromium_net");
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.841 -0500", hash_original_field = "EA5D9DBD6B32675A8D38DFDEA2CC60D6", hash_generated_field = "09C76BDC3F22F12D5D3EE075C4365D0C")

    // Used by the Chromium HTTP stack.
    private static String sDatabaseDirectory;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.843 -0500", hash_original_field = "A5336F69617A8F465B4516B6E38D4574", hash_generated_field = "AF7392F9E0B86779E1955C31E6B8D8D2")

    private static String sCacheDirectory;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.845 -0500", hash_original_field = "E9BB7C4CF1427582BF68D3E345AC02D7", hash_generated_field = "075B7A0020C230CE0B114CFAF022DB3C")

    private static Boolean sUseChromiumHttpStack;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.847 -0500", hash_original_field = "CEF70F4829B188D89E37229D281B99F4", hash_generated_field = "BB35128711B5DD286691A47454B04C39")

    private static Context sContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.861 -0500", hash_original_field = "81DC0344028916DF5E3ACF3104622976", hash_generated_field = "A5A05B8DD281F40CCF47CBF0390B00E3")

    private static final String ANDROID_CONTENT = "content:";
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:10.839 -0500", hash_original_method = "7797423382E6C41F3BC6877796CBF278", hash_generated_method = "9AE85924909E9A13CD3A5362A20BBAC1")
    
private JniUtil() {}
}

