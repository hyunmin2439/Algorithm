package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1964 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.valueOf(br.readLine());
		
		long curr = (N == 1) ? 5 : 7; // 현재 더해야 할 숫자
		long sum = (N == 1) ? 5 : 12;
		for (int i = 0; i < N - 2; i++) {
			curr += 3;
			sum = (sum + curr) % 45678;
		}
		
		System.out.println(sum);
		br.close();
	}

}
