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


package java.nio;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

final class ReadOnlyLongArrayBuffer extends LongArrayBuffer {

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.144 -0500", hash_original_method = "4E6C468E7026EB60D76A14D272ED9069", hash_generated_method = "5D090BD2D1376E282E6CB3E4F11FF037")
    
static ReadOnlyLongArrayBuffer copy(LongArrayBuffer other, int markOfOther) {
        ReadOnlyLongArrayBuffer buf =
                new ReadOnlyLongArrayBuffer(other.capacity(), other.backingArray, other.offset);
        buf.limit = other.limit;
        buf.position = other.position();
        buf.mark = markOfOther;
        return buf;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.147 -0500", hash_original_method = "C217C684C5DFBD85DAC953DA8CC4F355", hash_generated_method = "C217C684C5DFBD85DAC953DA8CC4F355")
    
ReadOnlyLongArrayBuffer(int capacity, long[] backingArray, int arrayOffset) {
        super(capacity, backingArray, arrayOffset);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.149 -0500", hash_original_method = "9283ADC80310F7E479295F96D960F17D", hash_generated_method = "85D5B9839D5ED6379D1BE9575AC6A34E")
    
@Override
    public LongBuffer asReadOnlyBuffer() {
        return duplicate();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.151 -0500", hash_original_method = "0AF2CD05C541DF81A768714F9C7047F3", hash_generated_method = "8EFDA4DBCAEA9FBE2CB68C5C0EADDE2B")
    
@Override
    public LongBuffer compact() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.153 -0500", hash_original_method = "BCAEF14AE65D2F213653B4CB45E8B265", hash_generated_method = "C800A40BEC6C63B069051B527F16F425")
    
@Override
    public LongBuffer duplicate() {
        return copy(this, mark);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.156 -0500", hash_original_method = "D0F583BEFACE4F70BE4011CED42FF4E6", hash_generated_method = "E930BFBB15BF0F3A3E2A714B41BC0488")
    
@Override
    public boolean isReadOnly() {
        return true;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.158 -0500", hash_original_method = "4182362C5B9492F840FDEB2D9E41E07C", hash_generated_method = "314E8FC243271486795F8FB51FB1276F")
    
@Override
    protected long[] protectedArray() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.161 -0500", hash_original_method = "0ABFB09F32C9F0D211E0D26315A037E6", hash_generated_method = "74EBBCBD37A62C7AD6A8876E571480EF")
    
@Override
    protected int protectedArrayOffset() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.164 -0500", hash_original_method = "22273AD75DCD1D369E6F6089E4C15AAA", hash_generated_method = "4DE1ADBFC1C0BCD2FC8429D8307DD9AC")
    
@Override
    protected boolean protectedHasArray() {
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.166 -0500", hash_original_method = "7393DFCF52569FCC2605F08F020574B2", hash_generated_method = "385304DD6D4D8544F3D9D9C4D368D68A")
    
@Override
    public LongBuffer put(long c) {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.168 -0500", hash_original_method = "E5FA3278A640CB37396E7D9BDEEA6C53", hash_generated_method = "DD7FC024DC60D26A6FAA95E3EED6807C")
    
@Override
    public LongBuffer put(int index, long c) {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.171 -0500", hash_original_method = "30B5D6EAC290C108C17070B0EA497381", hash_generated_method = "A32F41C7F17CA38389414917422C74DC")
    
@Override
    public LongBuffer put(LongBuffer buf) {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.174 -0500", hash_original_method = "216FEF8EB820A47DE670615020EB98B3", hash_generated_method = "A18D32D6F624A28DB3D70B187533371F")
    
@Override
    public final LongBuffer put(long[] src, int srcOffset, int longCount) {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:55.177 -0500", hash_original_method = "B55D734F8AFBBB86296CAB7433B17B3B", hash_generated_method = "DB1604BFCBF4BAA3B3196EE57458FBF1")
    
@Override
    public LongBuffer slice() {
        return new ReadOnlyLongArrayBuffer(remaining(), backingArray, offset + position);
    }
    
}

