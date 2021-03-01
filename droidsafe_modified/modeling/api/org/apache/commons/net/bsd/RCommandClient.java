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


package org.apache.commons.net.bsd;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.apache.commons.net.io.SocketInputStream;

/***
 * RCommandClient is very similar to
 * {@link org.apache.commons.net.bsd.RExecClient},
 * from which it is derived, and implements the rcmd() facility that
 * first appeared in 4.2BSD Unix.  rcmd() is the facility used by the rsh
 * (rshell) and other commands to execute a command on another machine
 * from a trusted host without issuing a password.  The trust relationship
 * between two machines is established by the contents of a machine's
 * /etc/hosts.equiv file and a user's .rhosts file.  These files specify
 * from which hosts and accounts on those hosts rcmd() requests will be
 * accepted.  The only additional measure for establishing trust is that
 * all client connections must originate from a port between 512 and 1023.
 * Consequently, there is an upper limit to the number of rcmd connections
 * that can be running simultaneously.   The required ports are reserved
 * ports on Unix systems, and can only be bound by a
 * process running with root permissions (to accomplish this rsh, rlogin,
 * and related commands usualy have the suid bit set).  Therefore, on a
 * Unix system, you will only be able to successfully use the RCommandClient
 * class if the process runs as root.  However, there is no such restriction
 * on Windows95 and some other systems.  The security risks are obvious.
 * However, when carefully used, rcmd() can be very useful when used behind
 * a firewall.
 * <p>
 * As with virtually all of the client classes in org.apache.commons.net, this
 * class derives from SocketClient.  But it overrides most of its connection
 * methods so that the local Socket will originate from an acceptable
 * rshell port.  The way to use RCommandClient is to first connect
 * to the server, call the {@link #rcommand  rcommand() } method,
 * and then
 * fetch the connection's input, output, and optionally error streams.
 * Interaction with the remote command is controlled entirely through the
 * I/O streams.  Once you have finished processing the streams, you should
 * invoke {@link org.apache.commons.net.bsd.RExecClient#disconnect disconnect() }
 *  to clean up properly.
 * <p>
 * By default the standard output and standard error streams of the
 * remote process are transmitted over the same connection, readable
 * from the input stream returned by
 * {@link org.apache.commons.net.bsd.RExecClient#getInputStream getInputStream() }
 * .  However, it is
 * possible to tell the rshd daemon to return the standard error
 * stream over a separate connection, readable from the input stream
 * returned by {@link org.apache.commons.net.bsd.RExecClient#getErrorStream getErrorStream() }
 * .  You
 * can specify that a separate connection should be created for standard
 * error by setting the boolean <code> separateErrorStream </code>
 * parameter of {@link #rcommand  rcommand() } to <code> true </code>.
 * The standard input of the remote process can be written to through
 * the output stream returned by
 * {@link org.apache.commons.net.bsd.RExecClient#getOutputStream getOutputStream() }
 * .
 * <p>
 * <p>
 * @author Daniel F. Savarese
 * @see org.apache.commons.net.SocketClient
 * @see RExecClient
 * @see RLoginClient
 ***/

public class RCommandClient extends RExecClient
{
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.201 -0500", hash_original_field = "6070E15548757F469E270E1861028336", hash_generated_field = "0004F53B41A9731CC51CF4DA4A9FA173")

    public static final int DEFAULT_PORT = 514;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.205 -0500", hash_original_field = "BB67302ED45317EBBC554D6FD600BD1D", hash_generated_field = "06EDE26AC816712E74E28F749FC8EB81")

    public static final int MIN_CLIENT_PORT = 512;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.208 -0500", hash_original_field = "DF916B9948422A0241C0624137F688E9", hash_generated_field = "E1DFCCCBABFEC90DDEDBCE984DAC3F10")

    public static final int MAX_CLIENT_PORT = 1023;

    /***
     * The default RCommandClient constructor.  Initializes the
     * default port to <code> DEFAULT_PORT </code>.
     ***/
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.215 -0500", hash_original_method = "1188D4273316F9B5EDA39665C10C9828", hash_generated_method = "58A6E3A25432CE31F9C1159CB1B0C65B")
    
public RCommandClient()
    {
        setDefaultPort(DEFAULT_PORT);
    }

    // Overrides method in RExecClient in order to implement proper
    // port number limitations.
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.211 -0500", hash_original_method = "DB12E329A7FB34475F6C8066E7AEF166", hash_generated_method = "67D55DE6F9EBE484EC429B0811D221A5")
    
InputStream _createErrorStream() throws IOException
    {
        int localPort;
        ServerSocket server;
        Socket socket;

        localPort = MAX_CLIENT_PORT;
        server = null; // Keep compiler from barfing

        for (localPort = MAX_CLIENT_PORT; localPort >= MIN_CLIENT_PORT; --localPort)
        {
            try
            {
                server = _socketFactory_.createServerSocket(localPort, 1,
                         getLocalAddress());
            }
            catch (SocketException e)
            {
                continue;
            }
            break;
        }

        if (localPort < MIN_CLIENT_PORT)
            throw new BindException("All ports in use.");

        _output_.write(Integer.toString(server.getLocalPort()).getBytes());
        _output_.write('\0');
        _output_.flush();

        socket = server.accept();
        server.close();

        if (isRemoteVerificationEnabled() && !verifyRemote(socket))
        {
            socket.close();
            throw new IOException(
                "Security violation: unexpected connection attempt by " +
                socket.getInetAddress().getHostAddress());
        }

        return (new SocketInputStream(socket, socket.getInputStream()));
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the specified local address using a port in a range
     * acceptable to the BSD rshell daemon.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param host  The remote host.
     * @param port  The port to connect to on the remote host.
     * @param localAddr  The local address to use.
     * @exception SocketException If the socket timeout could not be set.
     * @exception BindException If all acceptable rshell ports are in use.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     ***/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.218 -0500", hash_original_method = "7BF2E593626185B2E6F847E859923DDC", hash_generated_method = "14FD268961B28DCEFB74FD818DE217D5")
    
public void connect(InetAddress host, int port, InetAddress localAddr)
    throws SocketException, BindException, IOException
    {
        int localPort;

        localPort = MAX_CLIENT_PORT;

        for (localPort = MAX_CLIENT_PORT; localPort >= MIN_CLIENT_PORT; --localPort)
        {
            try
            {
                _socket_ =
                    _socketFactory_.createSocket(host, port, localAddr, localPort);
            }
            catch (SocketException e)
            {
                continue;
            }
            break;
        }

        if (localPort < MIN_CLIENT_PORT)
            throw new BindException("All ports in use or insufficient permssion.");

        _connectAction_();
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the current host at a port in a range acceptable
     * to the BSD rshell daemon.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param host  The remote host.
     * @param port  The port to connect to on the remote host.
     * @exception SocketException If the socket timeout could not be set.
     * @exception BindException If all acceptable rshell ports are in use.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     ***/
    @DSSpec(DSCat.INTERNET)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.222 -0500", hash_original_method = "25234E4421C894C46BEFF3C2D6F65EA1", hash_generated_method = "7D529598C4C3E26A3937F0B0DA06109C")
    
public void connect(InetAddress host, int port)
    throws SocketException, IOException
    {
        connect(host, port, InetAddress.getLocalHost());
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the current host at a port in a range acceptable
     * to the BSD rshell daemon.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param hostname  The name of the remote host.
     * @param port  The port to connect to on the remote host.
     * @exception SocketException If the socket timeout could not be set.
     * @exception BindException If all acceptable rshell ports are in use.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     * @exception UnknownHostException If the hostname cannot be resolved.
     ***/
    @DSSpec(DSCat.INTERNET)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.225 -0500", hash_original_method = "3BE6D453A9005138440AA1CF89066F01", hash_generated_method = "CD2C5FD65C265C0E1618FCD65E43913B")
    
public void connect(String hostname, int port)
    throws SocketException, IOException
    {
        connect(InetAddress.getByName(hostname), port, InetAddress.getLocalHost());
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the specified local address using a port in a range
     * acceptable to the BSD rshell daemon.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param hostname  The remote host.
     * @param port  The port to connect to on the remote host.
     * @param localAddr  The local address to use.
     * @exception SocketException If the socket timeout could not be set.
     * @exception BindException If all acceptable rshell ports are in use.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     ***/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.227 -0500", hash_original_method = "717D0337397BFF87A303CCE3D14EBAE6", hash_generated_method = "57D241359F7E821773DEA49E878DA2D4")
    
public void connect(String hostname, int port, InetAddress localAddr)
    throws SocketException, IOException
    {
        connect(InetAddress.getByName(hostname), port, localAddr);
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the specified local address and port. The
     * local port must lie between <code> MIN_CLIENT_PORT </code> and
     * <code> MAX_CLIENT_PORT </code> or an IllegalArgumentException will
     * be thrown.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param host  The remote host.
     * @param port  The port to connect to on the remote host.
     * @param localAddr  The local address to use.
     * @param localPort  The local port to use.
     * @exception SocketException If the socket timeout could not be set.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     * @exception IllegalArgumentException If an invalid local port number
     *            is specified.
     ***/
    @DSSpec(DSCat.INTERNET)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.230 -0500", hash_original_method = "3233024E7A226F908649D648295A5491", hash_generated_method = "0FFFE0C72EE55BA0C5144FABE2688C3C")
    
public void connect(InetAddress host, int port,
                        InetAddress localAddr, int localPort)
    throws SocketException, IOException, IllegalArgumentException
    {
        if (localPort < MIN_CLIENT_PORT || localPort > MAX_CLIENT_PORT)
            throw new IllegalArgumentException("Invalid port number " + localPort);
        super.connect(host, port, localAddr, localPort);
    }

    /***
     * Opens a Socket connected to a remote host at the specified port and
     * originating from the specified local address and port. The
     * local port must lie between <code> MIN_CLIENT_PORT </code> and
     * <code> MAX_CLIENT_PORT </code> or an IllegalArgumentException will
     * be thrown.
     * Before returning, {@link org.apache.commons.net.SocketClient#_connectAction_  _connectAction_() }
     * is called to perform connection initialization actions.
     * <p>
     * @param hostname  The name of the remote host.
     * @param port  The port to connect to on the remote host.
     * @param localAddr  The local address to use.
     * @param localPort  The local port to use.
     * @exception SocketException If the socket timeout could not be set.
     * @exception IOException If the socket could not be opened.  In most
     *  cases you will only want to catch IOException since SocketException is
     *  derived from it.
     * @exception UnknownHostException If the hostname cannot be resolved.
     * @exception IllegalArgumentException If an invalid local port number
     *            is specified.
     ***/
    @DSSpec(DSCat.INTERNET)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.234 -0500", hash_original_method = "4CE4B9C6E018E7C294717E37D7F3A45B", hash_generated_method = "E7FA8F09583B1ACDCE7E48866E111B25")
    
public void connect(String hostname, int port,
                        InetAddress localAddr, int localPort)
    throws SocketException, IOException, IllegalArgumentException
    {
        if (localPort < MIN_CLIENT_PORT || localPort > MAX_CLIENT_PORT)
            throw new IllegalArgumentException("Invalid port number " + localPort);
        super.connect(hostname, port, localAddr, localPort);
    }

    /***
     * Remotely executes a command through the rshd daemon on the server
     * to which the RCommandClient is connected.  After calling this method,
     * you may interact with the remote process through its standard input,
     * output, and error streams.  You will typically be able to detect
     * the termination of the remote process after reaching end of file
     * on its standard output (accessible through
     * {@link #getInputStream  getInputStream() }.  Disconnecting
     * from the server or closing the process streams before reaching
     * end of file will not necessarily terminate the remote process.
     * <p>
     * If a separate error stream is requested, the remote server will
     * connect to a local socket opened by RCommandClient, providing an
     * independent stream through which standard error will be transmitted.
     * The local socket must originate from a secure port (512 - 1023),
     * and rcommand() ensures that this will be so.
     * RCommandClient will also do a simple security check when it accepts a
     * connection for this error stream.  If the connection does not originate
     * from the remote server, an IOException will be thrown.  This serves as
     * a simple protection against possible hijacking of the error stream by
     * an attacker monitoring the rexec() negotiation.  You may disable this
     * behavior with
     * {@link org.apache.commons.net.bsd.RExecClient#setRemoteVerificationEnabled setRemoteVerificationEnabled()}
     * .
     * <p>
     * @param localUsername  The user account on the local machine that is
     *        requesting the command execution.
     * @param remoteUsername  The account name on the server through which to
     *        execute the command.
     * @param command   The command, including any arguments, to execute.
     * @param separateErrorStream True if you would like the standard error
     *        to be transmitted through a different stream than standard output.
     *        False if not.
     * @exception IOException If the rcommand() attempt fails.  The exception
     *            will contain a message indicating the nature of the failure.
     ***/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.239 -0500", hash_original_method = "BF10C70D6CA6641A61EF972A72ACB73E", hash_generated_method = "71298B19A3CEFC37F150C0AC0A37DE94")
    
public void rcommand(String localUsername, String remoteUsername,
                         String command, boolean separateErrorStream)
    throws IOException
    {
        rexec(localUsername, remoteUsername, command, separateErrorStream);
    }

    /***
     * Same as
     * <code> rcommand(localUsername, remoteUsername, command, false); </code>
     ***/
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-02-25 10:38:11.244 -0500", hash_original_method = "1ACE38F1A3DCA2E175832759CC0EDB36", hash_generated_method = "D7486A2C1D1BC6F4E42697FE5F899AF6")
    
public void rcommand(String localUsername, String remoteUsername,
                         String command)
    throws IOException
    {
        rcommand(localUsername, remoteUsername, command, false);
    }

}

