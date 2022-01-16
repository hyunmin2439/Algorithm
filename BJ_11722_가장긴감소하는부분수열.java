package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp[i] : 조건을 만족하는 자신을 마지막으로 하는 부분 수열의 길이
 * 
 * Memory:14552KB / Time:144ms
 */

public class BJ_11722_가장긴감소하는부분수열 {

	static int N;
	static int[] A, dp;

	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.println(res);
	}
	
	private static int solve() {
		int maxLength = 1;
		
		for(int i = 1; i < N; i++) {
			int currNum = A[i];
			
			for(int j = i - 1; j >= 0; j--) {
				if(currNum < A[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			
			maxLength = Math.max(maxLength, dp[i]);
		}
		
		return maxLength;
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		A = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}

		in.close();
	}

}
