package kr.re.nsr.crypto;

/**
 * 블록암호 운용모드 구현을 위한 인터페이
 */
public abstract class BlockCipher {
	
	public enum Mode {
		/** 암호화 모드 */
		ENCRYPT,
		/** 복호화 모드 */
		DECRYPT
	}
	
	/**
	 * 초기화 함수
	 * 
	 * @param mode
	 * 			  {@link BlockCipher.Mode}
	 * @param mk
	 * 			  암호화 
	 */
	public abstract void init(Mode mode, byte[] mk);
	
	/**
	 * 새로운 데이터를 처리하기 위해 init을 수행한 상태로 복
	 */
	public abstract void reset();
	
	/**
	 * 현재 객체가 구현하고 있는 알고리즘 이름을 리턴
	 * 
	 * @return 알고리즘 이름을 "블록암호/운용모드" 형태로 리턴
	 */
	public abstract String getAlgorithmName();
	
	/**
	 * 입력에 대한 출력 길이를 계산
	 * 
	 * @param len
	 * 			  입력 길이
	 * @return len 만큼의 입력을 처리하기 위해 필요한 출력 길이
	 */
	public abstract int getOutputSize(int len);
	
	/**
	 * 부분 업데이트를 위해 필요한 출력의 길이 계산, 주로 블록크기의 배수로 계산됨
	 * 
	 * @param len
	 * 			  입력 길이
	 * @return len 만큼의 입력을 처리하기 위해 필요한 중간 출력 길이
	 */
	public abstract int getUpdateOutputSize(int len);
}
