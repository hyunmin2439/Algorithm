package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon3273 {

	// 수열의 크기, 조건 X값, 결과값
	static int N, X, cnt;
	static int[] arr; // 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		X = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		twoPointer();
		
		System.out.println(cnt);
		br.close();
	}

	private static void twoPointer() {
		int i = 0, j = N - 1;
		
		while(i < j) {
			int add = arr[i] + arr[j];
			
			if(add == X) {
				cnt++;
				i++;
			}
			else if(add < X) i++;
			else j--;
		}
	}
}