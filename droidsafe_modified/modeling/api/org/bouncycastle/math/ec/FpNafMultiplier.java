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


package org.bouncycastle.math.ec;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.math.BigInteger;

class FpNafMultiplier implements ECMultiplier {
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:25:41.665 -0400", hash_original_method = "BABE471E2E455F4576B9B6A994FEF2CC", hash_generated_method = "BABE471E2E455F4576B9B6A994FEF2CC")
    public FpNafMultiplier ()
    {
        //Synthesized constructor
    }
    /**
     * D.3.2 pg 101
     * @see org.bouncycastle.math.ec.ECMultiplier#multiply(org.bouncycastle.math.ec.ECPoint, java.math.BigInteger)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:37.822 -0500", hash_original_method = "DE008A6A0942FE352BE58D2F6B7E9069", hash_generated_method = "D3C5BDA22382B8D3BA336B952BC07A61")
    
public ECPoint multiply(ECPoint p, BigInteger k, PreCompInfo preCompInfo)
    {
        // TODO Probably should try to add this
        // BigInteger e = k.mod(n); // n == order of p
        BigInteger e = k;
        BigInteger h = e.multiply(BigInteger.valueOf(3));

        ECPoint neg = p.negate();
        ECPoint R = p;

        for (int i = h.bitLength() - 2; i > 0; --i)
        {             
            R = R.twice();

            boolean hBit = h.testBit(i);
            boolean eBit = e.testBit(i);

            if (hBit != eBit)
            {
                R = R.add(hBit ? p : neg);
            }
        }

        return R;
    }
    
}

