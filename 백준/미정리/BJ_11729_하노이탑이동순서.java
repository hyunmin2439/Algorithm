package solved.submit;

import java.io.*;

/*
 * Tower of Hanoi
 * 
 * Input
 * N (1 ≤ N ≤ 20)
 * 
 * Out
 * Number of movements
 * Path of movements
 */

public class BJ_11729_하노이탑이동순서 {
	static int cnt;
	static StringBuilder sb;
	
	private static void hanoi( int n, char s, char t, char d ) {
		if(n < 1) return;
		
		hanoi(n-1, s, d, t);
		
		cnt++;
		sb.append(s).append(' ').append(d).append('\n');
		
		hanoi(n-1, t, s, d);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		sb = new StringBuilder();
		
		int n = Integer.parseInt( br.readLine().trim() );

		hanoi(n, '1', '2', '3');
		
		bw.write(String.valueOf(cnt) + '\n');
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
