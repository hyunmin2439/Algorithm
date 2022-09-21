package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_4796_캠핑 {

	public static void main(String[] args) throws Exception {
		solve();
	}
	
	private static void solve() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int L, P, V, day, idx = 0;
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			
			idx++;
			L = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			if(L == 0) break;
			
			day = getDateOfUse(L, P, V);
			sb.append("Case ").append(idx).append(": ").append(day).append("\n");
		}
		
		in.close();
		
		System.out.print(sb);
	}
	
	private static int getDateOfUse(int L, int P, int V) {
		return V / P * L + Math.min(V % P, L);
	}

}
