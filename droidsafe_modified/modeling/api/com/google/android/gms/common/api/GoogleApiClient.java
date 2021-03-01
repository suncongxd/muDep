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

package com.google.android.gms.common.api;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.TimeUnit;

public abstract interface GoogleApiClient
{

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
        public abstract void onConnected (Bundle connectionHint);
        public abstract void onConnectionSuspended (int cause);
    }
    public interface OnConnectionFailedListener {
        public abstract void onConnectionFailed (ConnectionResult result);
    }

    public abstract android.os.Looper getLooper();

    public abstract void connect();

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long  l0, java.util.concurrent.TimeUnit  r1);

    public abstract @DSSafe(DSCat.SAFE_OTHERS)
    void disconnect();

    public abstract void reconnect();

    public abstract void stopAutoManage();

    public abstract @DSSafe(DSCat.SAFE_OTHERS)
    boolean isConnected();

    public abstract boolean isConnecting();

    public abstract @DSSafe(DSCat.SAFE_OTHERS)
    void registerConnectionCallbacks(ConnectionCallbacks  r0);

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks  r0);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks  r0);

    public abstract @DSSafe(DSCat.SAFE_OTHERS)
    void registerConnectionFailedListener(OnConnectionFailedListener  r0);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener  r0);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener  r0);
    
    public class Builder {
    	
    	GoogleApiClient client;
    	@DSSafe(DSCat.SAFE_OTHERS)
        public Builder() {
    		client = new DroidsafeGoogleApiClient();
    	}
    	
    	@DSSafe(DSCat.SAFE_LIST)
        public GoogleApiClient build()  {
    		return client;
    	}
    	
    	@DSSafe(DSCat.SAFE_LIST)
        public Builder addApi(Object api, Object options) {
    		client.addTaint(api.getTaint());
    		client.addTaint(options.getTaint());
    	    return this;
    	}

    	@DSSafe(DSCat.SAFE_LIST)
        public Builder addApi(Object api) {
    		client.addTaint(api.getTaint());
    	    return this;
    	}

    	@DSSafe(DSCat.SAFE_LIST)
        public Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
    		client.registerConnectionCallbacks(listener);
    	    return this;
    	}

    	@DSSafe(DSCat.SAFE_LIST)
        public Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
    		client.registerConnectionFailedListener(listener);
    	    return this;
    	}

    	@DSSafe(DSCat.SAFE_LIST)
        public Builder addScope(Scope scope) {
    		client.addTaint(scope.eP().length());
    	    return this;
    	}

    	public Builder enableAutoManage(FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener unresolvedConnectionFailedListener) {
    		client.registerConnectionFailedListener(unresolvedConnectionFailedListener);
    	    return this;
    	}
    	public Builder setAccountName(String accountName) {
    		client.addTaint(accountName.getTaint());
    	    return this;
    	}
    	public Builder setGravityForPopups(int gravityForPopups) {
    		client.addTaint(gravityForPopups);
    	    return this;
    	}

    	public Builder setHandler(Handler handler) {
    	    return this;
    	}

    	public Builder setViewForPopups(View viewForPopups) {
    	    return this;
    	}

    	public Builder useDefaultAccount() {
    	    return this;
    	}
	}
}
