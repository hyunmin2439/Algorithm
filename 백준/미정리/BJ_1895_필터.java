package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Memory:14964KB / Time:144ms
 */
public class BJ_1895_필터 {

	private static int R, C, T;
	private static int[][] image;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		image = new int[R][C];
		
		for(int y = 0; y < R; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < C; x++) {
				image[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		T = Integer.parseInt(in.readLine());
		
		in.close();
	}

	private static int solve() {
		// y, x 각각 시작, 끝 좌표
		int sy = -1, sx = -1, ey = 0, ex = 0, res = 0;
		int[] filterValues = new int[9];
		
		while(++sy <= R - 3) {
			sx = -1;
			ey = sy + 3;
			
			while(++sx <= C - 3) {
				ex = sx + 3;
				
				// 중간 값을 찾기 위해 각 값 배열에 넣음
				int i = 0;
				for(int y = sy; y < ey; y++) {
					for(int x = sx; x < ex; x++) {
						filterValues[i++] = image[y][x];
					}
				}
				
				// 정렬하고 중간 값이 T보다 크면 ++
				Arrays.sort(filterValues);
				if(filterValues[4] >= T) res++;
			}
		}
		
		return res;
	}
}
