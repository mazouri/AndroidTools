package com.mazouri.tools;

/**
 * Created by wangdongdong on 17-1-20.
 */

/**
 * 利用正则表达式校验邮箱、手机号等
 */
public interface IRegex {

    /**
     * 校验用户名
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    boolean isUserName(String username);

    /**
     * 校验密码
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    boolean isPassword(String password);

    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    boolean isMobile(String mobile);

    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    boolean isEmail(String email);

    /**
     * 校验汉字
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    boolean isChinese(String chinese);

    /**
     * 校验身份证
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    boolean isIDCard(String idCard);

    /**
     * 校验URL
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    boolean isUrl(String url);

    /**
     * 校验IP地址
     * @param ipAddress
     * @return
     */
    boolean isIPAddress(String ipAddress);
}
