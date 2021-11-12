package solved.submit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS로 불가능
// N이 최대 125로 갈 수 있는 경로의 수가 굉장히 많다.
// 아무리 가지치기를 한다고 하더라도 불가능
// 때문에 다익스트라 알고리즘을 사용하여 풀어야 한다.

// 시간초과
public class BJ_4485_녹색옷입은애가젤다지_DFS_불가능 {

	static int N, t, min;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(in.readLine());
			if(N == 0) break;
			
			min = Integer.MAX_VALUE;
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0, map[0][0]);
			
			System.out.println("Problem " + ++t + ": " + min);
		}
		
		
		in.close();
	}
	
	private static void dfs(int y, int x, int rupee) {
		
		// 가지치기
		if(rupee >= min) return;
		
		// 목적지 도착
		if(y == N - 1 && x == N - 1) {
			min = Math.min(min, rupee);
			return;
		}
		
		// 사방 탐색
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			// 좌표 범위 넘어가거나 방문한 곳이면
			if( !(0 <= ny && ny < N && 0 <= nx && nx < N) || visit[ny][nx] ) continue;
			
			visit[ny][nx] = true;
			dfs(ny, nx, rupee + map[ny][nx]);
			
			// 최단거리를 찾는 것이 아닌 비용이기 때문에 
			// 모든 경로를 완탐하기 위해 방문해제
			visit[ny][nx] = false;
		}
	}

}
