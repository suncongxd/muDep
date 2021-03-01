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

public final class CallIdentifier extends SIPObject {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.766 -0500", hash_original_field = "6E8CDB98589AEC6B3E29C92534CD2B41", hash_generated_field = "A04AE76734793B24A3254EFC13B52182")

    private static final long serialVersionUID = 7314773655675451377L;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.769 -0500", hash_original_field = "FBF0220078DB2389AF33B23B7A05CA2E", hash_generated_field = "E0739543348EE12559B498D426D0BA5B")

    protected String localId;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.773 -0500", hash_original_field = "3EBF9FC23C14AE1E55EAA4D77C46C987", hash_generated_field = "B7178EBD81D0CF23E79BD79FD4385F5A")

    protected String host;

    /**
     * Default constructor
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.776 -0500", hash_original_method = "725C6736E3C17143B15C26E546AA8C2D", hash_generated_method = "E496F5BBFDD5DD78C9074B543E8ACBB6")
    
public CallIdentifier() {
    }

    /**
     * Constructor
     * @param localId id is the local id.
     * @param host is the host.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.780 -0500", hash_original_method = "FA95D86B0844593C9B40E5A0309137C0", hash_generated_method = "66EEC8B99BCD65839D62858CD64D9EFA")
    
public CallIdentifier(String localId, String host) {
        this.localId = localId;
        this.host = host;
    }

    /**
     * constructor
     * @param cid String to set
     * @throws IllegalArgumentException if cid is null or is not a token,
     * or token@token
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.783 -0500", hash_original_method = "DC194AEEEBC974AB700C1882192F385A", hash_generated_method = "D410F5CDFB8E6B26DD4C45B9235F91A2")
    
public CallIdentifier(String cid) throws IllegalArgumentException {
        setCallID(cid);
    }

    /**
     * Get the encoded version of this id.
     * @return String to set
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.786 -0500", hash_original_method = "A36333A6F745F23182438BFF478F971C", hash_generated_method = "3E83C3693072E036EE6283AD07E42258")
    
public String encode() {
        return encode(new StringBuffer()).toString();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.789 -0500", hash_original_method = "BA99BEB4A81573E88FDC5269E1629A0E", hash_generated_method = "A3A46C572519B95CA3ECF6B08D2B973D")
    
public StringBuffer encode(StringBuffer buffer) {
        buffer.append(localId);
        if (host != null) {
            buffer.append(AT).append(host);
        }
        return buffer;
    }

    /**
     * Compare two call identifiers for equality.
     * @param other Object to set
     * @return true if the two call identifiers are equals, false
     * otherwise
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.793 -0500", hash_original_method = "89691ECA244C4E1A9E04F5B4F61288E7", hash_generated_method = "70447D9B5C38F395E125BC3A0127D68F")
    
public boolean equals(Object other) {
        if (other == null ) return false;
        if (!other.getClass().equals(this.getClass())) {
            return false;
        }
        CallIdentifier that = (CallIdentifier) other;
        if (this.localId.compareTo(that.localId) != 0) {
            return false;
        }
        if (this.host == that.host)
            return true;
        if ((this.host == null && that.host != null)
            || (this.host != null && that.host == null))
            return false;
        if (host.compareToIgnoreCase(that.host) != 0) {
            return false;
        }
        return true;
    }
    
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.796 -0500", hash_original_method = "CE30DABED6DD49816A955450A93F0F59", hash_generated_method = "DE8A2DF5AC53EDBD6D556ED922685CAF")
    
@Override
    public int hashCode() {
        if (this.localId  == null ) {
             throw new UnsupportedOperationException("Hash code called before id is set");
        }
        return this.localId.hashCode();
    }

    /** get the LocalId field
     * @return String
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.799 -0500", hash_original_method = "842945DA69DE126BDF81117018A1E298", hash_generated_method = "A851EA4F670C431087A51EE458194FF5")
    
public String getLocalId() {
        return localId;
    }

    /** get the host field
     * @return host member String
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.802 -0500", hash_original_method = "2F2AEDD17A97DC05E80343AAC323BA28", hash_generated_method = "1B655222E0C0F88C57DF58BCA9BFA498")
    
public String getHost() {
        return host;
    }

    /**
     * Set the localId member
     * @param localId String to set
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.805 -0500", hash_original_method = "0AB9BF9EEACCCDCF81C792E2B80A49BD", hash_generated_method = "03E2A91E6B57865D678AF44377AD7144")
    
public void setLocalId(String localId) {
        this.localId = localId;
    }

    /** set the callId field
     * @param cid Strimg to set
     * @throws IllegalArgumentException if cid is null or is not a token or
     * token@token
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.809 -0500", hash_original_method = "634BBA0AF80BB0AB1D1819140BE7D2A5", hash_generated_method = "C0EFF5BA76B0152213713C357B686F24")
    
public void setCallID(String cid) throws IllegalArgumentException {
        if (cid == null)
            throw new IllegalArgumentException("NULL!");
        int index = cid.indexOf('@');
        if (index == -1) {
            localId = cid;
            host = null;
        } else {
            localId = cid.substring(0, index);
            host = cid.substring(index + 1, cid.length());
            if (localId == null || host == null) {
                throw new IllegalArgumentException("CallID  must be token@token or token");
            }
        }
    }

    /**
     * Set the host member
     * @param host String to set
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.812 -0500", hash_original_method = "50061DDE3121AF364F9B69CF73D75DB1", hash_generated_method = "010F15293B4FE9EAAF6AB1C3B5D69AE3")
    
public void setHost(String host) {
        this.host = host;
    }
}

