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
 * Copyright (C) 2011 The Libphonenumber Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package com.android.i18n.phonenumbers.geocoding;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.SortedMap;
import java.util.TreeSet;

abstract class AreaCodeMapStorageStrategy {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.958 -0500", hash_original_field = "00F1381F548F3A5295097F2401ADFC52", hash_generated_field = "A8A65EF4F8315577299BC66787FEAF93")

  protected int numOfEntries = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.960 -0500", hash_original_field = "F27B9F27D291E9C70B7132D7F839782A", hash_generated_field = "117D2F6B6B0CD3E3A98E981A0FE51C20")

  protected final TreeSet<Integer> possibleLengths = new TreeSet<Integer>();

  @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.963 -0500", hash_original_method = "552FE922C6186E104B342E1C364AC348", hash_generated_method = "23406EDC9C84BF42534B33340C50DEFB")
    
public AreaCodeMapStorageStrategy() {}

  /**
   * Returns whether the underlying implementation of this abstract class is flyweight.
   * It is expected to be flyweight if it implements the {@code FlyweightMapStorage} class.
   *
   * @return  whether the underlying implementation of this abstract class is flyweight
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.965 -0500", hash_original_method = "8C3DF0E4670B1B720EA4BF046177B5B2", hash_generated_method = "CB033832448114A4BA252F7449E19AB9")
    
public abstract boolean isFlyweight();

  /**
   * @return  the number of entries contained in the area code map
   */
  @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.968 -0500", hash_original_method = "67028691C3A13A3B8C6995CB53BC17C2", hash_generated_method = "EFB1CF74F5BD00D02AAAED4029EEF8F9")
    
public int getNumOfEntries() {
    return numOfEntries;
  }

  /**
   * @return  the set containing the possible lengths of prefixes
   */
  @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.970 -0500", hash_original_method = "9B53A77A841456041603E3D119D30EB4", hash_generated_method = "E9E126B336B9EFA67D1946B657F3667B")
    
public TreeSet<Integer> getPossibleLengths() {
    return possibleLengths;
  }

  /**
   * Gets the phone number prefix located at the provided {@code index}.
   *
   * @param index  the index of the prefix that needs to be returned
   * @return  the phone number prefix at the provided index
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.973 -0500", hash_original_method = "CC816A42DE056FA45F4750DD0DF8C50A", hash_generated_method = "D5C1E20A41D3068C1E34614A380A7FF3")
    
public abstract int getPrefix(int index);

  /**
   * Gets the description corresponding to the phone number prefix located at the provided {@code
   * index}.
   *
   * @param index  the index of the phone number prefix that needs to be returned
   * @return  the description corresponding to the phone number prefix at the provided index
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.975 -0500", hash_original_method = "41C6F1984B61B370D6BEF5F1720FA001", hash_generated_method = "166E43DDF270AD32066C2CB4EFA87421")
    
public abstract String getDescription(int index);

  /**
   * Sets the internal state of the underlying storage implementation from the provided {@code
   * sortedAreaCodeMap} that maps phone number prefixes to description strings.
   *
   * @param sortedAreaCodeMap  a sorted map that maps phone number prefixes including country
   *    calling code to description strings
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.977 -0500", hash_original_method = "63AE65579CA13AF1E8A6BECB2C0ECB78", hash_generated_method = "5B489F00212DEF5896651E77C051F3BB")
    
public abstract void readFromSortedMap(SortedMap<Integer, String> sortedAreaCodeMap);

  /**
   * Sets the internal state of the underlying storage implementation reading the provided {@code
   * objectInput}.
   *
   * @param objectInput  the object input stream from which the area code map is read
   * @throws IOException  if an error occurred reading the provided input stream
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.979 -0500", hash_original_method = "6FE639BDF297291E4A948A266A0FB6FB", hash_generated_method = "7C97ACD2BCD1119C2251275D5760D253")
    
public abstract void readExternal(ObjectInput objectInput) throws IOException;

  /**
   * Writes the internal state of the underlying storage implementation to the provided {@code
   * objectOutput}.
   *
   * @param objectOutput  the object output stream to which the area code map is written
   * @throws IOException  if an error occurred writing to the provided output stream
   */
  @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.982 -0500", hash_original_method = "78E7DE830841F23017EDA8BF3FC41760", hash_generated_method = "B4A3118C40AE051E6D6353D22AA49DED")
    
public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

  @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:06.985 -0500", hash_original_method = "4D596518D97EDAAEB28E920BC89837B3", hash_generated_method = "8A1AAC2535CE3F088536231CA78CCA30")
    
@Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    int numOfEntries = getNumOfEntries();

    for (int i = 0; i < numOfEntries; i++) {
      output.append(getPrefix(i));
      output.append("|");
      output.append(getDescription(i));
      output.append("\n");
    }
    return output.toString();
  }
    
}

