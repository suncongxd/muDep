/* Licensed to the Apache Software Foundation (ASF) under one or more
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
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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


package java.nio;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelectionKey;

final class SelectionKeyImpl extends AbstractSelectionKey {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.273 -0500", hash_original_field = "FEFAC73692D7E17DB18C4E769106B5E2", hash_generated_field = "E125EF1A670EBEF5649D9B822EF15475")

    private AbstractSelectableChannel channel;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.276 -0500", hash_original_field = "381160572B7D65DA766EB935C94A98D6", hash_generated_field = "99D2BA400CD7DBB29DFED003AED91D48")

    private int interestOps;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.278 -0500", hash_original_field = "BF82598D36DA59E4D21ADA19BAF48464", hash_generated_field = "410007A56D30CA888F4024F6CCB6F8C8")

    private int readyOps;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.280 -0500", hash_original_field = "D72B1E8B218DD9CE14F4033905AA4F41", hash_generated_field = "5473DE3E076906AD060E0C9087021F2C")

    private SelectorImpl selector;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.282 -0500", hash_original_method = "00B21AF608B9E98BCADD70D0A49AAABA", hash_generated_method = "550C7685E871695F1776BDC18D24C968")
    
public SelectionKeyImpl(AbstractSelectableChannel channel, int operations,
            Object attachment, SelectorImpl selector) {
        this.channel = channel;
        interestOps = operations;
        this.selector = selector;
        attach(attachment);
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.285 -0500", hash_original_method = "882667334549B80F71299B2D55BDD68C", hash_generated_method = "17F74C9ED9C7367CD6BCD5E6AA0DABE6")
    
@Override
    public SelectableChannel channel() {
        return channel;
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.287 -0500", hash_original_method = "88C76B7DE6B06DD1BF72DD8504F2EE79", hash_generated_method = "DA0EC1937E65D0D4E1CDE2880C6A7526")
    
@Override
    public int interestOps() {
        checkValid();
        synchronized (selector.keysLock) {
            return interestOps;
        }
    }

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.289 -0500", hash_original_method = "0E5852722D76AA650E985E5327BEB666", hash_generated_method = "0E5852722D76AA650E985E5327BEB666")
    
int interestOpsNoCheck() {
        synchronized (selector.keysLock) {
            return interestOps;
        }
    }

    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.292 -0500", hash_original_method = "4D6BC0A0A118723D5704E38CA961A8E7", hash_generated_method = "9C16C1AB6088FA2DA2BEBACB660A767C")
    
@Override
    public SelectionKey interestOps(int operations) {
        checkValid();
        if ((operations & ~(channel().validOps())) != 0) {
            throw new IllegalArgumentException();
        }
        synchronized (selector.keysLock) {
            interestOps = operations;
        }
        return this;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.295 -0500", hash_original_method = "D572EFB96E0388A4ABC799875BA302C3", hash_generated_method = "B7AE2A8B2DE248A405DAFE26655115A5")
    
@Override
    public int readyOps() {
        checkValid();
        return readyOps;
    }

    @DSSafe(DSCat.SAFE_LIST)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.297 -0500", hash_original_method = "4899CDE2873B162644661E5D73296ACA", hash_generated_method = "9D075E5D6FB12CC57E0FD2447C218E20")
    
@Override
    public Selector selector() {
        return selector;
    }

    /*
     * package private method for setting the ready operation by selector
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.299 -0500", hash_original_method = "9A32477274F9217DE646A1A1CD8308E4", hash_generated_method = "9A32477274F9217DE646A1A1CD8308E4")
    
void setReadyOps(int readyOps) {
        this.readyOps = readyOps;
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.302 -0500", hash_original_method = "3C7107E57611448B023007D9312E8BE0", hash_generated_method = "F91A0CD9DF37B4321768C6E647EB893D")
    
private void checkValid() {
        if (!isValid()) {
            throw new CancelledKeyException();
        }
    }

    /**
     * Returns true if the channel for this key is connected. If the channel
     * does not need connecting, this always return true.
     */
    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:57:06.305 -0500", hash_original_method = "566ADAA0BA7A180A2DDEAD33A7978412", hash_generated_method = "566ADAA0BA7A180A2DDEAD33A7978412")
    
boolean isConnected() {
        return !(channel instanceof SocketChannel) || ((SocketChannel) channel).isConnected();
    }
    
}

