import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = 0, T, N, K, weight[], price[], dp[];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			weight = new int[N];
			price = new int[N];
			dp = new int[K + 1];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				for(int w = K; w >= weight[i]; w--) {
					dp[w] = Math.max(dp[w], dp[w - weight[i]] + price[i]);
				}
			}
			
			sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

}