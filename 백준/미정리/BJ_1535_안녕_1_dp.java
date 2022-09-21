import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp : 0-1 Knapsack문제과 동일한 문제
 * 
 * Memory : 14244KB / Time : 136ms
 */

public class BJ_1535_안녕_1_dp {

	static int N;
	
	static int[] losingEnergy, joy;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		// init / 0 : dummy
		losingEnergy = new int[N + 1];
		joy = new int[N + 1];
		dp = new int[N + 1][101];  
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			losingEnergy[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			joy[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {	
			// j는 잃는 체력을 나타냄, 체력이 0이나 음수가 되면 기쁨 0
			for(int j = 1; j <= 99; j++) { 
				if(j < losingEnergy[i]) 
					dp[i][j] = dp[i - 1][j];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][ j - losingEnergy[i] ] + joy[i]);
			}
		}
		
		System.out.println(dp[N][99]);
		in.close();
	}

}
