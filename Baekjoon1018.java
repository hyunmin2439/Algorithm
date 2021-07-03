package solved;

import java.io.*;

/*
 * Repainting the chessBoard
 * 							      번갈아
 * 1. The chessBoard is painted alternately white and black.
 * 
 * 2. Minimum number of squares that need to be repainted after 
 *    being cut from a given n * m size plate to an 8 * 8 size chessboard
 * 
 * Input
 * n m (8 <= n, m <= 50)
 * n row, m column  W || B  (W:White, B:Black)
 * 
 * Output
 * Minimum number of squares to repaint
 */

public class Baekjoon1018 {
	static char[][] chess;
	static char[][][] answer = 
		{ { { 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' } },

			{ { 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' },
			{ 'B','W','B','W','B','W','B','W' },
			{ 'W','B','W','B','W','B','W','B' } } };
	
	public static void main(String[] args) throws IOException {
		int n, m, minCnt = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ));
		
		// input
		String[] line = br.readLine().trim().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		chess = new char[n][m];
		
		{ // input { } 묶어서 i 변수 버리기
			int i = 0;
			while(i < n) {
				line = br.readLine().trim().split("");
				for(int j = 0; j < m; j++) {
					chess[i][j] = line[j].trim().charAt(0);
				}
				i++;
			}
		}
		
		for(int i = 0; i + 8 <= n; i++) {
			for(int j = 0; j + 8 <= m; j++) {
				int cnt = secondSolve(i, j);
				minCnt = cnt < minCnt ? cnt : minCnt;
			}
		}
		
		bw.write(String.valueOf(minCnt));
		br.close();
		bw.close();
	}

	// memory : 15140KB  Time : 160ms
	private static int firstSolve(int rowStart, int columnStart) {
		int[] cnt = new int[2];
		
		for(int i = rowStart; i < rowStart + 8; i++) {
			for(int j = columnStart; j < columnStart + 8; j++) {
				if( !( chess[i][j] 
						== answer[0][i - rowStart][j - columnStart] ) ) cnt[0]++;
				if( !( chess[i][j] 
						== answer[1][i - rowStart][j - columnStart] ) ) cnt[1]++;
			}
		}
		
		return cnt[0] < cnt[1] ? cnt[0] : cnt[1];
	}
	
	// memory : 15308KB  Time : 156ms
	private static int secondSolve(int rowStart, int columnStart) {
		int[] cnt = new int[2];

		for (int i = rowStart; i < rowStart + 8; i += 2) {
			// case 1. 1-line 'W','B', ...
			for (int j = columnStart; j < columnStart + 8; j += 2) {
				if( !( chess[i][j] == 'W' ) ) cnt[0]++;
				if( !( chess[i][j + 1] == 'B' ) ) cnt[0]++;
				
				if( !( chess[i + 1][j] == 'B' ) ) cnt[0]++;
				if( !( chess[i + 1][j + 1] == 'W' ) ) cnt[0]++;
			}
			
			// case 2. 1-line 'B','W', ...
			for (int j = columnStart; j < columnStart + 8; j += 2) {
				if( !( chess[i][j] == 'B' ) ) cnt[1]++;
				if( !( chess[i][j + 1] == 'W' ) ) cnt[1]++;
				
				if( !( chess[i + 1][j] == 'W' ) ) cnt[1]++;
				if( !( chess[i + 1][j + 1] == 'B' ) ) cnt[1]++;
			}
		}
		
		return cnt[0] < cnt[1] ? cnt[0] : cnt[1];
	}
}
