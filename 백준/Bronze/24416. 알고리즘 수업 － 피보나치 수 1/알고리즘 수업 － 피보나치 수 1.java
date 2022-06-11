import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	private static int fib1Cnt, fib2Cnt;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		
		fib1(n);
		fib2(n);
		
		System.out.println(fib1Cnt + " " + fib2Cnt);
	}
	
	private static int fib1(int n) {
		if(n == 1 || n == 2) {
			fib1Cnt++;
			return 1;
		}
		
		return fib1(n - 1) + fib1(n - 2);
	}
	
	private static void fib2(int n) {
		int[] dp = new int[n + 1];
		dp[1] = dp[2] = 1;
		
		for(int i = 3; i <= n; i++) {
			fib2Cnt++;
			dp[i] = dp[i - 1] + dp[i - 2];
		}
	}

}
