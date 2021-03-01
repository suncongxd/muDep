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
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package java.net;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;

public final class DatagramPacket {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:50.571 -0400", hash_original_field = "B330DF564CD90A5498A9E4F0AB344BB9", hash_generated_field = "BCB00A81B11593F3A75239028B6E65B9")

    public byte[] data;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:50.571 -0400", hash_original_field = "136C4DD872F8065EECD33F6CD4E7556E", hash_generated_field = "C7D2E1B1F4403418CA216FC250689056")

    private int length;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.338 -0500", hash_original_field = "0B1D26332B4020647655E70C20DE6D8D", hash_generated_field = "0B1D26332B4020647655E70C20DE6D8D")

    int capacity;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:50.572 -0400", hash_original_field = "FDCE7272CFC91A6374FBF7F54D5CC8E4", hash_generated_field = "220CE3F94E98B4B531A4938FAFF932C0")

@DSVAModeled
    private InetAddress address;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:50.572 -0400", hash_original_field = "BAF684EECB20D799A434AFCF1B13F01D", hash_generated_field = "F51DF048A4B5B6B4A61A3652544F4757")

    private int port = -1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-09-03 15:00:50.573 -0400", hash_original_field = "B57AAF7BF5F2E3B125D28448999D0D18", hash_generated_field = "724EB7AA080C9DB92A724877C069F566")

    private int offset = 0;

    /**
     * Constructs a new {@code DatagramPacket} object to receive data up to
     * {@code length} bytes.
     *
     * @param data
     *            a byte array to store the read characters.
     * @param length
     *            the length of the data buffer.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.348 -0500", hash_original_method = "28D366BE99A6BECEEC6B0BD286389E83", hash_generated_method = "0540F8CFBE8C783DBBBA4EAC64FD0D4C")
    
public DatagramPacket(byte[] data, int length) {
        this(data, 0, length);
    }

    /**
     * Constructs a new {@code DatagramPacket} object to receive data up to
     * {@code length} bytes with a specified buffer offset.
     *
     * @param data
     *            a byte array to store the read characters.
     * @param offset
     *            the offset of the byte array where the bytes is written.
     * @param length
     *            the length of the data.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.351 -0500", hash_original_method = "4CE5C637B4B89EB1E9EB381D06DF8800", hash_generated_method = "DA7DC205E10495C34B4CCC8227D3F364")
    
public DatagramPacket(byte[] data, int offset, int length) {
        setData(data, offset, length);
    }

    /**
     * Constructs a new {@code DatagramPacket} object to send data to the port
     * {@code aPort} of the address {@code host}. The {@code length} must be
     * lesser than or equal to the size of {@code data}. The first {@code
     * length} bytes from the byte array position {@code offset} are sent.
     *
     * @param data
     *            a byte array which stores the characters to be sent.
     * @param offset
     *            the offset of {@code data} where to read from.
     * @param length
     *            the length of data.
     * @param host
     *            the address of the target host.
     * @param aPort
     *            the port of the target host.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.354 -0500", hash_original_method = "E7BE9233E3C5340436266F1CA27D4E74", hash_generated_method = "3AF5F21251316F945FC425D1419D9BCA")
    
public DatagramPacket(byte[] data, int offset, int length,
            InetAddress host, int aPort) {
        this(data, offset, length);
        setPort(aPort);
        address = host;
    }

    /**
     * Constructs a new {@code DatagramPacket} object to send data to the port
     * {@code aPort} of the address {@code host}. The {@code length} must be
     * lesser than or equal to the size of {@code data}. The first {@code
     * length} bytes are sent.
     *
     * @param data
     *            a byte array which stores the characters to be sent.
     * @param length
     *            the length of data.
     * @param host
     *            the address of the target host.
     * @param port
     *            the port of the target host.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.356 -0500", hash_original_method = "2604ABB8A21562C349D5EE8B9E092E22", hash_generated_method = "D61F101FB212FF8FE9898A2F74A92CA8")
    
public DatagramPacket(byte[] data, int length, InetAddress host, int port) {
        this(data, 0, length, host, port);
    }

    /**
     * Constructs a new {@code DatagramPacket} object to send data to the
     * address {@code sockAddr}. The {@code length} must be lesser than or equal
     * to the size of {@code data}. The first {@code length} bytes of the data
     * are sent.
     *
     * @param data
     *            the byte array to store the data.
     * @param length
     *            the length of the data.
     * @param sockAddr
     *            the target host address and port.
     * @throws SocketException
     *             if an error in the underlying protocol occurs.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.390 -0500", hash_original_method = "5A348CBDEF8E4CCB182CB7D327F8975B", hash_generated_method = "D3A41011ED854179B274A712081CE540")
    
public DatagramPacket(byte[] data, int length, SocketAddress sockAddr) throws SocketException {
        this(data, 0, length);
        setSocketAddress(sockAddr);
    }

    /**
     * Constructs a new {@code DatagramPacket} object to send data to the
     * address {@code sockAddr}. The {@code length} must be lesser than or equal
     * to the size of {@code data}. The first {@code length} bytes of the data
     * are sent.
     *
     * @param data
     *            the byte array to store the data.
     * @param offset
     *            the offset of the data.
     * @param length
     *            the length of the data.
     * @param sockAddr
     *            the target host address and port.
     * @throws SocketException
     *             if an error in the underlying protocol occurs.
     */
    @DSComment("constructor")
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.393 -0500", hash_original_method = "9EFE7F8CACFA39C8DFB2EE9B45B29DD7", hash_generated_method = "FE60111D267819A7D2C38F9ED9FD4220")
    
public DatagramPacket(byte[] data, int offset, int length,
            SocketAddress sockAddr) throws SocketException {
        this(data, offset, length);
        setSocketAddress(sockAddr);
    }

    /**
     * Gets the sender or destination IP address of this datagram packet.
     *
     * @return the address from where the datagram was received or to which it
     *         is sent.
     */
    @DSComment("Data structure only")
    @DSSpec(DSCat.IO)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.359 -0500", hash_original_method = "F0319BA9B19AC93124B00C2891893A52", hash_generated_method = "C1DC99F69AF6376A7FC069FBD1826D41")
    
    public synchronized InetAddress getAddress() {
        return address;
    }

    /**
     * Gets the data of this datagram packet.
     *
     * @return the received data or the data to be sent.
     */
    @DSComment("Data structure only")
    @DSSpec(DSCat.IO)
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.361 -0500", hash_original_method = "B9E74D06348E60D6DDB9FA9E07D464F9", hash_generated_method = "A16CB4E41E808ED016EB88BE01DF9A6C")    
    public synchronized byte[] getData() {        
        this.addTaint(data.getTaint());
        data[0] = getTaintByte();
        return data;
    }

    /**
     * Gets the length of the data stored in this datagram packet.
     *
     * @return the length of the received data or the data to be sent.
     */
    @DSComment("Data structure only")
    @DSSafe(DSCat.DATA_STRUCTURE)
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.364 -0500", hash_original_method = "A993727E7D5DC0CA2BB581135F7F678B", hash_generated_method = "2DB509520BC98A42BC7C0D8F720E796A")
    
public synchronized int getLength() {
        return length;
    }

    /**
     * Gets the offset of the data stored in this datagram packet.
     *
     * @return the position of the received data or the data to be sent.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.366 -0500", hash_original_method = "ACEB13DAA855070296BA592FB1ADADA1", hash_generated_method = "5481909E9946628917952CC7F7C31227")
    
public synchronized int getOffset() {
        return offset;
    }

    /**
     * Gets the port number of the target or sender host of this datagram
     * packet.
     *
     * @return the port number of the origin or target host.
     */
    @DSComment("Data structure only")
    @DSSafe(DSCat.DATA_STRUCTURE)
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.369 -0500", hash_original_method = "A5F7344FAECBE0D6DB15D1F6E7BEBC6E", hash_generated_method = "FDD2B2E226295EB6A09A09D12ED55358")
    
public synchronized int getPort() {
        return port;
    }

    /**
     * Sets the IP address of the target host.
     *
     * @param addr
     *            the target host address.
     */
    @DSComment("Data structure only")
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.372 -0500", hash_original_method = "C8080B968BD32815251343303FD7866E", hash_generated_method = "49236674C02A8A8DC3D9405507855A3C")
    @DSSink({DSSinkKind.NETWORK})
public synchronized void setAddress(InetAddress addr) {
        address = addr;
    }

    /**
     * Sets the data buffer for this datagram packet.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.375 -0500", hash_original_method = "5E0274686A01B4FB96908A6A9486BA81", hash_generated_method = "2DA12462DD5F5F3C0044E4B33DF841F2")
    @DSSafe(DSCat.DATA_STRUCTURE)
public synchronized void setData(byte[] data, int offset, int byteCount) {
        if ((offset | byteCount) < 0 || offset > data.length || byteCount > data.length - offset) {
            throw new IllegalArgumentException();
        }
        this.data = data;
        this.offset = offset;
        this.length = byteCount;
        this.capacity = byteCount;
    }

    /**
     * Sets the data buffer for this datagram packet. The length of the datagram
     * packet is set to the buffer length.
     *
     * @param buf
     *            the buffer to store the data.
     */

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.378 -0500", hash_original_method = "90578DC03777F39E5EBE6643D9CBD4FE", hash_generated_method = "B57994F332F0EFF7B5747BCD72DD9853")
        @DSSafe(DSCat.DATA_STRUCTURE)
public synchronized void setData(byte[] buf) {
        length = buf.length; // This will check for null
        capacity = buf.length;
        data = buf;
        offset = 0;
    }

    /**
     * Gets the current capacity value.
     *
     * @return the current capacity value
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.380 -0500", hash_original_method = "59C864B14A0070866D297CD091E1F29D", hash_generated_method = "A78FFB8D04E78CAACD325961B8A131AF")
    
synchronized int getCapacity() {
        return capacity;
    }

    /**
     * Sets the length of the datagram packet. This length plus the offset must
     * be lesser than or equal to the buffer size.
     *
     * @param length
     *            the length of this datagram packet.
     */
    @DSSink({DSSinkKind.NETWORK})
    @DSComment("Data structure only")
    @DSSafe(DSCat.DATA_STRUCTURE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.382 -0500", hash_original_method = "A9C966E60C597F426105F63D3A989F4F", hash_generated_method = "5093D4306BE47F048B868A1A46851AAD")
    
public synchronized void setLength(int length) {
        setLengthOnly(length);
        this.capacity = length;
    }

    /**
     * An alternative to {@link #setLength(int)}, that doesn't reset the {@link #capacity}
     * field.
     *
     * @param len the length of this datagram packet
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.385 -0500", hash_original_method = "78A3DBC65D2912D5EFEBEB58F048F8DC", hash_generated_method = "26E42A9547BE66E096D30B0B02657C8A")
    
synchronized void setLengthOnly(int length) {
        if (length < 0 || offset + length > data.length) {
            throw new IndexOutOfBoundsException("length=" + length + ", offset=" + offset +
                    ", buffer size=" + data.length);
        }
        this.length = length;
    }

    /**
     * Sets the port number of the target host of this datagram packet.
     *
     * @param aPort
     *            the target host port number.
     */
    @DSComment("Data structure only")
    @DSSafe(DSCat.DATA_STRUCTURE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.387 -0500", hash_original_method = "CC3AA0BB5DF3EE4831739014FCB197DF", hash_generated_method = "14D0D4C0E504C45EE17EF675E5057C05")
    
public synchronized void setPort(int aPort) {
        if (aPort < 0 || aPort > 65535) {
            throw new IllegalArgumentException("Port out of range: " + aPort);
        }
        port = aPort;
    }

    /**
     * Gets the host address and the port to which this datagram packet is sent
     * as a {@code SocketAddress} object.
     *
     * @return the SocketAddress of the target host.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.395 -0500", hash_original_method = "FA4C710C49AB0B200E625D4A04A47D21", hash_generated_method = "98653A3A077D3CCCC6EF171E73494A06")
    
public synchronized SocketAddress getSocketAddress() {
        return new InetSocketAddress(getAddress(), getPort());
    }

    /**
     * Sets the {@code SocketAddress} for this datagram packet.
     *
     * @param sockAddr
     *            the SocketAddress of the target host.
     */
    @DSSink({DSSinkKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:58:08.399 -0500", hash_original_method = "3EDF0F039E2E002F80626A052A7B5C38", hash_generated_method = "B2E1FDF6EF31C2E23A516CDDA1476D64")
    
public synchronized void setSocketAddress(SocketAddress sockAddr) {
        if (!(sockAddr instanceof InetSocketAddress)) {
            throw new IllegalArgumentException("Socket address not an InetSocketAddress: " +
                    (sockAddr == null ? null : sockAddr.getClass()));
        }
        InetSocketAddress inetAddr = (InetSocketAddress) sockAddr;
        if (inetAddr.isUnresolved()) {
            throw new IllegalArgumentException("Socket address unresolved: " + sockAddr);
        }
        port = inetAddr.getPort();
        address = inetAddr.getAddress();
    }

    public void setReceivedLength(int byteCount) {
        // TODO Auto-generated method stub
        
    }
    
}

