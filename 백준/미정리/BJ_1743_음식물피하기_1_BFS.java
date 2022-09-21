import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Memory:15856KB / Time:180ms
 */

public class BJ_1743_음식물피하기_1_BFS {

	static int N, M, K, maxSize;
	static boolean[][] isExistGarbage, visited;
	
	static Node[] garbage;
	static Queue<Node> queue;
	
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isExistGarbage = new boolean[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		garbage = new Node[K];
		queue = new LinkedList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			garbage[i] = new Node(r, c);
			isExistGarbage[r][c] = true;
		}
		
		for(int i = 0; i < K; i++) {
			if( !visited[ garbage[i].r ][ garbage[i].c ] )
				bfs(i);
		}
		
		System.out.print(maxSize);
		
		in.close();
	}

	private static void bfs(int idx) {
		int size = 0;
		queue.offer(garbage[idx]);
		visited[ garbage[idx].r ][ garbage[idx].c ] = true;
		
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			size++;
			
			for(int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if( !(1 <= nr && nr <= N && 1 <= nc && nc <= M) ) continue;
				
				if( !isExistGarbage[nr][nc] ) continue;
				
				if( visited[nr][nc] ) continue;
				
				visited[nr][nc] = true;
				queue.offer(new Node(nr, nc));
			}
		}
		
		maxSize = Math.max(maxSize, size);
	}

	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
