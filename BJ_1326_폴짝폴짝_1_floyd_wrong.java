package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 메모리 초과
 * 
 * 음수쪽으로 이동할 수 있다는 것도 간과
 */
public class BJ_1326_폴짝폴짝_1_floyd_wrong {

	private static final int INF = 1_000_000_001;
	private static int N, start, end, bridge[][];
	
	public static void main(String[] args) throws Exception {		
		input();
		
		solve();
		
		System.out.print(bridge[start][end] >= INF ? -1 : bridge[start][end]);
	}

	private static void solve() {
		// 초기 값 세팅
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				
				// 배수이면 갈 수 있는 곳
				if( j % bridge[0][i] == 0 )
					bridge[i][j] = 1;
			}
		}
		
		// 개구리는 앞으로만 갈 수 있다.
		// 때문에 i < k < j 조건을 만족해야 한다.
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(i >= k) continue;
				
				for(int j = 1; j <= N; j++) {
					if(k >= j || i >= j) continue;
					
					bridge[i][j] = Math.min(bridge[i][j], bridge[i][k] + bridge[k][j]);
				}
			}
		}
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		bridge = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(bridge[i], INF);
		}
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			bridge[0][i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		in.close();
	}

}
