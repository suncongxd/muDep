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
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package javax.net.ssl;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.net.SocketFactory;

public abstract class SSLSocketFactory extends SocketFactory {

    /**
     * Returns the default {@code SSLSocketFactory} instance. The default is
     * defined by the security property {@code 'ssl.SocketFactory.provider'}.
     *
     * @return the default ssl socket factory instance.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.846 -0500", hash_original_method = "F37B707301EB6A97B76ADAB5F497E9E1", hash_generated_method = "B4D96C783A50122614CF39201E30880B")
    
public static synchronized SocketFactory getDefault() {
        if (defaultSocketFactory != null) {
            return defaultSocketFactory;
        }
        if (defaultName == null) {
            defaultName = Security.getProperty("ssl.SocketFactory.provider");
            if (defaultName != null) {
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                if (cl == null) {
                    cl = ClassLoader.getSystemClassLoader();
                }
                try {
                    final Class<?> sfc = Class.forName(defaultName, true, cl);
                    defaultSocketFactory = (SocketFactory) sfc.newInstance();
                } catch (Exception e) {
                    System.logE("Problem creating " + defaultName, e);
                }
            }
        }

        if (defaultSocketFactory == null) {
            SSLContext context;
            try {
                context = SSLContext.getDefault();
            } catch (NoSuchAlgorithmException e) {
                context = null;
            }
            if (context != null) {
                defaultSocketFactory = context.getSocketFactory();
            }
        }
        if (defaultSocketFactory == null) {
            // Use internal implementation
            defaultSocketFactory = new DefaultSSLSocketFactory("No SSLSocketFactory installed");
        }
        return defaultSocketFactory;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.840 -0500", hash_original_field = "D445111C237045BAFE004B62E7689361", hash_generated_field = "B1CEB63DACD9C74231F5E90BB143FC2F")

    // The default SSL socket factory
    private static SocketFactory defaultSocketFactory;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.842 -0500", hash_original_field = "84BC5372A12AA239726FB6B8D530CCAC", hash_generated_field = "B5EAD347CF9492DD929521E60E38417F")

    private static String defaultName;

    /**
     * Creates a new {@code SSLSocketFactory}.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.849 -0500", hash_original_method = "A3C82F74D64165B62D4E0BF2DAC4717C", hash_generated_method = "1A3C4ADDC93D3C6DB5B85245756B722D")
    
public SSLSocketFactory() {
    }

    /**
     * Returns the names of the cipher suites that are enabled by default.
     *
     * @return the names of the cipher suites that are enabled by default.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.852 -0500", hash_original_method = "39637FAA2A428B026F3142CDE9E65872", hash_generated_method = "B1D7CA4E890A48074B49D06D9FFA1B03")
    
public abstract String[] getDefaultCipherSuites();

    /**
     * Returns the names of the cipher suites that are supported and could be
     * enabled for an SSL connection.
     *
     * @return the names of the cipher suites that are supported.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.854 -0500", hash_original_method = "972B577B4B04B597248145E0CF6E63D2", hash_generated_method = "09D1FF6565C262832AA989BA28390929")
    
public abstract String[] getSupportedCipherSuites();

    /**
     * Creates an {@code SSLSocket} over the specified socket that is connected
     * to the specified host at the specified port.
     *
     * @param s
     *            the socket.
     * @param host
     *            the host.
     * @param port
     *            the port number.
     * @param autoClose
     *            {@code true} if socket {@code s} should be closed when the
     *            created socket is closed, {@code false} if the socket
     *            {@code s} should be left open.
     * @return the creates ssl socket.
     * @throws IOException
     *             if creating the socket fails.
     * @throws java.net.UnknownHostException
     *             if the host is unknown.
     */
    
    @DSComment("Abstract Method")
    @DSSpec(DSCat.INTERNET)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:00.856 -0500", hash_original_method = "92AB7E02FDD9B6DD187F9A9C0226B2B5", hash_generated_method = "B9E6F078DE3B349829055D34988DB96B")
    
public abstract Socket createSocket(Socket s, String host, int port, boolean autoClose)
            throws IOException;
}

