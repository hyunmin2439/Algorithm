package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이항 계수
// (1 ≤ N ≤ 4,000,000, 0 ≤ K ≤ N)
// 굉장히 큰 값을 가지고 있기 때문에
// 파스칼 삼각형을 이용한 dp방법으로 풀긴 어려움

// 페르마의 소정리 : a^(p-2) ≡ 1 / a
// 조합식 : nCr = n! / (r! * (n-r)!)

// 모듈러연산 위해, 나누기 연산 -> 곱셈연산
// 이때, 페르마의 소정리 이용
// nCr ≡ n! * ( r! * (n-r) )^(p-2)

// 팩토리얼 연산 반복하며 필요한 부분만 저장
// 제곱 : 분할 정복 알고리즘 사용, O(logN)

// Memory:14308KB / Time:172ms
public class Baekjoon11401 {
	
	static final int MOD = 1_000_000_007;

	static int N, R;
	static long nf = 1, rf = 1, nrf = 1; // n! r! nr! 값, 1로 초기화X => 1 1, 0이 출력 
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); // 문제에서 K이나 보편적인 R로 대체
		
		fact(N); // factorial 연산 한번으로 3개의 값 전부 구함
		System.out.println( ( nf * pow((rf * nrf) % MOD, MOD - 2)) % MOD);
	}
	
	public static long pow(long n, int e) {
		// 홀수 일때 마다 따로 곱해서 저장
		long res = 1L;
		
		while(e > 0) {
			if(e % 2 == 1) // e가 1이되면 res에 값이 곱해져서 나온다.
				res = (res * n) % MOD;
			e = e >> 1;
			n = (n * n) % MOD;
		}
		
		return res;
	}

	public static void fact(int n) {
		long f = 1;
		
		for (int i = 1; i <= n; i++) {
			f = (f * i) % MOD;
			
			// N - R, R, N이 동일한 값을 가질 수도 있으니 따로 if문 체크
			if(i == N - R) nrf = f;
			if(i == R) rf = f;
			if(i == N) nf = f;
		}
	}

}
