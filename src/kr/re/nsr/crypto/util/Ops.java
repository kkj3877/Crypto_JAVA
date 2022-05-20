package kr.re.nsr.crypto.util;

public class Ops {

	private Ops() {
		throw new AssertionError("Can't create an instance of class Ops");
	}
	
	/**
	 * byte array 를 int array 로 변환시켜주는 함수
	 */
	public static final void pack(byte[] in, int[] out) {
		if (in == null || out == null) {
			throw new NullPointerException();
		}
		
		if (in.length != out.length * 4) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int outIdx = 0;
		for (int inIdx = 0; inIdx < in.length; ++inIdx, ++outIdx) {
			out[outIdx] = (in[inIdx] & 0xff);
			out[outIdx] |= (in[++inIdx] & 0xff) << 8;
			out[outIdx] |= (in[++inIdx] & 0xff) << 16;
			out[outIdx] |= (in[++inIdx] & 0xff) << 24;
		}
	}
	
	public static final void pack(byte[] in, int inOff, int[] out, int outOff, int inlen) {
		if (in == null || out == null) {
			throw new NullPointerException();
		}
		
		if ((inlen & 3) != 0) {
			throw new IllegalArgumentException("length should be multiple of 4");
		}
		
		if (in.length < inOff + inlen || out.length < outOff + inlen / 4) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int outIdx = outOff;
		int endInIdx = inOff + inlen;
		for (int inIdx = inOff; inIdx < endInIdx; ++inIdx, ++outIdx) {
			out[outIdx] = (in[inIdx] & 0xff);
			out[outIdx] |= (in[++inIdx] & 0xff) << 8;
			out[outIdx] |= (in[++inIdx] & 0xff) << 16;
			out[outIdx] |= (in[++inIdx] & 0xff) << 24;
		}
	}
	
	/**
	 * int array 를 byte array 로 변환시켜주는 함수
	 */
	public static final void unpack(int[] in, byte[] out) {
		if (in == null || out == null) {
			throw new NullPointerException();
		}
		
		if (in.length * 4 != out.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int outIdx = 0;
		for (int inIdx = 0; inIdx < in.length; ++inIdx, ++outIdx) {
			out[outIdx] = (byte) in[inIdx];
			out[++outIdx] = (byte) (in[inIdx] >> 8);
			out[++outIdx] = (byte) (in[inIdx] >> 16);
			out[++outIdx] = (byte) (in[inIdx] >> 24);
		}
	}
	
	public static final void unpack(int[] in, int inOff, byte[] out, int outOff, int inlen) {
		if (in == null || out == null) {
			throw new NullPointerException();
		}
		
		if (in.length < inOff + inlen || out.length < outOff + inlen * 4) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int outIdx = outOff;
		int endInIdx = inOff + inlen;
		for (int inIdx = inOff; inIdx < endInIdx; ++inIdx, ++outIdx) {
			out[outIdx] = (byte) in[inIdx];
			out[++outIdx] = (byte) (in[inIdx] >> 8);
			out[++outIdx] = (byte) (in[inIdx] >> 16);
			out[++outIdx] = (byte) (in[inIdx] >> 24);
		}
	}
}






















