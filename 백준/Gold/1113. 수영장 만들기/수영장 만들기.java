import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, maxHei;
	
	private static final int WALL = 0, WATER = 1, EMPTY = 2;
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		int[][][] pool = input();
		Queue<Node> queue = new LinkedList<>();
		List<Node> list = new ArrayList<>();
		
		for(int h = maxHei - 1; h > 0; h--) {
			boolean[][] visited = new boolean[N][M];
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < M; x++) {
					if( !visited[y][x] && pool[h][y][x] != WALL ) 
						bfs(queue, list, pool[h], visited, new Node(y, x));
				}
			}
		}
		
		int water = 0;
		for(int h = 1; h < maxHei; h++) {
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < M; x++) {
					if(pool[h][y][x] == WATER)
						water++;
				}
			}
		}
		
		System.out.print(water);
	}
	
	private static void bfs(Queue<Node> queue, List<Node> list, int[][] floor, boolean[][] visited, Node curr) {
		boolean isOut = false;
		visited[curr.y][curr.x] = true;
		queue.add(curr);
		list.add(curr);
		
		int ny, nx;
		Node node;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			for(int d = 0; d < dy.length; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) {
					isOut = true;
					continue;
				}
				
				if(visited[ny][nx] || floor[ny][nx] != WATER) continue;
				
				visited[ny][nx] = true;
				node = new Node(ny, nx);
				queue.add(node);
				list.add(node);
			}
		}
		
		if( isOut ) {
			for(int i = 0; i < list.size(); i++) {
				node = list.get(i);
				floor[node.y][node.x] = EMPTY;;
			}
		}
		
		list.clear();
	}
	
	private static int[][][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[] line;
		int[][] tmp = new int[N][M];
		
		for(int y = 0; y < N; y++) {
			line = in.readLine().toCharArray();
			for(int x = 0; x < M; x++) {
				tmp[y][x] = line[x] - '0';
				maxHei = Math.max(maxHei, tmp[y][x]);
			}
		}
		
		int[][][] pool = new int[maxHei][N][M];
		
		for(int h = 1; h < maxHei; h++) {
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < M; x++) {
					pool[h][y][x] = h < tmp[y][x] ? WALL : WATER;
				}
			}
		}
		
		in.close();
		
		return pool;
	}
	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}