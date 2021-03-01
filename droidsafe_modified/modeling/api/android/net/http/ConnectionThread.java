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


package android.net.http;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.util.Log;
import droidsafe.annotations.*;
import android.content.Context;
import android.os.SystemClock;

class ConnectionThread extends Thread {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.111 -0500", hash_original_field = "F615C13B274003F82E1E923A46F9CFF8", hash_generated_field = "CDF9EFF3FF58D3FBB7389BB4E67088C6")

    static final int WAIT_TIMEOUT = 5000;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.113 -0500", hash_original_field = "A4C5B983B8A34220392241002BA0396C", hash_generated_field = "6434CE10B92F984DC3B223CA51302E19")

    static final int WAIT_TICK = 1000;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.115 -0500", hash_original_field = "E966A0C9EF7AB198CDE57AFDD0799F34", hash_generated_field = "E966A0C9EF7AB198CDE57AFDD0799F34")

    long mCurrentThreadTime;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.117 -0500", hash_original_field = "16BF64DFEFBE8CCBBAB04441B66D83E5", hash_generated_field = "16BF64DFEFBE8CCBBAB04441B66D83E5")

    long mTotalThreadTime;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.119 -0500", hash_original_field = "A503FEFEF44D8F2333FE77DF0C4ADA1F", hash_generated_field = "665647B80D6EB31435625685BA1ECE83")

    private boolean mWaiting;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.121 -0500", hash_original_field = "62B498EC2CE9351B23513BC086ECF42D", hash_generated_field = "1680C4AF884999C7E334262B1B6BB81C")

    private volatile boolean mRunning = true;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.123 -0500", hash_original_field = "B997E37019471EC8FC5B98148C7A8AD7", hash_generated_field = "C458E619396054F78BC926FB81B4386D")

    private Context mContext;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.126 -0500", hash_original_field = "B4112AFF11446C800D3153F102E00E0A", hash_generated_field = "5E862F9BC4AF6A124C2523C96F48D143")

    private RequestQueue.ConnectionManager mConnectionManager;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.128 -0500", hash_original_field = "537B204D0FD240DD08711E1D73085387", hash_generated_field = "AB9F34A7715DF2E623F95D1821A3CBDB")

    private RequestFeeder mRequestFeeder;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.129 -0500", hash_original_field = "9F66E723E60E4F10157FDA7C23C67293", hash_generated_field = "9AA2EA3A2433F5D6F841BEFD54A673B4")

    private int mId;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.133 -0500", hash_original_field = "D468AD0EA1184CE89FA8A45254E2CDD6", hash_generated_field = "D468AD0EA1184CE89FA8A45254E2CDD6")

    Connection mConnection;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.135 -0500", hash_original_method = "49EA97FC2CE469A889BBEB2F2C20CC1F", hash_generated_method = "49EA97FC2CE469A889BBEB2F2C20CC1F")
    
ConnectionThread(Context context,
                     int id,
                     RequestQueue.ConnectionManager connectionManager,
                     RequestFeeder requestFeeder) {
        super();
        mContext = context;
        setName("http" + id);
        mId = id;
        mConnectionManager = connectionManager;
        mRequestFeeder = requestFeeder;
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.137 -0500", hash_original_method = "A5666AE0946DEF68E8FF606C0FDBCF6D", hash_generated_method = "A5666AE0946DEF68E8FF606C0FDBCF6D")
    
void requestStop() {
        synchronized (mRequestFeeder) {
            mRunning = false;
            mRequestFeeder.notify();
        }
    }

    /**
     * Loop until app shutdown. Runs connections in priority
     * order.
     */
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.140 -0500", hash_original_method = "CA4464BE27725D08DF2A2ECD7DD50C0A", hash_generated_method = "F47C9B72D0495D1FB90B4A55DD62F1C2")
    
public void run() {
        android.os.Process.setThreadPriority(
                android.os.Process.THREAD_PRIORITY_DEFAULT +
                android.os.Process.THREAD_PRIORITY_LESS_FAVORABLE);

        // these are used to get performance data. When it is not in the timing,
        // mCurrentThreadTime is 0. When it starts timing, mCurrentThreadTime is
        // first set to -1, it will be set to the current thread time when the
        // next request starts.
        mCurrentThreadTime = 0;
        mTotalThreadTime = 0;

        while (mRunning) {
            if (mCurrentThreadTime == -1) {
                mCurrentThreadTime = SystemClock.currentThreadTimeMillis();
            }

            Request request;

            /* Get a request to process */
            request = mRequestFeeder.getRequest();

            /* wait for work */
            if (request == null) {
                synchronized(mRequestFeeder) {
                    if (HttpLog.LOGV) HttpLog.v("ConnectionThread: Waiting for work");
                    mWaiting = true;
                    try {
                        mRequestFeeder.wait();
                    } catch (InterruptedException e) {
                    }
                    mWaiting = false;
                    if (mCurrentThreadTime != 0) {
                        mCurrentThreadTime = SystemClock
                                .currentThreadTimeMillis();
                    }
                }
            } else {
                if (HttpLog.LOGV) HttpLog.v("ConnectionThread: new request " +
                                            request.mHost + " " + request );

                mConnection = mConnectionManager.getConnection(mContext,
                        request.mHost);
                mConnection.processRequests(request);
                if (mConnection.getCanPersist()) {
                    if (!mConnectionManager.recycleConnection(mConnection)) {
                        mConnection.closeConnection();
                    }
                } else {
                    mConnection.closeConnection();
                }
                mConnection = null;

                if (mCurrentThreadTime > 0) {
                    long start = mCurrentThreadTime;
                    mCurrentThreadTime = SystemClock.currentThreadTimeMillis();
                    mTotalThreadTime += mCurrentThreadTime - start;
                }
            }

        }
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:13.142 -0500", hash_original_method = "E5691DA5C4D558B8174DB10F2BE5CF59", hash_generated_method = "A84530E34FD98E5588F55591BE99CCA1")
    
public synchronized String toString() {
        String con = mConnection == null ? "" : mConnection.toString();
        String active = mWaiting ? "w" : "a";
        return "cid " + mId + " " + active + " "  + con;
    }
}

