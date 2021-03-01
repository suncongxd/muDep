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
 * Licensed to the Apache Software Foundation (ASF) under one or more
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
/*
 * Copyright (C) 2008 The Android Open Source Project
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


package java.lang.ref;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class Reference<T> {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.724 -0500", hash_original_field = "4318A9541E288C0C1A2D1D7206659018", hash_generated_field = "4AAE9C6A17BDEC3E1C4355B9D19EC64B")

    volatile T referent;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.727 -0500", hash_original_field = "370473892E310BF90FE5C14926BBF212", hash_generated_field = "876EC5DE34F64F2FFD05BC63B899180C")

    @SuppressWarnings("unchecked")
    volatile ReferenceQueue queue;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.729 -0500", hash_original_field = "7BA8922350D76294BFB21B8077C96609", hash_generated_field = "E273209CA38C7634EA6E996A3D36090C")

    @SuppressWarnings("unchecked")
    volatile Reference queueNext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.732 -0500", hash_original_field = "50D6C3D8C4144911DEB3BC16FECCEB6E", hash_generated_field = "4121E324E0F64E4E4F86F1B320B39449")

    public volatile Reference<?> pendingNext;

    /**
     * Constructs a new instance of this class.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.734 -0500", hash_original_method = "6CD3D0843AA30F3B07C5B27C93AA8456", hash_generated_method = "6CD3D0843AA30F3B07C5B27C93AA8456")
    
Reference() {
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.737 -0500", hash_original_method = "12DB2A62AB24EF0D8394447093A7278F", hash_generated_method = "12DB2A62AB24EF0D8394447093A7278F")
    
Reference(T r, ReferenceQueue q) {
        referent = r;
        queue = q;
    }

    /**
     * Makes the referent {@code null}. This does not force the reference
     * object to be enqueued.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.739 -0500", hash_original_method = "0AFD16A0602732B7092274BEFFE68A69", hash_generated_method = "D22A9D70012ADAB162F6F2808D9E0BAF")
    
public void clear() {
        referent = null;
    }

    /**
     * Adds an object to its reference queue.
     *
     * @return {@code true} if this call has caused the {@code Reference} to
     * become enqueued, or {@code false} otherwise
     *
     * @hide
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.742 -0500", hash_original_method = "26440EC338F5668042D20BF591B8B96C", hash_generated_method = "2111DDCBAFE3A72FEA0F6335CF132654")
    
public final synchronized boolean enqueueInternal() {
        if (queue != null && queueNext == null) {
            queue.enqueue(this);
            queue = null;
            return true;
        }
        return false;
    }

    /**
     * Forces the reference object to be enqueued if it has been associated with
     * a queue.
     *
     * @return {@code true} if this call has caused the {@code Reference} to
     * become enqueued, or {@code false} otherwise
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.745 -0500", hash_original_method = "D897CF322202613DC6CC465308B391E4", hash_generated_method = "5EF8DAFECFDD103332E8F810D33909CE")
    
public boolean enqueue() {
        return enqueueInternal();
    }

    /**
     * Returns the referent of the reference object.
     *
     * @return the referent to which reference refers, or {@code null} if the
     *         object has been cleared.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.749 -0500", hash_original_method = "3B05713F718010AF3C5E0D5C54D10077", hash_generated_method = "4AFFE090F7B2D69F816896DB6BEE599A")
    
public T get() {
        return referent;
    }

    /**
     * Checks whether the reference object has been enqueued.
     *
     * @return {@code true} if the {@code Reference} has been enqueued, {@code
     *         false} otherwise
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:32.755 -0500", hash_original_method = "22F3CCC310074C277F932626BFB7DCB6", hash_generated_method = "FB8F80D156FCF2C6768C48826119B1E7")
    
public boolean isEnqueued() {
        //return queueNext != null;
        if (queueNext == null)
            return false;
        return queueNext.getTaintBoolean();
    }
    
}

