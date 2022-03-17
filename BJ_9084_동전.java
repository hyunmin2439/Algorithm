package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * Memory:14164KB / Time:124ms
 */

public class BJ_9084_동전 {
	
	public static void main(String[] args) throws Exception {
		solve();
	}
	
	private static void solve() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T, N, M, coin[];
		
		T = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < T; i++) {
			N = Integer.parseInt(in.readLine());
			coin = new int[N];
			
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				coin[j] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(in.readLine());
			
			getNumOfCase(coin, N, M);
		}
	}
	
	private static void getNumOfCase(int[] coin, int N, int M) {
		int[] dp = new int[M + 1];
		
		dp[0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = coin[i]; j <= M; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}
		
		System.out.println(dp[M]);
	}

}
