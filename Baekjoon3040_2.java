package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon3040_2 {
	
	static int N = 9, R = 7;
	static int[] input = new int[N];
	static int[] tgt = new int[R];
	static boolean done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
		
		br.close();
	}
	
	private static void comb(int inIdx, int tgtIdx, int sum) {
		if( done ) return;
		
		// 기저조건
		if( tgtIdx == R ) {
			// complete code
			if( sum == 100 ) {
				// 답
				done = true;
				for (int i = 0; i < R; i++) {
					System.out.println(tgt[i]);
				}
			}
			return;
		}
		
		if( inIdx == N ) return;
		
		tgt[tgtIdx] = input[inIdx];
		
		comb(inIdx + 1, tgtIdx + 1, sum + tgt[tgtIdx] );
		comb(inIdx + 1, tgtIdx, sum);
	}
}
