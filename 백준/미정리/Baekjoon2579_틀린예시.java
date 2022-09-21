package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 문제점
 * 
 * 굉장히 복잡하게 생각하고
 * 
 * 또한 Dynamic Programming 기법으로 풀어야 함에도 불구하고
 * 
 * 습관적인 Greedy한 생각으로 접근하여 문제를 풀었다.
 */

public class Baekjoon2579_틀린예시 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); // N : 총 계단수
		
		int[] dp = new int[N + 1];
		
		dp[1] = Integer.parseInt(in.readLine()); // 첫번째 계단
		dp[2] = Integer.parseInt(in.readLine()) + dp[1]; // 첫번째 계단 + 두번째 계단
		
		boolean check = true; // 두칸 연속으로 올라옴, 이후 세번째 계단 밟을 수 없음
		
		for (int i = 3; i <= N; i++) {
			int score = Integer.parseInt(in.readLine()); // 현재 계단 점수
			
			// 두칸 연속으로 올라왔을 때
			if( check ) {
				dp[i] = score + dp[i - 2];
				check = false;
			}
			// 두칸을 한번에 올라왔을 때
			else { // 누적 점수 비교
				
				// 점수가 같을 때에는 두칸을 한꺼번에 
				// 올라오면 다음 것도 밟을 수 있으니 더 이득이다.
				if(dp[i - 1] <= dp[i - 2]) {
					dp[i] = score + dp[i - 2];
				}
				else {
					dp[i] = score + dp[i - 1];
					check = true;
				}
			}
		}
		
		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
		
		in.close();
	}

}
