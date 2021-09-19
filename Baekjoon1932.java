package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1932 {
	
	static int N, max;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[2][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				int curr = i % 2, prev = (i + 1) % 2;
				int num = Integer.parseInt(st.nextToken());
				if(j != 0) dp[curr][j] = Math.max(dp[curr][j], dp[prev][j - 1] + num);
				if(j == 0 || j != i) dp[curr][j] = Math.max(dp[curr][j], dp[prev][j] + num);
			}
		}
		
		int curr = (N - 1) % 2;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[curr][i]);
		}
		
		System.out.println(max);
		br.close();
	}

}
