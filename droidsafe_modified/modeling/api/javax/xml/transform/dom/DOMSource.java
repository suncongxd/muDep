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
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: DOMSource.java 446598 2006-09-15 12:55:40Z jeremias $


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package javax.xml.transform.dom;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import javax.xml.transform.Source;

import org.w3c.dom.Node;

public class DOMSource implements Source {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.301 -0500", hash_original_field = "854AAE683B90ED7486002A8649A4844B", hash_generated_field = "544E5A424F01A7FBB778D82E3C1AE2BE")

    public static final String FEATURE =
        "http://javax.xml.transform.dom.DOMSource/feature";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.296 -0500", hash_original_field = "D4FCE8012659E56145F1753A3BD8D56E", hash_generated_field = "FF348B25B2CE5FD5278D4CA5F8E8C280")

    private Node node;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.298 -0500", hash_original_field = "0EFEFA79FB9C455CF108F420D8462143", hash_generated_field = "83BD3571475BC4C0C301203EC49F6EAB")

    private String systemID;

    /**
     * <p>Zero-argument default constructor.  If this constructor is used, and
     * no DOM source is set using {@link #setNode(Node node)} , then the
     * <code>Transformer</code> will
     * create an empty source {@link org.w3c.dom.Document} using
     * {@link javax.xml.parsers.DocumentBuilder#newDocument()}.</p>
     *
     * @see javax.xml.transform.Transformer#transform(Source xmlSource, Result outputTarget)
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.303 -0500", hash_original_method = "C22C4B52562F798E2DC7E2B5F763062E", hash_generated_method = "5A01E933F6E8AF5A24CC3F6F9F33278E")
    
public DOMSource() { }

    /**
     * Create a new input source with a DOM node.  The operation
     * will be applied to the subtree rooted at this node.  In XSLT,
     * a "/" pattern still means the root of the tree (not the subtree),
     * and the evaluation of global variables and parameters is done
     * from the root node also.
     *
     * @param n The DOM node that will contain the Source tree.
     */
    @DSComment("no suspicious activity, only creates object")
    @DSSafe(DSCat.DATA_STRUCTURE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.305 -0500", hash_original_method = "7C7F7287E74EAB9B8232F0908501FA89", hash_generated_method = "F7CF3F71550B5F629D892F23E6C2C4EC")
    
public DOMSource(Node n) {
        setNode(n);
    }

    /**
     * Create a new input source with a DOM node, and with the
     * system ID also passed in as the base URI.
     *
     * @param node The DOM node that will contain the Source tree.
     * @param systemID Specifies the base URI associated with node.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.308 -0500", hash_original_method = "27D1704B3C4A5B83EAF9B6B6BE5CC0DE", hash_generated_method = "30910A96A3F18BC661914F3F322BA981")
    
public DOMSource(Node node, String systemID) {
        setNode(node);
        setSystemId(systemID);
    }

    /**
     * Set the node that will represents a Source DOM tree.
     *
     * @param node The node that is to be transformed.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.310 -0500", hash_original_method = "4F37876713B3FCA5FC091F96D4BE4E11", hash_generated_method = "01DCCA1E36C014E8C565743AA6EE0B46")
    
public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Get the node that represents a Source DOM tree.
     *
     * @return The node that is to be transformed.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.312 -0500", hash_original_method = "10E144C61713FDC7DCDB7ABF8ED1C0C7", hash_generated_method = "1A8D6F3F45A7778A1B2DD3B2B8BDF446")
    
public Node getNode() {
        return node;
    }

    /**
     * Set the base ID (URL or system ID) from where URLs
     * will be resolved.
     *
     * @param systemID Base URL for this DOM tree.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.314 -0500", hash_original_method = "E981DAB2BFA2B95115ED74FBC4FCBCC2", hash_generated_method = "00AAF2562F56BDD5F519F377D4C978BF")
    
public void setSystemId(String systemID) {
        this.systemID = systemID;
    }

    /**
     * Get the base ID (URL or system ID) from where URLs
     * will be resolved.
     *
     * @return Base URL for this DOM tree.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:59.317 -0500", hash_original_method = "2FF877EC5EDDB8E93544988C2CFEB0F3", hash_generated_method = "B0E248D5BB57924E80D7C544E8D5DDF5")
    
public String getSystemId() {
        return this.systemID;
    }
}

