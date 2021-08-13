package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// bitMasking && Next Permutation
public class Baekjoon3040 {
	
	static int N = 9, R = 7, flag, end;
	static int[] input = new int[N];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		// flag init
		flag = (1 << R) - 1;
		end = 1 << N;
		
		// Next Permutation
		do {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if( (flag & 1 << i) != 0 ) 
					sum += input[i];
			}
			
			if(sum == 100) break;
			
		}while(comb());
		
		for (int i = 0; i < N; i++) {
			if( (flag & 1 << i) != 0)
				System.out.println(input[i]);
		}
		
		br.close();
	}

	private static boolean comb() {
		
		int i = 1; // bit 연산이기 때문에 1
		// i가 N이 아니고 바로 앞 숫자보다 크거나 같으면 bit 앞으로 옮기기
		while( i < end && (flag & i << 1) >= (flag & i) ) i <<= 1;
		
		// 모든 조합 경우를 다 뽑았을 경우
		if(i >= end) return false;
		
		int j = 1;
		// i - 1과 바꿀 i보다 작은 처음 만나는 수 찾기
		while( (flag & i << 1) >= (flag & j) ) j <<= 1;
		
		// i - 1과 j 바꾸기 => 각 위치 반전
		// ... 0 0 1 1 1 1 1 1 1
		// ... 0 0 0 0 0 0 0 0 1
		// ---------------------
		// ... 0 0 1 1 1 1 1 1 0
		
		// 합이 0이 아니고 한쪽 값과 같을 경우에만 => 두 비트가 다를 경우에만
		int temp = (flag & j) + (flag & i << 1);
		if( temp != 0 && ( temp == (flag & j) || temp == (flag & i << 1) ) ) {
			flag = flag ^ j;
			flag = flag ^ i << 1;
		}

		int k = 1;
		// i위치 부터 정렬
		while (i > k) {
			temp = (flag & k) + (flag & i);
			if( temp != 0 && ( temp == (flag & k) || temp == (flag & i) ) ) {
				flag = flag ^ k;
				flag = flag ^ i;
			}

			i >>= 1; k <<= 1; // 범위 좁히기
		}

		return true;
	}

}