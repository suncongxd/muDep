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


package android.provider;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.mobile1.DrmRawContent;
import android.drm.mobile1.DrmRights;
import android.drm.mobile1.DrmRightsManager;
import android.net.Uri;
import android.util.Log;

public final class DrmStore {

    /**
     * Utility function for inserting a file into the DRM content provider.
     *
     * @param cr The content resolver to use
     * @param file The file to insert
     * @param title The title for the content (or null)
     * @return uri to the DRM record or null
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.745 -0500", hash_original_method = "63C6946FC58566498A0CAB48839D6A9B", hash_generated_method = "214151EB5D4D89012A234996889CF1B8")
    
public static final Intent addDrmFile(ContentResolver cr, File file, String title) {
        FileInputStream fis = null;
        Intent result = null;

        try {
            fis = new FileInputStream(file);
            if (title == null) {
                title = file.getName();
                int lastDot = title.lastIndexOf('.');
                if (lastDot > 0) {
                    title = title.substring(0, lastDot);
                }
            }
            result = addDrmFile(cr, fis, title);
        } catch (Exception e) {
            Log.e(TAG, "pushing file failed", e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException in DrmStore.addDrmFile()", e);
            }
        }

        return result;
    }

    /**
     * Utility function for inserting a file stream into the DRM content provider.
     *
     * @param cr The content resolver to use
     * @param fis The FileInputStream to insert
     * @param title The title for the content (or null)
     * @return uri to the DRM record or null
     */
    @DSSink({DSSinkKind.LOG})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.749 -0500", hash_original_method = "4CDEA202B3139E78A079F62F7879F788", hash_generated_method = "1F0B839B8FD566BE358767B61EF6803A")
    
public static final Intent addDrmFile(ContentResolver cr, FileInputStream fis, String title) {
        OutputStream os = null;
        Intent result = null;

        try {
            DrmRawContent content = new DrmRawContent(fis, (int) fis.available(),
                    DrmRawContent.DRM_MIMETYPE_MESSAGE_STRING);
            String mimeType = content.getContentType();
            long size = fis.getChannel().size();

            DrmRightsManager manager = manager = DrmRightsManager.getInstance();
            DrmRights rights = manager.queryRights(content);
            InputStream stream = content.getContentInputStream(rights);

            Uri contentUri = null;
            if (mimeType.startsWith("audio/")) {
                contentUri = DrmStore.Audio.CONTENT_URI;
            } else if (mimeType.startsWith("image/")) {
                contentUri = DrmStore.Images.CONTENT_URI;
            } else {
                Log.w(TAG, "unsupported mime type " + mimeType);
            }

            if (contentUri != null) {
                ContentValues values = new ContentValues(3);
                values.put(DrmStore.Columns.TITLE, title);
                values.put(DrmStore.Columns.SIZE, size);
                values.put(DrmStore.Columns.MIME_TYPE, mimeType);

                Uri uri = cr.insert(contentUri, values);
                if (uri != null) {
                    os = cr.openOutputStream(uri);

                    byte[] buffer = new byte[1000];
                    int count;

                    while ((count = stream.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                    result = new Intent();
                    result.setDataAndType(uri, mimeType);

                }
            }
        } catch (Exception e) {
            Log.e(TAG, "pushing file failed", e);
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (os != null)
                    os.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException in DrmStore.addDrmFile()", e);
            }
        }

        return result;
    }

    /**
     * Utility function to enforce any permissions required to access DRM
     * content.
     *
     * @param context A context used for checking calling permission.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.752 -0500", hash_original_method = "F5346A2FCFA56F0B2264ABE6F2542994", hash_generated_method = "78F0B9034785C01AE417B801F62881AF")
    
public static void enforceAccessDrmPermission(Context context) {
        if (context.checkCallingOrSelfPermission(ACCESS_DRM_PERMISSION) !=
                PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException("Requires DRM permission");
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.729 -0500", hash_original_field = "A25F543E1D7DA769D205B9BDD55D1DC0", hash_generated_field = "432FBE01DD1C069C6C057D9BD9F228FE")

    private static final String TAG = "DrmStore";
    
    public interface Columns extends BaseColumns {
        
        public static final String DATA = "_data";
        
        public static final String SIZE = "_size";
        
        public static final String TITLE = "title";
        
        public static final String MIME_TYPE = "mime_type";

    }
    
    public interface Images extends Columns {

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/images");
    }
    
    public interface Audio extends Columns {

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/audio");
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.731 -0500", hash_original_field = "94E342F666953DA9539DE438F6A82261", hash_generated_field = "F95243080EDD554C30FC0476DD98FCCD")

    public static final String AUTHORITY = "drm";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:44.734 -0500", hash_original_field = "8A0D99AA222FD2CEDB130CEF0C95EBE4", hash_generated_field = "A4BF6B5761EC4106BC7C8CE9325687FB")

    private static final String ACCESS_DRM_PERMISSION = "android.permission.ACCESS_DRM";
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:30.341 -0400", hash_original_method = "794F8F649F74858E61C047D8FF367CC2", hash_generated_method = "794F8F649F74858E61C047D8FF367CC2")
    public DrmStore ()
    {
        //Synthesized constructor
    }
}

