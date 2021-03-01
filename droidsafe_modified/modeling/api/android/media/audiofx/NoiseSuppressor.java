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


package android.media.audiofx;

/**
 * Noise Suppressor (NS).
 * <p>Noise suppression (NS) is an audio pre-processing which removes background noise from the
 * captured signal. The component of the signal considered as noise can be either stationary
 * (car/airplane engine, AC system) or non-stationary (other peoples conversations, car horn) for
 * more advanced implementations.
 * <p>NS is mostly used by voice communication applications (voice chat, video conferencing,
 * SIP calls).
 * <p>An application creates a NoiseSuppressor object to instantiate and control an NS
 * engine in the audio framework.
 * <p>To attach the NoiseSuppressor to a particular {@link android.media.AudioRecord},
 * specify the audio session ID of this AudioRecord when constructing the NoiseSuppressor.
 * The audio session is retrieved by calling
 * {@link android.media.AudioRecord#getAudioSessionId()} on the AudioRecord instance.
 * <p>On some devices, NS can be inserted by default in the capture path by the platform
 * according to the {@link android.media.MediaRecorder.AudioSource} used. The application can
 * query which pre-processings are currently applied to an AudioRecord instance by calling
 * {@link android.media.audiofx.AudioEffect#queryPreProcessings(int)} with the audio session of the
 * AudioRecord.
 * <p>See {@link android.media.audiofx.AudioEffect} class for more details on
 * controlling audio effects.
 * @hide
 */

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
public class NoiseSuppressor extends AudioEffect {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:17.902 -0400", hash_original_field = "C1F5CC065D12B1668C59E867B5F48C04", hash_generated_field = "292E8E6F4C2673CFF193CDFBEC50E1F5")


    private final static String TAG = "NoiseSuppressor";

    /**
     * Class constructor.
     * <p> The application must catch exceptions when creating an NoiseSuppressor as the
     * constructor is not guarantied to succeed:
     * <ul>
     *  <li>IllegalArgumentException is thrown if the device does not implement an NS</li>
     *  <li>UnsupportedOperationException is thrown is the resources allocated to audio
     *  pre-procesing are currently exceeded.</li>
     * </ul>
     *
     * @param audioSession system wide unique audio session identifier. The NoiseSuppressor
     * will be applied to the AudioRecord with the same audio session.
     *
     * @throws java.lang.IllegalArgumentException
     * @throws java.lang.UnsupportedOperationException
     * @throws java.lang.RuntimeException
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2014-03-24 16:07:17.904 -0400", hash_original_method = "EEEB134CB1BBAA32357F6127B2BD4E86", hash_generated_method = "CEA42E1C7DCBEB4137FD62E1AEFC8799")
    
public NoiseSuppressor(int audioSession)
            throws IllegalArgumentException, UnsupportedOperationException, RuntimeException {
        super(EFFECT_TYPE_NS, EFFECT_TYPE_NULL, 0, audioSession);
    }
}
