package com.beta.replyservice;

import com.beta.replyservice.exception.InvalidInput;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class ReplyMessage {

	private String data;

	public ReplyMessage(String data) {
		this.data = data;
	}

	public ReplyMessage(String rule, String string) throws Exception {
		// validate rule and string
		if (Pattern.matches("[12]+", rule) && Pattern.matches("[a-z0-9]+", string)) {
			// process rule and string
			for (char r : rule.toCharArray()) {
				string = r == '1' ? ReverseString(string) : EncodeString(string);
			}
			this.data = string;
		} else {
			throw new InvalidInput();
		}
	}

	private String EncodeString(String message) throws Exception {
		try {
			// Create MD5 hash using build-in security library
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(message.getBytes());
			byte[] messageDigest = digest.digest();

			// Convert byte array to Hex string
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(Integer.toHexString(0xFF & b));
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new Exception("Internal Server Error");
		}
	}

	private String ReverseString(String message) {
		return  new StringBuilder(message).reverse().toString();
	}

	public String getMessage() {
		return data;
	}
}