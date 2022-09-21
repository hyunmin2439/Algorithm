import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[y][x]에는 그 좌표에서 최대 갈 수 있는 이동거리를 기록
 * 
 * 만약 dp[y][x+1]에서 사방 탐색 시 d[y][x]는 0이 아니기 때문에
 * 
 * dp[y][x+1] = 1 + dp[y][x] 값을 기록하면 되는 것.
 * 
 * 만약, dp[y+1][x]가 0이라고 한다면 dfs 탐색으로 이동하는 좌표에
 * 
 * 1로 이동거리를 초기화, 이후 더이상 나아갈 방향이 없다면 기록된 
 * 
 * dp[?][?] 이동거리 값을 return, dfs 탐색을 끝내고 돌아오면서 
 * 
 * 1 + dp[?][?]를 기록하면 된다.
 * 
 * Memory:37,404KB / Time:492ms
 */
public class BJ_1937_욕심쟁이판다 {

	private static int N;
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		int[][] forest = input();
		
		int[][] dp = new int[N][N];
		
		int answer = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(dp[y][x] == 0) 
					answer = Math.max(answer, dfs(forest, dp, y, x, 1));
			}
		}
		
		System.out.println(answer);
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		
		int[][] forest = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				forest[y][x] = Integer.parseInt(st.nextToken());				
			}
		}
		
		in.close();
		
		return forest;
	}
	
	private static int dfs(int[][] forest, int[][] dp, int y, int x, int move) {
		dp[y][x] = 1;
		
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if( isOutForest(ny, nx) || forest[y][x] >= forest[ny][nx] ) continue;
			
			if( dp[ny][nx] > 0 )
				dp[y][x] = Math.max(dp[y][x], 1 + dp[ny][nx]);
			else
				dp[y][x] = Math.max(dp[y][x], 1 + dfs(forest, dp, ny, nx, move + 1));
		}
		
		return dp[y][x];
	}
	
	private static boolean isOutForest(int y, int x) {
		return !(0 <= y && y < N && 0 <= x && x < N);
	}
}
