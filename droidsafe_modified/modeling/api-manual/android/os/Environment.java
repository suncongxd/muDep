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


package android.os;

import java.io.File;

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSModeled;

public class Environment {
	
	    
	    /**
	     * Standard directory in which to place any audio files that should be
	     * in the regular list of music for the user.
	     * This may be combined with
	     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS},
	     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
	     * of directories to categories a particular audio file as more than one
	     * type.
	     */
	    public static String DIRECTORY_MUSIC = "Music";
	    
	    /**
	     * Standard directory in which to place any audio files that should be
	     * in the list of podcasts that the user can select (not as regular
	     * music).
	     * This may be combined with {@link #DIRECTORY_MUSIC},
	     * {@link #DIRECTORY_NOTIFICATIONS},
	     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
	     * of directories to categories a particular audio file as more than one
	     * type.
	     */
	    public static String DIRECTORY_PODCASTS = "Podcasts";
	    
	    /**
	     * Standard directory in which to place any audio files that should be
	     * in the list of ringtones that the user can select (not as regular
	     * music).
	     * This may be combined with {@link #DIRECTORY_MUSIC},
	     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS}, and
	     * {@link #DIRECTORY_ALARMS} as a series
	     * of directories to categories a particular audio file as more than one
	     * type.
	     */
	    public static String DIRECTORY_RINGTONES = "Ringtones";
	    
	    /**
	     * Standard directory in which to place any audio files that should be
	     * in the list of alarms that the user can select (not as regular
	     * music).
	     * This may be combined with {@link #DIRECTORY_MUSIC},
	     * {@link #DIRECTORY_PODCASTS}, {@link #DIRECTORY_NOTIFICATIONS},
	     * and {@link #DIRECTORY_RINGTONES} as a series
	     * of directories to categories a particular audio file as more than one
	     * type.
	     */
	    public static String DIRECTORY_ALARMS = "Alarms";
	    
	    /**
	     * Standard directory in which to place any audio files that should be
	     * in the list of notifications that the user can select (not as regular
	     * music).
	     * This may be combined with {@link #DIRECTORY_MUSIC},
	     * {@link #DIRECTORY_PODCASTS},
	     * {@link #DIRECTORY_ALARMS}, and {@link #DIRECTORY_RINGTONES} as a series
	     * of directories to categories a particular audio file as more than one
	     * type.
	     */
	    public static String DIRECTORY_NOTIFICATIONS = "Notifications";
	    
	    /**
	     * Standard directory in which to place pictures that are available to
	     * the user.  Note that this is primarily a convention for the top-level
	     * public directory, as the media scanner will find and collect pictures
	     * in any directory.
	     */
	    public static String DIRECTORY_PICTURES = "Pictures";
	    
	    /**
	     * Standard directory in which to place movies that are available to
	     * the user.  Note that this is primarily a convention for the top-level
	     * public directory, as the media scanner will find and collect movies
	     * in any directory.
	     */
	    public static String DIRECTORY_MOVIES = "Movies";
	    
	    /**
	     * Standard directory in which to place files that have been downloaded by
	     * the user.  Note that this is primarily a convention for the top-level
	     * public directory, you are free to download files anywhere in your own
	     * private directories.  Also note that though the constant here is
	     * named DIRECTORY_DOWNLOADS (plural), the actual file name is non-plural for
	     * backwards compatibility reasons.
	     */
	    public static String DIRECTORY_DOWNLOADS = "Download";
	    
	    /**
	     * The traditional location for pictures and videos when mounting the
	     * device as a camera.  Note that this is primarily a convention for the
	     * top-level public directory, as this convention makes no sense elsewhere.
	     */
	    public static String DIRECTORY_DCIM = "DCIM";
	    	    
	/**
     * Get a top-level public external storage directory for placing files of
     * a particular type.  This is where the user will typically place and
     * manage their own files, so you should be careful about what you put here
     * to ensure you don't erase their files or get in the way of their own
     * organization.
     * 
     * <p>Here is an example of typical code to manipulate a picture on
     * the public external storage:</p>
     * 
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * public_picture}
     * 
     * @param type The type of storage directory to return.  Should be one of
     * {@link #DIRECTORY_MUSIC}, {@link #DIRECTORY_PODCASTS},
     * {@link #DIRECTORY_RINGTONES}, {@link #DIRECTORY_ALARMS},
     * {@link #DIRECTORY_NOTIFICATIONS}, {@link #DIRECTORY_PICTURES},
     * {@link #DIRECTORY_MOVIES}, {@link #DIRECTORY_DOWNLOADS}, or
     * {@link #DIRECTORY_DCIM}.  May not be null.
     * 
     * @return Returns the File path for the directory.  Note that this
     * directory may not yet exist, so you must make sure it exists before
     * using it such as with {@link File#mkdirs File.mkdirs()}.
     */
	@DSModeled(DSC.SPEC)
    public static File getExternalStoragePublicDirectory(String type) {
        return new File("mnt/sdcard");
    }
}
