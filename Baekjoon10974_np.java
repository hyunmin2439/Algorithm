package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 순열 기본기 복습 문제 풀이 Next Permutation 
 * 
 * Memory:52788KB / Time:2072ms
 */

public class Baekjoon10974_np {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		
		do {
			
			for(int i = 0; i < N; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			
		} while(np());
		
		in.close();
	}

	private static boolean np() {
		int i = N - 1;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = N - 1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(i-1, j);
		
		int k = N - 1;
		while(i < k) 
			swap(i++, k--);
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
