package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP문제
 * 주어진 조건에 따라 Dynamic Programming 기법으로 구현만 하면 되는 문제
 * 
 * DP를 제대로 배우기 전에는 감이 안잡혔는데, DP를 배우고 나니 상당히 간단한 문제
 * 
 * 깊이 생각할 필요없이 주어진 조건에 따라 재귀가 아닌 반복문으로 구현만 하면 되는 문제
 */

public class BJ_9184_신나는함수실행_1_dp {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[][][] dp = new int[21][21][21];
		
		for (int a = 0; a < dp.length; a++) {
			for (int b = 0; b < dp.length; b++) {
				for (int c = 0; c < dp.length; c++) {
					if(a == 0 || b == 0 || c == 0) dp[a][b][c] = 1;
					else if(a < b && b < c) dp[a][b][c] = dp[a][b][c-1] + dp[a][b-1][c-1] - dp[a][b-1][c];
					else dp[a][b][c] = dp[a-1][b][c] + dp[a-1][b-1][c] + dp[a-1][b][c-1] - dp[a-1][b-1][c-1];
				}
			}
		}
		
		int result = 0;
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1 && c == -1) break; // 종료
			
			if(a <= 0 || b <= 0 || c <= 0) result = 1;
			else if(a > 20 || b > 20 || c > 20) result = dp[20][20][20];
			else result = dp[a][b][c];
			
			sb.append("w(").append(a).append(", ").append(b).append(", ")
			.append(c).append(") = ").append(result).append('\n');
		}
		
		System.out.print(sb);
		
		in.close();
	}

}
