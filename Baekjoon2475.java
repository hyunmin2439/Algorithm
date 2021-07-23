package solved.submit;

import java.io.*;

/*
 * Input
 * n1 n2 ... n5 (0 <= ni <= 9)
 * 
 * Ouput
 * (n1^2 + n2^2 ... n5^2) % 10
 */

public class Baekjoon2475 {

	public static void main(String[] args) throws IOException {
		int sum = 0;
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		for(String num : br.readLine().split(" ")) {
			sum += Math.pow(Integer.parseInt(num), 2);
		}
		
		bw.write(String.valueOf(sum % 10));
		br.close();
		bw.close();
	}

}
