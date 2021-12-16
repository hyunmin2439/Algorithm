package solved;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Input
 * n k (1 <= n <= 5,000,000, 1<= k <= n)
 * a1, a2, a3, ..., an (-10^9 <= ai <= 10^9)
 * 
 * sort in ascending order
 * 
 * Output
 * ak
 */

public class BJ_11004_K번째수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[n];
		while(n-- > 0) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		bw.write(String.valueOf(arr[k - 1]));
		br.close();
		bw.close();
	}

}
