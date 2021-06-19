package solved;

import java.io.*;

/*
 * fibonacci
 * 
 * f(n) = f(n-1) + f(n-2) (n >= 2)
 * 
 * 0 1 2 3 4 5 6 7  8  ...
 * 0 1 1 2 3 5 8 13 21 ...
 * 
 * 입력
 * n (n <= 20)
 * 
 */

public class Baekjoon10870 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt( br.readLine().trim() );
		
		bw.write( String.valueOf( recursion(n) ) );
		
		br.close();
		bw.close();
	}

	// 시간 136ms
	private static int recursion(int n) {
		if(n == 0) return 0;
		else if(n == 1) return 1;
		else if(n == 2) return 1;
		
		return recursion(n - 1) + recursion(n - 2);
	}

	// 시간 124ms
	private static int repeat(int n) {
		int[] f = new int[3];
		
		f[1] = 1; f[2] = 1;
		
		if(n < 3) return f[n];
		
		for(int i = 2; i <= n; i++) {
			f[2] = f[0] + f[1];
			f[0] = f[1];
			f[1] = f[2];
		}
		return f[2];
	}

}
