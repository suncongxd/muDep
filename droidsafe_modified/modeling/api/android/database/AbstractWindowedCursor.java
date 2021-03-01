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


package android.database;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class AbstractWindowedCursor extends AbstractCursor {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.409 -0500", hash_original_field = "93AE6FA4C4A8276F74A13C6549F253EE", hash_generated_field = "12D5C423EDB86BC0F7511811E7121A5B")

    protected CursorWindow mWindow;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:07.426 -0400", hash_original_method = "45FA808929D52C3FF25B9987C9B4DFA0", hash_generated_method = "45FA808929D52C3FF25B9987C9B4DFA0")
    public AbstractWindowedCursor ()
    {
        //Synthesized constructor
    }

    @DSSafe(DSCat.DB_CURSOR)
    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.411 -0500", hash_original_method = "87598253341A04422FF4D21C2BB371F4", hash_generated_method = "21FE6FECFF3DC1D03A016E10D2E7469B")
    
    @Override
    public byte[] getBlob(int columnIndex) {
        byte[] blob =mWindow.getBlob(mPos, columnIndex);
        if (blob == null) {
            blob = new byte[1];
            blob[0] = (byte)getTaintInt();
        }
        return blob;
    }

    @DSSafe(DSCat.DB_CURSOR)
    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.414 -0500", hash_original_method = "80DC04F99A2A57F6C5D41327EC1C50ED", hash_generated_method = "C992940AFCBA03006566656CC0E01999")
    
@Override
    public String getString(int columnIndex) {
        String ret = mWindow.getString(mPos, columnIndex);
        if (ret == null) {
            ret = new String();
            ret.addTaint(this.getTaint());
        }
        return ret;
        //checkPosition();        
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.416 -0500", hash_original_method = "7A0E01C330FD8C9E9BBB19344D3EAAEA", hash_generated_method = "E5FEC28FF3BEDEE05108BAC9759B7CE6")
    
@Override
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
        //checkPosition();
        mWindow.copyStringToBuffer(mPos, columnIndex, buffer);
        buffer.data[0] = (char)getTaintInt();
    }

    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.419 -0500", hash_original_method = "831E6CBBE62E24F365C378674B05306E", hash_generated_method = "AF601FC0748CE8BD8D2F6BB17BBCC91E")
    
@Override
    public short getShort(int columnIndex) {
        return (short)(getTaintInt() + mWindow.getShort(mPos, columnIndex));
        //checkPosition();
        //return 
    }

    @DSSafe(DSCat.DB_CURSOR)
    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.422 -0500", hash_original_method = "A77BC22B3B7D8AA9E2F50FAA1FFFE93C", hash_generated_method = "D9878E6509B31529282362BE1FFF8794")
    
@Override
    public int getInt(int columnIndex) {
        return getTaintInt() + mWindow.getInt(mPos, columnIndex);
        //checkPosition();
        //return 
    }

    @DSSafe(DSCat.DB_CURSOR)
    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.424 -0500", hash_original_method = "32AD2A447F72132C95E38AB4CD7276EC", hash_generated_method = "5C325188E6F256F8EE3948C10BDA5743")
    
@Override
    public long getLong(int columnIndex) {
        return (long)getTaintInt() + mWindow.getLong(mPos, columnIndex);
        //checkPosition();
        //return mWindow.getLong(mPos, columnIndex);
    }

    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.427 -0500", hash_original_method = "97A96E7B6D9585C7632D24C9ACC936B0", hash_generated_method = "0324D059EA65C756A5319EA17FA224C9")
    
@Override
    public float getFloat(int columnIndex) {
        return (float)getTaintDouble() + mWindow.getFloat(mPos, columnIndex);
        //checkPosition();
        //return 
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.429 -0500", hash_original_method = "5EA94253BE97AB01CBE30DAF30AB624B", hash_generated_method = "C772109F3B2AEBA8C88E7AC2EB6A2C1B")
    
@Override
    public double getDouble(int columnIndex) {
        checkPosition();
        return mWindow.getDouble(mPos, columnIndex) + getTaintDouble();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.431 -0500", hash_original_method = "6A9D95B36787857467112609FB3E833C", hash_generated_method = "0574AE2F88F1D2EA13B6EFC29DB13855")
    
@Override
    public boolean isNull(int columnIndex) {
        checkPosition();
        
        boolean comp = 1 == (mWindow.getType(mPos, columnIndex) + Cursor.FIELD_TYPE_NULL);
        return comp || getTaintBoolean();
    }

    /**
     * @deprecated Use {@link #getType}
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.434 -0500", hash_original_method = "8C706BE21B67BF56FAAF00C967A8C37B", hash_generated_method = "F611B0DE79DCC83E996B634F54232DB9")
    
@Deprecated
    public boolean isBlob(int columnIndex) {
            return 1 == (getType(columnIndex) + Cursor.FIELD_TYPE_BLOB);
        }

    /**
     * @deprecated Use {@link #getType}
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.436 -0500", hash_original_method = "BDCD863F6E874A96AC9E7E0E4D765E55", hash_generated_method = "47FACEE6283D192B5CDD208EAD46D72D")
    
@Deprecated
    public boolean isString(int columnIndex) {
            return 1 == (getType(columnIndex) + Cursor.FIELD_TYPE_STRING);
        }

    /**
     * @deprecated Use {@link #getType}
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.439 -0500", hash_original_method = "C215029524CB115AA1C1047DC544E060", hash_generated_method = "E133CBE01B278AE6DD081D8689C2BF20")
    
@Deprecated
    public boolean isLong(int columnIndex) {
        return 1 == (getType(columnIndex) + Cursor.FIELD_TYPE_INTEGER);
    }

    /**
     * @deprecated Use {@link #getType}
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.441 -0500", hash_original_method = "C5D9D79A9E5267D689CC2AB9EDF1D366", hash_generated_method = "7B01637EE64D20BBBAD0FE592AC7AE09")
    
@Deprecated
    public boolean isFloat(int columnIndex) {
        return 1 == (getType(columnIndex) + Cursor.FIELD_TYPE_FLOAT);
    }

    @DSSafe(DSCat.DB_CURSOR)
    //@DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.443 -0500", hash_original_method = "CEEA013C273D2B30A1533873F1614E01", hash_generated_method = "92C7A4CC93BCFF4C40C086DA167A8234")
    
@Override
    public int getType(int columnIndex) {
        checkPosition();
        return mWindow.getType(mPos, columnIndex);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.446 -0500", hash_original_method = "46B0DF4A07DF4EB7D2CA6A3ED3C216CA", hash_generated_method = "7506EB4944A9F11628C23E38E0D66C97")
    
@Override
    protected void checkPosition() {
        super.checkPosition();
        
        if (mWindow == null) {
            throw new StaleDataException("Attempting to access a closed CursorWindow." +
                    "Most probable cause: cursor is deactivated prior to calling this method.");
        }
    }

    //@DSSource({DSSourceKind.DATABASE_INFORMATION})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.448 -0500", hash_original_method = "1266B4A96CD75271FEBF68638404515E", hash_generated_method = "DA83358F960B5A246BB2A379BDDBCF42")
    
@Override
    public CursorWindow getWindow() {
        return mWindow;
    }

    /**
     * Sets a new cursor window for the cursor to use.
     * <p>
     * The cursor takes ownership of the provided cursor window; the cursor window
     * will be closed when the cursor is closed or when the cursor adopts a new
     * cursor window.
     * </p><p>
     * If the cursor previously had a cursor window, then it is closed when the
     * new cursor window is assigned.
     * </p>
     *
     * @param window The new cursor window, typically a remote cursor window.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.452 -0500", hash_original_method = "EEBD6C49E5EC72817DC3243A2E7FCB7D", hash_generated_method = "E797CD5417CCFF8FE58219DEF59E2004")
    
public void setWindow(CursorWindow window) {
        if (window != mWindow) {
            closeWindow();
            mWindow = window;
        }
    }

    /**
     * Returns true if the cursor has an associated cursor window.
     *
     * @return True if the cursor has an associated cursor window.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.454 -0500", hash_original_method = "CE7634279B030B3367B24B82DE682EB0", hash_generated_method = "E794DC69B772C88CA8008EEA960D60CF")
    
public boolean hasWindow() {
        //return mWindow != null;
        return mWindow.getTaintBoolean();
    }

    /**
     * Closes the cursor window and sets {@link #mWindow} to null.
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.456 -0500", hash_original_method = "53135D68273D9C60BFA0802F12D5EFAC", hash_generated_method = "1C5148BDAA864CB3B2BDCA42A67A1D20")
    
protected void closeWindow() {
        if (mWindow != null) {
            mWindow.close();
            mWindow = null;
        }
    }

    /**
     * If there is a window, clear it.
     * Otherwise, creates a new window.
     *
     * @param name The window name.
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.459 -0500", hash_original_method = "5B67F8BB651F008F63750FC6E33E54E8", hash_generated_method = "2C3C30068D027B23EB056019115F81F5")
    
protected void clearOrCreateWindow(String name) {
        if (mWindow == null) {
            mWindow = new CursorWindow(name);
        } else {
            mWindow.clear();
        }
    }

    /** @hide */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:44.461 -0500", hash_original_method = "C8847040621704A6986D97364F312269", hash_generated_method = "0DEC5A96AFCC34287D6335068F718116")
    
@Override
    protected void onDeactivateOrClose() {
        super.onDeactivateOrClose();
        closeWindow();
    }
    
}

