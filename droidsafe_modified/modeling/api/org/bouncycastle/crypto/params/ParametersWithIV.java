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


package org.bouncycastle.crypto.params;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import org.bouncycastle.crypto.CipherParameters;






public class ParametersWithIV implements CipherParameters {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.131 -0500", hash_original_field = "E884C91B0186461100A61CCE6B0406E0", hash_generated_field = "EF60E6736481A45AB4AEED35775132C4")

    private byte[]              iv;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.133 -0500", hash_original_field = "95B98FE0C5601A91715393CB954CA53B", hash_generated_field = "37E4A1EB8E400F5F526111B05CB36773")

    private CipherParameters    parameters;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.136 -0500", hash_original_method = "02220345FBEC664F191759358E3133FB", hash_generated_method = "FC0AA52C9E90129E04116D96B6832BDD")
    
public ParametersWithIV(
        CipherParameters    parameters,
        byte[]              iv)
    {
        this(parameters, iv, 0, iv.length);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.138 -0500", hash_original_method = "828E5FEC62EC7B691A55A07AD37E3F65", hash_generated_method = "B641260C4389AA9F4B990975B8E142CA")
    
public ParametersWithIV(
        CipherParameters    parameters,
        byte[]              iv,
        int                 ivOff,
        int                 ivLen)
    {
        this.iv = new byte[ivLen];
        this.parameters = parameters;

        System.arraycopy(iv, ivOff, this.iv, 0, ivLen);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.141 -0500", hash_original_method = "D19EA42446BC5DC2CC45B356827C0DC7", hash_generated_method = "EFC97A9055972B046A2769DFAEED1D6F")
    
public byte[] getIV()
    {
        return iv;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:30.143 -0500", hash_original_method = "4D76F2EFD1EF9C1CA864824F06254E67", hash_generated_method = "574A09DACE341E4A2632E93FD70A2C75")
    
public CipherParameters getParameters()
    {
        return parameters;
    }

    
}

