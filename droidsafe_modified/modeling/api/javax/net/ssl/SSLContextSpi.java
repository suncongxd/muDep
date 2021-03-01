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
import java.security.KeyManagementException;
import java.security.SecureRandom;

public abstract class SSLContextSpi {

    /**
     * Creates a new {@code SSLContextSpi} instance.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.598 -0500", hash_original_method = "C071A8DB81BAA2AB9BA30E20CDF7E4D3", hash_generated_method = "C7069F93AA2E00C38D14556562CFA0A9")
    
public SSLContextSpi() {
    }

    /**
     * Initializes this {@code SSLContext} instance. All of the arguments are
     * optional, and the security providers will be searched for the required
     * implementations of the needed algorithms.
     *
     * @param km
     *            the key sources or {@code null}.
     * @param tm
     *            the trust decision sources or {@code null}.
     * @param sr
     *            the randomness source or {@code null.}
     * @throws KeyManagementException
     *             if initializing this instance fails.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.601 -0500", hash_original_method = "245D0F04D06FF5102BFAAD599C6F8184", hash_generated_method = "CCC513EC7732E4B2172CB0AA95F1970B")
    
protected abstract void engineInit(KeyManager[] km, TrustManager[] tm, SecureRandom sr)
            throws KeyManagementException;

    /**
     * Returns a socket factory for this instance.
     *
     * @return a socket factory for this instance.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.604 -0500", hash_original_method = "39786C3A44B2F55622E1413C0A3046A6", hash_generated_method = "0711AE2C6939557ACD8EC063ADEEE744")
    
protected abstract SSLSocketFactory engineGetSocketFactory();

    /**
     * Returns a server socket factory for this instance.
     *
     * @return a server socket factory for this instance.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.606 -0500", hash_original_method = "48EAB4B5184298F54D0CA50CF9CD0C32", hash_generated_method = "FA53224652D9161260CA952891E267FC")
    
protected abstract SSLServerSocketFactory engineGetServerSocketFactory();

    /**
     * Creates an {@code SSLEngine} instance from this context with the
     * specified hostname and port.
     *
     * @param host
     *            the name of the host
     * @param port
     *            the port
     * @return an {@code SSLEngine} instance from this context.
     * @throws UnsupportedOperationException
     *             if the provider does not support the operation.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.609 -0500", hash_original_method = "723A4AE0790C4C8818CA14B5260F23B9", hash_generated_method = "A89C09CEAF6AE7B8AD529CA1BF1E82B0")
    
protected abstract SSLEngine engineCreateSSLEngine(String host, int port);

    /**
     * Creates an {@code SSLEngine} instance from this context.
     *
     * @return an {@code SSLEngine} instance from this context.
     * @throws UnsupportedOperationException
     *             if the provider does not support the operation.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.611 -0500", hash_original_method = "3C3670A71F62C061AC866CDADB563DA8", hash_generated_method = "B6E7A1B9FDF8F09F2D5759D4C8B62113")
    
protected abstract SSLEngine engineCreateSSLEngine();

    /**
     * Returns the SSL session context that encapsulates the set of SSL sessions
     * that can be used for the server side of the SSL handshake.
     *
     * @return the SSL server session context for this context or {@code null}
     *         if the underlying provider does not provide an implementation of
     *         the {@code SSLSessionContext} interface.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.613 -0500", hash_original_method = "A84D19BBBD892F761878CF9E4ED14018", hash_generated_method = "ED189869B6C29133BABCC4115647C5FB")
    
protected abstract SSLSessionContext engineGetServerSessionContext();

    /**
     * Returns the SSL session context that encapsulates the set of SSL sessions
     * that can be used for the client side of the SSL handshake.
     *
     * @return the SSL client session context for this context or {@code null}
     *         if the underlying provider does not provide an implementation of
     *         the {@code SSLSessionContext} interface.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.615 -0500", hash_original_method = "DCB31B04B4F8E364C2142249E76829A7", hash_generated_method = "FD0BB50D604EB0AF4BF937EF0E20E052")
    
protected abstract SSLSessionContext engineGetClientSessionContext();

    /**
     * Returns a new SSLParameters instance that includes the default
     * SSL handshake parameters values including cipher suites,
     * protocols, and client authentication.
     *
     * <p>The default implementation returns an SSLParameters with values
     * based an SSLSocket created from this instances SocketFactory.
     *
     * @since 1.6
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.617 -0500", hash_original_method = "38369B007DD3DFBC2F17B36F6861DA09", hash_generated_method = "19B85BDCFFFD7283752B67100B24DF80")
    
protected javax.net.ssl.SSLParameters engineGetDefaultSSLParameters() {
        return createSSLParameters(false);
    }

    /**
     * Returns a new SSLParameters instance that includes all
     * supported cipher suites and protocols.
     *
     * <p>The default implementation returns an SSLParameters with values
     * based an SSLSocket created from this instances SocketFactory.
     *
     * @since 1.6
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.620 -0500", hash_original_method = "86210C07C6F5D2D352BAF9AB53AE79CE", hash_generated_method = "43B4830D41E80F841649FB50919541A4")
    
protected javax.net.ssl.SSLParameters engineGetSupportedSSLParameters() {
        return createSSLParameters(true);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:02:01.622 -0500", hash_original_method = "511D5113A1843E08B751FF4FDB4F02E4", hash_generated_method = "C3279A7AEB32CC8F8E8680CAC4936037")
    
private javax.net.ssl.SSLParameters createSSLParameters(boolean supported) {
        try {
            SSLSocket s = (SSLSocket) engineGetSocketFactory().createSocket();
            javax.net.ssl.SSLParameters p = new javax.net.ssl.SSLParameters();
            String[] cipherSuites;
            String[] protocols;
            if (supported) {
                cipherSuites = s.getSupportedCipherSuites();
                protocols = s.getSupportedProtocols();
            } else {
                cipherSuites = s.getEnabledCipherSuites();
                protocols = s.getEnabledProtocols();
            }
            p.setCipherSuites(cipherSuites);
            p.setProtocols(protocols);
            p.setNeedClientAuth(s.getNeedClientAuth());
            p.setWantClientAuth(s.getWantClientAuth());
            return p;
        } catch (IOException e) {
            /*
             * SSLContext.getDefaultSSLParameters specifies to throw
             * UnsupportedOperationException if there is a problem getting the
             * parameters
             */
            throw new UnsupportedOperationException("Could not access supported SSL parameters");
        }
    }
    
}

