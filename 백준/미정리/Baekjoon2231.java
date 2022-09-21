package solved;

import java.io.*;

/* 
 * ex) M : 245 N : 256(245 + 2 + 4 + 5)
 * M is the constructor of N
 * 
 * input
 * n (1 <= n <= 1,000,000)
 * 
 * output
 * m (m is smallest constructor of n)
 */

public class Baekjoon2231 {

	public static void main(String[] args) throws IOException {
		boolean check = false;
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out ));
		
		String line = br.readLine().trim();
		
		int n = Integer.parseInt( line );
		
		int m = n / 2;
		
		while(m <= n) {
			if( check = isConstructor(m, n) ) {
				break;
			}
			m++;
		}
		
		if( check )
			bw.write(String.valueOf(m));
		else
			bw.write("0");
		
		br.close();
		bw.close();
	}

	private static boolean isConstructor(int m, int n) {
		String strM = String.valueOf(m);
		int length = strM.length();
		
		for(int i = 0; i < length; i++) {
			m += strM.charAt(i) - 48;
		}
		
		if(m == n) 	return true;
		else 		return false;
	}
}
