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
 * Copyright (C) 2010 The Android Open Source Project
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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ZoomButtonsController;

class ZoomControlEmbedded implements ZoomControlBase {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.008 -0500", hash_original_field = "89F04BCF2CEF2FF9614BE546923CA10D", hash_generated_field = "9B99C2FE2EA0A3C0CAEC1D267CB6150C")

    private  ZoomManager mZoomManager;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.010 -0500", hash_original_field = "E044D69EE05B24F4B93E3A3B208F5343", hash_generated_field = "99346B50377E115231263981E00655CA")

    private  WebView mWebView;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.012 -0500", hash_original_field = "4AD90657AB7879F5FF6863A18E81A79F", hash_generated_field = "A8A0DD9E8E1D109A248CAEBBC4A8688D")

    private ZoomButtonsController mZoomButtonsController;

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.014 -0500", hash_original_method = "58B848602B04675A34284534D0242989", hash_generated_method = "D748EB4302246DD00F5F94F46567B16A")
    
public ZoomControlEmbedded(ZoomManager zoomManager, WebView webView) {
        mZoomManager = zoomManager;
        mWebView = webView;
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.017 -0500", hash_original_method = "AC6DDF8CBB501A63B7872C74FE4374B8", hash_generated_method = "328A0AB607EB91E6D512D071D2A483CD")
    
public void show() {
        if (!getControls().isVisible() && !mZoomManager.isZoomScaleFixed()) {

            mZoomButtonsController.setVisible(true);

            if (mZoomManager.isDoubleTapEnabled()) {
                WebSettings settings = mWebView.getSettings();
                int count = settings.getDoubleTapToastCount();
                if (mZoomManager.isInZoomOverview() && count > 0) {
                    settings.setDoubleTapToastCount(--count);
                    Toast.makeText(mWebView.getContext(),
                            com.android.internal.R.string.double_tap_toast,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.019 -0500", hash_original_method = "01D9936CB3AEEFE463F6049C6D59A6E4", hash_generated_method = "0B40AA260FFF7C1BE2FE7387A99F28AF")
    
public void hide() {
        if (mZoomButtonsController != null) {
            mZoomButtonsController.setVisible(false);
        }
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.021 -0500", hash_original_method = "518B1D44CA5DDB1FC736D41ED30011CF", hash_generated_method = "5BD531AE3778221A23DFC231A1D18C84")
    
public boolean isVisible() {
        return mZoomButtonsController != null && mZoomButtonsController.isVisible();
    }

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.023 -0500", hash_original_method = "3EEB1B7639B141DBF4A839E2ACE958E1", hash_generated_method = "4EB328696D58A43BEC74059B167A3B43")
    
public void update() {
        if (mZoomButtonsController == null) {
            return;
        }

        boolean canZoomIn = mZoomManager.canZoomIn();
        boolean canZoomOut = mZoomManager.canZoomOut() && !mZoomManager.isInZoomOverview();
        if (!canZoomIn && !canZoomOut) {
            // Hide the zoom in and out buttons if the page cannot zoom
            mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
        } else {
            // Set each one individually, as a page may be able to zoom in or out
            mZoomButtonsController.setZoomInEnabled(canZoomIn);
            mZoomButtonsController.setZoomOutEnabled(canZoomOut);
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.026 -0500", hash_original_method = "0263F8BCA60BCA372CF61DE8AB7A303B", hash_generated_method = "F5D4F746BF08592E30559E547F0A1A5D")
    
private ZoomButtonsController getControls() {
        if (mZoomButtonsController == null) {
            mZoomButtonsController = new ZoomButtonsController(mWebView);
            mZoomButtonsController.setOnZoomListener(new ZoomListener());
            // ZoomButtonsController positions the buttons at the bottom, but in
            // the middle. Change their layout parameters so they appear on the
            // right.
            View controls = mZoomButtonsController.getZoomControls();
            ViewGroup.LayoutParams params = controls.getLayoutParams();
            if (params instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) params).gravity = Gravity.RIGHT;
            }
        }
        return mZoomButtonsController;
    }
    
    private class ZoomListener implements ZoomButtonsController.OnZoomListener {
        
        @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:56.584 -0400", hash_original_method = "97A31822F6766CF42618FF6D567EE294", hash_generated_method = "97A31822F6766CF42618FF6D567EE294")
        public ZoomListener ()
        {
            //Synthesized constructor
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.028 -0500", hash_original_method = "CAB57885F430D7392888CFD12B17B225", hash_generated_method = "4111D1A62E6EB55B091510A8B69395AA")
        
public void onVisibilityChanged(boolean visible) {
            if (visible) {
                mWebView.switchOutDrawHistory();
                // Bring back the hidden zoom controls.
                mZoomButtonsController.getZoomControls().setVisibility(View.VISIBLE);
                update();
            }
        }

        @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:33:11.031 -0500", hash_original_method = "A16881C1E119771D4FCCD4DE4DCAF173", hash_generated_method = "3724CEBC8BB7643EC7A121CA32DBAB53")
        
public void onZoom(boolean zoomIn) {
            if (zoomIn) {
                mWebView.zoomIn();
            } else {
                mWebView.zoomOut();
            }
            update();
        }
        
    }
    
}

