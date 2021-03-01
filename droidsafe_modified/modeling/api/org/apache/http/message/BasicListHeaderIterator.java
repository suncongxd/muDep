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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/message/BasicListHeaderIterator.java $
 * $Revision: 584542 $
 * $Date: 2007-10-14 06:29:34 -0700 (Sun, 14 Oct 2007) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.http.message;

// Droidsafe Imports
import droidsafe.annotations.*;
import droidsafe.helpers.*;

import droidsafe.runtime.*;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

public class BasicListHeaderIterator implements HeaderIterator {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.212 -0500", hash_original_field = "59CAC29E430EB81839749401CA5CDCF3", hash_generated_field = "3B804EE90FB805BB2F9B41892E7AB9CC")

    protected  List allHeaders;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.215 -0500", hash_original_field = "6F81E025B1753B7DB14DC2504C9B53B1", hash_generated_field = "888ED00F76573C33F42FD289758C6753")

    protected int currentIndex;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.219 -0500", hash_original_field = "B1F9D60B21452E129540AD46C28EB39B", hash_generated_field = "7984FA65CEAD27AA9A0B68B55B2F3751")

    protected int lastIndex;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.222 -0500", hash_original_field = "BD66DD28721A718F4DADB56502A74024", hash_generated_field = "A59FED2AE4AD2264FB1F7BF89553485D")

    protected String headerName;

    /**
     * Creates a new header iterator.
     *
     * @param headers   a list of headers over which to iterate
     * @param name      the name of the headers over which to iterate, or
     *                  <code>null</code> for any
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.225 -0500", hash_original_method = "2D091C8CAFC19B749551E2F1187B6105", hash_generated_method = "CFF082B2C43AC35F1DBB1110DFE2B4F0")
    
public BasicListHeaderIterator(List headers, String name) {
        if (headers == null) {
            throw new IllegalArgumentException
                ("Header list must not be null.");
        }

        this.allHeaders = headers;
        this.headerName = name;
        this.currentIndex = findNext(-1);
        this.lastIndex = -1;
    }

    /**
     * Determines the index of the next header.
     *
     * @param from      one less than the index to consider first,
     *                  -1 to search for the first header
     *
     * @return  the index of the next header that matches the filter name,
     *          or negative if there are no more headers
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.227 -0500", hash_original_method = "AE89C08F08BB3F2E2361D882A86E08E6", hash_generated_method = "AC9717801F6771F94DD04A5A3483AC34")
    
protected int findNext(int from) {
        if (from < -1)
            return -1;

        final int to = this.allHeaders.size()-1;
        boolean found = false;
        while (!found && (from < to)) {
            from++;
            found = filterHeader(from);
        }
        return found ? from : -1;
    }

    /**
     * Checks whether a header is part of the iteration.
     *
     * @param index     the index of the header to check
     *
     * @return  <code>true</code> if the header should be part of the
     *          iteration, <code>false</code> to skip
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:48.230 -0500", hash_original_method = "DC309B64F8D7236BD3D5DA9945B2E659", hash_generated_method = "68C78FD967A9D3987D5B594BFCB3C1DD")
    
protected boolean filterHeader(int index) {
        if (this.headerName == null)
            return true;

        // non-header elements, including null, will trigger exceptions
        final String name = ((Header)this.allHeaders.get(index)).getName();

        return this.headerName.equalsIgnoreCase(name);
    }
    
	@DSSafe(DSCat.SAFE_LIST)
    @Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
    
    @Override
	public Header nextHeader() {
		// TODO Auto-generated method stub

        final int current = this.currentIndex;
        if (current < 0) {
            throw new NoSuchElementException("Iteration already finished.");
        }

        this.lastIndex    = current;
        this.currentIndex = findNext(current);

        return (Header) this.allHeaders.get(current);
	}
    
	@DSSafe(DSCat.SAFE_LIST)
    @Override
	public Object next() {
		// TODO Auto-generated method stub
		return new Object();
	}
    
    @DSSafe(DSCat.DATA_STRUCTURE)
    @Override
	public void remove() {
		// TODO Auto-generated method stub
	}
    
}

