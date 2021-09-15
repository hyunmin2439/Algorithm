package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baekjoon2839_second_dp {

	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		
		// 5미만이면 아래 초기화에서 에러가 남
		if(N < 5) {
			System.out.println( (N == 4) ? -1 : 1 );
			return;
		}
		
		dp[1] = dp[2] = dp[4] = 2000; // 나올 수 없는 큰 값
		dp[3] = dp[5] = 1;
		
		for (int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}
		
		if(dp[N] >= 2000) System.out.println(-1);
		else System.out.println(dp[N]);
		
		br.close();
	}

}
