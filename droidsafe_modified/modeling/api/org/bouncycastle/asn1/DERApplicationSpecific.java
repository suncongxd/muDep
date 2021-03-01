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

import org.bouncycastle.util.Arrays;

public class DERApplicationSpecific extends ASN1Object {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.614 -0500", hash_original_field = "9E3BCD745F0AFBDCD90F57B999378CC1", hash_generated_field = "8267FCF370BB52E90BE4145558A847E9")

    private  boolean   isConstructed;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.617 -0500", hash_original_field = "0870A130AAC33D99955FFBF59B769627", hash_generated_field = "3577E5E669CAE3960DB734B50E9992AB")

    private  int       tag;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.619 -0500", hash_original_field = "8DC08CC08E3D12210E910A181E8E9022", hash_generated_field = "10C1AE9E7AE48DBBFF95B14599A41386")

    private  byte[]    octets;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.621 -0500", hash_original_method = "F358753DF763D75E1752044681BF3AC8", hash_generated_method = "F358753DF763D75E1752044681BF3AC8")
    
DERApplicationSpecific(
        boolean isConstructed,
        int     tag,
        byte[]  octets)
    {
        this.isConstructed = isConstructed;
        this.tag = tag;
        this.octets = octets;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.624 -0500", hash_original_method = "4CF4A9576D642E83593F5E4C6AD75E4B", hash_generated_method = "1CBB1E1957B52EE59856286D34C01769")
    
public DERApplicationSpecific(
        int    tag,
        byte[] octets)
    {
        this(false, tag, octets);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.626 -0500", hash_original_method = "6CD0D23957E9B404C2F9698016AC75E4", hash_generated_method = "4E467E1A593E937EA68D471216249503")
    
public DERApplicationSpecific(
        int                  tag, 
        DEREncodable         object) 
        throws IOException 
    {
        this(true, tag, object);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.629 -0500", hash_original_method = "C83BD1B204CE742C0B031F1EFE6A4686", hash_generated_method = "B3EFD0C70F5DA120A3A2AC7141125FBD")
    
public DERApplicationSpecific(
        boolean      explicit,
        int          tag,
        DEREncodable object)
        throws IOException
    {
        byte[] data = object.getDERObject().getDEREncoded();

        this.isConstructed = explicit;
        this.tag = tag;

        if (explicit)
        {
            this.octets = data;
        }
        else
        {
            int lenBytes = getLengthOfLength(data);
            byte[] tmp = new byte[data.length - lenBytes];
            System.arraycopy(data, lenBytes, tmp, 0, tmp.length);
            this.octets = tmp;
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.632 -0500", hash_original_method = "94BAC854E28F90CA4EA0E8959FF01D3B", hash_generated_method = "C253467099CF55C696B2303D9572C173")
    
public DERApplicationSpecific(int tagNo, ASN1EncodableVector vec)
    {
        this.tag = tagNo;
        this.isConstructed = true;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        for (int i = 0; i != vec.size(); i++)
        {
            try
            {
                bOut.write(((ASN1Encodable)vec.get(i)).getEncoded());
            }
            catch (IOException e)
            {
                throw new ASN1ParsingException("malformed object: " + e, e);
            }
        }
        this.octets = bOut.toByteArray();
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.634 -0500", hash_original_method = "61F856A6747C3DB85AB853DB99D560A7", hash_generated_method = "054CBFF1DF59C20151D384DBECEEED8A")
    
private int getLengthOfLength(byte[] data)
    {
        int count = 2;               // TODO: assumes only a 1 byte tag number

        while((data[count - 1] & 0x80) != 0)
        {
            count++;
        }

        return count;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.637 -0500", hash_original_method = "4AFA588B499059410429802033BA5ED0", hash_generated_method = "C1AB853C45CE61BA2090D0ECBB795DD8")
    
public boolean isConstructed()
    {
        return isConstructed;
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.639 -0500", hash_original_method = "1D312847F188B0EEDCC8340737DDA9DA", hash_generated_method = "98081DDC7A587C1EECF41A99E3406824")
    
public byte[] getContents()
    {
        return octets;
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.641 -0500", hash_original_method = "8070D9796AF3020498C0A81362041E60", hash_generated_method = "22B94708BD55994C18450260E92C89EF")
    
public int getApplicationTag() 
    {
        return tag;
    }

    /**
     * Return the enclosed object assuming explicit tagging.
     *
     * @return  the resulting object
     * @throws IOException if reconstruction fails.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.643 -0500", hash_original_method = "E9ECF57BA1A3D60C7D24C2D1B840176E", hash_generated_method = "826DA2A2AE51776B63D284502B4FDAF6")
    
public DERObject getObject() 
        throws IOException 
    {
        return new ASN1InputStream(getContents()).readObject();
    }

    /**
     * Return the enclosed object assuming implicit tagging.
     *
     * @param derTagNo the type tag that should be applied to the object's contents.
     * @return  the resulting object
     * @throws IOException if reconstruction fails.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.645 -0500", hash_original_method = "8D6BE9D0FACD64D64BE0372060383689", hash_generated_method = "79A304059705BC9278BE9FD585D1152B")
    
public DERObject getObject(int derTagNo)
        throws IOException
    {
        if (derTagNo >= 0x1f)
        {
            throw new IOException("unsupported tag number");
        }

        byte[] orig = this.getEncoded();
        byte[] tmp = replaceTagNumber(derTagNo, orig);

        if ((orig[0] & DERTags.CONSTRUCTED) != 0)
        {
            tmp[0] |= DERTags.CONSTRUCTED;
        }

        return new ASN1InputStream(tmp).readObject();
    }
    
    /* (non-Javadoc)
     * @see org.bouncycastle.asn1.DERObject#encode(org.bouncycastle.asn1.DEROutputStream)
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.647 -0500", hash_original_method = "E93A5605AEDD2C30A620D419DCC3B82A", hash_generated_method = "E93A5605AEDD2C30A620D419DCC3B82A")
    
void encode(DEROutputStream out) throws IOException
    {
        int classBits = DERTags.APPLICATION;
        if (isConstructed)
        {
            classBits |= DERTags.CONSTRUCTED; 
        }

        out.writeEncoded(classBits, tag, octets);
    }
    
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.649 -0500", hash_original_method = "2058BDE8789C9C651125E90A6C9106EF", hash_generated_method = "2058BDE8789C9C651125E90A6C9106EF")
    
boolean asn1Equals(
        DERObject o)
    {
        if (!(o instanceof DERApplicationSpecific))
        {
            return false;
        }

        DERApplicationSpecific other = (DERApplicationSpecific)o;

        return isConstructed == other.isConstructed
            && tag == other.tag
            && Arrays.areEqual(octets, other.octets);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.652 -0500", hash_original_method = "CE8CF2E74F2DF6C65F2B809B8ECF710B", hash_generated_method = "DF8DCEE655EA050B508FB581D990EB55")
    
public int hashCode()
    {
        return (isConstructed ? 1 : 0) ^ tag ^ Arrays.hashCode(octets);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:18.655 -0500", hash_original_method = "DBC165FE2DD4A16BCAF214B19B914EC2", hash_generated_method = "0FF39C6D913D5FC54C7BC4488516DF51")
    
private byte[] replaceTagNumber(int newTag, byte[] input)
        throws IOException
    {
        int tagNo = input[0] & 0x1f;
        int index = 1;
        //
        // with tagged object tag number is bottom 5 bits, or stored at the start of the content
        //
        if (tagNo == 0x1f)
        {
            tagNo = 0;

            int b = input[index++] & 0xff;

            // X.690-0207 8.1.2.4.2
            // "c) bits 7 to 1 of the first subsequent octet shall not all be zero."
            if ((b & 0x7f) == 0) // Note: -1 will pass
            {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }

            while ((b >= 0) && ((b & 0x80) != 0))
            {
                tagNo |= (b & 0x7f);
                tagNo <<= 7;
                b = input[index++] & 0xff;
            }

            tagNo |= (b & 0x7f);
        }

        byte[] tmp = new byte[input.length - index + 1];

        System.arraycopy(input, index, tmp, 1, tmp.length - 1);

        tmp[0] = (byte)newTag;

        return tmp;
    }
    
}

