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


package android.support.v7.internal.widget;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v7.appcompat.R;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

/**
 * @hide
 */
public class CompatTextView extends TextView {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-19 14:39:29.715 -0400", hash_original_method = "8C17596EF66E1CC44E115EFF576EF240", hash_generated_method = "45AEBC05347870ED2BDEB22183983D06")
    
public CompatTextView(Context context) {
        this(context, null);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-19 14:39:29.727 -0400", hash_original_method = "39C2015F4703D950BC671736B89FB34F", hash_generated_method = "DE6E8A9CB432F2F51C0699A376F3F86E")
    
public CompatTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-19 14:39:29.743 -0400", hash_original_method = "3BF75E4858947AC6B4C3CB6085CF7A0A", hash_generated_method = "5ED51FF9AA9BCB977B249F080DE23948")
    
public CompatTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        boolean allCaps = false;

        TypedArray style = context
                .obtainStyledAttributes(attrs, R.styleable.CompatTextView, defStyle, 0);
        allCaps = style.getBoolean(R.styleable.CompatTextView_textAllCaps, false);
        style.recycle();

        // Framework impl also checks TextAppearance for textAllCaps. This isn't needed for our
        // purposes so has been omitted.

        if (allCaps) {
            setTransformationMethod(new AllCapsTransformationMethod(context));
        }
    }

    /**
     * Transforms source text into an ALL CAPS string, locale-aware.
     */
    private static class AllCapsTransformationMethod implements TransformationMethod {

        private final Locale mLocale;

        public AllCapsTransformationMethod(Context context) {
            mLocale = context.getResources().getConfiguration().locale;
        }

        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source != null ? source.toString().toUpperCase(mLocale) : null;
        }

        @Override
        public void onFocusChanged(View view, CharSequence charSequence, boolean b, int i,
                Rect rect) {
        }
    }
}
