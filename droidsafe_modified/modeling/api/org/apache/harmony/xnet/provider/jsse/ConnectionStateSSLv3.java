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


package org.apache.harmony.xnet.provider.jsse;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLProtocolException;

public class ConnectionStateSSLv3 extends ConnectionState {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.980 -0500", hash_original_field = "4E8A5D540F861204E8032EC19BEBF1B2", hash_generated_field = "FCEAD5705B5DC9FB1937BC2FCBBCA9AF")

    private  MessageDigest messageDigest;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.982 -0500", hash_original_field = "03ABC585FF3E189597CE9D84DD36BBBA", hash_generated_field = "52F0A7E7FB8F697BA2DBD34D808EE930")

    private  byte[] mac_write_secret;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.984 -0500", hash_original_field = "C677F0C0D78B9C8A57EB564574FE7AA2", hash_generated_field = "8761BC41F67E290D2E4C5002A6776D51")

    private  byte[] mac_read_secret;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.987 -0500", hash_original_field = "6AFD6D646359D2316AE189459311B1D0", hash_generated_field = "F7AB63294607944F603CC7ED7CDCFB7E")

    private  byte[] pad_1;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.989 -0500", hash_original_field = "05E909E2D0446AE7025C102AD82B8116", hash_generated_field = "BB8A0D4D3287636E4CC9DC82B8FCDD50")

    private  byte[] pad_2;
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.992 -0500", hash_original_field = "E7BD2645D7456572A31ED10DC03653E7", hash_generated_field = "5D85A0ED992BC70830B26009DA56A82F")

    // length of 3 == 1(SSLCompressed.type) + 2(SSLCompressed.length)
    // (more on SSLv3 MAC computation and payload protection see
    // SSL v3 specification, p. 5.2.3)
    private final byte[] mac_material_part = new byte[3];

    /**
     * Creates the instance of SSL v3 Connection State. All of the
     * security parameters are provided by session object.
     * @param   session: the sessin object which incapsulates
     * all of the security parameters established by handshake protocol.
     * The key calculation for the state is done according
     * to the SSL v3 Protocol specification.
     * (http://www.mozilla.org/projects/security/pki/nss/ssl/draft302.txt)
     */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:04.997 -0500", hash_original_method = "1B482CA9527A6F5074105168D7DF9619", hash_generated_method = "73AA028CE3FAAE6C1391FDD4E75C022F")
    
protected ConnectionStateSSLv3(SSLSessionImpl session) {
        try {
            CipherSuite cipherSuite = session.cipherSuite;

            boolean is_exportabe =  cipherSuite.isExportable();
            hash_size = cipherSuite.getMACLength();
            int key_size = (is_exportabe)
                ? cipherSuite.keyMaterial
                : cipherSuite.expandedKeyMaterial;
            int iv_size = cipherSuite.ivSize;
            block_size = cipherSuite.getBlockSize();

            String algName = cipherSuite.getBulkEncryptionAlgorithm();
            String hashName = cipherSuite.getHashName();
            if (logger != null) {
                logger.println("ConnectionStateSSLv3.create:");
                logger.println("  cipher suite name: "
                                        + session.getCipherSuite());
                logger.println("  encryption alg name: " + algName);
                logger.println("  hash alg name: " + hashName);
                logger.println("  hash size: " + hash_size);
                logger.println("  block size: " + block_size);
                logger.println("  IV size:" + iv_size);
                logger.println("  key size: " + key_size);
            }

            byte[] clientRandom = session.clientRandom;
            byte[] serverRandom = session.serverRandom;
            // so we need PRF value of size of
            // 2*hash_size + 2*key_size + 2*iv_size
            byte[] key_block = new byte[2*hash_size + 2*key_size + 2*iv_size];
            byte[] seed = new byte[clientRandom.length + serverRandom.length];
            System.arraycopy(serverRandom, 0, seed, 0, serverRandom.length);
            System.arraycopy(clientRandom, 0, seed, serverRandom.length,
                    clientRandom.length);

            PRF.computePRF_SSLv3(key_block, session.master_secret, seed);

            byte[] client_mac_secret = new byte[hash_size];
            byte[] server_mac_secret = new byte[hash_size];
            byte[] client_key = new byte[key_size];
            byte[] server_key = new byte[key_size];

            boolean is_client = !session.isServer;

            System.arraycopy(key_block, 0, client_mac_secret, 0, hash_size);
            System.arraycopy(key_block, hash_size,
                    server_mac_secret, 0, hash_size);
            System.arraycopy(key_block, 2*hash_size, client_key, 0, key_size);
            System.arraycopy(key_block, 2*hash_size+key_size,
                    server_key, 0, key_size);

            IvParameterSpec clientIV = null;
            IvParameterSpec serverIV = null;

            if (is_exportabe) {
                if (logger != null) {
                    logger.println("ConnectionStateSSLv3: is_exportable");
                }

                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(client_key);
                md5.update(clientRandom);
                md5.update(serverRandom);
                client_key = md5.digest();

                md5.update(server_key);
                md5.update(serverRandom);
                md5.update(clientRandom);
                server_key = md5.digest();

                key_size = cipherSuite.expandedKeyMaterial;

                if (block_size != 0) {
                    md5.update(clientRandom);
                    md5.update(serverRandom);
                    clientIV = new IvParameterSpec(md5.digest(), 0, iv_size);
                    md5.update(serverRandom);
                    md5.update(clientRandom);
                    serverIV = new IvParameterSpec(md5.digest(), 0, iv_size);
                }
            } else if (block_size != 0) {
                clientIV = new IvParameterSpec(key_block,
                        2*hash_size+2*key_size, iv_size);
                serverIV = new IvParameterSpec(key_block,
                        2*hash_size+2*key_size+iv_size, iv_size);
            }

            if (logger != null) {
                logger.println("is exportable: "+is_exportabe);
                logger.println("master_secret");
                logger.print(session.master_secret);
                logger.println("client_random");
                logger.print(clientRandom);
                logger.println("server_random");
                logger.print(serverRandom);
                //logger.println("key_block");
                //logger.print(key_block);
                logger.println("client_mac_secret");
                logger.print(client_mac_secret);
                logger.println("server_mac_secret");
                logger.print(server_mac_secret);
                logger.println("client_key");
                logger.print(client_key, 0, key_size);
                logger.println("server_key");
                logger.print(server_key, 0, key_size);
                if (clientIV != null) {
                    logger.println("client_iv");
                    logger.print(clientIV.getIV());
                    logger.println("server_iv");
                    logger.print(serverIV.getIV());
                } else {
                    logger.println("no IV.");
                }
            }

            if (algName == null) {
                encCipher = new NullCipher();
                decCipher = new NullCipher();
            } else {
                encCipher = Cipher.getInstance(algName);
                decCipher = Cipher.getInstance(algName);
                if (is_client) { // client side
                    encCipher.init(Cipher.ENCRYPT_MODE,
                                   new SecretKeySpec(client_key, 0, key_size, algName),
                                   clientIV);
                    decCipher.init(Cipher.DECRYPT_MODE,
                                   new SecretKeySpec(server_key, 0, key_size, algName),
                                   serverIV);
                } else { // server side
                    encCipher.init(Cipher.ENCRYPT_MODE,
                                   new SecretKeySpec(server_key, 0, key_size, algName),
                                   serverIV);
                    decCipher.init(Cipher.DECRYPT_MODE,
                                   new SecretKeySpec(client_key, 0, key_size, algName),
                                   clientIV);
                }
            }

            messageDigest = MessageDigest.getInstance(hashName);
            if (is_client) { // client side
                mac_write_secret = client_mac_secret;
                mac_read_secret = server_mac_secret;
            } else { // server side
                mac_write_secret = server_mac_secret;
                mac_read_secret = client_mac_secret;
            }
            if (hashName.equals("MD5")) {
                pad_1 = SSLv3Constants.MD5pad1;
                pad_2 = SSLv3Constants.MD5pad2;
            } else {
                pad_1 = SSLv3Constants.SHApad1;
                pad_2 = SSLv3Constants.SHApad2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLProtocolException(
                        "Error during computation of security parameters"));
        }
    }

    /**
     * Creates the GenericStreamCipher or GenericBlockCipher
     * data structure for specified data of specified type.
     * @throws AlertException if alert was occurred.
     */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:05.001 -0500", hash_original_method = "3F84D57F957CA4DCEBAF95364A0886D3", hash_generated_method = "0E8AC2BD0EC1AA0C121FCD5C79498A68")
    
@Override
    protected byte[] encrypt(byte type, byte[] fragment, int offset, int len) {
        try {
            int content_mac_length = len + hash_size;
            int padding_length = (block_size == 0) ? 0 : getPaddingSize(++content_mac_length);
            byte[] res = new byte[content_mac_length + padding_length];
            System.arraycopy(fragment, offset, res, 0, len);

            mac_material_part[0] = type;
            mac_material_part[1] = (byte) ((0x00FF00 & len) >> 8);
            mac_material_part[2] = (byte) (0x0000FF & len);

            messageDigest.update(mac_write_secret);
            messageDigest.update(pad_1);
            messageDigest.update(write_seq_num);
            messageDigest.update(mac_material_part);
            messageDigest.update(fragment, offset, len);
            byte[] digest = messageDigest.digest();
            messageDigest.update(mac_write_secret);
            messageDigest.update(pad_2);
            messageDigest.update(digest);
            digest = messageDigest.digest();
            System.arraycopy(digest, 0, res, len, hash_size);

            //if (logger != null) {
            //    logger.println("MAC Material:");
            //    logger.print(write_seq_num);
            //    logger.print(mac_material_header);
            //    logger.print(fragment, offset, len);
            //}

            if (block_size != 0) {
                // do padding:
                Arrays.fill(res, content_mac_length-1,
                        res.length, (byte) (padding_length));
            }
            if (logger != null) {
                logger.println("SSLRecordProtocol.encrypt: "
                        + (block_size != 0
                            ? "GenericBlockCipher with padding["
                                +padding_length+"]:"
                            : "GenericStreamCipher:"));
                logger.print(res);
            }
            byte[] rez = new byte[encCipher.getOutputSize(res.length)];
            encCipher.update(res, 0, res.length, rez);
            incSequenceNumber(write_seq_num);
            return rez;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLProtocolException("Error during the encryption"));
        }
    }

    /**
     * Retrieves the fragment of the Plaintext structure of
     * the specified type from the provided data.
     * @throws AlertException if alert was occured.
     */
    @DSSink({DSSinkKind.SENSITIVE_UNCATEGORIZED})
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:05.005 -0500", hash_original_method = "227FECEA7131D056EF18EE2E9369B9A1", hash_generated_method = "459DC1330B09FE06A09C222972E31D1D")
    
@Override
    protected byte[] decrypt(byte type, byte[] fragment,
            int offset, int len) {
        // plain data of the Generic[Stream|Block]Cipher structure
        byte[] data = decCipher.update(fragment, offset, len);
        // the 'content' part of the structure
        byte[] content;
        if (block_size != 0) {
            // check padding
            int padding_length = data[data.length-1];
            for (int i=0; i<padding_length; i++) {
                if (data[data.length-2-i] != padding_length) {
                    throw new AlertException(
                            AlertProtocol.DECRYPTION_FAILED,
                            new SSLProtocolException(
                                "Received message has bad padding"));
                }
            }
            content = new byte[data.length - hash_size - padding_length - 1];
        } else {
            content = new byte[data.length - hash_size];
        }

        byte[] mac_value;

        mac_material_part[0] = type;
        mac_material_part[1] = (byte) ((0x00FF00 & content.length) >> 8);
        mac_material_part[2] = (byte) (0x0000FF & content.length);

        messageDigest.update(mac_read_secret);
        messageDigest.update(pad_1);
        messageDigest.update(read_seq_num);
        messageDigest.update(mac_material_part);
        messageDigest.update(data, 0, content.length);
        mac_value = messageDigest.digest();
        messageDigest.update(mac_read_secret);
        messageDigest.update(pad_2);
        messageDigest.update(mac_value);
        mac_value = messageDigest.digest();

        if (logger != null) {
            logger.println("Decrypted:");
            logger.print(data);
            //logger.println("MAC Material:");
            //logger.print(read_seq_num);
            //logger.print(mac_material_header);
            //logger.print(data, 0, content.length);
            logger.println("Expected mac value:");
            logger.print(mac_value);
        }
        // checking the mac value
        for (int i=0; i<hash_size; i++) {
            if (mac_value[i] != data[i+content.length]) {
                throw new AlertException(AlertProtocol.BAD_RECORD_MAC,
                        new SSLProtocolException("Bad record MAC"));
            }
        }
        System.arraycopy(data, 0, content, 0, content.length);
        incSequenceNumber(read_seq_num);
        return content;
    }

    /**
     * Shutdown the protocol. It will be impossible to use the instance
     * after the calling of this method.
     */
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:01:05.007 -0500", hash_original_method = "193A05B6A3D6926494D4F0030FC865D1", hash_generated_method = "6B6E8ECDD5832732E8CAB74DA3C4FFDC")
    
@Override
    protected void shutdown() {
        Arrays.fill(mac_write_secret, (byte) 0);
        Arrays.fill(mac_read_secret, (byte) 0);
        super.shutdown();
    }
    
}

