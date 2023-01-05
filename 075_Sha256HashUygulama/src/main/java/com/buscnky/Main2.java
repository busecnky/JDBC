package com.buscnky;

import org.apache.commons.codec.digest.DigestUtils;

public class Main2 {
	
	public static void main(String[] args) {
		
	
		String s = DigestUtils.sha256Hex("123");
		System.out.println("Ali'nin şifresi 123'ün SHA256 ile hashlenmiş hali: " + s);
		

		String s2 = DigestUtils.sha256Hex("123");
		System.out.println("Veli'nin şifresi 321'in SHA256 ile hashlenmiş hali: " + s2);
		
	}
}
