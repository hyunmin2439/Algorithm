package solved;

import java.io.*;

/*
 * 입력
 * n (0 <= n <= 12)
 * 
 * use recursive functions
 * 
 * 출력
 * n!
 */

public class Baekjoon10872 {
	
	private static int factorial(int n) {
		int result = n;
		if(n == 0) return 1;
		return result *= factorial(n - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt( br.readLine().trim() );
		
		bw.write( String.valueOf( factorial( n ) ) );
		
		br.close();
		bw.close();
	}

}