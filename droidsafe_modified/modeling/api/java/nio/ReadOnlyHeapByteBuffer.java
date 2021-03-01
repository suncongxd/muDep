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

final class ReadOnlyHeapByteBuffer extends HeapByteBuffer {

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.685 -0500", hash_original_method = "F87692C45C0A8D0A5A75D4739DA65D10", hash_generated_method = "7B0FE031CEBD49E576F2A1EE4DB3CEC6")
    
static ReadOnlyHeapByteBuffer copy(HeapByteBuffer other, int markOfOther) {
        ReadOnlyHeapByteBuffer buf =
                new ReadOnlyHeapByteBuffer(other.backingArray, other.capacity(), other.offset);
        buf.limit = other.limit;
        buf.position = other.position();
        buf.mark = markOfOther;
        return buf;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.687 -0500", hash_original_method = "2F2ACF3B9ABB2D0DAB5827DF665B4EAD", hash_generated_method = "2F2ACF3B9ABB2D0DAB5827DF665B4EAD")
    
ReadOnlyHeapByteBuffer(byte[] backingArray, int capacity, int arrayOffset) {
        super(backingArray, capacity, arrayOffset);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.690 -0500", hash_original_method = "DAFC57596AF38F7142398219C2B48840", hash_generated_method = "855A6B8048FC6C558B5B12C48BFA9A0C")
    
@Override
    public ByteBuffer asReadOnlyBuffer() {
        return ReadOnlyHeapByteBuffer.copy(this, mark);
    }

    @DSSafe(DSCat.MEM_BUFFER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.692 -0500", hash_original_method = "E9BD68C2A3E399E8B6F437AA7B03F4EC", hash_generated_method = "077F04774A20C0082DD03C3E2A797A75")
    
@Override
    public ByteBuffer compact() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.695 -0500", hash_original_method = "C2883EB2B7E86A704D76356E1AAB194F", hash_generated_method = "ED4EF02B9858C417E9309F42C851963A")
    
@Override
    public ByteBuffer duplicate() {
        return copy(this, mark);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.697 -0500", hash_original_method = "D0F583BEFACE4F70BE4011CED42FF4E6", hash_generated_method = "E930BFBB15BF0F3A3E2A714B41BC0488")
    
@Override
    public boolean isReadOnly() {
        return true;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.700 -0500", hash_original_method = "722C56C70277A2AF29EF09DE3FC2E957", hash_generated_method = "2DB086672856E18F41212E7A3F2DAB23")
    
@Override
    protected byte[] protectedArray() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.702 -0500", hash_original_method = "0ABFB09F32C9F0D211E0D26315A037E6", hash_generated_method = "74EBBCBD37A62C7AD6A8876E571480EF")
    
@Override
    protected int protectedArrayOffset() {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.705 -0500", hash_original_method = "22273AD75DCD1D369E6F6089E4C15AAA", hash_generated_method = "4DE1ADBFC1C0BCD2FC8429D8307DD9AC")
    
@Override
    protected boolean protectedHasArray() {
        return false;
    }

    @DSSafe(DSCat.MEM_BUFFER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.707 -0500", hash_original_method = "F6BE9BC005619EB0DCAA2A874D1401FC", hash_generated_method = "D4E8436B32EFF0936500D8D699AC6CF4")
    
@Override
    public ByteBuffer put(byte b) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.MEM_BUFFER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.709 -0500", hash_original_method = "3C5952D8FAADE293E2AA7CAEA3FBDFDE", hash_generated_method = "1412C4604D38DE87742B28C650760662")
    
@Override
    public ByteBuffer put(int index, byte b) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.MEM_BUFFER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.712 -0500", hash_original_method = "951D4EAC88501D9206E4E1A989B35D3E", hash_generated_method = "D831EC916321DB52F3301DB5CD73F5BC")
    
@Override
    public ByteBuffer put(byte[] src, int srcOffset, int byteCount) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.716 -0500", hash_original_method = "A805DD85D5AD0B04A08295FCAF525294", hash_generated_method = "500142933DBF90738A4C8B8E723B3FD8")
    
@Override
    public ByteBuffer putDouble(double value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.718 -0500", hash_original_method = "B21C449764944752357064F64E16A04C", hash_generated_method = "25D87BF619D9A032E4C6E37081D3BE62")
    
@Override
    public ByteBuffer putDouble(int index, double value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.721 -0500", hash_original_method = "0936EB19B88AD6A1540E67EF21842269", hash_generated_method = "42A0CD0CCFB6AC96CE2EFD7E58C675DB")
    
@Override
    public ByteBuffer putFloat(float value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.723 -0500", hash_original_method = "6EDCFEA9CE18908F355474CE24EE73F3", hash_generated_method = "565D0D9F5C7022BFF1A842A5DE683C81")
    
@Override
    public ByteBuffer putFloat(int index, float value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.726 -0500", hash_original_method = "6D4DC7AF194DC54CDF161D65F1FDDEA8", hash_generated_method = "14CCA7A20DA7D5549659417F7415D506")
    
@Override
    public ByteBuffer putInt(int value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.728 -0500", hash_original_method = "596A6D4589A1478BD0E76A52D09F2DF6", hash_generated_method = "B007FD7EF205E7C38B39788290D7C31B")
    
@Override
    public ByteBuffer putInt(int index, int value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.731 -0500", hash_original_method = "6CDC02834F940A2320A4AB5E7F512207", hash_generated_method = "F9756E5053DFED99410C20BD46BC79DE")
    
@Override
    public ByteBuffer putLong(int index, long value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.733 -0500", hash_original_method = "4935387395F1528D3182F7E3202284B4", hash_generated_method = "2054D951AA79E2D2781A2ADE108069E1")
    
@Override
    public ByteBuffer putLong(long value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.736 -0500", hash_original_method = "3FFCBC2284BD734FFA5002B7F6F7C258", hash_generated_method = "11BAA8664AAE5B9374F256E78204A00A")
    
@Override
    public ByteBuffer putShort(int index, short value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.738 -0500", hash_original_method = "1212B9E4C2EAD5C33D73EC873813CB4B", hash_generated_method = "879817F88E6064FC080E2F8BDD54F069")
    
@Override
    public ByteBuffer putShort(short value) {
        throw new ReadOnlyBufferException();
    }

    @DSSafe(DSCat.MEM_BUFFER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.741 -0500", hash_original_method = "927CD040BA02D17CD9D13CDD471B3CCF", hash_generated_method = "69DF853CF3CDE579BA901FAE444AE4A5")
    
@Override
    public ByteBuffer put(ByteBuffer buf) {
        throw new ReadOnlyBufferException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:52.743 -0500", hash_original_method = "6FE9FDD30B055D5D0C17F2FBAA978884", hash_generated_method = "D775BE2A99B3C371F4C624E5CBA19BEE")
    
@Override
    public ByteBuffer slice() {
        return new ReadOnlyHeapByteBuffer(backingArray, remaining(), offset + position);
    }
    
}

