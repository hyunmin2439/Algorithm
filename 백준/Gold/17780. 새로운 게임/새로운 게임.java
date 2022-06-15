import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static final int WHITE = 0, RED = 1, BLUE = 2;
	private static final int[] dy = { 0, 0,  0, -1, 1 };
	private static final int[] dx = { 0, 1, -1,  0, 0 };
	
	private static int N, K, color[][];
	
	private static List<Node> list;
	private static List<Integer> board[][];
	
	public static void main(String[] args) throws Exception{
		input();
		
		int cnt = 0;
		
		while(cnt <= 1000) {
			cnt++;
			for(int i = 1; i <= K; i++) {
				if( isStop(list.get(i)) ) {
					System.out.print(cnt);
					return;
				}
			}
		}
		
		System.out.print(-1);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		color = new int[N + 1][N + 1];
		list = new ArrayList<>();
		board = new LinkedList[N + 1][N + 1];
		
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 1; x <= N; x++) {
				color[y][x] = Integer.parseInt(st.nextToken());
				board[y][x] = new LinkedList<>();
			}
		}
		
		list.add(new Node(0, 0, 0, 0)); // dummy
		int y, x, d;
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(in.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			board[y][x].add(i);
			list.add(new Node(y, x, d, i));
		}
		
		in.close();
	}
	
	private static boolean isStop(Node node) {
		// 말이 맨 아래에 있어야만 움직일 수 있다
//		System.out.println("[" + node.y + "," + node.x + "]");
		if(board[node.y][node.x].get(0) != node.i)
			return false;
		
		int ny = node.y + dy[node.d]; 
		int	nx = node.x + dx[node.d];
		
		if( isOutBoard(ny, nx) || color[ny][nx] == BLUE) {
			node.d = node.d < 3 ? (node.d == 1 ? 2 : 1) : (node.d == 3 ? 4 : 3);
			ny = node.y + dy[node.d];
			nx = node.x + dx[node.d];
			
			if( isOutBoard(ny, nx) || color[ny][nx] == BLUE ) {
				ny = node.y;
				nx = node.x;
			}
			else {
				if( color[ny][nx] == RED )
					reverse(node.y, node.x);
				move(ny, nx, node.y, node.x);
			}
		}
		else {
			// 뒤집고 이동해도 상관없음
			if( color[ny][nx] == RED )
				reverse(node.y, node.x);
			// WHITE 일땐 이동만
			move(ny, nx, node.y, node.x);
		}
		
		return board[ny][nx].size() >= 4;
	}
	
	private static boolean isOutBoard(int ny, int nx) {
		return !( 1 <= ny && ny <= N && 1 <= nx && nx <= N );
	}
	
	private static void move(int ny, int nx, int y, int x) {
		board[y][x].forEach( i -> {
			list.get(i).y = ny;
			list.get(i).x = nx;
		});
		board[ny][nx].addAll(board[y][x]);
		board[y][x].clear();
		
	}
	
	private static void reverse(int y, int x) {
		List<Integer> tmp = new LinkedList<>();
		
		for(int i = board[y][x].size() - 1; i >= 0; i--) {
			tmp.add(board[y][x].get(i));
		}
		
		board[y][x] = tmp;
	}
	
	static class Node {
		int y, x, d, i;
		
		public Node(int y, int x, int d, int i) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.i = i;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", d=" + d + ", i=" + i + "]";
		}
		
		
	}

}