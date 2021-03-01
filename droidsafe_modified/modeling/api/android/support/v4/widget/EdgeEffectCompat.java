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
 * Copyright (C) 2011 The Android Open Source Project
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


package android.support.v4.widget;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;

public class EdgeEffectCompat {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.875 -0500", hash_original_field = "6A866271DD9DB39957FFF0E71ABE47DD", hash_generated_field = "C39368587D934C727CBBE97C813A7339")

    private static  EdgeEffectImpl IMPL;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.872 -0500", hash_original_field = "F098455E78527867B0243F5F899E173E", hash_generated_field = "8B86712DCA30565AC003C93F8A9D023C")

    private Object mEdgeEffect;

    /**
     * Construct a new EdgeEffect themed using the given context.
     *
     * <p>Note: On platform versions that do not support EdgeEffect, all operations
     * on the newly constructed object will be mocked/no-ops.</p>
     *
     * @param context Context to use for theming the effect
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.923 -0500", hash_original_method = "D753748532B85BCF2954D7C221C91401", hash_generated_method = "99B6705C421266259D620F031788635C")
    
public EdgeEffectCompat(Context context) {
        mEdgeEffect = IMPL.newEdgeEffect(context);
    }

    /**
     * Set the size of this edge effect in pixels.
     *
     * @param width Effect width in pixels
     * @param height Effect height in pixels
     */
    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.926 -0500", hash_original_method = "D4DAEE1A405642168AB096E0B8E2EAD2", hash_generated_method = "B498A43726E29C2CDF3B80ECFCEDD032")
    
public void setSize(int width, int height) {
        IMPL.setSize(mEdgeEffect, width, height);
    }

    /**
     * Reports if this EdgeEffectCompat's animation is finished. If this method returns false
     * after a call to {@link #draw(Canvas)} the host widget should schedule another
     * drawing pass to continue the animation.
     *
     * @return true if animation is finished, false if drawing should continue on the next frame.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.928 -0500", hash_original_method = "EFAA81473E99AA681742723D4D62AB38", hash_generated_method = "D3236B79F22826E0529DDF103F454015")
    
public boolean isFinished() {
        return IMPL.isFinished(mEdgeEffect);
    }

    /**
     * Immediately finish the current animation.
     * After this call {@link #isFinished()} will return true.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.930 -0500", hash_original_method = "9C12A7C81A14CC06F079C8FA3122B4B4", hash_generated_method = "30CF6231B05877BBDC59CDAD6BFD5988")
    
public void finish() {
        IMPL.finish(mEdgeEffect);
    }

    /**
     * A view should call this when content is pulled away from an edge by the user.
     * This will update the state of the current visual effect and its associated animation.
     * The host view should always {@link android.view.View#invalidate()} if this method
     * returns true and draw the results accordingly.
     *
     * @param deltaDistance Change in distance since the last call. Values may be 0 (no change) to
     *                      1.f (full length of the view) or negative values to express change
     *                      back toward the edge reached to initiate the effect.
     * @return true if the host view should call invalidate, false if it should not.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.932 -0500", hash_original_method = "1908B5EE653AB1B5A5CE65E81F6BDB7A", hash_generated_method = "9A640D01A4977E3F4B65B70A033B7231")
    
public boolean onPull(float deltaDistance) {
        return IMPL.onPull(mEdgeEffect, deltaDistance);
    }

    /**
     * Call when the object is released after being pulled.
     * This will begin the "decay" phase of the effect. After calling this method
     * the host view should {@link android.view.View#invalidate()} if this method
     * returns true and thereby draw the results accordingly.
     *
     * @return true if the host view should invalidate, false if it should not.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.935 -0500", hash_original_method = "45F767B8769552AFBFA0E978EE8933FD", hash_generated_method = "EAF114B9C5561D8D31AEE3D1496D01BD")
    
public boolean onRelease() {
        return IMPL.onRelease(mEdgeEffect);
    }

    /**
     * Call when the effect absorbs an impact at the given velocity.
     * Used when a fling reaches the scroll boundary.
     *
     * <p>When using a {@link android.widget.Scroller} or {@link android.widget.OverScroller},
     * the method <code>getCurrVelocity</code> will provide a reasonable approximation
     * to use here.</p>
     *
     * @param velocity Velocity at impact in pixels per second.
     * @return true if the host view should invalidate, false if it should not.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.937 -0500", hash_original_method = "F0B0848CCB34A5B04946FEDCFE4DC62C", hash_generated_method = "AE0842DBE8852517F775BFF6D7FE3266")
    
public boolean onAbsorb(int velocity) {
        return IMPL.onAbsorb(mEdgeEffect, velocity);
    }
    
    static class BaseEdgeEffectImpl implements EdgeEffectImpl {
        
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:34.020 -0400", hash_original_method = "E93AF950D68741CBF25DC67984C27D7B", hash_generated_method = "E93AF950D68741CBF25DC67984C27D7B")
        public BaseEdgeEffectImpl ()
        {
            //Synthesized constructor
        }
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.881 -0500", hash_original_method = "598F7EE7163BC02F940E7AE457E81E71", hash_generated_method = "355286D7ACF6715DC4A71E1EFC10BE32")
        
public Object newEdgeEffect(Context context) {
            return null;
        }

        @DSSafe(DSCat.SAFE_LIST)
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.884 -0500", hash_original_method = "7AB0823D4C5E0A56EB104E764A6EE643", hash_generated_method = "2A3F2A3644496ACB1CF1EDA0A2938943")
        
public void setSize(Object edgeEffect, int width, int height) {
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.886 -0500", hash_original_method = "18A3B4E50EAF82FBFB3F783FB1724270", hash_generated_method = "209B27BE8A70DC329352EB3997FF02D2")
        
public boolean isFinished(Object edgeEffect) {
            return true;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.888 -0500", hash_original_method = "4537DDA1058A07DD12C2683C46DAE0A0", hash_generated_method = "77E1BC407011D6971D3B0AAA2FC875E7")
        
public void finish(Object edgeEffect) {
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.890 -0500", hash_original_method = "6D6767FA0A7FD096DC5FAE330C25B4EB", hash_generated_method = "D4A16B50969E92E180B854DFEB29F16D")
        
public boolean onPull(Object edgeEffect, float deltaDistance) {
            return false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.893 -0500", hash_original_method = "4CCDA82CBCC611597E463B09D91011D3", hash_generated_method = "048FBF4CE4B86B1810E1BBBD49C02C8E")
        
public boolean onRelease(Object edgeEffect) {
            return false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.895 -0500", hash_original_method = "E4A19D3F19F8749DFC3F77D5467C90AC", hash_generated_method = "C0A49FBA9F3151B3B686FF6CECB264C0")
        
public boolean onAbsorb(Object edgeEffect, int velocity) {
            return false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.898 -0500", hash_original_method = "014795F812E9F03FFB958DEDF8F6EEBD", hash_generated_method = "3393E26C320D3FDDEEBEF275690BD46D")
        
public boolean draw(Object edgeEffect, Canvas canvas) {
            return false;
        }
        
    }
    
    static class EdgeEffectIcsImpl implements EdgeEffectImpl {
        
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:34.021 -0400", hash_original_method = "722DC077141274393C35BB912CA199C0", hash_generated_method = "722DC077141274393C35BB912CA199C0")
        public EdgeEffectIcsImpl ()
        {
            //Synthesized constructor
        }
        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.902 -0500", hash_original_method = "4DBA645CACC3049A11EF7913FF866293", hash_generated_method = "704CC874AC1A6D8E9DA7E8E94E887166")
        
public Object newEdgeEffect(Context context) {
            return EdgeEffectCompatIcs.newEdgeEffect(context);
        }

        @DSSafe(DSCat.SAFE_LIST)
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.904 -0500", hash_original_method = "7828FC66149E7A8F26ACE38B475CC37B", hash_generated_method = "12A358F1E0CC70FBC6CB0DC94F3A42EC")
        
public void setSize(Object edgeEffect, int width, int height) {
            EdgeEffectCompatIcs.setSize(edgeEffect, width, height);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.906 -0500", hash_original_method = "C615DC1E8C401B6C07B9EA0C459CE8A5", hash_generated_method = "A068BC3E0718F9554B4A521E87A7E308")
        
public boolean isFinished(Object edgeEffect) {
            return EdgeEffectCompatIcs.isFinished(edgeEffect);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.909 -0500", hash_original_method = "AD76F4504BEBAE253EF4AD4435BC2FB9", hash_generated_method = "F51AD21E7905D964A87CBE074E81AA80")
        
public void finish(Object edgeEffect) {
            EdgeEffectCompatIcs.finish(edgeEffect);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.912 -0500", hash_original_method = "A82E9A0A10EC720EFD1907409B81ECC2", hash_generated_method = "B8DDE75A9C34234C428C883D2CDB7B82")
        
public boolean onPull(Object edgeEffect, float deltaDistance) {
            return EdgeEffectCompatIcs.onPull(edgeEffect, deltaDistance);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.914 -0500", hash_original_method = "D85EB1006C2B11862110869EE99A2168", hash_generated_method = "282A51D0D4386F09AAEB9DF651CA9FCC")
        
public boolean onRelease(Object edgeEffect) {
            return EdgeEffectCompatIcs.onRelease(edgeEffect);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.917 -0500", hash_original_method = "DA1A282FCCAC2CD96B6A4C969DDDC50D", hash_generated_method = "70090F8E4927C0E956CE6D8CE505536C")
        
public boolean onAbsorb(Object edgeEffect, int velocity) {
            return EdgeEffectCompatIcs.onAbsorb(edgeEffect, velocity);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.919 -0500", hash_original_method = "C724F7B236835D74B44177C7968204E4", hash_generated_method = "9368FA0DE94CF02E1022FBBC94CA4E8B")
        
public boolean draw(Object edgeEffect, Canvas canvas) {
            return EdgeEffectCompatIcs.draw(edgeEffect, canvas);
        }
        
    }
    
    interface EdgeEffectImpl {
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public Object newEdgeEffect(Context context);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public void setSize(Object edgeEffect, int width, int height);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public boolean isFinished(Object edgeEffect);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public void finish(Object edgeEffect);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public boolean onPull(Object edgeEffect, float deltaDistance);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public boolean onRelease(Object edgeEffect);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public boolean onAbsorb(Object edgeEffect, int velocity);
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public boolean draw(Object edgeEffect, Canvas canvas);
    }

    /**
     * Draw into the provided canvas. Assumes that the canvas has been rotated
     * accordingly and the size has been set. The effect will be drawn the full
     * width of X=0 to X=width, beginning from Y=0 and extending to some factor <
     * 1.f of height.
     *
     * @param canvas Canvas to draw into
     * @return true if drawing should continue beyond this frame to continue the
     *         animation
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:30:25.940 -0500", hash_original_method = "F27D97BEDCE0E3BB57468FC6E19E8F78", hash_generated_method = "4AC54B9402416493AE1941352F872991")
    
public boolean draw(Canvas canvas) {
        return IMPL.draw(mEdgeEffect, canvas);
    }
    static {
        if (Build.VERSION.SDK_INT >= 14) { 
            IMPL = new EdgeEffectIcsImpl();
        } else {
            IMPL = new BaseEdgeEffectImpl();
        }
    }
    
}

