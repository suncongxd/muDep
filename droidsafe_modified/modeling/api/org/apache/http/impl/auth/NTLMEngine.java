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
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/auth/NTLMEngine.java $
 * $Revision: 659788 $
 * $Date: 2008-05-24 03:42:23 -0700 (Sat, 24 May 2008) $
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


package org.apache.http.impl.auth;

/**
 * Abstract NTLM authentication engine. The engine can be used to
 * generate Type1 messages and Type3 messages in response to a 
 * Type2 challenge.
 * <p/>
 * For details see <a href="http://davenport.sourceforge.net/ntlm.html">this resource</a>
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
*/
import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
public interface NTLMEngine {

    /**
     * Generates a Type1 message given the domain and workstation.
     * 
     * @param domain Optional Windows domain name. Can be <code>null</code>.
     * @param workstation Optional Windows workstation name. Can be 
     *  <code>null</code>.
     * @return Type1 message
     * @throws NTLMEngineException
     */
    String generateType1Msg(
            String domain, 
            String workstation) throws NTLMEngineException;
    
    /**
     * Generates a Type3 message given the user credentials and the 
     * authentication challenge.
     *  
     * @param username Windows user name
     * @param password Password
     * @param domain Windows domain name
     * @param workstation Windows workstation name
     * @param challenge Type2 challenge.
     * @return Type3 response.
     * @throws NTLMEngineException
     */
    String generateType3Msg(
            String username,
            String password,
            String domain, 
            String workstation,
            String challenge) throws NTLMEngineException;
    
}
