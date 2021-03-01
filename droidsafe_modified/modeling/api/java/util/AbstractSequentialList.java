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


package java.util;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class AbstractSequentialList<E> extends AbstractList<E> {

    /**
     * Constructs a new instance of this AbstractSequentialList.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.103 -0500", hash_original_method = "E95CC14AC93438CE9E4618C927D37CA8", hash_generated_method = "B04098F93E3E9654690AFB5E709BB77E")
    
protected AbstractSequentialList() {
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.105 -0500", hash_original_method = "0430D75F7F3D5E3A6D5F6B220D260C02", hash_generated_method = "64BC5CD164AE9351910CD21AB37E9EB1")
    
@Override
    public void add(int location, E object) {
        addElementAt(location, object);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.108 -0500", hash_original_method = "252A37CEEA1A0797639D5FF26661E00A", hash_generated_method = "C05BA4B15514461480E933AFEED877BD")
    
@Override
    public boolean addAll(int location, Collection<? extends E> collection) {
        return super.addAll(location, collection);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.110 -0500", hash_original_method = "B5F59D8654788FE80539C2042F9F3DA0", hash_generated_method = "B64B7ED54C32A134E892BDDE7873952A")
    
@Override
    public E get(int location) {
        return getElementAt(location);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.113 -0500", hash_original_method = "1D00ECD22B3575A885077212162F97B2", hash_generated_method = "F8A35E883987D86B173CE6E6651B4120")
    
@Override
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.115 -0500", hash_original_method = "8817022DCE1939D78B58CBD817768953", hash_generated_method = "121B63D82A543223901DFF56B53DE100")
    
@Override
    public abstract ListIterator<E> listIterator(int location);

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.117 -0500", hash_original_method = "F4326C1F9DDC82196649CFA76C7956BB", hash_generated_method = "A2F888213920404B25010F9F3D5F5D32")
    
@Override
    public E remove(int location) {
        return super.remove(location);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:43.120 -0500", hash_original_method = "A0691253183890C7E0DBA0D74DD52EDC", hash_generated_method = "6F75963FFA57E8B5B5D8C57390534586")
    
@Override
    public E set(int location, E object) {
        return super.set(location, object);
    }
    
}

