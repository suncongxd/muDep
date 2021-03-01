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


package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSModeled;
/*
import android.util.DisplayMetrics;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
*/

public final class Bitmap implements Parcelable {
	
	@DSModeled(DSC.SAFE)
	public Bitmap() {
		super();
		//Doesn't exist in the real class but was showing up in specdump
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	@DSModeled(value = DSC.SAFE)
	public void setDensity(int density) {
		addTaint(density);
        //mDensity = density;
    }
	
	@DSModeled(value = DSC.SAFE)
	public int getDensity() {
		return getTaintInt();
        //return mDensity;
    }
	
	public enum Config {
        // these native values must match up with the enum in SkBitmap.h

        /**
         * Each pixel is stored as a single translucency (alpha) channel.
         * This is very useful to efficiently store masks for instance.
         * No color information is stored.
         * With this configuration, each pixel requires 1 byte of memory.
         */
        ALPHA_8     (2),

        /**
         * Each pixel is stored on 2 bytes and only the RGB channels are
         * encoded: red is stored with 5 bits of precision (32 possible
         * values), green is stored with 6 bits of precision (64 possible
         * values) and blue is stored with 5 bits of precision.
         * 
         * This configuration can produce slight visual artifacts depending
         * on the configuration of the source. For instance, without
         * dithering, the result might show a greenish tint. To get better
         * results dithering should be applied.
         * 
         * This configuration may be useful when using opaque bitmaps
         * that do not require high color fidelity.
         */
        RGB_565     (4),

        /**
         * Each pixel is stored on 2 bytes. The three RGB color channels
         * and the alpha channel (translucency) are stored with a 4 bits
         * precision (16 possible values.)
         * 
         * This configuration is mostly useful if the application needs
         * to store translucency information but also needs to save
         * memory.
         * 
         * It is recommended to use {@link #ARGB_8888} instead of this
         * configuration.
         * 
         * @deprecated Because of the poor quality of this configuration,
         *             it is advised to use {@link #ARGB_8888} instead.
         */
        @Deprecated
        ARGB_4444   (5),

        /**
         * Each pixel is stored on 4 bytes. Each channel (RGB and alpha
         * for translucency) is stored with 8 bits of precision (256
         * possible values.)
         * 
         * This configuration is very flexible and offers the best
         * quality. It should be used whenever possible.
         */
        ARGB_8888   (6);

        final int nativeInt;

        @SuppressWarnings({"deprecation"})
        private static Config sConfigs[] = {
            null, null, ALPHA_8, null, RGB_565, ARGB_4444, ARGB_8888
        };
        
        @DSModeled(DSC.SAFE)
        Config(int ni) {
            this.nativeInt = ni;
        }

        static Config nativeToConfig(int ni) {
            return sConfigs[ni];
        }
    }
	
	@DSModeled(DSC.SAFE)
	private void setHeight(int height) {
		//Synthetic method in order to track height taints, which are managed in native code
		addTaint(height);
	}
	
	@DSModeled(DSC.SAFE)
	private int getHeight() {
		//Synthetic method in order to track height taints, which are managed in native code
		return getTaintInt();
	}
	
	@DSModeled(DSC.SAFE)
	private void setWidth(int width) {
		//Synthetic method in order to track width taints, which are managed in native code
		addTaint(width);
	}
	
	@DSModeled(DSC.SAFE)
	private int getWidth() {
		//Synthetic method in order to track width taints, which are managed in native code
		return getTaintInt();
	}
	
	@DSModeled(DSC.SAFE)
	private void setHasAlpha(boolean hasAlpha) {
		//Synthetic method in order to track hasAlpha taints, which are managed in native code
		addTaint(hasAlpha);
	}
	
	@DSModeled(DSC.SAFE)
	private boolean hasAlpha() {
		//Previous called into native code.  Since we are tracking the taint in, we can track it out
		return getTaintBoolean();
	}
	
	@DSModeled(DSC.SAFE)
	private static Bitmap createBitmap(int width, int height, Config config, boolean hasAlpha) {
        Bitmap bm = new Bitmap();
        bm.setHeight(height);
        bm.setWidth(width);
        bm.setHasAlpha(hasAlpha);
        /*
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width and height must be > 0");
        }
        Bitmap bm = nativeCreate(null, 0, width, width, height, config.nativeInt, true);
        if (config == Config.ARGB_8888 && !hasAlpha) {
            nativeErase(bm.mNativeBitmap, 0xff000000);
            nativeSetHasAlpha(bm.mNativeBitmap, hasAlpha);
        } else {
            // No need to initialize it to zeroes; it is backed by a VM byte array
            // which is by definition preinitialized to all zeroes.
            //
            //nativeErase(bm.mNativeBitmap, 0);
        }
        */
        return bm;
    }
	
	@DSModeled(DSC.SAFE)
	public static Bitmap createBitmap(int width, int height, Config config) {
        return createBitmap(width, height, config, true);
    }
}
