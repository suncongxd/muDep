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
import java.io.File;

import android.content.res.AssetManager;

import droidsafe.helpers.DSUtils;

public class Typeface {

    /**
     * Create a typeface object given a family name, and option style information.
     * If null is passed for the name, then the "default" font will be chosen.
     * The resulting typeface object can be queried (getStyle()) to discover what
     * its "real" style characteristics are.
     *
     * @param familyName May be null. The name of the font family.
     * @param style  The style (normal, bold, italic) of the typeface.
     *               e.g. NORMAL, BOLD, ITALIC, BOLD_ITALIC
     * @return The best matching typeface.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.659 -0500", hash_original_method = "17B527703375EC0E18C6C2B14F73239B", hash_generated_method = "978A8D227C75F06C0F72123179F370E8")
    
public static Typeface create(String familyName, int style) {
        return new Typeface(nativeCreate(familyName, style));
    }

    /**
     * Create a typeface object that best matches the specified existing
     * typeface and the specified Style. Use this call if you want to pick a new
     * style from the same family of an existing typeface object. If family is
     * null, this selects from the default font's family.
     *
     * @param family May be null. The name of the existing type face.
     * @param style  The style (normal, bold, italic) of the typeface.
     *               e.g. NORMAL, BOLD, ITALIC, BOLD_ITALIC
     * @return The best matching typeface.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.661 -0500", hash_original_method = "440330E00D63CA8C46C2D2C8DF2C6AC6", hash_generated_method = "6ADE5CB6ADC5D7A39E399D5D4E6783C5")
    
public static Typeface create(Typeface family, int style) {
        int ni = 0;        
        if (family != null) {
            ni = family.native_instance;
        }
        return new Typeface(nativeCreateFromTypeface(ni, style));
    }

    /**
     * Returns one of the default typeface objects, based on the specified style
     *
     * @return the default typeface that corresponds to the style
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.663 -0500", hash_original_method = "B06912E79E41E10797503036CC965799", hash_generated_method = "9D914BF564A8989D727B0640BD894E02")
    
public static Typeface defaultFromStyle(int style) {
        return sDefaults[style];
    }
    
    /**
     * Create a new typeface from the specified font data.
     * @param mgr The application's asset manager
     * @param path  The file name of the font data in the assets directory
     * @return The new typeface.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.666 -0500", hash_original_method = "B7B625088F029DD3EB2D5292E983F6DF", hash_generated_method = "0A54A29A95BB9391FFC250FC66E26A42")
    
public static Typeface createFromAsset(AssetManager mgr, String path) {
        return new Typeface(nativeCreateFromAsset(mgr, path));
    }

    /**
     * Create a new typeface from the specified font file.
     *
     * @param path The path to the font data. 
     * @return The new typeface.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.668 -0500", hash_original_method = "75B14133CB489027D77A424630AF754B", hash_generated_method = "35F35D443F4F1C8CEBFB68A43EE62BD6")
    
public static Typeface createFromFile(File path) {
        return new Typeface(nativeCreateFromFile(path.getAbsolutePath()));
    }

    /**
     * Create a new typeface from the specified font file.
     *
     * @param path The full path to the font data. 
     * @return The new typeface.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.670 -0500", hash_original_method = "41766EA6657E21C45E368553CD429F56", hash_generated_method = "F2ECF31D59A09C57439120926468258C")
    
public static Typeface createFromFile(String path) {
        return new Typeface(nativeCreateFromFile(path));
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeCreate(String familyName, int style) {
        return (familyName.getTaintInt() + style);
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeCreateFromTypeface(int native_instance, int style) {
        return (native_instance + style);
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeUnref(int native_instance) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeGetStyle(int native_instance) {
        return native_instance;
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeCreateFromAsset(AssetManager mgr, String path) {
        mgr.addTaint(path.getTaintInt());
        return mgr.getTaintInt();
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeCreateFromFile(String path) {
        return path.getTaintInt();
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public static void setGammaForText(float blackGamma, float whiteGamma) {
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.627 -0500", hash_original_field = "8DF784A9F5E85BEBD0698D4CB91805F5", hash_generated_field = "0B2DA0B843E1AF20E33C18AF2C026319")

    public static  Typeface DEFAULT;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.629 -0500", hash_original_field = "CD38327509F2A84FC37FD1D34FC3C350", hash_generated_field = "39E278D928292BB72F2BB1941D84F1F2")

    public static  Typeface DEFAULT_BOLD;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.632 -0500", hash_original_field = "B1057AF3B6212441638B93C003ACD481", hash_generated_field = "DA2C3CBE6435D5462F6DAA252A072D88")

    public static  Typeface SANS_SERIF;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.634 -0500", hash_original_field = "3F07749F8E5A1FAFC3A54CA6E2F64AFC", hash_generated_field = "1F15F7D1B81488B4264416B4539F7B6A")

    public static  Typeface SERIF;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.637 -0500", hash_original_field = "4ECA9AD8841F280C7513D7DB66AF8217", hash_generated_field = "3895E8E9929B42525674D4A2E0502153")

    public static  Typeface MONOSPACE;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.639 -0500", hash_original_field = "D0061E515C6F421BACE32ED15D27CA69", hash_generated_field = "6FEC825196C265DC8D36C397F8A05A7D")
 static Typeface[] sDefaults;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.643 -0500", hash_original_field = "F4DDFE7A9BA099A1A6770B758F14F43C", hash_generated_field = "A4C87F852072D72C29870CFCB6AD7CC6")

    public static final int NORMAL = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.645 -0500", hash_original_field = "262D1E2BFD27CFAD4D39771739839EFB", hash_generated_field = "89D7140D7EB752A6C7528F551B43007D")

    public static final int BOLD = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.648 -0500", hash_original_field = "232AF836147F62CB90CA0A9FF0DEF71D", hash_generated_field = "AAC914EC5529D3EFA7515451CA579C98")

    public static final int ITALIC = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.650 -0500", hash_original_field = "6787308A71C9A109A79BC41318B14AF1", hash_generated_field = "ACD97265A92F699022D5C7173259C9A9")

    public static final int BOLD_ITALIC = 3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.641 -0500", hash_original_field = "1353DF0D3FEF59358BA81F3F4AC59875", hash_generated_field = "1353DF0D3FEF59358BA81F3F4AC59875")
 int native_instance;

    // don't allow clients to call this directly
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.672 -0500", hash_original_method = "A1E0C89A80599EF6E13C90B8B8945AA4", hash_generated_method = "0B0E47126C56BF6E71E6DE4A77BB0B48")
    
private Typeface(int ni) {
        if (0 == ni) {
            throw new RuntimeException("native typeface cannot be made");
        }
        native_instance = ni;
    }

    /** Returns the typeface's intrinsic style attributes */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.652 -0500", hash_original_method = "49E10597FC0BC827A75ECB0B721408FD", hash_generated_method = "B1A319B88CE99D3D37B0A0D49A11FA23")
    
public int getStyle() {
        return nativeGetStyle(native_instance);
    }

    /** Returns true if getStyle() has the BOLD bit set. */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.654 -0500", hash_original_method = "F9C5D984693E715CACA8B421F6CE6BD8", hash_generated_method = "1E88F458374AD4516F7A9850FC969AC0")
    
public final boolean isBold() {
        return 1 == ((getStyle() & BOLD) + 0);
    }

    /** Returns true if getStyle() has the ITALIC bit set. */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.656 -0500", hash_original_method = "55AE9D2665222680CD04F733708ADD28", hash_generated_method = "9FD604C75922F775EE3B6BFFCC00423B")
    
public final boolean isItalic() {
        return 1 == ((getStyle() & ITALIC) + 0);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:06.675 -0500", hash_original_method = "365BC48BBC32364F202A52D242C543E7", hash_generated_method = "8A0D631D5B8462432D6D6194C67FFABD")
    
protected void finalize() throws Throwable {
        super.finalize();
        nativeUnref(native_instance);
    }
    static {
        DEFAULT         = create((String)null, 0);
        DEFAULT_BOLD    = create((String)null, Typeface.BOLD);
        SANS_SERIF      = create("sans-serif", 0);
        SERIF           = create("serif", 0);
        MONOSPACE       = create("monospace", 0);
        sDefaults = new Typeface[] {
            DEFAULT,
            DEFAULT_BOLD,
            create((String)null, Typeface.ITALIC),
            create((String)null, Typeface.BOLD_ITALIC),
        };
    }
    
}

