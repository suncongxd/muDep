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


package java.security.cert;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Set;

/**
 * The parameter specification for a PKIX {@code CertPathBuilder}
 * algorithm used to {@link CertPathBuilder#build(CertPathParameters) build}
 * certificate chains validated with the PKIX certification path validation.
 * <p>
 * The parameters must be created with <i>trusted</i> certificate authorities
 * and constraints for the target certificates.
 *
 * @see CertPathBuilder
 * @see CertPathParameters
 */
public class PKIXBuilderParameters extends PKIXParameters {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.521 -0400", hash_original_field = "6FAF64A3FB7E0BB9D2B30F00FE43FA79", hash_generated_field = "55D92C188924AA111FA0C3254A1E0F89")

    private int maxPathLength = 5;

    /**
     * Creates a new {@code PKIXBuilderParameters} instance with the specified
     * set of {@code TrustAnchor} and certificate constraints.
     *
     * @param trustAnchors
     *            the set of {@code TrustAnchors}.
     * @param targetConstraints
     *            the certificate constraints.
     * @throws InvalidAlgorithmParameterException
     *             if {@code trustAnchors} is empty.
     * @throws ClassCastException
     *             if one of the items in {@code trustAnchors} is not an
     *             instance of {@code java.security.cert.TrustAnchor}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.523 -0400", hash_original_method = "A634CDB48890C995340768AA6BF5BE26", hash_generated_method = "D430334D9B87003F25B7F6FD35568D51")
    
public PKIXBuilderParameters(Set<TrustAnchor> trustAnchors,
            CertSelector targetConstraints)
        throws InvalidAlgorithmParameterException {
        super(trustAnchors);
        super.setTargetCertConstraints(targetConstraints);
    }

    /**
     * Creates a new {@code PKIXBuilderParameters} instance with the trusted
     * {@code X509Certificate} entries from the specified {@code KeyStore}.
     *
     * @param keyStore
     *            the key store containing trusted certificates.
     * @param targetConstraints
     *            the certificate constraints.
     * @throws KeyStoreException
     *             if the {@code keyStore} is not initialized.
     * @throws InvalidAlgorithmParameterException
     *             if {@code keyStore} does not contained any trusted
     *             certificate entry.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.524 -0400", hash_original_method = "A3BCEA0FDE9AF1EDDBDA98BB95AC88F0", hash_generated_method = "58AE3A7DE265526C8AB14ED6BBFAACC7")
    
public PKIXBuilderParameters(KeyStore keyStore,
            CertSelector targetConstraints)
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        super(keyStore);
        super.setTargetCertConstraints(targetConstraints);
    }

    /**
     * Returns the maximum length of a certification path.
     * <p>
     * This is the maximum number of non-self-signed certificates in a
     * certification path.
     *
     * @return the maximum length of a certification path, or {@code -1} if it
     *         is unlimited.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.525 -0400", hash_original_method = "7706CCC594DE869377CE0DDEF504EBFB", hash_generated_method = "1D59B084D6D2B34EF5AAEB32EE5C524C")
    
public int getMaxPathLength() {
        return maxPathLength;
    }

    /**
     * Set the maximum length of a certification path.
     * <p>
     * This is the maximum number of non-self-signed certificates in a
     * certification path.
     *
     * @param maxPathLength
     *            the maximum length of a certification path.
     * @throws InvalidParameterException
     *             if {@code maxPathLength} is less than {@code -1}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.526 -0400", hash_original_method = "567ECEF62C1D6506AF05174D591CF33C", hash_generated_method = "67D96A22ED45A2B22E0C8A17D7DDBF26")
    
public void setMaxPathLength(int maxPathLength) {
        if (maxPathLength < -1) {
            throw new InvalidParameterException("maxPathLength < -1");
        }
        this.maxPathLength = maxPathLength;
    }

    /**
     * Returns a string representation of this {@code PKIXBuilderParameters}
     * instance.
     *
     * @return a string representation of this {@code PKIXBuilderParameters}
     *         instance.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.528 -0400", hash_original_method = "E701AF787F5B39B1A149F5C1C0147E38", hash_generated_method = "6643CAF824B6FA80E87CCE7B58B18A2A")
    
public String toString() {
        StringBuilder sb = new StringBuilder("[\n");
        sb.append(super.toString());
        sb.append(" Max Path Length: ");
        sb.append(maxPathLength);
        sb.append("\n]");
        return sb.toString();
    }
}
