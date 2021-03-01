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


package android.content;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.DebugUtils;

public class Loader<D> {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.622 -0500", hash_original_field = "9F66E723E60E4F10157FDA7C23C67293", hash_generated_field = "9F66E723E60E4F10157FDA7C23C67293")

    int mId;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.624 -0500", hash_original_field = "3E73DDB57866D56D9C2443D15644A1EA", hash_generated_field = "3E73DDB57866D56D9C2443D15644A1EA")

    OnLoadCompleteListener<D> mListener;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.626 -0500", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "B997E37019471EC8FC5B98148C7A8AD7")

    Context mContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.628 -0500", hash_original_field = "5D947BF952E8653BAADEA609028A78F9", hash_generated_field = "5D947BF952E8653BAADEA609028A78F9")

    boolean mStarted = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.630 -0500", hash_original_field = "688AFAAD56F5DDB79630BAB635796C3C", hash_generated_field = "688AFAAD56F5DDB79630BAB635796C3C")

    boolean mAbandoned = false;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.632 -0500", hash_original_field = "359194BB54288CCCE4CAFE12392EC644", hash_generated_field = "359194BB54288CCCE4CAFE12392EC644")

    boolean mReset = true;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.634 -0500", hash_original_field = "799325477E4C0AD32C3DFCC8D4F71211", hash_generated_field = "799325477E4C0AD32C3DFCC8D4F71211")

    boolean mContentChanged = false;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.649 -0500", hash_original_method = "6088A27F132FBE0E650A99C21779299A", hash_generated_method = "9F6ED2F092FC52CF1EF86EAB72AB0992")
    
public Loader(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * Sends the result of the load to the registered listener. Should only be called by subclasses.
     *
     * Must be called from the process's main thread.
     *
     * @param data the result of the load
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.651 -0500", hash_original_method = "7AF94F59AC743CB047D54940F8BB69AC", hash_generated_method = "5FD77500856C7DA55A21EC1D34CDB499")
    
public void deliverResult(D data) {
        if (mListener != null) {
            mListener.onLoadComplete(this, data);
        }
    }

    /**
     * @return an application context retrieved from the Context passed to the constructor.
     */
    @DSComment("General android operation, no security concern")
    @DSSafe(DSCat.OS_GENERAL)
    @DSSource({DSSourceKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.653 -0500", hash_original_method = "4F4EBC54D108D66F416C93B46580E117", hash_generated_method = "BA026F6873AF9B17E96AB49AFB6CEE03")
    
public Context getContext() {
        return mContext;
    }

    /**
     * @return the ID of this loader
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.655 -0500", hash_original_method = "849E83C6BA01C72387E236CA4FAA38B9", hash_generated_method = "11DEA77066B2A20ED28C130805C2B9DE")
    
public int getId() {
        return mId;
    }

    /**
     * Registers a class that will receive callbacks when a load is complete.
     * The callback will be called on the process's main thread so it's safe to
     * pass the results to widgets.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.657 -0500", hash_original_method = "F7FC6EAD1080F4315E95C53063FFE31D", hash_generated_method = "6F14E7D5CBB62D3846689962BA66AFE8")
    
public void registerListener(int id, OnLoadCompleteListener<D> listener) {
        if (mListener != null) {
            throw new IllegalStateException("There is already a listener registered");
        }
        mListener = listener;
        mId = id;
        if (listener != null) {
            listener.onLoadComplete(this, null);
        }
    }

    /**
     * Remove a listener that was previously added with {@link #registerListener}.
     *
     * Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.659 -0500", hash_original_method = "F0B8962C538754B5FF8377ACC78D9441", hash_generated_method = "9F30B196EA73C4F76C09270F56611058")
    
public void unregisterListener(OnLoadCompleteListener<D> listener) {
        if (mListener == null) {
            throw new IllegalStateException("No listener register");
        }
        if (mListener != listener) {
            throw new IllegalArgumentException("Attempting to unregister the wrong listener");
        }
        mListener = null;
    }

    /**
     * Return whether this load has been started.  That is, its {@link #startLoading()}
     * has been called and no calls to {@link #stopLoading()} or
     * {@link #reset()} have yet been made.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.661 -0500", hash_original_method = "4FF549E03853BC8FE3FDFAEBF99B8097", hash_generated_method = "011F17A6E1B0A4655AC9CB141825EFD5")
    
public boolean isStarted() {
        return mStarted;
    }

    /**
     * Return whether this loader has been abandoned.  In this state, the
     * loader <em>must not</em> report any new data, and <em>must</em> keep
     * its last reported data valid until it is finally reset.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.664 -0500", hash_original_method = "A2FBF2FF6E2191DA1A91C50D7F722DEF", hash_generated_method = "376AAACABE682930F4AC5377B5C459AD")
    
public boolean isAbandoned() {
        return mAbandoned;
    }

    /**
     * Return whether this load has been reset.  That is, either the loader
     * has not yet been started for the first time, or its {@link #reset()}
     * has been called.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.666 -0500", hash_original_method = "6F78AC76FABAE5B00729D2DF0B0C81EB", hash_generated_method = "BB9BBFFEC1CEA5136C58814820AD315E")
    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS)
public boolean isReset() {
        return mReset;
    }
    @DSVerified
    @DSBan(DSCat.DROIDSAFE_INTERNAL)
    public void droidsafeCallbackHook() {
        onContentChanged();
        onForceLoad();
        onAbandon();
        onStopLoading();
        onReset();
    }
    /**
     * This function will normally be called for you automatically by
     * {@link android.app.LoaderManager} when the associated fragment/activity
     * is being started.  When using a Loader with {@link android.app.LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     *
     * Starts an asynchronous load of the Loader's data. When the result
     * is ready the callbacks will be called on the process's main thread.
     * If a previous load has been completed and is still valid
     * the result may be passed to the callbacks immediately.
     * The loader will monitor the source of
     * the data set and may deliver future callbacks if the source changes.
     * Calling {@link #stopLoading} will stop the delivery of callbacks.
     *
     * <p>This updates the Loader's internal state so that
     * {@link #isStarted()} and {@link #isReset()} will return the correct
     * values, and then calls the implementation's {@link #onStartLoading()}.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.669 -0500", hash_original_method = "8E7A6B4B56209ADF45890FC32754A367", hash_generated_method = "A8FDC28840DC97D7B5C895BB675AFA80")
    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS) 
public final void startLoading() {
        mStarted = true;
        mReset = false;
        mAbandoned = false;
        onStartLoading();
        droidsafeCallbackHook();
    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.671 -0500", hash_original_method = "DA06EE0B2F861EB56F065785FCE355AD", hash_generated_method = "82A80F2F9384B501AB683D80A79AB3A5")
    
protected void onStartLoading() {
    }

    /**
     * Force an asynchronous load. Unlike {@link #startLoading()} this will ignore a previously
     * loaded data set and load a new one.  This simply calls through to the
     * implementation's {@link #onForceLoad()}.  You generally should only call this
     * when the loader is started -- that is, {@link #isStarted()} returns true.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSComment("Force loading content, event handler may also be invoked")
    @DSSpec(DSCat.ANDROID_LOADER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.673 -0500", hash_original_method = "E102AD0C3BB2F8D5B106A9E25CAC340A", hash_generated_method = "7C65C609A676021DC6E00C13415FEFCD")
    
public void forceLoad() {
        onForceLoad();
    }

    /**
     * Subclasses must implement this to take care of requests to {@link #forceLoad()}.
     * This will always be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.675 -0500", hash_original_method = "FE3F333F9E40E18C50A63599879C8FC2", hash_generated_method = "0085AE78BD24D90851730D9CC9CFF4EA")
    
protected void onForceLoad() {
    }

    /**
     * This function will normally be called for you automatically by
     * {@link android.app.LoaderManager} when the associated fragment/activity
     * is being stopped.  When using a Loader with {@link android.app.LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     *
     * <p>Stops delivery of updates until the next time {@link #startLoading()} is called.
     * Implementations should <em>not</em> invalidate their data at this point --
     * clients are still free to use the last data the loader reported.  They will,
     * however, typically stop reporting new data if the data changes; they can
     * still monitor for changes, but must not report them to the client until and
     * if {@link #startLoading()} is later called.
     *
     * <p>This updates the Loader's internal state so that
     * {@link #isStarted()} will return the correct
     * value, and then calls the implementation's {@link #onStopLoading()}.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.677 -0500", hash_original_method = "98899B741F509D9E0CD920B3A41ABA16", hash_generated_method = "3F87F9E134A29D5D691BEA0A3D6F7DB5")
    
public void stopLoading() {
        mStarted = false;
        onStopLoading();
    }

    /**
     * Subclasses must implement this to take care of stopping their loader,
     * as per {@link #stopLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #stopLoading()}.
     * This will always be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.679 -0500", hash_original_method = "61C04D3A0F64307AA142FA0229BCD9C9", hash_generated_method = "E65242F5E7BCCB09D085C1F6A436A2F7")
    
protected void onStopLoading() {
    }

    /**
     * This function will normally be called for you automatically by
     * {@link android.app.LoaderManager} when restarting a Loader.  When using
     * a Loader with {@link android.app.LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     *
     * Tell the Loader that it is being abandoned.  This is called prior
     * to {@link #reset} to have it retain its current data but not report
     * any new data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.681 -0500", hash_original_method = "ACB324D8F1768E7142E127CF7A1A7031", hash_generated_method = "48EA11BD72707F304B6A1EE7D8D93FD6")
    @DSVerified
    @DSSafe(DSCat.ANDROID_CALLBACK)
public void abandon() {
        mAbandoned = true;
        onAbandon();
    }
    
    /**
     * Subclasses implement this to take care of being abandoned.  This is
     * an optional intermediate state prior to {@link #onReset()} -- it means that
     * the client is no longer interested in any new data from the loader,
     * so the loader must not report any further updates.  However, the
     * loader <em>must</em> keep its last reported data valid until the final
     * {@link #onReset()} happens.  You can retrieve the current abandoned
     * state with {@link #isAbandoned}.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.683 -0500", hash_original_method = "A161936916B264DFAEE5DB9D8DEA7F5D", hash_generated_method = "CBC00E77921BBCC552F1B3DA797F6D5F")
    @DSVerified
    @DSSafe(DSCat.ANDROID_CALLBACK)
protected void onAbandon() {        
    }
    
    /**
     * This function will normally be called for you automatically by
     * {@link android.app.LoaderManager} when destroying a Loader.  When using
     * a Loader with {@link android.app.LoaderManager},
     * you <em>must not</em> call this method yourself, or you will conflict
     * with its management of the Loader.
     *
     * Resets the state of the Loader.  The Loader should at this point free
     * all of its resources, since it may never be called again; however, its
     * {@link #startLoading()} may later be called at which point it must be
     * able to start running again.
     *
     * <p>This updates the Loader's internal state so that
     * {@link #isStarted()} and {@link #isReset()} will return the correct
     * values, and then calls the implementation's {@link #onReset()}.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.685 -0500", hash_original_method = "1D7BF800397B2CC75C7600D5EBC7C29A", hash_generated_method = "E573CB7964BA333ED5A8B0E9DFBCEE8D")
    @DSVerified
    @DSSafe(DSCat.ANDROID_CALLBACK)
public void reset() {
        onReset();
        mReset = true;
        mStarted = false;
        mAbandoned = false;
        mContentChanged = false;
    }

    /**
     * Subclasses must implement this to take care of resetting their loader,
     * as per {@link #reset()}.  This is not called by clients directly,
     * but as a result of a call to {@link #reset()}.
     * This will always be called from the process's main thread.
     */
    @DSComment("normal android callback")
    @DSSafe(DSCat.ANDROID_CALLBACK)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.687 -0500", hash_original_method = "16611E3F48156A317644CA113F750BCD", hash_generated_method = "7263AB7619FC664C9743FCE8FF63E928")
    
protected void onReset() {
    }

    /**
     * Take the current flag indicating whether the loader's content had
     * changed while it was stopped.  If it had, true is returned and the
     * flag is cleared.
     */
    @DSComment("General android operation, no security concern")
    @DSSafe(DSCat.OS_GENERAL)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.689 -0500", hash_original_method = "C55C1C662CB28045D31932504C1021C3", hash_generated_method = "FB79F93498345AEA72EC028E85462AAE")
    
public boolean takeContentChanged() {
        boolean res = mContentChanged;
        mContentChanged = false;
        return res;
    }
    
    /**
     * Called when {@link ForceLoadContentObserver} detects a change.  The
     * default implementation checks to see if the loader is currently started;
     * if so, it simply calls {@link #forceLoad()}; otherwise, it sets a flag
     * so that {@link #takeContentChanged()} returns true.
     *
     * <p>Must be called from the process's main thread.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.692 -0500", hash_original_method = "9CACF0CE278273BE0AB1B5F570D8CB20", hash_generated_method = "0BBB21E67670D69FBD1DFC64D6E42AA5")
    @DSVerified
    @DSSafe(DSCat.ANDROID_CALLBACK)
public void onContentChanged() {
        if (mStarted) {
            forceLoad();
        } else {
            // This loader has been stopped, so we don't want to load
            // new data right now...  but keep track of it changing to
            // refresh later if we start again.
            mContentChanged = true;
        }
    }

    /**
     * For debugging, converts an instance of the Loader's data class to
     * a string that can be printed.  Must handle a null data.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.694 -0500", hash_original_method = "F5B015CDDEBA6C005C6DD563385EE3D7", hash_generated_method = "F15119CA027EE50228CCEEB28B580515")
    
public String dataToString(D data) {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(data, sb);
        sb.append("}");
        return sb.toString();
    }

    @DSSafe(DSCat.SAFE_LIST)
    
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.697 -0500", hash_original_method = "7AA2EFEA4BBA4CB5078AB2979053EAD3", hash_generated_method = "35579B681AC67154C4322D5C32537DDC")
    
@Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        DebugUtils.buildShortClassTag(this, sb);
        sb.append(" id=");
        sb.append(mId);
        sb.append("}");
        return sb.toString();
    }

    /**
     * Print the Loader's state into the given stream.
     *
     * @param prefix Text to print at the front of each line.
     * @param fd The raw file descriptor that the dump is being sent to.
     * @param writer A PrintWriter to which the dump is to be set.
     * @param args Additional arguments to the dump request.
     */
    @DSSink({DSSinkKind.FILE})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.699 -0500", hash_original_method = "DC77AF55A2A9006E9EA4E79F1D0E0673", hash_generated_method = "96B5713FB4F642D42FB97E334FFFFC83")
    
public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.print(prefix); writer.print("mId="); writer.print(mId);
                writer.print(" mListener="); writer.println(mListener);
        writer.print(prefix); writer.print("mStarted="); writer.print(mStarted);
                writer.print(" mContentChanged="); writer.print(mContentChanged);
                writer.print(" mAbandoned="); writer.print(mAbandoned);
                writer.print(" mReset="); writer.println(mReset);
    }
    
    public final class ForceLoadContentObserver extends ContentObserver {
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.637 -0500", hash_original_method = "B9E73A5B4B60A8E0702AD317D1848844", hash_generated_method = "D97E27512932796789D4DE052DC12583")
        
public ForceLoadContentObserver() {
            super(new Handler());
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.639 -0500", hash_original_method = "0AAB5F0B0973D030DF154EF2BA0EE06F", hash_generated_method = "11A1AD60FCFEC7497B90886D427B8601")
        
@Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:34:41.642 -0500", hash_original_method = "E96460F373279AF9AB8806E159ABA424", hash_generated_method = "FE6E4E239777D858CE4B7F313EA96B2A")
        
@Override
        public void onChange(boolean selfChange) {
            onContentChanged();
        }
        
    }
    
    public interface OnLoadCompleteListener<D> {
        
        public void onLoadComplete(Loader<D> loader, D data);
    }
    
}

