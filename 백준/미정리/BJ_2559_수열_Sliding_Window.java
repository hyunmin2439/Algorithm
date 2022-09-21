import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2559_수열_Sliding_Window {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int end = N - K + 1, max = Integer.MIN_VALUE;
		int[] dp = new int[end];
		for(int i = 0; i < K; i++) {
			dp[0] += arr[i];
		}

		max = Math.max(max, dp[0]);
		
		for(int i = 1; i < end; i++) {
			dp[i] = dp[i - 1] - arr[i - 1] + arr[i + K - 1];
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
		
		in.close();
	}

}
