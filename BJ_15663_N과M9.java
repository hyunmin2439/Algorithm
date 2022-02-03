package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * TreeSet 사용 시 아래와 같은 예제 잘못 정렬
 * 
 * 3 1
 * 1 19 2
 * 
 * 숫자 순대로 정렬해서 출력하기 위해 number를 입력받고 정렬하고 사용
 * 
 * 넣은 순서대로 정렬하기 위해서 LikedHashSet 사용
 * 
 * Memory:31856KB / Time:556ms
 */

public class BJ_15663_N과M9 {

	private static int N, M;
	private static int[] number;
	private static Set<String> set;
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
		
		output();
	}
	
	private static void solve() {
		Arrays.sort(number);
		
		comb(new boolean[N], 0, "");
	}
	
	private static void comb(boolean[] isUsed, int idx, String ans) {
		if(idx == M) {
			set.add( ans );
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(isUsed[i]) continue;
			
			isUsed[i] = true;
			comb(isUsed, idx + 1, ans + number[i] + " ");
			isUsed[i] = false;
		}
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		number = new int[N];
		set = new LinkedHashSet<>(); // 넣은 순서대로 저장
		
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
