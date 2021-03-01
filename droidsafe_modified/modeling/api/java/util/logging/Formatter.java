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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.util.logging;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public abstract class Formatter {

    /**
     * Constructs a {@code Formatter} object.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:36.818 -0500", hash_original_method = "3D350E85A089AFD791F4A318777DFCA3", hash_generated_method = "B391543BD46E60AF59B6CE3503AD0B73")
    
protected Formatter() {
    }

    /**
     * Converts a {@link LogRecord} object into a string representation. The
     * resulted string is usually localized and includes the message field of
     * the record.
     *
     * @param r
     *            the log record to be formatted into a string.
     * @return the formatted string.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:36.821 -0500", hash_original_method = "7E168986BF1FADDA4930F992D3D84353", hash_generated_method = "E6DE8F47A6778B61C83B2AA82D9DF8FB")
    
public abstract String format(LogRecord r);

    /**
     * Formats a {@code LogRecord} object into a localized string
     * representation. This is a convenience method for subclasses of {@code
     * Formatter}.
     * <p>
     * The message string is firstly localized using the {@code ResourceBundle}
     * object associated with the supplied {@code LogRecord}.
     * <p>
     * Notice : if message contains "{0", then java.text.MessageFormat is used.
     * Otherwise no formatting is performed.
     *
     * @param r
     *            the log record to be formatted.
     * @return the string resulted from the formatting.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:36.824 -0500", hash_original_method = "C04F7375B92ACCB2488DC0EF0253C5B2", hash_generated_method = "2477C53B755CB69B3C21EA08E87BCC0E")
    
public String formatMessage(LogRecord r) {
        String pattern = r.getMessage();
        ResourceBundle rb = null;
        // try to localize the message string first
        if ((rb = r.getResourceBundle()) != null) {
            try {
                pattern = rb.getString(pattern);
            } catch (Exception e) {
                pattern = r.getMessage();
            }
        }
        if (pattern != null) {
            Object[] params = r.getParameters();
            /*
             * if the message contains "{0", use java.text.MessageFormat to
             * format the string
             */
            if (pattern.indexOf("{0") >= 0 && params != null && params.length > 0) {
                try {
                    pattern = MessageFormat.format(pattern, params);
                } catch (IllegalArgumentException e) {
                    pattern = r.getMessage();
                }
            }
        }
        return pattern;
    }

    /**
     * Gets the head string used to wrap a set of log records. This base class
     * always returns an empty string.
     *
     * @param h
     *            the target handler.
     * @return the head string used to wrap a set of log records, empty in this
     *         implementation.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:36.826 -0500", hash_original_method = "07107B089EAE5612AD155B7D9F923109", hash_generated_method = "101AC05BF274870B943BA199D7465EAE")
    
public String getHead(Handler h) {
        return "";
    }

    /**
     * Gets the tail string used to wrap a set of log records. This base class
     * always returns the empty string.
     *
     * @param h
     *            the target handler.
     * @return the tail string used to wrap a set of log records, empty in this
     *         implementation.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:36.828 -0500", hash_original_method = "C189C1A511189A9AF799AA8C081148A1", hash_generated_method = "98F3E6FA5F9C1CD9F3A0ED244AD38C4E")
    
public String getTail(Handler h) {
        return "";
    }
    
}

