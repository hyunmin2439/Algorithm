package solved.submit;

import java.io.IOException;
import java.util.Scanner;

// 완탐에 가까운 풀이 방법
public class Baekjoon2839_recur {

	static int N, min;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // (3 ≤ N ≤ 5000)
		min = 5000; // 충분히 큰 값
		
		comb(0, 0); // 첫번째 : 5를 사용하는 횟수(5kg 봉지의 수), 두번째 3을 사용하는 횟수(3kg 봉지의 수)
		min = min == 5000 ? -1 : min;
		
		System.out.println(min);
		sc.close();
	}

	private static void comb(int five, int three) {
		int sum = five * 5 + three * 3; 
		
		// 기저 조건
		if( sum == N ) {
			min = Math.min( min, five + three);
			return;
		}
		
		if( sum > N ) return;
		
		comb(five + 1, three);
		comb(five, three + 1); 
		
	}
}
