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
 * Copyright (C) 2006 The Android Open Source Project
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


package android.text;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.lang.reflect.Array;

import com.android.internal.util.ArrayUtils;

abstract class SpannableStringInternal {

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.111 -0500", hash_original_method = "6B7A1DDF85ADC183B3426DC1B4007521", hash_generated_method = "47901FCF4C66D2137EBF33493DFF4299")
    
private static String region(int start, int end) {
        return "(" + start + " ... " + end + ")";
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.127 -0500", hash_original_field = "41605FD9568522F1EC6EA33C9F0B24F1", hash_generated_field = "1908F7E294A3EABBA82B230EE13B1975")
 static final Object[] EMPTY = new Object[0];
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.130 -0500", hash_original_field = "27A41ADDFF59BC8B58A601456818918E", hash_generated_field = "D858CF53160507E553F41D31EE5B91B4")

    private static final int START = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.133 -0500", hash_original_field = "540ED2104944C8092BBE8BC3BA882B55", hash_generated_field = "8419802F1C9707D8A0E8B46456CF017D")

    private static final int END = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.135 -0500", hash_original_field = "A4C02BDB3C3630DA8A27BB30DD0DECA5", hash_generated_field = "AE684057078F2726E006BED2F46F1E81")

    private static final int FLAGS = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.138 -0500", hash_original_field = "E0E42F440C2428C6B6D89F8A4DE3669C", hash_generated_field = "A566B744E87F20E42BDCFABE418BC571")

    private static final int COLUMNS = 3;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.117 -0500", hash_original_field = "5C1E1D7AA24FFCA320ADE83B66FB0AC4", hash_generated_field = "2EBA23F479A3FA4159B4CC40D57E4F25")

    private String mText;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.119 -0500", hash_original_field = "CEB48CD34EDA945F7544A85E8671F5CB", hash_generated_field = "64DC9467A0917D43133A286556BA813F")

    private Object[] mSpans;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.122 -0500", hash_original_field = "E8B0D3DACF2BC5E083F116FB79140AF0", hash_generated_field = "938E92621B3F4547B2C3C20B887698D5")

    private int[] mSpanData;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.124 -0500", hash_original_field = "72EED4C1552152EAD8F6596F5703265D", hash_generated_field = "BDC8CC3C9B276345952883B6B6FC3B62")

    private int mSpanCount;
    /* package */ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.066 -0500", hash_original_method = "6B8C85BD24C5FB6AAF3EB88754652005", hash_generated_method = "6B8C85BD24C5FB6AAF3EB88754652005")
    
SpannableStringInternal(CharSequence source,
                                          int start, int end) {
        
        mText = new String();
        mText.addTaint(source.getTaint());
        mText.addTaint(start + end);
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.069 -0500", hash_original_method = "9A68249D01A4FE1CBD4D535668FCEB6F", hash_generated_method = "65B805BA188437A52E59B7D842E00788")
    
public final int length() {
        return mText.length();
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.072 -0500", hash_original_method = "9584624FC2131094C464C9F194744C46", hash_generated_method = "F12D087B8C9FA86ECE21342650B5C978")
    
public final char charAt(int i) {
        return mText.charAt(i);
    }
    
	@DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    public final String toString() {
        return new String(mText);
    }

    /* subclasses must do subSequence() to preserve type */

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.078 -0500", hash_original_method = "E3364302A15643D598F9B7C454DC0AA8", hash_generated_method = "E32091FBB2919720EBC07F30BA91042C")
    
public final void getChars(int start, int end, char[] dest, int off) {
        mText.getChars(start, end, dest, off);
    }

    /* package */ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.082 -0500", hash_original_method = "7B198AB61B6B726FF2E2996AF3FF95CD", hash_generated_method = "7B198AB61B6B726FF2E2996AF3FF95CD")
    
void setSpan(Object what, int start, int end, int flags) {
        mText.addTaint(what.getTaintInt() + end + flags);
        /*int nstart = start;
        int nend = end;

        checkRange("setSpan", start, end);

        if ((flags & Spannable.SPAN_PARAGRAPH) == Spannable.SPAN_PARAGRAPH) {
            if (start != 0 && start != length()) {
                char c = charAt(start - 1);

                if (c != '\n')
                    throw new RuntimeException(
                            "PARAGRAPH span must start at paragraph boundary" +
                            " (" + start + " follows " + c + ")");
            }

            if (end != 0 && end != length()) {
                char c = charAt(end - 1);

                if (c != '\n')
                    throw new RuntimeException(
                            "PARAGRAPH span must end at paragraph boundary" +
                            " (" + end + " follows " + c + ")");
            }
        }

        int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        for (int i = 0; i < count; i++) {
            if (spans[i] == what) {
                int ostart = data[i * COLUMNS + START];
                int oend = data[i * COLUMNS + END];

                data[i * COLUMNS + START] = start;
                data[i * COLUMNS + END] = end;
                data[i * COLUMNS + FLAGS] = flags;

                sendSpanChanged(what, ostart, oend, nstart, nend);
                return;
            }
        }

        if (mSpanCount + 1 >= mSpans.length) {
            int newsize = ArrayUtils.idealIntArraySize(mSpanCount + 1);
            Object[] newtags = new Object[newsize];
            int[] newdata = new int[newsize * 3];

            System.arraycopy(mSpans, 0, newtags, 0, mSpanCount);
            System.arraycopy(mSpanData, 0, newdata, 0, mSpanCount * 3);

            mSpans = newtags;
            mSpanData = newdata;
        }

        mSpans[mSpanCount] = what;
        mSpanData[mSpanCount * COLUMNS + START] = start;
        mSpanData[mSpanCount * COLUMNS + END] = end;
        mSpanData[mSpanCount * COLUMNS + FLAGS] = flags;
        mSpanCount++;

        if (this instanceof Spannable)
            sendSpanAdded(what, nstart, nend);*/
    }

    /* package */ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.085 -0500", hash_original_method = "03C5B870069E116AF35881F1682BF48B", hash_generated_method = "03C5B870069E116AF35881F1682BF48B")
    
void removeSpan(Object what) {
       /* int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        for (int i = count - 1; i >= 0; i--) {
            if (spans[i] == what) {
                int ostart = data[i * COLUMNS + START];
                int oend = data[i * COLUMNS + END];

                int c = count - (i + 1);

                System.arraycopy(spans, i + 1, spans, i, c);
                System.arraycopy(data, (i + 1) * COLUMNS,
                                 data, i * COLUMNS, c * COLUMNS);

                mSpanCount--;

                sendSpanRemoved(what, ostart, oend);
                return;
            }
        }*/
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.088 -0500", hash_original_method = "2C34546685C4DD853C66A09CEFBD0C65", hash_generated_method = "49A5A83F9D601DCC21A804366460C069")
    
public int getSpanStart(Object what) {
      /*  int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        for (int i = count - 1; i >= 0; i--) {
            if (spans[i] == what) {
                return data[i * COLUMNS + START];
            }
        }

        return -1;*/
        return (mText.getTaintInt() + what.getTaintInt());
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.091 -0500", hash_original_method = "D299E1841C6A6AC66FA9181AA7F112ED", hash_generated_method = "5F80416C7EB9B0125A02073DF204AAD9")
    
public int getSpanEnd(Object what) {
   /*     int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        for (int i = count - 1; i >= 0; i--) {
            if (spans[i] == what) {
                return data[i * COLUMNS + END];
            }
        }

        return -1;*/

        return (mText.getTaintInt() + what.getTaintInt());
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.093 -0500", hash_original_method = "0BC2A6452DDDFB33D1E74093A43A22EC", hash_generated_method = "6EB8AB2A3F9A018CE5A37968A4FC11D4")
    
public int getSpanFlags(Object what) {
/*        int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        for (int i = count - 1; i >= 0; i--) {
            if (spans[i] == what) {
                return data[i * COLUMNS + FLAGS];
            }
        }

        return 0;*/ 
        return (mText.getTaintInt() + what.getTaintInt());
    }
    
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-18 10:21:37.792 -0400", hash_original_method = "A79E5C3938B902091EA5DB757C342E2D", hash_generated_method = "8B11A1D83B40723E850120BF22A1B3F9")
    public <T> T[] getSpans(int queryStart, int queryEnd, Class<T> kind) {
        
        
        Object[] spans = new Object[1];
        spans[0].addTaint(queryStart + queryEnd + kind.getTaintInt() + mText.getTaintInt());
        
        return (T[])spans;
        
       /* addTaint(kind.getTaint());
        addTaint(queryEnd);
        addTaint(queryStart);
        int count = 0;
        int spanCount = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;
        Object[] ret = null;
        Object ret1 = null;
for(int i = 0;i < spanCount;i++)
        {
            if(kind != null && !kind.isInstance(spans[i]))            
            {
                continue;
            } //End block
            int spanStart = data[i * COLUMNS + START];
            int spanEnd = data[i * COLUMNS + END];
            if(spanStart > queryEnd)            
            {
                continue;
            } //End block
            if(spanEnd < queryStart)            
            {
                continue;
            } //End block
            if(spanStart != spanEnd && queryStart != queryEnd)            
            {
                if(spanStart == queryEnd)                
                {
                    continue;
                } //End block
                if(spanEnd == queryStart)                
                {
                    continue;
                } //End block
            } //End block
            if(count == 0)            
            {
                ret1 = spans[i];
                count++;
            } //End block
            else
            {
                if(count == 1)                
                {
                    ret = (Object[]) Array.newInstance(kind, spanCount - i + 1);
                    ret[0] = ret1;
                } //End block
                int prio = data[i * COLUMNS + FLAGS] & Spanned.SPAN_PRIORITY;
                if(prio != 0)                
                {
                    int j;
for(j = 0;j < count;j++)
                    {
                        int p = getSpanFlags(ret[j]) & Spanned.SPAN_PRIORITY;
                        if(prio > p)                        
                        {
                            break;
                        } //End block
                    } //End block
                    System.arraycopy(ret, j, ret, j + 1, count - j);
                    ret[j] = spans[i];
                    count++;
                } //End block
                else
                {
                    ret[count++] = spans[i];
                } //End block
            } //End block
        } //End block
        if(count == 0)        
        {
T[] var2CE1F6B8DBEF82A682E5E21A4D0315D3_1058709778 =             (T[]) ArrayUtils.emptyArray(kind);
            var2CE1F6B8DBEF82A682E5E21A4D0315D3_1058709778.addTaint(getTaint());
            return var2CE1F6B8DBEF82A682E5E21A4D0315D3_1058709778;
        } //End block
        if(count == 1)        
        {
            ret = (Object[]) Array.newInstance(kind, 1);
            ret[0] = ret1;
T[] var110CDC22E76A3D741F90CE34DAD38798_138378504 =             (T[]) ret;
            var110CDC22E76A3D741F90CE34DAD38798_138378504.addTaint(getTaint());
            return var110CDC22E76A3D741F90CE34DAD38798_138378504;
        } //End block
        if(count == ret.length)        
        {
T[] var110CDC22E76A3D741F90CE34DAD38798_1848989534 =             (T[]) ret;
            var110CDC22E76A3D741F90CE34DAD38798_1848989534.addTaint(getTaint());
            return var110CDC22E76A3D741F90CE34DAD38798_1848989534;
        } //End block
        Object[] nret = (Object[]) Array.newInstance(kind, count);
        System.arraycopy(ret, 0, nret, 0, count);
T[] varC2D15B14AC552DB2CA74824AA0037754_1920914000 =         (T[]) nret;
        varC2D15B14AC552DB2CA74824AA0037754_1920914000.addTaint(getTaint());
        return varC2D15B14AC552DB2CA74824AA0037754_1920914000;
        // ---------- Original Method ----------
        // Original Method Too Long, Refer to Original Implementation
*/    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.100 -0500", hash_original_method = "AE7B5CCE37B25ED0DAD474C110343705", hash_generated_method = "B15379486045DF44417ECB33E33EDC55")
    
public int nextSpanTransition(int start, int limit, Class kind) {
        /*int count = mSpanCount;
        Object[] spans = mSpans;
        int[] data = mSpanData;

        if (kind == null) {
            kind = Object.class;
        }

        for (int i = 0; i < count; i++) {
            int st = data[i * COLUMNS + START];
            int en = data[i * COLUMNS + END];

            if (st > start && st < limit && kind.isInstance(spans[i]))
                limit = st;
            if (en > start && en < limit && kind.isInstance(spans[i]))
                limit = en;
        }

        return limit;*/
        return (start + limit + kind.getTaintInt() + mText.getTaintInt());
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.103 -0500", hash_original_method = "AE5753E793A4485018C5FAB8D6A92DBC", hash_generated_method = "42E76E0158E824BFE31D4A31A4EA2C22")
    
private void sendSpanAdded(Object what, int start, int end) {
        SpanWatcher[] recip = getSpans(start, end, SpanWatcher.class);
        int n = recip.length;

        for (int i = 0; i < n; i++) {
            recip[i].onSpanAdded((Spannable) this, what, start, end);
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.105 -0500", hash_original_method = "60A86A7ABB49125FA35263BF87AB59D6", hash_generated_method = "E86D7CB5BE08A013400D518D87763112")
    
private void sendSpanRemoved(Object what, int start, int end) {
        SpanWatcher[] recip = getSpans(start, end, SpanWatcher.class);
        int n = recip.length;

        for (int i = 0; i < n; i++) {
            recip[i].onSpanRemoved((Spannable) this, what, start, end);
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.108 -0500", hash_original_method = "859EF27A1BB05860E7E5EF0EAB661D52", hash_generated_method = "E0C5B043150B00CE3B8546CD924727DA")
    
private void sendSpanChanged(Object what, int s, int e, int st, int en) {
        SpanWatcher[] recip = getSpans(Math.min(s, st), Math.max(e, en),
                                       SpanWatcher.class);
        int n = recip.length;

        for (int i = 0; i < n; i++) {
            recip[i].onSpanChanged((Spannable) this, what, s, e, st, en);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:07.113 -0500", hash_original_method = "70C480F1BBBBF461E174788F8B29326C", hash_generated_method = "A599E02D51122E6540797E709E0B7A7A")
    
private void checkRange(final String operation, int start, int end) {
        if (end < start) {
            throw new IndexOutOfBoundsException(operation + " " +
                                                region(start, end) +
                                                " has end before start");
        }

        int len = length();

        if (start > len || end > len) {
            throw new IndexOutOfBoundsException(operation + " " +
                                                region(start, end) +
                                                " ends beyond length " + len);
        }

        if (start < 0 || end < 0) {
            throw new IndexOutOfBoundsException(operation + " " +
                                                region(start, end) +
                                                " starts before 0");
        }
    }
}

