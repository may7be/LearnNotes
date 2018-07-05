package com.zg.android_utils.util_common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncryptUtil {

	public static String encreptPass(String password) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		char[] charArray = password.toCharArray();

        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] mdBytes = md.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < mdBytes.length; i++) {
            int val = ((int) mdBytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
	}
}
