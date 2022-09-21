package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Memory:14092KB / Time:120ms
public class BJ_1855_암호 {

	private static int N, rowLen;
	private static char[][] passwordPattern;
	
	public static void main(String[] args) throws Exception {
		input();
		
		String res = solve();
		
		System.out.print(res);
	}
	
	private static String solve() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < rowLen; j++) {
				sb.append(passwordPattern[j][i]);
			}
		}
		
		return sb.toString();
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		String password = in.readLine();
		
		rowLen = password.length() / N;
		
		passwordPattern = new char[rowLen][N];
		
		int start = 0, sw = 1, idx = 0;
		for(int i = 0; i < rowLen; i++) {
			start = i % 2 == 0 ? 0 : N - 1;
			sw = i % 2 == 0 ? 1 : -1;
			
			for(int j = start; 0 <= j && j < N; j += sw) {
				passwordPattern[i][j] = password.charAt(idx);
				idx++;
			}
			
		}
		
		in.close();
	}

}
