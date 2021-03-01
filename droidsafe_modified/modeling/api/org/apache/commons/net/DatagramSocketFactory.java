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

 * Copyright 2001-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.apache.commons.net;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/***
 * The DatagramSocketFactory interface provides a means for the
 * programmer to control the creation of datagram sockets and
 * provide his own DatagramSocket implementations for use by all
 * classes derived from
 * {@link org.apache.commons.net.DatagramSocketClient}
 * .
 * This allows you to provide your own DatagramSocket implementations and
 * to perform security checks or browser capability requests before
 * creating a DatagramSocket.
 * <p>
 * <p>
 * @author Daniel F. Savarese
 ***/

public interface DatagramSocketFactory
{

    /***
     * Creates a DatagramSocket on the local host at the first available port.
     * <p>
     * @exception SocketException If the socket could not be created.
     ***/
    @DSSpec(DSCat.INTERNET)
    public DatagramSocket createDatagramSocket() throws SocketException;

    /***
     * Creates a DatagramSocket on the local host at a specified port.
     * <p>
     * @param port The port to use for the socket.
     * @exception SocketException If the socket could not be created.
     ***/
    @DSSpec(DSCat.INTERNET)
    public DatagramSocket createDatagramSocket(int port) throws SocketException;

    /***
     * Creates a DatagramSocket at the specified address on the local host
     * at a specified port.
     * <p>
     * @param port The port to use for the socket.
     * @param laddr  The local address to use.
     * @exception SocketException If the socket could not be created.
     ***/
    @DSSpec(DSCat.INTERNET)
    public DatagramSocket createDatagramSocket(int port, InetAddress laddr)
    throws SocketException;
}
