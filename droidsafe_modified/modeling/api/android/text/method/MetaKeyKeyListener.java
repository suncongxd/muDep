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
import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.Spanned;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;

public abstract class MetaKeyKeyListener {

    /**
     * Resets all meta state to inactive.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.498 -0500", hash_original_method = "8AF64E1EA84DF175FDB50570C8CEC5BF", hash_generated_method = "21B6D80DCB62FCFE1606C3D631A5E0D3")
    
public static void resetMetaState(Spannable text) {
        text.removeSpan(CAP);
        text.removeSpan(ALT);
        text.removeSpan(SYM);
        text.removeSpan(SELECTING);
    }

    /**
     * Gets the state of the meta keys.
     * 
     * @param text the buffer in which the meta key would have been pressed.
     *
     * @return an integer in which each bit set to one represents a pressed
     *         or locked meta key.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.501 -0500", hash_original_method = "A3D3969A4E5BE158AB30CE45C881503B", hash_generated_method = "8E6859C3B9FCD0D9EC9A4DF8B984938E")
    
public static final int getMetaState(CharSequence text) {
        return getActive(text, CAP, META_SHIFT_ON, META_CAP_LOCKED) |
               getActive(text, ALT, META_ALT_ON, META_ALT_LOCKED) |
               getActive(text, SYM, META_SYM_ON, META_SYM_LOCKED) |
               getActive(text, SELECTING, META_SELECTING, META_SELECTING);
    }

    /**
     * Gets the state of a particular meta key.
     *
     * @param meta META_SHIFT_ON, META_ALT_ON, META_SYM_ON, or META_SELECTING
     * @param text the buffer in which the meta key would have been pressed.
     *
     * @return 0 if inactive, 1 if active, 2 if locked.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.505 -0500", hash_original_method = "BC173CC5F32A7F644973716C59DAC229", hash_generated_method = "247FA46B8CB62E86008EA7F4E88B98D7")
    
public static final int getMetaState(CharSequence text, int meta) {
        switch (meta) {
            case META_SHIFT_ON:
                return getActive(text, CAP, 1, 2);

            case META_ALT_ON:
                return getActive(text, ALT, 1, 2);

            case META_SYM_ON:
                return getActive(text, SYM, 1, 2);

            case META_SELECTING:
                return getActive(text, SELECTING, 1, 2);

            default:
                return 0;
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.508 -0500", hash_original_method = "DC7E5B320979169FEF23BC2039331A57", hash_generated_method = "A0EED6475A72BC8D44D20E76C1138C0B")
    
private static int getActive(CharSequence text, Object meta,
                                 int on, int lock) {
        if (!(text instanceof Spanned)) {
            return 0;
        }

        Spanned sp = (Spanned) text;
        int flag = sp.getSpanFlags(meta);

        if (flag == LOCKED) {
            return lock;
        } else if (flag != 0) {
            return on;
        } else {
            return 0;
        }
    }

    /**
     * Call this method after you handle a keypress so that the meta
     * state will be reset to unshifted (if it is not still down)
     * or primed to be reset to unshifted (once it is released).
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.512 -0500", hash_original_method = "32802909F6EBAD9D75292258771F56F1", hash_generated_method = "3BA613D5324C3D37BC1D540DE0F7918B")
    
public static void adjustMetaAfterKeypress(Spannable content) {
        adjust(content, CAP);
        adjust(content, ALT);
        adjust(content, SYM);
    }

    /**
     * Returns true if this object is one that this class would use to
     * keep track of any meta state in the specified text.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.515 -0500", hash_original_method = "8BA6018CB8A318230609528649963F72", hash_generated_method = "C565AB3185A2854C0E70614435A35BCC")
    
public static boolean isMetaTracker(CharSequence text, Object what) {
        return what == CAP || what == ALT || what == SYM ||
               what == SELECTING;
    }

    /**
     * Returns true if this object is one that this class would use to
     * keep track of the selecting meta state in the specified text.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.517 -0500", hash_original_method = "F9AD5FB516FC92F512EBE4470BCD6254", hash_generated_method = "D291075F019491CDE84D4F2AF5684185")
    
public static boolean isSelectingMetaTracker(CharSequence text, Object what) {
        return what == SELECTING;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.521 -0500", hash_original_method = "3BF97564F386FB083018A6AA958725D3", hash_generated_method = "5E00F607FF0F01F7DEB1EE4F8138FBE9")
    
private static void adjust(Spannable content, Object what) {
        int current = content.getSpanFlags(what);

        if (current == PRESSED)
            content.setSpan(what, 0, 0, USED);
        else if (current == RELEASED)
            content.removeSpan(what);
    }

    /**
     * Call this if you are a method that ignores the locked meta state
     * (arrow keys, for example) and you handle a key.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.524 -0500", hash_original_method = "85EDDF72490A9BF1E58EACB23506A0DF", hash_generated_method = "D3EF5707B9CCEF81D998C8F2A719679C")
    
protected static void resetLockedMeta(Spannable content) {
        resetLock(content, CAP);
        resetLock(content, ALT);
        resetLock(content, SYM);
        resetLock(content, SELECTING);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.527 -0500", hash_original_method = "F385B7A10FE4F8593841A972238FB62B", hash_generated_method = "B877076FE3F00B59975203659B35F9FA")
    
private static void resetLock(Spannable content, Object what) {
        int current = content.getSpanFlags(what);

        if (current == LOCKED)
            content.removeSpan(what);
    }

    /**
     * Start selecting text.
     * @hide pending API review
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.536 -0500", hash_original_method = "FCE3F0DACA962344761C11774B544CCF", hash_generated_method = "942ABB0BCF583B3FE6A77B7D88C3097A")
    
public static void startSelecting(View view, Spannable content) {
        content.setSpan(SELECTING, 0, 0, PRESSED);
    }

    /**
     * Stop selecting text.  This does not actually collapse the selection;
     * call {@link android.text.Selection#setSelection} too.
     * @hide pending API review
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.539 -0500", hash_original_method = "F1455B908B6FB899340003D4BDACF2B8", hash_generated_method = "FBBD4F88CF121B7225FC51054D2EC033")
    
public static void stopSelecting(View view, Spannable content) {
        content.removeSpan(SELECTING);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.552 -0500", hash_original_method = "3BBDACC08BE9E524CFC4AC125A516AAF", hash_generated_method = "95EE6EA7D6BE7E57EFCD5FCA500C6C89")
    
public static void clearMetaKeyState(Editable content, int states) {
        if ((states&META_SHIFT_ON) != 0) content.removeSpan(CAP);
        if ((states&META_ALT_ON) != 0) content.removeSpan(ALT);
        if ((states&META_SYM_ON) != 0) content.removeSpan(SYM);
        if ((states&META_SELECTING) != 0) content.removeSpan(SELECTING);
    }

    /**
     * Call this if you are a method that ignores the locked meta state
     * (arrow keys, for example) and you handle a key.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.554 -0500", hash_original_method = "0D999DF67FD3DA664CB224325BB4CDF3", hash_generated_method = "BA088125197E885C0F007722531AC093")
    
public static long resetLockedMeta(long state) {
        if ((state & META_CAP_LOCKED) != 0) {
            state &= ~META_SHIFT_MASK;
        }
        if ((state & META_ALT_LOCKED) != 0) {
            state &= ~META_ALT_MASK;
        }
        if ((state & META_SYM_LOCKED) != 0) {
            state &= ~META_SYM_MASK;
        }
        return state;
    }

    // ---------------------------------------------------------------------
    // Version of API that operates on a state bit mask
    // ---------------------------------------------------------------------

    /**
     * Gets the state of the meta keys.
     * 
     * @param state the current meta state bits.
     *
     * @return an integer in which each bit set to one represents a pressed
     *         or locked meta key.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.557 -0500", hash_original_method = "8759945C5194DF9FDA8684974019087A", hash_generated_method = "C6021809A3234588E9A91CEFDF648670")
    
public static final int getMetaState(long state) {
        int result = 0;

        if ((state & META_CAP_LOCKED) != 0) {
            result |= META_CAP_LOCKED;
        } else if ((state & META_SHIFT_ON) != 0) {
            result |= META_SHIFT_ON;
        }

        if ((state & META_ALT_LOCKED) != 0) {
            result |= META_ALT_LOCKED;
        } else if ((state & META_ALT_ON) != 0) {
            result |= META_ALT_ON;
        }

        if ((state & META_SYM_LOCKED) != 0) {
            result |= META_SYM_LOCKED;
        } else if ((state & META_SYM_ON) != 0) {
            result |= META_SYM_ON;
        }

        return result;
    }

    /**
     * Gets the state of a particular meta key.
     *
     * @param state the current state bits.
     * @param meta META_SHIFT_ON, META_ALT_ON, or META_SYM_ON
     *
     * @return 0 if inactive, 1 if active, 2 if locked.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.561 -0500", hash_original_method = "9198ADB45CCF6407614EAE29C1436072", hash_generated_method = "6CB187D36897895866179D6DBBA7DC2E")
    
public static final int getMetaState(long state, int meta) {
        switch (meta) {
            case META_SHIFT_ON:
                if ((state & META_CAP_LOCKED) != 0) return 2;
                if ((state & META_SHIFT_ON) != 0) return 1;
                return 0;

            case META_ALT_ON:
                if ((state & META_ALT_LOCKED) != 0) return 2;
                if ((state & META_ALT_ON) != 0) return 1;
                return 0;

            case META_SYM_ON:
                if ((state & META_SYM_LOCKED) != 0) return 2;
                if ((state & META_SYM_ON) != 0) return 1;
                return 0;

            default:
                return 0;
        }
    }

    /**
     * Call this method after you handle a keypress so that the meta
     * state will be reset to unshifted (if it is not still down)
     * or primed to be reset to unshifted (once it is released).  Takes
     * the current state, returns the new state.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.564 -0500", hash_original_method = "85D05E4D48A450B763B6DAAD57350FCF", hash_generated_method = "55662DC866222A7973725C5362BC602B")
    
public static long adjustMetaAfterKeypress(long state) {
        if ((state & META_CAP_PRESSED) != 0) {
            state = (state & ~META_SHIFT_MASK) | META_SHIFT_ON | META_CAP_USED;
        } else if ((state & META_CAP_RELEASED) != 0) {
            state &= ~META_SHIFT_MASK;
        }

        if ((state & META_ALT_PRESSED) != 0) {
            state = (state & ~META_ALT_MASK) | META_ALT_ON | META_ALT_USED;
        } else if ((state & META_ALT_RELEASED) != 0) {
            state &= ~META_ALT_MASK;
        }

        if ((state & META_SYM_PRESSED) != 0) {
            state = (state & ~META_SYM_MASK) | META_SYM_ON | META_SYM_USED;
        } else if ((state & META_SYM_RELEASED) != 0) {
            state &= ~META_SYM_MASK;
        }
        return state;
    }

    /**
     * Handles presses of the meta keys.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.568 -0500", hash_original_method = "9974C6A3EEB89F79A5040D80CF59B71F", hash_generated_method = "599015E0527571C431A15827FA2EB1AB")
    
public static long handleKeyDown(long state, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            return press(state, META_SHIFT_ON, META_SHIFT_MASK,
                    META_CAP_LOCKED, META_CAP_PRESSED, META_CAP_RELEASED, META_CAP_USED);
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            return press(state, META_ALT_ON, META_ALT_MASK,
                    META_ALT_LOCKED, META_ALT_PRESSED, META_ALT_RELEASED, META_ALT_USED);
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            return press(state, META_SYM_ON, META_SYM_MASK,
                    META_SYM_LOCKED, META_SYM_PRESSED, META_SYM_RELEASED, META_SYM_USED);
        }
        return state;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.572 -0500", hash_original_method = "A4E9062BCF162F20B883EE858916DF72", hash_generated_method = "43D882320186F204DAD07DFBCA9DDBBE")
    
private static long press(long state, int what, long mask,
            long locked, long pressed, long released, long used) {
        if ((state & pressed) != 0) {
            // repeat before use
        } else if ((state & released) != 0) {
            state = (state &~ mask) | what | locked;
        } else if ((state & used) != 0) {
            // repeat after use
        } else if ((state & locked) != 0) {
            state &= ~mask;
        } else {
            state |= what | pressed;
        }
        return state;
    }

    /**
     * Handles release of the meta keys.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.575 -0500", hash_original_method = "2D3829F01E07E80E02365A68C6B16FCB", hash_generated_method = "F10381060CF01AE740B456044757E6C6")
    
public static long handleKeyUp(long state, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            return release(state, META_SHIFT_ON, META_SHIFT_MASK,
                    META_CAP_PRESSED, META_CAP_RELEASED, META_CAP_USED, event);
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            return release(state, META_ALT_ON, META_ALT_MASK,
                    META_ALT_PRESSED, META_ALT_RELEASED, META_ALT_USED, event);
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            return release(state, META_SYM_ON, META_SYM_MASK,
                    META_SYM_PRESSED, META_SYM_RELEASED, META_SYM_USED, event);
        }
        return state;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.578 -0500", hash_original_method = "10C2625F1E262859011D37FDCAF1A563", hash_generated_method = "4588BB36D600BCDD1612130EDB7E26E3")
    
private static long release(long state, int what, long mask,
            long pressed, long released, long used, KeyEvent event) {
        switch (event.getKeyCharacterMap().getModifierBehavior()) {
            case KeyCharacterMap.MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED:
                if ((state & used) != 0) {
                    state &= ~mask;
                } else if ((state & pressed) != 0) {
                    state |= what | released;
                }
                break;

            default:
                state &= ~mask;
                break;
        }
        return state;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.425 -0500", hash_original_field = "55930881330C14F1961FD5DF41287A5A", hash_generated_field = "50FB6A77E614B1ED8B296A53624D1C28")

    public static final int META_SHIFT_ON = KeyEvent.META_SHIFT_ON;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.428 -0500", hash_original_field = "4D81C48597CECC367742F61300E6E108", hash_generated_field = "A7D01BF2B90736D77B6C1B9C8A14691C")

    public static final int META_ALT_ON = KeyEvent.META_ALT_ON;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.431 -0500", hash_original_field = "190BFCC2F5AD415F36F91AD921493EB4", hash_generated_field = "E66DF9B973124964B6BEE6AE2D11CD98")

    public static final int META_SYM_ON = KeyEvent.META_SYM_ON;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.435 -0500", hash_original_field = "344338028108EB01C4E21D2186F362E0", hash_generated_field = "447FC51E6AE0E3B16DE40EBC9DBC960D")

    public static final int META_CAP_LOCKED = KeyEvent.META_CAP_LOCKED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.439 -0500", hash_original_field = "C8CC15804E76D8C7BB7363DB88D09571", hash_generated_field = "B0A84D4062148CA880301CFD297B7593")

    public static final int META_ALT_LOCKED = KeyEvent.META_ALT_LOCKED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.442 -0500", hash_original_field = "8AAB508F52552B26F6FAB76AC3BBB30E", hash_generated_field = "3C3F8EAFF71797500355E92C6A4E9521")

    public static final int META_SYM_LOCKED = KeyEvent.META_SYM_LOCKED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.445 -0500", hash_original_field = "AC03BBDB01A8C51E1B15F6A83D211F4F", hash_generated_field = "33B69A2A6949E560C4902EDA1683DBA6")

    public static final int META_SELECTING = KeyEvent.META_SELECTING;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.448 -0500", hash_original_field = "BC9B07B70F07A87451F4215AD01163D4", hash_generated_field = "2A7F4610C59E61684A2E4D49D62D11E2")

    // They are deliberately assigned values outside of the representable range of an 'int'
    // so as not to conflict with any meta key states publicly defined by KeyEvent.
    private static final long META_CAP_USED = 1L << 32;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.451 -0500", hash_original_field = "47FDBB479FA10F279DA414D7447F13B4", hash_generated_field = "416E18F4CAACD41547A511274199372C")

    private static final long META_ALT_USED = 1L << 33;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.454 -0500", hash_original_field = "F2FEEBE47B284566362EAA68066910FD", hash_generated_field = "F4636ABDD216B71C7F174060386EFF53")

    private static final long META_SYM_USED = 1L << 34;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.457 -0500", hash_original_field = "2B883E72B74504F3D6F1C06B5FF15D13", hash_generated_field = "51CD84D505E6D4831202FD0007A27D43")
    
    private static final long META_CAP_PRESSED = 1L << 40;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.461 -0500", hash_original_field = "C561B766AF8D73247D7C4381F7979903", hash_generated_field = "B17461FACE3B2089532657AE6E58FCC5")

    private static final long META_ALT_PRESSED = 1L << 41;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.463 -0500", hash_original_field = "E21B2FB0B029B875DCC4D9401FE663EE", hash_generated_field = "7B9C1B5E3D25B14EF67E916D0C6FECAF")

    private static final long META_SYM_PRESSED = 1L << 42;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.466 -0500", hash_original_field = "651DC2D25ED152D71C95BA2FEDA12763", hash_generated_field = "65E05A712966D9C21718EAAECA8D41F7")
    
    private static final long META_CAP_RELEASED = 1L << 48;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.469 -0500", hash_original_field = "98CAB7565BF969AF61DD2C32618B0A2D", hash_generated_field = "93CFE14245B5D84462927164A9DC73E4")

    private static final long META_ALT_RELEASED = 1L << 49;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.472 -0500", hash_original_field = "44B28940BF6E15FCE82BE50975B83143", hash_generated_field = "96DB514097F9CB132B57424F1BAC7FB5")

    private static final long META_SYM_RELEASED = 1L << 50;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.477 -0500", hash_original_field = "E189C0EF04B58E622E668AAF67609874", hash_generated_field = "4C64A1C928F979D257FB830FA9A9CA3C")

    private static final long META_SHIFT_MASK = META_SHIFT_ON
            | META_CAP_LOCKED | META_CAP_USED
            | META_CAP_PRESSED | META_CAP_RELEASED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.480 -0500", hash_original_field = "DC1204349C616C97F2EE7D2810E60157", hash_generated_field = "4D41DD10A09A8496CAB18AAC9D3C0814")

    private static final long META_ALT_MASK = META_ALT_ON
            | META_ALT_LOCKED | META_ALT_USED
            | META_ALT_PRESSED | META_ALT_RELEASED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.483 -0500", hash_original_field = "4BC9C9E828ABD3FA322ED6DF41A327CA", hash_generated_field = "5969AB589085121EAD361C0B2E6B806B")

    private static final long META_SYM_MASK = META_SYM_ON
            | META_SYM_LOCKED | META_SYM_USED
            | META_SYM_PRESSED | META_SYM_RELEASED;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.486 -0500", hash_original_field = "B13EF79B0E2F648B78F1E81294ED31A2", hash_generated_field = "32785D8650D9452EBAC9F402F8F67AC3")
    
    private static final Object CAP = new NoCopySpan.Concrete();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.489 -0500", hash_original_field = "ECE87B15F6F8613AE2AC168D37419CC3", hash_generated_field = "B88F90E026963AEBF638FEDCBEAB9636")

    private static final Object ALT = new NoCopySpan.Concrete();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.492 -0500", hash_original_field = "C79D68B1A0E65FFC63EA5B56E372E95D", hash_generated_field = "B87091EF52B26B0E1B6D750562987C06")

    private static final Object SYM = new NoCopySpan.Concrete();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.495 -0500", hash_original_field = "F813306506E5492640B8A70849CEA3D7", hash_generated_field = "3C09E31C92AB1AD9D12FD85F60EB25B1")

    private static final Object SELECTING = new NoCopySpan.Concrete();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.584 -0500", hash_original_field = "E7B6C3ED0A02492EE0EF60C45E833A8A", hash_generated_field = "202BC3B94B3A5615EE126335905630FB")

    private static final int PRESSED = 
        Spannable.SPAN_MARK_MARK | (1 << Spannable.SPAN_USER_SHIFT);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.587 -0500", hash_original_field = "8B98065209A785F928FDD6D6AC63D107", hash_generated_field = "BC66E5DC8BB3C6648B931311058232F7")

    private static final int RELEASED = 
        Spannable.SPAN_MARK_MARK | (2 << Spannable.SPAN_USER_SHIFT);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.590 -0500", hash_original_field = "827E1771DC6AE3C45038CCBFEAF63CC2", hash_generated_field = "C61D469DE0E04078B7D762DE922DEDE9")

    private static final int USED = 
        Spannable.SPAN_MARK_MARK | (3 << Spannable.SPAN_USER_SHIFT);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.593 -0500", hash_original_field = "769A408F76426B77FAABB0670295B9A5", hash_generated_field = "101707FF248EB2BB713DED8E5B8324C3")

    private static final int LOCKED = 
        Spannable.SPAN_MARK_MARK | (4 << Spannable.SPAN_USER_SHIFT);
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:39.374 -0400", hash_original_method = "50168EE5CC1513DB2EA8BA89DC077CDF", hash_generated_method = "50168EE5CC1513DB2EA8BA89DC077CDF")
    public MetaKeyKeyListener ()
    {
        //Synthesized constructor
    }

    /**
     * Handles presses of the meta keys.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.530 -0500", hash_original_method = "624DA77F613D89E75494C45A9B699A03", hash_generated_method = "F9B9D9A532011D3CD57536643DB24851")
    
public boolean onKeyDown(View view, Editable content, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            press(content, CAP);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            press(content, ALT);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            press(content, SYM);
            return true;
        }

        return false; // no super to call through to
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.533 -0500", hash_original_method = "04CA7F5473BAF6356CB45A56F068D921", hash_generated_method = "FC33B1232AEA1E4804E92EDDA441C236")
    
private void press(Editable content, Object what) {
        int state = content.getSpanFlags(what);

        if (state == PRESSED)
            ; // repeat before use
        else if (state == RELEASED)
            content.setSpan(what, 0, 0, LOCKED);
        else if (state == USED)
            ; // repeat after use
        else if (state == LOCKED)
            content.removeSpan(what);
        else
            content.setSpan(what, 0, 0, PRESSED);
    }

    /**
     * Handles release of the meta keys.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.542 -0500", hash_original_method = "B6764DC3BD8B6ED5267E5A941E3F8260", hash_generated_method = "63F6B353EC34D087B37B1C1A1F64F9C8")
    
public boolean onKeyUp(View view, Editable content, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SHIFT_LEFT || keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT) {
            release(content, CAP, event);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_ALT_LEFT || keyCode == KeyEvent.KEYCODE_ALT_RIGHT
                || keyCode == KeyEvent.KEYCODE_NUM) {
            release(content, ALT, event);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_SYM) {
            release(content, SYM, event);
            return true;
        }

        return false; // no super to call through to
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.546 -0500", hash_original_method = "E71400F62D0FFB0C9763693B0DBF0337", hash_generated_method = "EA64849E504B5A3599AE6B7D059FD8E2")
    
private void release(Editable content, Object what, KeyEvent event) {
        int current = content.getSpanFlags(what);

        switch (event.getKeyCharacterMap().getModifierBehavior()) {
            case KeyCharacterMap.MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED:
                if (current == USED)
                    content.removeSpan(what);
                else if (current == PRESSED)
                    content.setSpan(what, 0, 0, RELEASED);
                break;

            default:
                content.removeSpan(what);
                break;
        }
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.548 -0500", hash_original_method = "A6CA9AE4F5FD679B652E94F0868BFDE8", hash_generated_method = "505DE050F2D46843B16D9B2ADB3CDD37")
    
public void clearMetaKeyState(View view, Editable content, int states) {
        clearMetaKeyState(content, states);
    }

    /**
     * Clears the state of the specified meta key if it is locked.
     * @param state the meta key state
     * @param which meta keys to clear, may be a combination of {@link #META_SHIFT_ON},
     * {@link #META_ALT_ON} or {@link #META_SYM_ON}.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:27:58.581 -0500", hash_original_method = "DB6E28AB316658FAB51CA26FDC440ADD", hash_generated_method = "404060AB0856B228DA7A79EF4FA4AA51")
    
public long clearMetaKeyState(long state, int which) {
        if ((which & META_SHIFT_ON) != 0 && (state & META_CAP_LOCKED) != 0) {
            state &= ~META_SHIFT_MASK;
        }
        if ((which & META_ALT_ON) != 0 && (state & META_ALT_LOCKED) != 0) {
            state &= ~META_ALT_MASK;
        }
        if ((which & META_SYM_ON) != 0 && (state & META_SYM_LOCKED) != 0) {
            state &= ~META_SYM_MASK;
        }
        return state;
    }
}

