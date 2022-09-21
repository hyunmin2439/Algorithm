package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:15,420KB / Time:208ms
 */
public class BJ_2003_수들의합2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1], dp = new int[N + 1];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i];
		}
		
		in.close();
		
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < i; j++) {
				if(dp[i] - dp[j] == M)
					ans++;
			}
		}
		
		System.out.print(ans);
	}

}
