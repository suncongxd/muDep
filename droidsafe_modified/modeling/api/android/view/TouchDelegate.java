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


package android.view;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.graphics.Rect;


public class TouchDelegate {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.569 -0500", hash_original_field = "28E9F52594733C7FCF89C39503BA42C2", hash_generated_field = "EC534F0636A3F6D8330DE66D6EE19BB7")

    public static final int ABOVE = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.572 -0500", hash_original_field = "AC27EDB7DEF8A2CEC1AD63BDF9AF70E6", hash_generated_field = "8B3CF69EA678FF029552A7CA7A611E81")

    public static final int BELOW = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.576 -0500", hash_original_field = "A78ED0AFA93C6202C6DF19BCD75CFFAB", hash_generated_field = "0291C2D8DA8DB1E6E814FD61549F32BA")

    public static final int TO_LEFT = 4;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.578 -0500", hash_original_field = "E02B296ABE2686A52E3CE3F3F9A549B8", hash_generated_field = "58292FF463EC92D00FCAFB3D70F02B81")

    public static final int TO_RIGHT = 8;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.561 -0500", hash_original_field = "82C707B47F2948F623B224540262BE97", hash_generated_field = "667AB02B0815B77F40A4B676D819C645")

    private View mDelegateView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.563 -0500", hash_original_field = "9B5975BB58D6180DFB202E80016B49DF", hash_generated_field = "ACA7F56CF4C5BB576D45AFE6705B72F4")

    private Rect mBounds;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.565 -0500", hash_original_field = "6A5DB79D948B9F561CA8DE935C5A2BD9", hash_generated_field = "3CA4A0B9369576539E1F2D69259990C2")

    private Rect mSlopBounds;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.567 -0500", hash_original_field = "D9FFF02D51009988BA364283ECDEC5CC", hash_generated_field = "5421EC5E0E13ED719B8FAF57C794F01B")

    private boolean mDelegateTargeted;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.580 -0500", hash_original_field = "36A23F758F1BAB418A00C8E7530FF272", hash_generated_field = "8F552CDA1150C95F5136931C6E8ECB4C")


    private int mSlop;

    /**
     * Constructor
     * 
     * @param bounds Bounds in local coordinates of the containing view that should be mapped to
     *        the delegate view
     * @param delegateView The view that should receive motion events
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.583 -0500", hash_original_method = "2AE60462A7E1E4F15EA63DC7A4583FF5", hash_generated_method = "E6D00652EDB59A3878C0903C2870BA6F")
    
public TouchDelegate(Rect bounds, View delegateView) {
        mBounds = bounds;

        mSlop = ViewConfiguration.get(delegateView.getContext()).getScaledTouchSlop();
        mSlopBounds = new Rect(bounds);
        mSlopBounds.inset(-mSlop, -mSlop);
        mDelegateView = delegateView;
    }

    /**
     * Will forward touch events to the delegate view if the event is within the bounds
     * specified in the constructor.
     * 
     * @param event The touch event to forward
     * @return True if the event was forwarded to the delegate, false otherwise.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:29:27.586 -0500", hash_original_method = "2299D41C6782643B71D7B9FD20834200", hash_generated_method = "BBB5A34988BFA93FB1A2CEA7EB8C8DB2")
    
public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        boolean sendToDelegate = false;
        boolean hit = true;
        boolean handled = false;

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            Rect bounds = mBounds;

            if (bounds.contains(x, y)) {
                mDelegateTargeted = true;
                sendToDelegate = true;
            }
            break;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_MOVE:
            sendToDelegate = mDelegateTargeted;
            if (sendToDelegate) {
                Rect slopBounds = mSlopBounds;
                if (!slopBounds.contains(x, y)) {
                    hit = false;
                }
            }
            break;
        case MotionEvent.ACTION_CANCEL:
            sendToDelegate = mDelegateTargeted;
            mDelegateTargeted = false;
            break;
        }
        if (sendToDelegate) {
            final View delegateView = mDelegateView;
            
            if (hit) {
                // Offset event coordinates to be inside the target view
                event.setLocation(delegateView.getWidth() / 2, delegateView.getHeight() / 2);
            } else {
                // Offset event coordinates to be outside the target view (in case it does
                // something like tracking pressed state)
                int slop = mSlop;
                event.setLocation(-(slop * 2), -(slop * 2));
            }
            handled = delegateView.dispatchTouchEvent(event);
        }
        return handled;
    }
}

