package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1568_새 {

	public static void main(String[] args) throws Exception {
		int N = input();
		
		int sec = solve(N);
		
		System.out.println(sec);
	}
	
	private static int solve(int N) {
		int K = 1, sec = 0;
		
		while(N > 0) {
			if(N - K < 0) K = 1;
			N -= K;
			K++;
			sec++;
		}
		
		return sec;
	}
	
	private static int input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		
		in.close();
		
		return N;
	}
}
