import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int N, M, a, b, dp[];

		N = Integer.parseInt(in.readLine());
		dp = new int[N + 1];
		
		st = new StringTokenizer(in.readLine());		
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(in.readLine());
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(dp[b] - dp[a - 1]).append('\n');
		}
		
		System.out.print(sb);
		
		in.close();		
	}

}