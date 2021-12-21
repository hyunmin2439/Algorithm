package solved;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * S is empty set
 * 
 * method
 * (1 <= x <= 20)
 * add x : Add x to S
 * remove x : Remove x to S
 * check x : If S has x, output 1, else output 0.
 * toggle x : If S has x, remove x to S, else add x to S.
 * all : change {1, 2, ..., 20}
 * empty : change empty set
 * 
 * Input
 * m (number of method, 1 <= m <= 3,000,000)
 * method-1
 * ....
 * method-m
 * 
 * Output
 * Check method is given, output the results
 * 
 */

public class BJ_11723_집합 {

	public static void main(String[] args) throws IOException {
		Set<Integer> s = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		int m = Integer.parseInt(br.readLine());
		
		while(m > 0) {
			StringTokenizer st = new StringTokenizer( br.readLine() );
			
			String method = st.nextToken();
			
			if( method.equals("all") ) 
				for(int i = 1; i <= 20; i++) s.add(i);
			else if( method.equals("empty") ) 
				s.clear();
			else {
				Integer x = Integer.valueOf(st.nextToken());
				
				switch(method) {
				
				case "add":
					s.add(x);
					break;
					
				case "remove":
					s.remove(x);
					break;
					
				case "check":
					if(s.contains(x)) sb.append(1 + "\n");
					else sb.append(0 + "\n");
					break;
				
				case "toggle":
					if(s.contains(x)) s.remove(x);
					else s.add(x);
					break;
				}
			}
			
			m--;
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
