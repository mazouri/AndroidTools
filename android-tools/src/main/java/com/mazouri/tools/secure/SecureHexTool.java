package com.mazouri.tools.secure;

import com.mazouri.tools.string.StringTool;

import java.nio.charset.Charset;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class SecureHexTool {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    private Charset charset;

    public static byte[] decodeHex(char[] data) {
        int len = data.length;

        if ((len & 0x1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        int i = 0;
        for (int j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = ((byte) (f & 0xFF));
        }

        return out;
    }

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];

        int i = 0;
        for (int j = 0; i < l; i++) {
            out[(j++)] = toDigits[((0xF0 & data[i]) >>> 4)];
            out[(j++)] = toDigits[(0xF & data[i])];
        }
        return out;
    }

    public static String encodeHexString(byte[] data) {
        return new String(encodeHex(data));
    }

    protected static int toDigit(char ch, int index)
            throws RuntimeException {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public SecureHexTool() {
        this.charset = DEFAULT_CHARSET;
    }

    private static final Object lock = new Object();
    private static volatile SecureHexTool instance;

    public static SecureHexTool instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new SecureHexTool();
                }
            }
        }
        return instance;
    }

    public SecureHexTool charset(Charset charset) {
        this.charset = charset;
        return this;
    }

    public SecureHexTool charsetName(String charsetName) {
        return charset(Charset.forName(charsetName));
    }

    public byte[] decode(byte[] array) {
        return decodeHex(new String(array, getCharset()).toCharArray());
    }

    public Object decode(Object object) {
        try {
            char[] charArray = (object instanceof String) ? ((String) object).toCharArray() : (char[]) object;
            return decodeHex(charArray);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] encode(byte[] array) {
        return encodeHexString(array).getBytes(getCharset());
    }

    public Object encode(Object object) {
        try {
            byte[] byteArray = (object instanceof String) ? ((String) object).getBytes(getCharset()) : (byte[]) object;

            return encodeHex(byteArray);
        } catch (ClassCastException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getCharsetName() {
        return this.charset.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }
}
