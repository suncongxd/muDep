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

import droidsafe.annotations.DSC;
import droidsafe.annotations.DSModeled;

public class Canvas {
	
	private Bitmap  mBitmap;
	
	@DSModeled(value = DSC.SAFE)
	public Canvas() {
		//Do Nothing
	}
	
	@DSModeled(DSC.SAFE)
	public void drawLine(float startX, float startY, float stopX, float stopY,
            Paint paint) {
		/*
		 * This method calls a native method that draws to a native canvas which
		 * is represented by the mNativeCanvas (a pointer/handle) value in the SDK 
		 * implementation the call will modify the bitmap managed by the native code.
		 * 
		 * DSFIXME:  Technically the underlying bitmap will be changed 
		 * (represented by the int mNativeCanvas).  It appears that this
		 * operation happens in real time, so tracking taint is difficult
		 * on the upflow.
		 */
		addTaint(startX);
		addTaint(startY);
		addTaint(stopX);
		addTaint(stopY);
		addTaint(paint.getTaint());
	}
	
	@DSModeled(DSC.SAFE)
	public void drawColor(int i) {
		addTaint(i);
	}
	
	@DSModeled(DSC.SAFE)
	public int save(int i) {
		addTaint(i);
		return getTaintInt();
	}
	
	@DSModeled(value = DSC.SAFE)
	public int getDensity() {
		return getTaintInt();
		//return mDensity;
    }
	
	@DSModeled(value = DSC.SAFE)
	public void setDensity(int density) {
		addTaint(density);  //Density is saved both here and in the bitmap
		mBitmap.setDensity(density);  //setDensity will track the taint in the Bitmap object
		/*
        if (mBitmap != null) {
            mBitmap.setDensity(density);
        }
        mDensity = density;
        */
	}
	
	@DSModeled(value = DSC.SAFE)
	public void setBitmap(Bitmap bitmap) {
		addTaint(bitmap.getTaint());
		addTaint(bitmap.getDensity()); //getDensity will return a tainted value
		/*
        if (isHardwareAccelerated()) {
            throw new RuntimeException("Can't set a bitmap device on a GL canvas");
        }

        int pointer = 0;
        if (bitmap != null) {
            if (!bitmap.isMutable()) {
                throw new IllegalStateException();
            }
            throwIfRecycled(bitmap);
            mDensity = bitmap.mDensity;
            pointer = bitmap.ni();
        }

        native_setBitmap(mNativeCanvas, pointer);
        mBitmap = bitmap;
        */
    }
	
	@DSModeled(DSC.SAFE)
    public void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) {
		addTaint(bitmap.getTaint());
		addTaint(left);
		addTaint(top);
		addTaint(paint.getTaint());
		
		/*
        throwIfRecycled(bitmap);
        native_drawBitmap(mNativeCanvas, bitmap.ni(), left, top,
                paint != null ? paint.mNativePaint : 0, mDensity, mScreenDensity,
                bitmap.mDensity);
        */
    }
	
	/**
     * Draw the specified oval using the specified paint. The oval will be
     * filled or framed based on the Style in the paint.
     *
     * @param oval The rectangle bounds of the oval to be drawn
     */
	@DSModeled(DSC.SAFE)
    public void drawOval(RectF oval, Paint paint) {
		addTaint(oval.getTaint());
		addTaint(paint.getTaint());
    	/* GITI DSModeled
        if (oval == null) {
            throw new NullPointerException();
        }
        native_drawOval(mNativeCanvas, oval, paint.mNativePaint);
        */
    }
	
	@DSModeled(DSC.SAFE)
    public void drawPath(Path path, Paint paint) {
		addTaint(path.getTaint());
		addTaint(paint.getTaint());
        /*
         native_drawPath(mNativeCanvas, path.ni(), paint.mNativePaint);
        */
    }
	
	//DSFIXME - Do not think this method needs to have any implementation, but need
	// 			to verify.
	@DSModeled(DSC.SAFE)
    public void restore() {
		
	}
	
	@DSModeled(DSC.SAFE)
	public void rotate(float degrees) {
		addTaint(degrees);
	}
	
	@DSModeled(DSC.SAFE)
	public void scale(float sx, float sy) {
		addTaint(sx);
		addTaint(sy);
	}
	
	@DSModeled(DSC.SAFE)
	public void translate(float dx, float dy) {
		addTaint(dx);
		addTaint(dy);
	}
	
	@DSModeled(DSC.SAFE)
	public void concat(Matrix matrix) {
		addTaint(matrix.native_instance);
    }
	
	@DSModeled(DSC.SAFE)
	public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
		addTaint(bitmap.getTaint());
		addTaint(src.getTaint());
		addTaint(dst.getTaint());
		addTaint(paint.getTaint());
    }
	
}
