import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * LIS(Longest Increasing Subsequence)
 * 
 * dp[i] : i번째 수를 마지막으로 하는 LIS 길이
 * 
 * Memory:14468KB / Time:148ms
 */

public class BJ_11053_가장긴증가하는부분수열_1 {

	static int N;
	static int[] arr, dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		findLIS();
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		
		in.close();
	}

	private static void findLIS() {
		dp[0] = 1;
		
		for(int i = 1; i < N; i++) {
			
			dp[i] = 1; // 1초기화
			
			for(int j = i - 1; j >= 0; j--) {
				if(arr[i] > arr[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
	}

}
