package symm;
import kr.re.nsr.crypto.BlockCipher;
import kr.re.nsr.crypto.engine.LeaEngine;
import kr.re.nsr.crypto.mode.ECBMode;

public class LEA {
	
	private LEA() {
		throw new AssertionError("Can't create an instance of class LEA");
	}
	
	public static final BlockCipher getEngine() {
		return new LeaEngine();
	}
	
	public static final class ECB extends ECBMode {
		public ECB() {
			super(getEngine());
		}
	}
}
