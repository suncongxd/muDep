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


package android.nfc.tech;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.util.Log;
import droidsafe.annotations.*;
import java.io.IOException;

import android.nfc.ErrorCodes;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;

public final class NfcF extends BasicTagTechnology {

    /**
     * Get an instance of {@link NfcF} for the given tag.
     * <p>Returns null if {@link NfcF} was not enumerated in {@link Tag#getTechList}.
     * This indicates the tag does not support NFC-F.
     * <p>Does not cause any RF activity and does not block.
     *
     * @param tag an NFC-F compatible tag
     * @return NFC-F object
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.296 -0500", hash_original_method = "01EE8F8F25FC85AEFD4590A65782423B", hash_generated_method = "65D8731C233C576499FFAA97AABF3A1E")
    
public static NfcF get(Tag tag) {
        if (!tag.hasTech(TagTechnology.NFC_F)) return null;
        try {
            return new NfcF(tag);
        } catch (RemoteException e) {
            return null;
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.283 -0500", hash_original_field = "76B42502A850F1BA8F9A78C316486025", hash_generated_field = "BC8F11E4AEAD11E0412B7B53DF0CA6C8")

    private static final String TAG = "NFC";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.285 -0500", hash_original_field = "3A3EF9C6C54546E4BF85A7751A52E607", hash_generated_field = "5704B992AD33B718B3C71C8EF40A32EC")

    public static final String EXTRA_SC = "systemcode";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.288 -0500", hash_original_field = "EB354D25CB69AD72862611E3F61F6F33", hash_generated_field = "34E329AB64B6D1770F31F0C3818A0179")

    public static final String EXTRA_PMM = "pmm";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.291 -0500", hash_original_field = "C464B493AC4D8DC6313AA390DA7A6EC5", hash_generated_field = "8144AE907BE5B2181516DBD1D62E97D6")

    private byte[] mSystemCode = null;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.293 -0500", hash_original_field = "883831788F4B7782C6FB141F06D6641B", hash_generated_field = "D7A1C8F789011716682DAB2FCD913220")

    private byte[] mManufacturer = null;

    /** @hide */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.299 -0500", hash_original_method = "0D9BC91BC56C91CB4B73C77A46406834", hash_generated_method = "BE2E50C5D4F8D9EA2230B6B194FD72CC")
    
public NfcF(Tag tag) throws RemoteException {
        super(tag, TagTechnology.NFC_F);
        Bundle extras = tag.getTechExtras(TagTechnology.NFC_F);
        if (extras != null) {
            mSystemCode = extras.getByteArray(EXTRA_SC);
            mManufacturer = extras.getByteArray(EXTRA_PMM);
        }
    }

    /**
     * Return the System Code bytes from tag discovery.
     *
     * <p>Does not cause any RF activity and does not block.
     *
     * @return System Code bytes
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.301 -0500", hash_original_method = "5593A08645ACC7B6D3C7AFEF98D893C4", hash_generated_method = "9CE4BDDFFE2865B8A00D5D4FDD81E27E")
    
public byte[] getSystemCode() {
      return mSystemCode;
    }

    /**
     * Return the Manufacturer bytes from tag discovery.
     *
     * <p>Does not cause any RF activity and does not block.
     *
     * @return Manufacturer bytes
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.303 -0500", hash_original_method = "674E72909806E7E1411B5B9E47606852", hash_generated_method = "A5414D0E37F77118EDF179C7B49BE366")
    
public byte[] getManufacturer() {
      return mManufacturer;
    }

    /**
     * Send raw NFC-F commands to the tag and receive the response.
     *
     * <p>Applications must not append the SoD (length) or EoD (CRC) to the payload,
     * it will be automatically calculated.
     *
     * <p>Use {@link #getMaxTransceiveLength} to retrieve the maximum amount of bytes
     * that can be sent with {@link #transceive}.
     *
     * <p>This is an I/O operation and will block until complete. It must
     * not be called from the main application thread. A blocked call will be canceled with
     * {@link IOException} if {@link #close} is called from another thread.
     *
     * <p class="note">Requires the {@link android.Manifest.permission#NFC} permission.
     *
     * @param data bytes to send
     * @return bytes received in response
     * @throws TagLostException if the tag leaves the field
     * @throws IOException if there is an I/O failure, or this operation is canceled
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.305 -0500", hash_original_method = "E43990821CE38E04B46B3E901EFDFA24", hash_generated_method = "CC13A29AED45E04C4807822531800242")
    
public byte[] transceive(byte[] data) throws IOException {
        return transceive(data, true);
    }

    /**
     * Return the maximum number of bytes that can be sent with {@link #transceive}.
     * @return the maximum number of bytes that can be sent with {@link #transceive}.
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.307 -0500", hash_original_method = "CF96EF3BF3FCE4DA3D9E7FBB541AEB70", hash_generated_method = "ECC61C711C2C081CEFB91A790D67A4A8")
    
public int getMaxTransceiveLength() {
        return getMaxTransceiveLengthInternal();
    }

    /**
     * Set the {@link #transceive} timeout in milliseconds.
     *
     * <p>The timeout only applies to {@link #transceive} on this object,
     * and is reset to a default value when {@link #close} is called.
     *
     * <p>Setting a longer timeout may be useful when performing
     * transactions that require a long processing time on the tag
     * such as key generation.
     *
     * <p class="note">Requires the {@link android.Manifest.permission#NFC} permission.
     *
     * @param timeout timeout value in milliseconds
     */
    @DSSink({DSSinkKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.309 -0500", hash_original_method = "D0C1DC7ECC9E082014D504E80FD68DDB", hash_generated_method = "1D0EF75D84E1BBF3CF36BCFF44B909AD")
    
public void setTimeout(int timeout) {
        try {
            int err = mTag.getTagService().setTimeout(TagTechnology.NFC_F, timeout);
            if (err != ErrorCodes.SUCCESS) {
                throw new IllegalArgumentException("The supplied timeout is not valid");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "NFC service dead", e);
        }
    }

    /**
     * Get the current {@link #transceive} timeout in milliseconds.
     *
     * <p class="note">Requires the {@link android.Manifest.permission#NFC} permission.
     *
     * @return timeout value in milliseconds
     */
    @DSSource({DSSourceKind.NFC})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:48.312 -0500", hash_original_method = "BA26E553829B17341BEC1294BC995A3C", hash_generated_method = "26151274738C2CA4100F741611DC2854")
    
public int getTimeout() {
        try {
            return mTag.getTagService().getTimeout(TagTechnology.NFC_F);
        } catch (RemoteException e) {
            Log.e(TAG, "NFC service dead", e);
            return 0;
        }
    }
}

