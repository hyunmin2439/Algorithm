package solved;

import java.io.*;

/*
 * Bertrand's postulate (베르트랑 공준)
 * Bertrand-Chebyshev theorem (베르트랑-체비쇼프 정리)
 * At least one decimal exists between two natural numbers n and 2n
 * 
 * Input
 * n (1 <= n <= 123,456)
 * n
 * n...
 * 0 (exit)
 * 
 * Output
 * prime number count (n < prime number <= 2n)
 */

public class Baekjoon4948 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int N = Integer.parseInt( br.readLine().trim() );
		
		do {
			int cnt = 0;
			
			for(int i = N + 1; i <= N * 2; i++) {
				if( checkPrimeNum(i) ) cnt++;				
			}
			
			bw.write( String.valueOf(cnt) + "\n" );
			N = Integer.parseInt( br.readLine().trim() );
		} while (N != 0);
		
		br.close();
		bw.close();
	}
	
	private static boolean checkPrimeNum(int num) {
		if( num == 1 || (num != 2 && num % 2 == 0) ) 
			return false;
		
		for(int i = 3; i <= Math.sqrt(num); i += 2 ) 
			if(num % i == 0) return false;
		
		return true;
	}
}
