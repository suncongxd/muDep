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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: frameworks/base/core/java/android/os/ICancellationSignal.aidl
 */
package android.os;
import droidsafe.annotations.*;
/**
 * @hide
 */
public interface ICancellationSignal extends android.os.IInterface
{
    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends android.os.Binder implements android.os.ICancellationSignal
    {
        private static final java.lang.String DESCRIPTOR = "android.os.ICancellationSignal";
        /** Construct the stub at attach it to the interface. */
        public Stub()
        {
            this.attachInterface(this, DESCRIPTOR);
        }
        /**
         * Cast an IBinder object into an android.os.ICancellationSignal interface,
         * generating a proxy if needed.
         */
        public static android.os.ICancellationSignal asInterface(android.os.IBinder obj)
        {
            if ((obj==null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin!=null)&&(iin instanceof android.os.ICancellationSignal))) {
                return ((android.os.ICancellationSignal)iin);
            }
            return new android.os.ICancellationSignal.Stub.Proxy(obj);
        }
        @Override public android.os.IBinder asBinder()
        {
            return this;
        }
        @DSSafe(DSCat.SAFE_LIST)
        @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
        {
            switch (code)
                {
                case INTERFACE_TRANSACTION:
                    {
                        reply.writeString(DESCRIPTOR);
                        return true;
                    }
                case TRANSACTION_cancel:
                    {
                        data.enforceInterface(DESCRIPTOR);
                        this.cancel();
                        return true;
                    }
                }
            return super.onTransact(code, data, reply, flags);
        }
        private static class Proxy implements android.os.ICancellationSignal
        {
            private android.os.IBinder mRemote;
            Proxy(android.os.IBinder remote)
            {
                mRemote = remote;
            }
            @Override public android.os.IBinder asBinder()
            {
                return mRemote;
            }
            public java.lang.String getInterfaceDescriptor()
            {
                return DESCRIPTOR;
            }
            @Override public void cancel() throws android.os.RemoteException
            {
                android.os.Parcel _data = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_cancel, _data, null, android.os.IBinder.FLAG_ONEWAY);
                }
                finally {
                    _data.recycle();
                }
            }
        }
        static final int TRANSACTION_cancel = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }
    public void cancel() throws android.os.RemoteException;
}
