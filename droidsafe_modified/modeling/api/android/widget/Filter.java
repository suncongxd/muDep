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
 * Copyright (C) 2007 The Android Open Source Project
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


package android.widget;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.util.Log;
import droidsafe.annotations.*;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public abstract class Filter {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.028 -0500", hash_original_field = "06735F1D7E8213DB4DE5C9BB0F124BC6", hash_generated_field = "793222EB5D405FC1EB1B7701D18817D3")

    private static final String LOG_TAG = "Filter";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.031 -0500", hash_original_field = "2CA3CA6B8A881CF91799949F6B4EAC10", hash_generated_field = "5FAF72EED80F110BC27332069A109797")
    
    private static final String THREAD_NAME = "Filter";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.033 -0500", hash_original_field = "53A8F98E96147FE8AD28FA0550BDD35D", hash_generated_field = "59508486D6172BE2A85FA3BBA6DCA371")

    private static final int FILTER_TOKEN = 0xD0D0F00D;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.035 -0500", hash_original_field = "9963632F8814EF7A7B73A6CB641F7D1E", hash_generated_field = "F92F74445173D271FAFC43822262A61D")

    private static final int FINISH_TOKEN = 0xDEADBEEF;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.037 -0500", hash_original_field = "BAEA207645ACF5A576E10EB1F988B5CA", hash_generated_field = "95F8A0CD9492F0837A9DA3FE5B46EFD4")

    private Handler mThreadHandler;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.039 -0500", hash_original_field = "6E088CB61BF73A2A3667E617035F5D1B", hash_generated_field = "2CD0385D10AF873EE6FED55190565ADB")

    private Handler mResultHandler;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.042 -0500", hash_original_field = "17F51DB4A25E80590853906CB798A74A", hash_generated_field = "80A4CE79B874839FC5345637AF2E83E5")

    private Delayer mDelayer;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.044 -0500", hash_original_field = "83DB9DCBBD2D99A708D9A1934D5CD5AB", hash_generated_field = "E59081251AD157907AA4CE8B3D8E654E")

    private final Object mLock = new Object();

    /**
     * <p>Creates a new asynchronous filter.</p>
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.046 -0500", hash_original_method = "3A43B55E5FFADA496A080A897709E281", hash_generated_method = "EE9897E67685D32AA6B24C02E2653ECB")
    
public Filter() {
        mResultHandler = new ResultsHandler();
    }

    /**
     * Provide an interface that decides how long to delay the message for a given query.  Useful
     * for heuristics such as posting a delay for the delete key to avoid doing any work while the
     * user holds down the delete key.
     *
     * @param delayer The delayer.
     * @hide
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.048 -0500", hash_original_method = "945149F2F16183884CBDA739B056A27B", hash_generated_method = "13D5ED73D9E98AE2B5EA759CC1559CD7")
    
public void setDelayer(Delayer delayer) {
        synchronized (mLock) {
            mDelayer = delayer;
        }
    }

    /**
     * <p>Starts an asynchronous filtering operation. Calling this method
     * cancels all previous non-executed filtering requests and posts a new
     * filtering request that will be executed later.</p>
     *
     * @param constraint the constraint used to filter the data
     *
     * @see #filter(CharSequence, android.widget.Filter.FilterListener)
     */
    @DSComment("Callback invoke happens in this method")
    @DSSpec(DSCat.CALLBACK_INVOKE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.050 -0500", hash_original_method = "547418F5BDE1A6BA9449D3E97456DC38", hash_generated_method = "D8F21C75E056C2BC79D4899B662ABAFE")
    
public final void filter(CharSequence constraint) {
        filter(constraint, null);
    }
    
    protected static class FilterResults {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.066 -0500", hash_original_field = "FB2332CB3D5BCF18F1EDBBFDCD55F2FA", hash_generated_field = "D3703C87CDEFAF1A6A55F65201CACA1D")

        public Object values;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.068 -0500", hash_original_field = "B83BF7ED7F5719DA923E1BC0AC69952B", hash_generated_field = "21B1DF5337CCD3E0731435FB1361ED67")

        public int count;
        @DSComment("constructor")
        @DSSafe(DSCat.SAFE_OTHERS)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.064 -0500", hash_original_method = "4DBCD611B55919CEC0E695E7409A22D3", hash_generated_method = "CCF8E93BD33E3F617EF0EEFB9A043275")
        
public FilterResults() {
            // nothing to see here
        }
        
    }
    
    private class RequestHandler extends Handler {
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.075 -0500", hash_original_method = "6F9C1865AABC7FABBD177D227607C2E3", hash_generated_method = "C9051BA25B2E69DF9F2C8C3DDC61B275")
        
public RequestHandler(Looper looper) {
            super(looper);
        }

        /**
         * <p>Handles filtering requests by calling
         * {@link Filter#performFiltering} and then sending a message
         * with the results to the results handler.</p>
         *
         * @param msg the filtering request
         */
        @DSSafe(DSCat.IPC_CALLBACK)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.078 -0500", hash_original_method = "B041560ADE0D1FD357DB3E4E690D9D5B", hash_generated_method = "82C4AAA173D4D7D040728FF5903727CE")
        
public void handleMessage(Message msg) {
            int what = msg.what;
            Message message;
            switch (what) {
                case FILTER_TOKEN:
                    RequestArguments args = (RequestArguments) msg.obj;
                    try {
                        args.results = performFiltering(args.constraint);
                    } catch (Exception e) {
                        args.results = new FilterResults();
                        Log.w(LOG_TAG, "An exception occured during performFiltering()!", e);
                    } finally {
                        message = mResultHandler.obtainMessage(what);
                        message.obj = args;
                        message.sendToTarget();
                    }

                    synchronized (mLock) {
                        if (mThreadHandler != null) {
                            Message finishMessage = mThreadHandler.obtainMessage(FINISH_TOKEN);
                            mThreadHandler.sendMessageDelayed(finishMessage, 3000);
                        }
                    }
                    break;
                case FINISH_TOKEN:
                    synchronized (mLock) {
                        if (mThreadHandler != null) {
                            mThreadHandler.getLooper().quit();
                            mThreadHandler = null;
                        }
                    }
                    break;
            }
        }
        
    }
    
    private class ResultsHandler extends Handler {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:59.721 -0400", hash_original_method = "1E08EF8EFA185C4653CFFF0D7408C61C", hash_generated_method = "1E08EF8EFA185C4653CFFF0D7408C61C")
        public ResultsHandler ()
        {
            //Synthesized constructor
        }
        /**
         * <p>Messages received from the request handler are processed in the
         * UI thread. The processing involves calling
         * {@link Filter#publishResults(CharSequence,
         * android.widget.Filter.FilterResults)}
         * to post the results back in the UI and then notifying the listener,
         * if any.</p> 
         *
         * @param msg the filtering results
         */
        @DSSafe(DSCat.IPC_CALLBACK)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.083 -0500", hash_original_method = "1BAD86CE9386A91CEFB9E87EDE15880F", hash_generated_method = "39A55FE0C355D23E3AC94EB74E8D27F6")
        
@Override
        public void handleMessage(Message msg) {
            RequestArguments args = (RequestArguments) msg.obj;

            publishResults(args.constraint, args.results);
            if (args.listener != null) {
                int count = args.results != null ? args.results.count : -1;
                args.listener.onFilterComplete(count);
            }
        }
        
    }
    
    private static class RequestArguments {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.088 -0500", hash_original_field = "A990B1A3EAE194295F4523C8D67C8224", hash_generated_field = "A990B1A3EAE194295F4523C8D67C8224")

        CharSequence constraint;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.089 -0500", hash_original_field = "D4CDE16C0546A2B2BFBBEE27F9F88E28", hash_generated_field = "D4CDE16C0546A2B2BFBBEE27F9F88E28")

        FilterListener listener;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.091 -0500", hash_original_field = "31AA06C32DAD475E424FD501DC622C26", hash_generated_field = "31AA06C32DAD475E424FD501DC622C26")

        FilterResults results;
        
        @DSComment("Private Method")
        @DSBan(DSCat.PRIVATE_METHOD)
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:59.721 -0400", hash_original_method = "6E50E95F99D18D81DCBEFDBA0A1A41EC", hash_generated_method = "6E50E95F99D18D81DCBEFDBA0A1A41EC")
        public RequestArguments ()
        {
            //Synthesized constructor
        }

    }
    
    public static interface FilterListener {
        
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        public void onFilterComplete(int count);
    }
    
    public interface Delayer {
        
        @DSComment("Abstract Method")
        @DSSpec(DSCat.ABSTRACT_METHOD)
        long getPostingDelay(CharSequence constraint);
    }

    /**
     * <p>Starts an asynchronous filtering operation. Calling this method
     * cancels all previous non-executed filtering requests and posts a new
     * filtering request that will be executed later.</p>
     *
     * <p>Upon completion, the listener is notified.</p>
     *
     * @param constraint the constraint used to filter the data
     * @param listener a listener notified upon completion of the operation
     *
     * @see #filter(CharSequence)
     * @see #performFiltering(CharSequence)
     * @see #publishResults(CharSequence, android.widget.Filter.FilterResults)
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.053 -0500", hash_original_method = "72132B08B36B7C63B817B4EDB2584A0E", hash_generated_method = "14BEA71A118655F166B81BAB279E61A4")
    @DSVerified("Calling/dispatching callbacks")
    @DSSafe(DSCat.ANDROID_CALLBACK)
public final void filter(CharSequence constraint, FilterListener listener) {
        //Original code delegates processing to the mThreadHandler 
        //The model performs it directly here as a model
        performFiltering(constraint);
        if (listener != null) {
            listener.onFilterComplete(DSUtils.FAKE_INT);
        }
        
        FilterResults results = new FilterResults();
        publishResults(constraint, results);

        //Original:
        /*
        synchronized (mLock) {
            if (mThreadHandler == null) {
                HandlerThread thread = new HandlerThread(
                        THREAD_NAME, android.os.Process.THREAD_PRIORITY_BACKGROUND);
                thread.start();
                mThreadHandler = new RequestHandler(thread.getLooper());
            }

            final long delay = (mDelayer == null) ? 0 : mDelayer.getPostingDelay(constraint);
            
            Message message = mThreadHandler.obtainMessage(FILTER_TOKEN);
    
            RequestArguments args = new RequestArguments();
            // make sure we use an immutable copy of the constraint, so that
            // it doesn't change while the filter operation is in progress
            args.constraint = constraint != null ? constraint.toString() : null;
            args.listener = listener;
            message.obj = args;
    
            mThreadHandler.removeMessages(FILTER_TOKEN);
            mThreadHandler.removeMessages(FINISH_TOKEN);
            mThreadHandler.sendMessageDelayed(message, delay);
        }
        */
    }

    /**
     * <p>Invoked in a worker thread to filter the data according to the
     * constraint. Subclasses must implement this method to perform the
     * filtering operation. Results computed by the filtering operation
     * must be returned as a {@link android.widget.Filter.FilterResults} that
     * will then be published in the UI thread through
     * {@link #publishResults(CharSequence,
     * android.widget.Filter.FilterResults)}.</p>
     *
     * <p><strong>Contract:</strong> When the constraint is null, the original
     * data must be restored.</p>
     *
     * @param constraint the constraint used to filter the data
     * @return the results of the filtering operation
     *
     * @see #filter(CharSequence, android.widget.Filter.FilterListener)
     * @see #publishResults(CharSequence, android.widget.Filter.FilterResults)
     * @see android.widget.Filter.FilterResults
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.056 -0500", hash_original_method = "5D9C7EB89D72F26BDF5B529628AEC808", hash_generated_method = "928692DC9D126A81A52E50557C0E61D0")
    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS) 
protected abstract FilterResults performFiltering(CharSequence constraint);

    /**
     * <p>Invoked in the UI thread to publish the filtering results in the
     * user interface. Subclasses must implement this method to display the
     * results computed in {@link #performFiltering}.</p>
     *
     * @param constraint the constraint used to filter the data
     * @param results the results of the filtering operation
     *
     * @see #filter(CharSequence, android.widget.Filter.FilterListener)
     * @see #performFiltering(CharSequence)
     * @see android.widget.Filter.FilterResults
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.059 -0500", hash_original_method = "AD07DA575512DAC3A3DE0441CEAE2598", hash_generated_method = "60F345BFA6DF36FA0B4D122993D1B1DD")
    
protected abstract void publishResults(CharSequence constraint,
            FilterResults results);

    /**
     * <p>Converts a value from the filtered set into a CharSequence. Subclasses
     * should override this method to convert their results. The default
     * implementation returns an empty String for null values or the default
     * String representation of the value.</p>
     *
     * @param resultValue the value to convert to a CharSequence
     * @return a CharSequence representing the value
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:31:37.061 -0500", hash_original_method = "5D0951FE3DB94952122FC0A58DABB40C", hash_generated_method = "548BF281A0CB22879A28C8B5DAAEBC85")

    @DSVerified
    @DSSafe(DSCat.SAFE_OTHERS)
public CharSequence convertResultToString(Object resultValue) {
        return resultValue == null ? "" : resultValue.toString();
    }
}

