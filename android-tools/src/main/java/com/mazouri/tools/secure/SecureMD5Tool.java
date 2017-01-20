package com.mazouri.tools.secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class SecureMD5Tool {

    // 全局数组
    private final static String[] strDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

    private static final Object lock = new Object();
    private static volatile SecureMD5Tool instance;

    private SecureMD5Tool() {
    }

    public static SecureMD5Tool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SecureMD5Tool();
                }
            }
        }
        return instance;
    }

    /**
     * 返回形式为数字跟字符串
     * @param bByte
     * @return
     */
    private String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 返回形式只为数字
     * @param bByte
     * @return
     */
    private String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    /**
     * 转换字节数组为16进制字串
     * @param bByte
     * @return
     */
    private String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 获取MD5编码
     * @param source
     * @return
     */
    public String getMD5Code(String source) {
        String resultString = null;
        try {
            resultString = new String(source);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(source.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }
}
