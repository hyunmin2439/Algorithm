import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17086_아기상어2 {

	private static final int SHARK = -1;
	private static int N, M, dist[][];

	private static int[] dy = { -1, -1, -1, 0, 1, 1,  1,  0 };
	private static int[] dx = { -1,  0,  1, 1, 1, 0, -1, -1 }; 
	
	public static void main(String[] args) throws Exception {
		List<Node> sharks = input();
		
		Queue<Node> queue = new LinkedList<>();
		
		sharks.forEach( shark -> bfs(queue, shark, dist) );
		
		int max = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				max = Math.max(max, dist[y][x]);
			}
		}
		
		System.out.print(max);
	}
	
	private static void bfs(Queue<Node> queue, Node shark, int[][] dist) {
		int ny, nx;
		
		queue.add(shark);
		
		while( !queue.isEmpty() ) {
			shark = queue.poll();
			
			for(int d = 0; d < 8; d++) {
				ny = shark.y + dy[d];
				nx = shark.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if( dist[ny][nx] != 0 && dist[ny][nx] <= shark.d + 1 ) continue;
				
				dist[ny][nx] = shark.d + 1;
				queue.offer(new Node(ny, nx, shark.d + 1));
			}
		}
	}

	private static List<Node> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist  = new int[N][M];
		
		List<Node> sharks = new ArrayList<>();
		
		for(int y = 0; y < N; y++) {
			 st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				if( "1".equals(st.nextToken()) ) {
					sharks.add(new Node(y, x, 0));
					dist[y][x] = SHARK;
				}
			}
		}
		
		return sharks;
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
