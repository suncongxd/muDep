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
 */

package droidsafe.concrete;

import java.util.Map;

//Droidsafe Imports
import droidsafe.annotations.*;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IContentProvider;
import android.database.ContentObserver;
import android.net.Uri;

/**
 * Started with android.test.mock.MockContentResolver;
 * 
 * @author mgordon
 *
 */
public class DroidSafeContentResolver extends ContentResolver {
    Map<String, ContentProvider> mProviders;
    private Context context;
    
    /*
     * Creates a local map of providers. This map is used instead of the global map when an
     * API call tries to acquire a provider.
     */
    
    public DroidSafeContentResolver(Context context) {
    	super(context);
    	this.context = context;
    }

    /**
     * Adds access to a provider based on its authority
     *
     * @param name The authority name associated with the provider.
     * @param provider An instance of {@link android.content.ContentProvider} or one of its
     * subclasses, or null.
     */
    public void addProvider(String name, ContentProvider provider) {

        /*
         * Maps the authority to the provider locally.
         */
        mProviders.put(name, provider);
    }

    /** @hide */
    @Override
    protected IContentProvider acquireProvider(Context context, String name) {
        return acquireExistingProvider(context, name);
    }

    /** @hide */
    @Override
    protected IContentProvider acquireExistingProvider(Context context, String name) {

        /*
         * Gets the content provider from the local map
         */
        final ContentProvider provider = mProviders.get(name);

        if (provider != null) {
            return provider.getIContentProvider();
        } else {
            return null;
        }
    }

    /** @hide */
    @Override
    public boolean releaseProvider(IContentProvider provider) {
        return true;
    }

    /**
     * Overrides {@link android.content.ContentResolver#notifyChange(Uri, ContentObserver, boolean)
     * ContentResolver.notifChange(Uri, ContentObserver, boolean)}. All parameters are ignored.
     * The method hides providers linked to MockContentResolver from other observers in the system.
     *
     * @param uri (Ignored) The uri of the content provider.
     * @param observer (Ignored) The observer that originated the change.
     * @param syncToNetwork (Ignored) If true, attempt to sync the change to the network.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @Override
    public void notifyChange(Uri uri,
            ContentObserver observer,
            boolean syncToNetwork) {
    }
}
