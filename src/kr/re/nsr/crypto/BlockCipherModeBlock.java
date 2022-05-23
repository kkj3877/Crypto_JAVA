package kr.re.nsr.crypto;

import java.util.Arrays;

import kr.re.nsr.crypto.BlockCipher.Mode;
import kr.re.nsr.crypto.padding.Padding;

public abstract class BlockCipherModeBlock extends BlockCipherModeImpl {
	
	protected Padding padding;
	
	public BlockCipherModeBlock(BlockCipher cipher) {
		super(cipher);
	}

	@Override
	public int getOutputSize(int len) {
		int size = ((len + bufferOffset) & blockmask) + blocksize;
		if (mode == Mode.ENCRYPT) {
			return padding != null ? size : len;
		}
		
		return len;
	}

	@Override
	public int getUpdateOutputSize(int len) {
		if (mode == Mode.DECRYPT && padding != null) {
			return (len + bufferOffset - blocksize) & blockmask;
		}
		
		return (len + bufferOffset) & blockmask;
	}

	@Override
	public void init(Mode mode, byte[] mk) {
		throw new IllegalStateException("This init method is not applicatble to " + getAlgorithmName());
	}

	@Override
	public void init(Mode mode, byte[] mk, byte[] iv) {
		throw new IllegalStateException("This init method is not applicatble to " + getAlgorithmName());
	}

	@Override
	public void reset() {
		bufferOffset = 0;
		Arrays.fill(buffer, (byte) 0);
	}

	@Override
	public byte[] update(byte[] msg) {
		
		
		
		
		if (msg == null) {
			return null;
		}
		
		int len = msg.length;
		int gap = buffer.length - bufferOffset;
		int inOff = 0;
		int outOff = 0;
		byte[] out = new byte[getUpdateOutputSize(len)];
		
		if (len >= gap) {
			System.arraycopy(msg, inOff, buffer, bufferOffset, gap);
			outOff += processBlock(buffer, 0, out, outOff);
			
			bufferOffset = 0;
			len -= gap;
			inOff += gap;
			
			while (len >= buffer.length) {
				outOff += processBlock(msg, inOff, out, outOff);
				len -= blocksize;
				inOff += blocksize;
			}
		}
		
		if (len > 0) {
			System.arraycopy(msg, inOff, buffer, bufferOffset, len);
			bufferOffset += len;
			len = 0;
		}
		
		return out;
	}
	
	@Override
	public byte[] doFinal() {
		if (bufferOffset == 0) {
			return null;
			
		} else if (bufferOffset != blocksize) {
			throw new IllegalStateException("Bad padding");
		}
		
		byte[] out = new byte[blocksize];
		processBlock(buffer, 0, out, 0, blocksize);
		
		return out;
	}
	
}
