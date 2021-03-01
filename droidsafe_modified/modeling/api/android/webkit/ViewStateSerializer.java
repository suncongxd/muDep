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
 * Copyright (C) 2011 The Android Open Source Project
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


package android.webkit;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Point;
import android.graphics.Region;
import android.webkit.WebViewCore.DrawData;

import droidsafe.helpers.DSUtils;

class ViewStateSerializer {

    @DSComment("Package priviledge")
    @DSBan(DSCat.DEFAULT_MODIFIER)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:42.297 -0500", hash_original_method = "1EF06B01DEFC03F0294E2EE5BC731C9C", hash_generated_method = "B43AF825380E83A00B9112FC5C7D13E6")
    
static boolean serializeViewState(OutputStream stream, WebView web)
            throws IOException {
        int baseLayer = web.getBaseLayer();
        if (baseLayer == 0) {
            return false;
        }
        DataOutputStream dos = new DataOutputStream(stream);
        dos.writeInt(VERSION);
        dos.writeInt(web.getContentWidth());
        dos.writeInt(web.getContentHeight());
        return nativeSerializeViewState(baseLayer, dos,
                new byte[WORKING_STREAM_STORAGE]);
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:42.300 -0500", hash_original_method = "740591971B604C9B95CAEBB1564A3BD3", hash_generated_method = "654562A21ECB0146F219616D2189591A")
    
static DrawData deserializeViewState(InputStream stream, WebView web)
            throws IOException {
        DataInputStream dis = new DataInputStream(stream);
        int version = dis.readInt();
        if (version != VERSION) {
            throw new IOException("Unexpected version: " + version);
        }
        int contentWidth = dis.readInt();
        int contentHeight = dis.readInt();
        int baseLayer = nativeDeserializeViewState(dis,
                new byte[WORKING_STREAM_STORAGE]);

        final WebViewCore.DrawData draw = new WebViewCore.DrawData();
        draw.mViewState = new WebViewCore.ViewState();
        int viewWidth = web.getViewWidth();
        int viewHeight = web.getViewHeightWithTitle() - web.getTitleHeight();
        draw.mViewSize = new Point(viewWidth, viewHeight);
        draw.mContentSize = new Point(contentWidth, contentHeight);
        draw.mViewState.mDefaultScale = web.getDefaultZoomScale();
        draw.mBaseLayer = baseLayer;
        draw.mInvalRegion = new Region(0, 0, contentWidth, contentHeight);
        return draw;
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static boolean nativeSerializeViewState(int baseLayer,
            OutputStream stream, byte[] storage) {
        stream.addTaint(baseLayer);
        stream.addTaint(storage.getTaint());
        stream.addTaint(storage[0]);
        return stream.getTaintBoolean();
    }
    
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    private static int nativeDeserializeViewState(
            InputStream stream, byte[] storage) {
        storage[0] = (byte)stream.getTaintInt();
        return storage.getTaintInt();
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:42.293 -0500", hash_original_field = "93DB6FF890F60901303B2AEAD85141C3", hash_generated_field = "16A3D3E1F1A46232673D292E95776672")

    private static final int WORKING_STREAM_STORAGE = 16 * 1024;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:42.295 -0500", hash_original_field = "965BFA0136A42F88AEBCBAB90EBADD3A", hash_generated_field = "C4C590CA9F059E3E20E236FD779C6637")

    static final int VERSION = 1;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:32:42.312 -0500", hash_original_method = "49BFAAEC63FD743661F2FA5579ECB6BF", hash_generated_method = "DA8C7636A19B2A584C27A6067BDE4401")
    
private ViewStateSerializer() {}
}

