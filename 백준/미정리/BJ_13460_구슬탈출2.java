package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:80,900KB / Time:276ms
public class BJ_13460_구슬탈출2 {
	
	private static Node startRed, startBlue;
	private static int N, M, minCnt;

	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception{
		char[][] board = input();
		
		minCnt = 11;
		
		dfs(board, startRed, startBlue, 1);
		
		System.out.print( minCnt == 11 ? -1 : minCnt);
	}
	
	private static char[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[N][];
		for(int y = 0; y < N; y++) {
			board[y] = in.readLine().toCharArray();
			
			for(int x = 0; x < M; x++) {
				if(board[y][x] == 'R') {
					startRed = new Node(y, x);
					board[y][x] = '.';
				}
				else if(board[y][x] == 'B') {
					startBlue = new Node(y, x);
					board[y][x] = '.';
				}
			}
		}
		
		in.close();
		
		return board;
	}
	
	private static void dfs(char[][] board, Node red, Node blue, int cnt) {
		if(cnt >= minCnt) return;
		
		for(int d = 0; d < dy.length; d++) {
			Node newRed = new Node(red.y, red.x);
			Node newBlue = new Node(blue.y, blue.x);
			
			int result = gravity(board, newRed, newBlue, d);
			
//			printBoard(board, newRed, newBlue, cnt);
			
			switch(result) {
			case -1: continue;
			case 0: dfs(board, newRed, newBlue, cnt + 1); break;
			case 1: minCnt = cnt; break;
			}
		}
	}
	
	private static int gravity(char[][] board, Node red, Node blue, int direc) {
		boolean isRedOut = false, isBlueOut = false, isRedFirst = false;
		
		switch(direc) {
		case 0: isRedFirst = red.y < blue.y; break;
		case 1: isRedFirst = red.x > blue.x;  break;
		case 2: isRedFirst = red.y > blue.y;  break;
		case 3: isRedFirst = red.x < blue.x;  break;
		}
		
		// 빨간 구슬 이동할 수 있는 위치까지
		while( board[red.y][red.x] == '.' ) {
			red.y += dy[direc];
			red.x += dx[direc];
		}
		
		// 구멍에 빠졌는지 체크
		if( board[red.y][red.x] == 'O' ) isRedOut = true;
		else { // 벽 위에 위치해 있기 때문에 한칸 뒤로
			red.y -= dy[direc];
			red.x -= dx[direc];
		}
		
		// 파란 구슬 같은 동작
		while( board[blue.y][blue.x] == '.' ) {
			blue.y += dy[direc];
			blue.x += dx[direc];
		}
		
		if( board[blue.y][blue.x] == 'O' ) isBlueOut = true;
		else {
			blue.y -= dy[direc];
			blue.x -= dx[direc];
		}
		
		if(isBlueOut) return -1; // 파란 구슬 들어가면 실패
		else if(isRedOut) return 1; // 빨간 구슬만 들어갈 경우 성공
		
		// 파란 구슬, 빨간 구슬 위치 겹칠 경우 어느 것이 먼저 움직이는지에 따라 위치 바꿔주기
		if(red.y == blue.y && red.x == blue.x) {
			if(isRedFirst) {
				blue.y -= dy[direc];
				blue.x -= dx[direc];
			}
			else {
				red.y -= dy[direc];
				red.x -= dx[direc];					
			}
		}
		
		return 0;
	}
	
	private static void printBoard(char[][] board, Node red, Node blue, int cnt) {
		System.out.println("---------------" + cnt + "회차---------------");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(board[y][x] == 'O')
					System.out.print(board[y][x]);
				else if(red.y == y && red.x == x)
					System.out.print('R');
				else if(blue.y == y && blue.x == x)
					System.out.print('B');
				else
					System.out.print(board[y][x]);
			}
			System.out.println();
		}
	}
	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
