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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/protocol/RequestAddCookies.java $
 * $Revision: 673450 $
 * $Date: 2008-07-02 10:35:05 -0700 (Wed, 02 Jul 2008) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.http.client.protocol;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.ProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class RequestAddCookies implements HttpRequestInterceptor {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:44.734 -0500", hash_original_field = "0DA7E40E862C937570CA6B0D96D2555A", hash_generated_field = "3FCE5BFF671FE7B3BB3E2D744C5E5D2C")

    private final Log log = LogFactory.getLog(getClass());
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:44.736 -0500", hash_original_method = "33F7F2F89F06A1D5C3C4E627DB5C44E6", hash_generated_method = "C8C07D397BBD3C87402B5D7CCF39E7E5")
    
public RequestAddCookies() {
        super();
    }
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:44.741 -0500", hash_original_method = "6C6FAAF5CABF854B7199A55A540BF3C8", hash_generated_method = "AF85D0B7666B3821BD93702C99E0C256")
    
public void process(final HttpRequest request, final HttpContext context) 
            throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        
        // Obtain cookie store
        CookieStore cookieStore = (CookieStore) context.getAttribute(
                ClientContext.COOKIE_STORE);
        if (cookieStore == null) {
            this.log.info("Cookie store not available in HTTP context");
            return;
        }
        
        // Obtain the registry of cookie specs
        CookieSpecRegistry registry= (CookieSpecRegistry) context.getAttribute(
                ClientContext.COOKIESPEC_REGISTRY);
        if (registry == null) {
            this.log.info("CookieSpec registry not available in HTTP context");
            return;
        }
        
        // Obtain the target host (required)
        HttpHost targetHost = (HttpHost) context.getAttribute(
                ExecutionContext.HTTP_TARGET_HOST);
        if (targetHost == null) {
            throw new IllegalStateException("Target host not specified in HTTP context");
        }
        
        // Obtain the client connection (required)
        ManagedClientConnection conn = (ManagedClientConnection) context.getAttribute(
                ExecutionContext.HTTP_CONNECTION);
        if (conn == null) {
            throw new IllegalStateException("Client connection not specified in HTTP context");
        }

        String policy = HttpClientParams.getCookiePolicy(request.getParams());
        if (this.log.isDebugEnabled()) {
            this.log.debug("CookieSpec selected: " + policy);
        }
        
        URI requestURI;
        if (request instanceof HttpUriRequest) {
            requestURI = ((HttpUriRequest) request).getURI();
        } else {
            try {
                requestURI = new URI(request.getRequestLine().getUri());
            } catch (URISyntaxException ex) {
                throw new ProtocolException("Invalid request URI: " + 
                        request.getRequestLine().getUri(), ex);
            }
        }
        
        String hostName = targetHost.getHostName();
        int port = targetHost.getPort();
        if (port < 0) {
            port = conn.getRemotePort();
        }
        
        CookieOrigin cookieOrigin = new CookieOrigin(
                hostName, 
                port, 
                requestURI.getPath(),
                conn.isSecure());
        
        // Get an instance of the selected cookie policy
        CookieSpec cookieSpec = registry.getCookieSpec(policy, request.getParams());
        // Get all cookies available in the HTTP state
        List<Cookie> cookies = new ArrayList<Cookie>(cookieStore.getCookies());
        // Find cookies matching the given origin
        List<Cookie> matchedCookies = new ArrayList<Cookie>();
        for (Cookie cookie : cookies) {
            if (cookieSpec.match(cookie, cookieOrigin)) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Cookie " + cookie + " match " + cookieOrigin);
                }
                matchedCookies.add(cookie);
            }
        }
        // Generate Cookie request headers
        if (!matchedCookies.isEmpty()) {
            List<Header> headers = cookieSpec.formatCookies(matchedCookies);
            for (Header header : headers) {
                request.addHeader(header);
            }
        }
        
        int ver = cookieSpec.getVersion();
        if (ver > 0) {
            boolean needVersionHeader = false;
            for (Cookie cookie : matchedCookies) {
                if (ver != cookie.getVersion()) {
                    needVersionHeader = true;
                }
            }

            if (needVersionHeader) {
                Header header = cookieSpec.getVersionHeader();
                if (header != null) {
                    // Advertise cookie version support
                    request.addHeader(header);
                }
            }
        }
        
        // Stick the CookieSpec and CookieOrigin instances to the HTTP context
        // so they could be obtained by the response interceptor
        context.setAttribute(ClientContext.COOKIE_SPEC, cookieSpec);
        context.setAttribute(ClientContext.COOKIE_ORIGIN, cookieOrigin);
    }
    
}

