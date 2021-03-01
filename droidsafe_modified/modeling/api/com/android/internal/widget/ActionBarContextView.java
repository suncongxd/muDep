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
 * Copyright (C) 2010 The Android Open Source Project
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


package com.android.internal.widget;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.R;
import com.android.internal.view.menu.ActionMenuPresenter;
import com.android.internal.view.menu.ActionMenuView;
import com.android.internal.view.menu.MenuBuilder;

public class ActionBarContextView extends AbsActionBarView implements AnimatorListener {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.072 -0500", hash_original_field = "4903F05D59AFFD00260A63A4EC905F53", hash_generated_field = "C45604C41252B81A800ABE94C507699D")

    private static final String TAG = "ActionBarContextView";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.103 -0500", hash_original_field = "F4D2F1B228E9CC66BE59AEB5714DFD77", hash_generated_field = "2A63476C517D08BD976212871A960624")

    private static final int ANIMATE_IDLE = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.106 -0500", hash_original_field = "33D78AF0979B298E43A05E2384D0B7FD", hash_generated_field = "822EF0AC3705B18FD672B9EE2414E019")

    private static final int ANIMATE_IN = 1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.108 -0500", hash_original_field = "341E9CA04B293188D328A40DDD9E92B5", hash_generated_field = "1F174DF669D4184CA0C67FB39313E9F2")

    private static final int ANIMATE_OUT = 2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.074 -0500", hash_original_field = "8C445459F9A68BF8713F87AC9D695E47", hash_generated_field = "ECAB18871B55104BACD4283C12B1074D")

    private CharSequence mTitle;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.076 -0500", hash_original_field = "AB153FEBC9BAB866EB9D9BD858F863D7", hash_generated_field = "775125B4EBC8ED0F3E5FBA051277E18D")

    private CharSequence mSubtitle;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.078 -0500", hash_original_field = "9E5ABA39447839CB8750D1879400036C", hash_generated_field = "24F281CC402C90EA9D9EB00DDCF618F4")

    private View mClose;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.081 -0500", hash_original_field = "ADD711F109703E2EAF61E2AD917F3FB6", hash_generated_field = "58BF9FCCDFB47B7346A31DF060279244")

    private View mCustomView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.083 -0500", hash_original_field = "F5CA594DCFB48DED54369A102A0AE68B", hash_generated_field = "53515DAB03CBCC904881F85C0D0B24F4")

    private LinearLayout mTitleLayout;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.085 -0500", hash_original_field = "B3B1177DB6C3A24EAC74C6705FD25300", hash_generated_field = "3B26FAD098CFEC3A217F2BF71097A83E")

    private TextView mTitleView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.087 -0500", hash_original_field = "913CA974C51FCA87DC9298E376261903", hash_generated_field = "5B91EC47B0CD462AA7928A5697458750")

    private TextView mSubtitleView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.089 -0500", hash_original_field = "91BBE04D928B78D71ACCA8B7A1A78C5D", hash_generated_field = "CFEDF1432A06310A2FB06B788CC20410")

    private int mTitleStyleRes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.092 -0500", hash_original_field = "1B837B3FAA0F380D6AF3C59E69CB3BE4", hash_generated_field = "9FA8532B8CB2D1269C79565E301C661F")

    private int mSubtitleStyleRes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.094 -0500", hash_original_field = "A2559183FE2F41FA2ACFFAAD43DEAB22", hash_generated_field = "EAFF30D2C8DDA41FABEEAF56960F2296")

    private Drawable mSplitBackground;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.096 -0500", hash_original_field = "46ED3AB600B5BE887F6EA524F7C38FC0", hash_generated_field = "33771BEA3F609DD1955FEC169216294A")

    private Animator mCurrentAnimation;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.099 -0500", hash_original_field = "3D2A0FA08A835C522985A23EBD1EB076", hash_generated_field = "8265308A3A327A796C7CC259CBBF12AC")

    private boolean mAnimateInOnLayout;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.101 -0500", hash_original_field = "B93C987598053C43E87566648E3A3752", hash_generated_field = "7231072E88C5D78E0458C5139E2E8DF1")

    private int mAnimationMode;
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.111 -0500", hash_original_method = "2EBFD0846938BB846291BE9F4AD94F04", hash_generated_method = "5204B1F16256DBE4D4C418301785327D")
    
public ActionBarContextView(Context context) {
        this(context, null);
    }
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.113 -0500", hash_original_method = "43CDEE17F6E403B0B882E0F499365FE4", hash_generated_method = "348B0930F38AD43D786D05BDE59E7074")
    
public ActionBarContextView(Context context, AttributeSet attrs) {
        this(context, attrs, com.android.internal.R.attr.actionModeStyle);
    }
    
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.116 -0500", hash_original_method = "19C70EFA7CD06BD307662BD45113B543", hash_generated_method = "513528AC2564102B25E794BB862225FD")
    
public ActionBarContextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionMode, defStyle, 0);
        setBackgroundDrawable(a.getDrawable(
                com.android.internal.R.styleable.ActionMode_background));
        mTitleStyleRes = a.getResourceId(
                com.android.internal.R.styleable.ActionMode_titleTextStyle, 0);
        mSubtitleStyleRes = a.getResourceId(
                com.android.internal.R.styleable.ActionMode_subtitleTextStyle, 0);

        mContentHeight = a.getLayoutDimension(
                com.android.internal.R.styleable.ActionMode_height, 0);

        mSplitBackground = a.getDrawable(
                com.android.internal.R.styleable.ActionMode_backgroundSplit);

        a.recycle();
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.118 -0500", hash_original_method = "B72CC99F1AE7F170915B2F32514E502E", hash_generated_method = "85E4C34DF224DF00676DC6184CD8DB89")
    
@Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mActionMenuPresenter != null) {
            mActionMenuPresenter.hideOverflowMenu();
            mActionMenuPresenter.hideSubMenus();
        }
    }

    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.121 -0500", hash_original_method = "E552720A78112C3EB9A59B322D8064C9", hash_generated_method = "49BAA6D56EACC40A43915DA120FC42EB")
    
@Override
    public void setSplitActionBar(boolean split) {
        if (mSplitActionBar != split) {
            if (mActionMenuPresenter != null) {
                // Mode is already active; move everything over and adjust the menu itself.
                final LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.MATCH_PARENT);
                if (!split) {
                    mMenuView = (ActionMenuView) mActionMenuPresenter.getMenuView(this);
                    mMenuView.setBackgroundDrawable(null);
                    final ViewGroup oldParent = (ViewGroup) mMenuView.getParent();
                    if (oldParent != null) oldParent.removeView(mMenuView);
                    addView(mMenuView, layoutParams);
                } else {
                    // Allow full screen width in split mode.
                    mActionMenuPresenter.setWidthLimit(
                            getContext().getResources().getDisplayMetrics().widthPixels, true);
                    // No limit to the item count; use whatever will fit.
                    mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
                    // Span the whole width
                    layoutParams.width = LayoutParams.MATCH_PARENT;
                    layoutParams.height = mContentHeight;
                    mMenuView = (ActionMenuView) mActionMenuPresenter.getMenuView(this);
                    mMenuView.setBackgroundDrawable(mSplitBackground);
                    final ViewGroup oldParent = (ViewGroup) mMenuView.getParent();
                    if (oldParent != null) oldParent.removeView(mMenuView);
                    mSplitView.addView(mMenuView, layoutParams);
                }
            }
            super.setSplitActionBar(split);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.123 -0500", hash_original_method = "2AF53FC77EF46E1AB46FFEE70D9B4BD3", hash_generated_method = "73099CFA6347D7279481242D53B55ACD")
    
public void setContentHeight(int height) {
        mContentHeight = height;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.126 -0500", hash_original_method = "BF35621F736CEABB1B31AE57D0749737", hash_generated_method = "253AA444B71B925E0AF3E202A9F00B36")
    
public void setCustomView(View view) {
        if (mCustomView != null) {
            removeView(mCustomView);
        }
        mCustomView = view;
        if (mTitleLayout != null) {
            removeView(mTitleLayout);
            mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.128 -0500", hash_original_method = "211EB79F55FBE601162C52B7DDCEAE29", hash_generated_method = "ABDADF55037DFFFE045D7206C2E121F1")
    
public void setTitle(CharSequence title) {
        mTitle = title;
        initTitle();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.131 -0500", hash_original_method = "7D9357B6D11390EB60D28E00C6F28FA8", hash_generated_method = "9A059E21C7BE314F1FFA7934207C4071")
    
public void setSubtitle(CharSequence subtitle) {
        mSubtitle = subtitle;
        initTitle();
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.133 -0500", hash_original_method = "7D691BCB64236E7B5AF8F6CB0399C091", hash_generated_method = "E948C46F1704FC1F24545ED5A725BEE6")
    
public CharSequence getTitle() {
        return mTitle;
    }

    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.135 -0500", hash_original_method = "8334E54E49A430BB5E2CB9D01857CDC7", hash_generated_method = "3773E52DB2B3822F51D6A3F2CB66D7C2")
    
public CharSequence getSubtitle() {
        return mSubtitle;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.138 -0500", hash_original_method = "790F9E36BA88F469E91A3DDEB8BA7A0E", hash_generated_method = "CF8D1EF1AB822485BDC7A5B7DEDCBEC6")
    
private void initTitle() {
        if (mTitleLayout == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(R.layout.action_bar_title_item, this);
            mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            mTitleView = (TextView) mTitleLayout.findViewById(R.id.action_bar_title);
            mSubtitleView = (TextView) mTitleLayout.findViewById(R.id.action_bar_subtitle);
            if (mTitleStyleRes != 0) {
                mTitleView.setTextAppearance(mContext, mTitleStyleRes);
            }
            if (mSubtitleStyleRes != 0) {
                mSubtitleView.setTextAppearance(mContext, mSubtitleStyleRes);
            }
        }

        mTitleView.setText(mTitle);
        mSubtitleView.setText(mSubtitle);

        final boolean hasTitle = !TextUtils.isEmpty(mTitle);
        final boolean hasSubtitle = !TextUtils.isEmpty(mSubtitle);
        mSubtitleView.setVisibility(hasSubtitle ? VISIBLE : GONE);
        mTitleLayout.setVisibility(hasTitle || hasSubtitle ? VISIBLE : GONE);
        if (mTitleLayout.getParent() == null) {
            addView(mTitleLayout);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.144 -0500", hash_original_method = "6CF0CC9D4208F5A3169016F9961CD359", hash_generated_method = "DE11EE3242ADFFC29FD32D11F328FFD8")
    
public void initForMode(final ActionMode mode) {
        if (mClose == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mClose = inflater.inflate(R.layout.action_mode_close_item, this, false);
            addView(mClose);
        } else if (mClose.getParent() == null) {
            addView(mClose);
        }

        View closeButton = mClose.findViewById(R.id.action_mode_close_button);
        closeButton.setOnClickListener(new OnClickListener() {
            @DSSafe(DSCat.SAFE_LIST)
        public void onClick(View v) {
                mode.finish();
            }
        });

        final MenuBuilder menu = (MenuBuilder) mode.getMenu();
        if (mActionMenuPresenter != null) {
            mActionMenuPresenter.dismissPopupMenus();
        }
        mActionMenuPresenter = new ActionMenuPresenter(mContext);
        mActionMenuPresenter.setReserveOverflow(true);

        final LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        if (!mSplitActionBar) {
            menu.addMenuPresenter(mActionMenuPresenter);
            mMenuView = (ActionMenuView) mActionMenuPresenter.getMenuView(this);
            mMenuView.setBackgroundDrawable(null);
            addView(mMenuView, layoutParams);
        } else {
            // Allow full screen width in split mode.
            mActionMenuPresenter.setWidthLimit(
                    getContext().getResources().getDisplayMetrics().widthPixels, true);
            // No limit to the item count; use whatever will fit.
            mActionMenuPresenter.setItemLimit(Integer.MAX_VALUE);
            // Span the whole width
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = mContentHeight;
            menu.addMenuPresenter(mActionMenuPresenter);
            mMenuView = (ActionMenuView) mActionMenuPresenter.getMenuView(this);
            mMenuView.setBackgroundDrawable(mSplitBackground);
            mSplitView.addView(mMenuView, layoutParams);
        }

        mAnimateInOnLayout = true;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.147 -0500", hash_original_method = "45A4BF13DB4610A7D54223CBAAD97337", hash_generated_method = "6D71A88FCCD28043C3A469BA8A2144A0")
    
public void closeMode() {
        if (mAnimationMode == ANIMATE_OUT) {
            // Called again during close; just finish what we were doing.
            return;
        }
        if (mClose == null) {
            killMode();
            return;
        }

        finishAnimation();
        mAnimationMode = ANIMATE_OUT;
        mCurrentAnimation = makeOutAnimation();
        mCurrentAnimation.start();
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.149 -0500", hash_original_method = "A219535592712D030952E62AEE6B3C28", hash_generated_method = "091E96789E470C896B6903990C8D19F7")
    
private void finishAnimation() {
        final Animator a = mCurrentAnimation;
        if (a != null) {
            mCurrentAnimation = null;
            a.end();
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.152 -0500", hash_original_method = "2B49C690F775A4BD526B81BD0C8494DB", hash_generated_method = "99A5621E5B5796F2FD5CAB23DCCA30F1")
    
public void killMode() {
        finishAnimation();
        removeAllViews();
        if (mSplitView != null) {
            mSplitView.removeView(mMenuView);
        }
        mCustomView = null;
        mMenuView = null;
        mAnimateInOnLayout = false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.154 -0500", hash_original_method = "6ECDD9C0917165AAFEF87E5E5EC8B474", hash_generated_method = "DB1A2AF65655622E1B55F35B09A3551A")
    
@Override
    public boolean showOverflowMenu() {
        if (mActionMenuPresenter != null) {
            return mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.156 -0500", hash_original_method = "5D05982F33AF24415676BD0317DE2206", hash_generated_method = "467E80F972452C0C6AFEA47847C03B8F")
    
@Override
    public boolean hideOverflowMenu() {
        if (mActionMenuPresenter != null) {
            return mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.159 -0500", hash_original_method = "2F838D8CBDDC91E04D7A8F0F52ABF2C8", hash_generated_method = "DE5567A716B3EC74155227BDC35F2E18")
    
@Override
    public boolean isOverflowMenuShowing() {
        if (mActionMenuPresenter != null) {
            return mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.161 -0500", hash_original_method = "73CB5175F40E2B68B8D41267F726F54F", hash_generated_method = "DC8C23AC82FD27CFC58436A7F967E5C5")
    
@Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        // Used by custom views if they don't supply layout params. Everything else
        // added to an ActionBarContextView should have them already.
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.163 -0500", hash_original_method = "5C07F753B3F2B1F9F4C858C93EDD71AE", hash_generated_method = "13C3DE314C4F1232733881CF39400F8B")
    
@Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.168 -0500", hash_original_method = "ADE259D11B02655AAFB5EBCEC641AC5E", hash_generated_method = "384A75FBEC4DA6149CCE41556CE66768")
    
@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " +
                    "with android:layout_width=\"match_parent\" (or fill_parent)");
        }

        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " +
                    "with android:layout_height=\"wrap_content\"");
        }
        
        final int contentWidth = MeasureSpec.getSize(widthMeasureSpec);

        int maxHeight = mContentHeight > 0 ?
                mContentHeight : MeasureSpec.getSize(heightMeasureSpec);

        final int verticalPadding = getPaddingTop() + getPaddingBottom();
        int availableWidth = contentWidth - getPaddingLeft() - getPaddingRight();
        final int height = maxHeight - verticalPadding;
        final int childSpecHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        
        if (mClose != null) {
            availableWidth = measureChildView(mClose, availableWidth, childSpecHeight, 0);
            MarginLayoutParams lp = (MarginLayoutParams) mClose.getLayoutParams();
            availableWidth -= lp.leftMargin + lp.rightMargin;
        }

        if (mMenuView != null && mMenuView.getParent() == this) {
            availableWidth = measureChildView(mMenuView, availableWidth,
                    childSpecHeight, 0);
        }

        if (mTitleLayout != null && mCustomView == null) {
            availableWidth = measureChildView(mTitleLayout, availableWidth, childSpecHeight, 0);
        }

        if (mCustomView != null) {
            ViewGroup.LayoutParams lp = mCustomView.getLayoutParams();
            final int customWidthMode = lp.width != LayoutParams.WRAP_CONTENT ?
                    MeasureSpec.EXACTLY : MeasureSpec.AT_MOST;
            final int customWidth = lp.width >= 0 ?
                    Math.min(lp.width, availableWidth) : availableWidth;
            final int customHeightMode = lp.height != LayoutParams.WRAP_CONTENT ?
                    MeasureSpec.EXACTLY : MeasureSpec.AT_MOST;
            final int customHeight = lp.height >= 0 ?
                    Math.min(lp.height, height) : height;
            mCustomView.measure(MeasureSpec.makeMeasureSpec(customWidth, customWidthMode),
                    MeasureSpec.makeMeasureSpec(customHeight, customHeightMode));
        }

        if (mContentHeight <= 0) {
            int measuredHeight = 0;
            final int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View v = getChildAt(i);
                int paddedViewHeight = v.getMeasuredHeight() + verticalPadding;
                if (paddedViewHeight > measuredHeight) {
                    measuredHeight = paddedViewHeight;
                }
            }
            setMeasuredDimension(contentWidth, measuredHeight);
        } else {
            setMeasuredDimension(contentWidth, maxHeight);
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.171 -0500", hash_original_method = "1F1E13F5C24EF6D24D9BF3983118D5E2", hash_generated_method = "521EFDF524BC6F65D7E35DD426994084")
    
private Animator makeInAnimation() {
        mClose.setTranslationX(-mClose.getWidth() -
                ((MarginLayoutParams) mClose.getLayoutParams()).leftMargin);
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(mClose, "translationX", 0);
        buttonAnimator.setDuration(200);
        buttonAnimator.addListener(this);
        buttonAnimator.setInterpolator(new DecelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        AnimatorSet.Builder b = set.play(buttonAnimator);

        if (mMenuView != null) {
            final int count = mMenuView.getChildCount();
            if (count > 0) {
                for (int i = count - 1, j = 0; i >= 0; i--, j++) {
                    View child = mMenuView.getChildAt(i);
                    child.setScaleY(0);
                    ObjectAnimator a = ObjectAnimator.ofFloat(child, "scaleY", 0, 1);
                    a.setDuration(100);
                    a.setStartDelay(j * 70);
                    b.with(a);
                }
            }
        }

        return set;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.173 -0500", hash_original_method = "BAA3822313887C49DB18D4BC04440E7E", hash_generated_method = "C71D5FA9618B9F09AA534EE9250755E6")
    
private Animator makeOutAnimation() {
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(mClose, "translationX",
                -mClose.getWidth() - ((MarginLayoutParams) mClose.getLayoutParams()).leftMargin);
        buttonAnimator.setDuration(200);
        buttonAnimator.addListener(this);
        buttonAnimator.setInterpolator(new DecelerateInterpolator());

        AnimatorSet set = new AnimatorSet();
        AnimatorSet.Builder b = set.play(buttonAnimator);

        if (mMenuView != null) {
            final int count = mMenuView.getChildCount();
            if (count > 0) {
                for (int i = 0; i < 0; i++) {
                    View child = mMenuView.getChildAt(i);
                    child.setScaleY(0);
                    ObjectAnimator a = ObjectAnimator.ofFloat(child, "scaleY", 0);
                    a.setDuration(100);
                    a.setStartDelay(i * 70);
                    b.with(a);
                }
            }
        }

        return set;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.176 -0500", hash_original_method = "EAC754957749E93A677F7BF2F54929F7", hash_generated_method = "582452AB9563C9347552530ACFF06267")
    
@Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = getPaddingLeft();
        final int y = getPaddingTop();
        final int contentHeight = b - t - getPaddingTop() - getPaddingBottom();
        
        if (mClose != null && mClose.getVisibility() != GONE) {
            MarginLayoutParams lp = (MarginLayoutParams) mClose.getLayoutParams();
            x += lp.leftMargin;
            x += positionChild(mClose, x, y, contentHeight);
            x += lp.rightMargin;

            if (mAnimateInOnLayout) {
                mAnimationMode = ANIMATE_IN;
                mCurrentAnimation = makeInAnimation();
                mCurrentAnimation.start();
                mAnimateInOnLayout = false;
            }
        }

        if (mTitleLayout != null && mCustomView == null) {
            x += positionChild(mTitleLayout, x, y, contentHeight);
        }
        
        if (mCustomView != null) {
            x += positionChild(mCustomView, x, y, contentHeight);
        }
        
        x = r - l - getPaddingRight();

        if (mMenuView != null) {
            x -= positionChildInverse(mMenuView, x, y, contentHeight);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.179 -0500", hash_original_method = "B990D1A680B01C8A5486D22741D7E96B", hash_generated_method = "A0DF4431BC87990F62C49C1D9C1CE1F8")
    
@Override
    public void onAnimationStart(Animator animation) {
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.181 -0500", hash_original_method = "5DF138862D9EBBC48B8DDB31E593969C", hash_generated_method = "247BF268C441BCDBC972E7066611B0BC")
    
@Override
    public void onAnimationEnd(Animator animation) {
        if (mAnimationMode == ANIMATE_OUT) {
            killMode();
        }
        mAnimationMode = ANIMATE_IDLE;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.183 -0500", hash_original_method = "16FF962612CA0CC38B4765F38064800C", hash_generated_method = "09F3DCB3B2C6F194F589204D6CEA1D02")
    
@Override
    public void onAnimationCancel(Animator animation) {
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.186 -0500", hash_original_method = "FA58700A4888ED9E658B43737D17A966", hash_generated_method = "10E9921CB3298EBACCE8F83DAB252AF1")
    
@Override
    public void onAnimationRepeat(Animator animation) {
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.188 -0500", hash_original_method = "9DA99B4E01A506E72BA59AF598A3C38D", hash_generated_method = "FF1288C9953DCFF93F8070857A51FACB")
    
@Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @DSSafe(DSCat.ANDROID_CALLBACK)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:59:42.190 -0500", hash_original_method = "237F1A60A9E9685B4EADEDCFF21B9A19", hash_generated_method = "9B6A9EEA900E48CEBA0104EC08FB727B")
    
@Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            // Action mode started
            event.setSource(this);
            event.setClassName(getClass().getName());
            event.setPackageName(getContext().getPackageName());
            event.setContentDescription(mTitle);
        } else {
            super.onInitializeAccessibilityEvent(event);
        }
    }
}

