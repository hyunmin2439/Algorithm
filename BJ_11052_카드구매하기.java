package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11052_카드구매하기 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		int[] cost = input();
		
		solve(cost);
	}

	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		int[] cost = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return cost;
	}
	
	private static void solve(int[] cost) {
		int[] dp = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++ ) {
				if(j % i == 0) dp[j] = Math.max(dp[j], cost[i] * (j / i));
				dp[j] = Math.max(dp[j], dp[j - i] + cost[i]);				
			}
		}
		
		System.out.print(dp[N]);
	}
}
