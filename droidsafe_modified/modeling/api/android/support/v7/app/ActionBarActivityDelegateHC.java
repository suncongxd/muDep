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
 * Copyright (C) 2013 The Android Open Source Project
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


package android.support.v7.app;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.os.Bundle;
import android.view.Window;

class ActionBarActivityDelegateHC extends ActionBarActivityDelegateBase {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:31.427 -0400", hash_original_method = "F7B9D058672D16CA569401493642E485", hash_generated_method = "F7B9D058672D16CA569401493642E485")
    
ActionBarActivityDelegateHC(ActionBarActivity activity) {
        super(activity);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:31.434 -0400", hash_original_method = "1880A25C06C3EE2C2E1D61C88E96B80B", hash_generated_method = "F1F5D426BF91D963D74F976C09CE159C")
    
@Override
    void onCreate(Bundle savedInstanceState) {
        /**
         * A native Action Mode could be displayed (text selection, etc) so we need to make sure it
         * is positioned correctly. Here we request the ACTION_MODE_OVERLAY feature so that it
         * displays over the compat Action Bar.
         * {@link android.support.v7.internal.widget.NativeActionModeAwareLayout} is responsible for
         * making sure that the compat Action Bar is visible when an Action Mode is started
         * (for positioning).
         */
        mActivity.getWindow().requestFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        super.onCreate(savedInstanceState);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:31.440 -0400", hash_original_method = "75B3B44244FB022562B42FFEB818AD88", hash_generated_method = "4D956D0F2C263B5E8ABE602CC78F543C")
    
@Override
    public ActionBar createSupportActionBar() {
        ensureSubDecor();
        return new ActionBarImplHC(mActivity, mActivity);
    }
}
