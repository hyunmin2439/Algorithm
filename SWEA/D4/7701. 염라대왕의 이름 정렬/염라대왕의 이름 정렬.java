import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		TreeSet<String> set = new TreeSet<>( (a, b) ->  {
			if(a.length() != b.length())
				return a.length() - b.length();
			return a.compareTo(b);
		});
		
		int t = 0, T, N;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			
			for(int i = 0; i < N; i++) {
				set.add(in.readLine());
			}
			
			sb.append("#").append(t).append("\n");
			while(!set.isEmpty()) {
				sb.append(set.pollFirst()).append("\n");
			}
		}
		
		out.write(sb.toString());
		out.flush();
	}

}