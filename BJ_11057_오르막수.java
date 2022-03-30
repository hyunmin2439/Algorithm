package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 풀이
 * 
 * S N  1   2   3
 *  0   1  10  55
 *  1   1   9  45
 *  2   1   8  36
 *  3   1   7  28
 *  4   1   6  21   ....
 *  5   1   5  15
 *  6   1   4  10
 *  7   1   3   6
 *  8   1   2   3
 *  9   1   1   1
 * 합  10  55 220
 * 
 * S: 시작 숫자 / N: 숫자 길이 / dp[N][S]
 * 
 * dp[N + 1][0] = dp[N][합]
 * 
 * dp[N][i] = dp[N][i - 1] - dp[N-1][i - 1] (1 <= i <= 9)
 * 
 * ex) dp[3][4](21) = dp[3][3](28) - dp[2][3](7)
 * 
 * Memory:14316KB / Time:128ms
 */

public class BJ_11057_오르막수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		final int MOD = 10_007;

		int[][] dp = new int[2][11]; // 공간복잡도 낮춤
		
		// 기본 초기화
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
			dp[1][10] += dp[1][i];
		}

		int from, to;
		for (int i = 2; i <= N; i++) {
			to = i % 2;
			from = (i - 1) % 2;
			
			dp[to][0] = dp[from][10];
			dp[to][10] += dp[to][0];
			dp[from][10] = 0; // 0으로 초기화 하지 않으면 계속 더해짐

			for (int j = 1; j < 10; j++) {
				// 음수값 방지를 위해 MOD를 더하고 MOD를 나눔
				dp[to][j] = (MOD + dp[to][j - 1] - dp[from][j - 1]) % MOD;
				dp[to][10] = (MOD + dp[to][10] + dp[to][j]) % MOD;
			}
		}

		System.out.print(dp[N % 2][10]);
		
		in.close();
	}

}
