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


package java.text;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.Comparator;
import java.util.Locale;

import libcore.icu.ICU;
import libcore.icu.RuleBasedCollatorICU;

public abstract class Collator implements Comparator<Object>, Cloneable {

    /**
     * Returns an array of locales for which custom {@code Collator} instances
     * are available.
     * <p>Note that Android does not support user-supplied locale service providers.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.156 -0500", hash_original_method = "079D2BDCF2543C9EF39D2D400341A9D8", hash_generated_method = "746A813ED9B4684977432502F2AA3677")
    
public static Locale[] getAvailableLocales() {
        return ICU.getAvailableCollatorLocales();
    }

    /**
     * Returns a {@code Collator} instance which is appropriate for the user's default
     * {@code Locale}.
     * See "<a href="../util/Locale.html#default_locale">Be wary of the default locale</a>".
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.163 -0500", hash_original_method = "36423D53959653C2984C84B905F35ABE", hash_generated_method = "5922C2B7903C130A111FFD3F547D8C2C")
    
public static Collator getInstance() {
        return getInstance(Locale.getDefault());
    }

    /**
     * Returns a {@code Collator} instance which is appropriate for {@code locale}.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.166 -0500", hash_original_method = "FDDB3D7E9466CD25FA15155141F4B994", hash_generated_method = "8A20382FC64DCF44DF83452E25D2BB49")
    
public static Collator getInstance(Locale locale) {
        if (locale == null) {
            throw new NullPointerException();
        }
        return new RuleBasedCollator(new RuleBasedCollatorICU(locale));
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.115 -0500", hash_original_field = "93329CB9D361319AFB21377676C97BF2", hash_generated_field = "9E43ED873605C1C1034619325D9DA889")

    public static final int NO_DECOMPOSITION = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.118 -0500", hash_original_field = "6DAC4EBCDEAC47E3A4AD91769058A94C", hash_generated_field = "CD40FC2067D59891F7BA4573BEF1DB6B")

    public static final int CANONICAL_DECOMPOSITION = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.121 -0500", hash_original_field = "CD82542B7C4968D170F6CEFF73179A09", hash_generated_field = "792407D4F0E9907685F71381C9FD4E61")

    public static final int FULL_DECOMPOSITION = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.124 -0500", hash_original_field = "10400586614D213A01A787357FB34D0C", hash_generated_field = "B6442BE7EB0FE61CE720EB713BF63684")

    public static final int PRIMARY = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.126 -0500", hash_original_field = "CD952DDD756F0240AD1202585D05941D", hash_generated_field = "545302936326A1D8CF924AC93757F924")

    public static final int SECONDARY = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.129 -0500", hash_original_field = "FF76A48A3A8382D0F892799DE2ED42A2", hash_generated_field = "CA7F342F2246C9B80B01671D88E6D41C")

    public static final int TERTIARY = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.131 -0500", hash_original_field = "46A5A37AECF3DF53FAF7C778E512FD94", hash_generated_field = "19D42BD9A98581EBEB400999C2E7C1B9")

    public static final int IDENTICAL = 3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.133 -0500", hash_original_field = "571EB1F8C774D603CEDE0C031FD66C11", hash_generated_field = "571EB1F8C774D603CEDE0C031FD66C11")

    RuleBasedCollatorICU icuColl;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.137 -0500", hash_original_method = "18D47750B740CE28B17D63629E2E6276", hash_generated_method = "18D47750B740CE28B17D63629E2E6276")
    
Collator(RuleBasedCollatorICU icuColl) {
        this.icuColl = icuColl;
    }

    /**
     * Constructs a new {@code Collator} instance.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.140 -0500", hash_original_method = "D1E80A5B5BD1C7C64C7B560256B076FF", hash_generated_method = "542514D3955BA44323F328F19C1378D5")
    
protected Collator() {
        icuColl = new RuleBasedCollatorICU(Locale.getDefault());
    }

    /**
     * Returns a new collator with the same decomposition mode and
     * strength value as this collator.
     *
     * @return a shallow copy of this collator.
     * @see java.lang.Cloneable
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.142 -0500", hash_original_method = "2284DB2FEC4C2AB3A601FDA70F532028", hash_generated_method = "21F87DF2591203FC0F1B2A488AAA8A15")
    
@Override
    public Object clone() {
        try {
            Collator clone = (Collator) super.clone();
            clone.icuColl = (RuleBasedCollatorICU) icuColl.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Compares two objects to determine their relative order. The objects must
     * be strings.
     *
     * @param object1
     *            the first string to compare.
     * @param object2
     *            the second string to compare.
     * @return a negative value if {@code object1} is less than {@code object2},
     *         0 if they are equal, and a positive value if {@code object1} is
     *         greater than {@code object2}.
     * @throws ClassCastException
     *         if {@code object1} or {@code object2} is not a {@code String}.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.146 -0500", hash_original_method = "7C02E06D11E019F44D80294CC033745E", hash_generated_method = "F85FDF97A13D35C0B3110E84B80DDE74")
    
public int compare(Object object1, Object object2) {
        return compare((String) object1, (String) object2);
    }

    /**
     * Compares two strings to determine their relative order.
     *
     * @param string1
     *            the first string to compare.
     * @param string2
     *            the second string to compare.
     * @return a negative value if {@code string1} is less than {@code string2},
     *         0 if they are equal and a positive value if {@code string1} is
     *         greater than {@code string2}.
     */
    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.148 -0500", hash_original_method = "9DEFA3A080C5B36241D14E3A04AEFBBF", hash_generated_method = "260149D2BD45EA572C2F49EEC2BD0711")
    
public abstract int compare(String string1, String string2);

    /**
     * Compares this collator with the specified object and indicates if they
     * are equal.
     *
     * @param object
     *            the object to compare with this object.
     * @return {@code true} if {@code object} is a {@code Collator} object and
     *         it has the same strength and decomposition values as this
     *         collator; {@code false} otherwise.
     * @see #hashCode
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.151 -0500", hash_original_method = "F5E912698B2C6614F01702D72214A0C5", hash_generated_method = "E19FDC12CACA1655EB3FF687EBB0791B")
    
@Override
    public boolean equals(Object object) {
        if (!(object instanceof Collator)) {
            return false;
        }
        Collator collator = (Collator) object;
        return icuColl == null ? collator.icuColl == null : icuColl.equals(collator.icuColl);
    }

    /**
     * Compares two strings using the collation rules to determine if they are
     * equal.
     *
     * @param string1
     *            the first string to compare.
     * @param string2
     *            the second string to compare.
     * @return {@code true} if {@code string1} and {@code string2} are equal
     *         using the collation rules, false otherwise.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.153 -0500", hash_original_method = "9F5D52877C91EB2005E3609373B16781", hash_generated_method = "1FFC8442EA80E70A4A0F5B8A9E58C86E")
    
public boolean equals(String string1, String string2) {
        return compare(string1, string2) == 0;
    }

    /**
     * Returns a {@link CollationKey} for the specified string for this collator
     * with the current decomposition rule and strength value.
     *
     * @param string
     *            the source string that is converted into a collation key.
     * @return the collation key for {@code string}.
     */
    @DSComment("Abstract Method")
    @DSSpec(DSCat.ABSTRACT_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.158 -0500", hash_original_method = "C0C15CDE83796D9CCD9CA148FDE67C91", hash_generated_method = "F440130EEB37B0341262C8EB9EF14EDE")
    
public abstract CollationKey getCollationKey(String string);

    /**
     * Returns the decomposition rule for this collator.
     *
     * @return the decomposition rule, either {@code NO_DECOMPOSITION} or
     *         {@code CANONICAL_DECOMPOSITION}. {@code FULL_DECOMPOSITION} is
     *         not supported.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.161 -0500", hash_original_method = "80384F871328DEB63A40E323709EFE86", hash_generated_method = "5EEB3791091FB7072EEC245609772E7A")
    
public int getDecomposition() {
        return decompositionMode_ICU_Java(icuColl.getDecomposition());
    }

    /**
     * Returns the strength value for this collator.
     *
     * @return the strength value, either PRIMARY, SECONDARY, TERTIARY or
     *         IDENTICAL.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.169 -0500", hash_original_method = "1088192DB9E30CE77C93098214CBF249", hash_generated_method = "24992D02A8378C893C0606A884EC9451")
    
public int getStrength() {
        return strength_ICU_Java(icuColl.getStrength());
    }

    @DSComment("Abstract Method")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.171 -0500", hash_original_method = "55C270AB7FB96DF5C59CA61A88D62880", hash_generated_method = "DC0B80FFE6A69EF8A10E204DA3FCAB54")
    
@Override
    public abstract int hashCode();

    /**
     * Sets the decomposition rule for this collator.
     *
     * @param value
     *            the decomposition rule, either {@code NO_DECOMPOSITION} or
     *            {@code CANONICAL_DECOMPOSITION}. {@code FULL_DECOMPOSITION}
     *            is not supported.
     * @throws IllegalArgumentException
     *            if the provided decomposition rule is not valid. This includes
     *            {@code FULL_DECOMPOSITION}.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.174 -0500", hash_original_method = "B72BC12BA62E721E78973B8AD1CD75B5", hash_generated_method = "F1C2AFE44575D612EDDFFF43B6540109")
    
public void setDecomposition(int value) {
        icuColl.setDecomposition(decompositionMode_Java_ICU(value));
    }

    /**
     * Sets the strength value for this collator.
     *
     * @param value
     *            the strength value, either PRIMARY, SECONDARY, TERTIARY, or
     *            IDENTICAL.
     * @throws IllegalArgumentException
     *            if the provided strength value is not valid.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.176 -0500", hash_original_method = "EE6B9836B69B72CAEC3F00255CD1C53E", hash_generated_method = "70C8E85EC36DDE7A493D9EF302947660")
    
public void setStrength(int value) {
        icuColl.setStrength(strength_Java_ICU(value));
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.178 -0500", hash_original_method = "59C448B2403440A52B430BC264B57CED", hash_generated_method = "577F370F1D4A66A2107149B322EF3C48")
    
private int decompositionMode_Java_ICU(int mode) {
        switch (mode) {
        case Collator.CANONICAL_DECOMPOSITION:
            return RuleBasedCollatorICU.VALUE_ON;
        case Collator.NO_DECOMPOSITION:
            return RuleBasedCollatorICU.VALUE_OFF;
        }
        throw new IllegalArgumentException();
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.182 -0500", hash_original_method = "221AF1495063A31B1C807B8FB0041DE6", hash_generated_method = "56E18FB8B2BA73FC2D26CDDE44BD3710")
    
private int decompositionMode_ICU_Java(int mode) {
        int javaMode = mode;
        switch (mode) {
        case RuleBasedCollatorICU.VALUE_OFF:
            javaMode = Collator.NO_DECOMPOSITION;
            break;
        case RuleBasedCollatorICU.VALUE_ON:
            javaMode = Collator.CANONICAL_DECOMPOSITION;
            break;
        }
        return javaMode;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.184 -0500", hash_original_method = "8D5EE21418DD2AA072EEA3359693FB17", hash_generated_method = "09A2E0392960F89DDB5B919F89124B94")
    
private int strength_Java_ICU(int value) {
        switch (value) {
        case Collator.PRIMARY:
            return RuleBasedCollatorICU.VALUE_PRIMARY;
        case Collator.SECONDARY:
            return RuleBasedCollatorICU.VALUE_SECONDARY;
        case Collator.TERTIARY:
            return RuleBasedCollatorICU.VALUE_TERTIARY;
        case Collator.IDENTICAL:
            return RuleBasedCollatorICU.VALUE_IDENTICAL;
        }
        throw new IllegalArgumentException();
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:56:02.187 -0500", hash_original_method = "28CE8F975C7774291F421ACF5AFE6047", hash_generated_method = "C222350EAC3FDFAB68521A62D98E2944")
    
private int strength_ICU_Java(int value) {
        int javaValue = value;
        switch (value) {
        case RuleBasedCollatorICU.VALUE_PRIMARY:
            javaValue = Collator.PRIMARY;
            break;
        case RuleBasedCollatorICU.VALUE_SECONDARY:
            javaValue = Collator.SECONDARY;
            break;
        case RuleBasedCollatorICU.VALUE_TERTIARY:
            javaValue = Collator.TERTIARY;
            break;
        case RuleBasedCollatorICU.VALUE_IDENTICAL:
            javaValue = Collator.IDENTICAL;
            break;
        }
        return javaValue;
    }
}

