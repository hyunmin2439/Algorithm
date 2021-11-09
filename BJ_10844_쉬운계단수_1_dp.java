import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Dynamic Programming
 * 
 * 마지막 자리(1의 자리)가 0이거나 9이면 뒤에 올 수 있는 수는 각각 1, 8로 고정
 * 
 * 나머지 숫자는 각 수의 -1, +1이 올 수 있다.
 * 
 * n/num 0   1   2   3   4   5   6   7   8   9   sum
 * 1	 0   1   1   1   1   1   1   1   1   1    9
 * 2     1   1   2   2   2   2   2   2   2   1   17
 * 3     1   3   3   4   4   4   4   4   3   2   32
 * ...
 * 
 * 이를 토대로 아래 점화식 세우는 것이 가능.
 * 
 * dp[n][0] = dp[n - 1][1] (n >= 2)
 * 
 * dp[n][i] = dp[n - 1][i - 1] + dp[n - 1][i + 1]; (1 <= i <= 8)
 * 
 * dp[n][9] = dp[n - 1][8]
 */

public class BJ_10844_쉬운계단수_1_dp {

	static final int MOD = 1_000_000_000;
	
	static int N;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		dp = new int[N + 1][10];
		
		// 0부터 시작하는 계단수는 없다.
		// 때문에 1일때는 1 ~ 9
		for(int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			// 0 일때
			dp[i][0] = dp[i - 1][1];
			
			// 1 ~ 8
			for(int j = 1; j <= 8; j++) {
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
			}
			
			// 9 일때
			dp[i][9] = dp[i - 1][8];
		}
		
		int sum = 0;
		
		for(int j = 0; j <= 9; j++) {
			// System.out.print(dp[N][j] + " "); // debugging : dp 확인용
			sum += dp[N][j];
			sum %= MOD;
		}
		System.out.print(sum);
		
		in.close();
	}

}