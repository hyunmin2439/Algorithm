package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14,592KB / Time:156ms
public class BJ_1965_상자넣기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int N = Integer.parseInt(in.readLine()); 
		int[] box = new int[N], dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i] = 1;
			for(int j = i - 1; j >= 0; j--) {
				if(box[i] > box[j])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.print(max);
		
		in.close();
	}

}
