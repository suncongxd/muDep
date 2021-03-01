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
import java.security.PublicKey;

/**
 * The result of the PKIX certification path builder, returned by
 * {@link CertPathBuilder#build(CertPathParameters)}.
 */
public class PKIXCertPathBuilderResult extends PKIXCertPathValidatorResult
        implements CertPathBuilderResult {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.546 -0400", hash_original_field = "1102A718075F06795C918CF1E4D84F5F", hash_generated_field = "FF5ABA4D61B6F1CB5707CFE2F4CDD4AA")

    private  CertPath certPath;

    /**
     * Creates a new {@code PKIXCertPathBuilderResult} instance with the
     * specified validated certification path, the trust anchor of the
     * certification path, the policy tree and the public key of the subject.
     *
     * @param certPath
     *            the validated certification path.
     * @param trustAnchor
     *            the trust anchor.
     * @param policyTree
     *            the policy tree (or {@code null} if not used).
     * @param subjectPublicKey
     *            the public key.
     * @throws NullPointerException
     *             if the {@code cerPath}, {@code trustAnchor} or {@code
     *             subjectPolicyKey} is {@code null}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.548 -0400", hash_original_method = "0523DA20BE7A71AC473168A9790AB191", hash_generated_method = "6D94C5FBF11C6D0B6C6179D02770C46D")
    
public PKIXCertPathBuilderResult(CertPath certPath, TrustAnchor trustAnchor,
            PolicyNode policyTree, PublicKey subjectPublicKey) {
        super(trustAnchor, policyTree, subjectPublicKey);
        this.certPath = certPath;
        if (this.certPath == null) {
            throw new NullPointerException("certPath == null");
        }
    }

    /**
     * Returns the validated certification path.
     *
     * @return the validated certification path.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.549 -0400", hash_original_method = "35CFF0FD7CF6918FB79869750B5E218F", hash_generated_method = "04C7C373635014C9EBF0F9663C328964")
    
public CertPath getCertPath() {
        return certPath;
    }

    /**
     * Returns a string representation of this {@code PKIXCertPathBuilderResult}
     * instance.
     *
     * @return a string representation of this {@code PKIXCertPathBuilderResult}
     *         instance.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-08-13 13:14:15.551 -0400", hash_original_method = "A0C62BFA0E7CD9759D161D976CDDFB18", hash_generated_method = "8DB579BF292786F6108345D9CA430AE0")
    
public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("\n Certification Path: ");
        sb.append(certPath.toString());
        sb.append("\n]");
        return sb.toString();
    }
}
