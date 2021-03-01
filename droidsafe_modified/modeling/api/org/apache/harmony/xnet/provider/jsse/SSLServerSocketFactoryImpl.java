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


package org.apache.harmony.xnet.provider.jsse;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyManagementException;

import javax.net.ssl.SSLServerSocketFactory;

import libcore.util.EmptyArray;

public class SSLServerSocketFactoryImpl extends SSLServerSocketFactory {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.115 -0500", hash_original_field = "8EC89ADA2574F1DBC3DD4508F1BBC0BF", hash_generated_field = "D3BEC3280BC0116C55BDEA33933A255E")

    private SSLParametersImpl sslParameters;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.118 -0500", hash_original_field = "4B271BE77FE082BAEDB0CC3C06D67CEA", hash_generated_field = "2D5C9847E3AA9248107E9F6E4986B2D4")

    private IOException instantiationException;

    /**
     * Constructor.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.120 -0500", hash_original_method = "73CB7C407A43304FA150044B815B6A46", hash_generated_method = "11E99EF8CA100EA6392DE82C9F4963C2")
    
public SSLServerSocketFactoryImpl() {
        try {
            this.sslParameters = SSLParametersImpl.getDefault();
            this.sslParameters.setUseClientMode(false);
        } catch (KeyManagementException e) {
            instantiationException =
                new IOException("Delayed instantiation exception:");
            instantiationException.initCause(e);
        }
    }

    /**
     * Constructor.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.124 -0500", hash_original_method = "76241236FAFD17D5464286E69E45CFDE", hash_generated_method = "14B7C2A9002B0752102CEF7A43465266")
    
protected SSLServerSocketFactoryImpl(SSLParametersImpl sslParameters) {
        this.sslParameters = (SSLParametersImpl) sslParameters.clone();
        this.sslParameters.setUseClientMode(false);
    }

    /**
     * @see javax.net.ssl.SSLServerSocketFactory#getDefaultCipherSuites()
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.126 -0500", hash_original_method = "D2E4703F25DE73045253D43D661F8658", hash_generated_method = "F28A7F75D17BAB5AF6CF7A48106E8A68")
    
@Override
    public String[] getDefaultCipherSuites() {
        if (instantiationException != null) {
            return EmptyArray.STRING;
        }
        return sslParameters.getEnabledCipherSuites();
    }

    /**
     * @see javax.net.ssl.SSLServerSocketFactory#getSupportedCipherSuites()
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.129 -0500", hash_original_method = "107E6937D2B525F81A260013B97CF7D4", hash_generated_method = "ADFA088456815838E904E8BFB04AE9AA")
    
@Override
    public String[] getSupportedCipherSuites() {
        if (instantiationException != null) {
            return EmptyArray.STRING;
        }
        return CipherSuite.getSupportedCipherSuiteNames();
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket()
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.131 -0500", hash_original_method = "8CBCDC02B624BDC3EBB9915D8C4D504E", hash_generated_method = "F55D08272B88D2CA77E8204C7878E00B")
    
@Override
    public ServerSocket createServerSocket() throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl((SSLParametersImpl) sslParameters.clone());
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.134 -0500", hash_original_method = "38B1F9FB8A801D54D6140D98F9574C0C", hash_generated_method = "3307EDE13B8D11B7CDAED7AF5366F1D8")
    
@Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port,
                (SSLParametersImpl) sslParameters.clone());
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int,int)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.136 -0500", hash_original_method = "DB92DF56A4C60994E7A02C3BBC7271E4", hash_generated_method = "8B925420487D6D89E0A21700B7096A4E")
    
@Override
    public ServerSocket createServerSocket(int port, int backlog)
            throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port, backlog,
                (SSLParametersImpl) sslParameters.clone());
    }

    /**
     * @see javax.net.ServerSocketFactory#createServerSocket(int,int,InetAddress)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:19.138 -0500", hash_original_method = "05EB93BEC7CA6CC8959662993B7E734B", hash_generated_method = "2844F0E18AB186ECF05D709528A38414")
    
@Override
    public ServerSocket createServerSocket(int port, int backlog,
            InetAddress iAddress) throws IOException {
        if (instantiationException != null) {
            throw instantiationException;
        }
        return new SSLServerSocketImpl(port, backlog, iAddress,
                (SSLParametersImpl) sslParameters.clone());
    }
    
}

