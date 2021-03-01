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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.style.ParagraphStyle;
import android.util.FloatMath;

public class BoringLayout extends Layout implements TextUtils.EllipsizeCallback {
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.448 -0500", hash_original_method = "45302125C5623367D121D61EE264BB3D", hash_generated_method = "B6F6733ADD16B96B785BE8B828E1CB18")
    
public static BoringLayout make(CharSequence source,
                        TextPaint paint, int outerwidth,
                        Alignment align,
                        float spacingmult, float spacingadd,
                        BoringLayout.Metrics metrics, boolean includepad) {
        return new BoringLayout(source, paint, outerwidth, align,
                                spacingmult, spacingadd, metrics,
                                includepad);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.451 -0500", hash_original_method = "6E63C5CDA40BE5F908BE8F8A47E2CA22", hash_generated_method = "EAC7D9B3733B40AF40E6CEC4555A65F1")
    
public static BoringLayout make(CharSequence source,
                        TextPaint paint, int outerwidth,
                        Alignment align,
                        float spacingmult, float spacingadd,
                        BoringLayout.Metrics metrics, boolean includepad,
                        TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
        return new BoringLayout(source, paint, outerwidth, align,
                                spacingmult, spacingadd, metrics,
                                includepad, ellipsize, ellipsizedWidth);
    }

    /**
     * Returns null if not boring; the width, ascent, and descent if boring.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.471 -0500", hash_original_method = "96B9D5C527FFE50D39D28C5BAE93E1F6", hash_generated_method = "7DDC272504240B7C0817A1EDA013058A")
    
public static Metrics isBoring(CharSequence text,
                                   TextPaint paint) {
        return isBoring(text, paint, TextDirectionHeuristics.FIRSTSTRONG_LTR, null);
    }

    /**
     * Returns null if not boring; the width, ascent, and descent if boring.
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.474 -0500", hash_original_method = "4C34347B458F16A46FBA6214D1ED5964", hash_generated_method = "A6CCBBAD1B4ECC995F624A6D656399FE")
    
public static Metrics isBoring(CharSequence text,
                                   TextPaint paint,
                                   TextDirectionHeuristic textDir) {
        return isBoring(text, paint, textDir, null);
    }

    /**
     * Returns null if not boring; the width, ascent, and descent in the
     * provided Metrics object (or a new one if the provided one was null)
     * if boring.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.477 -0500", hash_original_method = "94FF10F23CAC1DAA4FE15530650F9ABB", hash_generated_method = "6077B41364B1F42147C038DAED378094")
    
public static Metrics isBoring(CharSequence text, TextPaint paint, Metrics metrics) {
        return isBoring(text, paint, TextDirectionHeuristics.FIRSTSTRONG_LTR, metrics);
    }

    /**
     * Returns null if not boring; the width, ascent, and descent in the
     * provided Metrics object (or a new one if the provided one was null)
     * if boring.
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.480 -0500", hash_original_method = "C478053468A8ECCA4C801EC7C4CA3211", hash_generated_method = "AC4E2B54F11003706D3423743D1E5DA8")
    
public static Metrics isBoring(CharSequence text, TextPaint paint,
            TextDirectionHeuristic textDir, Metrics metrics) {
        char[] temp = TextUtils.obtain(500);
        int length = text.length();
        boolean boring = true;

        outer:
        for (int i = 0; i < length; i += 500) {
            int j = i + 500;

            if (j > length)
                j = length;

            TextUtils.getChars(text, i, j, temp, 0);

            int n = j - i;

            for (int a = 0; a < n; a++) {
                char c = temp[a];

                if (c == '\n' || c == '\t' || c >= FIRST_RIGHT_TO_LEFT) {
                    boring = false;
                    break outer;
                }
            }

            if (textDir != null && textDir.isRtl(temp, 0, n)) {
               boring = false;
               break outer;
            }
        }

        TextUtils.recycle(temp);

        if (boring && text instanceof Spanned) {
            Spanned sp = (Spanned) text;
            Object[] styles = sp.getSpans(0, length, ParagraphStyle.class);
            if (styles.length > 0) {
                boring = false;
            }
        }

        if (boring) {
            Metrics fm = metrics;
            if (fm == null) {
                fm = new Metrics();
            }

            TextLine line = TextLine.obtain();
            line.set(paint, text, 0, length, Layout.DIR_LEFT_TO_RIGHT,
                    Layout.DIRS_ALL_LEFT_TO_RIGHT, false, null);
            fm.width = (int) FloatMath.ceil(line.metrics(fm));
            TextLine.recycle(line);

            return fm;
        } else {
            return null;
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.527 -0500", hash_original_field = "C43C06106F60684BB16151D66140BBB2", hash_generated_field = "C2F2F45019948F5515A5534F9B80EBF4")

    private static final char FIRST_RIGHT_TO_LEFT = '\u0590';
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.544 -0500", hash_original_field = "7DC8DE07DCC9FC2DB1DAF906950EA1CC", hash_generated_field = "AEB4C46A4C665812604A1CC18568D7E1")

    private static final TextPaint sTemp =
                                new TextPaint();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.530 -0500", hash_original_field = "43602C9DA546BE47B69DC72EAB1E2E24", hash_generated_field = "1B339E7F22FA229115A323159C174907")

    private String mDirect;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.532 -0500", hash_original_field = "0344A2D91EF1BCE652EE63EFC12EEAC9", hash_generated_field = "75BCEEBE83B26919B33E8EA8B289919E")

    private Paint mPaint;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "2FFD20D7E48915664FDF7F12FC3889D8", hash_generated_field = "E3BE43E8597DE7EA48FEB065FD52A21A")

    int mBottom;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "7CC9BE6231ADF86659204A1B34464053", hash_generated_field = "73810C960686DE7D2398567074C867B2")

    int mDesc;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "A6796D45932911E4DA1D9D40D1BD23B8", hash_generated_field = "74C5EEEB9113B0F29D66E0A4983A6BC3")

    private int mTopPadding;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "097A31AA17188409E251DABE4869FAFE", hash_generated_field = "8A7C981A0C6837E513C477AD1250B374")

    private int mBottomPadding;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.539 -0500", hash_original_field = "49586E9819437ED10960A2451558B838", hash_generated_field = "61EBA81EE8DC22B1F5E764B863E5361B")

    private float mMax;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "5713AC9F49879DE1055FA780DFEFDF1C", hash_generated_field = "C354DF48C5764CCC7B8B820C49BF2BBD")

    private int mEllipsizedWidth;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "2500F3F15EEAC3B4C3773F3A0481EE44", hash_generated_field = "B5CB4429DE9FEE6D7E71228CD62A452F")

    private int mEllipsizedStart;
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.463 -0400", hash_original_field = "99B4B7415CCA47573BDE1AEF6AEEFAB0", hash_generated_field = "AB882CB344FFDF6F2159A6342489C1EA")

    private int mEllipsizedCount;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.461 -0500", hash_original_method = "4AC8C9A33DE614873CD5C4119639BAC7", hash_generated_method = "F2DBC1B10E2958A754D909B3760D47C6")
    
public BoringLayout(CharSequence source,
                        TextPaint paint, int outerwidth,
                        Alignment align,
                        float spacingmult, float spacingadd,
                        BoringLayout.Metrics metrics, boolean includepad) {
        super(source, paint, outerwidth, align, spacingmult, spacingadd);

        mEllipsizedWidth = outerwidth;
        mEllipsizedStart = 0;
        mEllipsizedCount = 0;

        init(source, paint, outerwidth, align, spacingmult, spacingadd,
             metrics, includepad, true);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.464 -0500", hash_original_method = "614BD9653B1F69671BF8486744526AD2", hash_generated_method = "07824EE093EA75D93760AA9AB9EF9A80")
    
public BoringLayout(CharSequence source,
                        TextPaint paint, int outerwidth,
                        Alignment align,
                        float spacingmult, float spacingadd,
                        BoringLayout.Metrics metrics, boolean includepad,
                        TextUtils.TruncateAt ellipsize, int ellipsizedWidth) {
        /*
         * It is silly to have to call super() and then replaceWith(),
         * but we can't use "this" for the callback until the call to
         * super() finishes.
         */
        super(source, paint, outerwidth, align, spacingmult, spacingadd);

        boolean trust;

        if (ellipsize == null || ellipsize == TextUtils.TruncateAt.MARQUEE) {
            mEllipsizedWidth = outerwidth;
            mEllipsizedStart = 0;
            mEllipsizedCount = 0;
            trust = true;
        } else {
            replaceWith(TextUtils.ellipsize(source, paint, ellipsizedWidth,
                                           ellipsize, true, this),
                        paint, outerwidth, align, spacingmult,
                        spacingadd);

            mEllipsizedWidth = ellipsizedWidth;
            trust = false;
        }

        init(getText(), paint, outerwidth, align, spacingmult, spacingadd,
             metrics, includepad, trust);
    }

    /**
     * Returns a BoringLayout for the specified text, potentially reusing
     * this one if it is already suitable.  The caller must make sure that
     * no one is still using this Layout.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.454 -0500", hash_original_method = "CC7D93FCE240EB61BF168844CD074EBF", hash_generated_method = "E2D2D813F37FE42C06629C9257DB741D")
    
public BoringLayout replaceOrMake(CharSequence source, TextPaint paint,
                                      int outerwidth, Alignment align,
                                      float spacingmult, float spacingadd,
                                      BoringLayout.Metrics metrics,
                                      boolean includepad) {
        replaceWith(source, paint, outerwidth, align, spacingmult,
                    spacingadd);

        mEllipsizedWidth = outerwidth;
        mEllipsizedStart = 0;
        mEllipsizedCount = 0;

        init(source, paint, outerwidth, align, spacingmult, spacingadd,
             metrics, includepad, true);
        return this;
    }

    /**
     * Returns a BoringLayout for the specified text, potentially reusing
     * this one if it is already suitable.  The caller must make sure that
     * no one is still using this Layout.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.457 -0500", hash_original_method = "4B78087A434BCC06D8E64A3B5F0E26A9", hash_generated_method = "70531B076501D18170292338579EDB83")
    
public BoringLayout replaceOrMake(CharSequence source, TextPaint paint,
                                      int outerwidth, Alignment align,
                                      float spacingmult, float spacingadd,
                                      BoringLayout.Metrics metrics,
                                      boolean includepad,
                                      TextUtils.TruncateAt ellipsize,
                                      int ellipsizedWidth) {
        boolean trust;

        if (ellipsize == null || ellipsize == TextUtils.TruncateAt.MARQUEE) {
            replaceWith(source, paint, outerwidth, align, spacingmult,
                        spacingadd);

            mEllipsizedWidth = outerwidth;
            mEllipsizedStart = 0;
            mEllipsizedCount = 0;
            trust = true;
        } else {
            replaceWith(TextUtils.ellipsize(source, paint, ellipsizedWidth,
                                           ellipsize, true, this),
                        paint, outerwidth, align, spacingmult,
                        spacingadd);

            mEllipsizedWidth = ellipsizedWidth;
            trust = false;
        }

        init(getText(), paint, outerwidth, align, spacingmult, spacingadd,
             metrics, includepad, trust);
        return this;
    }

    /* package */ @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.468 -0500", hash_original_method = "72A5FE71A3D3A6FEDD367DD5E3D986F4", hash_generated_method = "352587C3B27032D2CE05C3C7C6ECED82")
    
void init(CharSequence source,
                            TextPaint paint, int outerwidth,
                            Alignment align,
                            float spacingmult, float spacingadd,
                            BoringLayout.Metrics metrics, boolean includepad,
                            boolean trustWidth) {
        int spacing;

        if (source instanceof String && align == Layout.Alignment.ALIGN_NORMAL) {
            mDirect = source.toString();
        } else {
            mDirect = null;
        }

        mPaint = paint;

        if (includepad) {
            spacing = metrics.bottom - metrics.top;
        } else {
            spacing = metrics.descent - metrics.ascent;
        }

        if (spacingmult != 1 || spacingadd != 0) {
            spacing = (int)(spacing * spacingmult + spacingadd + 0.5f);
        }

        mBottom = spacing;

        if (includepad) {
            mDesc = spacing + metrics.top;
        } else {
            mDesc = spacing + metrics.ascent;
        }

        if (trustWidth) {
            mMax = metrics.width;
        } else {
            /*
             * If we have ellipsized, we have to actually calculate the
             * width because the width that was passed in was for the
             * full text, not the ellipsized form.
             */
            TextLine line = TextLine.obtain();
            line.set(paint, source, 0, source.length(), Layout.DIR_LEFT_TO_RIGHT,
                    Layout.DIRS_ALL_LEFT_TO_RIGHT, false, null);
            mMax = (int) FloatMath.ceil(line.metrics(null));
            TextLine.recycle(line);
        }

        if (includepad) {
            mTopPadding = metrics.top - metrics.ascent;
            mBottomPadding = metrics.bottom - metrics.descent;
        }
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.483 -0500", hash_original_method = "9C145AF64C7D263F1721F68F7FF83F17", hash_generated_method = "1A5EDB7C78561E1B6A3CA9296B022CEA")
    
@Override
    public int getHeight() {
        return mBottom;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.485 -0500", hash_original_method = "2D16E05C4EC39BC67C8A172C64094881", hash_generated_method = "1DE24C63CB0697E8870329A5E24CC12B")
    
@Override
    public int getLineCount() {
        return 1;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.488 -0500", hash_original_method = "FC2E2A347EB554C00B43E510002C6FE3", hash_generated_method = "990E12A6FCB4160DA287EE4A98074680")
    
@Override
    public int getLineTop(int line) {
        if (line == 0)
            return 0;
        else
            return mBottom;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.491 -0500", hash_original_method = "CEFD06A93AC917501D80ABE3ADC321FA", hash_generated_method = "C46B9B5899C49F0AB130E8988688C51E")
    
@Override
    public int getLineDescent(int line) {
        return mDesc;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.494 -0500", hash_original_method = "01507A0D62D72F853366982B51A08E39", hash_generated_method = "D7C20105564F43ADECD0C10972D21858")
    
@Override
    public int getLineStart(int line) {
        if (line == 0)
            return 0;
        else
            return getText().length();
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.497 -0500", hash_original_method = "C13A8C90111FE51B6C5C9E4F734C2F1E", hash_generated_method = "834AF7DF658E88CC4B9C6FB7AFC51B75")
    
@Override
    public int getParagraphDirection(int line) {
        return DIR_LEFT_TO_RIGHT;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.499 -0500", hash_original_method = "26D4049A2082A20A88CBFE67C9B7B59C", hash_generated_method = "E837ED9BD633F310511D8B2EDFAD5CC4")
    
@Override
    public boolean getLineContainsTab(int line) {
        return false;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.501 -0500", hash_original_method = "B75E83FBFEF77E240A9CDC2AC2A1C8A4", hash_generated_method = "17CABB93B65AC003BC199153051E2372")
    
@Override
    public float getLineMax(int line) {
        return mMax;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.504 -0500", hash_original_method = "6C6069268CAFC6462BC63ED1C5A7526F", hash_generated_method = "6780211DA0E50E9C5F3C19D6521C8B68")
    
@Override
    public final Directions getLineDirections(int line) {
        return Layout.DIRS_ALL_LEFT_TO_RIGHT;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.507 -0500", hash_original_method = "0410F05B3F48387C42DB277B8067D4A9", hash_generated_method = "C9A37F9D7E53BC032BE1989D8B7F43F3")
    
@Override
    public int getTopPadding() {
        return mTopPadding;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.511 -0500", hash_original_method = "A7597317BC8A3102F80AD4E9AD038652", hash_generated_method = "F0B9D53486F44C4D9B5E36E2D1B1C62A")
    
@Override
    public int getBottomPadding() {
        return mBottomPadding;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.513 -0500", hash_original_method = "BD15B39FEBBFBBCAB07017E8474F5CA3", hash_generated_method = "0B170560F878D4D3FBE864A3634D2C9B")
    
@Override
    public int getEllipsisCount(int line) {
        return mEllipsizedCount;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.516 -0500", hash_original_method = "414A86D89277DC065BB670E3078E25DD", hash_generated_method = "B386686EB6F85D1C517661821B85FFDB")
    
@Override
    public int getEllipsisStart(int line) {
        return mEllipsizedStart;
    }

    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.519 -0500", hash_original_method = "5CA9F8625BABB74DD57E485BAAAC3103", hash_generated_method = "466A36682DB1C1AD0B2234723777A380")
    
@Override
    public int getEllipsizedWidth() {
        return mEllipsizedWidth;
    }
    
    public static class Metrics extends Paint.FontMetricsInt {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.547 -0500", hash_original_field = "1589F848FE4FF192F7CCE1B9F9E85747", hash_generated_field = "6FAF64E7577371F06EC12EBE94C8466B")

        public int width;
        
        @DSComment("From safe class list")
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:36.475 -0400", hash_original_method = "5093089FD85953828BC1A867B844524A", hash_generated_method = "5093089FD85953828BC1A867B844524A")
        public Metrics ()
        {
            //Synthesized constructor
        }

        @DSComment("From safe class list")
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.550 -0500", hash_original_method = "12CBEFE116384BBA88EB750F89F5AC01", hash_generated_method = "A95A4D7572C4E1B6FBCDF53EAB89AD77")
        
@Override public String toString() {
            return super.toString() + " width=" + width;
        }
        
    }

    // Override draw so it will be faster.
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.522 -0500", hash_original_method = "5DBC6B0379E5085B5017E1E8304BC3E8", hash_generated_method = "0668CF67CEEDDF6C8A388466A8F78774")
    
@Override
    public void draw(Canvas c, Path highlight, Paint highlightpaint,
                     int cursorOffset) {
        if (mDirect != null && highlight == null) {
            c.drawText(mDirect, 0, mBottom - mDesc, mPaint);
        } else {
            super.draw(c, highlight, highlightpaint, cursorOffset);
        }
    }

    /**
     * Callback for the ellipsizer to report what region it ellipsized.
     */
    @DSComment("From safe class list")
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:28:10.524 -0500", hash_original_method = "C8834FD4CE389B3AEF157EF49CB23721", hash_generated_method = "1C6572A0E985A07720BE27AB01F3C5D5")
    
public void ellipsized(int start, int end) {
        mEllipsizedStart = start;
        mEllipsizedCount = end - start;
    }
}

