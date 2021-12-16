import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9655_돌게임 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
//		DP 풀이 방법
		
//		int N = Integer.parseInt(in.readLine());
//		
//		if( N < 3 ) {
//			System.out.print( N == 1 ? "SK" : "CY" );
//			return;
//		}
//		
//		boolean[] dp = new boolean[N + 1]; // true: 상근, false: 창영
//		dp[1] = true;
//		dp[2] = false;
//		dp[3] = true;
//		
//		for(int i = 4; i <= N; i++) {
//			dp[i] = dp[i - 1] && dp[i - 3] ? false : true;
//		}
//		
//		System.out.print( dp[N] ? "SK" : "CY" );

		// 매우 간단한 풀이방법
		System.out.print( Integer.parseInt(in.readLine()) % 2 != 0 ? "SK" : "CY" );
		
		// dp 결과값을 확인 해보니 홀수 일땐 상근의 승리 짝수일땐 창영의 승리
		
		in.close();
	}

}
