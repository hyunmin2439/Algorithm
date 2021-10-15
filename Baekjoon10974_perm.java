package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 순열 기본기 복습 문제 풀이 
 * 
 * Memory:62432KB / Time:2092ms
 */

public class Baekjoon10974_perm {

	static int N;
	static int[] arr;
	static boolean[] used;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		arr = new int[N + 1];
		used = new boolean[N + 1];
		
		perm(1);
		
		in.close();
	}

	private static void perm(int idx) {
		
		if(idx == N + 1) {
			for (int i = 1; i <= N; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(used[i]) continue;
			
			arr[idx] = i;
			used[i] = true;
			perm(idx + 1);
			used[i] = false;
		}
	}

}
