import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		boolean[][] isBlocked = input();
		// 0:가로 / 1:세로 / 2:대각선
		long[][][] dp = new long[N][N][3]; 
		
		// y:0 부분에는 가로파이프만 놓일 수 있음. 가로 파이프 1로 초기화
		for(int x = 1; x < N; x++) {
			if( isBlocked[0][x] ) break;
			dp[0][x][0] = 1;
		}
		
		for(int y = 1; y < N; y++) {
			// 모든 파이프는 2부터 위치 가능
			for(int x = 2; x < N; x++) {
				// 벽이 있으면 [y, x-1], [y-1, x], [y-1, x-1] 모두 0이기 때문에 
				// 이부분에 대한 막혀있는지 여부를 판단할 필요없이 현재 위치만 판단하면 된다.
				if( !isBlocked[y][x] ) {					
					dp[y][x][0] = dp[y][x - 1][0] + dp[y][x - 1][2]; // 가로
					dp[y][x][1] = dp[y - 1][x][1] + dp[y - 1][x][2]; // 세로
					if( !(isBlocked[y - 1][x] || isBlocked[y][x - 1]) ) // 대각선
						dp[y][x][2] = dp[y - 1][x - 1][0] + dp[y - 1][x - 1][1] + dp[y - 1][x - 1][2];
				}
			}
		}
		
		System.out.print(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
	
	private static boolean[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());
		boolean[][] isBlocked = new boolean[N][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				if("1".equals(st.nextToken()))
					isBlocked[y][x] = true;
			}
		}
		
		in.close();
		
		return isBlocked;
	}
}