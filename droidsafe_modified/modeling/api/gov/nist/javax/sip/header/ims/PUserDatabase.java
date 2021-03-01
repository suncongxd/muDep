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


package gov.nist.javax.sip.header.ims;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.text.ParseException;

import javax.sip.header.ExtensionHeader;

public class PUserDatabase extends gov.nist.javax.sip.header.ParametersHeader implements PUserDatabaseHeader, SIPHeaderNamesIms, ExtensionHeader {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.849 -0500", hash_original_field = "1D4A58FFF7AA38B6C9A27CCC6771A449", hash_generated_field = "86C05D139AEEDD5C16FB836B4667558E")

    private String databaseName;

    /**
     *
     * @param databaseName
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.853 -0500", hash_original_method = "7F14AD9E055B0EDA573715DABAF69E4A", hash_generated_method = "CDEDE6DD1D14EFE79101F1481576C186")
    
public PUserDatabase(String databaseName)
    {
        super(NAME);
        this.databaseName = databaseName;
    }

    /**
     * Default constructor.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.856 -0500", hash_original_method = "E39FCB193BD9E02D223AAEA09A512240", hash_generated_method = "4A7D58B86640240005440BF8BA013D2B")
    
public PUserDatabase() {
        super(P_USER_DATABASE);
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.860 -0500", hash_original_method = "1786B1376B4CD3FEAEBCAB28A560C6C6", hash_generated_method = "670AFE2F9B8340580BECCDB06D8BB4C6")
    
public String getDatabaseName() {

        return this.databaseName;
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:35.314 -0400", hash_original_method = "D51F20AACD3B656F6DA8A7AF0FAAC017", hash_generated_method = "91E5237D80F9174CB1590C4B10B03A2D")
    public void setDatabaseName(String databaseName) {
        if((databaseName==null)||(databaseName.equals(" ")))        
        {
        NullPointerException varA3D75CD097E396096D0DAAE2F57C8E0F_272624513 = new NullPointerException("Database name is null");
        varA3D75CD097E396096D0DAAE2F57C8E0F_272624513.addTaint(getTaint());
        throw varA3D75CD097E396096D0DAAE2F57C8E0F_272624513;
        }
        else
        if(!databaseName.contains("aaa://"))        
        this.databaseName = new StringBuffer().append("aaa://").append(databaseName).toString();
        else
        this.databaseName = databaseName;
        // ---------- Original Method ----------
        //if((databaseName==null)||(databaseName.equals(" ")))
            //throw new NullPointerException("Database name is null");
        //else
            //if(!databaseName.contains("aaa://"))
        //this.databaseName = new StringBuffer().append("aaa://").append(databaseName).toString();
            //else
                //this.databaseName = databaseName;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.867 -0500", hash_original_method = "C5A419AE4F54D4DB306D1B917A489A39", hash_generated_method = "C70DDAAF4CE56806DA74FFF9FD29CFCD")
    
protected String encodeBody() {

        StringBuffer retval = new StringBuffer();
        retval.append("<");
        if(getDatabaseName()!=null)
        retval.append(getDatabaseName());

        if (!parameters.isEmpty())
            retval.append(SEMICOLON + this.parameters.encode());
        retval.append(">");

        return retval.toString();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.870 -0500", hash_original_method = "E720B3A09C72EF9AD917691C9271EB0A", hash_generated_method = "97914C0F9C0738B1832C0F5D20126DF8")
    
public boolean equals(Object other)
    {
        return (other instanceof PUserDatabaseHeader) && super.equals(other);

    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.873 -0500", hash_original_method = "0C530D20B34B6FA1A3EDDB9B09D02FD1", hash_generated_method = "6D9B725C8901044875E10FB3FDDD193C")
    
public Object clone() {
        PUserDatabase retval = (PUserDatabase) super.clone();
        return retval;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:54:57.876 -0500", hash_original_method = "4B92A49D74A1215E0C3EC12D290AF61B", hash_generated_method = "014FE50D8A9A4F50DCF1C9E31F934432")
    
public void setValue(String value) throws ParseException {
        throw new ParseException(value,0);

    }
    
}

