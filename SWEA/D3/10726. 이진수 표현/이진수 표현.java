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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int t = 0, T, N, M;
		boolean check;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			check = true;
			
			for(int i = 0; i < N; i++) {
				if((M & 1 << i) != 0) continue;
				
				check = false;
				break;
			}
			
			sb.append('#').append(t).append(' ').append(check ? "ON" : "OFF").append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}

}