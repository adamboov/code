package com.adam.code.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 * @author VAIO-adam
 */
public class CryptographyUtil {

    /**
     * MD5加密
     */
    public static String MD5(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }


}
