package com.gsys.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

	public static String md5(String password) {
		if (password == null) {
			return password;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException ex) {
			//ex.printStackTrace();
			//throw new RuntimeException("couldn't make digest of partial content");
		}
		return null;
	}

}
