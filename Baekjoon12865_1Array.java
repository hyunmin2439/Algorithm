package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon12865_1Array {

	static int N, K;
	static int[][] prod;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		prod = new int[N + 1][2];
		dp = new int[K + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			prod[i][0] = w;
			prod[i][1] = v;
		}
		
		for (int i = 1; i <= N; i++) {
			// 1차원 배열 뒤쪽부터 채우면 됨.
			// 1차원 배열 안에 이전의 참조할 값과 현재의 값이 공존
			for (int w = K; w >= prod[i][0]; w--) {
				// 가방 최대 무게부터 물건의 무게 까지 돌기 때문에 무게 체크X
				dp[w] = Math.max(dp[w], dp[w - prod[i][0]] + prod[i][1] );
			}
		}
		
		System.out.println(dp[K]);
		in.close();
	}

}
