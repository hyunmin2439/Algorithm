import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:17528KB / Time:180ms
public class BJ_1303_전쟁전투_1_BFS {

	static int N, M, powerOfWhiteTeam, powerOfBlueTeam;
	
	static char[][] map;
	static boolean[][] visited;
	
	static Queue<Node> queue;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[M][];
		visited = new boolean[M][N];
		
		queue = new LinkedList<>();
		
		for(int y = 0; y < M; y++) {
			map[y] = in.readLine().toCharArray();
		}
		
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				if( !visited[y][x] ) {
					bfs(map[y][x], y, x);					
				}
			}
		}
		
		System.out.print(powerOfWhiteTeam + " " + powerOfBlueTeam);
		
		in.close();
	}
	
	private static void bfs(char c, int y, int x) {
		int numOfSoldiers = 0;
		
		visited[y][x] = true;
		queue.offer(new Node(y, x));
		
		while( !queue.isEmpty() ) {
			Node n = queue.poll();
			
			numOfSoldiers++;
			
			for(int d = 0; d < 4; d++) {
				int ny = n.y + dy[d];
				int nx = n.x + dx[d];
				
				if( !(0 <= ny && ny < M && 0 <= nx && nx < N) ) continue;
				
				if( visited[ny][nx] || map[ny][nx] != c ) continue;
				
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx));
			}
		}
		
		numOfSoldiers *= numOfSoldiers;
		if( c == 'W' ) powerOfWhiteTeam += numOfSoldiers;
		else powerOfBlueTeam += numOfSoldiers;
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}
