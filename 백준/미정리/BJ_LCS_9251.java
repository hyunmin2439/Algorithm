import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Memory:18,308KB / Time:148ms
 */
public class BJ_LCS_9251 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] A = in.readLine().toCharArray();
		char[] B = in.readLine().toCharArray();
		
		int[][] dp = new int[A.length + 1][B.length + 1];
		
		// DP LCS 길이 구하기
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= B.length; j++) {
				if(A[i - 1] == B[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= B.length; j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}
		
		// DP LCS 길이 통해서 루트 추적
//		int i = A.length, j = B.length, idx = dp[i][j] - 1;
//		
//		if(idx <= 0) {
//			System.out.print("");
//			return;
//		}
//		
//		char[] answer = new char[ dp[i][j] ];
//		while(i > 0 && j > 0) {
//			if(dp[i - 1][j] == dp[i][j - 1]) {
//				answer[idx] = A[i - 1];
//				i--; j--; idx--;
//			}
//			else if(dp[i - 1][j] > dp[i][j - 1]) 
//				i--;
//			else 
//				j--;
//		}
		
		System.out.print(dp[A.length][B.length]);
		
		in.close();
	}

}
