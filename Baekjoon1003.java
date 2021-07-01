package solved;

import java.io.*;

/*
 * C++ Source Code
 * int fibonacci(int n) {
 *   if (n == 0) {
 *      printf("0");
 *      return 0;
 *   } else if (n == 1) {
 *      printf("1");
 *      return 1;
 *   } else {
 *      return fibonacci(n‐1) + fibonacci(n‐2);
 *   }
 * }
 * 
 * How many times will 0 and 1 be printed?
 * 
 * Input
 * T
 * n (0 <= N <= 40)
 * 
 * Output
 * 0 1 count
 */

public class Baekjoon1003 {

	public static void main(String[] args) throws IOException {
		int t, n, cnt = 0;
		int[][] output = new int[2][41];
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		output[0][0] = 1; output[1][0] = 0;
		output[0][1] = 0; output[1][1] = 1;
		
		for(int i = 2; i < output[0].length; i++) {
			output[0][i] = output[0][i - 2] + output[0][i - 1]; 
			output[1][i] = output[1][i - 2] + output[1][i - 1]; 
		}
		
		t = Integer.parseInt( br.readLine().trim() );
		while(cnt < t) {
			n = Integer.parseInt( br.readLine().trim() );
			bw.write( output[0][n] + " " + output[1][n] + "\n");
			cnt++;
		}
		
		br.close();
		bw.close();
	}

}
