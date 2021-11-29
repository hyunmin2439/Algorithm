import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Dynamic Programming
 * 
 * dp[] : 현재 인덱스의 값을 마지락으로 하는 가장 큰 연속합
 * 
 * Memory:23948KB / Time:272ms
 */

public class BJ_1912_연속합 {

	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		// 첫 숫자
		int num = Integer.parseInt(st.nextToken());
		dp[0] = num;
		
		// 이후 숫자
		for(int i = 1; i < N; i++) {
			num = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(num, dp[i - 1] + num);
		}
		
		// 최대값 찾기
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
		
		in.close();
	}

}
