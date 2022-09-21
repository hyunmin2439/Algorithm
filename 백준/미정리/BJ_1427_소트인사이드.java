package solved;

import java.io.*;
import java.util.Arrays;

/*
 * Input
 * n (1 <= n <= 1,000,000,000)
 * 
 * Output
 * n Descending order
 * 
 * ex) 2143 -> 4321
 */

public class BJ_1427_소트인사이드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		char[] arr = br.readLine().trim().toCharArray();
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		
		/* append and reverse
		for(char c : arr) {
			sb.append(c);
		}
		
		sb.reverse();
		*/
		
		// append in reverse order
		for(int i = arr.length - 1; i > -1; i--) {
			sb.append(arr[i]);
		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.close();
	}

}
