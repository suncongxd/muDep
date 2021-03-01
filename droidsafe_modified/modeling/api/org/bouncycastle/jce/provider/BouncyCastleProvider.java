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
 */


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package org.bouncycastle.jce.provider;

// Droidsafe Imports
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import droidsafe.annotations.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.util.Iterator;
import java.util.Map;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.jce.interfaces.ConfigurableProvider;

public final class BouncyCastleProvider extends Provider implements ConfigurableProvider {
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.115 -0500", hash_original_field = "FCF20FF015ECD1405721C29088E4DE31", hash_generated_field = "C9727C0638F2013F7B780A7BE1307CFA")

    private static String info = "BouncyCastle Security Provider v1.46";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.117 -0500", hash_original_field = "B355B645C2D678E21D43E093C09A192E", hash_generated_field = "FCA92C763E0E8F4A5243B90555C59A84")

    //     this constant should be final
    public static final String PROVIDER_NAME = "BC";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.120 -0500", hash_original_field = "6AFE32B77084FC7CC5DB9AED5D3D3AEF", hash_generated_field = "96EF51D9176435A4A949DF4BC3624453")

    /*
     * Configurable symmetric ciphers
     */
    private static final String SYMMETRIC_CIPHER_PACKAGE = "org.bouncycastle.jce.provider.symmetric.";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.123 -0500", hash_original_field = "E32C48828299F4D32714C10B848C1510", hash_generated_field = "7AE81A85D149D23A29C2E5EABD4CD19C")

    private static final String[] SYMMETRIC_CIPHERS =
    {
        // BEGIN android-removed
        // "AES", "ARC4", "Blowfish", "Camellia", "CAST5", "CAST6", "DESede", "Grainv1", "Grain128", "HC128", "HC256", "IDEA",
        // "Noekeon", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Skipjack", "TEA", "Twofish", "VMPC", "VMPCKSA3", "XTEA"
        // END android-removed
        // BEGIN android-added
        "AES", "ARC4", "Blowfish", "DESede",
        // END android-added

    };
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.125 -0500", hash_original_field = "1720BB045F3BC4C3A6A6F04F074CAB4E", hash_generated_field = "30A48E7B93E404115A86D1135E5F52C1")

    private static final String ASYMMETRIC_CIPHER_PACKAGE = "org.bouncycastle.jce.provider.asymmetric.";
@DSGeneratedField(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.128 -0500", hash_original_field = "540BFAC708F7CD95235F861B636DD2C2", hash_generated_field = "B6CD2954F790C650C1566977B7063BED")

    private static final String[] ASYMMETRIC_CIPHERS =
    {
        "EC"
    };

    /**
     * Construct a new provider.  This should only be required when
     * using runtime registration of the provider using the
     * <code>Security.addProvider()</code> mechanism.
     */
    @DSSafe(DSCat.SAFE_OTHERS)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.133 -0500", hash_original_method = "A959A465DC8BCFFC9B23D0D2C15418FF", hash_generated_method = "83263833380411A00F94F79786397F7A")
    
public BouncyCastleProvider()
    {
        super(PROVIDER_NAME, 1.46, info);

        AccessController.doPrivileged(new PrivilegedAction()
        {
            public Object run()
            {
                setup();
                return null;
            }
        });
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.151 -0500", hash_original_method = "B0B8FDB1DF484505E991BFEF0E81F7AB", hash_generated_method = "753047AC83C01E8531C46D85518D63BA")
    
private void setup()
    {
        loadAlgorithms(SYMMETRIC_CIPHER_PACKAGE, SYMMETRIC_CIPHERS);
        loadAlgorithms(ASYMMETRIC_CIPHER_PACKAGE, ASYMMETRIC_CIPHERS);

        // BEGIN android-removed
        // //
        // // X509Store
        // //
        // put("X509Store.CERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertCollection");
        // put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.bouncycastle.jce.provider.X509StoreAttrCertCollection");
        // put("X509Store.CRL/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCRLCollection");
        // put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.bouncycastle.jce.provider.X509StoreCertPairCollection");
        //
        // put("X509Store.CERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCerts");
        // put("X509Store.CRL/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCRLs");
        // put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPAttrCerts");
        // put("X509Store.CERTIFICATEPAIR/LDAP", "org.bouncycastle.jce.provider.X509StoreLDAPCertPairs");
        //
        // //
        // // X509StreamParser
        // //
        // put("X509StreamParser.CERTIFICATE", "org.bouncycastle.jce.provider.X509CertParser");
        // put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.bouncycastle.jce.provider.X509AttrCertParser");
        // put("X509StreamParser.CRL", "org.bouncycastle.jce.provider.X509CRLParser");
        // put("X509StreamParser.CERTIFICATEPAIR", "org.bouncycastle.jce.provider.X509CertPairParser");
        // END android-removed

        //
        // KeyStore
        //
        put("KeyStore.BKS", "org.bouncycastle.jce.provider.JDKKeyStore");
        put("KeyStore.BouncyCastle", "org.bouncycastle.jce.provider.JDKKeyStore$BouncyCastleStore");
        put("KeyStore.PKCS12", "org.bouncycastle.jce.provider.JDKPKCS12KeyStore$BCPKCS12KeyStore");
        // BEGIN android-changed
        put("Alg.Alias.KeyStore.BCPKCS12", "PKCS12");
        // END android-changed
        // BEGIN android-removed
        // put("KeyStore.PKCS12-DEF", "org.bouncycastle.jce.provider.JDKPKCS12KeyStore$DefPKCS12KeyStore");
        // END android-removed

        // BEGIN android-changed
        put("Alg.Alias.KeyStore.PKCS12-3DES-40RC2", "PKCS12");
        // END android-changed
        // BEGIN android-removed
        // put("KeyStore.PKCS12-3DES-3DES", "org.bouncycastle.jce.provider.JDKPKCS12KeyStore$BCPKCS12KeyStore3DES");
        // END android-removed

        // BEGIN android-removed
        // put("KeyStore.PKCS12-DEF-3DES-40RC2", "org.bouncycastle.jce.provider.JDKPKCS12KeyStore$DefPKCS12KeyStore");
        // put("KeyStore.PKCS12-DEF-3DES-3DES", "org.bouncycastle.jce.provider.JDKPKCS12KeyStore$DefPKCS12KeyStore3DES");
        // END android-removed

        put("Alg.Alias.KeyStore.UBER", "BouncyCastle");
        put("Alg.Alias.KeyStore.BOUNCYCASTLE", "BouncyCastle");
        put("Alg.Alias.KeyStore.bouncycastle", "BouncyCastle");

        //
        // certificate factories.
        //
        put("CertificateFactory.X.509", "org.bouncycastle.jce.provider.JDKX509CertificateFactory");
        put("Alg.Alias.CertificateFactory.X509", "X.509");

        //
        // algorithm parameter generators
        //
        put("AlgorithmParameterGenerator.DH", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DH");
        put("AlgorithmParameterGenerator.DSA", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DSA");
        // BEGIN android-removed
        // put("AlgorithmParameterGenerator.GOST3410", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$GOST3410");
        // put("AlgorithmParameterGenerator.ELGAMAL", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$ElGamal");
        // put("AlgorithmParameterGenerator.DES", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DES");
        // put("AlgorithmParameterGenerator.DESEDE", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DES");
        // put("AlgorithmParameterGenerator." + PKCSObjectIdentifiers.des_EDE3_CBC, "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DES");
        // put("AlgorithmParameterGenerator." + OIWObjectIdentifiers.desCBC, "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$DES");
        // put("AlgorithmParameterGenerator.RC2", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$RC2");
        // put("AlgorithmParameterGenerator.1.2.840.113549.3.2", "org.bouncycastle.jce.provider.JDKAlgorithmParameterGenerator$RC2");
        // END android-removed

        put("Alg.Alias.AlgorithmParameterGenerator.DIFFIEHELLMAN", "DH");
        // BEGIN android-removed
        // put("Alg.Alias.AlgorithmParameterGenerator.GOST-3410", "GOST3410");
        // END android-removed
        //
        // algorithm parameters
        //
        put("AlgorithmParameters.OAEP", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$OAEP");
        // BEGIN android-removed
        // put("AlgorithmParameters.PSS", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$PSS");
        // END android-removed
        put("AlgorithmParameters.DH", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$DH");
        put("Alg.Alias.AlgorithmParameters.DIFFIEHELLMAN", "DH");
        put("AlgorithmParameters.DSA", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$DSA");
        // BEGIN android-removed
        // put("AlgorithmParameters.ELGAMAL", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$ElGamal");
        // put("AlgorithmParameters.IES", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$IES");
        // END android-removed
        put("AlgorithmParameters.PKCS12PBE", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$PKCS12PBE");
        // BEGIN android-changed
        // redundant with below
        // put("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        // END android-changed
        // BEGIN android-removed
        // put("AlgorithmParameters." + PKCSObjectIdentifiers.id_PBKDF2, "org.bouncycastle.jce.provider.JDKAlgorithmParameters$PBKDF2");
        //
        // put("AlgorithmParameters.GOST3410", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$GOST3410");
        // put("Alg.Alias.AlgorithmParameters.GOST-3410", "GOST3410");
        // END android-removed
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2", "PKCS12PBE");
        // BEGIN android-removed
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES", "PKCS12PBE");
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES", "PKCS12PBE");
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC2", "PKCS12PBE");
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC4", "PKCS12PBE");
        // END android-removed
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH", "PKCS12PBE");
        // BEGIN android-removed
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2-CBC", "PKCS12PBE");
        // END android-removed
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "PKCS12PBE");
        // BEGIN android-removed
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES3KEY-CBC", "PKCS12PBE");
        // put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES2KEY-CBC", "PKCS12PBE");
        // END android-removed
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC2-CBC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC4", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC2-CBC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC4", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDTWOFISH-CBC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.1", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.2", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.3", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.4", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.5", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.6", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWithSHAAnd3KeyTripleDES", "PKCS12PBE");
        
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId(), "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId(), "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId(), "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId(), "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId(), "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId(), "PKCS12PBE");

        put("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.id_RSAES_OAEP, "OAEP");
        
        // BEGIN android-removed
        // put("Alg.Alias.AlgorithmParameters.RSAPSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.RSASSA-PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.id_RSASSA_PSS, "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA1withRSA/PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA224withRSA/PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA256withRSA/PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA384withRSA/PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA512withRSA/PSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA1WITHRSAANDMGF1", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA224WITHRSAANDMGF1", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA256WITHRSAANDMGF1", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA384WITHRSAANDMGF1", "PSS");
        // put("Alg.Alias.AlgorithmParameters.SHA512WITHRSAANDMGF1", "PSS");
        // put("Alg.Alias.AlgorithmParameters.RAWRSAPSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.NONEWITHRSAPSS", "PSS");
        // put("Alg.Alias.AlgorithmParameters.NONEWITHRSASSA-PSS", "PSS");
        // END android-removed
        
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND192BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND256BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND128BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND192BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA256AND256BITAES-CBC-BC", "PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND128BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND192BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA1AND256BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND128BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND192BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-1AND256BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND128BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND192BITAES-CBC-BC","PKCS12PBE");
        put("Alg.Alias.AlgorithmParameters.PBEWITHSHA-256AND256BITAES-CBC-BC","PKCS12PBE");
        
        // BEGIN android-removed
        // put("AlgorithmParameters.SHA1WITHECDSA", "org.bouncycastle.jce.provider.JDKECDSAAlgParameters$SigAlgParameters");
        // put("AlgorithmParameters.SHA224WITHECDSA", "org.bouncycastle.jce.provider.JDKECDSAAlgParameters$SigAlgParameters");
        // put("AlgorithmParameters.SHA256WITHECDSA", "org.bouncycastle.jce.provider.JDKECDSAAlgParameters$SigAlgParameters");
        // put("AlgorithmParameters.SHA384WITHECDSA", "org.bouncycastle.jce.provider.JDKECDSAAlgParameters$SigAlgParameters");
        // put("AlgorithmParameters.SHA512WITHECDSA", "org.bouncycastle.jce.provider.JDKECDSAAlgParameters$SigAlgParameters");
        // END android-removed
        
        //
        // key agreement
        //
        put("KeyAgreement.DH", "org.bouncycastle.jce.provider.JCEDHKeyAgreement");
        put("Alg.Alias.KeyAgreement.DIFFIEHELLMAN", "DH");
        
        //
        // cipher engines
        //
        put("Cipher.DES", "org.bouncycastle.jce.provider.JCEBlockCipher$DES");
        // BEGIN android-removed
        // put("Cipher." + OIWObjectIdentifiers.desCBC, "org.bouncycastle.jce.provider.JCEBlockCipher$DESCBC");
        //
        // put("Cipher.RC2", "org.bouncycastle.jce.provider.JCEBlockCipher$RC2");
        // put("Cipher.RC2WRAP", "org.bouncycastle.jce.provider.WrapCipherSpi$RC2Wrap");
        // put("Cipher.1.2.840.113549.1.9.16.3.7", "org.bouncycastle.jce.provider.WrapCipherSpi$RC2Wrap");
        //
        // put("Cipher.1.2.840.113549.3.2", "org.bouncycastle.jce.provider.JCEBlockCipher$RC2CBC");
        // END android-removed
        
        put("Alg.Alias.Cipher.PBEWithSHAAnd3KeyTripleDES",  "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        
        // BEGIN android-removed
        // put("Cipher.GOST28147", "org.bouncycastle.jce.provider.JCEBlockCipher$GOST28147");
        // put("Alg.Alias.Cipher.GOST", "GOST28147");
        // put("Alg.Alias.Cipher.GOST-28147", "GOST28147");
        // put("Cipher." + CryptoProObjectIdentifiers.gostR28147_cbc, "org.bouncycastle.jce.provider.JCEBlockCipher$GOST28147cbc");
        // END android-removed

        put("Cipher.RSA", "org.bouncycastle.jce.provider.JCERSACipher$NoPadding");
        // BEGIN android-changed
        put("Alg.Alias.Cipher.RSA/RAW", "RSA");
        // END android-changed
        // BEGIN android-removed
        // put("Cipher.RSA/PKCS1", "org.bouncycastle.jce.provider.JCERSACipher$PKCS1v1_5Padding");
        // put("Cipher.1.2.840.113549.1.1.1", "org.bouncycastle.jce.provider.JCERSACipher$PKCS1v1_5Padding");
        // put("Cipher.2.5.8.1.1", "org.bouncycastle.jce.provider.JCERSACipher$PKCS1v1_5Padding");
        // put("Cipher.RSA/1", "org.bouncycastle.jce.provider.JCERSACipher$PKCS1v1_5Padding_PrivateOnly");
        // put("Cipher.RSA/2", "org.bouncycastle.jce.provider.JCERSACipher$PKCS1v1_5Padding_PublicOnly");
        // put("Cipher.RSA/OAEP", "org.bouncycastle.jce.provider.JCERSACipher$OAEPPadding");
        // put("Cipher." + PKCSObjectIdentifiers.id_RSAES_OAEP, "org.bouncycastle.jce.provider.JCERSACipher$OAEPPadding");
        // put("Cipher.RSA/ISO9796-1", "org.bouncycastle.jce.provider.JCERSACipher$ISO9796d1Padding");
        //
        // put("Cipher.ECIES", "org.bouncycastle.jce.provider.JCEIESCipher$ECIES");
        // put("Cipher.BrokenECIES", "org.bouncycastle.jce.provider.JCEIESCipher$BrokenECIES");
        // put("Cipher.IES", "org.bouncycastle.jce.provider.JCEIESCipher$IES");
        // put("Cipher.BrokenIES", "org.bouncycastle.jce.provider.JCEIESCipher$BrokenIES");
        // put("Cipher.ELGAMAL", "org.bouncycastle.jce.provider.JCEElGamalCipher$NoPadding");
        // put("Cipher.ELGAMAL/PKCS1", "org.bouncycastle.jce.provider.JCEElGamalCipher$PKCS1v1_5Padding");
        // END android-removed

        put("Alg.Alias.Cipher.RSA//RAW", "RSA");
        put("Alg.Alias.Cipher.RSA//NOPADDING", "RSA");
        // BEGIN android-removed
        // put("Alg.Alias.Cipher.RSA//PKCS1PADDING", "RSA/PKCS1");
        // put("Alg.Alias.Cipher.RSA//OAEPPADDING", "RSA/OAEP");
        // put("Alg.Alias.Cipher.RSA//ISO9796-1PADDING", "RSA/ISO9796-1");
        //
        // put("Alg.Alias.Cipher.ELGAMAL/ECB/PKCS1PADDING", "ELGAMAL/PKCS1");
        // put("Alg.Alias.Cipher.ELGAMAL/NONE/PKCS1PADDING", "ELGAMAL/PKCS1");
        // put("Alg.Alias.Cipher.ELGAMAL/NONE/NOPADDING", "ELGAMAL");
        // END android-removed

        put("Cipher.PBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithMD5AndDES");
        // BEGIN android-removed
        // put("Cipher.BROKENPBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        // END android-removed
        put("Cipher.PBEWITHMD5ANDRC2", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithMD5AndRC2");
        put("Cipher.PBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHA1AndDES");
        // BEGIN android-removed
        // put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        // END android-removed
        put("Cipher.PBEWITHSHA1ANDRC2", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHA1AndRC2");
        put("Cipher.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHAAndDES3Key");
        // BEGIN android-removed
        // put("Cipher.BROKENPBEWITHSHAAND3-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHAAndDES3Key");
        // put("Cipher.OLDPBEWITHSHAAND3-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndDES3Key");
        // END android-removed
        put("Cipher.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHAAndDES2Key");
        // BEGIN android-removed
        // put("Cipher.BROKENPBEWITHSHAAND2-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHAAndDES2Key");
        // END android-removed
        put("Cipher.PBEWITHSHAAND128BITRC2-CBC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHAAnd128BitRC2");
        put("Cipher.PBEWITHSHAAND40BITRC2-CBC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHAAnd40BitRC2");
        put("Cipher.PBEWITHSHAAND128BITRC4", "org.bouncycastle.jce.provider.JCEStreamCipher$PBEWithSHAAnd128BitRC4");
        put("Cipher.PBEWITHSHAAND40BITRC4", "org.bouncycastle.jce.provider.JCEStreamCipher$PBEWithSHAAnd40BitRC4");

        // BEGIN android-changed
        put("Alg.Alias.Cipher.PBEWITHSHA1AND3-KEYTRIPLEDES-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND2-KEYTRIPLEDES-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC2-CBC", "PBEWITHSHAAND128BITRC2-CBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC2-CBC", "PBEWITHSHAAND40BITRC2-CBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC4", "PBEWITHSHAAND128BITRC4");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC4", "PBEWITHSHAAND40BITRC4");
        // END android-changed

        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId(), "PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId(), "PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId(), "PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId(), "PBEWITHSHA256AND128BITAES-CBC-BC");
        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId(), "PBEWITHSHA256AND192BITAES-CBC-BC");
        put("Alg.Alias.Cipher." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId(), "PBEWITHSHA256AND256BITAES-CBC-BC");
        
        put("Cipher.PBEWITHSHAAND128BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHSHAAND192BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHSHAAND256BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHSHA256AND128BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHSHA256AND192BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHSHA256AND256BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND128BITAES-CBC-BC","PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND192BITAES-CBC-BC","PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA1AND256BITAES-CBC-BC","PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-1AND128BITAES-CBC-BC","PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-1AND192BITAES-CBC-BC","PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-1AND256BITAES-CBC-BC","PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-256AND128BITAES-CBC-BC","PBEWITHSHA256AND128BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-256AND192BITAES-CBC-BC","PBEWITHSHA256AND192BITAES-CBC-BC");
        put("Alg.Alias.Cipher.PBEWITHSHA-256AND256BITAES-CBC-BC","PBEWITHSHA256AND256BITAES-CBC-BC");
        
        put("Cipher.PBEWITHMD5AND128BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHMD5AND192BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        put("Cipher.PBEWITHMD5AND256BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithAESCBC");
        
        put("Cipher.PBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.JCEBlockCipher$PBEWithSHAAndTwofish");
        // BEGIN android-removed
        // put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        //
        // put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBEWITHMD2ANDDES");
        // put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC, "PBEWITHMD2ANDRC2");
        // END android-removed
        put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBEWITHMD5ANDDES");
        put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, "PBEWITHMD5ANDDES");
        put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBEWITHSHA1ANDDES");
        put("Alg.Alias.Cipher." + PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, "PBEWITHSHA1ANDRC2");

        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.1", "PBEWITHSHAAND128BITRC4");
        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.2", "PBEWITHSHAAND40BITRC4");
        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.3", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.4", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
        put("Alg.Alias.Cipher.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
        put("Alg.Alias.Cipher.PBEWITHSHA1ANDDESEDE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        //
        // key generators.
        //
        put("KeyGenerator.DES", "org.bouncycastle.jce.provider.JCEKeyGenerator$DES");
        put("Alg.Alias.KeyGenerator." + OIWObjectIdentifiers.desCBC, "DES");

        // BEGIN android-removed
        // put("KeyGenerator.RC2", "org.bouncycastle.jce.provider.JCEKeyGenerator$RC2");
        // put("KeyGenerator.1.2.840.113549.3.2", "org.bouncycastle.jce.provider.JCEKeyGenerator$RC2");
        //
        // put("KeyGenerator.GOST28147", "org.bouncycastle.jce.provider.JCEKeyGenerator$GOST28147");
        // put("Alg.Alias.KeyGenerator.GOST", "GOST28147");
        // put("Alg.Alias.KeyGenerator.GOST-28147", "GOST28147");
        // put("Alg.Alias.KeyGenerator." + CryptoProObjectIdentifiers.gostR28147_cbc, "GOST28147");
        // END android-removed

        //
        // key pair generators.
        //
        put("KeyPairGenerator.RSA", "org.bouncycastle.jce.provider.JDKKeyPairGenerator$RSA");
        put("KeyPairGenerator.DH", "org.bouncycastle.jce.provider.JDKKeyPairGenerator$DH");
        put("KeyPairGenerator.DSA", "org.bouncycastle.jce.provider.JDKKeyPairGenerator$DSA");
        // BEGIN android-removed
        // put("KeyPairGenerator.ELGAMAL", "org.bouncycastle.jce.provider.JDKKeyPairGenerator$ElGamal");
        // END android-removed

        put("Alg.Alias.KeyPairGenerator.1.2.840.113549.1.1.1", "RSA");
        put("Alg.Alias.KeyPairGenerator.DIFFIEHELLMAN", "DH");
        
        // BEGIN android-removed
        // put("KeyPairGenerator.GOST3410", "org.bouncycastle.jce.provider.JDKKeyPairGenerator$GOST3410");
        // put("Alg.Alias.KeyPairGenerator.GOST-3410", "GOST3410");
        // put("Alg.Alias.KeyPairGenerator.GOST-3410-94", "GOST3410");
        // END android-removed

        //
        // key factories
        //
        put("KeyFactory.RSA", "org.bouncycastle.jce.provider.JDKKeyFactory$RSA");
        put("KeyFactory.DH", "org.bouncycastle.jce.provider.JDKKeyFactory$DH");
        put("KeyFactory.DSA", "org.bouncycastle.jce.provider.JDKKeyFactory$DSA");
        // BEGIN android-removed
        // put("KeyFactory.ELGAMAL", "org.bouncycastle.jce.provider.JDKKeyFactory$ElGamal");
        // put("KeyFactory.ElGamal", "org.bouncycastle.jce.provider.JDKKeyFactory$ElGamal");
        //
        // put("KeyFactory.X.509", "org.bouncycastle.jce.provider.JDKKeyFactory$X509");
        // END android-removed
        
        put("Alg.Alias.KeyFactory.1.2.840.113549.1.1.1", "RSA");
        put("Alg.Alias.KeyFactory.1.2.840.10040.4.1", "DSA");

        put("Alg.Alias.KeyFactory.DIFFIEHELLMAN", "DH");

        // BEGIN android-removed
        // put("KeyFactory.GOST3410", "org.bouncycastle.jce.provider.JDKKeyFactory$GOST3410");
        // put("Alg.Alias.KeyFactory.GOST-3410", "GOST3410");
        // put("Alg.Alias.KeyFactory.GOST-3410-94", "GOST3410");
        // put("Alg.Alias.KeyFactory." + CryptoProObjectIdentifiers.gostR3410_94, "GOST3410");
        // END android-removed

        //
        // Algorithm parameters
        //
        put("AlgorithmParameters.DES", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$IVAlgorithmParameters");
        put("Alg.Alias.AlgorithmParameters." + OIWObjectIdentifiers.desCBC, "DES");
        put("AlgorithmParameters.DESEDE", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$IVAlgorithmParameters");
        // BEGIN android-changed
        put("Alg.Alias.AlgorithmParameters." + PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        // END android-changed
        // BEGIN android-removed
        // put("AlgorithmParameters.RC2", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$RC2AlgorithmParameters");
        // put("AlgorithmParameters.1.2.840.113549.3.2", "org.bouncycastle.jce.provider.JDKAlgorithmParameters$RC2AlgorithmParameters");
        // END android-removed
        
        //
        // secret key factories.
        //
        put("SecretKeyFactory.DES", "org.bouncycastle.jce.provider.JCESecretKeyFactory$DES");
        // BEGIN android-removed
        // put("SecretKeyFactory.PBEWITHMD2ANDDES", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD2AndDES");
        // END android-removed

        // BEGIN android-removed
        // put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBEWITHMD2ANDDES");
        // put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC, "PBEWITHMD2ANDRC2");
        // END android-removed
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBEWITHMD5ANDDES");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, "PBEWITHMD5ANDDES");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBEWITHSHA1ANDDES");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, "PBEWITHSHA1ANDRC2");

        // BEGIN android-removed
        // put("SecretKeyFactory.PBEWITHMD2ANDRC2", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD2AndRC2");
        // END android-removed
        put("SecretKeyFactory.PBEWITHMD5ANDDES", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD5AndDES");
        put("SecretKeyFactory.PBEWITHMD5ANDRC2", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD5AndRC2");
        put("SecretKeyFactory.PBEWITHSHA1ANDDES", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA1AndDES");
        put("SecretKeyFactory.PBEWITHSHA1ANDRC2", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA1AndRC2");
        put("SecretKeyFactory.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAndDES3Key");
        put("SecretKeyFactory.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAndDES2Key");
        put("SecretKeyFactory.PBEWITHSHAAND128BITRC4", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd128BitRC4");
        put("SecretKeyFactory.PBEWITHSHAAND40BITRC4", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd40BitRC4");
        put("SecretKeyFactory.PBEWITHSHAAND128BITRC2-CBC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd128BitRC2");
        put("SecretKeyFactory.PBEWITHSHAAND40BITRC2-CBC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd40BitRC2");
        put("SecretKeyFactory.PBEWITHSHAANDTWOFISH-CBC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAndTwofish");
        // BEGIN android-removed
        // put("SecretKeyFactory.PBEWITHHMACRIPEMD160", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithRIPEMD160");
        // END android-removed
        put("SecretKeyFactory.PBEWITHHMACSHA1", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA");
        // BEGIN android-removed
        // put("SecretKeyFactory.PBEWITHHMACTIGER", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithTiger");
        // END android-removed
        
        put("SecretKeyFactory.PBEWITHMD5AND128BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD5And128BitAESCBCOpenSSL");
        put("SecretKeyFactory.PBEWITHMD5AND192BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD5And192BitAESCBCOpenSSL");
        put("SecretKeyFactory.PBEWITHMD5AND256BITAES-CBC-OPENSSL", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithMD5And256BitAESCBCOpenSSL");

        // BEGIN android-removed
        // put("Alg.Alias.SecretKeyFactory.PBE", "PBE/PKCS5");
        //
        // put("Alg.Alias.SecretKeyFactory.BROKENPBEWITHMD5ANDDES", "PBE/PKCS5");
        // put("Alg.Alias.SecretKeyFactory.BROKENPBEWITHSHA1ANDDES", "PBE/PKCS5");
        // put("Alg.Alias.SecretKeyFactory.OLDPBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PBE/PKCS12");
        // put("Alg.Alias.SecretKeyFactory.BROKENPBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PBE/PKCS12");
        // put("Alg.Alias.SecretKeyFactory.BROKENPBEWITHSHAAND2-KEYTRIPLEDES-CBC", "PBE/PKCS12");
        // put("Alg.Alias.SecretKeyFactory.OLDPBEWITHSHAANDTWOFISH-CBC", "PBE/PKCS12");
        // END android-removed

        // BEGIN android-removed
        // put("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
        // put("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDRC2-CBC", "PBEWITHMD2ANDRC2");
        // END android-removed
        put("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
        put("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDRC2-CBC", "PBEWITHMD5ANDRC2");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDRC2-CBC", "PBEWITHSHA1ANDRC2");
        // BEGIN android-removed
        // put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC, "PBEWITHMD2ANDDES");
        // put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC, "PBEWITHMD2ANDRC2");
        // END android-removed
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC, "PBEWITHMD5ANDDES");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, "PBEWITHMD5ANDRC2");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC, "PBEWITHSHA1ANDDES");
        put("Alg.Alias.SecretKeyFactory." + PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, "PBEWITHSHA1ANDRC2");

        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.1", "PBEWITHSHAAND128BITRC4");
        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.2", "PBEWITHSHAAND40BITRC4");
        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.3", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.4", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
        put("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHHMACSHA", "PBEWITHHMACSHA1");
        put("Alg.Alias.SecretKeyFactory.1.3.14.3.2.26", "PBEWITHHMACSHA1");
        put("Alg.Alias.SecretKeyFactory.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        
        put("SecretKeyFactory.PBEWITHSHAAND128BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd128BitAESBC");
        put("SecretKeyFactory.PBEWITHSHAAND192BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd192BitAESBC");
        put("SecretKeyFactory.PBEWITHSHAAND256BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHAAnd256BitAESBC");
        put("SecretKeyFactory.PBEWITHSHA256AND128BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA256And128BitAESBC");
        put("SecretKeyFactory.PBEWITHSHA256AND192BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA256And192BitAESBC");
        put("SecretKeyFactory.PBEWITHSHA256AND256BITAES-CBC-BC", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBEWithSHA256And256BitAESBC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND128BITAES-CBC-BC","PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND192BITAES-CBC-BC","PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA1AND256BITAES-CBC-BC","PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND128BITAES-CBC-BC","PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND192BITAES-CBC-BC","PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-1AND256BITAES-CBC-BC","PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND128BITAES-CBC-BC","PBEWITHSHA256AND128BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND192BITAES-CBC-BC","PBEWITHSHA256AND192BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory.PBEWITHSHA-256AND256BITAES-CBC-BC","PBEWITHSHA256AND256BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes128_cbc.getId(), "PBEWITHSHAAND128BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes192_cbc.getId(), "PBEWITHSHAAND192BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha1_pkcs12_aes256_cbc.getId(), "PBEWITHSHAAND256BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes128_cbc.getId(), "PBEWITHSHA256AND128BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes192_cbc.getId(), "PBEWITHSHA256AND192BITAES-CBC-BC");
        put("Alg.Alias.SecretKeyFactory." + BCObjectIdentifiers.bc_pbe_sha256_pkcs12_aes256_cbc.getId(), "PBEWITHSHA256AND256BITAES-CBC-BC");
        // BEGIN android-added

        put("SecretKeyFactory.PBKDF2WithHmacSHA1", "org.bouncycastle.jce.provider.JCESecretKeyFactory$PBKDF2WithHmacSHA1");
        // END android-added

        addMacAlgorithms();

        addMessageDigestAlgorithms();

        addSignatureAlgorithms();

    // Certification Path API
        // BEGIN android-removed
        // put("CertPathValidator.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        // put("CertPathBuilder.RFC3281", "org.bouncycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        // END android-removed
        // BEGIN android-changed
        // Use Alg.Alias so RFC3280 doesn't show up when iterating provider services, only PKIX
        put("Alg.Alias.CertPathValidator.RFC3280", "PKIX");
        put("Alg.Alias.CertPathBuilder.RFC3280", "PKIX");
        // END android-changed
        put("CertPathValidator.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "org.bouncycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "org.bouncycastle.jce.provider.CertStoreCollectionSpi");
        // BEGIN android-removed
        // put("CertStore.LDAP", "org.bouncycastle.jce.provider.X509LDAPCertStoreSpi");
        // put("CertStore.Multi", "org.bouncycastle.jce.provider.MultiCertStoreSpi");
        // put("Alg.Alias.CertStore.X509LDAP", "LDAP");
        // END android-removed
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.154 -0500", hash_original_method = "686C79C0163D29E08379E22D5F4091D3", hash_generated_method = "02C3F3C487931441DA0EBB9E68C09D6F")
    
private void loadAlgorithms(String packageName, String[] names)
    {
        for (int i = 0; i != names.length; i++)
        {
            Class clazz = null;
            try
            {
                ClassLoader loader = this.getClass().getClassLoader();

                if (loader != null)
                {
                    clazz = loader.loadClass(packageName + names[i] + "$Mappings");
                }
                else
                {
                    clazz = Class.forName(packageName + names[i] + "$Mappings");
                }
            }
            catch (ClassNotFoundException e)
            {
                // ignore
            }

            if (clazz != null)
            {
                try
                {
                    addMappings((Map)clazz.newInstance());
                }
                catch (Exception e)
                {   // this should never ever happen!!
                    throw new InternalError("cannot create instance of "
                        + packageName + names[i] + "$Mappings : " + e);
                }
            }
        }
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.157 -0500", hash_original_method = "5007A2B2A77D22A1FFE2768D1D69CD26", hash_generated_method = "8C9B0958A6EA3788D9FF8221246989CF")
    
private void addMappings(Map mappings)
    {
        // can't use putAll due to JDK 1.1
        for (Iterator it = mappings.keySet().iterator(); it.hasNext();)
        {
            Object key = it.next();

            if (containsKey(key))
            {
                throw new IllegalStateException("duplicate provider key (" + key + ") found in " + mappings.getClass().getName());
            }
            put(key, mappings.get(key));
        }
    }

    //
    // macs
    //
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.160 -0500", hash_original_method = "BF810DB148D6F4B76C3D9477121B375F", hash_generated_method = "24512609174D25296C0AE935218C277E")
    
private void addMacAlgorithms()
    {
        // BEGIN android-removed
        // put("Mac.DESMAC", "org.bouncycastle.jce.provider.JCEMac$DES");
        // put("Alg.Alias.Mac.DES", "DESMAC");
        // put("Mac.DESMAC/CFB8", "org.bouncycastle.jce.provider.JCEMac$DESCFB8");
        // put("Alg.Alias.Mac.DES/CFB8", "DESMAC/CFB8");
        //
        // put("Mac.DESWITHISO9797", "org.bouncycastle.jce.provider.JCEMac$DES9797Alg3");
        // put("Alg.Alias.Mac.DESISO9797MAC", "DESWITHISO9797");
        //
        // put("Mac.ISO9797ALG3MAC", "org.bouncycastle.jce.provider.JCEMac$DES9797Alg3");
        // put("Alg.Alias.Mac.ISO9797ALG3", "ISO9797ALG3MAC");
        // put("Mac.ISO9797ALG3WITHISO7816-4PADDING", "org.bouncycastle.jce.provider.JCEMac$DES9797Alg3with7816d4");
        // put("Alg.Alias.Mac.ISO9797ALG3MACWITHISO7816-4PADDING", "ISO9797ALG3WITHISO7816-4PADDING");
        //
        // put("Mac.RC2MAC", "org.bouncycastle.jce.provider.JCEMac$RC2");
        // put("Alg.Alias.Mac.RC2", "RC2MAC");
        // put("Mac.RC2MAC/CFB8", "org.bouncycastle.jce.provider.JCEMac$RC2CFB8");
        // put("Alg.Alias.Mac.RC2/CFB8", "RC2MAC/CFB8");
        //
        //
        // put("Mac.GOST28147MAC", "org.bouncycastle.jce.provider.JCEMac$GOST28147");
        // put("Alg.Alias.Mac.GOST28147", "GOST28147MAC");
        //
        // put("Mac.OLDHMACSHA384", "org.bouncycastle.jce.provider.JCEMac$OldSHA384");
        //
        // put("Mac.OLDHMACSHA512", "org.bouncycastle.jce.provider.JCEMac$OldSHA512");
        //
        // addHMACAlgorithm("MD2", "org.bouncycastle.jce.provider.JCEMac$MD2", "org.bouncycastle.jce.provider.JCEKeyGenerator$MD2HMAC");
        // addHMACAlgorithm("MD4", "org.bouncycastle.jce.provider.JCEMac$MD4", "org.bouncycastle.jce.provider.JCEKeyGenerator$MD4HMAC");
        // END android-removed
        addHMACAlgorithm("MD5", "org.bouncycastle.jce.provider.JCEMac$MD5", "org.bouncycastle.jce.provider.JCEKeyGenerator$MD5HMAC");
        addHMACAlias("MD5", IANAObjectIdentifiers.hmacMD5);

        addHMACAlgorithm("SHA1", "org.bouncycastle.jce.provider.JCEMac$SHA1", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACSHA1");
        addHMACAlias("SHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
        addHMACAlias("SHA1", IANAObjectIdentifiers.hmacSHA1);
        // BEGIN android-removed
        // addHMACAlgorithm("SHA224", "org.bouncycastle.jce.provider.JCEMac$SHA224", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACSHA224");
        // addHMACAlias("SHA224", PKCSObjectIdentifiers.id_hmacWithSHA224);
        // END android-removed
        addHMACAlgorithm("SHA256", "org.bouncycastle.jce.provider.JCEMac$SHA256", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACSHA256");
        addHMACAlias("SHA256", PKCSObjectIdentifiers.id_hmacWithSHA256);
        addHMACAlgorithm("SHA384", "org.bouncycastle.jce.provider.JCEMac$SHA384", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACSHA384");
        addHMACAlias("SHA384", PKCSObjectIdentifiers.id_hmacWithSHA384);
        addHMACAlgorithm("SHA512", "org.bouncycastle.jce.provider.JCEMac$SHA512", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACSHA512");
        addHMACAlias("SHA512", PKCSObjectIdentifiers.id_hmacWithSHA512);

        // BEGIN android-removed
        // addHMACAlgorithm("RIPEMD128", "org.bouncycastle.jce.provider.JCEMac$RIPEMD128", "org.bouncycastle.jce.provider.JCEKeyGenerator$RIPEMD128HMAC");
        // addHMACAlgorithm("RIPEMD160", "org.bouncycastle.jce.provider.JCEMac$RIPEMD160", "org.bouncycastle.jce.provider.JCEKeyGenerator$RIPEMD160HMAC");
        // addHMACAlias("RIPEMD160", IANAObjectIdentifiers.hmacRIPEMD160);
        //
        // addHMACAlgorithm("TIGER", "org.bouncycastle.jce.provider.JCEMac$Tiger", "org.bouncycastle.jce.provider.JCEKeyGenerator$HMACTIGER");
        // addHMACAlias("TIGER", IANAObjectIdentifiers.hmacTIGER);
        // END android-removed

        put("Mac.PBEWITHHMACSHA", "org.bouncycastle.jce.provider.JCEMac$PBEWithSHA");
        put("Mac.PBEWITHHMACSHA1", "org.bouncycastle.jce.provider.JCEMac$PBEWithSHA");
        // BEGIN android-removed
        // put("Mac.PBEWITHHMACRIPEMD160", "org.bouncycastle.jce.provider.JCEMac$PBEWithRIPEMD160");
        // END android-removed
        put("Alg.Alias.Mac.1.3.14.3.2.26", "PBEWITHHMACSHA");
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.162 -0500", hash_original_method = "92FD2A26DA15B4D69904AC037AAE7782", hash_generated_method = "7E68A49C5E6B70148B8A3C7B7430B06E")
    
private void addHMACAlgorithm(
        String algorithm,
        String algorithmClassName,
        String keyGeneratorClassName)
    {
        String mainName = "HMAC" + algorithm;

        put("Mac." + mainName, algorithmClassName);
        put("Alg.Alias.Mac.HMAC-" + algorithm, mainName);
        put("Alg.Alias.Mac.HMAC/" + algorithm, mainName);
        put("KeyGenerator." + mainName, keyGeneratorClassName);
        put("Alg.Alias.KeyGenerator.HMAC-" + algorithm, mainName);
        put("Alg.Alias.KeyGenerator.HMAC/" + algorithm, mainName);
    }

    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.164 -0500", hash_original_method = "D71084920D31BEC8673D5601F90CF345", hash_generated_method = "F356194CE0CBEA34DC3CE2B3DC1313B8")
    
private void addHMACAlias(
        String              algorithm,
        DERObjectIdentifier oid)
    {
        String mainName = "HMAC" + algorithm;

        put("Alg.Alias.Mac." + oid, mainName);
        put("Alg.Alias.KeyGenerator." + oid, mainName);
    }

    //
    // message digests
    //
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.168 -0500", hash_original_method = "D1811F612B066C9B05A7CE06AED0A036", hash_generated_method = "6923C48A2FA6515FBAD586A55443BE9B")
    
private void addMessageDigestAlgorithms()
    {
        put("MessageDigest.SHA-1", "org.bouncycastle.jce.provider.JDKMessageDigest$SHA1");
        put("Alg.Alias.MessageDigest.SHA1", "SHA-1");
        put("Alg.Alias.MessageDigest.SHA", "SHA-1");
        put("Alg.Alias.MessageDigest." + OIWObjectIdentifiers.idSHA1, "SHA-1");
        // BEGIN android-removed
        // put("MessageDigest.SHA-224", "org.bouncycastle.jce.provider.JDKMessageDigest$SHA224");
        // put("Alg.Alias.MessageDigest.SHA224", "SHA-224");
        // put("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.id_sha224, "SHA-224");
        // END android-removed
        put("MessageDigest.SHA-256", "org.bouncycastle.jce.provider.JDKMessageDigest$SHA256");
        put("Alg.Alias.MessageDigest.SHA256", "SHA-256");
        put("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.id_sha256, "SHA-256");
        put("MessageDigest.SHA-384", "org.bouncycastle.jce.provider.JDKMessageDigest$SHA384");
        put("Alg.Alias.MessageDigest.SHA384", "SHA-384");
        put("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.id_sha384, "SHA-384");
        put("MessageDigest.SHA-512", "org.bouncycastle.jce.provider.JDKMessageDigest$SHA512");
        put("Alg.Alias.MessageDigest.SHA512", "SHA-512");
        put("Alg.Alias.MessageDigest." + NISTObjectIdentifiers.id_sha512, "SHA-512");
        
        // BEGIN android-removed
        // put("MessageDigest.MD2", "org.bouncycastle.jce.provider.JDKMessageDigest$MD2");
        // put("Alg.Alias.MessageDigest." + PKCSObjectIdentifiers.md2, "MD2");
        // put("MessageDigest.MD4", "org.bouncycastle.jce.provider.JDKMessageDigest$MD4");
        // put("Alg.Alias.MessageDigest." + PKCSObjectIdentifiers.md4, "MD4");
        // END android-removed
        put("MessageDigest.MD5", "org.bouncycastle.jce.provider.JDKMessageDigest$MD5");
        put("Alg.Alias.MessageDigest." + PKCSObjectIdentifiers.md5, "MD5");
        // BEGIN android-removed
        // put("MessageDigest.RIPEMD128", "org.bouncycastle.jce.provider.JDKMessageDigest$RIPEMD128");
        // put("Alg.Alias.MessageDigest." + TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        // put("MessageDigest.RIPEMD160", "org.bouncycastle.jce.provider.JDKMessageDigest$RIPEMD160");
        // put("Alg.Alias.MessageDigest." + TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        // put("MessageDigest.RIPEMD256", "org.bouncycastle.jce.provider.JDKMessageDigest$RIPEMD256");
        // put("Alg.Alias.MessageDigest." + TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        // put("MessageDigest.RIPEMD320", "org.bouncycastle.jce.provider.JDKMessageDigest$RIPEMD320");
        // put("MessageDigest.Tiger", "org.bouncycastle.jce.provider.JDKMessageDigest$Tiger");
        
        // put("MessageDigest.WHIRLPOOL", "org.bouncycastle.jce.provider.JDKMessageDigest$Whirlpool");
        
        // put("MessageDigest.GOST3411", "org.bouncycastle.jce.provider.JDKMessageDigest$GOST3411");
        // put("Alg.Alias.MessageDigest.GOST", "GOST3411");
        // put("Alg.Alias.MessageDigest.GOST-3411", "GOST3411");
        // put("Alg.Alias.MessageDigest." + CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        // END android-removed
    }
    
    //
    // signature algorithms.
    //
    @DSComment("Private Method")
    @DSBan(DSCat.PRIVATE_METHOD)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.173 -0500", hash_original_method = "7420A66ACC92234B394212961D34DE13", hash_generated_method = "6A0A569F3F65C21DF8973058772089AE")
    
private void addSignatureAlgorithms()
    {
        // BEGIN android-removed
        // Dropping MD2
        // put("Signature.MD2WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$MD2WithRSAEncryption");
        // put("Signature.MD4WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$MD4WithRSAEncryption");
        // END android-removed
        put("Signature.MD5WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$MD5WithRSAEncryption");
        put("Signature.SHA1WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Signature.SHA224WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$SHA224WithRSAEncryption");
        // END android-removed
        put("Signature.SHA256WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$SHA256WithRSAEncryption");
        put("Signature.SHA384WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$SHA384WithRSAEncryption");
        put("Signature.SHA512WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$SHA512WithRSAEncryption");
        // BEGIN android-removed
        // put("Signature.RIPEMD160WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$RIPEMD160WithRSAEncryption");
        // put("Signature.RIPEMD128WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$RIPEMD128WithRSAEncryption");
        // put("Signature.RIPEMD256WithRSAEncryption", "org.bouncycastle.jce.provider.JDKDigestSignature$RIPEMD256WithRSAEncryption");
        // END android-removed
        // BEGIN android-changed
        put("Signature.SHA1withDSA", "org.bouncycastle.jce.provider.JDKDSASigner$stdDSA");
        // END android-changed
        put("Signature.NONEWITHDSA", "org.bouncycastle.jce.provider.JDKDSASigner$noneDSA");
        // BEGIN android-removed
        // put("Signature.SHA1withRSA/ISO9796-2", "org.bouncycastle.jce.provider.JDKISOSignature$SHA1WithRSAEncryption");
        // put("Signature.MD5withRSA/ISO9796-2", "org.bouncycastle.jce.provider.JDKISOSignature$MD5WithRSAEncryption");
        // put("Signature.RIPEMD160withRSA/ISO9796-2", "org.bouncycastle.jce.provider.JDKISOSignature$RIPEMD160WithRSAEncryption");
        //
        // put("Signature.RSASSA-PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$PSSwithRSA");
        // put("Signature." + PKCSObjectIdentifiers.id_RSASSA_PSS, "org.bouncycastle.jce.provider.JDKPSSSigner$PSSwithRSA");
        // put("Signature.SHA1withRSA/PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$SHA1withRSA");
        // put("Signature.SHA224withRSA/PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$SHA224withRSA");
        // put("Signature.SHA256withRSA/PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$SHA256withRSA");
        // put("Signature.SHA384withRSA/PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$SHA384withRSA");
        // put("Signature.SHA512withRSA/PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$SHA512withRSA");
        //
        // put("Signature.RSA", "org.bouncycastle.jce.provider.JDKDigestSignature$noneRSA");
        // put("Signature.RAWRSASSA-PSS", "org.bouncycastle.jce.provider.JDKPSSSigner$nonePSS");
        // END android-removed

        put("Alg.Alias.Signature.RAWDSA", "NONEWITHDSA");

        // BEGIN android-removed
        // put("Alg.Alias.Signature.RAWRSA", "RSA");
        // put("Alg.Alias.Signature.NONEWITHRSA", "RSA");
        // put("Alg.Alias.Signature.RAWRSAPSS", "RAWRSASSA-PSS");
        // put("Alg.Alias.Signature.NONEWITHRSAPSS", "RAWRSASSA-PSS");
        // put("Alg.Alias.Signature.NONEWITHRSASSA-PSS", "RAWRSASSA-PSS");
        //
        // put("Alg.Alias.Signature.RSAPSS", "RSASSA-PSS");
        //
        // put("Alg.Alias.Signature.SHA1withRSAandMGF1", "SHA1withRSA/PSS");
        // put("Alg.Alias.Signature.SHA224withRSAandMGF1", "SHA224withRSA/PSS");
        // put("Alg.Alias.Signature.SHA256withRSAandMGF1", "SHA256withRSA/PSS");
        // put("Alg.Alias.Signature.SHA384withRSAandMGF1", "SHA384withRSA/PSS");
        // put("Alg.Alias.Signature.SHA512withRSAandMGF1", "SHA512withRSA/PSS");
        //
        // put("Alg.Alias.Signature.MD2withRSAEncryption", "MD2WithRSAEncryption");
        // put("Alg.Alias.Signature.MD4withRSAEncryption", "MD4WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature.MD5withRSAEncryption", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature.SHA1withRSAEncryption", "SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.SHA224withRSAEncryption", "SHA224WithRSAEncryption");
        // END android-removed

        put("Alg.Alias.Signature.SHA256withRSAEncryption", "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature.SHA384withRSAEncryption", "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature.SHA512withRSAEncryption", "SHA512WithRSAEncryption");

        put("Alg.Alias.Signature.SHA256WithRSAEncryption", "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature.SHA384WithRSAEncryption", "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature.SHA512WithRSAEncryption", "SHA512WithRSAEncryption");

        put("Alg.Alias.Signature.SHA256WITHRSAENCRYPTION", "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature.SHA384WITHRSAENCRYPTION", "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature.SHA512WITHRSAENCRYPTION", "SHA512WithRSAEncryption");

        // BEGIN android-removed
        // Dropping MD2
        // put("Alg.Alias.Signature.RIPEMD160withRSAEncryption", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature." + PKCSObjectIdentifiers.md2WithRSAEncryption, "MD2WithRSAEncryption");
        // put("Alg.Alias.Signature.MD2WithRSA", "MD2WithRSAEncryption");
        // put("Alg.Alias.Signature.MD2withRSA", "MD2WithRSAEncryption");
        // put("Alg.Alias.Signature.MD2/RSA", "MD2WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature.MD5WithRSA", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature.MD5withRSA", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature.MD5/RSA", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature." + PKCSObjectIdentifiers.md5WithRSAEncryption, "MD5WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.MD4WithRSA", "MD4WithRSAEncryption");
        // put("Alg.Alias.Signature.MD4withRSA", "MD4WithRSAEncryption");
        // put("Alg.Alias.Signature.MD4/RSA", "MD4WithRSAEncryption");
        // put("Alg.Alias.Signature." + PKCSObjectIdentifiers.md4WithRSAEncryption, "MD4WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature.SHA1WithRSA", "SHA1WithRSAEncryption");
        put("Alg.Alias.Signature.SHA1withRSA", "SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.SHA224WithRSA", "SHA224WithRSAEncryption");
        // put("Alg.Alias.Signature.SHA224withRSA", "SHA224WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature.SHA256WithRSA", "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature.SHA256withRSA", "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature.SHA384WithRSA", "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature.SHA384withRSA", "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature.SHA512WithRSA", "SHA512WithRSAEncryption");
        put("Alg.Alias.Signature.SHA512withRSA", "SHA512WithRSAEncryption");
        put("Alg.Alias.Signature.SHA1/RSA", "SHA1WithRSAEncryption");
        put("Alg.Alias.Signature.SHA-1/RSA", "SHA1WithRSAEncryption");
        put("Alg.Alias.Signature." + PKCSObjectIdentifiers.sha1WithRSAEncryption, "SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature." + PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature." + PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WithRSAEncryption");
        put("Alg.Alias.Signature." + PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WithRSAEncryption");
        put("Alg.Alias.Signature." + PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WithRSAEncryption");
        put("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.113549.1.1.1", "SHA1WithRSAEncryption");
        put("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.113549.1.1.5", "SHA1WithRSAEncryption");
        put("Alg.Alias.Signature.1.2.840.113549.2.5with1.2.840.113549.1.1.1", "MD5WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.RIPEMD160WithRSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD160withRSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD128WithRSA", "RIPEMD128WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD128withRSA", "RIPEMD128WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD256WithRSA", "RIPEMD256WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD256withRSA", "RIPEMD256WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD-160/RSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RMD160withRSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RMD160/RSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.1.3.36.3.3.1.2", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.1.3.36.3.3.1.3", "RIPEMD128WithRSAEncryption");
        // put("Alg.Alias.Signature.1.3.36.3.3.1.4", "RIPEMD256WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature." + OIWObjectIdentifiers.sha1WithRSA, "SHA1WithRSAEncryption");
        
        // BEGIN android-removed
        // put("Alg.Alias.Signature.MD2WITHRSAENCRYPTION", "MD2WithRSAEncryption");
        // END android-removed
        put("Alg.Alias.Signature.MD5WITHRSAENCRYPTION", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature.SHA1WITHRSAENCRYPTION", "SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.RIPEMD160WITHRSAENCRYPTION", "RIPEMD160WithRSAEncryption");
        // END android-removed

        put("Alg.Alias.Signature.MD5WITHRSA", "MD5WithRSAEncryption");
        put("Alg.Alias.Signature.SHA1WITHRSA", "SHA1WithRSAEncryption");
        // BEGIN android-removed
        // put("Alg.Alias.Signature.RIPEMD160WITHRSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RMD160WITHRSA", "RIPEMD160WithRSAEncryption");
        // put("Alg.Alias.Signature.RIPEMD160WITHRSA", "RIPEMD160WithRSAEncryption");
        // END android-removed

        // BEGIN android-removed
        // addSignatureAlgorithm("SHA224", "DSA", "org.bouncycastle.jce.provider.JDKDSASigner$dsa224", NISTObjectIdentifiers.dsa_with_sha224);
        // addSignatureAlgorithm("SHA256", "DSA", "org.bouncycastle.jce.provider.JDKDSASigner$dsa256", NISTObjectIdentifiers.dsa_with_sha256);
        // addSignatureAlgorithm("SHA384", "DSA", "org.bouncycastle.jce.provider.JDKDSASigner$dsa384", NISTObjectIdentifiers.dsa_with_sha384);
        // addSignatureAlgorithm("SHA512", "DSA", "org.bouncycastle.jce.provider.JDKDSASigner$dsa512", NISTObjectIdentifiers.dsa_with_sha512);
        // END android-removed

        // BEGIN android-changed
        put("Alg.Alias.Signature.SHA/DSA", "SHA1withDSA");
        put("Alg.Alias.Signature.DSA", "SHA1withDSA");
        put("Alg.Alias.Signature.SHA1WITHDSA", "SHA1withDSA");
        put("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.1", "SHA1withDSA");
        put("Alg.Alias.Signature.1.3.14.3.2.26with1.2.840.10040.4.3", "SHA1withDSA");
        put("Alg.Alias.Signature.DSAwithSHA1", "SHA1withDSA");
        put("Alg.Alias.Signature.DSAWITHSHA1", "SHA1withDSA");
        put("Alg.Alias.Signature.SHA1WithDSA", "SHA1withDSA");
        put("Alg.Alias.Signature.DSAWithSHA1", "SHA1withDSA");
        put("Alg.Alias.Signature.1.2.840.10040.4.3", "SHA1withDSA");
        // END android-changed
        // BEGIN android-removed
        // put("Alg.Alias.Signature.MD5WithRSA/ISO9796-2", "MD5withRSA/ISO9796-2");
        // put("Alg.Alias.Signature.SHA1WithRSA/ISO9796-2", "SHA1withRSA/ISO9796-2");
        // put("Alg.Alias.Signature.RIPEMD160WithRSA/ISO9796-2", "RIPEMD160withRSA/ISO9796-2");
        //
        // put("Signature.ECGOST3410", "org.bouncycastle.jce.provider.JDKGOST3410Signer$ecgost3410");
        // put("Alg.Alias.Signature.ECGOST-3410", "ECGOST3410");
        // put("Alg.Alias.Signature.GOST-3410-2001", "ECGOST3410");
        // put("Alg.Alias.Signature.GOST3411withECGOST3410", "ECGOST3410");
        // put("Alg.Alias.Signature.GOST3411WITHECGOST3410", "ECGOST3410");
        // put("Alg.Alias.Signature.GOST3411WithECGOST3410", "ECGOST3410");
        // put("Alg.Alias.Signature." + CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "ECGOST3410");
        //
        // put("Signature.GOST3410", "org.bouncycastle.jce.provider.JDKGOST3410Signer$gost3410");
        // put("Alg.Alias.Signature.GOST-3410", "GOST3410");
        // put("Alg.Alias.Signature.GOST-3410-94", "GOST3410");
        // put("Alg.Alias.Signature.GOST3411withGOST3410", "GOST3410");
        // put("Alg.Alias.Signature.GOST3411WITHGOST3410", "GOST3410");
        // put("Alg.Alias.Signature.GOST3411WithGOST3410", "GOST3410");
        // put("Alg.Alias.Signature." + CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3410");
        // END android-removed
    }

    // BEGIN android-removed
    // private void addSignatureAlgorithm(
    //     String digest,
    //     String algorithm,
    //     String className,
    //     DERObjectIdentifier oid)
    // {
    //     String mainName = digest + "WITH" + algorithm;
    //     String jdk11Variation1 = digest + "with" + algorithm;
    //     String jdk11Variation2 = digest + "With" + algorithm;
    //     String alias = digest + "/" + algorithm;
    //
    //     put("Signature." + mainName, className);
    //     put("Alg.Alias.Signature." + jdk11Variation1, mainName);
    //     put("Alg.Alias.Signature." + jdk11Variation2, mainName);
    //     put("Alg.Alias.Signature." + alias, mainName);
    //     put("Alg.Alias.Signature." + oid, mainName);
    //     put("Alg.Alias.Signature.OID." + oid, mainName);
    // }
    // END android-removed

    @DSGenerator(tool_name = "Doppelganger", tool_version = "2.0", generated_on = "2013-12-30 13:00:35.176 -0500", hash_original_method = "393A7CD364AF31C5A55C410D9408BF1B", hash_generated_method = "C457D365072D6C724410858C49E71599")
    
public void setParameter(String parameterName, Object parameter)
    {
        ProviderUtil.setParameter(parameterName, parameter);
    }
}

