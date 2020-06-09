package com.system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static void main(String[] args) {
        String s = requestTool("123456");
        System.out.println(s);
    }

    public static String requestTool(String requestMd5password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(requestMd5password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
