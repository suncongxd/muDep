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
import java.io.PrintWriter;

import android.os.Parcel;
import android.os.Parcelable;
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSModeled;

/**
 * RectF holds four float coordinates for a rectangle. The rectangle is
 * represented by the coordinates of its 4 edges (left, top, right bottom).
 * These fields can be accessed directly. Use width() and height() to retrieve
 * the rectangle's width and height. Note: most methods do not check to see that
 * the coordinates are sorted correctly (i.e. left <= right and top <= bottom).
 */
public class RectF implements Parcelable {
    private float left;
    private float right;
    private float top;
    private float bottom;
    /**
     * Create a new empty RectF. All coordinates are initialized to 0.
     */
    /* 
     * GITI: Empty constructor, should be safe
     */
    @DSModeled(DSC.SAFE)
    public RectF() {
    }

    /**
     * Create a new rectangle with the specified coordinates. Note: no range
     * checking is performed, so the caller must ensure that left <= right and
     * top <= bottom.
     *
     * @param left   The X coordinate of the left side of the rectagle
     * @param top    The Y coordinate of the top of the rectangle
     * @param right  The X coordinate of the right side of the rectagle
     * @param bottom The Y coordinate of the bottom of the rectangle
     */
    /*
     * GITI: only coordinates are set
     */
    @DSModeled(DSC.SAFE)
    public RectF(float left, float top, float right, float bottom) {    
    	this();
    	addTaint(left);
    	addTaint(top);
    	addTaint(right);
    	addTaint(bottom);
    }

    /**
     * Create a new rectangle, initialized with the values in the specified
     * rectangle (which is left unmodified).
     *
     * @param r The rectangle whose coordinates are copied into the new
     *          rectangle.
     */
    @DSModeled(DSC.SAFE)
    public RectF(RectF r) { 
    	this();
    	addTaint(r.getTaint());
    }
    
    @DSModeled(DSC.SAFE)
    public RectF(Rect r) {
    	this();
    	addTaint(r.left);
    	addTaint(r.right);
    	addTaint(r.top);
    	addTaint(r.bottom);
    }

    @DSModeled(DSC.SAFE)
    public String toString() {
    	String str = new String();
    	return str;        
    }

    /**
     * Return a string representation of the rectangle in a compact form.
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public String toShortString() {    	
    	String str = new String("");
        return str;
    }
    
    /**
     * Return a string representation of the rectangle in a compact form.
     * @hide
     */
    /*
     * GITI
     */
    @DSModeled (DSC.SAFE)
    public String toShortString(StringBuilder sb) {    	
    	sb.append(1);
    	return sb.toString();
    }
    
    /**
     * Print short representation to given writer.
     * @hide
     */
    @DSModeled(DSC.SAFE)
    public void printShortString(PrintWriter pw) {
    	char c = 0;
    	pw.append(c);
    }

    /**
     * Returns true if the rectangle is empty (left >= right or top >= bottom)
     */
    @DSModeled(DSC.SAFE)
    public final boolean isEmpty() {
        return false;
    }

    /**
     * @return the rectangle's width. This does not check for a valid rectangle
     * (i.e. left <= right) so the result may be negative.
     */
    @DSModeled(DSC.SAFE)
    public final float width() {
    	return getTaintInt();
        //return 0;
    }

    /**
     * @return the rectangle's height. This does not check for a valid rectangle
     * (i.e. top <= bottom) so the result may be negative.
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public final float height() {
    	return getTaintInt();
        //return 0;
    }

    /**
     * @return the horizontal center of the rectangle. This does not check for
     * a valid rectangle (i.e. left <= right)
     */
    /*
     * GITI:
     */
    @DSModeled(DSC.SAFE)
    public final float centerX() {
        return 0;
    }

    /**
     * @return the vertical center of the rectangle. This does not check for
     * a valid rectangle (i.e. top <= bottom)
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public final float centerY() {
        return 0;
    }
    
    /**
     * Set the rectangle to (0,0,0,0)
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public void setEmpty() {
    	//addTaint(0.getTaint());	//No need to track , just an initializer type function setting things to 0   
    }
    
    /**
     * Set the rectangle's coordinates to the specified values. Note: no range
     * checking is performed, so it is up to the caller to ensure that
     * left <= right and top <= bottom.
     *
     * @param left   The X coordinate of the left side of the rectagle
     * @param top    The Y coordinate of the top of the rectangle
     * @param right  The X coordinate of the right side of the rectagle
     * @param bottom The Y coordinate of the bottom of the rectangle
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public void set(float left, float top, float right, float bottom) {
    	addTaint(left);
    	addTaint(top);
    	addTaint(right);
    	addTaint(bottom);  
    }

    /**
     * Copy the coordinates from src into this rectangle.
     *
     * @param src The rectangle whose coordinates are copied into this
     *           rectangle.
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public void set(RectF src) {
    	addTaint(src.getTaint());
    }
    
    /**
     * Copy the coordinates from src into this rectangle.
     *
     * @param src The rectangle whose coordinates are copied into this
     *           rectangle.
     */
    /*
     * GITI
     */
    @DSModeled(DSC.SAFE)
    public void set(Rect src) {
    	addTaint(src.left);
    	addTaint(src.top);
    	addTaint(src.right);
    	addTaint(src.bottom);
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@DSModeled(DSC.SAFE)
	public final float left() {
		return getTaintFloat();
	}
	
	@DSModeled(DSC.SAFE)
	public final float top() {
		return getTaintFloat();
	}
}
