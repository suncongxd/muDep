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
 * Copyright (C) 2008 The Android Open Source Project
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


package android.renderscript;

// Droidsafe Imports
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import droidsafe.runtime.*;
import android.util.Log;
public class ProgramStore extends BaseObj {

    /**
    * Returns a pre-defined program store object with the following
    * characteristics:
    *  - incoming pixels are drawn if their depth value is less than
    *    the stored value in the depth buffer. If the pixel is
    *    drawn, its value is also stored in the depth buffer
    *  - incoming pixels override the value stored in the color
    *    buffer if it passes the depth test
    *
    *  @param rs Context to which the program will belong.
    **/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.500 -0500", hash_original_method = "B1F693A20C9EACBCF1A076E751E2AC13", hash_generated_method = "125A42D2E17871CA889CBF43544732B4")
    
public static ProgramStore BLEND_NONE_DEPTH_TEST(RenderScript rs) {
        if(rs.mProgramStore_BLEND_NONE_DEPTH_TEST == null) {
            ProgramStore.Builder builder = new ProgramStore.Builder(rs);
            builder.setDepthFunc(ProgramStore.DepthFunc.LESS);
            builder.setBlendFunc(BlendSrcFunc.ONE, BlendDstFunc.ZERO);
            builder.setDitherEnabled(false);
            builder.setDepthMaskEnabled(true);
            rs.mProgramStore_BLEND_NONE_DEPTH_TEST = builder.create();
        }
        return rs.mProgramStore_BLEND_NONE_DEPTH_TEST;
    }
    /**
    * Returns a pre-defined program store object with the following
    * characteristics:
    *  - incoming pixels always pass the depth test and their value
    *    is not stored in the depth buffer
    *  - incoming pixels override the value stored in the color
    *    buffer
    *
    *  @param rs Context to which the program will belong.
    **/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.503 -0500", hash_original_method = "65087DC5D7FD1CF215D9AA1C8C99653A", hash_generated_method = "DC77B6515A50DDA9A7C60FFC96CAD866")
    
public static ProgramStore BLEND_NONE_DEPTH_NONE(RenderScript rs) {
        if(rs.mProgramStore_BLEND_NONE_DEPTH_NO_DEPTH == null) {
            ProgramStore.Builder builder = new ProgramStore.Builder(rs);
            builder.setDepthFunc(ProgramStore.DepthFunc.ALWAYS);
            builder.setBlendFunc(BlendSrcFunc.ONE, BlendDstFunc.ZERO);
            builder.setDitherEnabled(false);
            builder.setDepthMaskEnabled(false);
            rs.mProgramStore_BLEND_NONE_DEPTH_NO_DEPTH = builder.create();
        }
        return rs.mProgramStore_BLEND_NONE_DEPTH_NO_DEPTH;
    }
    /**
    * Returns a pre-defined program store object with the following
    * characteristics:
    *  - incoming pixels are drawn if their depth value is less than
    *    the stored value in the depth buffer. If the pixel is
    *    drawn, its value is also stored in the depth buffer
    *  - if the incoming (Source) pixel passes depth test, its value
    *    is combined with the stored color (Dest) using the
    *    following formula
    *  Final.RGB = Source.RGB * Source.A + Dest.RGB * (1 - Source.A)
    *
    *  @param rs Context to which the program will belong.
    **/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.506 -0500", hash_original_method = "184BC201174779D42A7717E3FD838DBA", hash_generated_method = "CA32CE77C096EB5AB9DD622B33FBFAE8")
    
public static ProgramStore BLEND_ALPHA_DEPTH_TEST(RenderScript rs) {
        if(rs.mProgramStore_BLEND_ALPHA_DEPTH_TEST == null) {
            ProgramStore.Builder builder = new ProgramStore.Builder(rs);
            builder.setDepthFunc(ProgramStore.DepthFunc.LESS);
            builder.setBlendFunc(BlendSrcFunc.SRC_ALPHA, BlendDstFunc.ONE_MINUS_SRC_ALPHA);
            builder.setDitherEnabled(false);
            builder.setDepthMaskEnabled(true);
            rs.mProgramStore_BLEND_ALPHA_DEPTH_TEST = builder.create();
        }
        return rs.mProgramStore_BLEND_ALPHA_DEPTH_TEST;
    }
    /**
    * Returns a pre-defined program store object with the following
    * characteristics:
    *  - incoming pixels always pass the depth test and their value
    *    is not stored in the depth buffer
    *  - incoming pixel's value is combined with the stored color
    *    (Dest) using the following formula
    *  Final.RGB = Source.RGB * Source.A + Dest.RGB * (1 - Source.A)
    *
    *  @param rs Context to which the program will belong.
    **/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.508 -0500", hash_original_method = "72EA6E7457D3D683764F409EAD8192E1", hash_generated_method = "5E98BC2231DB84048368921DC1664049")
    
public static ProgramStore BLEND_ALPHA_DEPTH_NONE(RenderScript rs) {
        if(rs.mProgramStore_BLEND_ALPHA_DEPTH_NO_DEPTH == null) {
            ProgramStore.Builder builder = new ProgramStore.Builder(rs);
            builder.setDepthFunc(ProgramStore.DepthFunc.ALWAYS);
            builder.setBlendFunc(BlendSrcFunc.SRC_ALPHA, BlendDstFunc.ONE_MINUS_SRC_ALPHA);
            builder.setDitherEnabled(false);
            builder.setDepthMaskEnabled(false);
            rs.mProgramStore_BLEND_ALPHA_DEPTH_NO_DEPTH = builder.create();
        }
        return rs.mProgramStore_BLEND_ALPHA_DEPTH_NO_DEPTH;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.460 -0500", hash_original_field = "8FAB28482622024207604FC8F68FBA68", hash_generated_field = "8FAB28482622024207604FC8F68FBA68")

    DepthFunc mDepthFunc;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.462 -0500", hash_original_field = "FC6FA044D5DC3801C8E4182A42864AF5", hash_generated_field = "FC6FA044D5DC3801C8E4182A42864AF5")

    boolean mDepthMask;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.464 -0500", hash_original_field = "960D09803A4683C4A2879BE8D1C9DE36", hash_generated_field = "960D09803A4683C4A2879BE8D1C9DE36")

    boolean mColorMaskR;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.466 -0500", hash_original_field = "D753E63388AFCEB1C1E8ED1C2E5A5CDD", hash_generated_field = "D753E63388AFCEB1C1E8ED1C2E5A5CDD")

    boolean mColorMaskG;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.468 -0500", hash_original_field = "4F2C8807D2265836A23A4C5CE2D26F83", hash_generated_field = "4F2C8807D2265836A23A4C5CE2D26F83")

    boolean mColorMaskB;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.470 -0500", hash_original_field = "5CAA513127E3F20913DEBEEE391AC5C2", hash_generated_field = "5CAA513127E3F20913DEBEEE391AC5C2")

    boolean mColorMaskA;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.472 -0500", hash_original_field = "B1BBF7FAB925501900F9837599439C47", hash_generated_field = "B1BBF7FAB925501900F9837599439C47")

    BlendSrcFunc mBlendSrc;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.474 -0500", hash_original_field = "72512BE02D1B3C4D84AB5B81AC84E45D", hash_generated_field = "72512BE02D1B3C4D84AB5B81AC84E45D")

    BlendDstFunc mBlendDst;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.475 -0500", hash_original_field = "28ABDDC6F40109F392846C3ED0C6A12A", hash_generated_field = "28ABDDC6F40109F392846C3ED0C6A12A")

    boolean mDither;

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.478 -0500", hash_original_method = "10AC3BE9B8E79A8C066B5A14E3CCD64D", hash_generated_method = "10AC3BE9B8E79A8C066B5A14E3CCD64D")
    
ProgramStore(int id, RenderScript rs) {
        super(id, rs);
    }

    /**
    * @hide
    * @return depth function
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.480 -0500", hash_original_method = "E178526BAD510BEC5A50D318A1F97DC1", hash_generated_method = "EF43B32DCEA66AA421F4CF00BE6D5886")
    
public DepthFunc getDepthFunc() {
        return mDepthFunc;
    }

    /**
    * @hide
    * @return whether depth writes are enabled
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.482 -0500", hash_original_method = "1F891625D44773A65FB94F9D60624E8A", hash_generated_method = "84310C9CC3090C9E27D1E444EDF6DC10")
    
public boolean getDepthMaskEnabled() {
        return mDepthMask;
    }

    /**
    * @hide
    * @return red color channel mask
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.484 -0500", hash_original_method = "D81DE2799023369A5B0CF1E2BC6BC18C", hash_generated_method = "5C243E932225F546928BC1BB9CEEAD8B")
    
public boolean getColorMaskREnabled() {
        return mColorMaskR;
    }

    /**
    * @hide
    * @return green color channel mask
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.487 -0500", hash_original_method = "593F65FDE62B7209CAD6D4F19808F91A", hash_generated_method = "F0F9C71AC79D0ECC4C3BE67EB325E1DF")
    
public boolean getColorMaskGEnabled() {
        return mColorMaskG;
    }

    /**
    * @hide
    * @return blue color channel mask
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.489 -0500", hash_original_method = "03C0B53F0CD817F381C212AA9DF64896", hash_generated_method = "A3010293C2C9AFDB4E7BDF870C9ACB29")
    
public boolean getColorMaskBEnabled() {
        return mColorMaskB;
    }

    /**
    * @hide
    * @return alpha channel mask
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.491 -0500", hash_original_method = "9A2193599E83E8BA9884E0A2E8A26218", hash_generated_method = "30C5FC62BF75EE520B1C6A3DEC4ECA0D")
    
public boolean getColorMaskAEnabled() {
        return mColorMaskA;
    }

    /**
    * @hide
    * @return source blend function
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.493 -0500", hash_original_method = "357FBEAFF960E5DE9B63C0DF2F2569E1", hash_generated_method = "4C8F6BEA3EC481B91C782AFA7C158404")
    
public BlendSrcFunc getBlendSrcFunc() {
        return mBlendSrc;
    }

    /**
    * @hide
    * @return destination blend function
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.496 -0500", hash_original_method = "0FD248B13178F7E7D7FDD24B8B83E163", hash_generated_method = "2DB46AD618C39DE5994B58A8F9FA5460")
    
public BlendDstFunc getBlendDstFunc() {
        return mBlendDst;
    }

    /**
    * @hide
    * @return whether dither is enabled
    */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.498 -0500", hash_original_method = "B3B90DE9FCB53C4AF17446CF7F19CDA0", hash_generated_method = "E67A9410640A64AD90B2EDFEE09043E3")
    
public boolean getDitherEnabled() {
        return mDither;
    }
    
    public enum DepthFunc {
        ALWAYS (0),
        LESS (1),
        LESS_OR_EQUAL (2),
        GREATER (3),
        GREATER_OR_EQUAL (4),
        EQUAL (5),
        NOT_EQUAL (6);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.439 -0500", hash_original_field = "0B53E374282B34FB268F3024A1E73BA5", hash_generated_field = "0B53E374282B34FB268F3024A1E73BA5")

        int mID;
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.442 -0500", hash_original_method = "4D743C244C42A0A55820AF1A507A208A", hash_generated_method = "4D743C244C42A0A55820AF1A507A208A")
            
DepthFunc(int id) {
            mID = id;
        }
    }
    
    public enum BlendSrcFunc {
        ZERO (0),
        ONE (1),
        DST_COLOR (2),
        ONE_MINUS_DST_COLOR (3),
        SRC_ALPHA (4),
        ONE_MINUS_SRC_ALPHA (5),
        DST_ALPHA (6),
        ONE_MINUS_DST_ALPHA (7),
        SRC_ALPHA_SATURATE (8);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.447 -0500", hash_original_field = "0B53E374282B34FB268F3024A1E73BA5", hash_generated_field = "0B53E374282B34FB268F3024A1E73BA5")

        int mID;
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.449 -0500", hash_original_method = "18F498B116E7706225D000DB3E3C3B7D", hash_generated_method = "18F498B116E7706225D000DB3E3C3B7D")
            
BlendSrcFunc(int id) {
            mID = id;
        }
    }
    
    public enum BlendDstFunc {
        ZERO (0),
        ONE (1),
        SRC_COLOR (2),
        ONE_MINUS_SRC_COLOR (3),
        SRC_ALPHA (4),
        ONE_MINUS_SRC_ALPHA (5),
        DST_ALPHA (6),
        ONE_MINUS_DST_ALPHA (7);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.453 -0500", hash_original_field = "0B53E374282B34FB268F3024A1E73BA5", hash_generated_field = "0B53E374282B34FB268F3024A1E73BA5")

        int mID;
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.457 -0500", hash_original_method = "1D0F32AC64FC5D2F60302C581EF57CC8", hash_generated_method = "1D0F32AC64FC5D2F60302C581EF57CC8")
            
BlendDstFunc(int id) {
            mID = id;
        }
    }
    
    public static class Builder {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.511 -0500", hash_original_field = "AD6E2452AD39DF3B497ED4B7E83C4E0C", hash_generated_field = "AD6E2452AD39DF3B497ED4B7E83C4E0C")

        RenderScript mRS;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.513 -0500", hash_original_field = "8FAB28482622024207604FC8F68FBA68", hash_generated_field = "8FAB28482622024207604FC8F68FBA68")

        DepthFunc mDepthFunc;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.516 -0500", hash_original_field = "FC6FA044D5DC3801C8E4182A42864AF5", hash_generated_field = "FC6FA044D5DC3801C8E4182A42864AF5")

        boolean mDepthMask;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.518 -0500", hash_original_field = "960D09803A4683C4A2879BE8D1C9DE36", hash_generated_field = "960D09803A4683C4A2879BE8D1C9DE36")

        boolean mColorMaskR;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.520 -0500", hash_original_field = "D753E63388AFCEB1C1E8ED1C2E5A5CDD", hash_generated_field = "D753E63388AFCEB1C1E8ED1C2E5A5CDD")

        boolean mColorMaskG;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.522 -0500", hash_original_field = "4F2C8807D2265836A23A4C5CE2D26F83", hash_generated_field = "4F2C8807D2265836A23A4C5CE2D26F83")

        boolean mColorMaskB;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.524 -0500", hash_original_field = "5CAA513127E3F20913DEBEEE391AC5C2", hash_generated_field = "5CAA513127E3F20913DEBEEE391AC5C2")

        boolean mColorMaskA;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.526 -0500", hash_original_field = "B1BBF7FAB925501900F9837599439C47", hash_generated_field = "B1BBF7FAB925501900F9837599439C47")

        BlendSrcFunc mBlendSrc;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.528 -0500", hash_original_field = "72512BE02D1B3C4D84AB5B81AC84E45D", hash_generated_field = "72512BE02D1B3C4D84AB5B81AC84E45D")

        BlendDstFunc mBlendDst;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.531 -0500", hash_original_field = "28ABDDC6F40109F392846C3ED0C6A12A", hash_generated_field = "28ABDDC6F40109F392846C3ED0C6A12A")

        boolean mDither;

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.534 -0500", hash_original_method = "265149AB55D37D488388E31C3D90875C", hash_generated_method = "8782D1BB6D12356D1C95C1F209546338")
        
public Builder(RenderScript rs) {
            mRS = rs;
            mDepthFunc = DepthFunc.ALWAYS;
            mDepthMask = false;
            mColorMaskR = true;
            mColorMaskG = true;
            mColorMaskB = true;
            mColorMaskA = true;
            mBlendSrc = BlendSrcFunc.ONE;
            mBlendDst = BlendDstFunc.ZERO;
        }

        /**
        * Specifies the depth testing behavior
        *
        * @param func function used for depth testing
        *
        * @return this
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.536 -0500", hash_original_method = "077DECC8ABDB24AB85F09F77AF3C34E9", hash_generated_method = "83B7D7D8EE19DF26DD1DC84C8B54C726")
        
public Builder setDepthFunc(DepthFunc func) {
            mDepthFunc = func;
            return this;
        }

        /**
        * Enables writes into the depth buffer
        *
        * @param enable specifies whether depth writes are
        *         enabled or disabled
        *
        * @return this
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.538 -0500", hash_original_method = "73973CBC15407B8A1D4D695A797EF98B", hash_generated_method = "A3CF7E942FE917C2603F0A343B058756")
        
public Builder setDepthMaskEnabled(boolean enable) {
            mDepthMask = enable;
            return this;
        }

        /**
        * Enables writes into the color buffer
        *
        * @param r specifies whether red channel is written
        * @param g specifies whether green channel is written
        * @param b specifies whether blue channel is written
        * @param a specifies whether alpha channel is written
        *
        * @return this
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.541 -0500", hash_original_method = "A842B9DBA01F76FC3765EB18537F0DD4", hash_generated_method = "83052F8BA8E4B4DD7D23A1CF1872DB55")
        
public Builder setColorMaskEnabled(boolean r, boolean g, boolean b, boolean a) {
            mColorMaskR = r;
            mColorMaskG = g;
            mColorMaskB = b;
            mColorMaskA = a;
            return this;
        }

        /**
        * Specifies how incoming pixels are combined with the pixels
        * stored in the framebuffer
        *
        * @param src specifies how the source blending factor is
        *            computed
        * @param dst specifies how the destination blending factor is
        *            computed
        *
        * @return this
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.543 -0500", hash_original_method = "BF6FFFCAA7ABBFD68FA703F185B02D94", hash_generated_method = "B8A1D76D200F565D796D1937D9A8FCB9")
        
public Builder setBlendFunc(BlendSrcFunc src, BlendDstFunc dst) {
            mBlendSrc = src;
            mBlendDst = dst;
            return this;
        }

        /**
        * Enables dithering
        *
        * @param enable specifies whether dithering is enabled or
        *               disabled
        *
        * @return this
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.546 -0500", hash_original_method = "F7A644C4DD22B87998E888BDC808A0FA", hash_generated_method = "780137DC349CC213E7F93FB107356CF5")
        
public Builder setDitherEnabled(boolean enable) {
            mDither = enable;
            return this;
        }

        /**
        * Creates a program store from the current state of the builder
        */
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:03.549 -0500", hash_original_method = "7B8B743B7FBFF92D2151AB95EB2CD90A", hash_generated_method = "7307204335977C474DD967607DEE4EA3")
        
public ProgramStore create() {
            mRS.validate();
            int id = mRS.nProgramStoreCreate(mColorMaskR, mColorMaskG, mColorMaskB, mColorMaskA,
                                             mDepthMask, mDither,
                                             mBlendSrc.mID, mBlendDst.mID, mDepthFunc.mID);
            ProgramStore programStore = new ProgramStore(id, mRS);
            programStore.mDepthFunc = mDepthFunc;
            programStore.mDepthMask = mDepthMask;
            programStore.mColorMaskR = mColorMaskR;
            programStore.mColorMaskG = mColorMaskG;
            programStore.mColorMaskB = mColorMaskB;
            programStore.mColorMaskA = mColorMaskA;
            programStore.mBlendSrc = mBlendSrc;
            programStore.mBlendDst = mBlendDst;
            programStore.mDither = mDither;
            return programStore;
        }
        
    }
    
}

