package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {

	private static int N, M;
	
	public static void main(String[] args) throws Exception {
		solve();
	}
	
	private static void solve() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][N + 1];
				
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 1; x <= N; x++) {
				dp[y][x] = dp[y - 1][x] + dp[y][x - 1] + Integer.parseInt(st.nextToken());
			}
		}
		
		int sy, sx, ey, ex;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());			
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[ey][ex] - dp[ey][sx - 1] - dp[sy - 1][ex] + dp[sy][ey]);
		}
		
		in.close();
	}

}
