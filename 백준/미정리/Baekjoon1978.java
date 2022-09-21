package solved;

import java.io.*;
import java.util.StringTokenizer;

/*
 * 주어진 N개 중 소수 개수 출력
 * n ( n <= 100 )
 * num[n] ( num[n] <= 1000 )
 * 
 * ex)
 * 입력
 * 4
 * 1 3 5 7
 * 출력
 * 3
 */

public class Baekjoon1978 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int num, cnt = 0;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		br.readLine();
		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");

		while( st.hasMoreTokens() ) {
			num = Integer.parseInt( st.nextToken() );
			
			if ( checkPrimeNum( num ) ) {
				cnt++;				
			}
		}
		
		bw.write( String.valueOf(cnt) + "\n" );
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
