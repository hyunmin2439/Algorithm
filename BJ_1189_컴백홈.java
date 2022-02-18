package no_upload;

// Memory:15204KB / Time:152ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1189_컴백홈 {
	
	private static int R, C, K, res;
	private static char[][] map;

	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		boolean[][] visited = new boolean[R][C];
		
		visited[R - 1][0] = true;
		
		dfs(visited, R - 1, 0, 1);
		
		System.out.println(res);
	}
	
	private static void dfs(boolean[][] visited, int y, int x, int moveCnt) {
		if(moveCnt == K) {
			if(y == 0 && x == C - 1) {
				res++;
				return;
			}
			else return;
		}
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if( !(0 <= ny && ny < R && 0 <= nx && nx < C) ) continue;
			
			if( visited[ny][nx] || map[ny][nx] == 'T') continue;
			
			visited[ny][nx] = true;
			
			dfs(visited, ny, nx, moveCnt + 1);
			
			visited[ny][nx] = false;
		}
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		char[] line;
		for(int r = 0; r < R; r++) {
			line = in.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				map[r][c] = line[c];
			}
		}
		
		in.close();
	}

}
