package notSolved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_15989_123더하기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		int[] arr = new int[T];
		
		int max = 0;
		for(int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(in.readLine());			
			max = max > arr[i] ? max : arr[i];
		}
		
		int[][] dp = new int[T + 1][max + 1];
		for(int j = 1; j <= max; j++) {
			dp[1][j] = j;
		}
		
		for(int i = 2; i <= 3; i++) {
			for(int j = i; j <= max; j++) {
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				dp[i][j] = Math.max(dp[i][j], dp[i][j - i] + 1);
			}
		}
		
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= max; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < T; i++) {
			System.out.println( dp[2][ arr[i] ]);
		}
	}
	

}
