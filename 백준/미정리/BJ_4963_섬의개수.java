package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:15,876KB / Time:180ms

public class BJ_4963_섬의개수 {

	private static int W, H;
	private static final int SEA = 0;
	
	private static final int[] dy = { -1, -1, -1, 0, 1, 1,  1,  0 };
	private static final int[] dx = { -1,  0,  1, 1, 1, 0, -1, -1 };	
	
	public static void main(String[] args) throws Exception {
		 solve();
	}
	
	private static void solve() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int numOfLand = 0, world[][];
		boolean visited[][];
		while(true) {
			numOfLand = 0;
			st = new StringTokenizer(in.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W == 0 && H == 0) break;
			
			world = new int[H][W];
			visited = new boolean[H][W];
			
			for(int y = 0; y < H; y++) {
				st = new StringTokenizer(in.readLine());
				for(int x = 0; x < W; x++) {
					world[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int y = 0; y < H; y++) {
				for(int x = 0; x < W; x++) {
					if( !visited[y][x] && world[y][x] != SEA ) {
						bfs(world, visited, y, x);
						numOfLand++;
					}
				}
			}
			
			System.out.println(numOfLand);		
		}		
	}
	
	private static void bfs(int[][] world, boolean[][] visited, int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		
		while( !queue.isEmpty() ) {
			Node curr = queue.poll();
			
			for(int d = 0; d < dy.length; d++) {
				int ny = curr.y + dy[d];
				int nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < H && 0 <= nx && nx < W) ||
						visited[ny][nx] || world[ny][nx] == SEA ) continue;
				
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx));
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
