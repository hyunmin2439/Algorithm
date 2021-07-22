package solved;

import java.io.*;

/*
 * Input
 * T (Test Case)
 * ps (Parenthesis String, 2 <= ps <= 50)
 * 
 * process
 * using stack structure
 * 
 * Output
 * if Valid PS "YES"
 * else "NO"
 */

public class Baekjoon9012 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		int T = Integer.parseInt( br.readLine() );
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			if( check( br.readLine().trim() ) ) 
				sb.append("YES\n");
			else sb.append("NO\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static boolean check(String line) {
		int top = -1;
		char[] stack = new char[line.length()];
		char[] ps = line.toCharArray();

		for(int i = 0; i < ps.length; i++) {
			if ( ps[i] == '(' ) {
				top++;
				stack[top] = '(';
			}
			else {
				if( top > -1 && stack[top] == '(') {
					top--;
				}
				else {
					top++;
					stack[top] = ')';
				}
			}
		}
		
		if(top == -1) 
			return true;
		else return false;
	}
}
