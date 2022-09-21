package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이항계수
// 파스칼 삼각형 - dp
// 11050과 다르게 공간복잡도를 줄임.

public class BJ_11051_이항계수2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[2][N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= Math.min(i, K); j++) {
				int curr = i % 2, prev = (i + 1) % 2;
				
				if(j == 0 || j == i) dp[curr][j] = 1;
				else dp[curr][j] = (dp[prev][j - 1] + dp[prev][j]) % 10_007;
			}
		}
		
		System.out.println(dp[N % 2][K]);
		
		in.close();
	}

}
