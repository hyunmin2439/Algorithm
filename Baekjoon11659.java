import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:53852KB / Time:676ms
public class Baekjoon11659 {

	static int N, M;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1]; // 0:dummy

		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken()) + dp[i - 1];
		}
		
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(in.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int sum = dp[j] - dp[i - 1];
			
			sb.append(sum).append('\n');
		}
		
		System.out.println(sb);
		in.close();
	}

}
