import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2210_숫자판_점프 {

	private static int N = 5, ans = 0, matrix[][];
	private static boolean[] visited;
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		matrix = new int[N][N];
		visited = new boolean[1_000_000];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				dfs(y, x, matrix[y][x], 1);
			}
		}
		
		System.out.println(ans);
	}
	
	private static void dfs(int y, int x, int num, int idx) {
		if(idx == 6) {
			if( !visited[num] ) {
				visited[num] = true;
				ans++;
			}
			return;
		}
		
		for(int d = 0; d < dy.length; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
			
			dfs(ny, nx, num * 10 + matrix[ny][nx], idx + 1);
		}
	}
}
