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


package android.content.pm;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.os.Parcel;
import android.os.Parcelable;

public class ConfigurationInfo implements Parcelable {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.428 -0500", hash_original_field = "8BAD29BF4DC2318B41BFD38B92B9A3FB", hash_generated_field = "C64F87CC0E25666997582EEC8768F819")

    public static final int INPUT_FEATURE_HARD_KEYBOARD = 0x00000001;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.430 -0500", hash_original_field = "5DBF4C718AC5FFA43FD010DF87CC75A2", hash_generated_field = "5BEB66D3756A889C64CBD093D7DAFCC1")

    public static final int INPUT_FEATURE_FIVE_WAY_NAV = 0x00000002;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.435 -0500", hash_original_field = "F48EAB296ADF6CE662F7CDF5F130714D", hash_generated_field = "783C36C8508F1DD83B69876DC88244AC")

    public static final int GL_ES_VERSION_UNDEFINED = 0;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:03.416 -0400", hash_original_field = "858991FA57C439B05BBDC3DCF5C48F1C", hash_generated_field = "B0DCE505E982797116EDED118468DA14")

    public static final Creator<ConfigurationInfo> CREATOR =
        new Creator<ConfigurationInfo>() {
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.451 -0500", hash_original_method = "55BBC101BB2A26593D4442BBB1A17284", hash_generated_method = "E39D66EFDDD196D056209DCBCE6FBA80")
        
public ConfigurationInfo createFromParcel(Parcel source) {
            return new ConfigurationInfo(source);
        }
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.453 -0500", hash_original_method = "0326D89C816AA8DD712BD61E197104C9", hash_generated_method = "6582CE0B8051D23D1A351B16042CB7BB")
        
public ConfigurationInfo[] newArray(int size) {
            return new ConfigurationInfo[size];
        }
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.421 -0500", hash_original_field = "13B6A9CE6603C27AC2B3120301AF6F87", hash_generated_field = "520175E5479B41A42F01692389B0DBB8")

    public int reqTouchScreen;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.423 -0500", hash_original_field = "DF11CDBD2F4A37719B76F0DD51F7AFFC", hash_generated_field = "B7B56BFCE46D9BEDEE60FABBDC0BB54D")

    public int reqKeyboardType;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.425 -0500", hash_original_field = "AD3D2A3E054CABE82DF570BE1FD8CBBE", hash_generated_field = "AEA9ADF73BD257E98D85F51F5C11417B")

    public int reqNavigation;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.432 -0500", hash_original_field = "5D45849A263E3430FCFF8C682FF9DEF1", hash_generated_field = "EF985035373431B1AD5A3E161E098B88")

    public int reqInputFeatures = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.437 -0500", hash_original_field = "6AF15FFF05CBAB25A3FF6D68BA3FAAE6", hash_generated_field = "D9CA7B199E4287467AAA0CAF1003E688")

    public int reqGlEsVersion;

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.439 -0500", hash_original_method = "6A0CBEBA9367A4C86820E8B74F6CB393", hash_generated_method = "3664BCA479C3BA8728AC189B5CF4B2E1")
    
public ConfigurationInfo() {
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.442 -0500", hash_original_method = "7048090D37568420C37729B6120A9155", hash_generated_method = "3831911791DDFE14418234CE378EE061")
    
public ConfigurationInfo(ConfigurationInfo orig) {
        reqTouchScreen = orig.reqTouchScreen;
        reqKeyboardType = orig.reqKeyboardType;
        reqNavigation = orig.reqNavigation;
        reqInputFeatures = orig.reqInputFeatures;
        reqGlEsVersion = orig.reqGlEsVersion;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.459 -0500", hash_original_method = "7C700DBE05496C6AB562D5FCB315B327", hash_generated_method = "D6E80053319783E448991CF7F142DFD3")
    
private ConfigurationInfo(Parcel source) {
        reqTouchScreen = source.readInt();
        reqKeyboardType = source.readInt();
        reqNavigation = source.readInt();
        reqInputFeatures = source.readInt();
        reqGlEsVersion = source.readInt();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.444 -0500", hash_original_method = "DF17C53C5839729CA11877C3DC3AE571", hash_generated_method = "D166D9D7865D5462FC950CACCF7F8007")
    
public String toString() {
        return "ConfigurationInfo{"
            + Integer.toHexString(System.identityHashCode(this))
            + " touchscreen = " + reqTouchScreen
            + " inputMethod = " + reqKeyboardType
            + " navigation = " + reqNavigation
            + " reqInputFeatures = " + reqInputFeatures
            + " reqGlEsVersion = " + reqGlEsVersion + "}";
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.446 -0500", hash_original_method = "00F8174F9E89D0C972FA6D3F19742382", hash_generated_method = "D90463461B2A94FF94D13FDF69BB80C9")
    
public int describeContents() {
        return 0;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.448 -0500", hash_original_method = "93E63A9EAD498422955671B9C038ABAF", hash_generated_method = "94AA07160E05C77063C474EED9DF2579")
    
public void writeToParcel(Parcel dest, int parcelableFlags) {
        dest.writeInt(reqTouchScreen);
        dest.writeInt(reqKeyboardType);
        dest.writeInt(reqNavigation);
        dest.writeInt(reqInputFeatures);
        dest.writeInt(reqGlEsVersion);
    }

    /**
     * This method extracts the major and minor version of reqGLEsVersion attribute
     * and returns it as a string. Say reqGlEsVersion value of 0x00010002 is returned
     * as 1.2
     * @return String representation of the reqGlEsVersion attribute
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:45.461 -0500", hash_original_method = "6B16AB9E297007D3C91B331171401C71", hash_generated_method = "303984A3946A6AB415F5805F75FB0A51")
    
public String getGlEsVersion() {
        int major = ((reqGlEsVersion & 0xffff0000) >> 16);
        int minor = reqGlEsVersion & 0x0000ffff;
        return String.valueOf(major)+"."+String.valueOf(minor);
    }
    // orphaned legacy method
    public ConfigurationInfo createFromParcel(Parcel source) {
            return new ConfigurationInfo(source);
        }
    
    // orphaned legacy method
    public ConfigurationInfo[] newArray(int size) {
            return new ConfigurationInfo[size];
        }
    
}

