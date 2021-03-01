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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/params/HttpClientParams.java $
 * $Revision: 659595 $
 * $Date: 2008-05-23 09:47:14 -0700 (Fri, 23 May 2008) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.http.client.params;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import org.apache.http.params.HttpParams;

public class HttpClientParams {

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.974 -0500", hash_original_method = "7F5EC64678F2BF99B5FC6B00C0649726", hash_generated_method = "3A3F0F1B231BB4A7783DE3BE3B1EB0F8")
    
public static boolean isRedirecting(final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return params.getBooleanParameter
            (ClientPNames.HANDLE_REDIRECTS, true); 
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.976 -0500", hash_original_method = "D25A533CDBBF2DE2854A97D6ED189799", hash_generated_method = "303783B61F9AC70C54D356F35A804883")
    
public static void setRedirecting(final HttpParams params, boolean value) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        params.setBooleanParameter
            (ClientPNames.HANDLE_REDIRECTS, value); 
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.979 -0500", hash_original_method = "E99E0AD068BF86729C62F4A08E92A6D0", hash_generated_method = "722391365B5C6A3E5CC0B94E05778015")
    
public static boolean isAuthenticating(final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return params.getBooleanParameter
            (ClientPNames.HANDLE_AUTHENTICATION, true); 
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.981 -0500", hash_original_method = "4EEACF51057E652FCDB49FD3B08F1869", hash_generated_method = "1A46743B616F189FEFEE3F612AC08F23")
    
public static void setAuthenticating(final HttpParams params, boolean value) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        params.setBooleanParameter
            (ClientPNames.HANDLE_AUTHENTICATION, value); 
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.984 -0500", hash_original_method = "779C85BA9BC8F624D4E0DEDE86759022", hash_generated_method = "02DF876C61E723B59DF4DAE0F76A231D")
    
public static String getCookiePolicy(final HttpParams params) { 
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String cookiePolicy = (String)
            params.getParameter(ClientPNames.COOKIE_POLICY);
        if (cookiePolicy == null) {
            return CookiePolicy.BEST_MATCH;
        }
        return cookiePolicy;
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.986 -0500", hash_original_method = "544E4BCF61ABC6424F17B17EB5E4FA3B", hash_generated_method = "729030AEE3327883720C839166706874")
    
public static void setCookiePolicy(final HttpParams params, final String cookiePolicy) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        params.setParameter(ClientPNames.COOKIE_POLICY, cookiePolicy);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:43.971 -0500", hash_original_method = "C1A6271AA6801B961C8CB360166C77E0", hash_generated_method = "15C82D3BA2561F385477CD1643F8D62E")
    
private HttpClientParams() {
        super();
    }
    
}

