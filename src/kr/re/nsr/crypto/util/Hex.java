package kr.re.nsr.crypto.util;

public class Hex {
	
	private Hex() {
		throw new AssertionError("Can't create an instance of class Hex");
	}
	
	/**
	 * 16진수의 문자열을 입력하면 문자열에 대응하는 바이트 배열을 반환
	 * 
	 * @param hexString
	 * 				 변환하고자 하는 16진수 문자열
	 * @return 문자열을 바이트 배열로 변환한 결과
	 */
	public static final byte[] decodeHexString(String hexString) {
		if (hexString == null) {
			return null;
		}
		
		byte[] buf = new byte[hexString.length() / 2];
		
		for (int i = 0; i < buf.length; ++i) {
			buf[i] = (byte)(16 * decodeHexChar(hexString.charAt(i * 2)));
			buf[i] += decodeHexChar(hexString.charAt(i * 2 + 1));
		}
		
		return buf;
	}
	
	/**
	 * 한 16진수 문자를 16진수 바이트 값으로 반환
	 * 
	 * @param ch
	 * 			  반환하고자 하는 16진수 문자
	 * @return 16진수 문자 표현을 바이트 값으로 변환한 값
	 */
	private static final byte decodeHexChar(char ch) {
		if (ch >= '0' && ch <= '9') {
			return (byte)(ch - '0');
		}
		
		if (ch >= 'a' && ch <= 'f') {
			return (byte)(ch - 'a');
		}
		
		if (ch >= 'A' && ch <= 'Z') {
			return (byte)(ch - 'A');
		}
		
		return 0;
	}
	
	
}
