import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS
 * 
 * 주어진 조건에 따라 DFS로 탐색하여 경우의 수를 구하는 문제
 * 
 * 조건만 지켜서 잘 구현하면 까다롭지 않은 문제이다.
 * 
 * 우선 visited 방문 체크가 필요가 없다.
 * 
 * 갈 수 없는 곳만 체크하면 된다.
 * 
 * 그 이유는 사방향을 갈 수 있는 것이 아니라 가로, 세로, 대각선으로 나아간다.
 * 
 * Delta값 dy, dx를 보면 알 수 있듯, -1값이 없기 때문에 DFS 탐색을 하면서
 * 
 * 되돌아가서 같은 자리를 맴돌아 StackOverflow가 날 가능성이 없다.
 * 
 * 또한 파이프를 놓는 위치가 다음 파이프 놓는 위치에 영향을 주지 않기 때문에
 * 
 * 방문 체크가 아예 불필요하다. 따라서 단순히 처음에 벽으로 막혀져 있는지,
 * 
 * isBlocked라는 boolean 배열로 체크를 해놓고 DFS를 탐색하면 된다.
 * 
 * Memory:15292KB / Time:292ms
 */

public class BJ_17070_파이프옮기기1_1_DFS {
	
	static int N, numOfCase;
	static boolean[][] isBlocked;
	
	//			 가로, 대각선, 세로
	static int[] dy = { 0, 1, 1 };
	static int[] dx = { 1, 1, 0 };
			
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		isBlocked = new boolean[N][N];
		
		StringTokenizer st = null;
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				if( st.nextToken().equals("1") ) 
					isBlocked[y][x] = true;
			}
		}

		dfs(0, 0, 1); // 가로, 0, 1 위치에서 시작
		
		System.out.print(numOfCase);
		
		in.close();
	}

	private static void dfs(int dir, int y, int x) {
		if(y == N - 1 && x == N - 1) {
			numOfCase++;
			return;
		}
		
		for(int d = 0; d < 3; d++) {
			// 가로 0, 세로 2 => 차이 2이면 불가능
			if( Math.abs(dir - d) == 2 ) continue;
			
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
			
			if( isBlocked[ny][nx] ) continue;
			
			// 대각선 이동이면 두가지 위치 추가 체크
			if( d == 1 && (isBlocked[ny - 1][nx] || isBlocked[ny][nx - 1] ) ) continue;
			
			dfs(d, ny, nx);
		}
	}
}
