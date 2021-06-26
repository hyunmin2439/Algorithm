package solved;

import java.io.*;

/*
 * Goldbach's conjecture (골드바흐의 추측)
 * 
 * Input
 * Enter n as many as t-line (4 <= n <= 10,000)
 * 
 * Condition
 * Any even number greater than 2 can be represented 
 * by the sum of two decimal numbers.
 * 
 * Output
 * Two numbers that meet the condition
 */

public class Baekjoon9020 {

	public static void main(String[] args) throws IOException {
		int T, n;
		int[] divn = new int[2];
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		T = Integer.parseInt( br.readLine().trim() );
		
		for(int i = 0; i < T; i++) {
			n = Integer.parseInt( br.readLine().trim() );
			divn[0] = n / 2; divn[1] = n / 2;
			
			if( divn[0] % 2 == 0 && divn[0] != 2) { // divn[0] == divn[1], 2 is prime number
				divn[0]--; divn[1]++;
			}
			// if divn[0] is even, even is not a prime number, 
			// To reduce unnecessary calculations.
			
			while( !( checkPrimeNum(divn[0]) 
					&& checkPrimeNum(divn[1]) ) ) {
				divn[0] -= 2; divn[1] += 2; // 홀수 += 2 => 홀수
			}
			
			bw.write(divn[0] + " " + divn[1] + "\n");
		}
		
		br.close();
		bw.close();
	}

	private static boolean checkPrimeNum(int num) {
		if( num == 1 || (num != 2 && num % 2 == 0) ) 
			return false;
		
		// Divide to square roots and you can determine prime number.
		for(int i = 3; i <= Math.sqrt(num); i += 2 ) 
			if(num % i == 0) return false;
		
		return true;
	}
}
