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
 * Copyright (C) 2011 The Android Open Source Project
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

public final class StructStatFs {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.287 -0500", hash_original_field = "D617EA8324059E83C8A041340EC3BA12", hash_generated_field = "393385A523174AFDB05044DC2616C489")

    public  long f_bsize;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.289 -0500", hash_original_field = "CBB098BE021D45E901385C300E7EAA00", hash_generated_field = "424076025D97711D99003BEDC5BD56D5")

    /** Total block count. */
    public  long f_blocks;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.292 -0500", hash_original_field = "743E00615D6D66F04B8C529F5FB01666", hash_generated_field = "2A59F47552A3BB10BF4006731E711133")

    /** Free block count. */
    public  long f_bfree;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.294 -0500", hash_original_field = "6111090839430A49815CCD85E53911D9", hash_generated_field = "D6BB02272610265B89DB313894149ED5")

    /** Free block count available to non-root. */
    public  long f_bavail;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.296 -0500", hash_original_field = "C0F3BCE04F5831FD91FA7E1F13A15A98", hash_generated_field = "4E2880794A029FF23AFFA0FE39471438")

    /** Total file (inode) count. */
    public  long f_files;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.299 -0500", hash_original_field = "9517473BB74AB64A3A91FA248EFF34DC", hash_generated_field = "68CA80AD18F8E30F51EF41340E7A68B9")

    /** Free file (inode) count. */
    public  long f_ffree;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.301 -0500", hash_original_field = "4BC67C8B79F72CAE6E86A1340E85056A", hash_generated_field = "DC33258C138581369CF685116D3F124C")

    /** Maximum filename length. */
    public  long f_namemax;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.303 -0500", hash_original_field = "26E916DD664DA4B28A24516EBEB730E4", hash_generated_field = "375DCF7825D6DE51ACA7728B8A7F4D3A")

    /** Fundamental file system block size. */
    public  long f_frsize; /*unsigned long*/

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:20.307 -0500", hash_original_method = "A2BBDE1C513EF61F7C6F88EB6F620A3C", hash_generated_method = "A2BBDE1C513EF61F7C6F88EB6F620A3C")
    
StructStatFs(long f_bsize, long f_blocks, long f_bfree, long f_bavail,
            long f_files, long f_ffree, long f_namemax, long f_frsize) {
        this.f_bsize = f_bsize;
        this.f_blocks = f_blocks;
        this.f_bfree = f_bfree;
        this.f_bavail = f_bavail;
        this.f_files = f_files;
        this.f_ffree = f_ffree;
        this.f_namemax = f_namemax;
        this.f_frsize = f_frsize;
    }
    
}

