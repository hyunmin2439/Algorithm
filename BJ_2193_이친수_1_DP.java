import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
 * 이친수 - DP
 * 
 * 규칙1. 0으로 시작X
 * 
 * 규칙2. 1이 연속 두번으로 나타나지 않는다.
 * 
 * 							10000
 *  				   1000 10001
 * 			1  10  100 1001 10010	...
 * 				   101 1010 10100
 * 							10101
 *   N		1	2	3	 4	  5		...
 * output   1	1	2	 3	  5		...
 * 
 * 피보나치수 규칙과 같음
 * f(1) = 1
 * f(2) = 1
 * f(N) = f(N - 1) + f(N - 2) (N > 2)
 * 
 * Memory:14176KB / Time:140ms
 */

public class BJ_2193_이친수_1_DP {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		
		long[] dp = new long[N + 1];
		
		if(N < 3) {
			System.out.print(1);
			return;
		}
		
		dp[1] = dp[2] = 1;
		
		for(int i = 3; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		System.out.print(dp[N]);
		
		in.close();
	}

}
