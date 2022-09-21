package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory: 35,064KB / Time: 496ms
 */
public class BJ_20057_마법사_상어와_토네이도 {

	private static int N;
	
	//						   좌, 하, 우, 상
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[] dx = { -1, 0, 1, 0 };
	
	private static int[][][] direc = {
			{ {0, -2, 5}, {-1, -1, 10}, {1, -1, 10}, 
				{-2, 0, 2}, {-1, 0, 7}, {1, 0, 7}, {2, 0, 2}, 
				{-1, 1, 1}, {1, 1, 1} }, // 좌
			
			{ {2, 0, 5}, {1, 1, 10}, {1, -1, 10}, 
					{0, 2, 2}, {0, 1, 7}, {0, -1, 7}, {0, -2, 2}, 
					{-1, 1, 1}, {-1, -1, 1} }, // 하
			
			{ {0, 2, 5}, {1, 1, 10}, {-1, 1, 10}, 
				{2, 0, 2}, {1, 0, 7}, {-1, 0, 7}, {-2, 0, 2}, 
				{1, -1, 1}, {-1, -1, 1} }, // 우
			
			{ {-2, 0, 5}, {-1, -1, 10}, {-1, 1, 10}, 
				{0, -2, 2}, {0, -1, 7}, {0, 1, 7}, {0, 2, 2}, 
				{1, -1, 1}, {1, 1, 1} } // 상
	};
	
	public static void main(String[] args) throws Exception {
		int[][] map = input();

		int answer = solve(map);
		
		System.out.print(answer);
	}
	
	private static int solve(int[][] map) {
		int mid = N / 2, answer = 0;
		
		int ny = mid, nx = mid, d = 0, len = 1;
		while(true) {
			for(int i = 0; i < len; i++) {
				ny += dy[d];
				nx += dx[d];
				
				if( ! (0 <= ny && ny < N && 0 <= nx && nx < N) ) return answer;

				// 모래 흩날리기
				answer += moveSand(map, ny, nx, d);
			}
			
			d = ++d > 3 ? 0 : d;
			
			len = d % 2 == 0 ? len + 1 : len;
		}
	}
	
	private static int moveSand(int[][] map, int y, int x, int d) {
		int ny, nx, sand, sum = 0, outSum = 0;
		double ratio;
		
		for(int i = 0; i < direc[d].length; i++) {
			ny = y + direc[d][i][0];
			nx = x + direc[d][i][1];
			ratio = direc[d][i][2] * 0.01;
			
			sand = (int)(map[y][x] * ratio); // 흩날리는 모래량
			sum += sand;
			
			// 바깥으로 나간 모래량
			if( ! (0 <= ny && ny < N && 0 <= nx && nx < N) )
				outSum += sand;
			else 
				map[ny][nx] += sand;
		}
		
		ny = y + dy[d];
		nx = x + dx[d];
		sand = map[y][x] - sum;
		
		// 바깥으로 나간 모래량
		if( ! (0 <= ny && ny < N && 0 <= nx && nx < N) )
			outSum += sand;
		
		else 
			map[ny][nx] += sand;
		
		return outSum;
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		
		int[][] map = new int[N][N];
		
		StringTokenizer st = null;
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
		
		return map;
	}

}
