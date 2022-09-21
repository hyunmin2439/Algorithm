package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 오목
// memory : 16324KB / time:176ms
public class Baekjoon2615 {

	static int Y, X; // 좌표
	static char dol; // 현재 돌의 색깔
	static final int N = 19; // 바둑판 줄수 
	
	static char[][] pan = new char[N][];
	// 가로, 세로, 위쪽 대각선, 아래 대각선
	static int[] dy = { 0, 1, -1, 1};
	static int[] dx = { 1, 0,  1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < N; i++) {
			pan[i] = br.readLine().replace(" ", "").toCharArray();
		}
		
		isWin();
		
		// 아무도 이긴 쪽 없으면 0
		if(Y == 0) System.out.print(0);
		else System.out.print(dol + "\n" + Y + " " + X);
		br.close();
	}
	
	private static void isWin() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				// 돌이 놓아져 있으면
				if (pan[y][x] != '0') {
					dol = pan[y][x];
					for (int i = 0; i < dy.length; i++) {
						// 현재 위치 전 칸에 같은 돌이 있지 않을 경우만
						if (!check(y - dy[i], x - dx[i])) {
							// 2번째 칸부터 확인해야 하기에 cnt는 2;
							if (dfs(y + dy[i], x + dx[i], i, 2) == 5) {
								Y = y + 1;
								X = x + 1;
								return;
							}
						}
					}
				}
			}
		}
	}
	
	// 좌표, 방향, 돌의 개수
	private static int dfs(int y, int x, int d, int cnt) {
		if( !check(y, x) ) return 0;
		
		// 오목이면
		if(cnt == 5) {
			// 다음 칸이 범위를 벗어나거나 6목이 아니면
			if( !check(y + dy[d], x + dx[d]) ) return 5;
			else return 0;
		}
		
		return dfs(y + dy[d], x + dx[d], d, cnt + 1);
	}
	
	// 범위 안에 있고 같은색 돌이면 true
	private static boolean check(int y, int x) {
		if( 0 <= y && y < N && 0<= x && x < N && pan[y][x] == dol ) return true;
		return false;
	}
	
}
