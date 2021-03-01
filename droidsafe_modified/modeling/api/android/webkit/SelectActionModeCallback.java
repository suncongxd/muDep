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
 * Copyright (C) 2010 The Android Open Source Project
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
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Browser;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

class SelectActionModeCallback implements ActionMode.Callback {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.169 -0500", hash_original_field = "E044D69EE05B24F4B93E3A3B208F5343", hash_generated_field = "99346B50377E115231263981E00655CA")

    private WebView mWebView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.172 -0500", hash_original_field = "B7BD8B12490FD42C33E24469AF6B48C1", hash_generated_field = "941205D807AC4DF9704A65BDAF4756EC")

    private ActionMode mActionMode;
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:52.905 -0400", hash_original_method = "5762F84230A8FBDA28C52CC5E7905CF7", hash_generated_method = "5762F84230A8FBDA28C52CC5E7905CF7")
    public SelectActionModeCallback ()
    {
        //Synthesized constructor
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.174 -0500", hash_original_method = "2B5F8950C4750F957BA2CE7A16E0E578", hash_generated_method = "2B5F8950C4750F957BA2CE7A16E0E578")
    
void setWebView(WebView webView) {
        mWebView = webView;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.176 -0500", hash_original_method = "AEE4CA66C11C42A4DFBE3B808BED80FD", hash_generated_method = "5A55D270C47FEE6C8E965210FE81FBE1")
    
void finish() {
        // It is possible that onCreateActionMode was never called, in the case
        // where there is no ActionBar, for example.
        if (mActionMode != null) {
            mActionMode.finish();
        }
    }

    // ActionMode.Callback implementation

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.178 -0500", hash_original_method = "EEC72C77A82E33054EA06A487B276F43", hash_generated_method = "AF754D748711D481813985DE90BC2147")
    
@Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(com.android.internal.R.menu.webview_copy, menu);

        final Context context = mWebView.getContext();
        boolean allowText = context.getResources().getBoolean(
                com.android.internal.R.bool.config_allowActionMenuItemTextWithIcon);
        mode.setTitle(allowText ?
                context.getString(com.android.internal.R.string.textSelectionCABTitle) : null);

        if (!mode.isUiFocusable()) {
            // If the action mode UI we're running in isn't capable of taking window focus
            // the user won't be able to type into the find on page UI. Disable this functionality.
            // (Note that this should only happen in floating dialog windows.)
            // This can be removed once we can handle multiple focusable windows at a time
            // in a better way.
            final MenuItem findOnPageItem = menu.findItem(com.android.internal.R.id.find);
            if (findOnPageItem != null) {
                findOnPageItem.setVisible(false);
            }
        }
        mActionMode = mode;
        return true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.181 -0500", hash_original_method = "E3574021502A1E90BCF4C46DB1841054", hash_generated_method = "084A3D8BB1217CF825528FA834954073")
    
@Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.184 -0500", hash_original_method = "D632FC0DC2F573BE68AD858FDE491D0B", hash_generated_method = "1CF49B9308BED87EFB310233BE40AB41")
    
@Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.copy:
                mWebView.copySelection();
                mode.finish();
                break;

            case com.android.internal.R.id.share:
                String selection = mWebView.getSelection();
                Browser.sendString(mWebView.getContext(), selection);
                mode.finish();
                break;

            case com.android.internal.R.id.select_all:
                mWebView.selectAll();
                break;

            case com.android.internal.R.id.find:
                String sel= mWebView.getSelection();
                mode.finish();
                mWebView.showFindDialog(sel, false);
                break;
            case com.android.internal.R.id.websearch:
                mode.finish();
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.EXTRA_NEW_SEARCH, true);
                i.putExtra(SearchManager.QUERY, mWebView.getSelection());
                mWebView.getContext().startActivity(i);
                break;

            default:
                return false;
        }
        return true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:39.187 -0500", hash_original_method = "E29E2B7D27E4F7A85592500EAD3A4CE6", hash_generated_method = "D8E4B01526975F85C955D25C8585AC34")
    
@Override
    public void onDestroyActionMode(ActionMode mode) {
        mWebView.selectionDone();
    }
    
}

