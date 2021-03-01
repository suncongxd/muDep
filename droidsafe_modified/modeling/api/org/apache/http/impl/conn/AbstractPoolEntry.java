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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/conn/AbstractPoolEntry.java $
 * $Revision: 658775 $
 * $Date: 2008-05-21 10:30:45 -0700 (Wed, 21 May 2008) $
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


package org.apache.http.impl.conn;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public abstract class AbstractPoolEntry {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.141 -0500", hash_original_field = "E40B74D82804A7A9813CCFFD83EFDC95", hash_generated_field = "6D2607DA479BDA11A25B13C47E7F0C5C")

    protected  ClientConnectionOperator connOperator;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.143 -0500", hash_original_field = "45EB9987EA77D96C4F33011A8FFCB241", hash_generated_field = "D4C9C47F1F7C872802E0304B9FD77E44")

    protected  OperatedClientConnection connection;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.145 -0500", hash_original_field = "8824947D446181E666F5CCE159788709", hash_generated_field = "C6EE8D3571C9CAFA0488F1263D2FAF69")

    //@@@ currently accessed from connection manager(s) as attribute
    //@@@ avoid that, derived classes should decide whether update is allowed
    //@@@ SCCM: yes, TSCCM: no
    protected volatile HttpRoute route;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.148 -0500", hash_original_field = "9AF76BE6C13059245F182C3ACEF90276", hash_generated_field = "84C5CEDAE5C6F44A990A3028DB7D51E1")

    protected volatile Object state;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.150 -0500", hash_original_field = "F03334D6C9BF95AC15CF9711AE96481C", hash_generated_field = "178FE92301487BDC4E429ED14BD188F8")

    protected volatile RouteTracker tracker;

    /**
     * Creates a new pool entry.
     *
     * @param connOperator     the Connection Operator for this entry
     * @param route   the planned route for the connection,
     *                or <code>null</code>
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.153 -0500", hash_original_method = "87A56F69F978A1F4113143BFD741AEF0", hash_generated_method = "E5109B1670976D516FDE9A73A8892416")
    
protected AbstractPoolEntry(ClientConnectionOperator connOperator,
                                HttpRoute route) {
        super();
        if (connOperator == null) {
            throw new IllegalArgumentException("Connection operator may not be null");
        }
        this.connOperator = connOperator;
        this.connection = connOperator.createConnection();
        this.route = route;
        this.tracker = null;
    }

    /**
     * Returns the state object associated with this pool entry.
     * 
     * @return The state object
     */
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.155 -0500", hash_original_method = "079ECF1B585CB82D2480C2A65F324FB6", hash_generated_method = "D182279B326BD725F747B0B0AAA3409A")
    
public Object getState() {
        return state;
    }
    
    /**
     * Assigns a state object to this pool entry.
     * 
     * @param state The state object
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.157 -0500", hash_original_method = "9D16F7D7F90D43454808FD39B427C300", hash_generated_method = "224578FC3E85EF51536EB7D5810BFD25")
    
public void setState(final Object state) {
        this.state = state;
    }
    
    /**
     * Opens the underlying connection.
     *
     * @param route         the route along which to open the connection
     * @param context       the context for opening the connection
     * @param params        the parameters for opening the connection
     *
     * @throws IOException  in case of a problem
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.160 -0500", hash_original_method = "52A8017CB43DDF5506166F15621D416B", hash_generated_method = "8CE23F25206850F891145EB8898653D8")
    
public void open(HttpRoute route,
                     HttpContext context, HttpParams params)
        throws IOException {

        if (route == null) {
            throw new IllegalArgumentException
                ("Route must not be null.");
        }
        //@@@ is context allowed to be null? depends on operator?
        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }
        if ((this.tracker != null) && this.tracker.isConnected()) {
            throw new IllegalStateException("Connection already open.");
        }

        // - collect the arguments
        // - call the operator
        // - update the tracking data
        // In this order, we can be sure that only a successful
        // opening of the connection will be tracked.

        //@@@ verify route against planned route?

        this.tracker = new RouteTracker(route);
        final HttpHost proxy  = route.getProxyHost();

        connOperator.openConnection
            (this.connection,
             (proxy != null) ? proxy : route.getTargetHost(),
             route.getLocalAddress(),
             context, params);

        RouteTracker localTracker = tracker; // capture volatile

        // If this tracker was reset while connecting,
        // fail early.
        if (localTracker == null) {
            throw new IOException("Request aborted");
        }

        if (proxy == null) {
            localTracker.connectTarget(this.connection.isSecure());
        } else {
            localTracker.connectProxy(proxy, this.connection.isSecure());
        }

    } // open

    /**
     * Tracks tunnelling of the connection to the target.
     * The tunnel has to be established outside by sending a CONNECT
     * request to the (last) proxy.
     *
     * @param secure    <code>true</code> if the tunnel should be
     *                  considered secure, <code>false</code> otherwise
     * @param params    the parameters for tunnelling the connection
     *
     * @throws IOException  in case of a problem
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.162 -0500", hash_original_method = "01B7EFBCCE4449D378FBB0354733064C", hash_generated_method = "557C53C56C5D392F3318C8E0C0B8F3E1")
    
public void tunnelTarget(boolean secure, HttpParams params)
        throws IOException {

        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }

        //@@@ check for proxy in planned route?
        if ((this.tracker == null) || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        }
        if (this.tracker.isTunnelled()) {
            throw new IllegalStateException
                ("Connection is already tunnelled.");
        }

        // LOG.debug?

        this.connection.update(null, tracker.getTargetHost(),
                               secure, params);
        this.tracker.tunnelTarget(secure);

    } // tunnelTarget

    /**
     * Tracks tunnelling of the connection to a chained proxy.
     * The tunnel has to be established outside by sending a CONNECT
     * request to the previous proxy.
     *
     * @param next      the proxy to which the tunnel was established.
     *  See {@link org.apache.http.conn.ManagedClientConnection#tunnelProxy
     *                                  ManagedClientConnection.tunnelProxy}
     *                  for details.
     * @param secure    <code>true</code> if the tunnel should be
     *                  considered secure, <code>false</code> otherwise
     * @param params    the parameters for tunnelling the connection
     *
     * @throws IOException  in case of a problem
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.165 -0500", hash_original_method = "5E0B2221F8D2EF0994AAAD177B417545", hash_generated_method = "F4F65B7E9093228D7B22317C2AE4DEBF")
    
public void tunnelProxy(HttpHost next, boolean secure, HttpParams params)
        throws IOException {

        if (next == null) {
            throw new IllegalArgumentException
                ("Next proxy must not be null.");
        }
        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }

        //@@@ check for proxy in planned route?
        if ((this.tracker == null) || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        }

        // LOG.debug?

        this.connection.update(null, next, secure, params);
        this.tracker.tunnelProxy(next, secure);

    } // tunnelProxy

    /**
     * Layers a protocol on top of an established tunnel.
     *
     * @param context   the context for layering
     * @param params    the parameters for layering
     *
     * @throws IOException  in case of a problem
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.168 -0500", hash_original_method = "1EA8AE44B2BF05DF27C07019783A5F43", hash_generated_method = "FE4B58D9741614D3DCF53109A5ACB4F6")
    
public void layerProtocol(HttpContext context, HttpParams params)
        throws IOException {

        //@@@ is context allowed to be null? depends on operator?
        if (params == null) {
            throw new IllegalArgumentException
                ("Parameters must not be null.");
        }

        if ((this.tracker == null) || !this.tracker.isConnected()) {
            throw new IllegalStateException("Connection not open.");
        }
        if (!this.tracker.isTunnelled()) {
            //@@@ allow this?
            throw new IllegalStateException
                ("Protocol layering without a tunnel not supported.");
        }
        if (this.tracker.isLayered()) {
            throw new IllegalStateException
                ("Multiple protocol layering not supported.");
        }

        // - collect the arguments
        // - call the operator
        // - update the tracking data
        // In this order, we can be sure that only a successful
        // layering on top of the connection will be tracked.

        final HttpHost target = tracker.getTargetHost();

        connOperator.updateSecureConnection(this.connection, target,
                                             context, params);

        this.tracker.layerProtocol(this.connection.isSecure());

    } // layerProtocol

    /**
     * Shuts down the entry.
     * 
     * If {@link #open(HttpRoute, HttpContext, HttpParams)} is in progress,
     * this will cause that open to possibly throw an {@link IOException}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:32.170 -0500", hash_original_method = "9A8AC70CC76BCE1A1C90EAA4BDE4B23E", hash_generated_method = "AF9ECE305AF8983017FB26E5E30E930A")
    
protected void shutdownEntry() { 
        tracker = null;
    }
    
}

