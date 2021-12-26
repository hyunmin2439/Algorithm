import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:14308KB / Time:136ms
 */
public class BJ_14503_로봇청소기_1 {

	static int N, M, numOfCleanArea;
	static boolean[][] isWall, isClean;
	
	static Node robotCleaner;
	
	//				   북, 동, 남, 서
	static int[] dy = { -1, 0, 1,  0 };
	static int[] dx = {  0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		startCleanning();
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				System.out.print( (isClean[y][x] ? 1 : 0) + " " );
			}
			System.out.println();
		}
		
		System.out.print(numOfCleanArea);
	}

	private static void startCleanning() {
		boolean isDoneCleanning;
		int ny, nx, nd;
		
		while(true) {
			isDoneCleanning = false;
			ny = robotCleaner.y;
			nx = robotCleaner.x;
			nd = robotCleaner.d;

			// 후진했을 경우도 있기 때문에 조건 체크
			if( !isClean[ny][nx] ) {
				// 1. 현재 위치를 청소한다.
				isClean[ny][nx] = true;
				numOfCleanArea++;				
			}
			
			// 2. 왼쪽 방향부터 차례대로 인접한 칸 탐색
			// b ~ d 조건
			for(int i = 0; i < 4; i++) {
				if(--nd < 0) nd = 3;
				ny = robotCleaner.y + dy[nd];
				nx = robotCleaner.x + dx[nd];
				
				// 2-a. 왼쪽 방향 청소 안한 공간 존재
				if( !isWall[ny][nx] && !isClean[ny][nx] ) {
					isDoneCleanning = true;
					robotCleaner.y = ny;
					robotCleaner.x = nx;
					robotCleaner.d = nd;
					break;
				}
			}
			
			// 2-a. 조건 만족시 넘어감
			if( isDoneCleanning ) continue;
			
			nd = ( robotCleaner.d % 2 == 0 ? 2 : 4 ) - robotCleaner.d; // 반대 방향
			ny = robotCleaner.y + dy[nd];
			nx = robotCleaner.x + dx[nd];
			
			// 2-d. 후진 불가능
			if( isWall[ny][nx] ) break;
			
			// 2-c. 방향은 유지한 체 후진
			robotCleaner.y = ny;
			robotCleaner.x = nx;
		}
		
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isWall = new boolean[N][M];
		isClean = new boolean[N][M];
		
		st = new StringTokenizer(in.readLine());
		robotCleaner = new Node(Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()), 
								Integer.parseInt(st.nextToken()) );
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				if(st.nextToken().equals("1"))
					isWall[y][x] = true;
			}
		}
		
		in.close();
	}

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		
	}
}
