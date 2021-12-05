import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2583_영역구하기 {

	static int M, N, K, cnt;
	static boolean[][] visited;
	static boolean[][] isPainting;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static Queue<Node> queue;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		isPainting = new boolean[M][N];
		
		for(int r = 0; r < K; r++) {
			
			st = new StringTokenizer(in.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int ey = M - Integer.parseInt(st.nextToken()); // 아래쪽이 0,0 좌표
			int ex = Integer.parseInt(st.nextToken());
			int sy = M - Integer.parseInt(st.nextToken());
			
			paintPaper(sx, sy, ex, ey);
			
		}
		
		printPaper();
		
		queue = new LinkedList<>();
		list = new LinkedList<>();
		visited = new boolean[M][N];
		
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				if( !visited[y][x] && !isPainting[y][x] ) {
					bfs(y, x);
				}
			}
		}
		
		System.out.println(list.size());
		list.forEach( el -> System.out.print(el + " ") );
		
		in.close();
	}
	
	private static void bfs(int y, int x) {
		int area = 0;
		
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		
		Node node = null;
		while( !queue.isEmpty() ) {
			node = queue.poll();
			area++;
			
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				if( !(0 <= ny && ny < M && 0 <= nx && nx < N) ) continue;
				
				if( visited[y][x] || isPainting[y][x] ) continue;
				
				queue.offer(new Node(ny, nx));
			}
		}
		
		list.add(area);
	}

	private static void paintPaper(int sx, int sy, int ex, int ey) {
		
		for(int y = sy; y < ey; y++) {
			for(int x = sx; x < ex; x++) {
				isPainting[y][x] = true;
			}
		}
		
	}
	

	// 디버깅용
	private static void printPaper() {
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				System.out.print(isPainting[y][x]);
			}
			System.out.println();
		}
	}
	
	static class Node {
		int y , x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
