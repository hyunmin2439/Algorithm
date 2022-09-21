package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 순열 기본기 복습 문제 풀이 
 * 
 * 공간, 시간 복잡도 낮춤 
 * 
 * Memory:38328KB / Time:912ms
 */

public class BJ_10974_모든순열_3_perm2 {

	static int N;
	static boolean[] used;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		used = new boolean[N + 1];
		
		perm(1, "");
		
		in.close();
	}

	private static void perm(int idx, String ans) {
		
		if(idx == N + 1) {
			System.out.println(ans.substring(1));
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(used[i]) continue;
			
			used[i] = true;
			perm(idx + 1, ans + " " + i);
			used[i] = false;
		}
	}

}
