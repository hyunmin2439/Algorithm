package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Next Permutation을 알고리즘을 적용하기 아주 적합한 문제
 * 
 * Memory: 19348KB / Time: 380ms
 */

public class BJ_10972_다음순열 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N  = Integer.parseInt(in.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if( np(arr, N) ) {
			for (int i = 0; i < N; i++) {
				System.out.print(arr[i] + " ");
			}
		}
		else System.out.print(-1);
		
		in.close();
	}
	
	private static boolean np(int[] arr, int N) {
		int i = N - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = N - 1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(arr, i - 1, j);
		
		int k = N - 1;
		while(i < k)
			swap(arr, i++, k--);
		
		return true;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
