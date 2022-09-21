import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9507_Generations_of_Tribbles {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());

		int max = 0;
		int[] arr = new int[t];
		for(int i = 0; i < t; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			max = max > arr[i] ? max : arr[i];
		}
		
		long[] dp = new long[max + 1];
		dp[0] = dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i <= max; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
		}

		for(int i = 0; i < t; i++) {
			System.out.println(dp[ arr[i] ]);
		}
	}

}
