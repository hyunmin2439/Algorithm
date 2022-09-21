package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Memory:54232KB / Time:332ms
 */

public class BJ_2469_안전영역_1_bfs {

	static int N, maxHeight, maxNumOfSafeArea;
	static int[][] area;
	static boolean[][] isFlood, visited;
	
	static Queue<int[]> queue;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		inputAndInit();
		
		// 강수량 : 0 ~ 최대높이 - 1
		// 모든 지역 높이 1 => 1의 강수량, 결과 0 < 0의 강수량, 결과 1
		// 최대 높이 강수량 => 결과 0
		for(int height = 0; height < maxHeight; height++) {
			int numOfSafeArea = 0;
			
			checkFlood(height);
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					if( !isFlood[y][x] && !visited[y][x] ) {
						numOfSafeArea++;
						bfs(y, x);
					}
				}
			}			
			maxNumOfSafeArea = Math.max(numOfSafeArea, maxNumOfSafeArea);
			
			// test(height);
		}
		
		System.out.print(maxNumOfSafeArea);
	}

	private static void inputAndInit() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		queue = new LinkedList<>();
		
		N = Integer.parseInt(in.readLine());
		
		area = new int[N][N];
		isFlood = new boolean[N][N];
		visited = new boolean[N][N];		
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			
			for(int x = 0; x < N; x++) {
				area[y][x] = Integer.parseInt(st.nextToken());
				
				maxHeight = Math.max(area[y][x], maxHeight); // 최대 높이 구해놓는다.
			}
		}
		
		in.close();
	}
	
	private static void checkFlood(int height) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				visited[y][x] = false;
				
				// 잠기지 않았고 강수량이 현재 지역보다 높으면
				if( !isFlood[y][x] && area[y][x] <= height )
					isFlood[y][x] = true;
			}
		}
	}
	
	private static void bfs(int y, int x) {
		queue.offer(new int[] { y, x });
		visited[y][x] = true;
		
		while( !queue.isEmpty() ) {
			int[] pos = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int ny = pos[0] + dy[d];
				int nx = pos[1] + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				
				if( visited[ny][nx] || isFlood[ny][nx] ) continue;
				
				visited[ny][nx] = true;
				queue.offer(new int[] {ny, nx});
			}
		}
	}
	
	private static void test(int height) {
		System.out.println("현재높이:" + height + " 결과" + maxNumOfSafeArea);
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.print( (isFlood[y][x] ? 'X' : 'O') + " ");
			}
			System.out.println();
		}
	}
}
