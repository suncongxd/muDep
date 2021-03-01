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


package android.net.rtp;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.util.Arrays;

public class AudioCodec {

    /**
     * Returns system supported audio codecs.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.960 -0500", hash_original_method = "A02F235322CEC49098638CFAA7891025", hash_generated_method = "D78F8061E8F13431B3661F566FE40755")
    
public static AudioCodec[] getCodecs() {
        return Arrays.copyOf(sCodecs, sCodecs.length);
    }

    /**
     * Creates an AudioCodec according to the given configuration.
     *
     * @param type The payload type of the encoding defined in RTP/AVP.
     * @param rtpmap The encoding parameters specified in the corresponding SDP
     *     attribute, or null if it is not available.
     * @param fmtp The format parameters specified in the corresponding SDP
     *     attribute, or null if it is not available.
     * @return The configured AudioCodec or {@code null} if it is not supported.
     */
    @DSSource({DSSourceKind.NETWORK})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.963 -0500", hash_original_method = "4B34E1AD78FB7AEDA5C9AAEBE5789D37", hash_generated_method = "9CDF3EC00B2A7297DFC9321B0E5924EB")
    
public static AudioCodec getCodec(int type, String rtpmap, String fmtp) {
        if (type < 0 || type > 127) {
            return null;
        }

        AudioCodec hint = null;
        if (rtpmap != null) {
            String clue = rtpmap.trim().toUpperCase();
            for (AudioCodec codec : sCodecs) {
                if (clue.startsWith(codec.rtpmap)) {
                    String channels = clue.substring(codec.rtpmap.length());
                    if (channels.length() == 0 || channels.equals("/1")) {
                        hint = codec;
                    }
                    break;
                }
            }
        } else if (type < 96) {
            for (AudioCodec codec : sCodecs) {
                if (type == codec.type) {
                    hint = codec;
                    rtpmap = codec.rtpmap;
                    break;
                }
            }
        }

        if (hint == null) {
            return null;
        }
        if (hint == AMR && fmtp != null) {
            String clue = fmtp.toLowerCase();
            if (clue.contains("crc=1") || clue.contains("robust-sorting=1") ||
                    clue.contains("interleaving=")) {
                return null;
            }
        }
        return new AudioCodec(type, rtpmap, fmtp);
    }
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.944 -0500", hash_original_field = "483C4612ED814445BE18293248BE8F02", hash_generated_field = "72C5DB3322A5D181C06BCD1F412B8D8B")

    public static final AudioCodec PCMU = new AudioCodec(0, "PCMU/8000", null);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.947 -0500", hash_original_field = "33E364CE0CEACEEEB8ED89D09267FD77", hash_generated_field = "9573916B7511FAC95B24045A3D38633D")

    public static final AudioCodec PCMA = new AudioCodec(8, "PCMA/8000", null);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.949 -0500", hash_original_field = "E7F63B2B16507394EFAA76DDDBAD007F", hash_generated_field = "F99C3CCFF7F596754E3D077EB53342B8")

    public static final AudioCodec GSM = new AudioCodec(3, "GSM/8000", null);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.952 -0500", hash_original_field = "B19E2F7729F8D098683BCC8F27595861", hash_generated_field = "3D824786AF889508B95F1D8BCB909DAC")

    public static final AudioCodec GSM_EFR = new AudioCodec(96, "GSM-EFR/8000", null);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.954 -0500", hash_original_field = "BCBF036F8ECFC0BDDBB8F775064124A4", hash_generated_field = "2175889361BE7AC3354D744ED51EF382")

    public static final AudioCodec AMR = new AudioCodec(97, "AMR/8000", null);
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.956 -0500", hash_original_field = "2D1D7205125A0AC4541043E7654C0C55", hash_generated_field = "C91EA36E4EEB99A1A60AD3DFD8BCE544")

    private static final AudioCodec[] sCodecs = {GSM_EFR, AMR, GSM, PCMU, PCMA};
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.938 -0500", hash_original_field = "961B4204667A4AE2FF8DD374E6728ADE", hash_generated_field = "6AC5CE4BE311ED1283E9BD812937901E")

    public  int type;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.940 -0500", hash_original_field = "5D22ED68ADB5785FC6765D0677500D0B", hash_generated_field = "EFE641ECA835D29854D8EF22918C1848")

    public  String rtpmap;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.942 -0500", hash_original_field = "37D0B8FF8614E8D3208E2565283BF5BC", hash_generated_field = "514B19E90214DE74BBA4CCC8C3EAAA2D")

    public  String fmtp;

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 12:36:21.958 -0500", hash_original_method = "9592639532530D98F97A4B6F44806F64", hash_generated_method = "4A85C7BB46AC6C6020DF699A0BE8FACF")
    
private AudioCodec(int type, String rtpmap, String fmtp) {
        this.type = type;
        this.rtpmap = rtpmap;
        this.fmtp = fmtp;
    }
}

