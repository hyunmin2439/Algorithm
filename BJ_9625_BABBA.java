import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9625_BABBA {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(in.readLine());
		
		int[][] dp = new int[K + 1][2];
		
		dp[0][0] = 1; // A
		dp[0][1] = 0; // B
		
		for(int i = 1; i <= K; i++) {
			dp[i][0] = dp[i - 1][1];
			dp[i][1] = dp[i - 1][0] + dp[i - 1][1];
		}
		
		System.out.println(dp[K][0] + " " + dp[K][1]);
		
		in.close();
	}

}
