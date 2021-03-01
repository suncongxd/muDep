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


package android.support.v4.widget;

import android.view.ViewGroup.LayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/**
 * DrawerLayout acts as a top-level container for window content that allows for
 * interactive "drawer" views to be pulled out from the edge of the window.
 *
 * <p>Drawer positioning and layout is controlled using the <code>android:layout_gravity</code>
 * attribute on child views corresponding to which side of the view you want the drawer
 * to emerge from: left or right. (Or start/end on platform versions that support layout direction.)
 * </p>
 *
 * <p>To use a DrawerLayout, position your primary content view as the first child with
 * a width and height of <code>match_parent</code>. Add drawers as child views after the main
 * content view and set the <code>layout_gravity</code> appropriately. Drawers commonly use
 * <code>match_parent</code> for height with a fixed width.</p>
 *
 * <p>{@link DrawerListener} can be used to monitor the state and motion of drawer views.
 * Avoid performing expensive operations such as layout during animation as it can cause
 * stuttering; try to perform expensive operations during the {@link #STATE_IDLE} state.
 * {@link SimpleDrawerListener} offers default/no-op implementations of each callback method.</p>
 *
 * <p>As per the Android Design guide, any drawers positioned to the left/start should
 * always contain content for navigating around the application, whereas any drawers
 * positioned to the right/end should always contain actions to take on the current content.
 * This preserves the same navigation left, actions right structure present in the Action Bar
 * and elsewhere.</p>
 */
public class DrawerLayout extends ViewGroup {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.848 -0400", hash_original_field = "26F24006FF473996891598D7D8B92425", hash_generated_field = "7FA334F381851FF71FE3B38370FF3E6C")

    private static final String TAG = "DrawerLayout";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.858 -0400", hash_original_field = "A29CE8D544B7C51FFDE6A2AF3D4CB2B2", hash_generated_field = "A26F775F2AB3654FC9BDE03DAB2438E1")

    public static final int STATE_IDLE = ViewDragHelper.STATE_IDLE;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.866 -0400", hash_original_field = "0DF4612644BC3B111022B66A7FA18D5B", hash_generated_field = "8398F8B2940429167720DC6812459992")

    public static final int STATE_DRAGGING = ViewDragHelper.STATE_DRAGGING;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.874 -0400", hash_original_field = "A23EB2C2D917A976BE597337C8594027", hash_generated_field = "F7029BE4AE98874FC81C1676B4F96107")

    public static final int STATE_SETTLING = ViewDragHelper.STATE_SETTLING;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.881 -0400", hash_original_field = "15F935A372B4286574955C4CB9D3A6A6", hash_generated_field = "02E473856873F90185352E7EAE1432AA")

    public static final int LOCK_MODE_UNLOCKED = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.899 -0400", hash_original_field = "420D1798A1903A34543387437CFE51B6", hash_generated_field = "638C66BA7E8CBEE4BD9E07AE04F471BC")

    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.907 -0400", hash_original_field = "10236BB92438999572AAAC7F075B7D77", hash_generated_field = "797B58E1EE3739B8813100AAA56CA6B4")

    public static final int LOCK_MODE_LOCKED_OPEN = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.913 -0400", hash_original_field = "C4525F63C33119DE8815FD035945D8A8", hash_generated_field = "EFA579D956F9E54E3804DD9FABCBA10B")

    private static final int MIN_DRAWER_MARGIN = 64;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.921 -0400", hash_original_field = "1383E2E9A7590617142B3276BD6C384C", hash_generated_field = "65694A7A49CD35227C67BA4B9664E585")

    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.927 -0400", hash_original_field = "F08E061B904B7BE493D4DEF19958B026", hash_generated_field = "99BAA164352FBB897491DFD5AB9C67F1")

    private static final int PEEK_DELAY = 160;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.935 -0400", hash_original_field = "C54670DE93B24E059BE5F717E4D09C60", hash_generated_field = "798848595FEDCE7CE015C8667D81E2AC")

    /**
     * Minimum velocity that will be detected as a fling
     */
    private static final int MIN_FLING_VELOCITY = 400;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.943 -0400", hash_original_field = "03EE9A61673E037863AF40E830F8E8FD", hash_generated_field = "B8E89A343B7E2A1C58C80DEFBAC5F8D7")

    /**
     * Experimental feature.
     */
    private static final boolean ALLOW_EDGE_LOCK = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.949 -0400", hash_original_field = "B5A16B66A7CB847748A1C45918AD5C32", hash_generated_field = "6872CBDEB439EC8E7E57030805F9525B")

    private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.957 -0400", hash_original_field = "49A5886B0317422461E17F536A7F26C0", hash_generated_field = "269ACF60B358B7EF3AB19C568D168FF5")

    private static final float TOUCH_SLOP_SENSITIVITY = 1.f;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.966 -0400", hash_original_field = "6DBF438C7FAC0EBB2AFE33EF164ECAF2", hash_generated_field = "5ADDA5389845FF3454B51DCFE6C8CFF0")

    private static final int[] LAYOUT_ATTRS = new int[] {
            android.R.attr.layout_gravity
    };

    /**
     * Simple gravity to string - only supports LEFT and RIGHT for debugging output.
     *
     * @param gravity Absolute gravity value
     * @return LEFT or RIGHT as appropriate, or a hex string
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.009 -0400", hash_original_method = "806409CA94403904BCAE7E49635B0868", hash_generated_method = "FE8C1E45DEFAF6D13F32B5972901D9CE")
        
static String gravityToString(@EdgeGravity int gravity) {
        if ((gravity & Gravity.LEFT) == Gravity.LEFT) {
            return "LEFT";
        }
        if ((gravity & Gravity.RIGHT) == Gravity.RIGHT) {
            return "RIGHT";
        }
        return Integer.toHexString(gravity);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.373 -0400", hash_original_method = "2359666BAEDC03BC980B4E985AEDDA13", hash_generated_method = "1CA67C3510657ADC3ACFF92F5280576F")
        
private static boolean hasOpaqueBackground(View v) {
        final Drawable bg = v.getBackground();
        if (bg != null) {
            return bg.getOpacity() == PixelFormat.OPAQUE;
        }
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.248 -0400", hash_original_method = "1D2E68F16D483D47ED2BC556B1D9D8B3", hash_generated_method = "B83F5284E24BD195BDF99C47541B78D2")
        
private static boolean includeChildForAccessibilitiy(View child) {
        // If the child is not important for accessibility we make
        // sure this hides the entire subtree rooted at it as the
        // IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDATS is not
        // supported on older platforms but we want to hide the entire
        // content and not opened drawers if a drawer is opened.
        return ViewCompat.getImportantForAccessibility(child)
                != ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
                    && ViewCompat.getImportantForAccessibility(child)
                != ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO;
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.971 -0400", hash_original_field = "3FF5CAD773906785D5D0A8AE26E1021A", hash_generated_field = "4BA3728C37BDE948C0CA9B9419DBA8C6")

    private int mMinDrawerMargin;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.977 -0400", hash_original_field = "A190AA484522E7E835A12BC57FBE82A0", hash_generated_field = "DF66647C7B76C7FD7C950A61F76AC024")

    private int mScrimColor = DEFAULT_SCRIM_COLOR;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.984 -0400", hash_original_field = "52642FB6B15E5604DF4912156CEDDEEE", hash_generated_field = "BF635F4EBD5C65CD2D21D1520A8135BB")

    private float mScrimOpacity;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:51.995 -0400", hash_original_field = "2411B761592BCFBD2FD5AAA6E816507A", hash_generated_field = "463F724B6057A7D38DA3BD9EB74BD482")

    private Paint mScrimPaint = new Paint();
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.005 -0400", hash_original_field = "4E52AF123E8E3FAB90F30BB9969914B3", hash_generated_field = "7F990524113E3CDE4648A8AB98B25D73")

    private  ViewDragHelper mLeftDragger;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.011 -0400", hash_original_field = "DA39D5DF468EE3091842B42400F84113", hash_generated_field = "9F54163BFA766D57408811203B94BA7E")

    private  ViewDragHelper mRightDragger;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.017 -0400", hash_original_field = "DD6B38D9AA592C9E21A6C257E0253285", hash_generated_field = "9D8A4124645FDA944507287D0B94E798")

    private  ViewDragCallback mLeftCallback;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.024 -0400", hash_original_field = "73EC7168A3C328759BD3537023A666BC", hash_generated_field = "0747209CA21DBC0E15EDEFCE5426ECD6")

    private  ViewDragCallback mRightCallback;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.030 -0400", hash_original_field = "44217368283294308BBDD5EE1BB45ABC", hash_generated_field = "44059380F742255A7A4BA5C982E7CC43")

    private int mDrawerState;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.036 -0400", hash_original_field = "C7463435817ABA85E5AF0081505BD656", hash_generated_field = "AC8E671E2AA7EEF3EC800FAEB4B3A880")

    private boolean mInLayout;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.042 -0400", hash_original_field = "E8FBC0675E7594A5CB40D0BCA74175A7", hash_generated_field = "D2103C384BE0AB58542D96A0CD23C256")

    private boolean mFirstLayout = true;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.048 -0400", hash_original_field = "7EC9FC00AFB46D300253FDAB708C7497", hash_generated_field = "BA69DE8D6D0D81A59132E7CE552FD383")

    private int mLockModeLeft;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.054 -0400", hash_original_field = "4205A40278444713F808FAF6A150111C", hash_generated_field = "A59BF1A816AC589586D64F9B7FE985FE")

    private int mLockModeRight;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.060 -0400", hash_original_field = "66137F34DC933EEA7042011B5891B32F", hash_generated_field = "B320702ADC26DF61B8E7F8359C5FA94A")

    private boolean mDisallowInterceptRequested;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.066 -0400", hash_original_field = "41CB1FC25C86E8A3C11A9F7B4095A61F", hash_generated_field = "B06AB8299DC88FB44F4650ADD918DC82")

    private boolean mChildrenCanceledTouch;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.072 -0400", hash_original_field = "6045260F58BF0F04D7107F057203CA42", hash_generated_field = "748505D0C30305A2F9879DE9738D9BF7")

    private DrawerListener mListener;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.078 -0400", hash_original_field = "25CFDFB463A965BC44C22C5B603E6082", hash_generated_field = "E8D995D721D911B655A8BA1B8E85A671")

    private float mInitialMotionX;

    /**
     * Listener for monitoring events about drawers.
     */
    public interface DrawerListener {
        /**
         * Called when a drawer's position changes.
         * @param drawerView The child view that was moved
         * @param slideOffset The new offset of this drawer within its range, from 0-1
         */
        public void onDrawerSlide(View drawerView, float slideOffset);

        /**
         * Called when a drawer has settled in a completely open state.
         * The drawer is interactive at this point.
         *
         * @param drawerView Drawer view that is now open
         */
        public void onDrawerOpened(View drawerView);

        /**
         * Called when a drawer has settled in a completely closed state.
         *
         * @param drawerView Drawer view that is now closed
         */
        public void onDrawerClosed(View drawerView);

        /**
         * Called when the drawer motion state changes. The new state will
         * be one of {@link #STATE_IDLE}, {@link #STATE_DRAGGING} or {@link #STATE_SETTLING}.
         *
         * @param newState The new drawer motion state
         */
        public void onDrawerStateChanged(int newState);
    }

    /**
     * Stub/no-op implementations of all methods of {@link DrawerListener}.
     * Override this if you only care about a few of the available callback methods.
     */
    public static abstract class SimpleDrawerListener implements DrawerListener {
        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onDrawerClosed(View drawerView) {
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onDrawerStateChanged(int newState) {
        }
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.084 -0400", hash_original_field = "8405CC958EF7FF178B766BCBE27E1F2F", hash_generated_field = "123A2D63D23BC6C888D43FC39BEED8A4")

    private float mInitialMotionY;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.090 -0400", hash_original_field = "982376DECD91626980B09C9014399020", hash_generated_field = "C6A3FFA52473D5C96365E57A517D57B8")

    private Drawable mShadowLeft;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.095 -0400", hash_original_field = "D8AA775126BBC79AA5D16DAB49C4DE98", hash_generated_field = "58285E82F6A6F525C0A20338457FE90B")

    private Drawable mShadowRight;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.776 -0400", hash_original_field = "1E829CD7CB13AF5E19827C6C8A60FF70", hash_generated_field = "00B3DC018443434BED0AE4864D216548")

    private CharSequence mTitleRight;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.770 -0400", hash_original_field = "18D6D82A5182BC38F3F0C4363DCD4A26", hash_generated_field = "33042855B157A6DC102D9BD76B853043")

    private CharSequence mTitleLeft;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.635 -0400", hash_original_field = "C0FC9929241AD62CBC01F8435BC779C6", hash_generated_field = "BE93247AA10D563209847937B9C9A46F")

    private final ChildAccessibilityDelegate mChildAccessibilityDelegate =
            new ChildAccessibilityDelegate();

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.151 -0400", hash_original_method = "4A2A854D70AC2F56620169AC708697D2", hash_generated_method = "5BAE0BAC5011672B3A4F312F323D32CF")
        
public DrawerLayout(Context context) {
        this(context, null);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.157 -0400", hash_original_method = "DE3C43165D25E1B0CD4CB1CCCDB0A62F", hash_generated_method = "403383FB99B27844DE1678E5689B5191")
        
public DrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.851 -0400", hash_original_method = "48B469E706116A849FC828EB3BD46B14", hash_generated_method = "E7E6CD8149BEDD12108DF999D7A3CA00")
        
public DrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final float density = getResources().getDisplayMetrics().density;
        mMinDrawerMargin = (int) (MIN_DRAWER_MARGIN * density + 0.5f);
        final float minVel = MIN_FLING_VELOCITY * density;

        mLeftCallback = new ViewDragCallback(Gravity.LEFT);
        mRightCallback = new ViewDragCallback(Gravity.RIGHT);

        mLeftDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, mLeftCallback);
        mLeftDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        mLeftDragger.setMinVelocity(minVel);
        mLeftCallback.setDragger(mLeftDragger);

        mRightDragger = ViewDragHelper.create(this, TOUCH_SLOP_SENSITIVITY, mRightCallback);
        mRightDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
        mRightDragger.setMinVelocity(minVel);
        mRightCallback.setDragger(mRightDragger);

        // So that we can catch the back button
        setFocusableInTouchMode(true);

        ViewCompat.setImportantForAccessibility(this,
                ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);

        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
    }

    /**
     * Set a simple drawable used for the left or right shadow.
     * The drawable provided must have a nonzero intrinsic width.
     *
     * @param shadowDrawable Shadow drawable to use at the edge of a drawer
     * @param gravity Which drawer the shadow should apply to
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.860 -0400", hash_original_method = "3AA3C4510F0B528EEF7C3C0CFC6F19FE", hash_generated_method = "B5D3E4CE4144F144DD11D5413D36CD08")
        
public void setDrawerShadow(Drawable shadowDrawable, @EdgeGravity int gravity) {
        /*
         * TODO Someone someday might want to set more complex drawables here.
         * They're probably nuts, but we might want to consider registering callbacks,
         * setting states, etc. properly.
         */

        final int absGravity = GravityCompat.getAbsoluteGravity(gravity,
                ViewCompat.getLayoutDirection(this));
        if ((absGravity & Gravity.LEFT) == Gravity.LEFT) {
            mShadowLeft = shadowDrawable;
            invalidate();
        }
        if ((absGravity & Gravity.RIGHT) == Gravity.RIGHT) {
            mShadowRight = shadowDrawable;
            invalidate();
        }
    }

    /**
     * Set a simple drawable used for the left or right shadow.
     * The drawable provided must have a nonzero intrinsic width.
     *
     * @param resId Resource id of a shadow drawable to use at the edge of a drawer
     * @param gravity Which drawer the shadow should apply to
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.868 -0400", hash_original_method = "406E2F555730FA23B50427ED69F7F601", hash_generated_method = "D8CC6E6C56167162147D896BCC24014C")
        
public void setDrawerShadow(int resId, @EdgeGravity int gravity) {
        setDrawerShadow(getResources().getDrawable(resId), gravity);
    }

    /**
     * Set a color to use for the scrim that obscures primary content while a drawer is open.
     *
     * @param color Color to use in 0xAARRGGBB format.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.193 -0400", hash_original_method = "D562A42FCDFB612182A94C1E7FCC12E1", hash_generated_method = "14F12FDB9BDF66791B2395CC92F52BAE")
        
public void setScrimColor(int color) {
        mScrimColor = color;
        invalidate();
    }

    /**
     * Set a listener to be notified of drawer events.
     *
     * @param listener Listener to notify when drawer events occur
     * @see DrawerListener
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.199 -0400", hash_original_method = "200DC2D828FE2FB0F55BB3AA90BC23E8", hash_generated_method = "B1D7A8348C8CFD8F1C43CAD21F3CD6EA")
        
public void setDrawerListener(DrawerListener listener) {
        mListener = listener;
    }

    /**
     * Enable or disable interaction with all drawers.
     *
     * <p>This allows the application to restrict the user's ability to open or close
     * any drawer within this layout. DrawerLayout will still respond to calls to
     * {@link #openDrawer(int)}, {@link #closeDrawer(int)} and friends if a drawer is locked.</p>
     *
     * <p>Locking drawers open or closed will implicitly open or close
     * any drawers as appropriate.</p>
     *
     * @param lockMode The new lock mode for the given drawer. One of {@link #LOCK_MODE_UNLOCKED},
     *                 {@link #LOCK_MODE_LOCKED_CLOSED} or {@link #LOCK_MODE_LOCKED_OPEN}.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.886 -0400", hash_original_method = "B3E0D029EEFC61440338FF42B1227314", hash_generated_method = "C0E7689C2F7B915D9296C9D0937C5547")
        
public void setDrawerLockMode(int lockMode) {
        setDrawerLockMode(lockMode, Gravity.LEFT);
        setDrawerLockMode(lockMode, Gravity.RIGHT);
    }

    /**
     * Enable or disable interaction with the given drawer.
     *
     * <p>This allows the application to restrict the user's ability to open or close
     * the given drawer. DrawerLayout will still respond to calls to {@link #openDrawer(int)},
     * {@link #closeDrawer(int)} and friends if a drawer is locked.</p>
     *
     * <p>Locking a drawer open or closed will implicitly open or close
     * that drawer as appropriate.</p>
     *
     * @param lockMode The new lock mode for the given drawer. One of {@link #LOCK_MODE_UNLOCKED},
     *                 {@link #LOCK_MODE_LOCKED_CLOSED} or {@link #LOCK_MODE_LOCKED_OPEN}.
     * @param edgeGravity Gravity.LEFT, RIGHT, START or END.
     *                    Expresses which drawer to change the mode for.
     *
     * @see #LOCK_MODE_UNLOCKED
     * @see #LOCK_MODE_LOCKED_CLOSED
     * @see #LOCK_MODE_LOCKED_OPEN
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.898 -0400", hash_original_method = "94EB8A3AFA48EF715CCFDDCD1AB0FEFE", hash_generated_method = "82B1DA6BA869DF32035DEC219EDEDB4C")
        
public void setDrawerLockMode(int lockMode, @EdgeGravity int edgeGravity) {
        final int absGravity = GravityCompat.getAbsoluteGravity(edgeGravity,
                ViewCompat.getLayoutDirection(this));
        if (absGravity == Gravity.LEFT) {
            mLockModeLeft = lockMode;
        } else if (absGravity == Gravity.RIGHT) {
            mLockModeRight = lockMode;
        }
        if (lockMode != LOCK_MODE_UNLOCKED) {
            // Cancel interaction in progress
            final ViewDragHelper helper = absGravity == Gravity.LEFT ? mLeftDragger : mRightDragger;
            helper.cancel();
        }
        switch (lockMode) {
            case LOCK_MODE_LOCKED_OPEN:
                final View toOpen = findDrawerWithGravity(absGravity);
                if (toOpen != null) {
                    openDrawer(toOpen);
                }
                break;
            case LOCK_MODE_LOCKED_CLOSED:
                final View toClose = findDrawerWithGravity(absGravity);
                if (toClose != null) {
                    closeDrawer(toClose);
                }
                break;
            // default: do nothing
        }
    }

    /**
     * Enable or disable interaction with the given drawer.
     *
     * <p>This allows the application to restrict the user's ability to open or close
     * the given drawer. DrawerLayout will still respond to calls to {@link #openDrawer(int)},
     * {@link #closeDrawer(int)} and friends if a drawer is locked.</p>
     *
     * <p>Locking a drawer open or closed will implicitly open or close
     * that drawer as appropriate.</p>
     *
     * @param lockMode The new lock mode for the given drawer. One of {@link #LOCK_MODE_UNLOCKED},
     *                 {@link #LOCK_MODE_LOCKED_CLOSED} or {@link #LOCK_MODE_LOCKED_OPEN}.
     * @param drawerView The drawer view to change the lock mode for
     *
     * @see #LOCK_MODE_UNLOCKED
     * @see #LOCK_MODE_LOCKED_CLOSED
     * @see #LOCK_MODE_LOCKED_OPEN
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.906 -0400", hash_original_method = "B2E81D7C4F5D5432DDB979EFC6A2D55F", hash_generated_method = "65C24BD5CA4F5D28916CAC5839DB6E41")
        
public void setDrawerLockMode(int lockMode, View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a " +
                    "drawer with appropriate layout_gravity");
        }
        final int gravity = ((LayoutParams) drawerView.getLayoutParams()).gravity;
        setDrawerLockMode(lockMode, gravity);
    }

    /**
     * Check the lock mode of the drawer with the given gravity.
     *
     * @param edgeGravity Gravity of the drawer to check
     * @return one of {@link #LOCK_MODE_UNLOCKED}, {@link #LOCK_MODE_LOCKED_CLOSED} or
     *         {@link #LOCK_MODE_LOCKED_OPEN}.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.914 -0400", hash_original_method = "56D29209219CC1D29C3EE2DAE33ED4E5", hash_generated_method = "246CD12D3D6888B80ABDB4EBF83DCA89")
        
public int getDrawerLockMode(@EdgeGravity int edgeGravity) {
        final int absGravity = GravityCompat.getAbsoluteGravity(
                edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absGravity == Gravity.LEFT) {
            return mLockModeLeft;
        } else if (absGravity == Gravity.RIGHT) {
            return mLockModeRight;
        }
        return LOCK_MODE_UNLOCKED;
    }

    /**
     * Check the lock mode of the given drawer view.
     *
     * @param drawerView Drawer view to check lock mode
     * @return one of {@link #LOCK_MODE_UNLOCKED}, {@link #LOCK_MODE_LOCKED_CLOSED} or
     *         {@link #LOCK_MODE_LOCKED_OPEN}.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.921 -0400", hash_original_method = "117D2A957267B5D8B458287919539AC3", hash_generated_method = "119AB4E7C91C4218BF09810E727FD41A")
        
public int getDrawerLockMode(View drawerView) {
        final int absGravity = getDrawerViewAbsoluteGravity(drawerView);
        if (absGravity == Gravity.LEFT) {
            return mLockModeLeft;
        } else if (absGravity == Gravity.RIGHT) {
            return mLockModeRight;
        }
        return LOCK_MODE_UNLOCKED;
    }

    /**
     * Resolve the shared state of all drawers from the component ViewDragHelpers.
     * Should be called whenever a ViewDragHelper's state changes.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.942 -0400", hash_original_method = "96881A3CC271E47044D9DE139130AB25", hash_generated_method = "96881A3CC271E47044D9DE139130AB25")
        
void updateDrawerState(int forGravity, int activeState, View activeDrawer) {
        final int leftState = mLeftDragger.getViewDragState();
        final int rightState = mRightDragger.getViewDragState();

        final int state;
        if (leftState == STATE_DRAGGING || rightState == STATE_DRAGGING) {
            state = STATE_DRAGGING;
        } else if (leftState == STATE_SETTLING || rightState == STATE_SETTLING) {
            state = STATE_SETTLING;
        } else {
            state = STATE_IDLE;
        }

        if (activeDrawer != null && activeState == STATE_IDLE) {
            final LayoutParams lp = (LayoutParams) activeDrawer.getLayoutParams();
            if (lp.onScreen == 0) {
                dispatchOnDrawerClosed(activeDrawer);
            } else if (lp.onScreen == 1) {
                dispatchOnDrawerOpened(activeDrawer);
            }
        }

        if (state != mDrawerState) {
            mDrawerState = state;

            if (mListener != null) {
                mListener.onDrawerStateChanged(state);
            }
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.949 -0400", hash_original_method = "5C6986B225D608EFC8113E274B77629E", hash_generated_method = "4DD9732122465140B1A715C938914AFD")
        
void dispatchOnDrawerClosed(View drawerView) {
        final LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (lp.knownOpen) {
            lp.knownOpen = false;
            if (mListener != null) {
                mListener.onDrawerClosed(drawerView);
            }

            // If no drawer is opened, all drawers are not shown
            // for accessibility and the content is shown.
            View content = getChildAt(0);
            if (content != null) {
                ViewCompat.setImportantForAccessibility(content,
                        ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
            }
            ViewCompat.setImportantForAccessibility(drawerView,
                            ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);

            // Only send WINDOW_STATE_CHANGE if the host has window focus. This
            // may change if support for multiple foreground windows (e.g. IME)
            // improves.
            if (hasWindowFocus()) {
                final View rootView = getRootView();
                if (rootView != null) {
                    rootView.sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
                }
            }
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.955 -0400", hash_original_method = "D464C7782235B554E1AF5BA5C2A5550C", hash_generated_method = "556ADAA58ED219802F731D92FF5A6EE3")
        
void dispatchOnDrawerOpened(View drawerView) {
        final LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (!lp.knownOpen) {
            lp.knownOpen = true;
            if (mListener != null) {
                mListener.onDrawerOpened(drawerView);
            }

            // If a drawer is opened, only it is shown for
            // accessibility and the content is not shown.
            View content = getChildAt(0);
            if (content != null) {
                ViewCompat.setImportantForAccessibility(content,
                        ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
            }
            ViewCompat.setImportantForAccessibility(drawerView,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);

            sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
            drawerView.requestFocus();
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.270 -0400", hash_original_method = "140C5B34D506C079860F7F11508D96C5", hash_generated_method = "140C5B34D506C079860F7F11508D96C5")
        
void dispatchOnDrawerSlide(View drawerView, float slideOffset) {
        if (mListener != null) {
            mListener.onDrawerSlide(drawerView, slideOffset);
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.276 -0400", hash_original_method = "BAC3676B11CBEF43765A167CD87E3D85", hash_generated_method = "BAC3676B11CBEF43765A167CD87E3D85")
        
void setDrawerViewOffset(View drawerView, float slideOffset) {
        final LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
        if (slideOffset == lp.onScreen) {
            return;
        }

        lp.onScreen = slideOffset;
        dispatchOnDrawerSlide(drawerView, slideOffset);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.282 -0400", hash_original_method = "2C2A1CC62A04C86B48FC0026BEF9751C", hash_generated_method = "2C2A1CC62A04C86B48FC0026BEF9751C")
        
float getDrawerViewOffset(View drawerView) {
        return ((LayoutParams) drawerView.getLayoutParams()).onScreen;
    }

    /**
     * @return the absolute gravity of the child drawerView, resolved according
     *         to the current layout direction
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.287 -0400", hash_original_method = "D09FFB156570AA317329474871F55D61", hash_generated_method = "D09FFB156570AA317329474871F55D61")
        
int getDrawerViewAbsoluteGravity(View drawerView) {
        final int gravity = ((LayoutParams) drawerView.getLayoutParams()).gravity;
        return GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this));
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.293 -0400", hash_original_method = "D67722EB08FA196DA7EC38F1040AD3E7", hash_generated_method = "D67722EB08FA196DA7EC38F1040AD3E7")
        
boolean checkDrawerViewAbsoluteGravity(View drawerView, int checkFor) {
        final int absGravity = getDrawerViewAbsoluteGravity(drawerView);
        return (absGravity & checkFor) == checkFor;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.298 -0400", hash_original_method = "A59E037FB19E440569F6237E64730038", hash_generated_method = "A59E037FB19E440569F6237E64730038")
        
View findOpenDrawer() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (((LayoutParams) child.getLayoutParams()).knownOpen) {
                return child;
            }
        }
        return null;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.305 -0400", hash_original_method = "88B6EDF7939B36F098388453CE0BD529", hash_generated_method = "88B6EDF7939B36F098388453CE0BD529")
        
void moveDrawerToOffset(View drawerView, float slideOffset) {
        final float oldOffset = getDrawerViewOffset(drawerView);
        final int width = drawerView.getWidth();
        final int oldPos = (int) (width * oldOffset);
        final int newPos = (int) (width * slideOffset);
        final int dx = newPos - oldPos;

        drawerView.offsetLeftAndRight(
                checkDrawerViewAbsoluteGravity(drawerView, Gravity.LEFT) ? dx : -dx);
        setDrawerViewOffset(drawerView, slideOffset);
    }

    /**
     * @param gravity the gravity of the child to return. If specified as a
     *            relative value, it will be resolved according to the current
     *            layout direction.
     * @return the drawer with the specified gravity
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.311 -0400", hash_original_method = "CCED97483D8521C15A95C0F29507D2A1", hash_generated_method = "CCED97483D8521C15A95C0F29507D2A1")
        
View findDrawerWithGravity(int gravity) {
        final int absHorizGravity = GravityCompat.getAbsoluteGravity(
                gravity, ViewCompat.getLayoutDirection(this)) & Gravity.HORIZONTAL_GRAVITY_MASK;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final int childAbsGravity = getDrawerViewAbsoluteGravity(child);
            if ((childAbsGravity & Gravity.HORIZONTAL_GRAVITY_MASK) == absHorizGravity) {
                return child;
            }
        }
        return null;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.322 -0400", hash_original_method = "FF6C646BC7EB39EC914F2B8FB0B795B6", hash_generated_method = "8F25DB40F9F3610D849FD225B38F4A0F")
        
@Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mFirstLayout = true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.330 -0400", hash_original_method = "55E78F1E2A11C365755D77C284D387A2", hash_generated_method = "F4968E15513E07145315A993A80462B8")
        
@Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mFirstLayout = true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.342 -0400", hash_original_method = "EC0B4B8DEDBA907E373A9D556B6C3D52", hash_generated_method = "F829599756E7045A4DC663FE20937F40")
        
@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            if (isInEditMode()) {
                // Don't crash the layout editor. Consume all of the space if specified
                // or pick a magic number from thin air otherwise.
                // TODO Better communication with tools of this bogus state.
                // It will crash on a real device.
                if (widthMode == MeasureSpec.AT_MOST) {
                    widthMode = MeasureSpec.EXACTLY;
                } else if (widthMode == MeasureSpec.UNSPECIFIED) {
                    widthMode = MeasureSpec.EXACTLY;
                    widthSize = 300;
                }
                if (heightMode == MeasureSpec.AT_MOST) {
                    heightMode = MeasureSpec.EXACTLY;
                }
                else if (heightMode == MeasureSpec.UNSPECIFIED) {
                    heightMode = MeasureSpec.EXACTLY;
                    heightSize = 300;
                }
            } else {
                throw new IllegalArgumentException(
                        "DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }

        setMeasuredDimension(widthSize, heightSize);

        // Gravity value for each drawer we've seen. Only one of each permitted.
        int foundDrawers = 0;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isContentView(child)) {
                // Content views get measured at exactly the layout's size.
                final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                        widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);
                final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                        heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);
                child.measure(contentWidthSpec, contentHeightSpec);
            } else if (isDrawerView(child)) {
                final int childGravity =
                        getDrawerViewAbsoluteGravity(child) & Gravity.HORIZONTAL_GRAVITY_MASK;
                if ((foundDrawers & childGravity) != 0) {
                    throw new IllegalStateException("Child drawer has absolute gravity " +
                            gravityToString(childGravity) + " but this " + TAG + " already has a " +
                            "drawer view along that edge");
                }
                final int drawerWidthSpec = getChildMeasureSpec(widthMeasureSpec,
                        mMinDrawerMargin + lp.leftMargin + lp.rightMargin,
                        lp.width);
                final int drawerHeightSpec = getChildMeasureSpec(heightMeasureSpec,
                        lp.topMargin + lp.bottomMargin,
                        lp.height);
                child.measure(drawerWidthSpec, drawerHeightSpec);
            } else {
                throw new IllegalStateException("Child " + child + " at index " + i +
                        " does not have a valid layout_gravity - must be Gravity.LEFT, " +
                        "Gravity.RIGHT or Gravity.NO_GRAVITY");
            }
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.355 -0400", hash_original_method = "7EAF8BC8C7F173BAD216397D4E9AB885", hash_generated_method = "A5F48FB56F14E29EF62BEF257CB164D0")
        
@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mInLayout = true;
        final int width = r - l;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isContentView(child)) {
                child.layout(lp.leftMargin, lp.topMargin,
                        lp.leftMargin + child.getMeasuredWidth(),
                        lp.topMargin + child.getMeasuredHeight());
            } else { // Drawer, if it wasn't onMeasure would have thrown an exception.
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();
                int childLeft;

                final float newOffset;
                if (checkDrawerViewAbsoluteGravity(child, Gravity.LEFT)) {
                    childLeft = -childWidth + (int) (childWidth * lp.onScreen);
                    newOffset = (float) (childWidth + childLeft) / childWidth;
                } else { // Right; onMeasure checked for us.
                    childLeft = width - (int) (childWidth * lp.onScreen);
                    newOffset = (float) (width - childLeft) / childWidth;
                }

                final boolean changeOffset = newOffset != lp.onScreen;

                final int vgrav = lp.gravity & Gravity.VERTICAL_GRAVITY_MASK;

                switch (vgrav) {
                    default:
                    case Gravity.TOP: {
                        child.layout(childLeft, lp.topMargin, childLeft + childWidth,
                                lp.topMargin + childHeight);
                        break;
                    }

                    case Gravity.BOTTOM: {
                        final int height = b - t;
                        child.layout(childLeft,
                                height - lp.bottomMargin - child.getMeasuredHeight(),
                                childLeft + childWidth,
                                height - lp.bottomMargin);
                        break;
                    }

                    case Gravity.CENTER_VERTICAL: {
                        final int height = b - t;
                        int childTop = (height - childHeight) / 2;

                        // Offset for margins. If things don't fit right because of
                        // bad measurement before, oh well.
                        if (childTop < lp.topMargin) {
                            childTop = lp.topMargin;
                        } else if (childTop + childHeight > height - lp.bottomMargin) {
                            childTop = height - lp.bottomMargin - childHeight;
                        }
                        child.layout(childLeft, childTop, childLeft + childWidth,
                                childTop + childHeight);
                        break;
                    }
                }

                if (changeOffset) {
                    setDrawerViewOffset(child, newOffset);
                }

                final int newVisibility = lp.onScreen > 0 ? VISIBLE : INVISIBLE;
                if (child.getVisibility() != newVisibility) {
                    child.setVisibility(newVisibility);
                }
            }
        }
        mInLayout = false;
        mFirstLayout = false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.360 -0400", hash_original_method = "389DCFD3441732608176FB26FF97B055", hash_generated_method = "656A427FEB23A1178788DF33DC2C5D5C")
        
@Override
    public void requestLayout() {
        if (!mInLayout) {
            super.requestLayout();
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.366 -0400", hash_original_method = "0CEEA2A39592229598E307CB5440F16F", hash_generated_method = "4568E0C315D0837044E492316E827EB6")
        
@Override
    public void computeScroll() {
        final int childCount = getChildCount();
        float scrimOpacity = 0;
        for (int i = 0; i < childCount; i++) {
            final float onscreen = ((LayoutParams) getChildAt(i).getLayoutParams()).onScreen;
            scrimOpacity = Math.max(scrimOpacity, onscreen);
        }
        mScrimOpacity = scrimOpacity;

        // "|" used on purpose; both need to run.
        if (mLeftDragger.continueSettling(true) | mRightDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.386 -0400", hash_original_method = "B7775A37F7446EF76A38F9728D89A12C", hash_generated_method = "73A5EC339692F60D32169EAD8BDC6C49")
        
@Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        final int height = getHeight();
        final boolean drawingContent = isContentView(child);
        int clipLeft = 0, clipRight = getWidth();

        final int restoreCount = canvas.save();
        if (drawingContent) {
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View v = getChildAt(i);
                if (v == child || v.getVisibility() != VISIBLE ||
                        !hasOpaqueBackground(v) || !isDrawerView(v) ||
                        v.getHeight() < height) {
                    continue;
                }

                if (checkDrawerViewAbsoluteGravity(v, Gravity.LEFT)) {
                    final int vright = v.getRight();
                    if (vright > clipLeft) clipLeft = vright;
                } else {
                    final int vleft = v.getLeft();
                    if (vleft < clipRight) clipRight = vleft;
                }
            }
            canvas.clipRect(clipLeft, 0, clipRight, getHeight());
        }
        final boolean result = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(restoreCount);

        if (mScrimOpacity > 0 && drawingContent) {
            final int baseAlpha = (mScrimColor & 0xff000000) >>> 24;
            final int imag = (int) (baseAlpha * mScrimOpacity);
            final int color = imag << 24 | (mScrimColor & 0xffffff);
            mScrimPaint.setColor(color);

            canvas.drawRect(clipLeft, 0, clipRight, getHeight(), mScrimPaint);
        } else if (mShadowLeft != null && checkDrawerViewAbsoluteGravity(child, Gravity.LEFT)) {
            final int shadowWidth = mShadowLeft.getIntrinsicWidth();
            final int childRight = child.getRight();
            final int drawerPeekDistance = mLeftDragger.getEdgeSize();
            final float alpha =
                    Math.max(0, Math.min((float) childRight / drawerPeekDistance, 1.f));
            mShadowLeft.setBounds(childRight, child.getTop(),
                    childRight + shadowWidth, child.getBottom());
            mShadowLeft.setAlpha((int) (0xff * alpha));
            mShadowLeft.draw(canvas);
        } else if (mShadowRight != null && checkDrawerViewAbsoluteGravity(child, Gravity.RIGHT)) {
            final int shadowWidth = mShadowRight.getIntrinsicWidth();
            final int childLeft = child.getLeft();
            final int showing = getWidth() - childLeft;
            final int drawerPeekDistance = mRightDragger.getEdgeSize();
            final float alpha =
                    Math.max(0, Math.min((float) showing / drawerPeekDistance, 1.f));
            mShadowRight.setBounds(childLeft - shadowWidth, child.getTop(),
                    childLeft, child.getBottom());
            mShadowRight.setAlpha((int) (0xff * alpha));
            mShadowRight.draw(canvas);
        }
        return result;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.392 -0400", hash_original_method = "9CE3D073616FE9E91F0395CAB74719F6", hash_generated_method = "9CE3D073616FE9E91F0395CAB74719F6")
        
boolean isContentView(View child) {
        return ((LayoutParams) child.getLayoutParams()).gravity == Gravity.NO_GRAVITY;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.398 -0400", hash_original_method = "DAB162665F11EC6BE6BDCB87519847D6", hash_generated_method = "DAB162665F11EC6BE6BDCB87519847D6")
        
boolean isDrawerView(View child) {
        final int gravity = ((LayoutParams) child.getLayoutParams()).gravity;
        final int absGravity = GravityCompat.getAbsoluteGravity(gravity,
                ViewCompat.getLayoutDirection(child));
        return (absGravity & (Gravity.LEFT | Gravity.RIGHT)) != 0;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.405 -0400", hash_original_method = "27FCB7A5562688681CC0E7E61262B6BD", hash_generated_method = "425FE6C1D451EBC7D6C4C5EBB6A60553")
        
@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        // "|" used deliberately here; both methods should be invoked.
        final boolean interceptForDrag = mLeftDragger.shouldInterceptTouchEvent(ev) |
                mRightDragger.shouldInterceptTouchEvent(ev);

        boolean interceptForTap = false;

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mInitialMotionX = x;
                mInitialMotionY = y;
                if (mScrimOpacity > 0 &&
                        isContentView(mLeftDragger.findTopChildUnder((int) x, (int) y))) {
                    interceptForTap = true;
                }
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // If we cross the touch slop, don't perform the delayed peek for an edge touch.
                if (mLeftDragger.checkTouchSlop(ViewDragHelper.DIRECTION_ALL)) {
                    mLeftCallback.removeCallbacks();
                    mRightCallback.removeCallbacks();
                }
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                closeDrawers(true);
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
            }
        }

        return interceptForDrag || interceptForTap || hasPeekingDrawer() || mChildrenCanceledTouch;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.412 -0400", hash_original_method = "5E542DFBD5A074F7BC6EF06D97DB02D1", hash_generated_method = "051BD85932CE144799AB44A79963BFFC")
        
@Override
    public boolean onTouchEvent(MotionEvent ev) {
        mLeftDragger.processTouchEvent(ev);
        mRightDragger.processTouchEvent(ev);

        final int action = ev.getAction();
        boolean wantTouchEvents = true;

        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mInitialMotionX = x;
                mInitialMotionY = y;
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break;
            }

            case MotionEvent.ACTION_UP: {
                final float x = ev.getX();
                final float y = ev.getY();
                boolean peekingOnly = true;
                final View touchedView = mLeftDragger.findTopChildUnder((int) x, (int) y);
                if (touchedView != null && isContentView(touchedView)) {
                    final float dx = x - mInitialMotionX;
                    final float dy = y - mInitialMotionY;
                    final int slop = mLeftDragger.getTouchSlop();
                    if (dx * dx + dy * dy < slop * slop) {
                        // Taps close a dimmed open drawer but only if it isn't locked open.
                        final View openDrawer = findOpenDrawer();
                        if (openDrawer != null) {
                            peekingOnly = getDrawerLockMode(openDrawer) == LOCK_MODE_LOCKED_OPEN;
                        }
                    }
                }
                closeDrawers(peekingOnly);
                mDisallowInterceptRequested = false;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                closeDrawers(true);
                mDisallowInterceptRequested = false;
                mChildrenCanceledTouch = false;
                break;
            }
        }

        return wantTouchEvents;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.419 -0400", hash_original_method = "1C3D0D34A620532B0F76575B3905180A", hash_generated_method = "251D116DF88D41372B6EC24AC1AB37A2")
        
public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (CHILDREN_DISALLOW_INTERCEPT ||
                (!mLeftDragger.isEdgeTouched(ViewDragHelper.EDGE_LEFT) &&
                !mRightDragger.isEdgeTouched(ViewDragHelper.EDGE_RIGHT))) {
            // If we have an edge touch we want to skip this and track it for later instead.
            super.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
        mDisallowInterceptRequested = disallowIntercept;
        if (disallowIntercept) {
            closeDrawers(true);
        }
    }

    /**
     * Close all currently open drawer views by animating them out of view.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.423 -0400", hash_original_method = "DB6292B15897C24C66A9066234A7AE38", hash_generated_method = "B0B32C4571D0EED7DBDB97560ED8BC30")
        
public void closeDrawers() {
        closeDrawers(false);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.430 -0400", hash_original_method = "468DC80E189DB6347C6F966C963880DF", hash_generated_method = "468DC80E189DB6347C6F966C963880DF")
        
void closeDrawers(boolean peekingOnly) {
        boolean needsInvalidate = false;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (!isDrawerView(child) || (peekingOnly && !lp.isPeeking)) {
                continue;
            }

            final int childWidth = child.getWidth();

            if (checkDrawerViewAbsoluteGravity(child, Gravity.LEFT)) {
                needsInvalidate |= mLeftDragger.smoothSlideViewTo(child,
                        -childWidth, child.getTop());
            } else {
                needsInvalidate |= mRightDragger.smoothSlideViewTo(child,
                        getWidth(), child.getTop());
            }

            lp.isPeeking = false;
        }

        mLeftCallback.removeCallbacks();
        mRightCallback.removeCallbacks();

        if (needsInvalidate) {
            invalidate();
        }
    }

    /**
     * Open the specified drawer view by animating it into view.
     *
     * @param drawerView Drawer view to open
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.436 -0400", hash_original_method = "B48E0F56B3FA5C3B73D6F1F3B912592C", hash_generated_method = "47B4C5988B4770BE55AD3A1CDABF934F")
        
public void openDrawer(View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
        }

        if (mFirstLayout) {
            final LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
            lp.onScreen = 1.f;
            lp.knownOpen = true;
        } else {
            if (checkDrawerViewAbsoluteGravity(drawerView, Gravity.LEFT)) {
                mLeftDragger.smoothSlideViewTo(drawerView, 0, drawerView.getTop());
            } else {
                mRightDragger.smoothSlideViewTo(drawerView, getWidth() - drawerView.getWidth(),
                        drawerView.getTop());
            }
        }
        invalidate();
    }

    /**
     * Open the specified drawer by animating it out of view.
     *
     * @param gravity Gravity.LEFT to move the left drawer or Gravity.RIGHT for the right.
     *                GravityCompat.START or GravityCompat.END may also be used.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.126 -0400", hash_original_method = "88CAA022F1285CF57E6CD98C82011E5A", hash_generated_method = "AC4B07EC6323C2CD5405C4A8F905AC54")
        
public void openDrawer(@EdgeGravity int gravity) {
        final View drawerView = findDrawerWithGravity(gravity);
        if (drawerView == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " +
                    gravityToString(gravity));
        }
        openDrawer(drawerView);
    }

    /**
     * Close the specified drawer view by animating it into view.
     *
     * @param drawerView Drawer view to close
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.448 -0400", hash_original_method = "75F962D9B52C8023F4C69D7D10233070", hash_generated_method = "51EE5185896A92F76F9C55BA630742D4")
        
public void closeDrawer(View drawerView) {
        if (!isDrawerView(drawerView)) {
            throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
        }

        if (mFirstLayout) {
            final LayoutParams lp = (LayoutParams) drawerView.getLayoutParams();
            lp.onScreen = 0.f;
            lp.knownOpen = false;
        } else {
            if (checkDrawerViewAbsoluteGravity(drawerView, Gravity.LEFT)) {
                mLeftDragger.smoothSlideViewTo(drawerView, -drawerView.getWidth(),
                        drawerView.getTop());
            } else {
                mRightDragger.smoothSlideViewTo(drawerView, getWidth(), drawerView.getTop());
            }
        }
        invalidate();
    }

    /**
     * Close the specified drawer by animating it out of view.
     *
     * @param gravity Gravity.LEFT to move the left drawer or Gravity.RIGHT for the right.
     *                GravityCompat.START or GravityCompat.END may also be used.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.139 -0400", hash_original_method = "663E2609D092ABEB2C3E8BE7EEC66220", hash_generated_method = "9FC5A4318BAA90CBF0C1464D4BC6B569")
        
public void closeDrawer(@EdgeGravity int gravity) {
        final View drawerView = findDrawerWithGravity(gravity);
        if (drawerView == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " +
                    gravityToString(gravity));
        }
        closeDrawer(drawerView);
    }

    /**
     * Check if the given drawer view is currently in an open state.
     * To be considered "open" the drawer must have settled into its fully
     * visible state. To check for partial visibility use
     * {@link #isDrawerVisible(android.view.View)}.
     *
     * @param drawer Drawer view to check
     * @return true if the given drawer view is in an open state
     * @see #isDrawerVisible(android.view.View)
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.461 -0400", hash_original_method = "E509B2226D5CD1DFCE2770531D33BB5C", hash_generated_method = "7864A248E04A3FC736BCB527AFA53624")
        
public boolean isDrawerOpen(View drawer) {
        if (!isDrawerView(drawer)) {
            throw new IllegalArgumentException("View " + drawer + " is not a drawer");
        }
        return ((LayoutParams) drawer.getLayoutParams()).knownOpen;
    }

    /**
     * Check if the given drawer view is currently in an open state.
     * To be considered "open" the drawer must have settled into its fully
     * visible state. If there is no drawer with the given gravity this method
     * will return false.
     *
     * @param drawerGravity Gravity of the drawer to check
     * @return true if the given drawer view is in an open state
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.151 -0400", hash_original_method = "EABA63D32426F723CDAF47E0DF451843", hash_generated_method = "133DC2FD97D6154760177D1E60260B33")
        
public boolean isDrawerOpen(@EdgeGravity int drawerGravity) {
        final View drawerView = findDrawerWithGravity(drawerGravity);
        if (drawerView != null) {
            return isDrawerOpen(drawerView);
        }
        return false;
    }

    /**
     * Check if a given drawer view is currently visible on-screen. The drawer
     * may be only peeking onto the screen, fully extended, or anywhere inbetween.
     *
     * @param drawer Drawer view to check
     * @return true if the given drawer is visible on-screen
     * @see #isDrawerOpen(android.view.View)
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.472 -0400", hash_original_method = "A5844FA3AD051D68E41EF1DB2A92B52F", hash_generated_method = "468B953506E6B82D745E8E135178B775")
        
public boolean isDrawerVisible(View drawer) {
        if (!isDrawerView(drawer)) {
            throw new IllegalArgumentException("View " + drawer + " is not a drawer");
        }
        return ((LayoutParams) drawer.getLayoutParams()).onScreen > 0;
    }

    /**
     * Check if a given drawer view is currently visible on-screen. The drawer
     * may be only peeking onto the screen, fully extended, or anywhere in between.
     * If there is no drawer with the given gravity this method will return false.
     *
     * @param drawerGravity Gravity of the drawer to check
     * @return true if the given drawer is visible on-screen
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.165 -0400", hash_original_method = "D2C18FD3CBFE030BE116683AF1F044D0", hash_generated_method = "A96F07D7974BB2DD05E3E93711326664")
        
public boolean isDrawerVisible(@EdgeGravity int drawerGravity) {
        final View drawerView = findDrawerWithGravity(drawerGravity);
        if (drawerView != null) {
            return isDrawerVisible(drawerView);
        }
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.484 -0400", hash_original_method = "561151D89D845662BE87511D0C2DF167", hash_generated_method = "EA80D3AF818210335F10B87809E37516")
        
private boolean hasPeekingDrawer() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
            if (lp.isPeeking) {
                return true;
            }
        }
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.489 -0400", hash_original_method = "F12EE2EF5AFA7EC6FEDDB98F9D244F07", hash_generated_method = "16AD71462C46D331214CBB0DAAF2951E")
        
@Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.496 -0400", hash_original_method = "5E88FD9C1A0630B8DF61B0D809CE09B2", hash_generated_method = "280D47FE4BB7E73A5F7577402000045C")
        
@Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams
                ? new LayoutParams((LayoutParams) p)
                : p instanceof ViewGroup.MarginLayoutParams
                ? new LayoutParams((MarginLayoutParams) p)
                : new LayoutParams(p);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.503 -0400", hash_original_method = "0820242DFB1833A79D3A037A247A735A", hash_generated_method = "0FAAF748BA4C1D95221E65EBFCBC4B0B")
        
@Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams && super.checkLayoutParams(p);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.510 -0400", hash_original_method = "1C9B9BBEC46A39865FCD3A7288415ECD", hash_generated_method = "C4E1D1D633134DBA474501AB48EC2962")
        
@Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.515 -0400", hash_original_method = "72EE23FEEE53E74D565B9A0A6580D095", hash_generated_method = "045B07A27E6EECC17D0F396EC77CA6ED")
        
private boolean hasVisibleDrawer() {
        return findVisibleDrawer() != null;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.520 -0400", hash_original_method = "1D7EBBD6C182651ED5E70793B9D48104", hash_generated_method = "A2EC318939953F1981F8B21E196A5064")
        
private View findVisibleDrawer() {
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (isDrawerView(child) && isDrawerVisible(child)) {
                return child;
            }
        }
        return null;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.525 -0400", hash_original_method = "766D4F998F7E8AC254DFD532008A9B44", hash_generated_method = "6F57E10640260ED9E4A9AC72EE20D5FB")
        
void cancelChildViewTouch() {
        // Cancel child touches
        if (!mChildrenCanceledTouch) {
            final long now = SystemClock.uptimeMillis();
            final MotionEvent cancelEvent = MotionEvent.obtain(now, now,
                    MotionEvent.ACTION_CANCEL, 0.0f, 0.0f, 0);
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(cancelEvent);
            }
            cancelEvent.recycle();
            mChildrenCanceledTouch = true;
        }
    }

    /**
     * State persisted across instances
     */
    protected static class SavedState extends BaseSavedState {

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
            @DSSafe(DSCat.SAFE_LIST)
            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @DSSafe(DSCat.SAFE_LIST)
            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int openDrawerGravity = Gravity.NO_GRAVITY;
        int lockModeLeft = LOCK_MODE_UNLOCKED;
        int lockModeRight = LOCK_MODE_UNLOCKED;

        public SavedState(Parcel in) {
            super(in);
            openDrawerGravity = in.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(openDrawerGravity);
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private final int mAbsGravity;
        private ViewDragHelper mDragger;

        private final Runnable mPeekRunnable = new Runnable() {
            @DSSafe(DSCat.SAFE_LIST)
            @Override public void run() {
                peekDrawer();
            }
        };

        public ViewDragCallback(int gravity) {
            mAbsGravity = gravity;
        }

        @DSSafe(DSCat.SAFE_LIST)
        public void setDragger(ViewDragHelper dragger) {
            mDragger = dragger;
        }

        @DSSafe(DSCat.SAFE_LIST)
        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(mPeekRunnable);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            // Only capture views where the gravity matches what we're looking for.
            // This lets us use two ViewDragHelpers, one for each side drawer.
            return isDrawerView(child) && checkDrawerViewAbsoluteGravity(child, mAbsGravity)
                    && getDrawerLockMode(child) == LOCK_MODE_UNLOCKED;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onViewDragStateChanged(int state) {
            updateDrawerState(mAbsGravity, state, mDragger.getCapturedView());
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            float offset;
            final int childWidth = changedView.getWidth();

            // This reverses the positioning shown in onLayout.
            if (checkDrawerViewAbsoluteGravity(changedView, Gravity.LEFT)) {
                offset = (float) (childWidth + left) / childWidth;
            } else {
                final int width = getWidth();
                offset = (float) (width - left) / childWidth;
            }
            setDrawerViewOffset(changedView, offset);
            changedView.setVisibility(offset == 0 ? INVISIBLE : VISIBLE);
            invalidate();
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            final LayoutParams lp = (LayoutParams) capturedChild.getLayoutParams();
            lp.isPeeking = false;

            closeOtherDrawer();
        }

        @DSSafe(DSCat.SAFE_LIST)
        private void closeOtherDrawer() {
            final int otherGrav = mAbsGravity == Gravity.LEFT ? Gravity.RIGHT : Gravity.LEFT;
            final View toClose = findDrawerWithGravity(otherGrav);
            if (toClose != null) {
                closeDrawer(toClose);
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // Offset is how open the drawer is, therefore left/right values
            // are reversed from one another.
            final float offset = getDrawerViewOffset(releasedChild);
            final int childWidth = releasedChild.getWidth();

            int left;
            if (checkDrawerViewAbsoluteGravity(releasedChild, Gravity.LEFT)) {
                left = xvel > 0 || xvel == 0 && offset > 0.5f ? 0 : -childWidth;
            } else {
                final int width = getWidth();
                left = xvel < 0 || xvel == 0 && offset > 0.5f ? width - childWidth : width;
            }

            mDragger.settleCapturedViewAt(left, releasedChild.getTop());
            invalidate();
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            postDelayed(mPeekRunnable, PEEK_DELAY);
        }

        @DSSafe(DSCat.SAFE_LIST)
        private void peekDrawer() {
            final View toCapture;
            final int childLeft;
            final int peekDistance = mDragger.getEdgeSize();
            final boolean leftEdge = mAbsGravity == Gravity.LEFT;
            if (leftEdge) {
                toCapture = findDrawerWithGravity(Gravity.LEFT);
                childLeft = (toCapture != null ? -toCapture.getWidth() : 0) + peekDistance;
            } else {
                toCapture = findDrawerWithGravity(Gravity.RIGHT);
                childLeft = getWidth() - peekDistance;
            }
            // Only peek if it would mean making the drawer more visible and the drawer isn't locked
            if (toCapture != null && ((leftEdge && toCapture.getLeft() < childLeft) ||
                    (!leftEdge && toCapture.getLeft() > childLeft)) &&
                    getDrawerLockMode(toCapture) == LOCK_MODE_UNLOCKED) {
                final LayoutParams lp = (LayoutParams) toCapture.getLayoutParams();
                mDragger.smoothSlideViewTo(toCapture, childLeft, toCapture.getTop());
                lp.isPeeking = true;
                invalidate();

                closeOtherDrawer();

                cancelChildViewTouch();
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public boolean onEdgeLock(int edgeFlags) {
            if (ALLOW_EDGE_LOCK) {
                final View drawer = findDrawerWithGravity(mAbsGravity);
                if (drawer != null && !isDrawerOpen(drawer)) {
                    closeDrawer(drawer);
                }
                return true;
            }
            return false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            final View toCapture;
            if ((edgeFlags & ViewDragHelper.EDGE_LEFT) == ViewDragHelper.EDGE_LEFT) {
                toCapture = findDrawerWithGravity(Gravity.LEFT);
            } else {
                toCapture = findDrawerWithGravity(Gravity.RIGHT);
            }

            if (toCapture != null && getDrawerLockMode(toCapture) == LOCK_MODE_UNLOCKED) {
                mDragger.captureChildView(toCapture, pointerId);
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public int getViewHorizontalDragRange(View child) {
            return child.getWidth();
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (checkDrawerViewAbsoluteGravity(child, Gravity.LEFT)) {
                return Math.max(-child.getWidth(), Math.min(left, 0));
            } else {
                final int width = getWidth();
                return Math.max(width - child.getWidth(), Math.min(left, width));
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return child.getTop();
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        public int gravity = Gravity.NO_GRAVITY;
        float onScreen;
        boolean isPeeking;
        boolean knownOpen;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            final TypedArray a = c.obtainStyledAttributes(attrs, LAYOUT_ATTRS);
            this.gravity = a.getInt(0, Gravity.NO_GRAVITY);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, int gravity) {
            this(width, height);
            this.gravity = gravity;
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.gravity = source.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect mTmpRect = new Rect();

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            final AccessibilityNodeInfoCompat superNode = AccessibilityNodeInfoCompat.obtain(info);
            super.onInitializeAccessibilityNodeInfo(host, superNode);

            info.setSource(host);
            final ViewParent parent = ViewCompat.getParentForAccessibility(host);
            if (parent instanceof View) {
                info.setParent((View) parent);
            }
            copyNodeInfoNoChildren(info, superNode);

            superNode.recycle();

            addChildrenForAccessibility(info, (ViewGroup) host);
        }

        @DSSafe(DSCat.SAFE_LIST)
        private void addChildrenForAccessibility(AccessibilityNodeInfoCompat info, ViewGroup v) {
            final int childCount = v.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = v.getChildAt(i);
                if (filter(child)) {
                    continue;
                }

                // Adding children that are marked as not important for
                // accessibility will break the hierarchy, so we need to check
                // that value and re-parent views if necessary.
                final int importance = ViewCompat.getImportantForAccessibility(child);
                switch (importance) {
                    case ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS:
                        // Always skip NO_HIDE views and their descendants.
                        break;
                    case ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO:
                        // Re-parent children of NO view groups, skip NO views.
                        if (child instanceof ViewGroup) {
                            addChildrenForAccessibility(info, (ViewGroup) child);
                        }
                        break;
                    case ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO:
                        // Force AUTO views to YES and add them.
                        ViewCompat.setImportantForAccessibility(
                                child, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
                    case ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES:
                        info.addChild(child);
                        break;
                }
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child,
                AccessibilityEvent event) {
            if (!filter(child)) {
                return super.onRequestSendAccessibilityEvent(host, child, event);
            }
            return false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        public boolean filter(View child) {
            final View openDrawer = findOpenDrawer();
            return openDrawer != null && openDrawer != child;
        }

        /**
         * This should really be in AccessibilityNodeInfoCompat, but there unfortunately
         * seem to be a few elements that are not easily cloneable using the underlying API.
         * Leave it private here as it's not general-purpose useful.
         */
        @DSSafe(DSCat.SAFE_LIST)
        private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat dest,
                AccessibilityNodeInfoCompat src) {
            final Rect rect = mTmpRect;

            src.getBoundsInParent(rect);
            dest.setBoundsInParent(rect);

            src.getBoundsInScreen(rect);
            dest.setBoundsInScreen(rect);

            dest.setVisibleToUser(src.isVisibleToUser());
            dest.setPackageName(src.getPackageName());
            dest.setClassName(src.getClassName());
            dest.setContentDescription(src.getContentDescription());

            dest.setEnabled(src.isEnabled());
            dest.setClickable(src.isClickable());
            dest.setFocusable(src.isFocusable());
            dest.setFocused(src.isFocused());
            dest.setAccessibilityFocused(src.isAccessibilityFocused());
            dest.setSelected(src.isSelected());
            dest.setLongClickable(src.isLongClickable());

            dest.addAction(src.getActions());
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.512 -0400", hash_original_method = "3136D41FD5802BFCA8E16EA5B3F8477F", hash_generated_method = "C2BBBEC8913DC421C9137F8DDC736DAB")
            
@Override
        public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            // Special case to handle window state change events. As far as
            // accessibility services are concerned, state changes from
            // DrawerLayout invalidate the entire contents of the screen (like
            // an Activity or Dialog) and they should announce the title of the
            // new content.
            if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                final List<CharSequence> eventText = event.getText();
                final View visibleDrawer = findVisibleDrawer();
                if (visibleDrawer != null) {
                    final int edgeGravity = getDrawerViewAbsoluteGravity(visibleDrawer);
                    final CharSequence title = getDrawerTitle(edgeGravity);
                    if (title != null) {
                        eventText.add(title);
                    }
                }

                return true;
            }

            return super.dispatchPopulateAccessibilityEvent(host, event);
        }

        @DSSafe(DSCat.SAFE_LIST)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.505 -0400", hash_original_method = "86645AEBE3B736DED3186835A665D0EC", hash_generated_method = "18C91D9F648DEA6E4E50E6F536EC26E4")
            
@Override
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);

            event.setClassName(DrawerLayout.class.getName());
        }}

    final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        @DSSafe(DSCat.SAFE_LIST)
        @Override
        public void onInitializeAccessibilityNodeInfo(View child,
                AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(child, info);
            if (!includeChildForAccessibilitiy(child)) {
                // If we are ignoring the sub-tree rooted at the child,
                // break the connection to the rest of the node tree.
                // For details refer to includeChildForAccessibilitiy.
                info.setParent(null);
            }
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.531 -0400", hash_original_method = "9C735EFAE611B02E6FE7E8FE572C18CA", hash_generated_method = "D49F023E6D07BAE13420852EFC04D85A")
        
@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && hasVisibleDrawer()) {
            KeyEventCompat.startTracking(event);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.537 -0400", hash_original_method = "6C5E55BB3B41C7999126FE4083A38DFC", hash_generated_method = "079C7063CEACAFC97238A50DD317A07F")
        
@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final View visibleDrawer = findVisibleDrawer();
            if (visibleDrawer != null && getDrawerLockMode(visibleDrawer) == LOCK_MODE_UNLOCKED) {
                closeDrawers();
            }
            return visibleDrawer != null;
        }
        return super.onKeyUp(keyCode, event);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.543 -0400", hash_original_method = "38FD2AC8B8A875D6E7BA37CF18A03606", hash_generated_method = "3415B26881D0CB76734FE0E616C134E3")
        
@Override
    protected void onRestoreInstanceState(Parcelable state) {
        final SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (ss.openDrawerGravity != Gravity.NO_GRAVITY) {
            final View toOpen = findDrawerWithGravity(ss.openDrawerGravity);
            if (toOpen != null) {
                openDrawer(toOpen);
            }
        }

        setDrawerLockMode(ss.lockModeLeft, Gravity.LEFT);
        setDrawerLockMode(ss.lockModeRight, Gravity.RIGHT);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 14:43:52.550 -0400", hash_original_method = "DAA73C4A05B6D7789889CC806B03060D", hash_generated_method = "B800D872120CE97D1F07EDD59C463EA6")
        
@Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();

        final SavedState ss = new SavedState(superState);

        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (!isDrawerView(child)) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp.knownOpen) {
                ss.openDrawerGravity = lp.gravity;
                // Only one drawer can be open at a time.
                break;
            }
        }

        ss.lockModeLeft = mLockModeLeft;
        ss.lockModeRight = mLockModeRight;

        return ss;
    }

    /**
     * Returns the title of the drawer with the given gravity.
     *
     * @param edgeGravity Gravity.LEFT, RIGHT, START or END. Expresses which
     *            drawer to return the title for.
     * @return The title of the drawer, or null if none set.
     * @see #setDrawerTitle(int, CharSequence)
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.934 -0400", hash_original_method = "13C47A49F10E2C13EBB169FE2D2F5A39", hash_generated_method = "30D816389D559970F9E794566EEA4D63")
        
    public CharSequence getDrawerTitle(@EdgeGravity int edgeGravity) {
        final int absGravity = GravityCompat.getAbsoluteGravity(
                edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absGravity == Gravity.LEFT) {
            return mTitleLeft;
        } else if (absGravity == Gravity.RIGHT) {
            return mTitleRight;
        }
        return null;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:24.239 -0400", hash_original_method = "3A9C9462CF5CA53968299647F729674C", hash_generated_method = "8C1704DA5FDA78E9ACDB8BB4B2277E12")
        
@Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        // Until a drawer is open, it is hidden from accessibility.
        if (index > 0 || (index < 0 && getChildCount() > 0)) {
            ViewCompat.setImportantForAccessibility(child,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
            // Also set a delegate to break the child-parent relation if the
            // child is hidden. For details (see incluceChildForAccessibility).
            ViewCompat.setAccessibilityDelegate(child, mChildAccessibilityDelegate);
        } else  {
            // Initially, the content is shown for accessibility.
            ViewCompat.setImportantForAccessibility(child,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
        }
        super.addView(child, index, params);
    }

    /** @hide */
    private @interface EdgeGravity {}

    /**
     * Sets the title of the drawer with the given gravity.
     * <p>
     * When accessibility is turned on, this is the title that will be used to
     * identify the drawer to the active accessibility service.
     *
     * @param edgeGravity Gravity.LEFT, RIGHT, START or END. Expresses which
     *            drawer to set the title for.
     * @param title The title for the drawer.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 15:34:23.927 -0400", hash_original_method = "7198B03A053EFC324B15ADB387CDDBAF", hash_generated_method = "E80BEBB692E0D7811685A40EE86C5516")
        
public void setDrawerTitle(@EdgeGravity int edgeGravity, CharSequence title) {
        final int absGravity = GravityCompat.getAbsoluteGravity(
                edgeGravity, ViewCompat.getLayoutDirection(this));
        if (absGravity == Gravity.LEFT) {
            mTitleLeft = title;
        } else if (absGravity == Gravity.RIGHT) {
            mTitleRight = title;
        }
    }}
