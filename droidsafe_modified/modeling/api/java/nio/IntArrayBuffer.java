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

abstract class IntArrayBuffer extends IntBuffer {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.776 -0500", hash_original_field = "078CF565A844D74D6949BBCFDBA079B2", hash_generated_field = "04E795690AE1E5BC2E4466B25302D831")

    protected  int[] backingArray;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.779 -0500", hash_original_field = "B7E810BF01B52122CB927525A0CA4721", hash_generated_field = "22BBBB3BDBDCD622FED3EBF1A70B4EC5")

    protected  int offset;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.781 -0500", hash_original_method = "D18B871637BEEEB2AE768346D692C9D7", hash_generated_method = "D18B871637BEEEB2AE768346D692C9D7")
    
IntArrayBuffer(int[] array) {
        this(array.length, array, 0);
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.783 -0500", hash_original_method = "64ADCEABF0045F3DCF099C9C9B4F7808", hash_generated_method = "64ADCEABF0045F3DCF099C9C9B4F7808")
    
IntArrayBuffer(int capacity) {
        this(capacity, new int[capacity], 0);
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.786 -0500", hash_original_method = "8D85757E2A089A86486F1DA1C7B27DB7", hash_generated_method = "8D85757E2A089A86486F1DA1C7B27DB7")
    
IntArrayBuffer(int capacity, int[] backingArray, int offset) {
        super(capacity);
        this.backingArray = backingArray;
        this.offset = offset;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.788 -0500", hash_original_method = "2EA67F38ECDA332174959B54F9DC216E", hash_generated_method = "3F10A0AB64322B81AB92E00508EB5FAB")
    
@Override
    public final int get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return backingArray[offset + position++];
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.792 -0500", hash_original_method = "BB46C2F433DB7E33613C9314C6AAA302", hash_generated_method = "AC450672EEB2AEF0658F2C7F93A558BE")
    
@Override
    public final int get(int index) {
        checkIndex(index);
        return backingArray[offset + index];
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.794 -0500", hash_original_method = "CF9BFE676211E932B27B1BA0A79794E4", hash_generated_method = "2116483C05D44AE48A7FD29B6A81A494")
    
@Override
    public final IntBuffer get(int[] dst, int dstOffset, int intCount) {
        if (intCount > remaining()) {
            throw new BufferUnderflowException();
        }
        System.arraycopy(backingArray, offset + position, dst, dstOffset, intCount);
        position += intCount;
        return this;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.797 -0500", hash_original_method = "5B6C9F359344527666A57577F107BCAE", hash_generated_method = "1EC9A508074FE2792E86546C8EBA9E2B")
    
@Override
    public final boolean isDirect() {
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:01.799 -0500", hash_original_method = "A08DEC6C777D2C1D29E23F2F39FEA4AD", hash_generated_method = "FF53E3A2EB4F54AB08FAE8FA7C0C29DD")
    
@Override
    public final ByteOrder order() {
        return ByteOrder.nativeOrder();
    }
    
}

