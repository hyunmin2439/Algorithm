package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// N
// 1 => 1
// 2 => 00 11
// 3 => 100 001 111
// 4 => 0000 0011 1001 1100 1111
// 5 => 00001 00100 10000 00111 10011 11001 11100 11111
// ...

// f(1) = 1  f(2) = 2
// if(N > 2) f(N) = f(N - 1) + f(N - 2) 

public class Baekjoon1904 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N < 3) {
			System.out.println(N == 1 ? 1 : 2);
			return;
		}
		
		int[] fibo = new int[3];
		
		fibo[0] = 1; fibo[1] = 2;
		
		for (int i = 3; i <= N; i++) {
			fibo[2] = (fibo[0] + fibo[1]) % 15746;
			fibo[0] = fibo[1];
			fibo[1] = fibo[2];
		}
		
		System.out.println(fibo[2]);
		
		br.close();
	}

}
