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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/ssl/SSLSocketFactory.java $
 * $Revision: 659194 $
 * $Date: 2008-05-22 11:33:47 -0700 (Thu, 22 May 2008) $
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


package org.apache.http.conn.ssl;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class SSLSocketFactory implements LayeredSocketFactory {
    
    /**
     * Gets an singleton instance of the SSLProtocolSocketFactory.
     * @return a SSLProtocolSocketFactory
     */
    @DSComment("possible socket creation")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.632 -0500", hash_original_method = "6B121B894B8B46063AE1809ACAD0482F", hash_generated_method = "87AF58343C860BC81629A279DF58B94F")
    
public static SSLSocketFactory getSocketFactory() {
        return DEFAULT_FACTORY;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.661 -0500", hash_original_method = "EA988C7FB9376921E544F6B3583E6F19", hash_generated_method = "643339AE20E037E9B7782CFBA7C087CB")
    
private static KeyManager[] createKeyManagers(final KeyStore keystore, final String password)
        throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        if (keystore == null) {
            throw new IllegalArgumentException("Keystore may not be null");
        }
        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm());
        kmfactory.init(keystore, password != null ? password.toCharArray(): null);
        return kmfactory.getKeyManagers(); 
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.664 -0500", hash_original_method = "7F7E0A96CA61396C4827581CB75FE1E8", hash_generated_method = "825EB7420999C293441C199DE138D2D6")
    
private static TrustManager[] createTrustManagers(final KeyStore keystore)
        throws KeyStoreException, NoSuchAlgorithmException { 
        if (keystore == null) {
            throw new IllegalArgumentException("Keystore may not be null");
        }
        TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm());
        tmfactory.init(keystore);
        return tmfactory.getTrustManagers();
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.614 -0500", hash_original_field = "71F1B416844F8AD6CDB919A6F87FFF88", hash_generated_field = "CA5E4F0BFCB251BDC73BBB5281DA6EC7")

    public static final String TLS   = "TLS";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.616 -0500", hash_original_field = "124D4AF49728344A5035E3A8A654C08B", hash_generated_field = "86941BAF175786CD2D0CA4E940EC1D46")

    public static final String SSL   = "SSL";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.619 -0500", hash_original_field = "2E4DF696E54BBBEBCB4B8B4538E4459B", hash_generated_field = "1EF78CC576401D233FE0AF77A8567CD4")

    public static final String SSLV2 = "SSLv2";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.622 -0500", hash_original_field = "8C17E61C76FBAB027221525BBA2F085E", hash_generated_field = "D5F90D56F3DB6E488CA3C5CC0704B598")
    
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER 
        = new AllowAllHostnameVerifier();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.624 -0500", hash_original_field = "CC8C018C7AA7CCE05FA773E9C6818C7E", hash_generated_field = "5F3BDD7FFC06D2A92E3E1417CC23EFC0")
    
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER 
        = new BrowserCompatHostnameVerifier();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.627 -0500", hash_original_field = "F2925BB528BB48B6CDC5D00043F64F03", hash_generated_field = "11D7C2BC55F08EBD00B773626A86597A")
    
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER 
        = new StrictHostnameVerifier();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.630 -0500", hash_original_field = "534DE17C4745CBCA2C7DA051186E679D", hash_generated_field = "A610358EC0501A0752622D234730777E")

    private static final SSLSocketFactory DEFAULT_FACTORY = new SSLSocketFactory();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.635 -0500", hash_original_field = "AED75A470611C922665F1684CE9616BA", hash_generated_field = "99E7FAE9E3E14DF80520CEFA14EB241F")
    
    private  SSLContext sslcontext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.637 -0500", hash_original_field = "1EBE0F777ED2F311BDFF951F4973A92A", hash_generated_field = "E52538B4D22258905E6F27080C928A86")

    private  javax.net.ssl.SSLSocketFactory socketfactory;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.639 -0500", hash_original_field = "98B8869502CC5FF944B6B408CA137F17", hash_generated_field = "81E302E459F079F5FBE4C1ED4F82BD8C")

    private  HostNameResolver nameResolver;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.641 -0500", hash_original_field = "F179903F80FA9307038CF4DB34617917", hash_generated_field = "90112E8EDB7E7A821B80118DB10FBEFF")

    private X509HostnameVerifier hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.644 -0500", hash_original_method = "F80FA0F4889B4450DC2D852F3A3A58DF", hash_generated_method = "2FCEA0070360D64A18E82EE9DDF8BD51")
    
public SSLSocketFactory(
        String algorithm, 
        final KeyStore keystore, 
        final String keystorePassword, 
        final KeyStore truststore,
        final SecureRandom random,
        final HostNameResolver nameResolver) 
        throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        super();
        if (algorithm == null) {
            algorithm = TLS;
        }
        KeyManager[] keymanagers = null;
        if (keystore != null) {
            keymanagers = createKeyManagers(keystore, keystorePassword);
        }
        TrustManager[] trustmanagers = null;
        if (truststore != null) {
            trustmanagers = createTrustManagers(truststore);
        }
        this.sslcontext = SSLContext.getInstance(algorithm);
        this.sslcontext.init(keymanagers, trustmanagers, random);
        this.socketfactory = this.sslcontext.getSocketFactory();
        this.nameResolver = nameResolver;
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.647 -0500", hash_original_method = "56E365183E16E975E9BA33998913B83F", hash_generated_method = "95B8EF8382CC54005311C44D6340B270")
    
public SSLSocketFactory(
            final KeyStore keystore, 
            final String keystorePassword, 
            final KeyStore truststore) 
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this(TLS, keystore, keystorePassword, truststore, null, null);
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.649 -0500", hash_original_method = "A67823A5DC6C5FF7C43D604E05CE11CB", hash_generated_method = "36DEB92EBCED2815CFB7604925B388A9")
    
public SSLSocketFactory(final KeyStore keystore, final String keystorePassword) 
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this(TLS, keystore, keystorePassword, null, null, null);
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.652 -0500", hash_original_method = "28D877B99CD24F9AB6618A77DF1E85C8", hash_generated_method = "7C024ECAB8D81EDA96B924F92C38B4A2")
    
public SSLSocketFactory(final KeyStore truststore) 
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
        this(TLS, null, null, truststore, null, null);
    }

    /**
     * Constructs an HttpClient SSLSocketFactory backed by the given JSSE
     * SSLSocketFactory.
     *
     * @hide
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.656 -0500", hash_original_method = "607044CE64B6D03D5A8C48CB0A47A2B4", hash_generated_method = "7AA0CE63318E300FCF20DD78AB8B805B")
    
public SSLSocketFactory(javax.net.ssl.SSLSocketFactory socketfactory) {
        super();
        this.sslcontext = null;
        this.socketfactory = socketfactory;
        this.nameResolver = null;
    }

    /**
     * Creates the default SSL socket factory.
     * This constructor is used exclusively to instantiate the factory for
     * {@link #getSocketFactory getSocketFactory}.
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.659 -0500", hash_original_method = "B5B8605C888DE528BB32DDEA2B05EF81", hash_generated_method = "6683D6AE733EB1A46C4CCFA49E80FD03")
    
private SSLSocketFactory() {
        super();
        this.sslcontext = null;
        this.socketfactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        this.nameResolver = null;
    }

    // non-javadoc, see interface org.apache.http.conn.SocketFactory
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.666 -0500", hash_original_method = "63050927494CC5AD1D14809EA278FFBF", hash_generated_method = "2D16386E7F46613604C88B4691B040E5")
    
public Socket createSocket()
        throws IOException {

        // the cast makes sure that the factory is working as expected
        return (SSLSocket) this.socketfactory.createSocket();
    }

    // non-javadoc, see interface org.apache.http.conn.SocketFactory
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.669 -0500", hash_original_method = "1B91A377551E45AA28D03654EA268EA3", hash_generated_method = "6495BE9FBFE0542299E71FE520914380")
    
public Socket connectSocket(
        final Socket sock,
        final String host,
        final int port,
        final InetAddress localAddress,
        int localPort,
        final HttpParams params
    ) throws IOException {

        if (host == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        }
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null.");
        }

        SSLSocket sslsock = (SSLSocket)
            ((sock != null) ? sock : createSocket());

        if ((localAddress != null) || (localPort > 0)) {

            // we need to bind explicitly
            if (localPort < 0)
                localPort = 0; // indicates "any"

            InetSocketAddress isa =
                new InetSocketAddress(localAddress, localPort);
            sslsock.bind(isa);
        }

        int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
        int soTimeout = HttpConnectionParams.getSoTimeout(params);

        InetSocketAddress remoteAddress;
        if (this.nameResolver != null) {
            remoteAddress = new InetSocketAddress(this.nameResolver.resolve(host), port); 
        } else {
            remoteAddress = new InetSocketAddress(host, port);            
        }
        
        sslsock.connect(remoteAddress, connTimeout);

        sslsock.setSoTimeout(soTimeout);
        try {
            hostnameVerifier.verify(host, sslsock);
            // verifyHostName() didn't blowup - good!
        } catch (IOException iox) {
            // close the socket before re-throwing the exception
            try { sslsock.close(); } catch (Exception x) { /*ignore*/ }
            throw iox;
        }

        return sslsock;
    }

    /**
     * Checks whether a socket connection is secure.
     * This factory creates TLS/SSL socket connections
     * which, by default, are considered secure.
     * <br/>
     * Derived classes may override this method to perform
     * runtime checks, for example based on the cypher suite.
     *
     * @param sock      the connected socket
     *
     * @return  <code>true</code>
     *
     * @throws IllegalArgumentException if the argument is invalid
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.671 -0500", hash_original_method = "66B541002324CA4265A637C3F35D74A1", hash_generated_method = "EE32F948D2E48B6DA5AC4DA9B3B73969")
    
public boolean isSecure(Socket sock)
        throws IllegalArgumentException {

        if (sock == null) {
            throw new IllegalArgumentException("Socket may not be null.");
        }
        // This instanceof check is in line with createSocket() above.
        if (!(sock instanceof SSLSocket)) {
            throw new IllegalArgumentException
                ("Socket not created by this factory.");
        }
        // This check is performed last since it calls the argument object.
        if (sock.isClosed()) {
            throw new IllegalArgumentException("Socket is closed.");
        }

        return true;

    } // isSecure

    // non-javadoc, see interface LayeredSocketFactory
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.674 -0500", hash_original_method = "76BFB202D82A5741071D030FB94DC57A", hash_generated_method = "CAABFADD958083AD03DF766B6A0223C2")
    
public Socket createSocket(
        final Socket socket,
        final String host,
        final int port,
        final boolean autoClose
    ) throws IOException, UnknownHostException {
        SSLSocket sslSocket = (SSLSocket) this.socketfactory.createSocket(
              socket,
              host,
              port,
              autoClose
        );
        hostnameVerifier.verify(host, sslSocket);
        // verifyHostName() didn't blowup - good!
        return sslSocket;
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.676 -0500", hash_original_method = "CE9A92F10A15AE99F29896C9A2E4D993", hash_generated_method = "D72C1F86706343C941E0298CE4863A1F")
    
public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
        if ( hostnameVerifier == null ) {
            throw new IllegalArgumentException("Hostname verifier may not be null");
        }
        this.hostnameVerifier = hostnameVerifier;
    }

    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:27.678 -0500", hash_original_method = "E4B056341E706F6A6ED43C64885CAEDA", hash_generated_method = "E6917D33729ADF2866948C5F78D5FE60")
    
public X509HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }
}

