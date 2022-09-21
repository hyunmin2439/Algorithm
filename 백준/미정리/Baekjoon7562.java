package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 기초적인 bfs문제

public class Baekjoon7562 {

	static int T, N;
	static Node knight, dest;
	static boolean[][] visit;
	
	static int[] dy = { -2, -2, -1, -1,  1, 1,  2, 2};
	static int[] dx = { -1,  1, -2,  2, -2, 2, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine());
			visit = new boolean[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			knight = new Node(Integer.parseInt(st.nextToken()), 
							  Integer.parseInt(st.nextToken()), 0);
			
			st = new StringTokenizer(br.readLine());
			dest = new Node(Integer.parseInt(st.nextToken()), 
							  Integer.parseInt(st.nextToken()), 0);
			
			bfs();
		}
		
		br.close();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(knight);
		visit[knight.y][knight.x] = true;
		
		while( !queue.isEmpty() ) {
			Node pos = queue.poll();
			
			if(pos.y == dest.y && pos.x == dest.x) {
				System.out.println(pos.d);
				break;
			}
			
			for (int i = 0; i < dy.length; i++) {
				int ny = pos.y + dy[i];
				int nx = pos.x + dx[i];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) || visit[ny][nx]) continue;
				
				visit[ny][nx] = true;
				queue.offer(new Node(ny, nx, pos.d + 1));
			}
		}
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
