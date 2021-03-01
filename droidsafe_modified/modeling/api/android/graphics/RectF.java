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

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.PrintWriter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.FloatMath;

import com.android.internal.util.FastMath;

public class RectF implements Parcelable {
    
    /**
     * Returns true iff the two specified rectangles intersect. In no event are
     * either of the rectangles modified. To record the intersection,
     * use intersect() or setIntersect().
     *
     * @param a The first rectangle being tested for intersection
     * @param b The second rectangle being tested for intersection
     * @return true iff the two specified rectangles intersect. In no event are
     *              either of the rectangles modified.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.926 -0500", hash_original_method = "1FAEC0442F27706D6D5F3E840B971AB4", hash_generated_method = "B7ECD36105B38ABAEED5381ABDCCD9B3")
    
public static boolean intersects(RectF a, RectF b) {
        return (((a.getTaintInt() + b.getTaintInt())) == 1);
    }
    
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:32.336 -0400", hash_original_field = "5E7201C60E05C026DD3550B3101B80A5", hash_generated_field = "C46FA4AE8D434E2146AE8F7264B82507")

    public static final Parcelable.Creator<RectF> CREATOR = new Parcelable.Creator<RectF>() {
        /**
         * Return a new rectangle from the data in the specified parcel.
         */
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.947 -0500", hash_original_method = "A6A0C6EE524221145489C3D66C6A94BD", hash_generated_method = "C45F9601A0A982039FA7B7D5119617E1")
        
public RectF createFromParcel(Parcel in) {
            RectF r = new RectF();
            r.readFromParcel(in);
            return r;
        }
        
        /**
         * Return an array of rectangles of the specified size.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.950 -0500", hash_original_method = "889447B8D00C814B8B32F8DD32D6D4FC", hash_generated_method = "589C283F725F652F72E613A84FD494F5")
        
public RectF[] newArray(int size) {
            return new RectF[size];
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.856 -0500", hash_original_field = "737E6C75B1E37953492E1AC805552391", hash_generated_field = "F463FF95349F5FFDEBFA1888AA372D7F")

    public float left;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.858 -0500", hash_original_field = "5A30F5F1FC23335B87123877DB67623D", hash_generated_field = "F9D622C84E97B0C2CBDBCB618909D322")

    public float top;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.860 -0500", hash_original_field = "317F6F0068CAFA5F2BF737FAD30C87B4", hash_generated_field = "4A45372E50F3A0CA9B8E80524FE9837C")

    public float right;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.862 -0500", hash_original_field = "850F91B179F4198EC71F4EA92A267B16", hash_generated_field = "3601A2C074D2F75BE50976E0F9B684C6")

    public float bottom;
    
    /**
     * Create a new empty RectF. All coordinates are initialized to 0.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.864 -0500", hash_original_method = "D61F570F41294C50B794115B72F287CF", hash_generated_method = "0C35021DCF1E2EEAC4ACC137BA6B97DB")
    
public RectF() {}
    
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    public RectF(float left, float top, float right, float bottom) {    
    	this();
    	addTaintLocal(left);
    	addTaintLocal(top);
    	addTaintLocal(right);
    	addTaintLocal(bottom);
    }
    
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    public RectF(RectF r) { 
    	this();
    	addTaintLocal(r.getTaintInt());
    }
    
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    public RectF(Rect r) {
    	this();
    	addTaintLocal(r.getTaintInt());
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public String toString() {
    	String str = new String();
    	return str;        
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public String toShortString() {    	
    	String str = new String("");
        return str;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public String toShortString(StringBuilder sb) {    	
    	sb.append(1);
    	return sb.toString();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public void printShortString(PrintWriter pw) {
    	char c = (char)getSumTaintInt();
    	pw.append(c);
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final boolean isEmpty() {
        return getTaintBoolean();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final float width() {
    	return getTaintInt();
        //return 0;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final float height() {
    	return getTaintInt();
        //return 0;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final float centerX() {
        return getTaintFloat();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final float centerY() {
        return getTaintFloat();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public void setEmpty() {
    	//addTaint(0.getTaint());	//No need to track , just an initializer type function setting things to 0   
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public void set(float left, float top, float right, float bottom) {
    	addTaintLocal(left);
    	addTaintLocal(top);
    	addTaintLocal(right);
    	addTaintLocal(bottom);  
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    public void set(RectF src) {
    	addTaintLocal(src.getTaintInt());
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    public void set(Rect src) {
        addTaintLocal(src.getTaintInt());
    }

    /**
     * Offset the rectangle by adding dx to its left and right coordinates, and
     * adding dy to its top and bottom coordinates.
     *
     * @param dx The amount to add to the rectangle's left and right coordinates
     * @param dy The amount to add to the rectangle's top and bottom coordinates
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.903 -0500", hash_original_method = "987CF6DAC95BF2616996F2B7580ECF09", hash_generated_method = "209DD68EA03C32B026C6A69EF4BF7652")
    
    public void offset(float dx, float dy) {
        addTaintLocal(dx + dy);
    }

    /**
     * Offset the rectangle to a specific (left, top) position,
     * keeping its width and height the same.
     *
     * @param newLeft   The new "left" coordinate for the rectangle
     * @param newTop    The new "top" coordinate for the rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.906 -0500", hash_original_method = "17E8F6A0DA6BEF949708E45EF28FBDEC", hash_generated_method = "072395F153C45E5EB171F20F34EFAEBF")
    
public void offsetTo(float newLeft, float newTop) {
        addTaintLocal(newLeft + newTop);
    }
    
    /**
     * Inset the rectangle by (dx,dy). If dx is positive, then the sides are
     * moved inwards, making the rectangle narrower. If dx is negative, then the
     * sides are moved outwards, making the rectangle wider. The same holds true
     * for dy and the top and bottom.
     *
     * @param dx The amount to add(subtract) from the rectangle's left(right)
     * @param dy The amount to add(subtract) from the rectangle's top(bottom)
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.908 -0500", hash_original_method = "4ED8FEC7EC4817417CFF539CEB8CD3D5", hash_generated_method = "5C4D73D90534F267ACD4AF169BAC2F86")
    
public void inset(float dx, float dy) {
        addTaintLocal(dx + dy);
    }

    /**
     * Returns true if (x,y) is inside the rectangle. The left and top are
     * considered to be inside, while the right and bottom are not. This means
     * that for a x,y to be contained: left <= x < right and top <= y < bottom.
     * An empty rectangle never contains any point.
     *
     * @param x The X coordinate of the point being tested for containment
     * @param y The Y coordinate of the point being tested for containment
     * @return true iff (x,y) are contained by the rectangle, where containment
     *              means left <= x < right and top <= y < bottom
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.910 -0500", hash_original_method = "7C65F21BA78E9C886CB99F3A821D7FC2", hash_generated_method = "478A0BFDE6FD19E13A25783CBA08CD39")
    
public boolean contains(float x, float y) {
        return (((x + y + getTaintInt())) == 1);
    }
    
    /**
     * Returns true iff the 4 specified sides of a rectangle are inside or equal
     * to this rectangle. i.e. is this rectangle a superset of the specified
     * rectangle. An empty rectangle never contains another rectangle.
     *
     * @param left The left side of the rectangle being tested for containment
     * @param top The top of the rectangle being tested for containment
     * @param right The right side of the rectangle being tested for containment
     * @param bottom The bottom of the rectangle being tested for containment
     * @return true iff the the 4 specified sides of a rectangle are inside or
     *              equal to this rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.912 -0500", hash_original_method = "5736B1A89F8587C3C51B72E5484012E7", hash_generated_method = "B6FF23F3FBB84652AFC78CC30408C7FF")
    
public boolean contains(float left, float top, float right, float bottom) {
                // check for empty first
        return (((left + top + right + bottom + getTaintInt())) == 1);
    }
    
    /**
     * Returns true iff the specified rectangle r is inside or equal to this
     * rectangle. An empty rectangle never contains another rectangle.
     *
     * @param r The rectangle being tested for containment.
     * @return true iff the specified rectangle r is inside or equal to this
     *              rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.914 -0500", hash_original_method = "89862D8BCB63DE9E9B3F12DF7005EDAF", hash_generated_method = "F4522FD440D4239612D3807D7093E83F")
    
public boolean contains(RectF r) {
                // check for empty first
        return (((r.getTaintInt() + getTaintInt())) == 1);
    }
    
    /**
     * If the rectangle specified by left,top,right,bottom intersects this
     * rectangle, return true and set this rectangle to that intersection,
     * otherwise return false and do not change this rectangle. No check is
     * performed to see if either rectangle is empty. Note: To just test for
     * intersection, use intersects()
     *
     * @param left The left side of the rectangle being intersected with this
     *             rectangle
     * @param top The top of the rectangle being intersected with this rectangle
     * @param right The right side of the rectangle being intersected with this
     *              rectangle.
     * @param bottom The bottom of the rectangle being intersected with this
     *             rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     *              (and this rectangle is then set to that intersection) else
     *              return false and do not change this rectangle.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.917 -0500", hash_original_method = "B665C4D2FA87DEF248CEB5906A538992", hash_generated_method = "2D0C490DF28548B6DC59C756C6E5ED0F")
    
public boolean intersect(float left, float top, float right, float bottom) {
    return (((left + top + right + bottom + getTaintInt())) == 1);
    /*
        if (this.left < right && left < this.right
                && this.top < bottom && top < this.bottom) {
            if (this.left < left) {
                this.left = left;
            }
            if (this.top < top) {
                this.top = top;
            }
            if (this.right > right) {
                this.right = right;
            }
            if (this.bottom > bottom) {
                this.bottom = bottom;
            }
            return true;
        }
        return false;
        */
    }
    
    /**
     * If the specified rectangle intersects this rectangle, return true and set
     * this rectangle to that intersection, otherwise return false and do not
     * change this rectangle. No check is performed to see if either rectangle
     * is empty. To just test for intersection, use intersects()
     *
     * @param r The rectangle being intersected with this rectangle.
     * @return true if the specified rectangle and this rectangle intersect
     *              (and this rectangle is then set to that intersection) else
     *              return false and do not change this rectangle.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.919 -0500", hash_original_method = "F1511D7F30AFB03A76430BC5FB3647DC", hash_generated_method = "CFA0BCF83D702859DF36BA49575B2AC3")
    
public boolean intersect(RectF r) {
        return intersect(r.left, r.top, r.right, r.bottom);
    }
    
    /**
     * If rectangles a and b intersect, return true and set this rectangle to
     * that intersection, otherwise return false and do not change this
     * rectangle. No check is performed to see if either rectangle is empty.
     * To just test for intersection, use intersects()
     *
     * @param a The first rectangle being intersected with
     * @param b The second rectangle being intersected with
     * @return true iff the two specified rectangles intersect. If they do, set
     *              this rectangle to that intersection. If they do not, return
     *              false and do not change this rectangle.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.921 -0500", hash_original_method = "798EF6BEBDE27EE5C0F4552CB6C0BADF", hash_generated_method = "2AF3213ACBAE2E61B84DD66231F68225")
    
public boolean setIntersect(RectF a, RectF b) {
    /*
        if (a.left < b.right && b.left < a.right
                && a.top < b.bottom && b.top < a.bottom) {
            left = Math.max(a.left, b.left);
            top = Math.max(a.top, b.top);
            right = Math.min(a.right, b.right);
            bottom = Math.min(a.bottom, b.bottom);
            return true;
        }
        return false;
    */
        addTaintLocal(a.getTaintInt() + b.getTaintInt());
        return getTaintBoolean();
    }
    
    /**
     * Returns true if this rectangle intersects the specified rectangle.
     * In no event is this rectangle modified. No check is performed to see
     * if either rectangle is empty. To record the intersection, use intersect()
     * or setIntersect().
     *
     * @param left The left side of the rectangle being tested for intersection
     * @param top The top of the rectangle being tested for intersection
     * @param right The right side of the rectangle being tested for
     *              intersection
     * @param bottom The bottom of the rectangle being tested for intersection
     * @return true iff the specified rectangle intersects this rectangle. In
     *              no event is this rectangle modified.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.923 -0500", hash_original_method = "7636A1ABF3E5D698C7B1F582C19267F0", hash_generated_method = "9F2716432E7B0E6F949B4DA4024FDAFA")
    
public boolean intersects(float left, float top, float right,
                              float bottom) {
        /*
        return this.left < right && left < this.right
                && this.top < bottom && top < this.bottom;
        */
        return (((left + top + right + bottom + getTaintInt())) == 1);
    }
    
    /**
     * Set the dst integer Rect by rounding this rectangle's coordinates
     * to their nearest integer values.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.929 -0500", hash_original_method = "1D9E886326ACAAED42A7320F81B83BDE", hash_generated_method = "82AA82F3CD16F5D859C1EB06D94C7EA9")
    
public void round(Rect dst) {
        dst.set(FastMath.round(left), FastMath.round(top),
                FastMath.round(right), FastMath.round(bottom));
    }

    /**
     * Set the dst integer Rect by rounding "out" this rectangle, choosing the
     * floor of top and left, and the ceiling of right and bottom.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.931 -0500", hash_original_method = "5165AD4866DE272C811B07E7C1D571CC", hash_generated_method = "A3A32B017315656FB7F5300165C158CF")
    
public void roundOut(Rect dst) {
        dst.set((int) FloatMath.floor(left), (int) FloatMath.floor(top),
                (int) FloatMath.ceil(right), (int) FloatMath.ceil(bottom));
    }

    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     *
     * @param left The left edge being unioned with this rectangle
     * @param top The top edge being unioned with this rectangle
     * @param right The right edge being unioned with this rectangle
     * @param bottom The bottom edge being unioned with this rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.934 -0500", hash_original_method = "4ED17A6A0BD221C6F562FF187EDE0EC1", hash_generated_method = "4C2692EB26E3BEAAD6C7D4E751D89554")
    
public void union(float left, float top, float right, float bottom) {
    /*
        if ((left < right) && (top < bottom)) {
            if ((this.left < this.right) && (this.top < this.bottom)) {
                if (this.left > left)
                    this.left = left;
                if (this.top > top)
                    this.top = top;
                if (this.right < right)
                    this.right = right;
                if (this.bottom < bottom)
                    this.bottom = bottom;
            } else {
                this.left = left;
                this.top = top;
                this.right = right;
                this.bottom = bottom;
            }
        }
    */
    addTaintLocal(left + top + right + bottom);
    }
    
    /**
     * Update this Rect to enclose itself and the specified rectangle. If the
     * specified rectangle is empty, nothing is done. If this rectangle is empty
     * it is set to the specified rectangle.
     *
     * @param r The rectangle being unioned with this rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.936 -0500", hash_original_method = "7333A4F184A018E8DCD665D788936108", hash_generated_method = "DDE001E2C94ED57A95C9B868FAEDFEAD")
    
public void union(RectF r) {
        union(r.left, r.top, r.right, r.bottom);
    }
    
    /**
     * Update this Rect to enclose itself and the [x,y] coordinate. There is no
     * check to see that this rectangle is non-empty.
     *
     * @param x The x coordinate of the point to add to the rectangle
     * @param y The y coordinate of the point to add to the rectangle
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.938 -0500", hash_original_method = "5491E96A191FF9AA58559CF28A27B675", hash_generated_method = "F8C61246652072081D322D7B41B43ABE")
    
public void union(float x, float y) {
    /*
        if (x < left) {
            left = x;
        } else if (x > right) {
            right = x;
        }
        if (y < top) {
            top = y;
        } else if (y > bottom) {
            bottom = y;
        }
      */
        addTaintLocal(x + y);
    }
    
    /**
     * Swap top/bottom or left/right if there are flipped (i.e. left > right
     * and/or top > bottom). This can be called if
     * the edges are computed separately, and may have crossed over each other.
     * If the edges are already correct (i.e. left <= right and top <= bottom)
     * then nothing is done.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.940 -0500", hash_original_method = "5E187609DBD260B5F8E2F7AA44836C46", hash_generated_method = "E1C40BC234F345C5C8DA68129055BA5D")
    
public void sort() {
    /*
        if (left > right) {
            float temp = left;
            left = right;
            right = temp;
        }
        if (top > bottom) {
            float temp = top;
            top = bottom;
            bottom = temp;
        }
    */
    }

    /**
     * Parcelable interface methods
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.942 -0500", hash_original_method = "00F8174F9E89D0C972FA6D3F19742382", hash_generated_method = "D90463461B2A94FF94D13FDF69BB80C9")
    
public int describeContents() {
        return 0;
    }
    
    /**
     * Write this rectangle to the specified parcel. To restore a rectangle from
     * a parcel, use readFromParcel()
     * @param out The parcel to write the rectangle's coordinates into
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.944 -0500", hash_original_method = "5C789B83F9658DFF3AD82091DAFFDD49", hash_generated_method = "1CECFCE84FD52EF63862DC7F8ED424FB")
    
public void writeToParcel(Parcel out, int flags) {
        out.writeFloat(left);
        out.writeFloat(top);
        out.writeFloat(right);
        out.writeFloat(bottom);
        out.addTaint(flags);
    }
    
    /**
     * Set the rectangle's coordinates from the data stored in the specified
     * parcel. To write a rectangle to a parcel, call writeToParcel().
     *
     * @param in The parcel to read the rectangle's coordinates from
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:25.954 -0500", hash_original_method = "BACCEAD3B6EC38250A4D00D9AA0D9F78", hash_generated_method = "32582E56D2E66C5F6A01976E86765F0C")
    
public void readFromParcel(Parcel in) {
        addTaintLocal(in.getTaintInt());
        /*
        left = in.readFloat();
        top = in.readFloat();
        right = in.readFloat();
        bottom = in.readFloat();
        */
    }
    // orphaned legacy method
    
	public final float top() {
		//return getTaintFloat();
		return getSumTaintInt();
	}
    
    // orphaned legacy method
    
	public final float left() {
		//return getTaintFloat();
		return getSumTaintInt();
	}

    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    private void droidsafeUpdateMembers() {
        right = getTaintInt();
        left  = getTaintInt();
        bottom = getTaintInt();
        top    = getTaintInt();
    }
    
    private void addTaintLocal(double t) {
        super.addTaint(t);
        droidsafeUpdateMembers();
    }
    
    private int getSumTaintInt() {
        return (int)(right + left + bottom + top + getTaintInt());
    }
}

