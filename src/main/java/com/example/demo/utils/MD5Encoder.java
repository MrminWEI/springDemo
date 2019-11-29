package com.example.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Encoder {
    static Logger log = LoggerFactory.getLogger(MD5Encoder.class);

    public static String generatePassword(String userName, String password) {
        String encodeStr = DigestUtils.md5Hex(userName + password);
        return encodeStr;
    }

    public static void main(String[] args) {
        System.out.println("密码：" + MD5Encoder.generatePassword("super", "z"));
    }
}