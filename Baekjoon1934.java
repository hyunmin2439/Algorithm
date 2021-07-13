package solved;

import java.io.*;

/*
 * Input
 * t   (1 <= t <= 1,000)
 * a b (1 <= a <= b < 45,000)
 * ...
 * t-line a b
 * 
 * Output
 * lcm (Least common multiple)
 * ...
 * t-line lcm
 */

public class Baekjoon1934 {

	public static void main(String[] args) throws IOException {
		int gcd = 0, lcm;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int t = Integer.parseInt(br.readLine());
		
		while(t > 0) {
			String[] line = br.readLine().split(" ");
			
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);

			for(int i = 1; i <= a && i <= b; i++) 
				if(a % i == 0 && b % i == 0) gcd = i;
			
			lcm = a * b / gcd;
			sb.append(lcm + "\n");
			t--;
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
