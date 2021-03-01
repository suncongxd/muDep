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


package android.net;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketOptions;

public class LocalSocket {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.174 -0500", hash_original_field = "C64187E1E8E3968144AF18D9B41A1645", hash_generated_field = "9FC425CCAE80D9162FEB6CEC3E95B3C0")

    private LocalSocketImpl impl;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.177 -0500", hash_original_field = "E6D504C945AB3F2AFF7B311813DA0B9F", hash_generated_field = "40B4D44783DDD878FE14964CDFAD9280")

    private volatile boolean implCreated;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.179 -0500", hash_original_field = "6EA039161543E3A8643CEA026FAED862", hash_generated_field = "998A409B350B5C2296F10AA66491F7E9")

    private LocalSocketAddress localAddress;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.182 -0500", hash_original_field = "B2E2EDC9404AD10BB9AF0AC0088343DD", hash_generated_field = "C0B93BC213B673FF2E47783C561E2823")

    private boolean isBound;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.184 -0500", hash_original_field = "63EA3415F69DFBAE1E285FCFD4110E54", hash_generated_field = "214E866967BF7B324CDA44BDFA9EF130")

    private boolean isConnected;

    /**
     * Creates a AF_LOCAL/UNIX domain stream socket.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.186 -0500", hash_original_method = "FEA4ACACBEC27CDC2E19A445360CDF2B", hash_generated_method = "BB97870F58D7F42B8A597BDBDE78E2D6")
    
public LocalSocket() {
        this(new LocalSocketImpl());
        isBound = false;
        isConnected = false;
    }

    /**
     * for use with AndroidServerSocket
     * @param impl a SocketImpl
     */
    /*package*/ @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.189 -0500", hash_original_method = "2CADB18C95425230AEE84C8462CF6B5E", hash_generated_method = "2CADB18C95425230AEE84C8462CF6B5E")
    
LocalSocket(LocalSocketImpl impl) {
        this.impl = impl;
        this.isConnected = false;
        this.isBound = false;
    }

    /** {@inheritDoc} */
    @DSSource({DSSourceKind.NETWORK})
    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.191 -0500", hash_original_method = "A3B0926C7F2CA904DDE9677BB08BC75D", hash_generated_method = "11030F7C80EE3FD8B8BDA6B693B1BDC1")
    
@Override
    public String toString() {
        return super.toString() + " impl:" + impl;
    }

    /**
     * It's difficult to discern from the spec when impl.create() should be
     * called, but it seems like a reasonable rule is "as soon as possible,
     * but not in a context where IOException cannot be thrown"
     *
     * @throws IOException from SocketImpl.create()
     */
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.193 -0500", hash_original_method = "49E5C1FBB8FADEED10B26703F569758C", hash_generated_method = "0869DD9505DD3649F4764FEE639920D0")
    
private void implCreateIfNeeded() throws IOException {
        if (!implCreated) {
            synchronized (this) {
                if (!implCreated) {
                    try {
                        impl.create(true);
                    } finally {
                        implCreated = true;
                    }
                }
            }
        }
    }

    /**
     * Connects this socket to an endpoint. May only be called on an instance
     * that has not yet been connected.
     *
     * @param endpoint endpoint address
     * @throws IOException if socket is in invalid state or the address does
     * not exist.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.195 -0500", hash_original_method = "FEC40F6E47E3058E55E0DA8C21B29091", hash_generated_method = "093B3CA6BEB21278CDCA9E7AF5C9F77B")
    @DSSpec(DSCat.NETWORK)    
    public void connect(LocalSocketAddress endpoint) throws IOException {
        synchronized (this) {
            if (isConnected) {
                throw new IOException("already connected");
            }

            implCreateIfNeeded();
            impl.connect(endpoint, 0);
            isConnected = true;
            isBound = true;
        }
    }

    /**
     * Binds this socket to an endpoint name. May only be called on an instance
     * that has not yet been bound.
     *
     * @param bindpoint endpoint address
     * @throws IOException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.197 -0500", hash_original_method = "FAE2FBB89E50156A8576EBEB5A91D0D2", hash_generated_method = "3883CF5A1FA2D7D0C186F6895B6D9AF0")
    
public void bind(LocalSocketAddress bindpoint) throws IOException {
        implCreateIfNeeded();

        synchronized (this) {
            if (isBound) {
                throw new IOException("already bound");
            }

            localAddress = bindpoint;
            impl.bind(localAddress);
            isBound = true;
        }
    }

    /**
     * Retrieves the name that this socket is bound to, if any.
     *
     * @return Local address or null if anonymous
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.199 -0500", hash_original_method = "E608923787A6482FF0ABAB03074C01F6", hash_generated_method = "7955BA736BD8AD783B98FFDED7E574D5")
    
public LocalSocketAddress getLocalSocketAddress() {
        return localAddress;
    }

    /**
     * Retrieves the input stream for this instance.
     *
     * @return input stream
     * @throws IOException if socket has been closed or cannot be created.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.202 -0500", hash_original_method = "DEA6C893F972103692387A11DBA05C46", hash_generated_method = "5AD8E3B5C2D779A9393A4F7C2DDFB805")
    
public InputStream getInputStream() throws IOException {
        implCreateIfNeeded();
        return impl.getInputStream();
    }

    /**
     * Retrieves the output stream for this instance.
     *
     * @return output stream
     * @throws IOException if socket has been closed or cannot be created.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.204 -0500", hash_original_method = "7FCFFCB2ACC45D6472F8A8A9AE347E00", hash_generated_method = "7312EA749E0D01671F2B6C4BB1603CB7")
    
public OutputStream getOutputStream() throws IOException {
        implCreateIfNeeded();
        return impl.getOutputStream();
    }

    /**
     * Closes the socket.
     *
     * @throws IOException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.206 -0500", hash_original_method = "0886675D1825D54CCEFC14FCAD88ED18", hash_generated_method = "1A19F7366982B53A293C960C684ED792")
    
public void close() throws IOException {
        implCreateIfNeeded();
        impl.close();
    }

    /**
     * Shuts down the input side of the socket.
     *
     * @throws IOException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.208 -0500", hash_original_method = "BB45D33ED8970F46DE3174E9794A0506", hash_generated_method = "16A2B4F8A647F49C8DEDFD1E178A7954")
    
public void shutdownInput() throws IOException {
        implCreateIfNeeded();
        impl.shutdownInput();
    }

    /**
     * Shuts down the output side of the socket.
     *
     * @throws IOException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.210 -0500", hash_original_method = "932CA0951C2BB2888D24DE8156E5EAB6", hash_generated_method = "BC090058BFA5B6988029F7C8CFDF2182")
    
public void shutdownOutput() throws IOException {
        implCreateIfNeeded();
        impl.shutdownOutput();
    }
    
    @DSSink({DSSinkKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.213 -0500", hash_original_method = "BF44A21BC401EF66CAA8945E3668C8DF", hash_generated_method = "18A71FAC606DBE0F3A474D08FDFA87D6")
    
public void setReceiveBufferSize(int size) throws IOException {
        impl.setOption(SocketOptions.SO_RCVBUF, Integer.valueOf(size));
    }
    
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.215 -0500", hash_original_method = "AB50F13A01F63FCA35BB3E86415EBFEF", hash_generated_method = "D62B9310802CED4E0D138E813EAC13DC")
    
public int getReceiveBufferSize() throws IOException {
        return ((Integer) impl.getOption(SocketOptions.SO_RCVBUF)).intValue();
    }

    @DSSink({DSSinkKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.217 -0500", hash_original_method = "4BF0B1F38FAF8905C2B3E64A190023C5", hash_generated_method = "F273B34F49DF4F5C1F3103C7278215AD")
    
public void setSoTimeout(int n) throws IOException {
        impl.setOption(SocketOptions.SO_TIMEOUT, Integer.valueOf(n));
    }
    
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.219 -0500", hash_original_method = "669C21804ABD7497EBD82667408E0CF6", hash_generated_method = "1EFD550F4DC4E177E51EA27BBA198AD6")
    
public int getSoTimeout() throws IOException {
        return ((Integer) impl.getOption(SocketOptions.SO_TIMEOUT)).intValue();
    }

    @DSSink({DSSinkKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.221 -0500", hash_original_method = "93F75C1311E324895F90364DB9A8A768", hash_generated_method = "9F34F14204F89F22F15013FCABE45C50")
    
public void setSendBufferSize(int n) throws IOException {
        impl.setOption(SocketOptions.SO_SNDBUF, Integer.valueOf(n));
    }
    
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.223 -0500", hash_original_method = "68B16714CB779407E8A17B8680E487D9", hash_generated_method = "042A9BCE624D4B2E9CDB22F5FB0D6F9F")
    
public int getSendBufferSize() throws IOException {
        return ((Integer) impl.getOption(SocketOptions.SO_SNDBUF)).intValue();
    }

    //???SEC
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.225 -0500", hash_original_method = "F9B2122C93F2E3BBD4E1C8511037E48C", hash_generated_method = "B54A71310F000EE8A50629AFA7C35DCB")
    
public LocalSocketAddress getRemoteSocketAddress() {
        throw new UnsupportedOperationException();
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.228 -0500", hash_original_method = "B1255CDE181FFAAB8CB434FB98B99FFF", hash_generated_method = "568FD6DA5B41E6A1EA96B85C0A52DDFF")
    
public synchronized boolean isConnected() {
        return isConnected;
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.230 -0500", hash_original_method = "5FD66514528CC1615B69519702A1D1B0", hash_generated_method = "3B38A31536E8AE445AD8BDE280A4B68A")
    
public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.232 -0500", hash_original_method = "589AFDD4CA6BF38ABB5F59E26BB3DC6D", hash_generated_method = "F40A87DA4BC926FA3C1B74F808C8A5CF")
    
public synchronized boolean isBound() {
        return isBound;
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.234 -0500", hash_original_method = "569A2E85196AE41DB2C0A92E12B60282", hash_generated_method = "C8D7DBB5E9555CBD31E2656AC14D9F83")
    
public boolean isOutputShutdown() {
        throw new UnsupportedOperationException();
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.236 -0500", hash_original_method = "0B31CF3D7E3CD09D712DD70672C78FD7", hash_generated_method = "DF048B0E30404D0611AAA98CC866DB51")
    
public boolean isInputShutdown() {
        throw new UnsupportedOperationException();
    }

    //???SEC
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.238 -0500", hash_original_method = "9D1281B98C667F4CB7081490F8B4AA52", hash_generated_method = "99E6BE525F4F02638F2AB3F7A0A88BB9")
    
public void connect(LocalSocketAddress endpoint, int timeout)
            throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * Enqueues a set of file descriptors to send to the peer. The queue
     * is one deep. The file descriptors will be sent with the next write
     * of normal data, and will be delivered in a single ancillary message.
     * See "man 7 unix" SCM_RIGHTS on a desktop Linux machine.
     *
     * @param fds non-null; file descriptors to send.
     */
    @DSSink({DSSinkKind.FILE})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.240 -0500", hash_original_method = "27C53C8DB31C3FA2B713C3C2CC55D90F", hash_generated_method = "30A303B268E40703E12176C1F74DEC1C")
    
public void setFileDescriptorsForSend(FileDescriptor[] fds) {
        impl.setFileDescriptorsForSend(fds);
    }

    /**
     * Retrieves a set of file descriptors that a peer has sent through
     * an ancillary message. This method retrieves the most recent set sent,
     * and then returns null until a new set arrives.
     * File descriptors may only be passed along with regular data, so this
     * method can only return a non-null after a read operation.
     *
     * @return null or file descriptor array
     * @throws IOException
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.242 -0500", hash_original_method = "447681287166469914CD051884431B5F", hash_generated_method = "61D7A75BC659D9F6621B870A3FAB763A")
    
public FileDescriptor[] getAncillaryFileDescriptors() throws IOException {
        return impl.getAncillaryFileDescriptors();
    }

    /**
     * Retrieves the credentials of this socket's peer. Only valid on
     * connected sockets.
     *
     * @return non-null; peer credentials
     * @throws IOException
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.244 -0500", hash_original_method = "4616CE50DFB82618FF8C3056DF06E72F", hash_generated_method = "4707C5AFA3100045C28BF3EEE5B466D0")
    
public Credentials getPeerCredentials() throws IOException {
        return impl.getPeerCredentials();
    }

    /**
     * Returns file descriptor or null if not yet open/already closed
     *
     * @return fd or null
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:10.246 -0500", hash_original_method = "48E5039C4FB2B8BAB81B3561EAA65E0E", hash_generated_method = "0FA483774DCFD89C5E0A6792E5C45D30")
    
public FileDescriptor getFileDescriptor() {
        return impl.getFileDescriptor();
    }
    
}

