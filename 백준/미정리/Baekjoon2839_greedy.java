package solved.submit;

import java.io.IOException;
import java.util.Scanner;

public class Baekjoon2839_greedy {

	static int N, threeCnt; // 3을 사용하는 횟수
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // (3 ≤ N ≤ 5000)

		while(true) {
			
			// 불가능한 경우
			if( N < 0 ) {
				System.out.println(-1);
				break;
			}
			
			if( N % 5 == 0 ) {
				System.out.println(N / 5 + threeCnt);
				break;
			}
			
			N -= 3;
			threeCnt++;
		}

		sc.close();
	}

}
