package kr.re.nsr.crypto;

import kr.re.nsr.crypto.BlockCipher.Mode;

import kr.re.nsr.crypto.engine.LeaEngine;

public class Main {

	public static void main(String[] args) {
		// ExpPractice.printPractice(null);
		
		
		byte[] plainText = {
				(byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, (byte)0x14, (byte)0x15, (byte)0x16, (byte)0x17,
				(byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f
		};
		
		byte[] cipherText = new byte[16];
		
		/**
		 * LEA-128 Test
		 */
		System.out.println(">> LEA-128 test");
		System.out.print("Plain Text : ");
		for (int i = 0; i < plainText.length; i++) System.out.printf("%02x ", plainText[i]);
		System.out.println();
		
		byte[] mk_128 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0
		};
		
		LeaEngine leaEngine = new LeaEngine();
		leaEngine.init(Mode.ENCRYPT, mk_128);
		
		// Encryption Test
		System.out.println("do Enctyprion");
		leaEngine.processBlock(plainText, 0, cipherText, 0);
		
		System.out.print("Cipher Text : ");
		for (int i = 0; i < cipherText.length; i++) System.out.printf("%02x ", cipherText[i]);
		System.out.println();
		
		System.out.println(">> LEA-128 test done\n\n");
		
		
		/**
		 * LEA-192 Test
		 */
		System.out.println(">> LEA-192 test");
		byte[] mk_192 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0,
				(byte)0xf0, (byte)0xe1, (byte)0xd2, (byte)0xc3, (byte)0xb4, (byte)0xa5, (byte)0x96, (byte)0x87
		};
		
		leaEngine = new LeaEngine();
		leaEngine.init(null, mk_192);
		
		System.out.println(">> LEA-192 test done\n\n");
		
		
		/**
		 * LEA-256 Test
		 */
		System.out.println(">> LEA-256 test");
		byte[] mk_256 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0,
				(byte)0xf0, (byte)0xe1, (byte)0xd2, (byte)0xc3, (byte)0xb4, (byte)0xa5, (byte)0x96, (byte)0x87,
				(byte)0x78, (byte)0x69, (byte)0x5a, (byte)0x4b, (byte)0x3c, (byte)0x2d, (byte)0x1e, (byte)0x0f
		};
		
		leaEngine = new LeaEngine();
		leaEngine.init(null, mk_256);
		
		System.out.println(">> LEA-256 test done\n\n");
	}

}
