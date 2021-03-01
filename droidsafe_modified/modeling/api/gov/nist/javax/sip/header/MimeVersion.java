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
import javax.sip.InvalidArgumentException;
import javax.sip.header.MimeVersionHeader;

public class MimeVersion extends SIPHeader implements MimeVersionHeader {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.505 -0500", hash_original_field = "92C18D4F59E10A12C6BBAA4B905B9A56", hash_generated_field = "55326010FCF59323AF113A86CEA19744")

    private static final long serialVersionUID = -7951589626435082068L;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.509 -0500", hash_original_field = "CDB181A6EBAD31015E8DB6B6C3CEA890", hash_generated_field = "159C0D69174BAFEC3E75C8954E429BCB")

    protected int minorVersion;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.512 -0500", hash_original_field = "54B35FC182F29A46E406978B30815FC5", hash_generated_field = "06A9400313FF659F56CC228B2F050E47")

    protected int majorVersion;

    /**
     * Default constructor
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.516 -0500", hash_original_method = "30624564A4AC1D2B636F927C84B1D79A", hash_generated_method = "AC3E6C1D290EEE6927D3611547E59433")
    
public MimeVersion() {
        super(MIME_VERSION);
    }

    /**
     * Gets the Minor version value of this MimeVersionHeader.
     *
     * @return the Minor version of this MimeVersionHeader
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.519 -0500", hash_original_method = "D7FCE27D9AEC3742DED8FD71AF804648", hash_generated_method = "5ABAC1D53954C5C5EDCF99CD1BE52D9D")
    
public int getMinorVersion() {
        return minorVersion;
    }

    /**
    * Gets the Major version value of this MimeVersionHeader.
    *
    * @return the Major version of this MimeVersionHeader
    */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.522 -0500", hash_original_method = "634F243B87354520E8F28C8C2539E93B", hash_generated_method = "B50B28C1BC552998DA3C64871DFDF459")
    
public int getMajorVersion() {
        return majorVersion;
    }

    /**
     * Sets the Minor-Version argument of this MimeVersionHeader to the supplied
     * <var>minorVersion</var> value.
     *
     * @param minorVersion - the new integer Minor version
     * @throws InvalidArgumentException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.525 -0500", hash_original_method = "6BBEE0FD6D45D5567C0156A66B800B02", hash_generated_method = "3D2BA737A149CD629B8D19A459B48FB8")
    
public void setMinorVersion(int minorVersion)
        throws InvalidArgumentException {
        if (minorVersion < 0)
            throw new InvalidArgumentException(
                "JAIN-SIP Exception"
                    + ", MimeVersion, setMinorVersion(), the minorVersion parameter is null");
        this.minorVersion = minorVersion;
    }

    /**
     * Sets the Major-Version argument of this MimeVersionHeader to the supplied
     * <var>majorVersion</var> value.
     *
     * @param majorVersion - the new integer Major version
     * @throws InvalidArgumentException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.528 -0500", hash_original_method = "D3EA6112924A8CE6932EA7AE61D5C665", hash_generated_method = "77F07287A1A0A9E8DF26E4E58E2A07C9")
    
public void setMajorVersion(int majorVersion)
        throws InvalidArgumentException {
        if (majorVersion < 0)
            throw new InvalidArgumentException(
                "JAIN-SIP Exception"
                    + ", MimeVersion, setMajorVersion(), the majorVersion parameter is null");
        this.majorVersion = majorVersion;
    }

    /**
     * Return canonical form.
     * @return String
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:00.532 -0500", hash_original_method = "C604A793DAEAE92DAC99FA0862A74B19", hash_generated_method = "20ACC0777E5DF4C5A88542C7A7DB4716")
    
public String encodeBody() {
        return Integer.toString(majorVersion)
            + DOT
            + Integer.toString(minorVersion);
    }
}

