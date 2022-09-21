package solved;

import java.io.*;

/*
 * Input
 * M N (1 <= M <= N <= 1,000,000)
 * 
 * Output
 * Output a prime number, one per line, in increasing order.
 */

public class Baekjoon1929 {
	public static void main(String[] args) throws IOException {
		int M, N;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		String[] lines = br.readLine().trim().split(" ");
		
		M = Integer.parseInt( lines[0].trim() );
		N = Integer.parseInt( lines[1].trim() );
		
		for(int i = M; i <= N; i++) {
			if( checkPrimeNum(i) )
				bw.write( String.valueOf(i) + "\n" );
		}
		
		br.close();
		bw.close();
	}
	
	private static boolean checkPrimeNum(int num) {
		if( num == 1 || (num != 2 && num % 2 == 0) ) 
			return false;
		
		// 제곱근까지만 나눠봐도 소수판별 가능
		for(int i = 3; i <= Math.sqrt(num); i += 2 ) 
			if(num % i == 0) return false;
		
		return true;
	}
}
