package kr.re.nsr.crypto.engine;

import kr.re.nsr.crypto.BlockCipher;

public class LeaEngine extends BlockCipher {

	private static final int BLOCKSIZE = 16;
	private static final int delta[] = { 0xc3efe9db, 0x44626b02, 0x79e27c8a, 0x78df30ec, 0x715ea49e, 0xc785da0a, 0xe04ef22a, 0xe5c40957 };
	
	private Mode mode;
	private int rounds;
	protected int[][] roundKeys; // 왜 라운드키는 프로텍트인가..!?
	private int[] block;
	
	public LeaEngine() {
		// 블록 사이즈는 BLOCKSIZE(16 Byte)이므로, 4바이트를 4칸 만든다.
		block = new int[BLOCKSIZE / 4];
	}

	@Override
	public void init(Mode mode, byte[] mk) {
		this.mode = mode;
		generateRoundKyes(mk);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOutputSize(int len) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUpdateOutputSize(int len) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void generateRoundKyes(byte[] mk) {
		if (mk == null || ((mk.length != 16) && (mk.length != 24) && (mk.length != 32))) {
			throw new IllegalArgumentException("Illegal key");
		}
		
		int[] T = new int[8];
		
		this.rounds = (mk.length >> 1) + 16;
		this.roundKeys = new int[this.rounds][6];
		
		pack(mk, 0, T, 0, 16);
		
		if (mk.length == 16) {
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	// utilities
	private static final int ROL(int state, int num) {
		return (int) ((state << num) | (state >>> (32 - num)));
	}
	
	private static final int ROR(int state, int num) {
		return (int) ((state >>> num) | (state << (32 - num)));
	}
	
}
