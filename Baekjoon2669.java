package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사각형의 면적 합 구하기

public class Baekjoon2669 {

	static int res;
	static boolean[][] map = new boolean[101][101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			fill( Integer.parseInt(st.nextToken()),
				  Integer.parseInt(st.nextToken()),
				  Integer.parseInt(st.nextToken()),
				  Integer.parseInt(st.nextToken()) );		
		}
		count();
		System.out.println(res);
		br.close();
	}
	
	private static void fill(int sx, int sy, int ex, int ey) {
		for (int y = sy; y < ey; y++) {
			for (int x = sx; x < ex; x++) {
				map[y][x] = true;
			}
		}
	}
	
	private static void count() {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				if(map[y][x]) res++;
			}
		}
	}
}
