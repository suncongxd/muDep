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


package libcore.io;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class BufferIterator {
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:23.996 -0400", hash_original_method = "D18BB79D0D4A277413AB03F78A6D226A", hash_generated_method = "D18BB79D0D4A277413AB03F78A6D226A")
    public BufferIterator ()
    {
        //Synthesized constructor
    }
    /**
     * Seeks to the absolute position {@code offset}, measured in bytes from the start.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.179 -0500", hash_original_method = "B1655DB4B03BAF0CB71C346A2CB0A771", hash_generated_method = "35BD1C3575B86A0A46D1AF8BA93E0AF9")
    
public abstract void seek(int offset);

    /**
     * Skips forwards or backwards {@code byteCount} bytes from the current position.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.181 -0500", hash_original_method = "CC5615186705CFDADB8C29CFF5127EEC", hash_generated_method = "A60039E417E9A76F3DC099155D350439")
    
public abstract void skip(int byteCount);

    /**
     * Copies {@code byteCount} bytes from the current position into {@code dst}, starting at
     * {@code dstOffset}, and advances the current position {@code byteCount} bytes.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.184 -0500", hash_original_method = "5B01B26AB573F1731BA9CC67B9FD2A67", hash_generated_method = "F399B0B9F8DEEBF1FFE942EC22E65968")
    
public abstract void readByteArray(byte[] dst, int dstOffset, int byteCount);

    /**
     * Returns the byte at the current position, and advances the current position one byte.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.187 -0500", hash_original_method = "312C8CD55B22F35D0367E41AADEB2D90", hash_generated_method = "995CB8823C249C5AD966F3422FBB53EA")
    
public abstract byte readByte();

    /**
     * Returns the 32-bit int at the current position, and advances the current position four bytes.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.189 -0500", hash_original_method = "97FB920EA9544B24495059FF8518145E", hash_generated_method = "F9CE40910F4BE47045E60285653C98F0")
    
public abstract int readInt();

    /**
     * Copies {@code intCount} 32-bit ints from the current position into {@code dst}, starting at
     * {@code dstOffset}, and advances the current position {@code 4 * intCount} bytes.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.191 -0500", hash_original_method = "25B81B8C8DB0E69F07028BC2D8BF5A20", hash_generated_method = "10897E9DD414F65452F9CE1BC92526E3")
    
public abstract void readIntArray(int[] dst, int dstOffset, int intCount);

    /**
     * Returns the 16-bit short at the current position, and advances the current position two bytes.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:28.193 -0500", hash_original_method = "79F6E5E535CEEE85EF82C58CF2026A77", hash_generated_method = "20EB2E694EC60D5039A074E2AD400C12")
    
public abstract short readShort();
    
}

