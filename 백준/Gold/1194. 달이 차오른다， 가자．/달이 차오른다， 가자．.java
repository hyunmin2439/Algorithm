import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final char START = '0';
	static final char EXIT = '1';
	
	static Node start;
	static int N, M, min;
	static char map[][];
	static boolean visit[][][];
		
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new char[N][M];
		visit = new boolean[N][M][64];
		
		for (int y = 0; y < N; y++) {
			String input = in.readLine();
			for (int x = 0; x < M; x++) {
				map[y][x] = input.charAt(x);
				
				if(map[y][x] == START) 
					start = new Node(y, x, 0, 0);
			}
		}
		
		bfs();
		
		System.out.print(min != Integer.MAX_VALUE ? min : -1);
		
		in.close();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(start);
		visit[start.y][start.x][start.hasKey] = true;
		
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			
			if(map[node.y][node.x] == EXIT) {
				min = node.move;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				int hasKey = node.hasKey;
				int doorCnt = -1;
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if( visit[ny][nx][hasKey]) continue;
				
				if( map[ny][nx] != EMPTY) {
					
					if(map[ny][nx] == WALL) continue;
					
					if('A' <= map[ny][nx] && map[ny][nx] <= 'F' ) {
						doorCnt = map[ny][nx] - 'A';
					}
					
					if( doorCnt != -1 && (hasKey & (1 << doorCnt)) == 0) continue;
					
					if('a' <= map[ny][nx] && map[ny][nx] <= 'f') {
						hasKey |= ( 1 << (map[ny][nx] - 'a') );
					}
					
				}
				
				queue.offer(new Node(ny, nx, hasKey, node.move + 1));
				
				visit[ny][nx][hasKey] = true;
			}
			
		}
		
	}

	static class Node {
		int y, x, hasKey, move;
		
		public Node(int y, int x, int hasKey, int move) {
			this.y = y;
			this.x = x;
			this.hasKey = hasKey;
			this.move = move;
		}
		
	}
}
