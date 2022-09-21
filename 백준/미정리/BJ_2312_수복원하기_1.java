package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 시간이 오래 걸림 다른 사람 코드 참조해볼 것
 * 
 * Memory : 126688KB / Time:316ms
 */

public class BJ_2312_수복원하기_1 {

	private static final int MAX_NUM = 100_001;
	private static boolean[] isNotPrimeNum = new boolean[MAX_NUM];
	private static int T, num[];
	
	public static void main(String[] args) throws Exception {
		input();
		
		primeNumSieve();
		
		for(int i = 0; i < T; i++) {
			solve(num[i]);			
		}
	}
	
	private static void primeNumSieve() {
		// 2의 배수들 다 소수가 아님
		for(int i = 4; i < MAX_NUM; i += 2) {
			isNotPrimeNum[i] = true;
		}
		
		// 3부터 홀수의 배수들 다 소수가 아님
		for(int i = 3; i < MAX_NUM; i += 2) {
			// i의 배수 부터 시작
			for(int j = i * 2; j < MAX_NUM; j += i) {
				isNotPrimeNum[j] = true;
			}
		}
	}
	
	private static void solve(int num) {
		int divisor = 2;
		int[] count = new int[MAX_NUM];
		
		while(num > 1) {
			if(num % divisor == 0) {
				count[divisor]++;
				num /= divisor;
			}
			else
				while( isNotPrimeNum[++divisor] ); // 다음 소수
		}
		
		if(count[2] > 0) 
			System.out.println(2 + " " + count[2]);
		
		for(int i = 3; i < MAX_NUM; i += 2) {
			if(count[i] > 0)
				System.out.println(i + " " + count[i]);
		}
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		num = new int[T];
		for(int i = 0; i < T; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		
		br.close();
	}

}
