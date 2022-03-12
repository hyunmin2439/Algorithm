import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1로만들기2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		in.close();

		if(N <= 3) {
		    if(N == 1) System.out.print(0 + "\n" + 1);
			else System.out.print(1 + "\n" + N + " " + 1);
			return;
		}

		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		dp[2] = dp[3] = 1;

		for(int X = 4; X <= N; X++) {
			if(X % 3 == 0) dp[X] = Math.min(dp[X], dp[X/3] + 1);
			if(X % 2 == 0) dp[X] = Math.min(dp[X], dp[X/2] + 1);
			dp[X] = Math.min(dp[X], dp[X - 1] + 1);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dp[N]).append('\n').append(N).append(' ');

		int cnt = dp[N];
		while(cnt > 0) {
		    if(N % 3== 0 && dp[N / 3] == cnt - 1) {
			sb.append(N / 3).append(' ');
			cnt--;
			N /= 3;
		    }
		    else if(N % 2== 0 && dp[N / 2] == cnt - 1) {
			sb.append(N / 2).append(' ');
			cnt--;
			N /= 2;
		    }
		    else if(dp[N - 1] == cnt - 1) {
			sb.append(N - 1).append(' ');
			cnt--;
			N--;
		    }
		}

		System.out.print(sb);
	}
}
