package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ_1252_이진수덧셈 {
	
	public static void main(String[] args) throws Exception {
		String[] binary = input();
		
		String res = solve(binary);
		
		System.out.print(res);
	}
	
	private static String[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		String[] binary = new String[2];
		
		binary[0] = st.nextToken();
		binary[1] = st.nextToken();
		
		in.close();
		
		return binary;
	}
	
	private static String solve(String[] binary) {
		BigInteger b1 = new BigInteger(binary[0], 2); // 2진수 -> 10진수
		BigInteger b2 = new BigInteger(binary[1], 2);
		
		b1 = b1.add(b2);
		
		return b1.toString(2); // 2진수로 
	}

}
