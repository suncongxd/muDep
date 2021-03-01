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
import android.os.SystemClock;

import droidsafe.helpers.DSUtils;

public class Interpolator {
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeConstructor(int valueCount, int frameCount) {
        return (valueCount + frameCount);
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeDestructor(int native_instance) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeReset(int native_instance, int valueCount, int frameCount) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeSetKeyFrame(int native_instance, int index, int msec, float[] values, float[] blend) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeSetRepeatMirror(int native_instance, float repeatCount, boolean mirror) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeTimeToValues(int native_instance, int msec, float[] values) {
        values[0] = (native_instance + msec);
        return (int)values[0];
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.812 -0500", hash_original_field = "7C821A44BA6C69802A2F40CFF359D196", hash_generated_field = "68C3E9DC71F308640D17475B37A6DDEF")
    
    private int mValueCount;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.814 -0500", hash_original_field = "2B862720623879764FB3C1B9EA11B335", hash_generated_field = "539D32ACC3FFB5021AAD2744A6F8CCC3")

    private int mFrameCount;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.816 -0500", hash_original_field = "1353DF0D3FEF59358BA81F3F4AC59875", hash_generated_field = "7F8F576E4626C874BE9FCD8FF3DA7FA2")

    private  int native_instance;

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.783 -0500", hash_original_method = "19122541958F7FA844B795B7542E8DCA", hash_generated_method = "CE84137946CF56CEAF822F4C91E1CE36")
    
public Interpolator(int valueCount) {
        mValueCount = valueCount;
        mFrameCount = 2;
        native_instance = nativeConstructor(valueCount, 2);
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.785 -0500", hash_original_method = "39AB5A4645DDA75ACBCA394AE8D72CCA", hash_generated_method = "CF536EA48C32003A951CDB3648B64BD8")
    
public Interpolator(int valueCount, int frameCount) {
        mValueCount = valueCount;
        mFrameCount = frameCount;
        native_instance = nativeConstructor(valueCount, frameCount);
    }
    
    /**
     * Reset the Interpolator to have the specified number of values and an
     * implicit keyFrame count of 2 (just a start and end). After this call the
     * values for each keyFrame must be assigned using setKeyFrame().
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.787 -0500", hash_original_method = "DF63005BC1AE5B0B1E3F7DD90D14732F", hash_generated_method = "8F79EB57671A32037C83D3369B7368ED")
    
public void reset(int valueCount) {
        reset(valueCount, 2);
    }
    
    /**
     * Reset the Interpolator to have the specified number of values and
     * keyFrames. After this call the values for each keyFrame must be assigned
     * using setKeyFrame().
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.790 -0500", hash_original_method = "C5023085042082F0979BC336BA173BAC", hash_generated_method = "C3B88D2CC4B9D570E2335D0B2FEE75D3")
    
public void reset(int valueCount, int frameCount) {
        mValueCount = valueCount;
        mFrameCount = frameCount;
        nativeReset(native_instance, valueCount, frameCount);
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.792 -0500", hash_original_method = "89BB77AE086853608E25F99A94348DC0", hash_generated_method = "1CB4F11CFA2991257AEC8A9AE99A491A")
    
public final int getKeyFrameCount() {
        return mFrameCount;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.795 -0500", hash_original_method = "776BE84F4731B9CD4E842330007BED2A", hash_generated_method = "DD489F639A9250C746DDDF3722535E08")
    
public final int getValueCount() {
        return mValueCount;
    }
    
    /**
     * Assign the keyFrame (specified by index) a time value and an array of key
     * values (with an implicity blend array of [0, 0, 1, 1] giving linear
     * transition to the next set of key values).
     * 
     * @param index The index of the key frame to assign
     * @param msec The time (in mililiseconds) for this key frame. Based on the
     *        SystemClock.uptimeMillis() clock
     * @param values Array of values associated with theis key frame
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.798 -0500", hash_original_method = "8B845648DDEC7EC4E813052052B8A4ED", hash_generated_method = "43FF38EF0AB89DB8347AED2F078AC8FA")
    
public void setKeyFrame(int index, int msec, float[] values) {
        setKeyFrame(index, msec, values, null);
    }

    /**
     * Assign the keyFrame (specified by index) a time value and an array of key
     * values and blend array.
     * 
     * @param index The index of the key frame to assign
     * @param msec The time (in mililiseconds) for this key frame. Based on the
     *        SystemClock.uptimeMillis() clock
     * @param values Array of values associated with theis key frame
     * @param blend (may be null) Optional array of 4 blend values
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.800 -0500", hash_original_method = "0A4BD1EDAE26740EE7CE301C3AD23A04", hash_generated_method = "0C62FAFCF086862A06AAF6823A5B7E21")
    
public void setKeyFrame(int index, int msec, float[] values, float[] blend) {
        if (index < 0 || index >= mFrameCount) {
            throw new IndexOutOfBoundsException();
        }
        if (values.length < mValueCount) {
            throw new ArrayStoreException();
        }
        if (blend != null && blend.length < 4) {
            throw new ArrayStoreException();
        }
        nativeSetKeyFrame(native_instance, index, msec, values, blend);
    }
    
    /**
     * Set a repeat count (which may be fractional) for the interpolator, and
     * whether the interpolator should mirror its repeats. The default settings
     * are repeatCount = 1, and mirror = false.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.802 -0500", hash_original_method = "951FAF18C8FD28DC8D0B362372C0DCF6", hash_generated_method = "AE0C836A346A863BC38BB857F2DB94DD")
    
public void setRepeatMirror(float repeatCount, boolean mirror) {
        if (repeatCount >= 0) {
            nativeSetRepeatMirror(native_instance, repeatCount, mirror);
        }
    }

    /**
     * Calls timeToValues(msec, values) with the msec set to now (by calling
     * (int)SystemClock.uptimeMillis().)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.806 -0500", hash_original_method = "BDADB3549C258921F869033B8AFE985D", hash_generated_method = "3B94C02D2A920D43CCB9AED3E5CC456E")
    
public Result timeToValues(float[] values) {
        return timeToValues((int)SystemClock.uptimeMillis(), values);
    }

    /**
     * Given a millisecond time value (msec), return the interpolated values and
     * return whether the specified time was within the range of key times
     * (NORMAL), was before the first key time (FREEZE_START) or after the last
     * key time (FREEZE_END). In any event, computed values are always returned.
     * 
     * @param msec The time (in milliseconds) used to sample into the
     *        Interpolator. Based on the SystemClock.uptimeMillis() clock
     * @param values Where to write the computed values (may be NULL).
     * @return how the values were computed (even if values == null)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.808 -0500", hash_original_method = "B04849E47B71B5E874F7B8DA72F507BD", hash_generated_method = "8D16EEF172DB5C8EF289B80B245CA74A")
    
public Result timeToValues(int msec, float[] values) {
        if (values != null && values.length < mValueCount) {
            throw new ArrayStoreException();
        }
        switch (nativeTimeToValues(native_instance, msec, values)) {
            case 0: return Result.NORMAL;
            case 1: return Result.FREEZE_START;
            default: return Result.FREEZE_END;
        }
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:10.810 -0500", hash_original_method = "CA9745465B207BED8177C430188A7C4B", hash_generated_method = "BE69CE7E8423113E232B1962E161780E")
    
@Override
    protected void finalize() throws Throwable {
        nativeDestructor(native_instance);
    }
    
    public enum Result {
        NORMAL,
        FREEZE_START,
        FREEZE_END
    }
    
}

