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


package android.text.method;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;

public abstract class NumberKeyListener extends BaseKeyListener implements InputFilter {

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:59.031 -0500", hash_original_method = "31CEF7B6ADFCFD6FD706824ECFEAE65B", hash_generated_method = "D718178AA3D8EF616421B07A2848E87F")
    
protected static boolean ok(char[] accept, char c) {
        for (int i = accept.length - 1; i >= 0; i--) {
            if (accept[i] == c) {
                return true;
            }
        }

        return false;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.475 -0400", hash_original_method = "4B29623A8F76435C1A957FA964420338", hash_generated_method = "4B29623A8F76435C1A957FA964420338")
    public NumberKeyListener ()
    {
        //Synthesized constructor
    }
    /**
     * You can say which characters you can accept.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:59.021 -0500", hash_original_method = "CEAB545228064F3E6229396D3CB18362", hash_generated_method = "BF028713305ABE6488DECAFE3077584F")
    
protected abstract char[] getAcceptedChars();

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:59.024 -0500", hash_original_method = "C0C70824215D530CCBC4D0D762C70EA3", hash_generated_method = "4FE9FBBC675127A11CFDC1B46E466BA9")
    
protected int lookup(KeyEvent event, Spannable content) {
        return event.getMatch(getAcceptedChars(), event.getMetaState() | getMetaState(content));
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:59.028 -0500", hash_original_method = "CB7791CDEF3F29FF221CF4242D732189", hash_generated_method = "7D0E43D08B18974A0313B9C3A3D46568")
    
public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        char[] accept = getAcceptedChars();
        boolean filter = false;

        int i;
        for (i = start; i < end; i++) {
            if (!ok(accept, source.charAt(i))) {
                break;
            }
        }

        if (i == end) {
            // It was all OK.
            return null;
        }

        if (end - start == 1) {
            // It was not OK, and there is only one char, so nothing remains.
            return "";
        }

        SpannableStringBuilder filtered =
            new SpannableStringBuilder(source, start, end);
        i -= start;
        end -= start;

        int len = end - start;
        // Only count down to i because the chars before that were all OK.
        for (int j = end - 1; j >= i; j--) {
            if (!ok(accept, source.charAt(j))) {
                filtered.delete(j, j + 1);
            }
        }

        return filtered;
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:59.035 -0500", hash_original_method = "A33764BD45F2F5F121C837A63F0C5C57", hash_generated_method = "9721C4EC7386B8A7047CAA23E2BFB523")
    
@Override
    public boolean onKeyDown(View view, Editable content,
                             int keyCode, KeyEvent event) {
        int selStart, selEnd;

        {
            int a = Selection.getSelectionStart(content);
            int b = Selection.getSelectionEnd(content);

            selStart = Math.min(a, b);
            selEnd = Math.max(a, b);
        }

        if (selStart < 0 || selEnd < 0) {
            selStart = selEnd = 0;
            Selection.setSelection(content, 0);
        }

        int i = event != null ? lookup(event, content) : 0;
        int repeatCount = event != null ? event.getRepeatCount() : 0;
        if (repeatCount == 0) {
            if (i != 0) {
                if (selStart != selEnd) {
                    Selection.setSelection(content, selEnd);
                }

                content.replace(selStart, selEnd, String.valueOf((char) i));

                adjustMetaAfterKeypress(content);
                return true;
            }
        } else if (i == '0' && repeatCount == 1) {
            // Pretty hackish, it replaces the 0 with the +

            if (selStart == selEnd && selEnd > 0 &&
                    content.charAt(selStart - 1) == '0') {
                content.replace(selStart - 1, selEnd, String.valueOf('+'));
                adjustMetaAfterKeypress(content);
                return true;
            }
        }

        adjustMetaAfterKeypress(content);
        return super.onKeyDown(view, content, keyCode, event);
    }
    
}

