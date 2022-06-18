import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		int[] param = input();
		
		int answer = bfs(param[0], param[1], param[2], param[3], param[4]);
		
		System.out.print(answer);
	}

	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[] param = new int[5];
		
		param[0] = Integer.parseInt(in.readLine());
		
		st = new StringTokenizer(in.readLine());
		param[1] = Integer.parseInt(st.nextToken()); 
		param[2] = Integer.parseInt(st.nextToken()); 
		param[3] = Integer.parseInt(st.nextToken()); 
		param[4] = Integer.parseInt(st.nextToken()); 
		
		in.close();
		
		return param;
	}
	
	private static int bfs(int N, int r1, int c1, int r2, int c2) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[] dr = { -2, -2,  0, 0,  2, 2 };
		int[] dc = { -1,  1, -2, 2, -1, 1 };
		
		queue.offer(new Node(r1, c1, 0));
		visited[r1][c1] = true;
		
		int nr, nc;
		Node curr;
		while( !(queue.isEmpty()) ) {
			curr = queue.poll();
			
			for(int d = 0; d < dr.length; d++) {
				nr = curr.r + dr[d];
				nc = curr.c + dc[d];
				
				if( !(0 <= nr && nr < N && 0 <= nc && nc < N) 
						|| visited[nr][nc] ) continue;
	
				if(nr == r2 && nc == c2) return curr.m + 1;
				
				queue.offer(new Node(nr, nc, curr.m + 1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	static class Node {
		int r, c, m;
		
		public Node(int r, int c, int m) {
			this.r = r;
			this.c = c;
			this.m = m;
		}
	}
}