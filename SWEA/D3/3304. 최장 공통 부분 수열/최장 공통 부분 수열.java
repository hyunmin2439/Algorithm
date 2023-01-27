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
		
		char[] word1, word2;
		int t = 0, T, dp[][];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());			
			word1 = st.nextToken().toCharArray();
			word2 = st.nextToken().toCharArray();
			dp = new int[word1.length + 1][word2.length + 1];
			
			for(int i = 1; i <= word1.length; i++) {
				for(int j = 1; j <= word2.length; j++) {
					if(word1[i - 1] == word2[j - 1])
						dp[i][j] = dp[i - 1][j - 1] + 1;
					else
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
						
				}
			}
			
			sb.append("#").append(t).append(" ").append(dp[word1.length][word2.length]).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

}