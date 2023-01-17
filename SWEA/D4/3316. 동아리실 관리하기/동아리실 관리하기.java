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
		int t = 0, T, answer, charger, mod = 1_000_000_007, dp[][];
		char[] N;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = st.nextToken().toCharArray();
			dp = new int[N.length][16];
			answer = 0;
			
			// 첫날
			for(int set = 1; set < 16; set++) {
				charger = 1 << (N[0] - 'A');
				if((1 & set) == 0 || (charger & set) == 0) continue;
				dp[0][set] = 1;
			}
			
			// 둘째날 이후
			for(int day = 1; day < N.length; day++) {
				charger = 1 << (N[day] - 'A'); // 오늘의 책임자
				
				for(int prev = 1; prev < 16; prev++) {
					if(dp[day - 1][prev] == 0) continue;
					
					for(int set = 1; set < 16; set++) {
						// 이전날 참가자가 없거나 || 오늘 책임자가 없으면
						if((prev & set) == 0 || (charger & set) == 0) continue;
						dp[day][set] = (dp[day][set] + dp[day - 1][prev]) % mod;
					}
				}
			}
			
			for(int i = 1; i < 16; i++) {
				answer = (answer + dp[N.length - 1][i]) % mod;
			}
			
			out.write("#" + t + " " + answer + "\n");
		}
		
		out.flush();
	}
}