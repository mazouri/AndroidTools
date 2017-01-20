package com.mazouri.tools.regex;

import com.mazouri.tools.AndroidTools;
import com.mazouri.tools.IRegex;

import java.util.regex.Pattern;

/**
 * Created by wangdongdong on 17-1-20.
 */

public final class RegexImpl implements IRegex {

    /**
     * 正则表达式:验证用户名(不包含中文和特殊字符)如果用户名使用手机号码或邮箱 则结合手机号验证和邮箱验证
     */
    private static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式:验证密码(不包含特殊字符)
     */
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式:验证手机号
     */
    private static final String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";

    /**
     * 正则表达式:验证邮箱
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式:验证汉字(1-9个汉字)  {1,9} 自定义区间
     */
    private static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{1,9}$";

    /**
     * 正则表达式:验证身份证
     */
    private static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{17}([0-9]|xX)$)";

    /**
     * 正则表达式:验证URL
     */
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式:验证IP地址
     */
    private static final String REGEX_IP_ADDR = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

    private static final Object lock = new Object();
    private static volatile RegexImpl instance;

    private RegexImpl() {
    }

    public static RegexImpl instance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RegexImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean isUserName(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    @Override
    public boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    @Override
    public boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    @Override
    public boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    @Override
    public boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    @Override
    public boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    @Override
    public boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    @Override
    public boolean isIPAddress(String ipAddress) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddress);
    }
}
