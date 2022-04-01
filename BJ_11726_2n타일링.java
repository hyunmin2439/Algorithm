package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11726_2n타일링 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int MOD = 10_007;
		int n = Integer.parseInt(in.readLine());
		
		int[] dp = new int[n + 1];
		
		dp[0] = dp[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
		}
		
		System.out.println(dp[n]);
	}

}
