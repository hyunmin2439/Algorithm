package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2563 {

	static int N, res;
	static boolean[][] board = new boolean[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 반복문 // Memory:14052KB / Time:132ms
			paste(y, x);
						 
			// 재귀   // Memory:14184KB / Time:140ms
			mx = x + 10; my = y + 10;
			//recur(y, x);
		}
		
		System.out.println(res);
		br.close();
	}

	private static void paste(int sy, int sx) {
		for (int y = sy; y < sy + 10; y++) {
			for (int x = sx; x < sx + 10; x++) {
				// 이미 칠해져 있지 않으면 증가
				if( !board[y][x] ) res++;
				board[y][x] = true;
			}
		}
	}
	
	
	static int mx, my;
	private static void recur(int sy, int sx) {
		if(sx >= mx) {
			sx = mx - 10;
			sy++;
		}
		
		if(sy >= my) return;
		
		// 이미 칠해져 있지 않으면 증가
		if( !board[sy][sx] ) res++;
		board[sy][sx] = true;
		
		recur(sy, sx + 1);
	}
}
