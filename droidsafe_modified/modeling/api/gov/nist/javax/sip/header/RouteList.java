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
* Conditions Of Use
*
* This software was developed by employees of the National Institute of
* Standards and Technology (NIST), an agency of the Federal Government.
* Pursuant to title 15 Untied States Code Section 105, works of NIST
* employees are not subject to copyright protection in the United States
* and are considered to be in the public domain.  As a result, a formal
* license is not needed to use the software.
*
* This software is provided by NIST as a service and is expressly
* provided "AS IS."  NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
* OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
* AND DATA ACCURACY.  NIST does not warrant or make any representations
* regarding the use of the software or the results thereof, including but
* not limited to the correctness, accuracy, reliability or usefulness of
* the software.
*
* Permission to use this software is contingent upon your acceptance
* of the terms of this agreement
*
* .
*
*/
/*******************************************************************************
* Product of NIST/ITL Advanced Networking Technologies Division (ANTD).        *
*******************************************************************************/

/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package gov.nist.javax.sip.header;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.ListIterator;

import javax.sip.header.RouteHeader;

public class RouteList extends SIPHeaderList<Route> {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:09.629 -0500", hash_original_field = "FBB315F346145333B7DA5F8B44F1528B", hash_generated_field = "E9DB318843918A7C1284240E819CF95B")

    private static final long serialVersionUID = 3407603519354809748L;

    /** default constructor
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:09.632 -0500", hash_original_method = "D139D7EC1148299A31F54E2FC8ABBD38", hash_generated_method = "CB92DE875B9E55E495E5D29EFAA43A60")
    
public RouteList() {
        super(Route.class, RouteHeader.NAME);

    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:09.635 -0500", hash_original_method = "2F61955E2C85706175B79FB3D2D2F3C0", hash_generated_method = "3AE466ADB4F0E366572AF5C7F6662148")
    
public Object clone() {
        RouteList retval = new RouteList();
        retval.clonehlist(this.hlist);
        return retval;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:09.638 -0500", hash_original_method = "48C7607E6836F35D30114914C241A09A", hash_generated_method = "00E0C4F60806BC67BD7CEDD480BF4C7C")
    
public String encode() {
        if ( super.hlist.isEmpty()) return "";
        else return super.encode();
    }

    /**
    * Order is important when comparing route lists.
    */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:09.641 -0500", hash_original_method = "A0122B485E57B455599C6CF601D6C0DF", hash_generated_method = "4D776348804CFA53F1F532DA6E9B2F88")
    
public boolean equals(Object other) {
        if (!(other instanceof RouteList))
            return false;
        RouteList that = (RouteList) other;
        if (this.size() != that.size())
            return false;
        ListIterator<Route> it = this.listIterator();
        ListIterator<Route> it1 = that.listIterator();
        while (it.hasNext()) {
            Route route = (Route) it.next();
            Route route1 = (Route) it1.next();
            if (!route.equals(route1))
                return false;
        }
        return true;
    }
}

