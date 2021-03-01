/* Licensed to the Apache Software Foundation (ASF) under one or more
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
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.nio;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import libcore.io.SizeOf;

final class DoubleToByteBufferAdapter extends DoubleBuffer {

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.277 -0500", hash_original_method = "B904958394A84B00377E65479CDB1292", hash_generated_method = "C0AAEB24DE5F07DF6C490DE458A2AF7F")
    
static DoubleBuffer asDoubleBuffer(ByteBuffer byteBuffer) {
        ByteBuffer slice = byteBuffer.slice();
        slice.order(byteBuffer.order());
        return new DoubleToByteBufferAdapter(slice);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.274 -0500", hash_original_field = "1550DCB87D4D50D3727BEA306C5BDDD3", hash_generated_field = "34B9235D8E87AE2A76AC711395EEC0EB")

    private  ByteBuffer byteBuffer;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.280 -0500", hash_original_method = "3C3F64E3DFEA19E69D83DE032CD69444", hash_generated_method = "D6CECB6527803B2B3C46C6D02D17738A")
    
private DoubleToByteBufferAdapter(ByteBuffer byteBuffer) {
        super(byteBuffer.capacity() / SizeOf.DOUBLE);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.clear();
        this.effectiveDirectAddress = byteBuffer.effectiveDirectAddress;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.283 -0500", hash_original_method = "66F1F6EEFF46B34AB7A86EE28CB8A669", hash_generated_method = "09C231E31860A1F2EBF10EC1E05FC23A")
    
@Override
    public DoubleBuffer asReadOnlyBuffer() {
        DoubleToByteBufferAdapter buf = new DoubleToByteBufferAdapter(byteBuffer.asReadOnlyBuffer());
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        buf.byteBuffer.order = byteBuffer.order;
        return buf;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.286 -0500", hash_original_method = "A0960685ED8E4B90630D067C0149A7A7", hash_generated_method = "E48B7F828D76D0EE1EFA14D5A1AA94FF")
    
@Override
    public DoubleBuffer compact() {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        byteBuffer.compact();
        byteBuffer.clear();
        position = limit - position;
        limit = capacity;
        mark = UNSET_MARK;
        return this;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.288 -0500", hash_original_method = "FD4932C207B542FC04D0525E47AC3161", hash_generated_method = "4EC2807070A30C697EB089EDDBA1D868")
    
@Override
    public DoubleBuffer duplicate() {
        ByteBuffer bb = byteBuffer.duplicate().order(byteBuffer.order());
        DoubleToByteBufferAdapter buf = new DoubleToByteBufferAdapter(bb);
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        return buf;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.291 -0500", hash_original_method = "0C75853E3C2CF6732FFCF6E98F870BD4", hash_generated_method = "8BFB2D29019C5C0E7D36420740DB24A0")
    
@Override
    public double get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return byteBuffer.getDouble(position++ * SizeOf.DOUBLE);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.293 -0500", hash_original_method = "E6FEAF9DCFF95E07F26C5202FDF2E7B8", hash_generated_method = "F09B0EB843E32BF1958A62C669F9D498")
    
@Override
    public double get(int index) {
        checkIndex(index);
        return byteBuffer.getDouble(index * SizeOf.DOUBLE);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.296 -0500", hash_original_method = "D37CED54394BDE1310F07C3D0EF5D332", hash_generated_method = "6CE4CDD90EE4623799D6D242B18B692B")
    
@Override
    public DoubleBuffer get(double[] dst, int dstOffset, int doubleCount) {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        if (byteBuffer instanceof DirectByteBuffer) {
            ((DirectByteBuffer) byteBuffer).get(dst, dstOffset, doubleCount);
        } else {
            ((HeapByteBuffer) byteBuffer).get(dst, dstOffset, doubleCount);
        }
        this.position += doubleCount;
        return this;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.298 -0500", hash_original_method = "78E98F36E3D964482B56C152BAA7846E", hash_generated_method = "009663403DDC37C5A9CC05093822324F")
    
@Override
    public boolean isDirect() {
        return byteBuffer.isDirect();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.301 -0500", hash_original_method = "A5873C4C7FD9475A7CC3E2D0E938541A", hash_generated_method = "C9CF4EA86C1E45319D8243CAB871AD82")
    
@Override
    public boolean isReadOnly() {
        return byteBuffer.isReadOnly();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.303 -0500", hash_original_method = "DBC83B570884A1A98A3F46EF314D6E4A", hash_generated_method = "72A628D0C0DB04E7966F3ACE077688CC")
    
@Override
    public ByteOrder order() {
        return byteBuffer.order();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.306 -0500", hash_original_method = "74B676FF851868B6C558CA9DCC9A472E", hash_generated_method = "26E96E345AE992B1603A39E5B8693CD0")
    
@Override
    protected double[] protectedArray() {
        throw new UnsupportedOperationException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.308 -0500", hash_original_method = "11D0A2FFE917F2CC89FA6C9EBE3FCE7F", hash_generated_method = "06D077D8B128869B8EAFBB3C6DF9114F")
    
@Override
    protected int protectedArrayOffset() {
        throw new UnsupportedOperationException();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.310 -0500", hash_original_method = "22273AD75DCD1D369E6F6089E4C15AAA", hash_generated_method = "4DE1ADBFC1C0BCD2FC8429D8307DD9AC")
    
@Override
    protected boolean protectedHasArray() {
        return false;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.313 -0500", hash_original_method = "40AF4DBB8CAF825D560827D27834F61A", hash_generated_method = "56C756281100F2E62A89ECCD9A9E2DAD")
    
@Override
    public DoubleBuffer put(double c) {
        if (position == limit) {
            throw new BufferOverflowException();
        }
        byteBuffer.putDouble(position++ * SizeOf.DOUBLE, c);
        return this;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.316 -0500", hash_original_method = "86E534F052F254A7B02EA4C07E94BFA8", hash_generated_method = "CA01EB90D90845CE9B581235FFE7779F")
    
@Override
    public DoubleBuffer put(int index, double c) {
        checkIndex(index);
        byteBuffer.putDouble(index * SizeOf.DOUBLE, c);
        return this;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.318 -0500", hash_original_method = "9EEEA82F785CE6D4D72E12428428FA8D", hash_generated_method = "3C5D1FDB13A699D53E8B20F0E254B34A")
    
@Override
    public DoubleBuffer put(double[] src, int srcOffset, int doubleCount) {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        if (byteBuffer instanceof ReadWriteDirectByteBuffer) {
            ((ReadWriteDirectByteBuffer) byteBuffer).put(src, srcOffset, doubleCount);
        } else {
            ((ReadWriteHeapByteBuffer) byteBuffer).put(src, srcOffset, doubleCount);
        }
        this.position += doubleCount;
        return this;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:03.322 -0500", hash_original_method = "22A6C9D0B842A6C5A7761EE0E7412B6B", hash_generated_method = "0E3CCD78659B36FEC51CF6329FE4CD3F")
    
@Override
    public DoubleBuffer slice() {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        ByteBuffer bb = byteBuffer.slice().order(byteBuffer.order());
        DoubleBuffer result = new DoubleToByteBufferAdapter(bb);
        byteBuffer.clear();
        return result;
    }
    
}

