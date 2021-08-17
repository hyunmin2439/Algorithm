package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1342 {

	static int cnt;
	static char[] schar;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		schar = br.readLine().toCharArray();
		
		Arrays.sort(schar);
		
		do {
			if( check() ) cnt++;
		} while(np(schar));
		
		System.out.println(cnt);
		br.close();
	}

	private static boolean check() {
		
		for (int i = 0; i < schar.length - 1; i++) {
			// 인접한 두 글자가 같으면 false 리턴
			if(schar[i] == schar[i + 1]) return false;
		}
		
		return true;
	}

	// Next Permitation
	private static boolean np(char[] arr) {
		
		int i = arr.length - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		
		if(i == 0) return false;
		
		int j = arr.length - 1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(arr, i-1, j);
		
		int k = arr.length - 1;
		while(i < k) {
			swap(arr, i++, k--);
		}
		
		return true;
	}

	private static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
