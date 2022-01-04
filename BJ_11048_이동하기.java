package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Dynamic Programming
 * 
 * 처음에는 Backtracking + Memoization으로 풀었으나 시간초과
 * 
 * 가로 세로 크기 최대가 1000이기 때문에 경로가 아무리 가지치기를 해도 시간복잡도가 너무 큼.
 * 
 * Memoizaion기법을 살려 DP 방법으로 풀면 됨. 현재 좌표에 이전 좌표의 최대값을 계속 더해나가면 된다.
 * 
 * ex)
 * 
 * 7 → 8	 7 → 15
 * ↓   ↓	 ↓    ↓
 * 4 → 5	11 → 20 
 * 
 * 위의 예제에서는 7 → 8 → 5 윗경로 오는 것이 더 최대 값
 * 
 * 문제에서는 대각선으로도 갈 수 있다고 나오지만 사탕의 개수가 음수가 없으므로
 * 
 * 대각선으로 바로 오는 것보다 가로, 세로를 거쳐서 오는 것이 무조건 크거나 같다.
 * 
 * Memory:75860KB / Time:540ms
 */

public class BJ_11048_이동하기 {

	static int N, M;
	static int[][] maze, dp;
	
	static int[] dy = { -1, 0 };
	static int[] dx = { 0, -1 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		// dp init
		dp[0][0] = maze[0][0];
		
		int ny, nx;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				
				// 2가지 경로
				for(int d = 0; d < 2; d++) {
					ny = y + dy[d];
					nx = x + dx[d];
					
					// 범위체크 : dy, dx가 -1의 값만 가지고 있으므로 0보다 작은지만 체크하면 된다.
					if( !(0 <= ny && 0 <= nx) ) continue;
					
					// 현재 기록된 최대값과 좌측이나 위측의 기록된 최대값 중 큰 쪽을 기록
					dp[y][x] = Math.max(dp[y][x], dp[ny][nx] + maze[y][x]);
				}
				
			}
		}
		
		System.out.print( dp[N - 1][M - 1] );
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		dp = new int[N][M];
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				maze[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
	}

}
