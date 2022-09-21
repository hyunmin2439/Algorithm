package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 첫 풀이시 이전 값을 이용해서 풀려고 시도
// +1로 i를 만들수 있는 최적 값
// +1, *2로 i를 만들 수 있는 최적 값
// +1, *2, *3으로 i를 만들 수 있는 최적 값

// 차례 차례 시도
// 1로 만들수 있는 값 미리 다 저장
// dp[2|3][i / 2|3] + i % 2|3 + 1으로 풀려고 했으나 값이 안 맞는 케이스 생김

// 1차원 배열 dp로 간단하게 풀 수 있는 문제
public class BJ_1463_1로만들기_1_DP {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// dp[i] i -> 1 만드는데 필요한 연산 횟수
		int[] dp = new int[N + 1];
		
		// 1에서 차례 차례 N을 찾아감 : 상향식(bottom-up)
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}
		
		System.out.println(dp[N]);
		
		br.close();
	}

}
