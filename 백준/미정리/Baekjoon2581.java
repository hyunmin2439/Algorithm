package solved;

import java.io.*;

/*
 * Input
 * M N (1 <= N <= M <= 10,000)
 * 
 * Output
 * The first line is sum of prime number between M and N
 * The second line is minimum value of prime number between M and N
 * Provided, That if there is no prime number
 * between M and N, -1 is outputted in the first line.
 */

public class Baekjoon2581 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int M, N, sum = 0, min = -1;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		M = Integer.parseInt( br.readLine().trim() );
		N = Integer.parseInt( br.readLine().trim() );
		
		for(int i = M; i <= N; i++) {
			if( checkPrimeNum(i) ) {
				sum += i;
				if(min == -1) min = i;
			}
		}
		if(sum != 0) bw.write(sum + "\n");
		bw.write( String.valueOf(min) );
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
