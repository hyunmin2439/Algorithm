package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:43736KB / Time:472ms

public class BJ_1926_그림 {

	private static int N, M, numOfPainting, maxArea, canvus[][];
	
	private static boolean[][] visited;
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
		
		System.out.print(numOfPainting + "\n" + maxArea);
	}
	
	private static void solve() {
		Queue<Node> queue = new LinkedList<>();
		visited = new boolean[N][M];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if( ! visited[y][x] && canvus[y][x] == 1 ) {
					numOfPainting++;
					visited[y][x] = true;
					queue.offer(new Node(y, x));
					bfs(queue);
				}
			}
		}
	}
	
	private static void bfs(Queue<Node> queue) {
		int area = 1;
		
		while( !queue.isEmpty() ) {
			Node curr = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int ny = curr.y + dy[d];
				int nx = curr.x + dx[d];
				
				if( ! (0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if(canvus[ny][nx] == 0 || visited[ny][nx]) continue;
				
				area++;
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx));
			}
		}
		
		maxArea = Math.max(maxArea, area);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		canvus = new int[N][M];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				canvus[y][x] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
