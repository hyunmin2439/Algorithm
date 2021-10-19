package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Memory:14024KB / Memory:120ms
public class Baekjoon9095 {
	
	static int N, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			N = Integer.parseInt(in.readLine());
			
			ans = 0;
			perm(0);
			
			System.out.println(ans);
		}
		
		in.close();
	}
	
	private static void perm(int sum) {
		if(sum > N) return;
		
		if(sum == N) {
			ans++;
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			perm(sum + i);
		}
	}
	
}
