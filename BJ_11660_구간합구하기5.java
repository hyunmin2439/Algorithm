package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21 
 * 7  8  9     12 27 45
 * 
 * 12 = 1 + 2 + 4 + 5
 * 
 * 27 = 1 + 2 + 4 + 5 + 7 + 8
 * 
 * 각 dp[x][y]는 전부 더해진 구간합
 * 
 * dp[x][y] = arr[y][x] + dp[x - 1][y] + dp[x][y - 1] + dp[x - 1][y - 1]
 * 
 * dp[x - 1[y - 1]까지의 값이 두번 더해졌으므로 한번 빼준다.
 * 
 * 이런 원리로 아래처럼 W 값에서 X의 위치를 빼준다. 
 * 
 *  ㅡㅡㅡ
 * ㅣCXX
 * ㅣXOO
 * ㅣXOW
 * 
 * 이 때 가로 X, 세로 X 두번 빼주기 때문에 C부분이 겹쳐서 빼진다. 
 * 
 * 이 부분만 다시 추가하면 되는 것.
 * 
 * Memory:125,136KB / Time:1,672ms
 */

public class Bj_11660_구간합구하기5 {

	private static int N, M;
	
	public static void main(String[] args) throws Exception {
		solve();
	}
	
	private static void solve() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][N + 1];
				
		for(int x = 1; x <= N; x++) {
			st = new StringTokenizer(in.readLine());
			for(int y = 1; y <= N; y++) {
				dp[x][y] = Integer.parseInt(st.nextToken()) + dp[x - 1][y] + dp[x][y - 1] - dp[x - 1][y - 1];
			}
		}
		
		int sx, sy, ex, ey;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());			
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[ex][ey] - dp[sx - 1][ey] - dp[ex][sy - 1] + dp[sx - 1][sy - 1]);
		}
		
		in.close();
	}
}
