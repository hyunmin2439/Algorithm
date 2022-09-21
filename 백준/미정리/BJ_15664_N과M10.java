package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Memory:16360KB / Time:164ms
 */
public class BJ_15664_N과M10 {

	private static int N, M, number[];
	private static Set<String> set;
	
	public static void main(String[] args) throws Exception {
		input();

		solve();
		
		output();
	}
	
	private static void solve() {
		Arrays.sort(number);
		
		comb(0, 0, "");
	}
	
	private static void comb(int start, int idx, String ans) {
		if(idx == M) {
			set.add(ans.trim());
			return;
		}
		
		for(int i = start; i < N; i++) {
			comb(i + 1, idx + 1, ans + number[i] + " ");
		}
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		set = new LinkedHashSet<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
	}
	
	private static void output() {
		set.forEach(System.out::println);
	}

}
