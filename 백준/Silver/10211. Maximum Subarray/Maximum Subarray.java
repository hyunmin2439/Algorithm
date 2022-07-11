import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T, N, num, max, dp[];
		
		T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			
			max = Integer.MIN_VALUE;
			dp = new int[N + 1];
			
			for(int i = 1; i <= N; i++) {
				num = Integer.parseInt(st.nextToken());
				
				dp[i] = Math.max(num, dp[i - 1] + num);
				max = Math.max(max, dp[i]);
			}
			
			System.out.println(max);
		}
	}
}