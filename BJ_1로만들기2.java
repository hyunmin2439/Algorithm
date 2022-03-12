package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_1로만들기2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		in.close();
		
		if(N <= 3) {
			System.out.print(N == 1 ? 0 : 1);
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
		
		// 가는 방법을 출력
		// dp를 굳이 안쓰고 bfs방식으로 써도 될듯
	}

	class Node {
		
	}
}
