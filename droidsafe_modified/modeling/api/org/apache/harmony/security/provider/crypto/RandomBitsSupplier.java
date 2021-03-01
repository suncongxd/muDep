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
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */



/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.harmony.security.provider.crypto;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.ProviderException;

public class RandomBitsSupplier implements SHA1_Data {

    /**
     * The method is called by provider to determine if a device is available.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.427 -0500", hash_original_method = "3259964D642F1E81BEF73199EA8A73AC", hash_generated_method = "9E5E54730E70BE586A1A95C1A642A8DE")
    
static boolean isServiceAvailable() {
        return serviceAvailable;
    }

    /**
     * On platforms with "random" devices available,
     * the method reads random bytes from the device.  <BR>
     *
     * In case of any runtime failure ProviderException gets thrown.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.430 -0500", hash_original_method = "A38BCE2020D253BC2B60805DDBFC4023", hash_generated_method = "195046E5C35B050C5174F241E9486995")
    
private static synchronized byte[] getUnixDeviceRandom(int numBytes) {

        byte[] bytes = new byte[numBytes];

        int total = 0;
        int bytesRead;
        int offset = 0;
        try {
            for ( ; ; ) {

                bytesRead = fis.read(bytes, offset, numBytes-total);

                // the below case should not occur because /dev/random or /dev/urandom is a special file
                // hence, if it is happened there is some internal problem
                if ( bytesRead == -1 ) {
                    throw new ProviderException("bytesRead == -1");
                }

                total  += bytesRead;
                offset += bytesRead;

                if ( total >= numBytes ) {
                    break;
                }
            }
        } catch (IOException e) {

            // actually there should be no IOException because device is a special file;
            // hence, there is either some internal problem or, for instance,
            // device was removed in runtime, or something else
            throw new ProviderException("ATTENTION: IOException in RandomBitsSupplier.getLinuxRandomBits(): " + e);
        }
        return bytes;
    }

    /**
     * The method returns byte array of requested length provided service is available.
     * ProviderException gets thrown otherwise.
     *
     * @param
     *       numBytes - length of bytes requested
     * @return
     *       byte array
     * @throws
     *       InvalidArgumentException - if numBytes <= 0
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.433 -0500", hash_original_method = "AC6ABEE79D7DD42398E45F6C2A11844E", hash_generated_method = "AD19D944B8C9506DD38C71483134531F")
    
public static byte[] getRandomBits(int numBytes) {
        if (numBytes <= 0) {
            throw new IllegalArgumentException(Integer.toString(numBytes));
        }

        // We have been unable to get a random device or fall back to the
        // native security module code - throw an exception.
        if ( !serviceAvailable ) {
            throw new ProviderException("ATTENTION: service is not available : no random devices");
        }

        return getUnixDeviceRandom(numBytes);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.417 -0500", hash_original_field = "670080ABF3760AFAA308F08D01CEFE67", hash_generated_field = "5953C13EEC4748E72DBEEA524CB92C42")

    private static FileInputStream fis = null;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.419 -0500", hash_original_field = "49CB41E63418DDFE0D00335A2EC6CF12", hash_generated_field = "8AB48E545C9A9079E8984894A324FFDB")

    private static File randomFile = null;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.422 -0500", hash_original_field = "9218B431CE7CD6BA2016BBED0F446830", hash_generated_field = "311846FC929AEDC17ACEFCD58751F46E")

    private static boolean serviceAvailable = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:03.425 -0500", hash_original_field = "6B2F18B12A9932334E3A5FCC8A9EED3C", hash_generated_field = "5BCAD3A625DEF166032C261CBA27C9CF")

    private static final String DEVICE_NAMES[] = { "/dev/urandom" /*, "/dev/random" */ };
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:28.222 -0400", hash_original_method = "C2EAC1E8FB299AA5C7A70ED055C01714", hash_generated_method = "C2EAC1E8FB299AA5C7A70ED055C01714")
    public RandomBitsSupplier ()
    {
        //Synthesized constructor
    }
    static {
        for (String deviceName : DEVICE_NAMES) {
            try {
                File file = new File(deviceName);
                if (file.canRead()) {
                    fis = new FileInputStream(file);
                    randomFile = file;
                    serviceAvailable = true;
                }
            } catch (FileNotFoundException e) {
            }
        }
    }
    
}

