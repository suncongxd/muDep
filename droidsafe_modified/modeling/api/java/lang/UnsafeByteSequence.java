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


package java.lang;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.nio.charset.Charset;

public class UnsafeByteSequence {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.444 -0500", hash_original_field = "AB97A4156FC1CC1DAF26375194010FF1", hash_generated_field = "4E58ABB9D5068B90ACC1157BF07E4EBB")

    private byte[] bytes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.447 -0500", hash_original_field = "B83BF7ED7F5719DA923E1BC0AC69952B", hash_generated_field = "82CC849FCF58347832EA6BB917282DBE")

    private int count;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.450 -0500", hash_original_method = "3B8A36370055AF819C3748255F1BDC68", hash_generated_method = "1C29F0B0ABC3F87EBEE15D486E829D2F")
    
public UnsafeByteSequence(int initialCapacity) {
        this.bytes = new byte[initialCapacity];
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.452 -0500", hash_original_method = "F417CE3385B772AADA134FBE4FF63C9E", hash_generated_method = "CF994FCC7B87BF04A6C8FECB8C53A973")
    
public int size() {
        return count;
    }

    /**
     * Moves the write pointer back to the beginning of the sequence,
     * but without resizing or reallocating the buffer.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.454 -0500", hash_original_method = "CD6FA0D10C84692422449C0C5D320E8A", hash_generated_method = "EA2B9D548D02D5504810B69D3A228AC6")
    
public void rewind() {
        count = 0;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.457 -0500", hash_original_method = "5B2175FB0361CE80BA769109F54D5CFC", hash_generated_method = "6572D11AE4A9AD80BD83F434F5BEFD81")
    
public void write(byte[] buffer, int offset, int length) {
        if (count + length >= bytes.length) {
            byte[] newBytes = new byte[(count + length) * 2];
            System.arraycopy(bytes, 0, newBytes, 0, count);
            bytes = newBytes;
        }
        System.arraycopy(buffer, offset, bytes, count, length);
        count += length;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.459 -0500", hash_original_method = "CCB897927F45B316C80C49A08CD4BEC5", hash_generated_method = "C2022BD423D4D6D5CB80F6F749E8D6C2")
    
public void write(int b) {
        if (count == bytes.length) {
            byte[] newBytes = new byte[count * 2];
            System.arraycopy(bytes, 0, newBytes, 0, count);
            bytes = newBytes;
        }
        bytes[count++] = (byte) b;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.461 -0500", hash_original_method = "6A961C2A026EF073EA4353A09C4F8B0B", hash_generated_method = "981B3831D2E46865BBD892784E85F418")
    
@FindBugsSuppressWarnings("EI_EXPOSE_REP")
    public byte[] toByteArray() {
        if (count == bytes.length) {
            return bytes;
        }
        byte[] result = new byte[count];
        System.arraycopy(bytes, 0, result, 0, count);
        return result;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:18.464 -0500", hash_original_method = "EBA6423CDD36F9469C412144E3270E61", hash_generated_method = "1ED3A0AB62778D7C396CBC9D276874E0")
    
public String toString(Charset cs) {
        return new String(bytes, 0, count, cs);
    }
    
}

