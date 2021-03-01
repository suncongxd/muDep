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
 * Copyright (C) 2009 The Android Open Source Project
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


package android.app.backup;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.FileDescriptor;
import java.io.IOException;

import droidsafe.helpers.DSUtils;

public class BackupDataOutput {
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int ctor(FileDescriptor fd) {
        return fd.getTaintInt();
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void dtor(int mBackupWriter) {
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int writeEntityHeader_native(int mBackupWriter, String key, int dataSize) {
        return (mBackupWriter + key.getTaintInt() + dataSize);
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int writeEntityData_native(int mBackupWriter, byte[] data, int size) {
        return (mBackupWriter + data.getTaintInt() + data[0] + size);
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static void setKeyPrefix_native(int mBackupWriter, String keyPrefix) {
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.323 -0500", hash_original_field = "B54359389579FCBEAD7641AAB8EECDC6", hash_generated_field = "B54359389579FCBEAD7641AAB8EECDC6")

    int mBackupWriter;

    /** @hide */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.326 -0500", hash_original_method = "519CBE74D64F306E29EEFC8CCFEDDE23", hash_generated_method = "A10EF57C1471170377D679F97F8CF700")
    
public BackupDataOutput(FileDescriptor fd) {
        if (fd == null) throw new NullPointerException();
        mBackupWriter = ctor(fd);
        if (mBackupWriter == 0) {
            throw new RuntimeException("Native initialization failed with fd=" + fd);
        }
    }

    /**
     * Mark the beginning of one record in the backup data stream. This must be called before
     * {@link #writeEntityData}.
     * @param key A string key that uniquely identifies the data record within the application
     * @param dataSize The size in bytes of this record's data.  Passing a dataSize
     *    of -1 indicates that the record under this key should be deleted.
     * @return The number of bytes written to the backup stream
     * @throws IOException if the write failed
     */
    @DSComment("Backup subsystem")
    @DSSpec(DSCat.BACKUP_SUBSYSTEM)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.328 -0500", hash_original_method = "64DACF34CA37B4EE5BF2F213E613C915", hash_generated_method = "D2499C3A5BB8DA1EA6861FFEBC25BB19")
    
public int writeEntityHeader(String key, int dataSize) throws IOException {
        int result = writeEntityHeader_native(mBackupWriter, key, dataSize);
        if (result >= 0) {
            return result;
        } else {
            throw new IOException("result=0x" + Integer.toHexString(result));
        }
    }

    /**
     * Write a chunk of data under the current entity to the backup transport.
     * @param data A raw data buffer to send
     * @param size The number of bytes to be sent in this chunk
     * @return the number of bytes written
     * @throws IOException if the write failed
     */
    @DSComment("Backup subsystem")
    @DSSpec(DSCat.BACKUP_SUBSYSTEM)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.332 -0500", hash_original_method = "1F7BD9065C295005115986DC5C5FEFFC", hash_generated_method = "79DE7E7815DF8F409EA7961CDB1960A7")
    
public int writeEntityData(byte[] data, int size) throws IOException {
        int result = writeEntityData_native(mBackupWriter, data, size);
        if (result >= 0) {
            return result;
        } else {
            throw new IOException("result=0x" + Integer.toHexString(result));
        }
    }

    /** @hide */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.335 -0500", hash_original_method = "BD5511530074677855FB5F031E6A0A48", hash_generated_method = "BA9AA9367BD1C1EAD3DB26039BF520B2")
    
public void setKeyPrefix(String keyPrefix) {
        setKeyPrefix_native(mBackupWriter, keyPrefix);
    }

    /** @hide */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:35:47.338 -0500", hash_original_method = "7380B31B3FE738857D26F18ED18C23C7", hash_generated_method = "C675161230185E2CCCC1C9197E9D6786")
    
protected void finalize() throws Throwable {
        try {
            dtor(mBackupWriter);
        } finally {
            super.finalize();
        }
    }
    
}

