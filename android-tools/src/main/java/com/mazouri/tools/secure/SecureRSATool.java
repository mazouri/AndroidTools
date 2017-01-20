package com.mazouri.tools.secure;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class SecureRSATool {
    /**
     * 填充方式
     */
    public static enum PADDING { NoPadding, PKCS1Padding };
    /**
     * 算法
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 算法/工作模式
     */
    public final static String CHIPER_ALGORITHM = "RSA/ECB/";
    /**
     * 密钥长度
     */
    public static final int KEY_SIZE = 1024;

    /**
     * 65537 or 0x010001
     */
    public static final byte[] PUBLIC_EXPONENT = { 1, 0, 1 };

    private static final Object lock = new Object();
    private static volatile SecureRSATool instance;

    private SecureRSATool() {
    }

    public static SecureRSATool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SecureRSATool();
                }
            }
        }
        return instance;
    }

    /**
     * 生成密钥对
     *
     * @return KeyPair
     */
    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new RuntimeException("Error when init key pair, errmsg: "
                    + e.getMessage(), e);
        }
    }

    /**
     * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return
     */
    private RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        try {
            RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                    1, modulus), new BigInteger(1, publicExponent));
            KeyFactory keyFac = KeyFactory.getInstance(KEY_ALGORITHM);
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error when generate rsaPubblicKey, errmsg: "
                            + e.getMessage(), e);
        }

    }

    /**
     * 生成私钥
     *
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     */
    private RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        try {
            KeyFactory keyFac = KeyFactory.getInstance(KEY_ALGORITHM);
            RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(
                    new BigInteger(1, modulus), new BigInteger(1,
                    privateExponent));
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error when generate rsaPrivateKey, errmsg: "
                            + e.getMessage(), e);
        }
    }

    /**
     * 加密
     *
     * @param key
     *            加密的密钥
     * @param data
     *            待加密的明文数据
     * @return 加密后的数据
     */
    private byte[] encrypt(Key key, byte[] data, PADDING padding) {
        try {
            Cipher cipher = Cipher.getInstance(CHIPER_ALGORITHM+(padding==null?PADDING.NoPadding:padding));
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error when encrypt data, errmsg: "
                    + e.getMessage(), e);
        }
    }

    /**
     * 公钥加密
     *
     * @param publicKey
     * @param data
     * @return
     */
    public byte[] encryptByPublicKey(byte[] publicKey, byte[] data, PADDING padding) {
        // 得到公钥
        RSAPublicKey key = generateRSAPublicKey(publicKey, PUBLIC_EXPONENT);
        // 加密
        return encrypt(key, data,padding);
    }

    /**
     * 私钥加密
     *
     * @param publicKey
     * @param privateKey
     * @param data
     * @return
     */
    public byte[] encryptByPrivateKey(byte[] publicKey, byte[] privateKey, byte[] data, PADDING padding) {
        // 得到私钥
        RSAPrivateKey key = generateRSAPrivateKey(publicKey, privateKey);
        // 加密
        return encrypt(key, data,padding);
    }

    /**
     * 解密
     *
     * @param key
     *            解密的密钥
     * @param data
     *            已经加密的数据
     * @return 解密后的明文
     */
    private byte[] decrypt(Key key, byte[] data, PADDING padding) {
        try {
            Cipher cipher = Cipher.getInstance(CHIPER_ALGORITHM+(padding==null?PADDING.NoPadding:padding));
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error when decrypt data, errmsg: "
                    + e.getMessage(), e);
        }
    }

    /**
     * 公钥解密
     *
     * @param publicKey
     * @param data
     * @ret公钥urn
     */
    public byte[] decryptByPublicKey(byte[] publicKey, byte[] data, PADDING padding) {
        // 得到公钥
        RSAPublicKey key = generateRSAPublicKey(publicKey, PUBLIC_EXPONENT);
        // 解密
        return decrypt(key, data,padding);
    }

    /**
     * 私钥解密
     *
     * @param publicKey
     * @param privateKey
     * @param data
     * @return
     */
    public byte[] decryptByPrivateKey(byte[] publicKey, byte[] privateKey, byte[] data, PADDING padding) {
        // 得到私钥
        RSAPrivateKey key = generateRSAPrivateKey(publicKey, privateKey);
        // 解密
        return decrypt(key, data,padding);
    }
}
