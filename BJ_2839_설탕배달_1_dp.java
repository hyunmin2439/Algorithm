package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BJ_2839_설탕배달_1_dp {

	static int N, min;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		N = Integer.parseInt(br.readLine()); // (3 ≤ N ≤ 5000)

		// DP는 보통 초기값이 지정이 된다.
		// 그래서 초기값이 들어있는 배열을 만들어놓고 시작한다.
		// DP는 부분이 모여서 전체가 되는 문제에 적용가능하다.
		
		// 초기 조건 체크
		if (N <= 5) {
			if (N == 3 || N == 5)
				bw.write(String.valueOf(1));
			else
				bw.write(String.valueOf(-1));

			br.close();
			bw.close();
			return;
		}
		
		if (N % 5 == 0) {
			bw.write(String.valueOf(N / 5));
			
			br.close();
			bw.close();
			return;
		}
		
		dp = new int[N + 1];
		Arrays.fill(dp, 0, 5, 5000);
		
		dp[3] = 1; // 3kg은 1개 봉투를 쓰면 된다.
		dp[5] = 1; // 5kg은 1개 봉투를 쓰면 된다.
		
		for (int i = 6; i <= N; i++) {
			dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
		}
		
		if( dp[N] > 5000 ) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);			
		}
		
		br.close();
		bw.close();
	}
}
