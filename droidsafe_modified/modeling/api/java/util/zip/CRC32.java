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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.util.zip;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.Arrays;

public class CRC32 implements Checksum {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.159 -0500", hash_original_field = "5E188210823E517BAA91872EABCAF7CD", hash_generated_field = "103DD39C404FCCE4417B434D3EE0291E")

    private long crc = 0L;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.161 -0500", hash_original_field = "2CA876BB74D9B634ED48B07BACA76027", hash_generated_field = "2CA876BB74D9B634ED48B07BACA76027")

    long tbytes = 0L;
    
    @DSComment("no info moving")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:16.485 -0400", hash_original_method = "D55E28551E901525721E2A005775E885", hash_generated_method = "D55E28551E901525721E2A005775E885")
    public CRC32 ()
    {
        //Synthesized constructor
    }

    /**
     * Returns the CRC32 checksum for all input received.
     *
     * @return The checksum for this instance.
     */
    @DSComment("no info moving")
    @DSSafe(DSCat.SAFE_OTHERS)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.163 -0500", hash_original_method = "854FE5E27AD17353F420BE5FB492E27A", hash_generated_method = "B19B471301649942B63964C318AED819")
    
public long getValue() {
        return crc;
    }

    /**
     * Resets the CRC32 checksum to it initial state.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.166 -0500", hash_original_method = "3C94069B7580B83ACAD9A7049BA1E3DA", hash_generated_method = "14ACC9FC067849549AFBF7F4DBB09450")
    
public void reset() {
        tbytes = crc = 0;

    }

    /**
     * Updates this checksum with the byte value provided as integer.
     *
     * @param val
     *            represents the byte to update the checksum.
     */
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.168 -0500", hash_original_method = "CC03AC9F84902D02E7C98FF0D9B3A071", hash_generated_method = "1BB18586428AA9D56B3090422507B482")
    
public void update(int val) {
        crc = updateByteImpl((byte) val, crc);
    }

    /**
     * Updates this checksum with the bytes contained in buffer {@code buf}.
     *
     * @param buf
     *            the buffer holding the data to update the checksum with.
     */
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.171 -0500", hash_original_method = "CE6B29801A6FECCB312673EE525F73B5", hash_generated_method = "C7A3A99D53983972F56F1121073E3E00")
    
public void update(byte[] buf) {
        update(buf, 0, buf.length);
    }

    /**
     * Update this {@code CRC32} checksum with the contents of {@code buf},
     * starting from {@code offset} and reading {@code byteCount} bytes of data.
     */
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:32.173 -0500", hash_original_method = "FA32CF22527C3BE95E419FDEDA807F82", hash_generated_method = "67B151244B370C307496F0872FD40CDC")
    
public void update(byte[] buf, int offset, int byteCount) {
        Arrays.checkOffsetAndCount(buf.length, offset, byteCount);
        tbytes += byteCount;
        crc = updateImpl(buf, offset, byteCount, crc);
    }

    @DSBan(DSCat.PRIVATE_METHOD)

    @DSComment("Private Method")

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:58.299 -0400", hash_original_method = "680551833B0F700FD9FEA4D72A9561BF", hash_generated_method = "02BF51FF0157008B19FE71F6EF1EFF51")
    
    private long updateImpl(byte[] buf, int offset, int byteCount, long crc1){
    	//Formerly a native method
    	double taintDouble = 0;
    	taintDouble += buf[0];
    	taintDouble += offset;
    	taintDouble += byteCount;
    	taintDouble += crc1;
    	addTaint(taintDouble);
    
    	return (long)taintDouble;
    }

    @DSBan(DSCat.PRIVATE_METHOD)

    @DSComment("Private Method")

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:58.300 -0400", hash_original_method = "90348FBF3DF8B04612B506AE017DBA82", hash_generated_method = "51D012D216C1F1216E230AE20D15D676")
    
    private long updateByteImpl(byte val, long crc1){
    	//Formerly a native method
    	double taintDouble = 0;
    	taintDouble += val;
    	taintDouble += crc1;
    	addTaint(taintDouble);
    
    	return (long)taintDouble;
    }
    
}

