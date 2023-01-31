import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Set<String> set = new HashSet<>();
		int t = 0, T, N, M, ans;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {	
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			set.clear();
			ans = N + M;
			
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					set.add(st.nextToken());
				}
				N = M;
			}
			
			ans -= set.size();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}