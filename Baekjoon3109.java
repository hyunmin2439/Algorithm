package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon3109 {

	static int R, C, cnt;
	static boolean[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		
		for (int y = 0; y < R; y++) {
			String line = br.readLine();
			for (int x = 0; x < C; x++) {
				if(line.charAt(x) == 'x')
					map[y][x] = true;
			}
		}
		
		for (int y = 0; y < R; y++) {			
			if( findPipeLine(y, 0) ) cnt++;
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	static int[] dy = { -1, 0, 1 };
	
	private static boolean findPipeLine(int y, int x) {
		int nx = x + 1;
		
		// 기저 조건
		if(nx >= C - 1) return true;

		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			
			if( !(0 <= ny && ny < R) || map[ny][nx] ) continue;
			
			map[ny][nx] = true; // 현재 길 사용
			
			if( findPipeLine(ny, nx) ) return true;
		}
		
		return false;
	}
	
}