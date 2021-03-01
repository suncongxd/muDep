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


package gov.nist.javax.sip.parser;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import gov.nist.core.InternalErrorHandler;
import gov.nist.javax.sip.header.SIPHeaderNamesCache;
import gov.nist.javax.sip.header.extensions.Join;
import gov.nist.javax.sip.header.extensions.MinSE;
import gov.nist.javax.sip.header.extensions.References;
import gov.nist.javax.sip.header.extensions.ReferredBy;
import gov.nist.javax.sip.header.extensions.Replaces;
import gov.nist.javax.sip.header.extensions.SessionExpires;
import gov.nist.javax.sip.header.ims.PAccessNetworkInfoHeader;
import gov.nist.javax.sip.header.ims.PAssertedIdentityHeader;
import gov.nist.javax.sip.header.ims.PAssociatedURIHeader;
import gov.nist.javax.sip.header.ims.PCalledPartyIDHeader;
import gov.nist.javax.sip.header.ims.PChargingFunctionAddressesHeader;
import gov.nist.javax.sip.header.ims.PChargingVectorHeader;
import gov.nist.javax.sip.header.ims.PMediaAuthorizationHeader;
import gov.nist.javax.sip.header.ims.PPreferredIdentityHeader;
import gov.nist.javax.sip.header.ims.PVisitedNetworkIDHeader;
import gov.nist.javax.sip.header.ims.PathHeader;
import gov.nist.javax.sip.header.ims.PrivacyHeader;
import gov.nist.javax.sip.header.ims.SecurityClientHeader;
import gov.nist.javax.sip.header.ims.SecurityServerHeader;
import gov.nist.javax.sip.header.ims.SecurityVerifyHeader;
import gov.nist.javax.sip.header.ims.ServiceRouteHeader;
import gov.nist.javax.sip.parser.extensions.JoinParser;
import gov.nist.javax.sip.parser.extensions.MinSEParser;
import gov.nist.javax.sip.parser.extensions.ReferencesParser;
import gov.nist.javax.sip.parser.extensions.ReferredByParser;
import gov.nist.javax.sip.parser.extensions.ReplacesParser;
import gov.nist.javax.sip.parser.extensions.SessionExpiresParser;
import gov.nist.javax.sip.parser.ims.PAccessNetworkInfoParser;
import gov.nist.javax.sip.parser.ims.PAssertedIdentityParser;
import gov.nist.javax.sip.parser.ims.PAssociatedURIParser;
import gov.nist.javax.sip.parser.ims.PCalledPartyIDParser;
import gov.nist.javax.sip.parser.ims.PChargingFunctionAddressesParser;
import gov.nist.javax.sip.parser.ims.PChargingVectorParser;
import gov.nist.javax.sip.parser.ims.PMediaAuthorizationParser;
import gov.nist.javax.sip.parser.ims.PPreferredIdentityParser;
import gov.nist.javax.sip.parser.ims.PVisitedNetworkIDParser;
import gov.nist.javax.sip.parser.ims.PathParser;
import gov.nist.javax.sip.parser.ims.PrivacyParser;
import gov.nist.javax.sip.parser.ims.SecurityClientParser;
import gov.nist.javax.sip.parser.ims.SecurityServerParser;
import gov.nist.javax.sip.parser.ims.SecurityVerifyParser;
import gov.nist.javax.sip.parser.ims.ServiceRouteParser;

import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.util.Hashtable;

import javax.sip.header.AcceptEncodingHeader;
import javax.sip.header.AcceptHeader;
import javax.sip.header.AcceptLanguageHeader;
import javax.sip.header.AlertInfoHeader;
import javax.sip.header.AllowEventsHeader;
import javax.sip.header.AllowHeader;
import javax.sip.header.AuthenticationInfoHeader;
import javax.sip.header.AuthorizationHeader;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.CallInfoHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentDispositionHeader;
import javax.sip.header.ContentEncodingHeader;
import javax.sip.header.ContentLanguageHeader;
import javax.sip.header.ContentLengthHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.DateHeader;
import javax.sip.header.ErrorInfoHeader;
import javax.sip.header.EventHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.InReplyToHeader;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.MimeVersionHeader;
import javax.sip.header.MinExpiresHeader;
import javax.sip.header.OrganizationHeader;
import javax.sip.header.PriorityHeader;
import javax.sip.header.ProxyAuthenticateHeader;
import javax.sip.header.ProxyAuthorizationHeader;
import javax.sip.header.ProxyRequireHeader;
import javax.sip.header.RAckHeader;
import javax.sip.header.RSeqHeader;
import javax.sip.header.ReasonHeader;
import javax.sip.header.RecordRouteHeader;
import javax.sip.header.ReferToHeader;
import javax.sip.header.ReplyToHeader;
import javax.sip.header.RequireHeader;
import javax.sip.header.RetryAfterHeader;
import javax.sip.header.RouteHeader;
import javax.sip.header.SIPETagHeader;
import javax.sip.header.SIPIfMatchHeader;
import javax.sip.header.ServerHeader;
import javax.sip.header.SubjectHeader;
import javax.sip.header.SubscriptionStateHeader;
import javax.sip.header.SupportedHeader;
import javax.sip.header.TimeStampHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.UnsupportedHeader;
import javax.sip.header.UserAgentHeader;
import javax.sip.header.ViaHeader;
import javax.sip.header.WWWAuthenticateHeader;
import javax.sip.header.WarningHeader;

public class ParserFactory {

    /**
     * create a parser for a header. This is the parser factory.
     */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:43.109 -0500", hash_original_method = "6AFE9CBA6B56E35C594D2C594C957D14", hash_generated_method = "EBE6484005C937078EB2F255949EA23A")
    
public static HeaderParser createParser(String line)
        throws ParseException {
        String headerName = Lexer.getHeaderName(line);
        String headerValue = Lexer.getHeaderValue(line);
        if (headerName == null || headerValue == null)
            throw new ParseException("The header name or value is null", 0);

        Class parserClass = (Class) parserTable.get(SIPHeaderNamesCache.toLowerCase(headerName));
        if (parserClass != null) {
            try {
                Constructor cons = (Constructor) parserConstructorCache.get(parserClass);
                if (cons == null) {
                    cons = parserClass.getConstructor(constructorArgs);
                    parserConstructorCache.put(parserClass, cons);
                }
                Object[] args = new Object[1];
                args[0] = line;
                HeaderParser retval = (HeaderParser) cons.newInstance(args);
                return retval;

            } catch (Exception ex) {
                InternalErrorHandler.handleException(ex);
                return null; // to placate the compiler.
            }

        } else {
            // Just generate a generic SIPHeader. We define
            // parsers only for the above.
            return new HeaderParser(line);
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:43.098 -0500", hash_original_field = "EDA93F77CCF1F9B25EBF295EE61751F5", hash_generated_field = "0A3C66CA4CD5AF69A34601C4EB5A6186")

    private static Hashtable<String,Class<? extends HeaderParser>> parserTable;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:43.101 -0500", hash_original_field = "7321F45FBFF47190C0E578923D62E147", hash_generated_field = "AA78F4096AC7D2050F7D84F484AF823F")

    private static Class[] constructorArgs;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:55:43.104 -0500", hash_original_field = "FA6A81A9B7C9791632C1CB31B7466221", hash_generated_field = "DDE7A2912B25508540047823ADE279C8")

    private static Hashtable parserConstructorCache;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:24:38.217 -0400", hash_original_method = "402FA7B6FCFC84B93C7F2F3C6D2A34EC", hash_generated_method = "402FA7B6FCFC84B93C7F2F3C6D2A34EC")
    public ParserFactory ()
    {
        //Synthesized constructor
    }
    static {
        parserTable = new Hashtable<String,Class<? extends HeaderParser>>();
        parserConstructorCache = new Hashtable();
        constructorArgs = new Class[1];
        constructorArgs[0] = String.class;
        parserTable.put(ReplyToHeader.NAME.toLowerCase(), ReplyToParser.class);
        parserTable.put(
            InReplyToHeader.NAME.toLowerCase(),
            InReplyToParser.class);
        parserTable.put(
            AcceptEncodingHeader.NAME.toLowerCase(),
            AcceptEncodingParser.class);
        parserTable.put(
            AcceptLanguageHeader.NAME.toLowerCase(),
            AcceptLanguageParser.class);
        parserTable.put("t", ToParser.class);
        parserTable.put(ToHeader.NAME.toLowerCase(), ToParser.class);
        parserTable.put(FromHeader.NAME.toLowerCase(), FromParser.class);
        parserTable.put("f", FromParser.class);
        parserTable.put(CSeqHeader.NAME.toLowerCase(), CSeqParser.class);
        parserTable.put(ViaHeader.NAME.toLowerCase(), ViaParser.class);
        parserTable.put("v", ViaParser.class);
        parserTable.put(ContactHeader.NAME.toLowerCase(), ContactParser.class);
        parserTable.put("m", ContactParser.class);
        parserTable.put(
            ContentTypeHeader.NAME.toLowerCase(),
            ContentTypeParser.class);
        parserTable.put("c", ContentTypeParser.class);
        parserTable.put(
            ContentLengthHeader.NAME.toLowerCase(),
            ContentLengthParser.class);
        parserTable.put("l", ContentLengthParser.class);
        parserTable.put(
            AuthorizationHeader.NAME.toLowerCase(),
            AuthorizationParser.class);
        parserTable.put(
            WWWAuthenticateHeader.NAME.toLowerCase(),
            WWWAuthenticateParser.class);
        parserTable.put(CallIdHeader.NAME.toLowerCase(), CallIDParser.class);
        parserTable.put("i", CallIDParser.class);
        parserTable.put(RouteHeader.NAME.toLowerCase(), RouteParser.class);
        parserTable.put(
            RecordRouteHeader.NAME.toLowerCase(),
            RecordRouteParser.class);
        parserTable.put(DateHeader.NAME.toLowerCase(), DateParser.class);
        parserTable.put(
            ProxyAuthorizationHeader.NAME.toLowerCase(),
            ProxyAuthorizationParser.class);
        parserTable.put(
            ProxyAuthenticateHeader.NAME.toLowerCase(),
            ProxyAuthenticateParser.class);
        parserTable.put(
            RetryAfterHeader.NAME.toLowerCase(),
            RetryAfterParser.class);
        parserTable.put(RequireHeader.NAME.toLowerCase(), RequireParser.class);
        parserTable.put(
            ProxyRequireHeader.NAME.toLowerCase(),
            ProxyRequireParser.class);
        parserTable.put(
            TimeStampHeader.NAME.toLowerCase(),
            TimeStampParser.class);
        parserTable.put(
            UnsupportedHeader.NAME.toLowerCase(),
            UnsupportedParser.class);
        parserTable.put(
            UserAgentHeader.NAME.toLowerCase(),
            UserAgentParser.class);
        parserTable.put(
            SupportedHeader.NAME.toLowerCase(),
            SupportedParser.class);
        parserTable.put("k", SupportedParser.class);
        parserTable.put(ServerHeader.NAME.toLowerCase(), ServerParser.class);
        parserTable.put(SubjectHeader.NAME.toLowerCase(), SubjectParser.class);
        parserTable.put( "s", SubjectParser.class); 
        parserTable.put(
            SubscriptionStateHeader.NAME.toLowerCase(),
            SubscriptionStateParser.class);
        parserTable.put(
            MaxForwardsHeader.NAME.toLowerCase(),
            MaxForwardsParser.class);
        parserTable.put(
            MimeVersionHeader.NAME.toLowerCase(),
            MimeVersionParser.class);
        parserTable.put(
            MinExpiresHeader.NAME.toLowerCase(),
            MinExpiresParser.class);
        parserTable.put(
            OrganizationHeader.NAME.toLowerCase(),
            OrganizationParser.class);
        parserTable.put(
            PriorityHeader.NAME.toLowerCase(),
            PriorityParser.class);
        parserTable.put(RAckHeader.NAME.toLowerCase(), RAckParser.class);
        parserTable.put(RSeqHeader.NAME.toLowerCase(), RSeqParser.class);
        parserTable.put(ReasonHeader.NAME.toLowerCase(), ReasonParser.class);
        parserTable.put(WarningHeader.NAME.toLowerCase(), WarningParser.class);
        parserTable.put(ExpiresHeader.NAME.toLowerCase(), ExpiresParser.class);
        parserTable.put(EventHeader.NAME.toLowerCase(), EventParser.class);
        parserTable.put("o", EventParser.class);
        parserTable.put(
            ErrorInfoHeader.NAME.toLowerCase(),
            ErrorInfoParser.class);
        parserTable.put(
            ContentLanguageHeader.NAME.toLowerCase(),
            ContentLanguageParser.class);
        parserTable.put(
            ContentEncodingHeader.NAME.toLowerCase(),
            ContentEncodingParser.class);
        parserTable.put("e", ContentEncodingParser.class);
        parserTable.put(
            ContentDispositionHeader.NAME.toLowerCase(),
            ContentDispositionParser.class);
        parserTable.put(
            CallInfoHeader.NAME.toLowerCase(),
            CallInfoParser.class);
        parserTable.put(
            AuthenticationInfoHeader.NAME.toLowerCase(),
            AuthenticationInfoParser.class);
        parserTable.put(AllowHeader.NAME.toLowerCase(), AllowParser.class);
        parserTable.put(
            AllowEventsHeader.NAME.toLowerCase(),
            AllowEventsParser.class);
        parserTable.put("u", AllowEventsParser.class);
        parserTable.put(
            AlertInfoHeader.NAME.toLowerCase(),
            AlertInfoParser.class);
        parserTable.put(AcceptHeader.NAME.toLowerCase(), AcceptParser.class);
        parserTable.put(ReferToHeader.NAME.toLowerCase(), ReferToParser.class);
        parserTable.put("r", ReferToParser.class);
        parserTable.put(SIPETagHeader.NAME.toLowerCase(), SIPETagParser.class);
        parserTable.put(SIPIfMatchHeader.NAME.toLowerCase(), SIPIfMatchParser.class);
        parserTable.put(PAccessNetworkInfoHeader.NAME.toLowerCase(), PAccessNetworkInfoParser.class);
        parserTable.put(PAssertedIdentityHeader.NAME.toLowerCase(), PAssertedIdentityParser.class);
        parserTable.put(PPreferredIdentityHeader.NAME.toLowerCase(), PPreferredIdentityParser.class);
        parserTable.put(PChargingVectorHeader.NAME.toLowerCase(), PChargingVectorParser.class);
        parserTable.put(PChargingFunctionAddressesHeader.NAME.toLowerCase(), PChargingFunctionAddressesParser.class);
        parserTable.put(PMediaAuthorizationHeader.NAME.toLowerCase(), PMediaAuthorizationParser.class);
        parserTable.put(PathHeader.NAME.toLowerCase(), PathParser.class);
        parserTable.put(PrivacyHeader.NAME.toLowerCase(), PrivacyParser.class);
        parserTable.put(ServiceRouteHeader.NAME.toLowerCase(), ServiceRouteParser.class);
        parserTable.put(PVisitedNetworkIDHeader.NAME.toLowerCase(), PVisitedNetworkIDParser.class);
        parserTable.put(PAssociatedURIHeader.NAME.toLowerCase(), PAssociatedURIParser.class);
        parserTable.put(PCalledPartyIDHeader.NAME.toLowerCase(), PCalledPartyIDParser.class);
        parserTable.put(SecurityServerHeader.NAME.toLowerCase(), SecurityServerParser.class);
        parserTable.put(SecurityClientHeader.NAME.toLowerCase(), SecurityClientParser.class);
        parserTable.put(SecurityVerifyHeader.NAME.toLowerCase(), SecurityVerifyParser.class);
        parserTable.put(ReferredBy.NAME.toLowerCase(), ReferredByParser.class);
        parserTable.put("b", ReferToParser.class);
        parserTable.put(SessionExpires.NAME.toLowerCase(), SessionExpiresParser.class);
        parserTable.put("x", SessionExpiresParser.class);
        parserTable.put(MinSE.NAME.toLowerCase(), MinSEParser.class);
        parserTable.put(Replaces.NAME.toLowerCase(), ReplacesParser.class);
        parserTable.put(Join.NAME.toLowerCase(), JoinParser.class);
        parserTable.put(References.NAME.toLowerCase(), ReferencesParser.class);
    }
    
}

