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


package android.support.v7.internal.view.menu;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Base class for MenuPresenters that have a consistent container view and item views. Behaves
 * similarly to an AdapterView in that existing item views will be reused if possible when items
 * change.
 *
 * @hide
 */
public abstract class BaseMenuPresenter implements MenuPresenter {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.952 -0400", hash_original_field = "A498D7B6B951074CC260EAF09122A9CD", hash_generated_field = "9BBD2C5E70A5CAEEE85328B585C6EDD6")

    protected Context mSystemContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.954 -0400", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "B3359F86E29A965BC1436888E98C55A8")

    protected Context mContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.957 -0400", hash_original_field = "E0FA7D9C40725579D8730D3DABE74643", hash_generated_field = "FD524EB2A4E22F4B2E2213811669D12E")

    protected MenuBuilder mMenu;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.959 -0400", hash_original_field = "DEB8D85BC68E1030935F12FDE966E270", hash_generated_field = "9175061DB185D7353CA0BE481FFC4473")

    protected LayoutInflater mSystemInflater;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.961 -0400", hash_original_field = "B03B4DAE3F576B7166425BEE37B96C5E", hash_generated_field = "76F426D24BEF6D52944D0403F5710568")

    protected LayoutInflater mInflater;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.964 -0400", hash_original_field = "1A99E72B6409E38FBCC780D1BAB4898D", hash_generated_field = "B05DD02C49016AA70EF55EB624CC40D3")

    private Callback mCallback;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.967 -0400", hash_original_field = "E3964B9758FE9C658990669AD0A92FF6", hash_generated_field = "4FE937236E901E4D73D88B87E9D305E0")

    private int mMenuLayoutRes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.969 -0400", hash_original_field = "892636E3A464AFFB770DCFC2EBD216F3", hash_generated_field = "C816B664A95F5226F0E062333FAD6D81")

    private int mItemLayoutRes;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.972 -0400", hash_original_field = "00AFA7C304AE938B0557AD18697A687E", hash_generated_field = "09958BB4E6973250CC3EA4C3B0BA7E7B")

    protected MenuView mMenuView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.974 -0400", hash_original_field = "9F66E723E60E4F10157FDA7C23C67293", hash_generated_field = "9AA2EA3A2433F5D6F841BEFD54A673B4")

    private int mId;

    /**
     * Construct a new BaseMenuPresenter.
     *
     * @param context       Context for generating system-supplied views
     * @param menuLayoutRes Layout resource ID for the menu container view
     * @param itemLayoutRes Layout resource ID for a single item view
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.977 -0400", hash_original_method = "0D2E3D4A8E23D7091EB09B3013BF35EA", hash_generated_method = "9808664F276C8A833E52A54B3F07FC66")
    
public BaseMenuPresenter(Context context, int menuLayoutRes, int itemLayoutRes) {
        mSystemContext = context;
        mSystemInflater = LayoutInflater.from(context);
        mMenuLayoutRes = menuLayoutRes;
        mItemLayoutRes = itemLayoutRes;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.980 -0400", hash_original_method = "132A0398F4F3F28C73E120DA24E1764B", hash_generated_method = "49888A427427AF37EEA2BDD9681A2A85")
    
@Override
    public void initForMenu(Context context, MenuBuilder menu) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mMenu = menu;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.983 -0400", hash_original_method = "5077062D5DDD15E91EAE389A4058EA7D", hash_generated_method = "41EAD5487885C144BB3841F65CF95AA7")
    
public MenuView getMenuView(ViewGroup root) {
        if (mMenuView == null) {
            mMenuView = (MenuView) mSystemInflater.inflate(mMenuLayoutRes, root, false);
            mMenuView.initialize(mMenu);
            updateMenuView(true);
        }

        return mMenuView;
    }

    /**
     * Reuses item views when it can
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.986 -0400", hash_original_method = "CC9D95592F6BB57C1E0F4DF84379BB05", hash_generated_method = "722596A52665E5BC82AE1341216491A8")
    
public void updateMenuView(boolean cleared) {
        final ViewGroup parent = (ViewGroup) mMenuView;
        if (parent == null) {
            return;
        }

        int childIndex = 0;
        if (mMenu != null) {
            mMenu.flagActionItems();
            ArrayList<MenuItemImpl> visibleItems = mMenu.getVisibleItems();
            final int itemCount = visibleItems.size();
            for (int i = 0; i < itemCount; i++) {
                MenuItemImpl item = visibleItems.get(i);
                if (shouldIncludeItem(childIndex, item)) {
                    final View convertView = parent.getChildAt(childIndex);
                    final MenuItemImpl oldItem = convertView instanceof MenuView.ItemView ?
                            ((MenuView.ItemView) convertView).getItemData() : null;
                    final View itemView = getItemView(item, convertView, parent);
                    if (item != oldItem) {
                        // Don't let old states linger with new data.
                        itemView.setPressed(false);
                        // itemView.jumpDrawablesToCurrentState();
                        // Animation API: Not available on API < 11
                    }
                    if (itemView != convertView) {
                        addItemView(itemView, childIndex);
                    }
                    childIndex++;
                }
            }
        }

        // Remove leftover views.
        while (childIndex < parent.getChildCount()) {
            if (!filterLeftoverView(parent, childIndex)) {
                childIndex++;
            }
        }
    }

    /**
     * Add an item view at the given index.
     *
     * @param itemView   View to add
     * @param childIndex Index within the parent to insert at
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.990 -0400", hash_original_method = "131BFAEC8D945AEDE48A08C61CFE6B46", hash_generated_method = "22AE91C1764F35EB5D86FA93F563BDED")
    
protected void addItemView(View itemView, int childIndex) {
        final ViewGroup currentParent = (ViewGroup) itemView.getParent();
        if (currentParent != null) {
            currentParent.removeView(itemView);
        }
        ((ViewGroup) mMenuView).addView(itemView, childIndex);
    }

    /**
     * Filter the child view at index and remove it if appropriate.
     *
     * @param parent     Parent to filter from
     * @param childIndex Index to filter
     * @return true if the child view at index was removed
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.993 -0400", hash_original_method = "2A7E19722F4165D83150E5278582AE5B", hash_generated_method = "317282763ECEA92D4FB172875EC559FA")
    
protected boolean filterLeftoverView(ViewGroup parent, int childIndex) {
        parent.removeViewAt(childIndex);
        return true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.996 -0400", hash_original_method = "80E7AE02A73BB486D54AC38F406B92BB", hash_generated_method = "EAFBC13B735460C43524285DBFE0E8D5")
    
public void setCallback(Callback cb) {
        mCallback = cb;
    }

    /**
     * Create a new item view that can be re-bound to other item data later.
     *
     * @return The new item view
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:36.999 -0400", hash_original_method = "D134EE9C84D0511ABB405D2C10EDC1CD", hash_generated_method = "339A869D5E7F3C867234798E69550C56")
    
public MenuView.ItemView createItemView(ViewGroup parent) {
        return (MenuView.ItemView) mSystemInflater.inflate(mItemLayoutRes, parent, false);
    }

    /**
     * Prepare an item view for use. See AdapterView for the basic idea at work here. This may
     * require creating a new item view, but well-behaved implementations will re-use the view
     * passed as convertView if present. The returned view will be populated with data from the item
     * parameter.
     *
     * @param item        Item to present
     * @param convertView Existing view to reuse
     * @param parent      Intended parent view - use for inflation.
     * @return View that presents the requested menu item
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.002 -0400", hash_original_method = "4524E542CCB3CEBB00AD430A4F472D75", hash_generated_method = "A5DAD7C08BF23E3E718E9F606CA7F4DA")
    
public View getItemView(MenuItemImpl item, View convertView, ViewGroup parent) {
        MenuView.ItemView itemView;
        if (convertView instanceof MenuView.ItemView) {
            itemView = (MenuView.ItemView) convertView;
        } else {
            itemView = createItemView(parent);
        }
        bindItemView(item, itemView);
        return (View) itemView;
    }

    /**
     * Bind item data to an existing item view.
     *
     * @param item     Item to bind
     * @param itemView View to populate with item data
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.006 -0400", hash_original_method = "E87EF560134394E4576C86BDF688B94A", hash_generated_method = "22ED289277EE84EF6B9BC1E0E71706FC")
    
public abstract void bindItemView(MenuItemImpl item, MenuView.ItemView itemView);

    /**
     * Filter item by child index and item data.
     *
     * @param childIndex Indended presentation index of this item
     * @param item       Item to present
     * @return true if this item should be included in this menu presentation; false otherwise
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.009 -0400", hash_original_method = "87E28A5B278A6CA733508943AA98463C", hash_generated_method = "2F31054307D456D95AA255A3CF4DC032")
    
public boolean shouldIncludeItem(int childIndex, MenuItemImpl item) {
        return true;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.012 -0400", hash_original_method = "722067599CD4502E441718E90CBC0EB7", hash_generated_method = "66BC754170E55A3A7C9F0FD154CE9E3C")
    
public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        if (mCallback != null) {
            mCallback.onCloseMenu(menu, allMenusAreClosing);
        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.014 -0400", hash_original_method = "A5E8641CBC0C1B6BF90AAD3ACE718984", hash_generated_method = "07D97A2C37E67BDE9795F83B152BF960")
    
public boolean onSubMenuSelected(SubMenuBuilder menu) {
        if (mCallback != null) {
            return mCallback.onOpenSubMenu(menu);
        }
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.017 -0400", hash_original_method = "4C665EA7F97447510193964EEFD645AE", hash_generated_method = "C1E1152399C2C1C3BB61E29F259A1FD1")
    
public boolean flagActionItems() {
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.020 -0400", hash_original_method = "5F1249279FE45B816C21E97DA3013708", hash_generated_method = "1AC0563B74EC3CB7F8AA75918408F79E")
    
public boolean expandItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.024 -0400", hash_original_method = "06DF13061AFE044B0D38F9C19B18A57A", hash_generated_method = "7FACC901E2C2CF1ADC66B0D53290352D")
    
public boolean collapseItemActionView(MenuBuilder menu, MenuItemImpl item) {
        return false;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.026 -0400", hash_original_method = "849E83C6BA01C72387E236CA4FAA38B9", hash_generated_method = "11DEA77066B2A20ED28C130805C2B9DE")
    
public int getId() {
        return mId;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:37.029 -0400", hash_original_method = "97D2A517E68B7CE37726268CF0899D78", hash_generated_method = "2C930A8D49DAA23CEDA00223A5DAF654")
    
public void setId(int id) {
        mId = id;
    }
}
