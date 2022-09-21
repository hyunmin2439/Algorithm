package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 빙고
public class Baekjoon2578 {

	static int line, cnt;
	static int bingo[][] = new int[5][5];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		for (int y = 0; y < 5; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < 5; x++) {
				bingo[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		out:
		for (int y = 0; y < 5; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < 5; x++) {
				cnt++;
				find(Integer.parseInt(st.nextToken()));
				if(line >= 3) break out;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}

	private static void find(int num) {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				if(bingo[y][x] == num) {
					bingo[y][x] = 0;
					check(y, x);
				}
			}
		}
	}

	private static void check(int y, int x) {
		int row = 0, col = 0; // 가로, 세로, 대각선
		
		for (int i = 0; i < 5; i++) {
			row += bingo[y][i];
			col += bingo[i][x];
		}
		
		if(row == 0) line++;
		if(col == 0) line++;
		
		// 대각선 단 두개
		if( y == x ) {
			int dia = 0;
			
			for (int i = 0; i < 5; i++) {
				dia += bingo[i][i];			
			}
			
			if(dia == 0) line++;
		}
		
		if( y + x == 4 ) {
			int dia = 0;
			
			for (int i = 0; i < 5; i++) {
				dia += bingo[i][4 - i];			
			}
			
			if(dia == 0) line++;
		}
	}
}
