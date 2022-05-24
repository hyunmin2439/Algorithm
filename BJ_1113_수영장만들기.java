import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 3차원 배열 물을 전부 채워놓고
 * 
 * 2. 한 층씩 내려가며 2차원 배열로 생각하여 외부와 맞닿는 곳은 빈곳 처리
 * 
 * 3. 최종적으로 남은 물양 구하기
 * 
 * Memory:15,208KB / Time:152ms
 */

public class BJ_1113_수영장만들기 {

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
		
		// 0 : 전부 바닥 벽
		for(int h = 1; h < maxHei; h++) {
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < M; x++) {
					// 벽 부분 빼고 전부 물로 채우기
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
