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


package android.graphics;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.os.Parcel;
import android.os.Parcelable;

public class Point implements Parcelable {
    
    public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>() {
        /**
         * Return a new point from the data in the specified parcel.
         */
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.624 -0500", hash_original_method = "B3310D4E2482FAA9B08EEBCB55A9E59A", hash_generated_method = "9B7D670C9DC0EB16FE01142237FB60EC")
        
public Point createFromParcel(Parcel in) {
            Point r = new Point();
            r.readFromParcel(in);
            return r;
        }

        /**
         * Return an array of rectangles of the specified size.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.627 -0500", hash_original_method = "18DD28AAA16190424D7A39669B473895", hash_generated_method = "6F98FA24140B54FC18045E1E12996FA5")
        
public Point[] newArray(int size) {
            return new Point[size];
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.593 -0500", hash_original_field = "3ECDEC351FCE13CBA8A59E38409B3C9E", hash_generated_field = "DC9CDA960147F3D22D20E612AF25CB17")

    public int x;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.595 -0500", hash_original_field = "93D19118DABD6AC5BACF335A34537CEB", hash_generated_field = "2B3105BD07A59FEFE1F1918C5EDA2B54")

    public int y;

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.597 -0500", hash_original_method = "D4552E1315F6D591F9A42261DB9D057B", hash_generated_method = "8FFCC2F5EFA8C2EC3A4BF6EFD301796D")
    
public Point() {}
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public Point(int x, int y){
        addTaint(x);
        addTaint(y);
        droidsafeUpdateMembers();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public Point(Point src){
        addTaint(src.getTaintInt() + src.x + src.y);
        droidsafeUpdateMembers();
        /*
        this.x = src.x;
        this.y = src.y;
        */
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public void set(int x, int y){
        addTaint(x);
        addTaint(y);
        droidsafeUpdateMembers();
        // Original method
        /*
        {
        this.x = x;
        this.y = y;
    }
        */
    }

        @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final void negate(){
    }

    /**
     * Offset the point's coordinates by dx, dy
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.608 -0500", hash_original_method = "2341B98028DF5979D10DDC3712740A20", hash_generated_method = "2AF844617832D70B37734FAC2E663D69")
    
public final void offset(int dx, int dy) {
        addTaint(dx + dy);
        droidsafeUpdateMembers();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final boolean equals(int x, int y){
        return (((x + y + getSumTaintInt())) == 1);
        // Original method
        /*
        {
        return this.x == x && this.y == y;
    }
        */
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @Override public boolean equals(Object o){
        //DSFIXME:  CODE0004: Local variable requires review, uncomment if needed
        //Point p = (Point) o;
        
        if (o instanceof Point) {
            Point p = (Point) o;
            return 1 == (
                    this.getSumTaintInt() +
                    p.getSumTaintInt());
        }
        return false;
        // Original method
        /*
        {
        if (o instanceof Point) {
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        return false;
    }
        */
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @Override public int hashCode(){
        return getSumTaintInt();
        // Original method
        /*
        {
        return x * 32713 + y;
    }
        */
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @Override public String toString(){
        String str = new String();
        str.addTaint(getTaint());
        return str;
        // Original method
        /*
        {
        return "Point(" + x + ", " + y+ ")";
    }
        */
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @Override public int describeContents(){
        return getSumTaintInt();
        // Original method
        /*
        {
        return 0;
    }
        */
    }

    /**
     * Write this point to the specified parcel. To restore a point from
     * a parcel, use readFromParcel()
     * @param out The parcel to write the point's coordinates into
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:26.622 -0500", hash_original_method = "54E86A99E3577FA18F65ED6CED3821E9", hash_generated_method = "B7119B834B06880890B6188EE22DD239")
    
@Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(flags);
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public void readFromParcel(Parcel in){
        addTaint(in.getTaintInt());
        droidsafeUpdateMembers();
        // Original method
        /*
        {
        x = in.readInt();
        y = in.readInt();
    }
        */
    }

    private void droidsafeUpdateMembers() {
        x = getTaintInt();
        y = getTaintInt();
    }
    
    public int getSumTaintInt() {
        return (x + y + getTaintInt());
    }
    
}

