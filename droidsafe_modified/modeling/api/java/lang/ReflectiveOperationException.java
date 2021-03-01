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
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.lang;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.lang.reflect.Array;

/**
 * Superclass of exceptions related to reflection.
 *
 * @since 1.7
 */
public class ReflectiveOperationException extends Exception {
  /**
   * Constructs a new exception with no detail message.
   */
  @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:33.710 -0400", hash_original_method = "4CFE655B9CD1600D2030A19B2AA7D435", hash_generated_method = "F0DCCB0FE5166555B044B4423F7B34E7")
    
public ReflectiveOperationException() {
  }

  /**
   * Constructs a new exception with the given detail message.
   */
  @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:33.710 -0400", hash_original_method = "DF2C39BEED6B604C45F0C32200E0C6A6", hash_generated_method = "74F4634D63FEE77A6C7C423A9EEA0729")
    
public ReflectiveOperationException(String message) {
    super(message);
  }

  /**
   * Constructs a new exception with the given cause.
   */
  @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:33.711 -0400", hash_original_method = "7009A6FE18DDB7DEEE74B6A284659EE0", hash_generated_method = "130D242C7828BA36B1B183EDE0AFFEF2")
    
public ReflectiveOperationException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new exception with the given detail message and cause.
   */
  @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:33.711 -0400", hash_original_method = "E1846D744F51FCC12E25E8D2169ECF44", hash_generated_method = "49BA0A14BCA5E88347A9522B6B7AC378")
    
public ReflectiveOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
