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
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.util.concurrent.locks;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public abstract class AbstractOwnableSynchronizer implements java.io.Serializable {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:21.959 -0500", hash_original_field = "57384E69ACC4D85F20AFA905BC1CC919", hash_generated_field = "F2F59C783D5C1305604BF875D65980D6")

    private static final long serialVersionUID = 3737899427754241961L;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:21.964 -0500", hash_original_field = "DE1D7AAF330FAAEDB911F8AFB0518211", hash_generated_field = "C1E5D5F5CE2C40C66D9F63320FBEDDDF")

    private transient Thread exclusiveOwnerThread;

    /**
     * Empty constructor for use by subclasses.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:21.961 -0500", hash_original_method = "09FACEDDA2CFE0BD4863CBAC9CCC5DB1", hash_generated_method = "6B42B7C527A821C460743CB35B5EAA52")
    
protected AbstractOwnableSynchronizer() { }

    /**
     * Sets the thread that currently owns exclusive access. A
     * <tt>null</tt> argument indicates that no thread owns access.
     * This method does not otherwise impose any synchronization or
     * <tt>volatile</tt> field accesses.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:21.967 -0500", hash_original_method = "54BE2CB7A2B251833C1274201519DC0B", hash_generated_method = "A15DE0D04E1C22FF9C6D35B6BFC96607")
    
protected final void setExclusiveOwnerThread(Thread t) {
        exclusiveOwnerThread = t;
    }

    /**
     * Returns the thread last set by
     * <tt>setExclusiveOwnerThread</tt>, or <tt>null</tt> if never
     * set.  This method does not otherwise impose any synchronization
     * or <tt>volatile</tt> field accesses.
     * @return the owner thread
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:21.969 -0500", hash_original_method = "B27D03344136636BB9AA0892188CCAE3", hash_generated_method = "00E8F599BB6FB5931DFFDB7FA6FB10B1")
    
protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}

