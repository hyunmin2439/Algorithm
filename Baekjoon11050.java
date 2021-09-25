package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이항계수
// 파스칼 삼각형 - dp

public class Baekjoon11050 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= Math.min(i, K); j++) {
				if(j == 0 || j == i) dp[i][j] = 1;
				else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		
		System.out.println(dp[N][K]);
		
		in.close();
	}

}
