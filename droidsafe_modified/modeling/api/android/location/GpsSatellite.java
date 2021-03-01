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


package android.location;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public final class GpsSatellite {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.897 -0500", hash_original_field = "ED254D523CA59D4B79795578544CDA27", hash_generated_field = "ED254D523CA59D4B79795578544CDA27")

    boolean mValid;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.899 -0500", hash_original_field = "1F3B5AD166020F5DF09464A3271AAA66", hash_generated_field = "1F3B5AD166020F5DF09464A3271AAA66")

    boolean mHasEphemeris;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.901 -0500", hash_original_field = "C3FF9F0F1710360264B3429C92C2C819", hash_generated_field = "C3FF9F0F1710360264B3429C92C2C819")

    boolean mHasAlmanac;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.904 -0500", hash_original_field = "63D621938B69E0E166A0ECC00B52A7F3", hash_generated_field = "63D621938B69E0E166A0ECC00B52A7F3")

    boolean mUsedInFix;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.906 -0500", hash_original_field = "1CDF0C01817EB541ECDA40BE76A880FD", hash_generated_field = "1CDF0C01817EB541ECDA40BE76A880FD")

    int mPrn;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.908 -0500", hash_original_field = "C91061B0C817384B77DAE63F2FE6ECD1", hash_generated_field = "C91061B0C817384B77DAE63F2FE6ECD1")

    float mSnr;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.910 -0500", hash_original_field = "3AD7268F8E4AA2FD03CE7A30AF454A89", hash_generated_field = "3AD7268F8E4AA2FD03CE7A30AF454A89")

    float mElevation;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.912 -0500", hash_original_field = "77AAD50693AA57F0F2E57E4972ADD329", hash_generated_field = "77AAD50693AA57F0F2E57E4972ADD329")

    float mAzimuth;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.914 -0500", hash_original_method = "8573EDE4D6155BE2F87E9990A729AEDD", hash_generated_method = "8573EDE4D6155BE2F87E9990A729AEDD")
    
GpsSatellite(int prn) {
        mPrn = prn;
    }

    /**
     * Used by {@link LocationManager#getGpsStatus} to copy LocationManager's
     * cached GpsStatus instance to the client's copy.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.916 -0500", hash_original_method = "6104464C0A9B407CFF88F943228D0F31", hash_generated_method = "6104464C0A9B407CFF88F943228D0F31")
    
void setStatus(GpsSatellite satellite) {
        mValid = satellite.mValid;
        mHasEphemeris = satellite.mHasEphemeris;
        mHasAlmanac = satellite.mHasAlmanac;
        mUsedInFix = satellite.mUsedInFix;
        mSnr = satellite.mSnr;
        mElevation = satellite.mElevation;
        mAzimuth = satellite.mAzimuth;
    }

    /**
     * Returns the PRN (pseudo-random number) for the satellite.
     *
     * @return PRN number
     */
    @DSSource({DSSourceKind.LOCATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.918 -0500", hash_original_method = "1CFB1A0ACEFDBC53538E3DE39E0274F8", hash_generated_method = "9D12234AEAD19A1636D5EE376830E3B5")
    
public int getPrn() {
        return mPrn;
    }

    /**
     * Returns the signal to noise ratio for the satellite.
     *
     * @return the signal to noise ratio
     */
    @DSSource({DSSourceKind.LOCATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.920 -0500", hash_original_method = "86375E5795460EBC5BAEFE7343FFC8C2", hash_generated_method = "615F32EE6FC6C9BC6A26FC500FAA152C")
    
public float getSnr() {
        return mSnr;
    }

    /**
     * Returns the elevation of the satellite in degrees.
     * The elevation can vary between 0 and 90.
     *
     * @return the elevation in degrees
     */
    @DSSource({DSSourceKind.LOCATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.923 -0500", hash_original_method = "45660D841120F9B89BAE31417734A703", hash_generated_method = "EDEF84553F172FBB0C214A1401CBF9F7")
    
public float getElevation() {
        return mElevation;
    }

    /**
     * Returns the azimuth of the satellite in degrees.
     * The azimuth can vary between 0 and 360.
     *
     * @return the azimuth in degrees
     */
    @DSSource({DSSourceKind.LOCATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.925 -0500", hash_original_method = "F306C300B6B496BB95E6F8AB34665AA0", hash_generated_method = "B4727536B9AA9573E0538EA21A8129DC")
    
public float getAzimuth() {
        return mAzimuth;
    }

    /**
     * Returns true if the GPS engine has ephemeris data for the satellite.
     *
     * @return true if the satellite has ephemeris data
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.927 -0500", hash_original_method = "A2DF67CCEC020DAA7F7D1228AFE48AE4", hash_generated_method = "A5A8B841741A4AB961D7D9F00DC863C3")
    
public boolean hasEphemeris() {
        return mHasEphemeris;
    }

    /**
     * Returns true if the GPS engine has almanac data for the satellite.
     *
     * @return true if the satellite has almanac data
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.930 -0500", hash_original_method = "6F26EA9283182B6EDEDBB0B351568D69", hash_generated_method = "F662A6A65B44702E63645DCDC7DA2C8A")
    
public boolean hasAlmanac() {
        return mHasAlmanac;
    }

    /**
     * Returns true if the satellite was used by the GPS engine when
     * calculating the most recent GPS fix.
     *
     * @return true if the satellite was used to compute the most recent fix.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:01.932 -0500", hash_original_method = "D2570999F2D51C5CE871F64BF39BACFE", hash_generated_method = "D909EAFDB0519CD702FFA646FE63378F")
    
public boolean usedInFix() {
        return mUsedInFix;
    }
    
}

