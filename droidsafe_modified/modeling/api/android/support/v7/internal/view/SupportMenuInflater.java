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


package android.support.v7.internal.view;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * This class is used to instantiate menu XML files into Menu objects.
 * <p>
 * For performance reasons, menu inflation relies heavily on pre-processing of
 * XML files that is done at build time. Therefore, it is not currently possible
 * to use SupportMenuInflater with an XmlPullParser over a plain XML file at runtime;
 * it only works with an XmlPullParser returned from a compiled resource (R.
 * <em>something</em> file.)
 *
 * @hide
 */
public class SupportMenuInflater extends MenuInflater {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.460 -0400", hash_original_field = "D06341A947FA672F67B56A8EA40E82F3", hash_generated_field = "E211B1E7BE12A5444444E625871D27D3")

    private static final String LOG_TAG = "SupportMenuInflater";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.462 -0400", hash_original_field = "E8571A1D1D35A75FA974D66944CD7F20", hash_generated_field = "E31E26254373C198064DD9A1A92086C6")

    private static final String XML_MENU = "menu";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.465 -0400", hash_original_field = "2B3BD04992C664DFBB50E960088EBF17", hash_generated_field = "741934D10DDA27952BC09F06E8B134F1")

    private static final String XML_GROUP = "group";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.468 -0400", hash_original_field = "7AE15B08CFBB6EABBC3957062F9FBBC0", hash_generated_field = "E6B1030CD1EE1C83F812536C256CA27C")

    private static final String XML_ITEM = "item";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.470 -0400", hash_original_field = "7268E03A5340DB38AA6685B8AA605B67", hash_generated_field = "E013106922B0138DD6AA1961763950FB")

    private static final int NO_ID = 0;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.473 -0400", hash_original_field = "D4148E18C49BEF6E1A417002BD55A8B1", hash_generated_field = "106DC72A08073671C77A7D24FBF7A3A9")

    private static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[] {Context.class};
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.476 -0400", hash_original_field = "4F684130B0122F446A598AD4585A3D11", hash_generated_field = "C7B470A3C7B9F2492DB764AD9D747D63")

    private static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE =
            ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.478 -0400", hash_original_field = "B67C5B388E9FA2DA18ED6D2A1AEFE8AC", hash_generated_field = "2E5F86E900916EB9BA1688830F2A5AD2")

    private  Object[] mActionViewConstructorArguments;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.481 -0400", hash_original_field = "9A27EFFBDE324A6C3E883862F710B33A", hash_generated_field = "EF0AF7D7071FC85FAD372A0098884F5B")

    private  Object[] mActionProviderConstructorArguments;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.483 -0400", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "C458E619396054F78BC926FB81B4386D")

    private Context mContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.485 -0400", hash_original_field = "E61DF532B5FBB551C9DB8D0C7D03A4B8", hash_generated_field = "05A81FB385E86344EC955B331EC753C2")

    private Object mRealOwner;

    /**
     * Constructs a menu inflater.
     *
     * @see Activity#getMenuInflater()
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.489 -0400", hash_original_method = "4212B981A7928D4D7AA2EA1CD67BEA53", hash_generated_method = "6A6BAA1E92C2AA4A5401DD35F7F05625")
    
public SupportMenuInflater(Context context) {
        super(context);
        mContext = context;
        mRealOwner = context;
        mActionViewConstructorArguments = new Object[] {context};
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    /**
     * Inflate a menu hierarchy from the specified XML resource. Throws
     * {@link InflateException} if there is an error.
     *
     * @param menuRes Resource ID for an XML layout resource to load (e.g.,
     *            <code>R.menu.main_activity</code>)
     * @param menu The Menu to inflate into. The items and submenus will be
     *            added to this Menu.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.493 -0400", hash_original_method = "C6C7F976DB99189CDA4BAC668484C52B", hash_generated_method = "2935D9CFF0E938B9AF1CA443976A9AE4")
    
@Override
    public void inflate(int menuRes, Menu menu) {
        // If we're not dealing with a SupportMenu instance, let super handle
        if (!(menu instanceof SupportMenu)) {
            super.inflate(menuRes, menu);
            return;
        }

        XmlResourceParser parser = null;
        try {
            parser = mContext.getResources().getLayout(menuRes);
            AttributeSet attrs = Xml.asAttributeSet(parser);

            parseMenu(parser, attrs, menu);
        } catch (XmlPullParserException e) {
            throw new InflateException("Error inflating menu XML", e);
        } catch (IOException e) {
            throw new InflateException("Error inflating menu XML", e);
        } finally {
            if (parser != null) parser.close();
        }
    }

    /**
     * Called internally to fill the given menu. If a sub menu is seen, it will
     * call this recursively.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-18 21:46:41.499 -0400", hash_original_method = "9930459085CDC2417094A7A8585D74E6", hash_generated_method = "5A33FCD11FD7601B075450C8B2424E13")
    
private void parseMenu(XmlPullParser parser, AttributeSet attrs, Menu menu)
            throws XmlPullParserException, IOException {
        MenuState menuState = new MenuState(menu);

        int eventType = parser.getEventType();
        String tagName;
        boolean lookingForEndOfUnknownTag = false;
        String unknownTagName = null;

        // This loop will skip to the menu start tag
        do {
            if (eventType == XmlPullParser.START_TAG) {
                tagName = parser.getName();
                if (tagName.equals(XML_MENU)) {
                    // Go to next tag
                    eventType = parser.next();
                    break;
                }

                throw new RuntimeException("Expecting menu, got " + tagName);
            }
            eventType = parser.next();
        } while (eventType != XmlPullParser.END_DOCUMENT);

        boolean reachedEndOfMenu = false;
        while (!reachedEndOfMenu) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (lookingForEndOfUnknownTag) {
                        break;
                    }

                    tagName = parser.getName();
                    if (tagName.equals(XML_GROUP)) {
                        menuState.readGroup(attrs);
                    } else if (tagName.equals(XML_ITEM)) {
                        menuState.readItem(attrs);
                    } else if (tagName.equals(XML_MENU)) {
                        // A menu start tag denotes a submenu for an item
                        SubMenu subMenu = menuState.addSubMenuItem();

                        // Parse the submenu into returned SubMenu
                        parseMenu(parser, attrs, subMenu);
                    } else {
                        lookingForEndOfUnknownTag = true;
                        unknownTagName = tagName;
                    }
                    break;

                case XmlPullParser.END_TAG:
                    tagName = parser.getName();
                    if (lookingForEndOfUnknownTag && tagName.equals(unknownTagName)) {
                        lookingForEndOfUnknownTag = false;
                        unknownTagName = null;
                    } else if (tagName.equals(XML_GROUP)) {
                        menuState.resetGroup();
                    } else if (tagName.equals(XML_ITEM)) {
                        // Add the item if it hasn't been added (if the item was
                        // a submenu, it would have been added already)
                        if (!menuState.hasAddedItem()) {
                            if (menuState.itemActionProvider != null &&
                                    menuState.itemActionProvider.hasSubMenu()) {
                                menuState.addSubMenuItem();
                            } else {
                                menuState.addItem();
                            }
                        }
                    } else if (tagName.equals(XML_MENU)) {
                        reachedEndOfMenu = true;
                    }
                    break;

                case XmlPullParser.END_DOCUMENT:
                    throw new RuntimeException("Unexpected end of document");
            }

            eventType = parser.next();
        }
    }

    private static class InflatedOnMenuItemClickListener
            implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = new Class[] { MenuItem.class };

        private Object mRealOwner;
        private Method mMethod;

        public InflatedOnMenuItemClickListener(Object realOwner, String methodName) {
            mRealOwner = realOwner;
            Class<?> c = realOwner.getClass();
            try {
                mMethod = c.getMethod(methodName, PARAM_TYPES);
            } catch (Exception e) {
                InflateException ex = new InflateException(
                        "Couldn't resolve menu item onClick handler " + methodName +
                                " in class " + c.getName());
                ex.initCause(e);
                throw ex;
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        public boolean onMenuItemClick(MenuItem item) {
            try {
                if (mMethod.getReturnType() == Boolean.TYPE) {
                    return (Boolean) mMethod.invoke(mRealOwner, item);
                } else {
                    mMethod.invoke(mRealOwner, item);
                    return true;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * State for the current menu.
     * <p>
     * Groups can not be nested unless there is another menu (which will have
     * its state class).
     */
    private class MenuState {
        private Menu menu;

        /*
         * Group state is set on items as they are added, allowing an item to
         * override its group state. (As opposed to set on items at the group end tag.)
         */
        private int groupId;
        private int groupCategory;
        private int groupOrder;
        private int groupCheckable;
        private boolean groupVisible;
        private boolean groupEnabled;

        private boolean itemAdded;
        private int itemId;
        private int itemCategoryOrder;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private int itemIconResId;
        private char itemAlphabeticShortcut;
        private char itemNumericShortcut;
        /**
         * Sync to attrs.xml enum:
         * - 0: none
         * - 1: all
         * - 2: exclusive
         */
        private int itemCheckable;
        private boolean itemChecked;
        private boolean itemVisible;
        private boolean itemEnabled;

        /**
         * Sync to attrs.xml enum, values in MenuItem:
         * - 0: never
         * - 1: ifRoom
         * - 2: always
         * - -1: Safe sentinel for "no value".
         */
        private int itemShowAsAction;

        private int itemActionViewLayout;
        private String itemActionViewClassName;
        private String itemActionProviderClassName;

        private String itemListenerMethodName;

        private ActionProvider itemActionProvider;

        private static final int defaultGroupId = NO_ID;
        private static final int defaultItemId = NO_ID;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemOrder = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemVisible = true;
        private static final boolean defaultItemEnabled = true;

        public MenuState(final Menu menu) {
            this.menu = menu;

            resetGroup();
        }

        @DSSafe(DSCat.SAFE_LIST)
        public void resetGroup() {
            groupId = defaultGroupId;
            groupCategory = defaultItemCategory;
            groupOrder = defaultItemOrder;
            groupCheckable = defaultItemCheckable;
            groupVisible = defaultItemVisible;
            groupEnabled = defaultItemEnabled;
        }

        /**
         * Called when the parser is pointing to a group tag.
         */
        @DSSafe(DSCat.SAFE_LIST)
        public void readGroup(AttributeSet attrs) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.MenuGroup);

            groupId = a.getResourceId(R.styleable.MenuGroup_android_id, defaultGroupId);
            groupCategory = a.getInt(
                    R.styleable.MenuGroup_android_menuCategory, defaultItemCategory);
            groupOrder = a.getInt(R.styleable.MenuGroup_android_orderInCategory, defaultItemOrder);
            groupCheckable = a.getInt(
                    R.styleable.MenuGroup_android_checkableBehavior, defaultItemCheckable);
            groupVisible = a.getBoolean(R.styleable.MenuGroup_android_visible, defaultItemVisible);
            groupEnabled = a.getBoolean(R.styleable.MenuGroup_android_enabled, defaultItemEnabled);

            a.recycle();
        }

        /**
         * Called when the parser is pointing to an item tag.
         */
        @DSSafe(DSCat.SAFE_LIST)
        public void readItem(AttributeSet attrs) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.MenuItem);

            // Inherit attributes from the group as default value
            itemId = a.getResourceId(R.styleable.MenuItem_android_id, defaultItemId);
            final int category = a.getInt(R.styleable.MenuItem_android_menuCategory, groupCategory);
            final int order = a.getInt(R.styleable.MenuItem_android_orderInCategory, groupOrder);
            itemCategoryOrder = (category & SupportMenu.CATEGORY_MASK) |
                    (order & SupportMenu.USER_MASK);
            itemTitle = a.getText(R.styleable.MenuItem_android_title);
            itemTitleCondensed = a.getText(R.styleable.MenuItem_android_titleCondensed);
            itemIconResId = a.getResourceId(R.styleable.MenuItem_android_icon, 0);
            itemAlphabeticShortcut =
                    getShortcut(a.getString(R.styleable.MenuItem_android_alphabeticShortcut));
            itemNumericShortcut =
                    getShortcut(a.getString(R.styleable.MenuItem_android_numericShortcut));
            if (a.hasValue(R.styleable.MenuItem_android_checkable)) {
                // Item has attribute checkable, use it
                itemCheckable = a.getBoolean(R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                // Item does not have attribute, use the group's (group can have one more state
                // for checkable that represents the exclusive checkable)
                itemCheckable = groupCheckable;
            }
            itemChecked = a.getBoolean(R.styleable.MenuItem_android_checked, defaultItemChecked);
            itemVisible = a.getBoolean(R.styleable.MenuItem_android_visible, groupVisible);
            itemEnabled = a.getBoolean(R.styleable.MenuItem_android_enabled, groupEnabled);
            itemShowAsAction = a.getInt(R.styleable.MenuItem_showAsAction, -1);
            itemListenerMethodName = a.getString(R.styleable.MenuItem_android_onClick);
            itemActionViewLayout = a.getResourceId(R.styleable.MenuItem_actionLayout, 0);
            itemActionViewClassName = a.getString(R.styleable.MenuItem_actionViewClass);
            itemActionProviderClassName = a.getString(R.styleable.MenuItem_actionProviderClass);

            final boolean hasActionProvider = itemActionProviderClassName != null;
            if (hasActionProvider && itemActionViewLayout == 0 && itemActionViewClassName == null) {
                itemActionProvider = newInstance(itemActionProviderClassName,
                        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE,
                        mActionProviderConstructorArguments);
            } else {
                if (hasActionProvider) {
                    Log.w(LOG_TAG, "Ignoring attribute 'actionProviderClass'."
                            + " Action view already specified.");
                }
                itemActionProvider = null;
            }

            a.recycle();

            itemAdded = false;
        }

        @DSSafe(DSCat.SAFE_LIST)
        private char getShortcut(String shortcutString) {
            if (shortcutString == null) {
                return 0;
            } else {
                return shortcutString.charAt(0);
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        private void setItem(MenuItem item) {
            item.setChecked(itemChecked)
                    .setVisible(itemVisible)
                    .setEnabled(itemEnabled)
                    .setCheckable(itemCheckable >= 1)
                    .setTitleCondensed(itemTitleCondensed)
                    .setIcon(itemIconResId)
                    .setAlphabeticShortcut(itemAlphabeticShortcut)
                    .setNumericShortcut(itemNumericShortcut);

            if (itemShowAsAction >= 0) {
                MenuItemCompat.setShowAsAction(item, itemShowAsAction);
            }

            if (itemListenerMethodName != null) {
                if (mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot "
                            + "be used within a restricted context");
                }
                item.setOnMenuItemClickListener(
                        new InflatedOnMenuItemClickListener(mRealOwner, itemListenerMethodName));
            }

            final MenuItemImpl impl = item instanceof MenuItemImpl ? (MenuItemImpl) item : null;
            if (itemCheckable >= 2) {
                if (item instanceof MenuItemImpl) {
                    ((MenuItemImpl) item).setExclusiveCheckable(true);
                } else if (item instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) item).setExclusiveCheckable(true);
                }
            }

            boolean actionViewSpecified = false;
            if (itemActionViewClassName != null) {
                View actionView = (View) newInstance(itemActionViewClassName,
                        ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments);
                MenuItemCompat.setActionView(item, actionView);
                actionViewSpecified = true;
            }
            if (itemActionViewLayout > 0) {
                if (!actionViewSpecified) {
                    MenuItemCompat.setActionView(item, itemActionViewLayout);
                    actionViewSpecified = true;
                } else {
                    Log.w(LOG_TAG, "Ignoring attribute 'itemActionViewLayout'."
                            + " Action view already specified.");
                }
            }
            if (itemActionProvider != null) {
                MenuItemCompat.setActionProvider(item, itemActionProvider);
            }
        }

        @DSSafe(DSCat.SAFE_LIST)
        public void addItem() {
            itemAdded = true;
            setItem(menu.add(groupId, itemId, itemCategoryOrder, itemTitle));
        }

        @DSSafe(DSCat.SAFE_LIST)
        public SubMenu addSubMenuItem() {
            itemAdded = true;
            SubMenu subMenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
            setItem(subMenu.getItem());
            return subMenu;
        }

        @DSSafe(DSCat.SAFE_LIST)
        public boolean hasAddedItem() {
            return itemAdded;
        }

        @DSSafe(DSCat.SAFE_LIST)
        @SuppressWarnings("unchecked")
        private <T> T newInstance(String className, Class<?>[] constructorSignature,
                Object[] arguments) {
            try {
                Class<?> clazz = mContext.getClassLoader().loadClass(className);
                Constructor<?> constructor = clazz.getConstructor(constructorSignature);
                return (T) constructor.newInstance(arguments);
            } catch (Exception e) {
                Log.w(LOG_TAG, "Cannot instantiate class: " + className, e);
            }
            return null;
        }
    }
}
