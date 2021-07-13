package solved;

import java.io.*;

/*
 * Input
 * num1 num2 (1 <= num1 <= num2 < 10,000)
 * 
 * Output
 * 1.gcd (Greatest common divisor)
 * 2.lcm (Least common multiple)
 */

public class Baekjoon2609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int gcd = 0, lcm;
		int[] num = new int[2];
		
		String[] line = br.readLine().split(" ");
		num[0] = Integer.parseInt(line[0]);
		num[1] = Integer.parseInt(line[1]);

		for(int i = 1; i <= num[0] && i <= num[1]; i++) 
			if(num[0] % i == 0 && num[1] % i == 0) gcd = i;
		
		
		lcm = num[0] * num[1] / gcd;
		bw.write(gcd + "\n");
		bw.write(String.valueOf(lcm));
		
		br.close();
		bw.close();
	}
	
}
