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
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.bouncycastle.asn1;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.Vector;

public class DEREncodableVector {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:28.698 -0500", hash_original_field = "A7A9A9F7A17391F6E585D35448FF7802", hash_generated_field = "A7A9A9F7A17391F6E585D35448FF7802")

    Vector v = new Vector();

    /**
     * @deprecated use ASN1EncodableVector instead.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:28.701 -0500", hash_original_method = "4B1458BF3C50FB4227938042BB3B78D8", hash_generated_method = "4DFB1B6B60EA995D6120F4C94D3E7A09")
    
public DEREncodableVector()
    {

    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:28.703 -0500", hash_original_method = "D2631DDAF2947787DEB94C0E4C39923E", hash_generated_method = "48317AC83DE284FEAB21BF24DC99B0C2")
    
public void add(
        DEREncodable   obj)
    {
        v.addElement(obj);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:28.705 -0500", hash_original_method = "B314138255529409821E04A292C38866", hash_generated_method = "41F69DCE6FBBE0D8D974E08E716C3A3F")
    
public DEREncodable get(
        int i)
    {
        return (DEREncodable)v.elementAt(i);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:28.707 -0500", hash_original_method = "F82248B1BB6EFF1F27955CBD5C3A97B0", hash_generated_method = "EB8E5EC84569D43FF91B328ACD5AFC64")
    
public int size()
    {
        return v.size();
    }
    
}

