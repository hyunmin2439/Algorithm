package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Sliding Window 기법
 * 
 * idx - K번째 초밥을 빼고 현재 idx 초밥을 새로 넣어야 한다.
 * 
 * 이때 count 배열을 감소시키고 0이 되면 Set에서 빼기
 * 
 * 이후 idx 초밥을 Set에 넣고 count 배열 증가.
 * 
 * Memory:310408KB / Time:848ms
 */

public class BJ_15961_회전초밥 {

	// 접시 수, 초밥 가지 수, 연속해서 먹는 접시 수, 쿠폰 번호
	private static int N, D, K, C, res, sushi[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
		
		System.out.print(res);
	}
	
	private static void solve() {
		Set<Integer> set = new HashSet<>();
		
		int i = 0, end = N + K - 1;
		int[] count = new int[D + 1];
		
		// 초기
		set.add(C); // 쿠폰 초밥
		
		for(i = 0; i < K; i++) {
			set.add(sushi[i]);
			count[sushi[i]]++;
		}
		
		res = Math.max(res, set.size());
		
		
		for( ; i < end; i++) {
			
			// 이전 초밥 빼기 
			if(sushi[i - K] != C) {
				count[sushi[i - K]]--;
				
				if(count[sushi[i - K]] == 0) set.remove(sushi[i - K]);
			}
			
			// 새로운 초밥 넣기
			set.add(sushi[i]);
			count[sushi[i]]++;
			
			res = Math.max(res, set.size());
		}
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		sushi = new int [N + K - 1];
		
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(in.readLine());
		}
		
		for(int i = 0; i < K - 1; i++) {
			sushi[N + i] = sushi[i];
		}
		
		in.close();
	}

}
