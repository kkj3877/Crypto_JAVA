package kr.re.nsr.crypto;

import kr.re.nsr.crypto.BlockCipher.Mode;

import kr.re.nsr.crypto.engine.LeaEngine;
import kr.re.nsr.crypto.mode.ECBMode;
import symm.LEA;

public class Main {

	public static void main(String[] args) {
		// ExpPractice.printPractice(null);
		
		
		/**
		 * LEA-128 Test
		 */
		// Set Test Data
		byte[] plainText_128 = {
				(byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, (byte)0x14, (byte)0x15, (byte)0x16, (byte)0x17,
				(byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f
		};
		
		System.out.println(">> LEA-128 test");
		System.out.print("Plain Text : ");
		for (int i = 0; i < plainText_128.length; i++) System.out.printf("%02x ", plainText_128[i]);
		System.out.println();
		
		byte[] mk_128 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0
		};
		
		
		// Encryption Test
		System.out.println("do Enctyprion");
		
		LeaEngine LeaEncrypter = new LeaEngine();
		LeaEncrypter.init(Mode.ENCRYPT, mk_128);
		
		byte[] cipherText_128 = new byte[16];
		LeaEncrypter.processBlock(plainText_128, 0, cipherText_128, 0);
		
		System.out.print("Cipher Text : ");
		for (int i = 0; i < cipherText_128.length; i++) System.out.printf("%02x ", cipherText_128[i]);
		System.out.println("\n");
		
		
		// Decryption Test
		System.out.println("do Decryption");
		
		LeaEngine LeaDecrypter = new LeaEngine();
		LeaDecrypter.init(Mode.DECRYPT, mk_128);
		
		byte[] decryptedText_128 = new byte[16];
		LeaDecrypter.processBlock(cipherText_128, 0, decryptedText_128, 0);
		
		System.out.print("Decrypted Text : ");
		for (int i = 0; i < decryptedText_128.length; i++) System.out.printf("%02x ", decryptedText_128[i]);
		System.out.println("\n");
		
		
		// BlockCipherMode_ECB Test
		System.out.println("do BlockCipher Test");
		byte[] plainText_128_blocks = {
				(byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, (byte)0x14, (byte)0x15, (byte)0x16, (byte)0x17,
				(byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f,
				
				(byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, (byte)0x14, (byte)0x15, (byte)0x16, (byte)0x17,
				(byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f,
				
				(byte)0x10, (byte)0x11, (byte)0x12, (byte)0x13, (byte)0x14, (byte)0x15, (byte)0x16, (byte)0x17,
				(byte)0x18, (byte)0x19, (byte)0x1a, (byte)0x1b, (byte)0x1c, (byte)0x1d, (byte)0x1e, (byte)0x1f
		};
		
		System.out.print("Plain Text : ");
		for (int i = 0; i < plainText_128_blocks.length; i++) {
			if (i % 16 == 0) System.out.println();
			System.out.printf("%02x ", plainText_128_blocks[i]);
		}
		System.out.println("\n");
		
		
		// Block Encryption Test
		BlockCipherMode blockCipherModeEncrypter = new LEA.ECB();
		blockCipherModeEncrypter.init(Mode.ENCRYPT, mk_128);
//		cipher.setPadding(new PKCS5Padding(16));
		byte[] cipherText_128_blocks1 = blockCipherModeEncrypter.update(plainText_128_blocks);
		byte[] cipherText_128_blocks2 = blockCipherModeEncrypter.doFinal();
		
		System.out.print("Cipher Text :");
		for (int i = 0; i < cipherText_128_blocks1.length; i++) {
			if (i % 16 == 0) System.out.println();
			System.out.printf("%02x ", cipherText_128_blocks1[i]);
		}
		System.out.println("\n");
		
		
		// Block Decryption Test
		BlockCipherMode blockCipherModeDecrypter = new LEA.ECB();
		blockCipherModeDecrypter.init(Mode.DECRYPT, mk_128);
//		cipher.setPadding(new PKCS5Padding(16));
		byte[] decryptedText_128_blocks1 = blockCipherModeDecrypter.update(cipherText_128_blocks1);
		byte[] decryptedText_128_blocks2 = blockCipherModeDecrypter.doFinal();
		
		System.out.print("Decrypted Text :");
		for (int i = 0; i < decryptedText_128_blocks1.length; i++) {
			if (i % 16 == 0) System.out.println();
			System.out.printf("%02x ", decryptedText_128_blocks1[i]);
		}
		System.out.println("\n");
		
		System.out.println(">> LEA-128 test done\n\n");
		
		
		
		
		
		/**
		 * LEA-192 Test
		 */
		
		/***************************************************
		
		byte[] plainText_192 = {
				(byte)0x20, (byte)0x21, (byte)0x22, (byte)0x23, (byte)0x24, (byte)0x25, (byte)0x26, (byte)0x27,
				(byte)0x28, (byte)0x29, (byte)0x2a, (byte)0x2b, (byte)0x2c, (byte)0x2d, (byte)0x2e, (byte)0x2f
		};
		
		byte[] cipherText_192 = new byte[16];
		System.out.println(">> LEA-192 test");
		byte[] mk_192 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0,
				(byte)0xf0, (byte)0xe1, (byte)0xd2, (byte)0xc3, (byte)0xb4, (byte)0xa5, (byte)0x96, (byte)0x87
		};
		
		// Encryption Test
		System.out.println("do Enctyprion");
		
		LeaEncrypter = new LeaEngine();
		LeaEncrypter.init(Mode.ENCRYPT, mk_192);
		
		cipherText_128 = new byte[16];
		LeaEncrypter.processBlock(plainText_192, 0, cipherText_192, 0);
		
		System.out.print("Cipher Text : ");
		for (int i = 0; i < cipherText_192.length; i++) System.out.printf("%02x ", cipherText_192[i]);
		System.out.println("\n");

		// Decryption Test
		System.out.println("do Decryption");
		
		LeaDecrypter = new LeaEngine();
		LeaDecrypter.init(Mode.DECRYPT, mk_192);
		
		byte[] decryptedText_192 = new byte[16];
		LeaDecrypter.processBlock(cipherText_192, 0, decryptedText_192, 0);
		
		System.out.print("Decrypted Text : ");
		for (int i = 0; i < decryptedText_192.length; i++) System.out.printf("%02x ", decryptedText_192[i]);
		System.out.println();
				
		
		System.out.println(">> LEA-192 test done\n\n");
		
		***************************************************/
		
		/**
		 * LEA-256 Test
		 */
		
		/***************************************************
		
		byte[] plainText_256 = {
				(byte)0x30, (byte)0x31, (byte)0x32, (byte)0x33, (byte)0x34, (byte)0x35, (byte)0x36, (byte)0x37,
				(byte)0x38, (byte)0x39, (byte)0x3a, (byte)0x3b, (byte)0x3c, (byte)0x3d, (byte)0x3e, (byte)0x3f
		};
		
		byte[] cipherText_256 = new byte[16];
		System.out.println(">> LEA-256 test");
		byte[] mk_256 = {
				(byte)0x0f, (byte)0x1e, (byte)0x2d, (byte)0x3c, (byte)0x4b, (byte)0x5a, (byte)0x69, (byte)0x78,
				(byte)0x87, (byte)0x96, (byte)0xa5, (byte)0xb4, (byte)0xc3, (byte)0xd2, (byte)0xe1, (byte)0xf0,
				(byte)0xf0, (byte)0xe1, (byte)0xd2, (byte)0xc3, (byte)0xb4, (byte)0xa5, (byte)0x96, (byte)0x87,
				(byte)0x78, (byte)0x69, (byte)0x5a, (byte)0x4b, (byte)0x3c, (byte)0x2d, (byte)0x1e, (byte)0x0f
		};
		
		// Encryption Test
		System.out.println("do Enctyprion");
		
		LeaEncrypter = new LeaEngine();
		LeaEncrypter.init(Mode.ENCRYPT, mk_256);
		
		cipherText_128 = new byte[16];
		LeaEncrypter.processBlock(plainText_256, 0, cipherText_256, 0);
		
		System.out.print("Cipher Text : ");
		for (int i = 0; i < cipherText_256.length; i++) System.out.printf("%02x ", cipherText_256[i]);
		System.out.println("\n");

		// Decryption Test
		System.out.println("do Decryption");
		
		LeaDecrypter = new LeaEngine();
		LeaDecrypter.init(Mode.DECRYPT, mk_256);
		
		byte[] decryptedText_256 = new byte[16];
		LeaDecrypter.processBlock(cipherText_256, 0, decryptedText_256, 0);
		
		System.out.print("Decrypted Text : ");
		for (int i = 0; i < decryptedText_256.length; i++) System.out.printf("%02x ", decryptedText_256[i]);
		System.out.println();
		
		
		System.out.println(">> LEA-256 test done\n\n");
		
		***************************************************/
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
