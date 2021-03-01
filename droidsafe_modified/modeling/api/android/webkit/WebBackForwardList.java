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


package android.webkit;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.Serializable;
import java.util.ArrayList;

public class WebBackForwardList implements Cloneable, Serializable {
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static synchronized void restoreIndex(int nativeFrame,
            int index) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void nativeClose(int nativeFrame) {
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.270 -0500", hash_original_field = "9D5DB06DCD458CB1FB573ED28600B2B5", hash_generated_field = "0311A696B59887F86EF6109DBE0484CC")

    private int mCurrentIndex;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.272 -0500", hash_original_field = "40ED77F9A03483BB034F43A6145E6409", hash_generated_field = "65483EDB69D85E829A0A1D4D7EF789CD")

    private ArrayList<WebHistoryItem> mArray;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.274 -0500", hash_original_field = "33A24E98767714B41BED5DB7EE47997B", hash_generated_field = "602E0BBCBCA5B94178C21F3474243E08")

    private boolean mClearPending;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.276 -0500", hash_original_field = "C50B804397335F4C59F495D6A5AAA565", hash_generated_field = "62789CA10670C708EA4D387AB18C5F89")

    private  CallbackProxy mCallbackProxy;

    /**
     * Construct a back/forward list used by clients of WebView.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.279 -0500", hash_original_method = "E1648D2B4FFD2451814681FC87C9187A", hash_generated_method = "E1648D2B4FFD2451814681FC87C9187A")
    
WebBackForwardList(CallbackProxy proxy) {
        mCurrentIndex = -1;
        mArray = new ArrayList<WebHistoryItem>();
        mCallbackProxy = proxy;
    }

    /**
     * Return the current history item. This method returns null if the list is
     * empty.
     * @return The current history item.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.281 -0500", hash_original_method = "5230E69659F0505E7D194D2488C74C74", hash_generated_method = "7BBFD33178DD994229AB40FFBC0234D8")
    
public synchronized WebHistoryItem getCurrentItem() {
        return getItemAtIndex(mCurrentIndex);
    }

    /**
     * Get the index of the current history item. This index can be used to
     * directly index into the array list.
     * @return The current index from 0...n or -1 if the list is empty.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.283 -0500", hash_original_method = "2A2A2AA161DBB8DEEC2045188BE5DE03", hash_generated_method = "F1A964DC93D525D9D154ED9C80DA7010")
    
public synchronized int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * Get the history item at the given index. The index range is from 0...n
     * where 0 is the first item and n is the last item.
     * @param index The index to retrieve.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.285 -0500", hash_original_method = "D9B5D5CD50BAA8A173683EE2BD19F40F", hash_generated_method = "64C8E288184E57DD901465A7263B0696")
    
public synchronized WebHistoryItem getItemAtIndex(int index) {
        if (index < 0 || index >= getSize()) {
            return null;
        }
        return mArray.get(index);
    }

    /**
     * Get the total size of the back/forward list.
     * @return The size of the list.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.287 -0500", hash_original_method = "7A631E875D902776AD0C3E21989EE281", hash_generated_method = "84328E53B827C13AB004DCEC859664E4")
    
public synchronized int getSize() {
        return mArray.size();
    }

    /**
     * Mark the back/forward list as having a pending clear. This is used on the
     * UI side to mark the list as being invalid during the clearHistory method.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.289 -0500", hash_original_method = "7F0DA21FF2FDC3954DD60BB35F4D7E5E", hash_generated_method = "265426AB37C57F06AF49BB0D5D845350")
    
synchronized void setClearPending() {
        mClearPending = true;
    }

    /**
     * Return the status of the clear flag. This is used on the UI side to
     * determine if the list is valid for checking things like canGoBack.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.291 -0500", hash_original_method = "18F3D3D2A3274FA9BAB45BC52054A870", hash_generated_method = "51544C5429B725FF54BB957B856943E7")
    
synchronized boolean getClearPending() {
        return mClearPending;
    }

    /**
     * Add a new history item to the list. This will remove all items after the
     * current item and append the new item to the end of the list. Called from
     * the WebCore thread only. Synchronized because the UI thread may be
     * reading the array or the current index.
     * @param item A new history item.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.293 -0500", hash_original_method = "CF7A7A96F9B6499845E300F4AB5BE8D8", hash_generated_method = "B826C2CCAE61A684F8C94C27EC0AF85A")
    
synchronized void addHistoryItem(WebHistoryItem item) {
        // Update the current position because we are going to add the new item
        // in that slot.
        ++mCurrentIndex;
        // If the current position is not at the end, remove all history items
        // after the current item.
        final int size = mArray.size();
        final int newPos = mCurrentIndex;
        if (newPos != size) {
            for (int i = size - 1; i >= newPos; i--) {
                final WebHistoryItem h = mArray.remove(i);
            }
        }
        // Add the item to the list.
        mArray.add(item);
        if (mCallbackProxy != null) {
            mCallbackProxy.onNewHistoryItem(item);
        }
    }

    /**
     * Clear the back/forward list. Called from the WebCore thread.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.296 -0500", hash_original_method = "86E2ECBC2C9AC2A7DC658CB964CA32CA", hash_generated_method = "D00D082CF22D68D1D1DBD6A8B53C3AC4")
    
synchronized void close(int nativeFrame) {
        // Clear the array first because nativeClose will call addHistoryItem
        // with the current item.
        mArray.clear();
        mCurrentIndex = -1;
        nativeClose(nativeFrame);
        // Reset the clear flag
        mClearPending = false;
    }

    /* Remove the item at the given index. Called by JNI only. */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.299 -0500", hash_original_method = "7D1DAF4A518C34619CF3BEB7A8646574", hash_generated_method = "06282ECD6E28461C500CF1554CF9B53E")
    
private synchronized void removeHistoryItem(int index) {
        // XXX: This is a special case. Since the callback is only triggered
        // when removing the first item, we can assert that the index is 0.
        // This lets us change the current index without having to query the
        // native BackForwardList.
        if (DebugFlags.WEB_BACK_FORWARD_LIST && (index != 0)) {
            throw new AssertionError();
        }
        final WebHistoryItem h = mArray.remove(index);
        // XXX: If we ever add another callback for removing history items at
        // any index, this will no longer be valid.
        mCurrentIndex--;
    }

    /**
     * Clone the entire object to be used in the UI thread by clients of
     * WebView. This creates a copy that should never be modified by any of the
     * webkit package classes.
     */
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.301 -0500", hash_original_method = "26B3CB37F20A0B53C2CE3E9A4CAEEB23", hash_generated_method = "65C35583B33AC219F8D79447C6A677D0")
    
protected synchronized WebBackForwardList clone() {
        WebBackForwardList l = new WebBackForwardList(null);
        if (mClearPending) {
            // If a clear is pending, return a copy with only the current item.
            l.addHistoryItem(getCurrentItem());
            return l;
        }
        l.mCurrentIndex = mCurrentIndex;
        int size = getSize();
        l.mArray = new ArrayList<WebHistoryItem>(size);
        for (int i = 0; i < size; i++) {
            // Add a copy of each WebHistoryItem
            l.mArray.add(mArray.get(i).clone());
        }
        return l;
    }

    /**
     * Set the new history index.
     * @param newIndex The new history index.
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:01.304 -0500", hash_original_method = "CB94BF4CFE461E0C4E4AA1BED49D9ED0", hash_generated_method = "7B34BBDF79729C158A1F3FEBD86E5361")
    
synchronized void setCurrentIndex(int newIndex) {
        mCurrentIndex = newIndex;
        if (mCallbackProxy != null) {
            mCallbackProxy.onIndexChanged(getItemAtIndex(newIndex), newIndex);
        }
    }
    
}

