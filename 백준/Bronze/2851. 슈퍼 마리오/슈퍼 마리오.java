import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int tmp = 0, idx = 0, minDiff = 100, dp[] = new int[11];
		for(int i = 1; i <= 10; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(in.readLine());
			tmp = Math.abs(dp[i] - 100);
			if(tmp <= minDiff) {
				minDiff = tmp;
				idx = i;
			}
		}
		
		System.out.print(dp[idx]);
		
		in.close();
	}
}