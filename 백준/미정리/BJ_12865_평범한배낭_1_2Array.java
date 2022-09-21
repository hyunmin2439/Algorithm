package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭_1_2Array {

	static int N, K;
	static int[][] prod;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		prod = new int[N + 1][2];
		dp = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			prod[i][0] = w;
			prod[i][1] = v;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				if(prod[i][0] > w) dp[i][w] = dp[i - 1][w];
				else {					
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - prod[i][0]] + prod[i][1] );
				}
			}
		}
		
		System.out.println(dp[N][K]);
		in.close();
	}

}
