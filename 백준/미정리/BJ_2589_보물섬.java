package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:294,592KB / Time:504ms

public class BJ_2589_보물섬 {

	private static final int INF = 10000;
	private static int R, C;
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		char[][] land = input();
		
		solve(land);
	}
	
	private static void solve(char[][] land) {
		int answer = 0;
		
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(land[y][x] == 'L')
					answer = Math.max(answer, bfs(land, new Node(y, x, 0)));
			}
		}
		
		System.out.print(answer);
	}
	
	private static int bfs(char[][] land, Node start) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		Node curr = null;
		int max = 0;
		
		visited[start.y][start.x] = true;
		queue.offer(start);
		
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			max = Math.max(max, curr.move);
			
			for(int d = 0; d < 4; d++) {
				int ny = curr.y + dy[d];
				int nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < R && 0 <= nx && nx < C) ) continue;
				
				if( visited[ny][nx] || land[ny][nx] == 'W' ) continue;
				
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx, curr.move + 1));
			}
		}
		
		return max;
	}
	
	private static char[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] land = new char[R][];
		for(int y = 0; y < R; y++) {
			land[y] = in.readLine().toCharArray();
		}
		
		in.close();
		
		return land;
	}
	
	static class Node {
		int y, x, move;
		
		public Node(int y, int x, int move) {
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}

}
