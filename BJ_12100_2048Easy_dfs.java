package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:18488KB / Time:172ms
 */

public class BJ_12100_2048Easy_dfs {
	
	private static int N, maxBlock, board[][];
	
	public static void main(String[] args) throws Exception {
		input();
		
		dfs(board, 0);
		
		System.out.print(maxBlock);
	}
	
	private static void dfs(int[][] originBoard, int moveCnt) {
		if(moveCnt == 5) return;
		
		int[][] board = null;
		
		// 4방향으로
		for(int d = 0; d < 4; d++) {
			board = copyArray(originBoard);
			
			moveBlock(board, d);
			
			// printBoard(board, moveCnt, d);
			
			dfs(board, moveCnt + 1);
		}
	}
	
	private static void moveBlock(int[][] board, int dir) {
		int mp; // 블록을 옮길 위치를 나타내는 포인터
		int smp = dir % 2 == 0 ? 0 : N - 1; // 블록을 옮길 위치를 나타내는 포인터 시작위치
		int sp = dir % 2 == 0 ? 1 : N - 2; // 옮겨질 블럭을 가르키는 포인터
		int sw = dir % 2 == 0 ? 1 : -1; // 감소 또는 증가
		
		if(dir < 2) { // 상 하 방향
			for(int x = 0; x < N; x++) {
				mp = smp;
				
				for(int y = sp; 0 <= y && y < N; y += sw) {
					
					// 옮길 것이 없거나, 옮길 위치와 올겨져야 할 위치가 같은 경우
					if(board[y][x] == 0 || mp == y) continue;
					
					if(board[mp][x] == 0) { // 옮길 위치가 비었으면
						board[mp][x] = board[y][x];
						board[y][x] = 0;
					}
					else if(board[mp][x] == board[y][x]) { // 옮기는 위치의 블럭과 옮겨질 블럭의 숫자가 같으면
						board[mp][x] *= 2;
						maxBlock = Math.max(maxBlock, board[mp][x]);
						board[y][x] = 0;
						mp += sw;
					}
					else { // 옮기는 위치의 블럭과 옮겨질 블럭의 숫자가 다르면
						mp += sw;
						if(mp == y) continue;
						board[mp][x] = board[y][x];
						board[y][x] = 0;
					}
				}
			}
		}
		else { // 좌 우 방향 - 알고리즘 동일
			for(int y = 0; y < N; y++) {
				mp = smp;
				
				for(int x = sp; 0 <= x && x < N; x += sw) {
					
					if(board[y][x] == 0 || mp == x) continue;
					
					if(board[y][mp] == 0) {
						board[y][mp] = board[y][x];
						board[y][x] = 0;
					}
					else if(board[y][mp] == board[y][x]) {
						board[y][mp] *= 2;
						maxBlock = Math.max(maxBlock, board[y][mp]);
						board[y][x] = 0;
						mp += sw;
					}
					else {
						mp += sw;
						if(mp == x) continue;
						board[y][mp] = board[y][x];
						board[y][x] = 0;
					}
				}
			}
		}
		
	}
	
	
	private static int[][] copyArray(int[][] originBoard) {
		int[][] newBoard = new int[N][];
		
		for(int y = 0; y < N; y++) {
			newBoard[y] = originBoard[y].clone();
		}
		
		return newBoard;
	}
	
	private static void printBoard(int[][] board, int moveCnt, int dir) {
		System.out.println(moveCnt + "번 째 이동 : " + dir + "방향으로 이동 후");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.printf("%3d ", board[y][x]);
			}
			System.out.println();
		}
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		
		StringTokenizer st = null;
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				
				maxBlock = Math.max(maxBlock, board[y][x]);
			}
		}
		
		in.close();
	}
}
