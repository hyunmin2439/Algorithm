package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {

	private static int N, M;
	
	private static int[][][] tetromino = {
			{ {0, 0}, {0, 1}, {0, 2}, {0, 3} }, // ㅡ
			{ {0, 0}, {1, 0}, {2, 0}, {3, 0} }, // l
			
			{ {0, 0}, {0, 1}, {1, 0}, {1, 1} }, // ㅁ
			
			{ {0, 0}, {1, 0}, {2, 0}, {2, 1} }, // └
			{ {0, 0}, {1, 0}, {2, 0}, {2, -1} }, // ┘
			
			{ {0, 0}, {1, 0}, {0, 1}, {0, 2} }, // ┌
			{ {0, 0}, {1, 0}, {0, -1}, {0, -2} }, // ┐
			
			{ {0, 0}, {0, 1}, {1, 1}, {2, 1} }, // ┐
			{ {0, 0}, {0, -1}, {1, -1}, {2, -1} }, // ┌
			
			{ {0, 0}, {1, 0}, {1, -1}, {1, -2} }, // ┘
			{ {0, 0}, {1, 0}, {1, 1}, {1, 2} }, // └
			
			{ {0, 0}, {1, 0}, {1, 1}, {2, 1} }, // └┐
			{ {0, 0}, {0, 1}, {-1, 1}, {-1, 2} }, // 회전
			
			{ {0, 0}, {1, 0}, {1, -1}, {2, -1} }, // ┌┘
			{ {0, 0}, {0, 1}, {1, 1}, {1, 2} }, // 회전
			
			{ {0, 0}, {1, -1}, {1, 0}, {1, 1} }, // ┴
			{ {0, 0}, {1, 0}, {1, 1}, {2, 0} }, // ├
			{ {0, -1}, {0, 0}, {0, 1}, {1, 0} }, // ┬
			{ {0, 0}, {1, 0}, {1, -1}, {2, 0} }, // ┤
	};
	
	public static void main(String[] args) throws Exception {
		int[][] map = input();
		
		solve(map);
	}
	
	private static void solve(int[][] map) {
		int ny, nx, sum = 0, max = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {

				for(int i = 0; i < tetromino.length; i++) {
					sum = 0;
					
					for(int d = 0; d < 4; d++) {
						ny = y + tetromino[i][d][0];
						nx = x + tetromino[i][d][1];
						
						if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) break;
						
						sum += map[ny][nx];
					}
					
					max = Math.max(max, sum);
				}
			}
		}
		
		System.out.print(max);
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
		
		return map;
	}

}
