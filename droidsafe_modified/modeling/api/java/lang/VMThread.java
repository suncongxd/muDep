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
 * Copyright (C) 2007 The Android Open Source Project
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

import droidsafe.helpers.DSUtils;

class VMThread {
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static void create(Thread t, long stackSize) {
    }
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static Thread currentThread() {
    	return new Thread();
    }
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static boolean interrupted() {
        boolean var84E2C64F38F78BA3EA5C905AB5A2DA27_1527386262 = DSUtils.UNKNOWN_BOOLEAN;
        return var84E2C64F38F78BA3EA5C905AB5A2DA27_1527386262;
    }
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static void sleep(long msec, int nsec) throws InterruptedException {
    }
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    static void yield() {
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.368 -0500", hash_original_field = "9806D3C74BE7A1F2A2AE6C3DF1EC822E", hash_generated_field = "F9B19B51E65C63905337C0FA1F19D0C4")

    static final Thread.State[] STATE_MAP = new Thread.State[] {
        Thread.State.TERMINATED,     // ZOMBIE
        Thread.State.RUNNABLE,       // RUNNING
        Thread.State.TIMED_WAITING,  // TIMED_WAIT
        Thread.State.BLOCKED,        // MONITOR
        Thread.State.WAITING,        // WAIT
        Thread.State.NEW,            // INITIALIZING
        Thread.State.NEW,            // STARTING
        Thread.State.RUNNABLE,       // NATIVE
        Thread.State.WAITING,        // VMWAIT
        Thread.State.RUNNABLE        // SUSPENDED
    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.319 -0500", hash_original_field = "AB87C4F6E5547EBD7483F34732EA576D", hash_generated_field = "AB87C4F6E5547EBD7483F34732EA576D")

    Thread thread;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.321 -0500", hash_original_field = "4CE593F597436D5B18C67B1F3A65463F", hash_generated_field = "4CE593F597436D5B18C67B1F3A65463F")

    int vmData;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.324 -0500", hash_original_method = "1F6D5BC845AA1BFC5CA0AAC86B89BD84", hash_generated_method = "1F6D5BC845AA1BFC5CA0AAC86B89BD84")
    
VMThread(Thread t) {
        thread = t;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.347 -0500", hash_original_method = "72EE977944BFE2711990DF062DD76748", hash_generated_method = "C135F20F4FD32489ABF297ACDC7DAB20")
    
    void interrupt(){
    	//Formerly a native method
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.351 -0500", hash_original_method = "52F72D61B7E8A4F0C857BD363070E325", hash_generated_method = "5003F7243C0F6F6B4860DAFF8D8320F2")
    
    boolean isInterrupted(){
    	//Formerly a native method
    	return getTaintBoolean();
    }

    /**
     *  Starts the VMThread (and thus the Java Thread) with the given
     *  stack size.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.353 -0500", hash_original_method = "A99C097CFE2867999295EE26A5D88F1F", hash_generated_method = "A99C097CFE2867999295EE26A5D88F1F")
    
void start(long stackSize) {
        VMThread.create(thread, stackSize);
    }

    /**
     * Queries whether this Thread holds a monitor lock on the
     * given object.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.357 -0500", hash_original_method = "E049D119A7B4A553F02CF8223BDECCF5", hash_generated_method = "69F031F83675ABF5D78C26020D90F3C7")
    
    boolean holdsLock(Object object){
    	//Formerly a native method
    	addTaint(object.getTaint());
    	return getTaintBoolean();
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.362 -0500", hash_original_method = "7C8E9A670D06C8AE48DAFFA12CDF6628", hash_generated_method = "A7B8E145FAF4202F5F2BD51F427460A3")
    
    void setPriority(int newPriority){
    	//Formerly a native method
    	addTaint(newPriority);
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.365 -0500", hash_original_method = "133516DDD0D787C1D7D737647A15F491", hash_generated_method = "ACAE73F819EAF0444D5F9A423E4467B4")
    
    int getStatus(){
    	//Formerly a native method
    	return getTaintInt();
    }

    /**
     * Tell the VM that the thread's name has changed.  This is useful for
     * DDMS, which would otherwise be oblivious to Thread.setName calls.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:28.372 -0500", hash_original_method = "E4C63287FA81E5CD749A3DF00B7871AE", hash_generated_method = "A395ADC766F313790DA998F5853068A3")
    
    void nameChanged(String newName){
    	//Formerly a native method
    	addTaint(newName.getTaint());
    }

}

