import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
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
				move(node, ny, nx);
				if( color[ny][nx] == RED )
					reverse(node.i, ny, nx);
			}
		}
		else {
			// WHITE 일땐 이동만
			move(node, ny, nx);
			if( color[ny][nx] == RED )
				reverse(node.i, ny, nx);
		}
		
		return board[ny][nx].size() >= 4;
	}
	
	private static boolean isOutBoard(int ny, int nx) {
		return !( 1 <= ny && ny <= N && 1 <= nx && nx <= N );
	}
	
	private static void move(Node node, int ny, int nx) {
		int nodeIdx = board[node.y][node.x].indexOf(node.i);
		for (Iterator<Integer> iter = board[node.y][node.x].iterator(); iter.hasNext();) {
			Integer idx = iter.next();			
			if(nodeIdx-- > 0) continue;
			
			list.get(idx).y = ny;
			list.get(idx).x = nx;
			board[ny][nx].add(idx);
			iter.remove();
		}
	}
	
	private static void reverse(int nodeNum, int ny, int nx) {
		List<Integer> tmp = new LinkedList<>();
		int idx = board[ny][nx].indexOf(nodeNum);
		
		// 뒤집지 않는 부분
		for(int i = 0; i < idx; i++) {
			tmp.add(board[ny][nx].get(i));
		}
		
		// 뒤집어야 하는 부분
		for(int i = board[ny][nx].size() - 1; i >= idx; i--) {
			tmp.add(board[ny][nx].get(i));
		}
		
		board[ny][nx] = tmp;
	}
	
	static class Node {
		int y, x, d, i;
		
		public Node(int y, int x, int d, int i) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.i = i;
		}
	}
}