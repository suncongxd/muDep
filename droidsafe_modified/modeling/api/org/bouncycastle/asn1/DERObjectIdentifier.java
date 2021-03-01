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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class DERObjectIdentifier extends ASN1Object {

    /**
     * return an OID from the passed in object
     *
     * @exception IllegalArgumentException if the object cannot be converted.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.623 -0500", hash_original_method = "8E6A00C9FD578924DAE1E9AA277D235D", hash_generated_method = "801B110C896132E1A396E76F36312800")
    
public static DERObjectIdentifier getInstance(
        Object  obj)
    {
        if (obj == null || obj instanceof DERObjectIdentifier)
        {
            return (DERObjectIdentifier)obj;
        }

        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    /**
     * return an Object Identifier from a tagged object.
     *
     * @param obj the tagged object holding the object we want
     * @param explicit true if the object is meant to be explicitly
     *              tagged false otherwise.
     * @exception IllegalArgumentException if the tagged object cannot
     *               be converted.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.626 -0500", hash_original_method = "A02B5A4CC1D675D6AB61342B6CB52A83", hash_generated_method = "DA83580FE5AB1481B0AA055FA3E544F1")
    
public static DERObjectIdentifier getInstance(
        ASN1TaggedObject obj,
        boolean          explicit)
    {
        DERObject o = obj.getObject();

        if (explicit || o instanceof DERObjectIdentifier)
        {
            return getInstance(o);
        }
        else
        {
            return new ASN1ObjectIdentifier(ASN1OctetString.getInstance(obj.getObject()).getOctets());
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.651 -0500", hash_original_method = "7A21A0FDB9F41FC21B39244A17757B27", hash_generated_method = "99A784EBDDE84250403E1CC619AC8F04")
    
private static boolean isValidIdentifier(
        String identifier)
    {
        if (identifier.length() < 3
            || identifier.charAt(1) != '.')
        {
            return false;
        }

        char first = identifier.charAt(0);
        if (first < '0' || first > '2')
        {
            return false;
        }

        boolean periodAllowed = false;
        for (int i = identifier.length() - 1; i >= 2; i--)
        {
            char ch = identifier.charAt(i);

            if ('0' <= ch && ch <= '9')
            {
                periodAllowed = true;
                continue;
            }

            if (ch == '.')
            {
                if (!periodAllowed)
                {
                    return false;
                }

                periodAllowed = false;
                continue;
            }

            return false;
        }

        return periodAllowed;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.620 -0500", hash_original_field = "67D18ED1400CD458B308847E018D0637", hash_generated_field = "67D18ED1400CD458B308847E018D0637")

    String      identifier;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.629 -0500", hash_original_method = "41784C2F0BEC0E535BBC46B058BAD130", hash_generated_method = "E194B8ACE3B852AC3A9A0D5915A8C6D8")
    
DERObjectIdentifier(
        byte[]  bytes)
    {
        StringBuffer    objId = new StringBuffer();
        long            value = 0;
        BigInteger      bigValue = null;
        boolean         first = true;

        for (int i = 0; i != bytes.length; i++)
        {
            int b = bytes[i] & 0xff;

            if (value < 0x80000000000000L) 
            {
                value = value * 128 + (b & 0x7f);
                if ((b & 0x80) == 0)             // end of number reached
                {
                    if (first)
                    {
                        switch ((int)value / 40)
                        {
                        case 0:
                            objId.append('0');
                            break;
                        case 1:
                            objId.append('1');
                            value -= 40;
                            break;
                        default:
                            objId.append('2');
                            value -= 80;
                        }
                        first = false;
                    }

                    objId.append('.');
                    objId.append(value);
                    value = 0;
                }
            } 
            else 
            {
                if (bigValue == null)
                {
                    bigValue = BigInteger.valueOf(value);
                }
                bigValue = bigValue.shiftLeft(7);
                bigValue = bigValue.or(BigInteger.valueOf(b & 0x7f));
                if ((b & 0x80) == 0) 
                {
                    objId.append('.');
                    objId.append(bigValue);
                    bigValue = null;
                    value = 0;
                }
            }
        }

        // BEGIN android-changed
        /*
         * Intern the identifier so there aren't hundreds of duplicates
         * (in practice).
         */
        this.identifier = objId.toString().intern();
        // END android-changed
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.632 -0500", hash_original_method = "475B203FC54A32C4FE512A1ADD61E543", hash_generated_method = "821A328933AD5429C03C69E3DE9D30AF")
    
public DERObjectIdentifier(
        String  identifier)
    {
        if (!isValidIdentifier(identifier))
        {
            throw new IllegalArgumentException("string " + identifier + " not an OID");
        }

        // BEGIN android-changed
        /*
         * Intern the identifier so there aren't hundreds of duplicates
         * (in practice).
         */
        this.identifier = identifier.intern();
        // END android-changed
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.634 -0500", hash_original_method = "9387C33D0DE26CACBA5D2CF5AB6F5FB6", hash_generated_method = "FF7AB1564D75C7CD37060B089918FD81")
    
public String getId()
    {
        return identifier;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.636 -0500", hash_original_method = "7C147E60AEA70A2E84F8C4657E7FBA13", hash_generated_method = "E45E5504E29A0C473AC244F4CCC51689")
    
private void writeField(
        OutputStream    out,
        long            fieldValue)
        throws IOException
    {
        byte[] result = new byte[9];
        int pos = 8;
        result[pos] = (byte)((int)fieldValue & 0x7f);
        while (fieldValue >= (1L << 7))
        {
            fieldValue >>= 7;
            result[--pos] = (byte)((int)fieldValue & 0x7f | 0x80);
        }
        out.write(result, pos, 9 - pos);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.639 -0500", hash_original_method = "58F2C435CDACAD8814E2A1351775DAC9", hash_generated_method = "5A4499E969469E1AB2CCB45521BE4E7D")
    
private void writeField(
        OutputStream    out,
        BigInteger      fieldValue)
        throws IOException
    {
        int byteCount = (fieldValue.bitLength()+6)/7;
        if (byteCount == 0) 
        {
            out.write(0);
        }  
        else 
        {
            BigInteger tmpValue = fieldValue;
            byte[] tmp = new byte[byteCount];
            for (int i = byteCount-1; i >= 0; i--) 
            {
                tmp[i] = (byte) ((tmpValue.intValue() & 0x7f) | 0x80);
                tmpValue = tmpValue.shiftRight(7); 
            }
            tmp[byteCount-1] &= 0x7f;
            out.write(tmp);
        }

    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.642 -0500", hash_original_method = "ECFD3ADA5852656A886183CB7FE8CA3F", hash_generated_method = "ECFD3ADA5852656A886183CB7FE8CA3F")
    
void encode(
        DEROutputStream out)
        throws IOException
    {
        OIDTokenizer            tok = new OIDTokenizer(identifier);
        ByteArrayOutputStream   bOut = new ByteArrayOutputStream();
        DEROutputStream         dOut = new DEROutputStream(bOut);

        writeField(bOut, 
                    Integer.parseInt(tok.nextToken()) * 40
                    + Integer.parseInt(tok.nextToken()));

        while (tok.hasMoreTokens())
        {
            String token = tok.nextToken();
            if (token.length() < 18) 
            {
                writeField(bOut, Long.parseLong(token));
            }
            else
            {
                writeField(bOut, new BigInteger(token));
            }
        }

        dOut.close();

        byte[]  bytes = bOut.toByteArray();

        out.writeEncoded(OBJECT_IDENTIFIER, bytes);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.644 -0500", hash_original_method = "F84420975A0078554FCD832859033A65", hash_generated_method = "9D581FCEE7A17AA66DF023030951627B")
    
public int hashCode()
    {
        return identifier.hashCode();
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.646 -0500", hash_original_method = "0655D55E620898111D8D6A0724E88908", hash_generated_method = "0655D55E620898111D8D6A0724E88908")
    
boolean asn1Equals(
        DERObject  o)
    {
        if (!(o instanceof DERObjectIdentifier))
        {
            return false;
        }

        return identifier.equals(((DERObjectIdentifier)o).identifier);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:13.648 -0500", hash_original_method = "7A5AF873124B543657B1C40A56842B0C", hash_generated_method = "241A0FB634B7F2AB240F27EA72BC5EEA")
    
public String toString()
    {
        return getId();
    }
    
}

