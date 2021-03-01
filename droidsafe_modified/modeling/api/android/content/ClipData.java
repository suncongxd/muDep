/**
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
 * Copyright (c) 2010, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package android.content;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.util.Log;
import droidsafe.annotations.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ClipData implements Parcelable {

    /**
     * Create a new ClipData holding data of the type
     * {@link ClipDescription#MIMETYPE_TEXT_PLAIN}.
     *
     * @param label User-visible label for the clip data.
     * @param text The actual text in the clip.
     * @return Returns a new ClipData containing the specified data.
     */
    @DSComment("not sensitive")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.666 -0500", hash_original_method = "6CB00F4121B4A367251983F994C76576", hash_generated_method = "1E4CE54ECA8AC843024EDE9ABED5CE43")
    
static public ClipData newPlainText(CharSequence label, CharSequence text) {
        Item item = new Item(text);
        return new ClipData(label, MIMETYPES_TEXT_PLAIN, item);
    }

    /**
     * Create a new ClipData holding an Intent with MIME type
     * {@link ClipDescription#MIMETYPE_TEXT_INTENT}.
     *
     * @param label User-visible label for the clip data.
     * @param intent The actual Intent in the clip.
     * @return Returns a new ClipData containing the specified data.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.669 -0500", hash_original_method = "4E92BB296FD3CDEF44C04DC9305DF09F", hash_generated_method = "616324D254BCF2C43662D8927D30FA51")
    
static public ClipData newIntent(CharSequence label, Intent intent) {
        Item item = new Item(intent);
        return new ClipData(label, MIMETYPES_TEXT_INTENT, item);
    }

    /**
     * Create a new ClipData holding a URI.  If the URI is a content: URI,
     * this will query the content provider for the MIME type of its data and
     * use that as the MIME type.  Otherwise, it will use the MIME type
     * {@link ClipDescription#MIMETYPE_TEXT_URILIST}.
     *
     * @param resolver ContentResolver used to get information about the URI.
     * @param label User-visible label for the clip data.
     * @param uri The URI in the clip.
     * @return Returns a new ClipData containing the specified data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.672 -0500", hash_original_method = "C11B990F3F58DC47FD386C0AEFA978D2", hash_generated_method = "083E3C3CE214A8DD083306D88FED7A3D")
    
static public ClipData newUri(ContentResolver resolver, CharSequence label,
            Uri uri) {
        Item item = new Item(uri);
        String[] mimeTypes = null;
        if ("content".equals(uri.getScheme())) {
            String realType = resolver.getType(uri);
            mimeTypes = resolver.getStreamTypes(uri, "*/*");
            if (mimeTypes == null) {
                if (realType != null) {
                    mimeTypes = new String[] { realType, ClipDescription.MIMETYPE_TEXT_URILIST };
                }
            } else {
                String[] tmp = new String[mimeTypes.length + (realType != null ? 2 : 1)];
                int i = 0;
                if (realType != null) {
                    tmp[0] = realType;
                    i++;
                }
                System.arraycopy(mimeTypes, 0, tmp, i, mimeTypes.length);
                tmp[i + mimeTypes.length] = ClipDescription.MIMETYPE_TEXT_URILIST;
                mimeTypes = tmp;
            }
        }
        if (mimeTypes == null) {
            mimeTypes = MIMETYPES_TEXT_URILIST;
        }
        return new ClipData(label, mimeTypes, item);
    }

    /**
     * Create a new ClipData holding an URI with MIME type
     * {@link ClipDescription#MIMETYPE_TEXT_URILIST}.
     * Unlike {@link #newUri(ContentResolver, CharSequence, Uri)}, nothing
     * is inferred about the URI -- if it is a content: URI holding a bitmap,
     * the reported type will still be uri-list.  Use this with care!
     *
     * @param label User-visible label for the clip data.
     * @param uri The URI in the clip.
     * @return Returns a new ClipData containing the specified data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.675 -0500", hash_original_method = "155F3F7B153F5EA8333C8D51E7A66990", hash_generated_method = "8A56D48CAE45BA842C91F0F5ADE2C28E")
    
static public ClipData newRawUri(CharSequence label, Uri uri) {
        Item item = new Item(uri);
        return new ClipData(label, MIMETYPES_TEXT_URILIST, item);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.618 -0500", hash_original_field = "93ADEF570C485E7E754F5B0EE93F4C0E", hash_generated_field = "FFD278290163FA53346E9B463D548F25")

    static final String[] MIMETYPES_TEXT_PLAIN = new String[] {
        ClipDescription.MIMETYPE_TEXT_PLAIN };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.620 -0500", hash_original_field = "0BACA1185BA8E8D498CEC40FE4292101", hash_generated_field = "0081E14CF2E7DA589C4FFF459050BDC6")

    static final String[] MIMETYPES_TEXT_URILIST = new String[] {
        ClipDescription.MIMETYPE_TEXT_URILIST };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.622 -0500", hash_original_field = "6178C0C9B9EAA00A8F5FA4D872E5BDF4", hash_generated_field = "DD2F391B9027C3620AF732A9D8A52B0F")

    static final String[] MIMETYPES_TEXT_INTENT = new String[] {
        ClipDescription.MIMETYPE_TEXT_INTENT };
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:22:59.562 -0400", hash_original_field = "3BBA404F84527DE1C18693556D0F0F97", hash_generated_field = "354B16FF6CC203D912D18248606A7B92")

    public static final Parcelable.Creator<ClipData> CREATOR =
        new Parcelable.Creator<ClipData>() {

            @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.696 -0500", hash_original_method = "E9B77AA38C2D4F67ACBCF421C777825E", hash_generated_method = "3DC0675F011A5E949860AC49DFD80659")
        
public ClipData createFromParcel(Parcel source) {
                return new ClipData(source);
            }

            @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.698 -0500", hash_original_method = "4F6E120BA30B443AED50CCA81CAD8061", hash_generated_method = "D04E3634FC4EEEE34D8E82E19FDE35E2")
        
public ClipData[] newArray(int size) {
                return new ClipData[size];
            }
        };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.624 -0500", hash_original_field = "BD054FDEC655C28C2D1BA2544910FE2F", hash_generated_field = "BD054FDEC655C28C2D1BA2544910FE2F")

     ClipDescription mClipDescription;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.627 -0500", hash_original_field = "EC076BF50357690C1FC8AB2DC0D41329", hash_generated_field = "EC076BF50357690C1FC8AB2DC0D41329")
    
     Bitmap mIcon;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.629 -0500", hash_original_field = "C06D30D4FE5B15243361075EF8FC091A", hash_generated_field = "642CB554EA43ABB4F05AD892541D7330")

    final ArrayList<Item> mItems = new ArrayList<Item>();

    /**
     * Create a new clip.
     *
     * @param label Label to show to the user describing this clip.
     * @param mimeTypes An array of MIME types this data is available as.
     * @param item The contents of the first item in the clip.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.662 -0500", hash_original_method = "EBF505A176A1691C0AFF349A48F88B72", hash_generated_method = "4C7AAE0C723F6C19972FBE1B2122D173")
    
public ClipData(CharSequence label, String[] mimeTypes, Item item) {
        mClipDescription = new ClipDescription(label, mimeTypes);
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        mIcon = null;
        mItems.add(item);
    }

    /**
     * Create a new clip.
     *
     * @param description The ClipDescription describing the clip contents.
     * @param item The contents of the first item in the clip.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.664 -0500", hash_original_method = "07F73711C3AF49FFEB328A8FE4F43045", hash_generated_method = "1B390410608A03DC0D9E987E9EB27ABB")
    
public ClipData(ClipDescription description, Item item) {
        mClipDescription = description;
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        mIcon = null;
        mItems.add(item);
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.693 -0500", hash_original_method = "58D4E89221B1347651A83B7DDCBD7718", hash_generated_method = "58D4E89221B1347651A83B7DDCBD7718")
    
ClipData(Parcel in) {
        mClipDescription = new ClipDescription(in);
        if (in.readInt() != 0) {
            mIcon = Bitmap.CREATOR.createFromParcel(in);
        } else {
            mIcon = null;
        }
        final int N = in.readInt();
        for (int i=0; i<N; i++) {
            CharSequence text = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
            Intent intent = in.readInt() != 0 ? Intent.CREATOR.createFromParcel(in) : null;
            Uri uri = in.readInt() != 0 ? Uri.CREATOR.createFromParcel(in) : null;
            mItems.add(new Item(text, intent, uri));
        }
    }

    /**
     * Return the {@link ClipDescription} associated with this data, describing
     * what it contains.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.CLIPBOARD})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.677 -0500", hash_original_method = "8475A7793CA47249207DFD30E601781C", hash_generated_method = "E6CC842A9FBA72E929DACF15D1422EBF")
    
public ClipDescription getDescription() {
        return mClipDescription;
    }
    
    /**
     * Add a new Item to the overall ClipData container.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.679 -0500", hash_original_method = "150F076539D3E4866582D984173D26A6", hash_generated_method = "A531D84ECA17241A3D1AF6850F807E56")
    
public void addItem(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        mItems.add(item);
    }

    /** @hide */
    @DSSource({DSSourceKind.CLIPBOARD})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.680 -0500", hash_original_method = "229D9D1026C0301FD9A4AD50AC984F17", hash_generated_method = "61B9B78324D83593E0914ECCCBD6EC01")
    
public Bitmap getIcon() {
        return mIcon;
    }
    
    public static class Item {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.631 -0500", hash_original_field = "A59BBC07E5E46996D793B2F37E80BD24", hash_generated_field = "A59BBC07E5E46996D793B2F37E80BD24")

         CharSequence mText;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.633 -0500", hash_original_field = "1811495D939DB843870F6315E04555CC", hash_generated_field = "1811495D939DB843870F6315E04555CC")

         Intent mIntent;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.635 -0500", hash_original_field = "49226456B4CE4E55A779249DE3DC63D4", hash_generated_field = "49226456B4CE4E55A779249DE3DC63D4")

         Uri mUri;

        /**
         * Create an Item consisting of a single block of (possibly styled) text.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.638 -0500", hash_original_method = "4A009EFCCF51C2EA640445D87C84D900", hash_generated_method = "CEE34EAF88E6FC7D89B93DC2D9F3A4BD")
        
public Item(CharSequence text) {
            mText = text;
            mIntent = null;
            mUri = null;
        }

        /**
         * Create an Item consisting of an arbitrary Intent.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.641 -0500", hash_original_method = "11EC393E75756EFF910C83D0B79625D8", hash_generated_method = "72AE5EEDB6F9FA7CC8AC78AEB9736027")
        
public Item(Intent intent) {
            mText = null;
            mIntent = intent;
            mUri = null;
        }

        /**
         * Create an Item consisting of an arbitrary URI.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.643 -0500", hash_original_method = "C0C0DA6EE7D207BF80500DD7F8FC5437", hash_generated_method = "4A60D11061AA1D64F4A2230CC4D37412")
        
public Item(Uri uri) {
            mText = null;
            mIntent = null;
            mUri = uri;
        }

        /**
         * Create a complex Item, containing multiple representations of
         * text, intent, and/or URI.
         */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.647 -0500", hash_original_method = "40EC9D6AF0E3564A5C6CD681E96DEE48", hash_generated_method = "DEF1BD49A4BA49D733C760E1419668C9")
        
public Item(CharSequence text, Intent intent, Uri uri) {
            mText = text;
            mIntent = intent;
            mUri = uri;
        }

        /**
         * Retrieve the raw text contained in this Item.
         */
        @DSSource({DSSourceKind.CLIPBOARD})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.649 -0500", hash_original_method = "D3D56665E0CC0B43413FBFB4C720E96C", hash_generated_method = "3C5AE00E10DCE5E04049FCC58EEF7C48")
        
public CharSequence getText() {
            return mText;
        }

        /**
         * Retrieve the raw Intent contained in this Item.
         */
        @DSSafe(DSCat.SAFE_LIST)
        @DSSource({DSSourceKind.CLIPBOARD})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.651 -0500", hash_original_method = "AD027B7B58A4A2F151CC138FB7B23244", hash_generated_method = "7CE6D2E5920E17BB1B744A5090D635BC")
        
public Intent getIntent() {
            return mIntent;
        }

        /**
         * Retrieve the raw URI contained in this Item.
         */
        @DSSource({DSSourceKind.CLIPBOARD})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.654 -0500", hash_original_method = "225259AA593B6A59F476A2C569F1B075", hash_generated_method = "A1B996311564AA1668AF8C1FC49818A0")
        
public Uri getUri() {
            return mUri;
        }

        /**
         * Turn this item into text, regardless of the type of data it
         * actually contains.
         *
         * <p>The algorithm for deciding what text to return is:
         * <ul>
         * <li> If {@link #getText} is non-null, return that.
         * <li> If {@link #getUri} is non-null, try to retrieve its data
         * as a text stream from its content provider.  If this succeeds, copy
         * the text into a String and return it.  If it is not a content: URI or
         * the content provider does not supply a text representation, return
         * the raw URI as a string.
         * <li> If {@link #getIntent} is non-null, convert that to an intent:
         * URI and returnit.
         * <li> Otherwise, return an empty string.
         * </ul>
         *
         * @param context The caller's Context, from which its ContentResolver
         * and other things can be retrieved.
         * @return Returns the item's textual representation.
         */
//BEGIN_INCLUDE(coerceToText)
        @DSSafe(DSCat.UTIL_FUNCTION)
        @DSSource({DSSourceKind.CLIPBOARD})
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.657 -0500", hash_original_method = "2A1795F61A3E14A1308A3A7CD55951FC", hash_generated_method = "DEFCF0170A8A578D98974249E132B756")
        
public CharSequence coerceToText(Context context) {
	    String str = new String();
	    str.addTaint(this.getTaint());
	    return str;
        }        
    }

    /**
     * Return the number of items in the clip data.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSSource({DSSourceKind.CLIPBOARD})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.683 -0500", hash_original_method = "2908203B9FEFAA3035EA88685E6DC23A", hash_generated_method = "5D1AD865251A7873FAFBCD6D25363BA0")
    
public int getItemCount() {
        return mItems.size();
    }

    /**
     * Return a single item inside of the clip data.  The index can range
     * from 0 to {@link #getItemCount()}-1.
     */
    @DSSource({DSSourceKind.CLIPBOARD})
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.684 -0500", hash_original_method = "79B4F12EE34F43C8036D233ADFF72DDF", hash_generated_method = "4F5CD9E17BCB4FF586B31D0FC9D3644C")
    
public Item getItemAt(int index) {
        return mItems.get(index);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.687 -0500", hash_original_method = "00F8174F9E89D0C972FA6D3F19742382", hash_generated_method = "8188008AC9C80E87937FE73DCA905200")
    
@Override
    public int describeContents() {
        return 0;
    }

    @DSSink({DSSinkKind.CLIPBOARD})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:40.690 -0500", hash_original_method = "0C9FB932279AD72A7EC79D2538384FC0", hash_generated_method = "3BDF0ABBB8CE40B99C35CF9571816F8F")
    
@Override
    public void writeToParcel(Parcel dest, int flags) {
        mClipDescription.writeToParcel(dest, flags);
        if (mIcon != null) {
            dest.writeInt(1);
            mIcon.writeToParcel(dest, flags);
        } else {
            dest.writeInt(0);
        }
        final int N = mItems.size();
        dest.writeInt(N);
        for (int i=0; i<N; i++) {
            Item item = mItems.get(i);
            TextUtils.writeToParcel(item.mText, dest, flags);
            if (item.mIntent != null) {
                dest.writeInt(1);
                item.mIntent.writeToParcel(dest, flags);
            } else {
                dest.writeInt(0);
            }
            if (item.mUri != null) {
                dest.writeInt(1);
                item.mUri.writeToParcel(dest, flags);
            } else {
                dest.writeInt(0);
            }
        }
    }
    // orphaned legacy method
    public ClipData createFromParcel(Parcel source) {
                return new ClipData(source);
            }
    
    // orphaned legacy method
    public ClipData[] newArray(int size) {
                return new ClipData[size];
            }
    
}

