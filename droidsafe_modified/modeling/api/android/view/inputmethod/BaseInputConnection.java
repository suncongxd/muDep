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
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package android.view.inputmethod;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.util.LogPrinter;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewRootImpl;

class ComposingText implements NoCopySpan {
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:49.232 -0400", hash_original_method = "299AC39680AC1BE3A17612B5D7B1D330", hash_generated_method = "299AC39680AC1BE3A17612B5D7B1D330")
    public ComposingText ()
    {
        //Synthesized constructor
    }

}

public class BaseInputConnection implements InputConnection {
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.429 -0500", hash_original_method = "62C66E7D51985E873C93BC6EE5B2664E", hash_generated_method = "E27089B8FDBBB72B63EBBED497B30100")
    
public static final void removeComposingSpans(Spannable text) {
        text.removeSpan(COMPOSING);
        Object[] sps = text.getSpans(0, text.length(), Object.class);
        if (sps != null) {
            for (int i=sps.length-1; i>=0; i--) {
                Object o = sps[i];
                if ((text.getSpanFlags(o)&Spanned.SPAN_COMPOSING) != 0) {
                    text.removeSpan(o);
                }
            }
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.432 -0500", hash_original_method = "149B8DCE1DD4AF251EAA1316EAECD41B", hash_generated_method = "A22A4EEC36BB24573500BE9B042D8909")
    
public static void setComposingSpans(Spannable text) {
        setComposingSpans(text, 0, text.length());
    }

    /** @hide */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.435 -0500", hash_original_method = "56739913A56341656B894EC8F37C96AC", hash_generated_method = "C226A8F329451972B7ADC82661373BFD")
    
public static void setComposingSpans(Spannable text, int start, int end) {
        final Object[] sps = text.getSpans(start, end, Object.class);
        if (sps != null) {
            for (int i=sps.length-1; i>=0; i--) {
                final Object o = sps[i];
                if (o == COMPOSING) {
                    text.removeSpan(o);
                    continue;
                }

                final int fl = text.getSpanFlags(o);
                if ((fl&(Spanned.SPAN_COMPOSING|Spanned.SPAN_POINT_MARK_MASK)) 
                        != (Spanned.SPAN_COMPOSING|Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)) {
                    text.setSpan(o, text.getSpanStart(o), text.getSpanEnd(o),
                            (fl & ~Spanned.SPAN_POINT_MARK_MASK)
                                    | Spanned.SPAN_COMPOSING
                                    | Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        text.setSpan(COMPOSING, start, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.437 -0500", hash_original_method = "5FB16EEB4B23553D26A4F5C8F80AB33C", hash_generated_method = "96CF70701AA7C42D3F0E282A7C1DD24C")
    
public static int getComposingSpanStart(Spannable text) {
        return text.getSpanStart(COMPOSING);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.440 -0500", hash_original_method = "7902CE0D5E0A3F946465422AB1EC8829", hash_generated_method = "6DB0E84CAEC5FD9B7584F2C3300AD05D")
    
public static int getComposingSpanEnd(Spannable text) {
        return text.getSpanEnd(COMPOSING);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.402 -0500", hash_original_field = "81DD852ECBE07BA98A61C8F3D0C85F01", hash_generated_field = "58EDF43BA541A4D47EECFEC3901C7AED")

    private static final boolean DEBUG = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.405 -0500", hash_original_field = "A6B44994F2FB4DC69E9C0DCAF8AA2888", hash_generated_field = "53A6B0F8E8ED4C9CDAAB0EB974BB44EF")

    private static final String TAG = "BaseInputConnection";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.407 -0500", hash_original_field = "7E8A677659B56D17DDCB20A097BE501D", hash_generated_field = "8705A035FE559B3B54E215D6480E2AFA")

    static final Object COMPOSING = new ComposingText();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.409 -0500", hash_original_field = "8A2D3AB4FFBC1A36EA3A9C7EBC8E2D9A", hash_generated_field = "141F0EFC0D8976518F576600E5590698")

    protected  InputMethodManager mIMM;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.412 -0500", hash_original_field = "97ACD18EF4B48BDC424981A4C6E55C66", hash_generated_field = "97ACD18EF4B48BDC424981A4C6E55C66")

     View mTargetView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.414 -0500", hash_original_field = "B578BC86523A8ED06C11106F457EA3A1", hash_generated_field = "B578BC86523A8ED06C11106F457EA3A1")

     boolean mDummyMode;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.416 -0500", hash_original_field = "262A329882BEE88213C74B3FDC4E1748", hash_generated_field = "252704B80FDD4D9B6E86ECF1CBD2B09A")
    
    private Object[] mDefaultComposingSpans;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.418 -0500", hash_original_field = "BC747A06F00EC3CB73FDE2D30B84DC2D", hash_generated_field = "BC747A06F00EC3CB73FDE2D30B84DC2D")
    
    Editable mEditable;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.420 -0500", hash_original_field = "5553093AC34FB198BC290668CD3A4BD4", hash_generated_field = "5553093AC34FB198BC290668CD3A4BD4")

    KeyCharacterMap mKeyCharacterMap;
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.423 -0500", hash_original_method = "1A943D99EC75638EA04879F1FF8F4BB3", hash_generated_method = "1A943D99EC75638EA04879F1FF8F4BB3")
    
BaseInputConnection(InputMethodManager mgr, boolean fullEditor) {
        mIMM = mgr;
        mTargetView = null;
        mDummyMode = !fullEditor;
    }
    
    @DSComment("no security concern")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.426 -0500", hash_original_method = "8D9F46A02734088C3A101A4C25C68420", hash_generated_method = "37CA8E5AF2E9549F228E0744CAF992B7")
    
public BaseInputConnection(View targetView, boolean fullEditor) {
        mIMM = (InputMethodManager)targetView.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        mTargetView = targetView;
        mDummyMode = !fullEditor;
    }
    
    /**
     * Return the target of edit operations.  The default implementation
     * returns its own fake editable that is just used for composing text;
     * subclasses that are real text editors should override this and
     * supply their own.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.442 -0500", hash_original_method = "A97F5DFC2840DF7B5D61C809FDCD4D64", hash_generated_method = "B9CC82C92617709D43E0C972CF5E0C75")
    
public Editable getEditable() {
        if (mEditable == null) {
            mEditable = Editable.Factory.getInstance().newEditable("");
            Selection.setSelection(mEditable, 0);
        }
        return mEditable;
    }
    
    /**
     * Default implementation does nothing.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.444 -0500", hash_original_method = "E28DA299470AFBBB55E3F0E22CB4A567", hash_generated_method = "A036F6419751AB9510A8B349F63C59A9")
    
public boolean beginBatchEdit() {
        return false;
    }

    /**
     * Default implementation does nothing.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.447 -0500", hash_original_method = "68C20202F4C35ADB2F9CDE7850BF84CC", hash_generated_method = "86CC35C50127CAC1253F81E3702F1634")
    
public boolean endBatchEdit() {
        return false;
    }

    /**
     * Default implementation uses
     * {@link MetaKeyKeyListener#clearMetaKeyState(long, int)
     * MetaKeyKeyListener.clearMetaKeyState(long, int)} to clear the state.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.449 -0500", hash_original_method = "BC6513316EC80C5373FB6263D33C3F52", hash_generated_method = "98D7A72A061F3E9B3F076E33F071F9DB")
    
public boolean clearMetaKeyStates(int states) {
        final Editable content = getEditable();
        if (content == null) return false;
        MetaKeyKeyListener.clearMetaKeyState(content, states);
        return true;
    }

    /**
     * Default implementation does nothing and returns false.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.451 -0500", hash_original_method = "AA7244FCF81F32F7DEF3D51FAE032B7D", hash_generated_method = "E94CE4BFF5CEED1491FA622961AF3AF5")
    
public boolean commitCompletion(CompletionInfo text) {
        return false;
    }

    /**
     * Default implementation does nothing and returns false.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.453 -0500", hash_original_method = "8ECFF7D6DBED10855DA2C653047584D7", hash_generated_method = "026A77FBF5B5886FDF2CB0009853C72D")
    
public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return false;
    }

    /**
     * Default implementation replaces any existing composing text with
     * the given text.  In addition, only if dummy mode, a key event is
     * sent for the new text and the current editable buffer cleared.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.456 -0500", hash_original_method = "25125C99E3204B3B6C4C8BDE15E97EE8", hash_generated_method = "2EE0FCFE80BDFE6EE86FDD4F643952E8")
    
public boolean commitText(CharSequence text, int newCursorPosition) {
        if (DEBUG) Log.v(TAG, "commitText " + text);
        replaceText(text, newCursorPosition, false);
        sendCurrentText();
        return true;
    }

    /**
     * The default implementation performs the deletion around the current
     * selection position of the editable text.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.459 -0500", hash_original_method = "74B12EE6F294A949F3B11979331384E6", hash_generated_method = "0D81FC045ACCD1B8D12A1541C1C020DA")
    
public boolean deleteSurroundingText(int leftLength, int rightLength) {
        if (DEBUG) Log.v(TAG, "deleteSurroundingText " + leftLength
                + " / " + rightLength);
        final Editable content = getEditable();
        if (content == null) return false;

        beginBatchEdit();
        
        int a = Selection.getSelectionStart(content);
        int b = Selection.getSelectionEnd(content);

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // ignore the composing text.
        int ca = getComposingSpanStart(content);
        int cb = getComposingSpanEnd(content);
        if (cb < ca) {
            int tmp = ca;
            ca = cb;
            cb = tmp;
        }
        if (ca != -1 && cb != -1) {
            if (ca < a) a = ca;
            if (cb > b) b = cb;
        }

        int deleted = 0;

        if (leftLength > 0) {
            int start = a - leftLength;
            if (start < 0) start = 0;
            content.delete(start, a);
            deleted = a - start;
        }

        if (rightLength > 0) {
            b = b - deleted;

            int end = b + rightLength;
            if (end > content.length()) end = content.length();

            content.delete(b, end);
        }
        
        endBatchEdit();
        
        return true;
    }

    /**
     * The default implementation removes the composing state from the
     * current editable text.  In addition, only if dummy mode, a key event is
     * sent for the new text and the current editable buffer cleared.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.461 -0500", hash_original_method = "1CCB9955A4205D39AC8709BDCAB9AF4C", hash_generated_method = "79B56BBE563CEA74B327E5525D4FFE73")
    
public boolean finishComposingText() {
        if (DEBUG) Log.v(TAG, "finishComposingText");
        final Editable content = getEditable();
        if (content != null) {
            beginBatchEdit();
            removeComposingSpans(content);
            endBatchEdit();
            sendCurrentText();
        }
        return true;
    }

    /**
     * The default implementation uses TextUtils.getCapsMode to get the
     * cursor caps mode for the current selection position in the editable
     * text, unless in dummy mode in which case 0 is always returned.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.464 -0500", hash_original_method = "A12ABCD2EB4522E8FE53DB6299A361B5", hash_generated_method = "28DA2DF10A711F52B85FC424358C926F")
    
public int getCursorCapsMode(int reqModes) {
        if (mDummyMode) return 0;
        
        final Editable content = getEditable();
        if (content == null) return 0;
        
        int a = Selection.getSelectionStart(content);
        int b = Selection.getSelectionEnd(content);

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        return TextUtils.getCapsMode(content, a, reqModes);
    }

    /**
     * The default implementation always returns null.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.466 -0500", hash_original_method = "A2A8D9B6A3DC6DA18051F8E023FD4A27", hash_generated_method = "8936794892555B759A66108240A3AB9A")
    
public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        return null;
    }

    /**
     * The default implementation returns the given amount of text from the
     * current cursor position in the buffer.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.468 -0500", hash_original_method = "75E370B84589846A89A63AE625719BF1", hash_generated_method = "F628D16938A07EC3C2781BED6C630F56")
    
public CharSequence getTextBeforeCursor(int length, int flags) {
        final Editable content = getEditable();
        if (content == null) return null;

        int a = Selection.getSelectionStart(content);
        int b = Selection.getSelectionEnd(content);

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (a <= 0) {
            return "";
        }
        
        if (length > a) {
            length = a;
        }

        if ((flags&GET_TEXT_WITH_STYLES) != 0) {
            return content.subSequence(a - length, a);
        }
        return TextUtils.substring(content, a - length, a);
    }

    /**
     * The default implementation returns the text currently selected, or null if none is
     * selected.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.471 -0500", hash_original_method = "ED9D872BFDE8C5E26AC99155DBA149BF", hash_generated_method = "583DA0B7B71E8D58AB532743D3867F50")
    
public CharSequence getSelectedText(int flags) {
        final Editable content = getEditable();
        if (content == null) return null;

        int a = Selection.getSelectionStart(content);
        int b = Selection.getSelectionEnd(content);

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (a == b) return null;

        if ((flags&GET_TEXT_WITH_STYLES) != 0) {
            return content.subSequence(a, b);
        }
        return TextUtils.substring(content, a, b);
    }

    /**
     * The default implementation returns the given amount of text from the
     * current cursor position in the buffer.
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.474 -0500", hash_original_method = "08096EEE0873EB795CD58B469F10D5B2", hash_generated_method = "E93C8508EF64179E6A7155EB519D1CAB")
    
public CharSequence getTextAfterCursor(int length, int flags) {
        final Editable content = getEditable();
        if (content == null) return null;

        int a = Selection.getSelectionStart(content);
        int b = Selection.getSelectionEnd(content);

        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // Guard against the case where the cursor has not been positioned yet.
        if (b < 0) {
            b = 0;
        }

        if (b + length > content.length()) {
            length = content.length() - b;
        }

        if ((flags&GET_TEXT_WITH_STYLES) != 0) {
            return content.subSequence(b, b + length);
        }
        return TextUtils.substring(content, b, b + length);
    }

    /**
     * The default implementation turns this into the enter key.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.477 -0500", hash_original_method = "F533043312AA12AABEB855D393677CB5", hash_generated_method = "AF6C93F953B0F20CBCE3F7AEC88E7E4D")
    
public boolean performEditorAction(int actionCode) {
        long eventTime = SystemClock.uptimeMillis();
        sendKeyEvent(new KeyEvent(eventTime, eventTime,
                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0, 0,
                KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE
                | KeyEvent.FLAG_EDITOR_ACTION));
        sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), eventTime,
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0, 0,
                KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE
                | KeyEvent.FLAG_EDITOR_ACTION));
        return true;
    }

    /**
     * The default implementation does nothing.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.480 -0500", hash_original_method = "CC9943DA76C04BDA8199AB8562F111EF", hash_generated_method = "C949E2510FB874197211F90E55CF99B1")
    
public boolean performContextMenuAction(int id) {
        return false;
    }

    /**
     * The default implementation does nothing.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.482 -0500", hash_original_method = "E554B360562612AFCC9A87837C58FB1E", hash_generated_method = "F5B0D13605B258FFB70028ABB9557250")
    
public boolean performPrivateCommand(String action, Bundle data) {
        return false;
    }

    /**
     * The default implementation places the given text into the editable,
     * replacing any existing composing text.  The new text is marked as
     * in a composing state with the composing style.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.484 -0500", hash_original_method = "9F64937BB19F72531BAD6E029A24112C", hash_generated_method = "FD6CB23B30363E9F1C526C952D30A824")
    
public boolean setComposingText(CharSequence text, int newCursorPosition) {
        if (DEBUG) Log.v(TAG, "setComposingText " + text);
        replaceText(text, newCursorPosition, true);
        return true;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.487 -0500", hash_original_method = "683D72CF1BBC9E7A391F140A2B9406BC", hash_generated_method = "67636B152BB6AA30875B1B52F7C79368")
    
public boolean setComposingRegion(int start, int end) {
        final Editable content = getEditable();
        if (content != null) {
            beginBatchEdit();
            removeComposingSpans(content);
            int a = start;
            int b = end;
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            // Clip the end points to be within the content bounds.
            final int length = content.length();
            if (a < 0) a = 0;
            if (b < 0) b = 0;
            if (a > length) a = length;
            if (b > length) b = length;

            ensureDefaultComposingSpans();
            if (mDefaultComposingSpans != null) {
                for (int i = 0; i < mDefaultComposingSpans.length; ++i) {
                    content.setSpan(mDefaultComposingSpans[i], a, b,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
                }
            }

            content.setSpan(COMPOSING, a, b,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);

            endBatchEdit();
            sendCurrentText();
        }
        return true;
    }

    /**
     * The default implementation changes the selection position in the
     * current editable text.
     */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.490 -0500", hash_original_method = "06BE1F921CCFEC5EE49CD2FF18114BBC", hash_generated_method = "FC8D5B5B840DE79CFC20DD26643C979E")
    
public boolean setSelection(int start, int end) {
        if (DEBUG) Log.v(TAG, "setSelection " + start + ", " + end);
        final Editable content = getEditable();
        if (content == null) return false;
        int len = content.length();
        if (start > len || end > len) {
            // If the given selection is out of bounds, just ignore it.
            // Most likely the text was changed out from under the IME,
            // the the IME is going to have to update all of its state
            // anyway.
            return true;
        }
        if (start == end && MetaKeyKeyListener.getMetaState(content,
                MetaKeyKeyListener.META_SELECTING) != 0) {
            // If we are in selection mode, then we want to extend the
            // selection instead of replacing it.
            Selection.extendSelection(content, start);
        } else {
            Selection.setSelection(content, start, end);
        }
        return true;
    }

    /**
     * Provides standard implementation for sending a key event to the window
     * attached to the input connection's view.
     */
    @DSComment("IO movement methodName")
    @DSSpec(DSCat.IO_ACTION_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.492 -0500", hash_original_method = "F539697840E1FFDBA9E983D0C0D8D0DD", hash_generated_method = "EFC70E180FE8A4B13933F470F54EF0F3")
    
public boolean sendKeyEvent(KeyEvent event) {
        synchronized (mIMM.mH) {
            Handler h = mTargetView != null ? mTargetView.getHandler() : null;
            if (h == null) {
                if (mIMM.mServedView != null) {
                    h = mIMM.mServedView.getHandler();
                }
            }
            if (h != null) {
                h.sendMessage(h.obtainMessage(ViewRootImpl.DISPATCH_KEY_FROM_IME,
                        event));
            }
        }
        return false;
    }
    
    /**
     * Updates InputMethodManager with the current fullscreen mode.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.495 -0500", hash_original_method = "FA18FCA0F08D3E4AC2B0D49617508B22", hash_generated_method = "FE2BE72850B9AB32DECB3FE94671B44D")
    
public boolean reportFullscreenMode(boolean enabled) {
        mIMM.setFullscreenMode(enabled);
        return true;
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.498 -0500", hash_original_method = "BDE0428D54DBC47996CEC1E4604D5DEE", hash_generated_method = "7906A3F46BB0899F790A91D7AE62D8D7")
    
private void sendCurrentText() {
        if (!mDummyMode) {
            return;
        }
        
        Editable content = getEditable();
        if (content != null) {
            final int N = content.length();
            if (N == 0) {
                return;
            }
            if (N == 1) {
                // If it's 1 character, we have a chance of being
                // able to generate normal key events...
                if (mKeyCharacterMap == null) {
                    mKeyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
                }
                char[] chars = new char[1];
                content.getChars(0, 1, chars, 0);
                KeyEvent[] events = mKeyCharacterMap.getEvents(chars);
                if (events != null) {
                    for (int i=0; i<events.length; i++) {
                        if (DEBUG) Log.v(TAG, "Sending: " + events[i]);
                        sendKeyEvent(events[i]);
                    }
                    content.clear();
                    return;
                }
            }
            
            // Otherwise, revert to the special key event containing
            // the actual characters.
            KeyEvent event = new KeyEvent(SystemClock.uptimeMillis(),
                    content.toString(), KeyCharacterMap.VIRTUAL_KEYBOARD, 0);
            sendKeyEvent(event);
            content.clear();
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.501 -0500", hash_original_method = "30261E28269E8D45DD173790F761946A", hash_generated_method = "7EBC729A206C85E02F3FD0BAEB161A12")
    
private void ensureDefaultComposingSpans() {
        if (mDefaultComposingSpans == null) {
            Context context;
            if (mTargetView != null) {
                context = mTargetView.getContext();
            } else if (mIMM.mServedView != null) {
                context = mIMM.mServedView.getContext();
            } else {
                context = null;
            }
            if (context != null) {
                TypedArray ta = context.getTheme()
                        .obtainStyledAttributes(new int[] {
                                com.android.internal.R.attr.candidatesTextStyleSpans
                        });
                CharSequence style = ta.getText(0);
                ta.recycle();
                if (style != null && style instanceof Spanned) {
                    mDefaultComposingSpans = ((Spanned)style).getSpans(
                            0, style.length(), Object.class);
                }
            }
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.504 -0500", hash_original_method = "A9689D9BE329300E95AF0BB78D1B0A9D", hash_generated_method = "C7369B4386C41968A7FE480161A021B3")
    
private void replaceText(CharSequence text, int newCursorPosition,
            boolean composing) {
        final Editable content = getEditable();
        if (content == null) {
            return;
        }
        
        beginBatchEdit();
        
        // delete composing text set previously.
        int a = getComposingSpanStart(content);
        int b = getComposingSpanEnd(content);

        if (DEBUG) Log.v(TAG, "Composing span: " + a + " to " + b);
        
        if (b < a) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        if (a != -1 && b != -1) {
            removeComposingSpans(content);
        } else {
            a = Selection.getSelectionStart(content);
            b = Selection.getSelectionEnd(content);
            if (a < 0) a = 0;
            if (b < 0) b = 0;
            if (b < a) {
                int tmp = a;
                a = b;
                b = tmp;
            }
        }

        if (composing) {
            Spannable sp = null;
            if (!(text instanceof Spannable)) {
                sp = new SpannableStringBuilder(text);
                text = sp;
                ensureDefaultComposingSpans();
                if (mDefaultComposingSpans != null) {
                    for (int i = 0; i < mDefaultComposingSpans.length; ++i) {
                        sp.setSpan(mDefaultComposingSpans[i], 0, sp.length(),
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE | Spanned.SPAN_COMPOSING);
                    }
                }
            } else {
                sp = (Spannable)text;
            }
            setComposingSpans(sp);
        }
        
        if (DEBUG) Log.v(TAG, "Replacing from " + a + " to " + b + " with \""
                + text + "\", composing=" + composing
                + ", type=" + text.getClass().getCanonicalName());
        
        if (DEBUG) {
            LogPrinter lp = new LogPrinter(Log.VERBOSE, TAG);
            lp.println("Current text:");
            TextUtils.dumpSpans(content, lp, "  ");
            lp.println("Composing text:");
            TextUtils.dumpSpans(text, lp, "  ");
        }

        // Position the cursor appropriately, so that after replacing the
        // desired range of text it will be located in the correct spot.
        // This allows us to deal with filters performing edits on the text
        // we are providing here.
        if (newCursorPosition > 0) {
            newCursorPosition += b - 1;
        } else {
            newCursorPosition += a;
        }
        if (newCursorPosition < 0) newCursorPosition = 0;
        if (newCursorPosition > content.length())
            newCursorPosition = content.length();
        Selection.setSelection(content, newCursorPosition);

        content.replace(a, b, text);
        
        if (DEBUG) {
            LogPrinter lp = new LogPrinter(Log.VERBOSE, TAG);
            lp.println("Final text:");
            TextUtils.dumpSpans(content, lp, "  ");
        }
        
        endBatchEdit();
    }
}

