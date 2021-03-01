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

/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package gov.nist.core;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.text.ParseException;

public abstract class ParserCore {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.646 -0500", hash_original_field = "98AAD20EFC540BFCEEE8C94A57587407", hash_generated_field = "C7421C837BE84CB71F0718DB57F8E962")

    public static final boolean debug = Debug.parserDebug;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.648 -0500", hash_original_field = "9FA3227F51436A81B8A27413064C2B27", hash_generated_field = "80C87DAAFBB424B160F9721506874F09")

    static int nesting_level;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.651 -0500", hash_original_field = "694AD1BF080B86E08B0E314389D08AB5", hash_generated_field = "AF108A7E81C8C1348B39F9FCBD8C60E7")

    protected LexerCore lexer;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:30.687 -0400", hash_original_method = "23390B10F07F5D8E9EE5EBAB724E0597", hash_generated_method = "23390B10F07F5D8E9EE5EBAB724E0597")
    public ParserCore ()
    {
        //Synthesized constructor
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.655 -0500", hash_original_method = "2B8F776DAFC357EFC899FF251A427EF6", hash_generated_method = "FED05485A0974D95153CC926E74EE894")
    
protected NameValue nameValue(char separator) throws ParseException  {
        if (debug) dbg_enter("nameValue");
        try {

        lexer.match(LexerCore.ID);
        Token name = lexer.getNextToken();
        // eat white space.
        lexer.SPorHT();
        try {

                boolean quoted = false;

            char la = lexer.lookAhead(0);

            if (la == separator ) {
                lexer.consume(1);
                lexer.SPorHT();
                String str = null;
                boolean isFlag = false;
                if (lexer.lookAhead(0) == '\"')  {
                     str = lexer.quotedString();
                     quoted = true;
                } else {
                   lexer.match(LexerCore.ID);
                   Token value = lexer.getNextToken();
                   str = value.tokenValue;

                   // JvB: flag parameters must be empty string!
                   if (str==null) {
                       str = "";
                       isFlag = true;
                   }
                }
                NameValue nv = new NameValue(name.tokenValue,str,isFlag);
                if (quoted) nv.setQuotedValue();
                return nv;
            }  else {
                // JvB: flag parameters must be empty string!
                return new NameValue(name.tokenValue,"",true);
            }
        } catch (ParseException ex) {
            return new NameValue(name.tokenValue,null,false);
        }

        } finally {
            if (debug) dbg_leave("nameValue");
        }

    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.657 -0500", hash_original_method = "FEF315A443660226B56655DBFD660360", hash_generated_method = "2CC809F5CB9762AB22FDDE770398FC72")
    
protected  void dbg_enter(String rule) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nesting_level ; i++)
            stringBuffer.append(">");

        if (debug)  {
            System.out.println(
                stringBuffer + rule +
                "\nlexer buffer = \n" +
                lexer.getRest());
        }
        nesting_level++;
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.660 -0500", hash_original_method = "1BC6FB469729E22F0BBD9E3870285FA5", hash_generated_method = "543C9BD19E373DB533AA5A2550507AEF")
    
protected void dbg_leave(String rule) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nesting_level ; i++)
            stringBuffer.append("<");

        if (debug)  {
            System.out.println(
                stringBuffer +
                rule +
                "\nlexer buffer = \n" +
                lexer.getRest());
        }
        nesting_level --;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.662 -0500", hash_original_method = "96EC2DBFF0EC1BA1DEDB704D9ED6E4AE", hash_generated_method = "BCA2F6BCA05A0FC34FC25E41E0AE28C7")
    
protected NameValue nameValue() throws ParseException  {
        return nameValue('=');
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:51.664 -0500", hash_original_method = "9816EEB9F7DF054BEDA3076A1CE20DA0", hash_generated_method = "8B483A1525BA623F62F1B79AA683FFEA")
    
protected void peekLine(String rule) {
        if (debug) {
            Debug.println(rule +" " + lexer.peekLine());
        }
    }
}

