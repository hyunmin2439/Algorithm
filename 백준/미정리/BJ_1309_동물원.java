package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Dynamic Programming
 * 
 * N:1 => 3
 * N:2 => 7
 * N:3 => 17
 * N:4 => 41
 * N:5 => 99
 * ...
 * 
 * dp[i] = d[i - 1] * 2 + dp[i - 2]
 * 
 * 규칙성을 찾을 수 있다.
 * 
 * Memory:14488KB / Time:128ms
 */

public class BJ_1309_동물원 {

	public static void main(String[] args) throws Exception {
		int N = input();

		int res = dp(N);
		
		System.out.print(res);
	}

	private static int input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		in.close();
		
		return N;
	}
	
	private static int dp(int N) {
		final int MOD = 9901;
		int[] dp = new int[N + 1];
		
		dp[0] = 1; // dummy
		dp[1] = 3;
		
		for(int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % MOD;
		}
		
		return dp[N];
	}
}
