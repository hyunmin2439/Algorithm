import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

// Memory:26,116KB / Time:308ms
public class BJ_12847_꿀_아르바이트 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		long[] arr = new long[n + 1], dp = new long[n + 1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i];
		}
		
		long max = 0;
		for(int i = m; i <= n; i++) {
			max = Math.max(max, dp[i] - dp[i - m]);
		}
		
		System.out.print(max);
	}

}
