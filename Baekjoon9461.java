package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// DP 문제
// int 배열을 사용하면 오버플로우
public class Baekjoon9461 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		long[] arr = new long[101];
		
		// 초기 값
		arr[1] = arr[2] = arr[3] = 1;
		arr[4] = arr[5] = 2;

		for (int i = 6; i <= 100; i++) {
			arr[i] = arr[i - 5] + arr[i - 1];
		}
		
		while(T-- > 0) {
			System.out.println(arr[Integer.parseInt(br.readLine())]);
		}
		
		br.close();
	}

}
