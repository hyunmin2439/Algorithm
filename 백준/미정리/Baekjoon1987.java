package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준1987   memory : 14520  time : 796
public class Baekjoon1987 {
	
	static int R, C, max;
	static char[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][];
		
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		// 0, 0 : 시작지점도 포함
		int checkBit = 1 << (board[0][0] - 'A');
		//    y  x cnt    flag
		recur(0, 0, 1, 0 | checkBit);
		
		System.out.println(max);
		br.close();
	}
	
	//				    상 하  좌 우
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	private static void recur(int y, int x, int cnt, int flag) {
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위 벗어나면 패스
			if( !(0 <= ny && ny < R) || !(0 <= nx && nx < C) ) continue;
			
			// 이미 밟았던 알파벳 칸이라면 패스
			int checkBit = 1 << (board[ny][nx] - 'A');
			if( (flag & checkBit) != 0 ) continue;
			
			// 재귀 호출
			recur(ny, nx, cnt + 1, flag | checkBit );
		}
		
		// 최대값인지 체크
		max = Math.max(max, cnt);
	}
	
}
