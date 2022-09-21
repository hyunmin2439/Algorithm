package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:111,888KB / Time:776ms
public class BJ_9465_스티커 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n, N, sticker[][], dp[][];
		
		int T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			n = Integer.parseInt(in.readLine());
			
			sticker = new int[2][n + 1];
			dp = new int[2][n + 1];
			
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 1; j <= n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];			
			
			for(int i = 2; i <= n; i++) {
				dp[0][i] = sticker[0][i] + Math.max(dp[1][i - 1], dp[1][i - 2]);
				dp[1][i] = sticker[1][i] + Math.max(dp[0][i - 1], dp[0][i - 2]);
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}	
		
		in.close();
		
	}
	
	
}
