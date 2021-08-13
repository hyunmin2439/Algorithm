package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Binary Counting
public class Baekjoon2961 {
	
	static int N, dif, flag, end;
	static Ingre[] in;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
		
		dif = Integer.MAX_VALUE; // 결과 값
		end = 1 << N; // 종료 시점
		
		// Input
		N = Integer.parseInt(br.readLine());
		in = new Ingre[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			in[i] = new Ingre( st.nextToken(), st.nextToken() );
		}
		
		// 동작
		while(++flag < end) {
			cook();
		}
		
		System.out.println(dif);
		br.close();
	}
	
	private static void cook() {
		int mulS = 1, sumB = 0;
		
		// bit Masking된 값 체크
		for (int i = 0; i < N; i++) {
			if( (flag & 1 << i) != 0 ) {
				mulS *= in[i].S;
				sumB += in[i].B;
			}			
		}
		
		dif = Math.min(dif, Math.abs(mulS - sumB));
	}

	static class Ingre {
		int S; // 신맛
		int B; // 쓴맛
		
		Ingre(String S, String B) {
			this.S = Integer.parseInt(S);
			this.B = Integer.parseInt(B);
		}
	}

}
