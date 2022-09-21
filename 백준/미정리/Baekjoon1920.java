package solved;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * Input
 * n (1 <= n <= 100,000)
 * a1 a2 ... an (-2^31 < ai(int) < 2^31)
 * m (1 <= m <= 100,000)
 * num_1 num_2 ... num_m
 * 
 * A find numbers in a array
 * 
 * Output
 * 1 if present and 0 if not present.
 * ...
 * m
 */

public class Baekjoon1920 {

	public static void main(String[] args) throws IOException {
		Set<Integer> a = new HashSet<>();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		while(n-- > 0) {
			a.add(Integer.parseInt(st.nextToken()));
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		StringBuilder sb = new StringBuilder();
		while(m-- > 0) {
			int t = 0;
			if( a.contains( Integer.parseInt( st.nextToken() ) ) ) {
				t = 1;
			}
			sb.append(t + "\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}