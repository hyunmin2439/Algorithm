package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 순열 기본기 + counting 배열 사용
 * 
 * 푸는데 걸린 시간 13분
 * 
 * Memory:14280KB / Time:124ms
 */

public class Baekjoon1233 {

	static int[] count;
	static int[] dice;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		dice = new int[4];
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < 3; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			sum += dice[i];
		}
		
		count = new int[sum + 1];
		
		perm(0, 0);
		
		int ans = 0, maxCnt = 0;
		for (int i = 3; i <= sum; i++) {
			if(count[i] > maxCnt) {
				ans = i;
				maxCnt = count[i];
			}
		}
		
		System.out.println(ans);
		
		in.close();
	}

	private static void perm(int idx, int sum) {
		if(idx == 3) {
			count[sum]++;
			return;
		}
		
		for(int i = 1; i <= dice[idx]; i++) {
			perm(idx + 1, sum + i);
		}
	}

}
