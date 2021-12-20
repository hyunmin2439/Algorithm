package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Input
 * N (1 ≤ N ≤ 10,000,000)
 * 
 * Output
 * The results of the factorization of N 
 * are outputted in ascending order, one per line.
 * If N is 1, nothing is output.
 */

public class BJ_11653_소인수분해 {

	public static void main(String[] args) throws IOException {
		int n, div = 2;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		List<Integer> list = new ArrayList<>();
		
		n = Integer.parseInt( br.readLine().trim() );
		
		while(n > 1) {
			if(n % div == 0) {
				list.add(div);
				n /= div;
				div = 2;
			}
			else div++;
		}
		
		for(int i : list) {
			bw.write(i + "\n");			
		}
		
		br.close();
		bw.close();
	}
}
