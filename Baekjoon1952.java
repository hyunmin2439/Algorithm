import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14176KB / Time:124ms
public class Baekjoon1952 {

	static int M, N, dir, turnCnt;
	static boolean[][] visited;
	
	static int[] dy = { 0, 1,  0, -1 };
	static int[] dx = { 1, 0, -1,  0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[M][N];

		snailCircle();
		
		System.out.println(turnCnt);
		
		in.close();
	}

	private static void snailCircle() {
		int y = 0, x = 0;
		int circleCnt = 0;
		int end = M * N;
		
		while(circleCnt < end) {
			visited[y][x] = true;

			y += dy[dir];
			x += dx[dir];
			
			if( !(0 <= y && y < M && 0 <= x && x < N) || visited[y][x] ) {
				y -= dy[dir];
				x -= dx[dir];
				
				dir++;
				if(dir == 4) dir = 0;
				
				y += dy[dir];
				x += dx[dir];
				
				if(visited[y][x]) break;
				
				turnCnt++;
			}

			circleCnt++;
		}
	}

}
