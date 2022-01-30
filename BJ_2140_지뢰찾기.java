package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 노션 풀이 주소
 * https://light-caption-620.notion.site/2140-9a8b43f94e524eb4aa277038019174c8
 * 
 * Memory:14380KB / Time:132ms
 */

public class BJ_2140_지뢰찾기 {

	private static final int UNKNOWN = -1, MINE = -2, EMPTY = -3;
	
	private static int N;
	private static int[][] board;
	
	private static Queue<Node> queue;
	
	//							→  ↘  ↓   ↙   ←   ↖   ↑   ↗
	private static int[] dy = { 0, 1, 1,  1,  0, -1, -1, -1};
	private static int[] dx = { 1, 1, 0, -1, -1, -1,  0,  1};
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = 0;
		if(N <= 3) {
			if(N == 3) res = board[0][0];
			
			System.out.print(res);
			return;
		}
		
		res = solve();
		if(N >= 5) res += Math.pow(N - 4, 2);
		
		System.out.println(res);
	}

	private static int solve() {
		int ny, nx, numOfMineInCurr = 0, totNumOfMine = 0;
		Queue<Node> unknownList = new LinkedList<>();
		
		// 테두리 좌표 돌면서 모든 폭탄개수를 다 구했을 시
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			numOfMineInCurr = 0;
			
			for(int d = 0; d < 8; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(1 <= ny && ny < N - 1 && 1 <= nx && nx < N - 1) ) continue;
				
				if(board[ny][nx] == MINE) // 주변 폭탄 세기
					numOfMineInCurr++;
				else if(board[ny][nx] == UNKNOWN) // 모르는 곳 좌표 담기
					unknownList.offer(new Node(ny, nx));
			}
			
			if(unknownList.isEmpty()) continue;
			
			if(board[curr.y][curr.x] == numOfMineInCurr) {
				while(!unknownList.isEmpty()) {
					Node unknown = unknownList.poll();
					board[unknown.y][unknown.x] = EMPTY;
				}
			}
			else if(board[curr.y][curr.x] == numOfMineInCurr + unknownList.size()) {
				while(!unknownList.isEmpty()) {
					Node unknown = unknownList.poll();
					board[unknown.y][unknown.x] = MINE;
					totNumOfMine++;
				}
			}
			else {
				queue.offer(curr); // 현재 좌표 다시 담기
				unknownList.clear(); // 초기화
			}
		}
		
		return totNumOfMine;
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		board = new int[N][N];
		
		queue = new LinkedList<>();
		
		char[] line = null;
		for(int y = 0; y < N; y++) {
			line = in.readLine().toCharArray();
			for(int x = 0; x < N; x++) {
				if(line[x] == '#')
					board[y][x] = UNKNOWN;
				else 
					board[y][x] = line[x] - '0';
				
				// 테두리 부분 좌표만 따로 queue에 담아놓기
				if(y == 0 || y == N - 1 || x == 0 || x == N - 1)
					queue.offer(new Node(y, x));
			}
		}
		
		in.close();
	}
	
	// 확인용 테스트 메서드
	private static void test() {
		String s;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(board[y][x] == MINE)
					s = "M";
				else if(board[y][x] == EMPTY)
					s = "E";
				else
					s = String.valueOf(board[y][x]);
				
				System.out.print(s + " ");
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

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}
		
	}

}
