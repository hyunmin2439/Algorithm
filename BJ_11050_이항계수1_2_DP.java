package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11050_이항계수1_2_DP {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int dp[][] = new int[2][K + 1];
		
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= Math.min(i, K); j++) {
				int curr = i % 2, prev = (i + 1) % 2;
				
				if(j == 0 || i == j) dp[curr][j] = 1;
				else dp[curr][j] = dp[prev][j - 1] + dp[prev][j];
			}
		}
		
		System.out.println(dp[N % 2][K]);
		in.close();
	}

}
