package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Memory:14,252KB / Time:124ms
public class BJ_11559_PuyoPuyo {

	private static final int N = 12, M = 6;
	private static final char EMPTY = '.';
	private static final int[] dy = { -1, 1, 0, 0 };
	private static final int[] dx = { 0, 0, -1, 1 };
	private static final char[] color = { 'R', 'G', 'B', 'P', 'Y' };
	
	public static void main(String[] args) throws Exception {
		char[][] board = input();
		
		solve(board);
	}
	
	private static char[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[][] board = new char[N][];
		
		for(int i = N - 1; i >= 0; i--)
			board[i] = in.readLine().toCharArray();
		
		in.close();
		
		return board;
	}
	
	private static void solve(char[][] board) {
		int cnt = 0;
		boolean isPuyo, visited[][];
		
		while(true) {
			isPuyo = false;
			visited = new boolean[N][M];
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < M; x++) {
					if( !visited[y][x] && board[y][x] != EMPTY )
						if( bfs(board, visited, y, x) )
							isPuyo = true;
				}
			}
			
			if( ! isPuyo ) break;

			gravity(board);
			
			cnt++;
		}
		
		System.out.print(cnt);
	}
	
	private static boolean bfs(char[][] board, boolean[][] visited, int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		List<Node> puyoList = new ArrayList<>();
		char color = board[y][x];
		
		visited[y][x] = true;
		Node node = new Node(y, x);
		queue.offer(node);
		puyoList.add(node);
		
		int ny, nx;
		while( !queue.isEmpty() ) {
			Node curr = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( ! (0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				if(visited[ny][nx] || board[ny][nx] != color) continue;
				
				visited[ny][nx] = true;
				node = new Node(ny, nx);
				queue.offer(node);
				puyoList.add(node);
			}
		}

		if(puyoList.size() > 3) {
			puyoList.forEach( puyo -> board[puyo.y][puyo.x] = '.' );
			return true;
		}
		
		return false;
	}
	
	private static void gravity(char[][] board) {
		int toY, fromY;
		
		for(int x = 0; x < M; x++) {
			toY = 0; fromY = 1;
			while(true) {
				while(toY < N && board[toY][x] != EMPTY) toY++;
				fromY = toY + 1;
				while(fromY < N && board[fromY][x] == EMPTY) fromY++;
				
				if(toY >= N || fromY >= N) break;
				board[toY][x] = board[fromY][x];
				board[fromY][x] = EMPTY;
			}
		}
	}
	
	private static void printBoard(char[][] board) {
		System.out.println("--------------------");
		for(int y = N -1; y >= 0; y--) {
			for(int x = 0; x < M; x++) {
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
