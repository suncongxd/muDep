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
 * Copyright (C) 2007-2008 The Android Open Source Project
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
import android.os.Bundle;
import android.view.KeyEvent;

public class InputConnectionWrapper implements InputConnection {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.061 -0500", hash_original_field = "8E3195515492BEAAB35BF3AB9AAAADC5", hash_generated_field = "151AD6A9E57045A4F9C7E4BD9FA7D17C")

    private InputConnection mTarget;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.063 -0500", hash_original_field = "899CF99A8BCFC1DC9FF2AE2242A53A1C", hash_generated_field = "899CF99A8BCFC1DC9FF2AE2242A53A1C")

     boolean mMutable;
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.066 -0500", hash_original_method = "0AE68A627B838EA5D1B587AAFC2A6012", hash_generated_method = "807A120A737872ABBAB79A5B04610B59")
    
public InputConnectionWrapper(InputConnection target, boolean mutable) {
        mMutable = mutable;
        mTarget = target;
    }

    /**
     * Change the target of the input connection.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.069 -0500", hash_original_method = "B3C6A81D2BBBD36FEBF3ECE06B5CDD0C", hash_generated_method = "23DA4F0C6064BD3E238682C42AF5D824")
    
public void setTarget(InputConnection target) {
        if (mTarget != null && !mMutable) {
            throw new SecurityException("not mutable");
        }
        mTarget = target;
    }
    
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.071 -0500", hash_original_method = "70D1E90906EDA66AADA5E9E7AF314024", hash_generated_method = "B461513FD88AC98BE482E876112D5202")
    
public CharSequence getTextBeforeCursor(int n, int flags) {
        return mTarget.getTextBeforeCursor(n, flags);
    }
    
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.073 -0500", hash_original_method = "511698C68B38701B7124628C33C2BDC9", hash_generated_method = "7598E78431D4AB1F9FF730CD883FFE61")
    
public CharSequence getTextAfterCursor(int n, int flags) {
        return mTarget.getTextAfterCursor(n, flags);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.075 -0500", hash_original_method = "F94B155A1C8F9C791E6D9828C6F8114B", hash_generated_method = "3DE3851AF3CFFC905685B8AAFDBD7A82")
    
public CharSequence getSelectedText(int flags) {
        return mTarget.getSelectedText(flags);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.077 -0500", hash_original_method = "FFC3D29E68F767E6DE7BA85D50A59F74", hash_generated_method = "0C0CBE9F677ADE2BBE3319D1F79DCAE3")
    
public int getCursorCapsMode(int reqModes) {
        return mTarget.getCursorCapsMode(reqModes);
    }
    
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.080 -0500", hash_original_method = "B46A0C14FE8C4BFCAB3863CCE2B487E1", hash_generated_method = "C2B8C6205BC7C225EE03F7B298CF6BC1")
    
public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        return mTarget.getExtractedText(request, flags);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.083 -0500", hash_original_method = "02B0C02DBA209769DC8321464F457D4E", hash_generated_method = "EC7DBFEAFE2B02D5184C88FFD3235E8A")
    
public boolean deleteSurroundingText(int leftLength, int rightLength) {
        return mTarget.deleteSurroundingText(leftLength, rightLength);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.086 -0500", hash_original_method = "C347FD161FDEFFB180258C7A61E86611", hash_generated_method = "36C28CC75410E2460F2D63EB57FCC5F5")
    
public boolean setComposingText(CharSequence text, int newCursorPosition) {
        return mTarget.setComposingText(text, newCursorPosition);
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.088 -0500", hash_original_method = "EB3F6672BEE71C12075AAA6BA4DB02F1", hash_generated_method = "6831CD4CB1CA4BCA457784819D25BE93")
    
public boolean setComposingRegion(int start, int end) {
        return mTarget.setComposingRegion(start, end);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.091 -0500", hash_original_method = "09C1683C22BFF62FC14B2B11C262ABDF", hash_generated_method = "B681580C9DC68BE46A385F4EF34D6B2A")
    
public boolean finishComposingText() {
        return mTarget.finishComposingText();
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.093 -0500", hash_original_method = "9F195FFD333D6DD92AB286EC5AB03487", hash_generated_method = "23676FD1890B717C0C3B625397ACEF05")
    
public boolean commitText(CharSequence text, int newCursorPosition) {
        return mTarget.commitText(text, newCursorPosition);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.096 -0500", hash_original_method = "947FB54A5439AE99C45577560BDB97CA", hash_generated_method = "628DF4907A2CD93B96684B452B9AE66F")
    
public boolean commitCompletion(CompletionInfo text) {
        return mTarget.commitCompletion(text);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.098 -0500", hash_original_method = "57757FD5C0807A7AB2D953AC1AE2D156", hash_generated_method = "27958DBD614D607330537D95C5699209")
    
public boolean commitCorrection(CorrectionInfo correctionInfo) {
        return mTarget.commitCorrection(correctionInfo);
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.101 -0500", hash_original_method = "E434BE133364C8C6947501760AA1AA25", hash_generated_method = "73BAC84DD77B212DB9DF8D8A206712C1")
    
public boolean setSelection(int start, int end) {
        return mTarget.setSelection(start, end);
    }
    
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.103 -0500", hash_original_method = "557B358548051EB41CD67FF4BAC9B39C", hash_generated_method = "F2DCE21E89D66883ED3205B6E14F9F15")
    
public boolean performEditorAction(int editorAction) {
        return mTarget.performEditorAction(editorAction);
    }
    
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.105 -0500", hash_original_method = "7FAFD46A8306525DB41EEE6E5B575235", hash_generated_method = "53A7C69D7D45FBC5EEDAE3424F060D0A")
    
public boolean performContextMenuAction(int id) {
        return mTarget.performContextMenuAction(id);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.108 -0500", hash_original_method = "AAA3AB8A2808B740C08D8BCAC6EF593C", hash_generated_method = "CB14BC8C680480BE0F8B9077DD656799")
    
public boolean beginBatchEdit() {
        return mTarget.beginBatchEdit();
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.110 -0500", hash_original_method = "C936839A69151952786EDD11909238AD", hash_generated_method = "BB4861E645C0E9FD305A3472C7BE098E")
    
public boolean endBatchEdit() {
        return mTarget.endBatchEdit();
    }
    
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.113 -0500", hash_original_method = "9D024620F1B53C75FE915B021E654688", hash_generated_method = "420AC69BA51E4382179CE23BC1D8364F")
    
public boolean sendKeyEvent(KeyEvent event) {
        return mTarget.sendKeyEvent(event);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.115 -0500", hash_original_method = "475264A381587BF32EFFB4A3D00FD07C", hash_generated_method = "CF3474EA16DC5715D83EFF79A5957ACD")
    
public boolean clearMetaKeyStates(int states) {
        return mTarget.clearMetaKeyStates(states);
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.118 -0500", hash_original_method = "F1C831DAF80FFACCBD0D484BA3C71C5A", hash_generated_method = "DDEB7325B19843D409D7C28F7A5CFF48")
    
public boolean reportFullscreenMode(boolean enabled) {
        return mTarget.reportFullscreenMode(enabled);
    }
    
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:21.120 -0500", hash_original_method = "E30515F5F44630827F2EF8B8A6D02C04", hash_generated_method = "813CC442176AC2FCDF5D27D20ECA5D63")
    
public boolean performPrivateCommand(String action, Bundle data) {
        return mTarget.performPrivateCommand(action, data);
    }
    
}

