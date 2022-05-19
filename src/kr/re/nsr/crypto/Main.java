package kr.re.nsr.crypto;

public class Main {
	
	public static void main(String[] args) {
		
		// 초기 조건
		int p = 181, q = 199;
		int N = p * q; // 36019
		int x = 12397;
		int g = 127;
		
		
		/**
		 * 지수 x 에 대한 비트표현식 추출
		 */
		StringBuffer sb = new StringBuffer();
		int tempX = x;
		while (tempX != 0) {
			sb = (tempX % 2 == 0) ? sb.insert(0, '0') : sb.insert(0, '1');
			tempX /= 2;
		}
		
		// print result
		System.out.println(x+" 's bit expression >> " + sb.toString());
		
		
		/**
		 * 지수연산 수행 : Left-to-right binary exponentiation
		 */
		System.out.println("\n>> Left-to-right binary exponentiation start");
		int A = 1;
		String exp = sb.toString();
		int t = exp.length();
		
		for (int i = 0; i < t; i++) {
			A *= A;
			A %= N;
			if (exp.charAt(i) == '1') {
				A *= g;
				A %= N;
			}
			System.out.println("now : " + A + "\t exponentiation >> " + exp.substring(0, i + 1));
		}
		
		// print result
		System.out.print(">> result of Left-to-right binary exponentiation\n\t\t\t\t-> ");
		System.out.println(A);
		
		
		/**
		 * 지수연산 수행 : Right-to-Left binary exponentiation
		 */
		System.out.println("\n>> Right-to-left binary exponentiation start");
		
		// exponentiation
		A = 1;
		int S = g;
		int e = x;
		while (e != 0) {
			if (e % 2 != 0) {
				A *= S;
				A %= N;
			}
			e /= 2;
			S *= S;
			S %= N;
		}
		
		// print result
		System.out.print(">> result of Left-to-right binary exponentiation\n\t\t\t\t-> ");
		System.out.println(A);
		
		
		/**
		 * 지수연산 수행 : Left-to-right k-ary exponentiation
		 */
		System.out.println("\n>> Left-to-right k-ary exponentiation start");
		
		// Precomputation
		System.out.println(">> Precomputation");
		int k = 3;
		int power = (int)Math.pow(2, k);
		int[] preComp = new int[power];
		preComp[0] = 1;
		for (int i = 1; i < power; i++) preComp[i] = (preComp[i - 1] * g) % N;
		
		// padding
		String paddingedString = paddingForKaryExponentiation(exp, k);
		System.out.println(">>  OriginalString : " + exp);
		System.out.println(">> paddingedString : " + paddingedString);
		
		// exponentiation
		A = 1;
		int quotient = paddingedString.length() / k;
		for (int i = 0; i < quotient; i++) {
			for (int j = 0; j < k; j++) {
				A *= A;
				A %= N;
			}
			
			String sub = paddingedString.substring(i * k, (i + 1) * k);
			int idx = bitToInteger(sub, k);
			A *= preComp[idx];
			A %= N;
			
			System.out.println("now : " + A + "\t exponentiation >> " + paddingedString.substring(0, (i + 1) * k));
		}
		
		// print result
		System.out.print(">> result of Left-to-right k-ary exponentiation\n\t\t\t\t-> ");
		System.out.println(A);
		
		
		/**
		 * 단순 곱 연산 수행
		 */
		System.out.println("\n>> simple multiplication operation start");
		int num = 1;
		for (int i = 0; i < x; i++) {
//			if (i % 1000 == 0) {
//				System.out.println("i == " + i + ", num = " + num);
//			}
			num *= g;
			num %= N;
		}
		
		// print result
		System.out.print(">> result of simple multiplication operation\n\t\t\t\t-> ");
		System.out.println(num);
		
	}
	
	public static String paddingForKaryExponentiation(String bitArr, int k) {
		StringBuffer paddingedString = new StringBuffer(bitArr);
		
		int needs = k - (bitArr.length() % k);
		for (int i = 0; i < needs; i++) paddingedString.insert(0, '0');
		
		return paddingedString.toString();
	}
	
	public static int bitToInteger(String sub, int k) {
		return Integer.parseInt(sub, 2);
	}
	
}


