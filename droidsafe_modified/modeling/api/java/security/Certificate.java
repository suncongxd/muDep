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


package java.security;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * {@code Certificate} represents an identity certificate, such as X.509 or PGP.
 * Note: A {@code Certificate} instances does not make any statement about the
 * validity of itself. It's in the responsibility of the application to verify
 * the validity of its certificates.
 *
 * @deprecated Replaced by behavior in {@link java.security.cert}
 * @see java.security.cert.Certificate
 */
@Deprecated
public interface Certificate {

    /**
     * Decodes a certificate from the given {@code InputStream}. The format of
     * the data to encode must be that identified by {@link #getFormat()} and
     * encoded by {@link #encode(OutputStream)}.
     *
     * @param stream
     *            the {@code InputStream} to read from.
     * @throws KeyException
     *             if certificate information is incomplete or incorrect.
     * @throws IOException
     *             if an exception is thrown by accessing the provided stream.
     * @see #encode(OutputStream)
     * @see #getFormat()
     */
    public void decode(InputStream stream) throws KeyException, IOException;

    /**
     * Encodes this certificate to an output stream. The
     * {@link #decode(InputStream)} method must be able to decode the format
     * written by this method.
     *
     * @param stream
     *            the {@code OutputStream} to encode this certificate to.
     * @throws KeyException
     *             if certificate information is incomplete or incorrect.
     * @throws IOException
     *             if an exception is thrown by accessing the provided stream.
     * @see #decode(InputStream)
     */
    public void encode(OutputStream stream) throws KeyException, IOException;

    /**
     * Returns a string identifying the format of this certificate.
     *
     * @return a string identifying the format of this certificate.
     */
    public String getFormat();

    /**
     * Returns the guarantor of this certificate. That guarantor guarantees,
     * that the public key of this certificate is from the principal returned by
     * {@link #getPrincipal()}.
     *
     * @return the guarantor of this certificate.
     * @see #getPrincipal()
     */
    public Principal getGuarantor();

    /**
     * Returns the principal of this certificate. The principal is guaranteed by
     * the guarantor returned by {@link #getGuarantor()}.
     *
     * @return the principal of this certificate.
     * @see #getGuarantor()
     */
    public Principal getPrincipal();

    /**
     * Returns the public key of this certificate. The public key is guaranteed
     * by the guarantor to belong to the principal.
     *
     * @return the public key of this certificate.
     * @see #getGuarantor()
     * @see Certificate#getPrincipal()
     */
    public PublicKey getPublicKey();

    /**
     * Returns a string containing a concise, human-readable description of the
     * this {@code Certificate}.
     *
     * @param detailed
     *            whether or not this method should return detailed information.
     * @return a string representation of this certificate.
     */
    public String toString(boolean detailed);
}
