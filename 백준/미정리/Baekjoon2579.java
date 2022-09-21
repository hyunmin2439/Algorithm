package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Bottom-UP DP
 * 
 * 생각보다 그렇게 쉽지만은 않은 문제인 것 같다.
 * 
 * 아직 DP에 익숙하지 않아서 그런지 점화식 세우는 시간이 오래 걸렸다.
 */

public class Baekjoon2579 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); // N : 총 계단수
		
		int[] score = new int[N + 1];
		int[] dp = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			score[i] = Integer.parseInt(in.readLine());
		}
		
		// 1, 2가 입력으로 들어올 수 도 있기 때문
		if( N < 3 ) {
			System.out.println(N == 1 ? score[1] : score[1] + score[2]);
			return;
		} else {
			dp[1] = score[1]; // 첫번째 계단
			dp[2] = score[1] + score[2]; // 첫번째 계단 + 두번째 계단
		}
		
		for (int i = 3; i <= N; i++) {
			// 현재 계단을 밟으려면
			// 1. 전 전 계단을 밟고 오거나
			// 2. 전 계단을 밟고 오거나 -> 전 전 계단을 밟으면 안됨 -> 전 전 전 계단을 밟고 와야함
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + score[i - 1]) + score[i];
		}
		
		System.out.println(dp[N]);
		
		in.close();
	}

}
