package solved;

import java.io.*;

/*
 * Counting Sort
 * 
 * Input
 * n (1 <= n <= 10,000,000)
 * n-line natural number (1 <= number <= 10,000)
 * 
 * Output
 * n-line Ascending sort
 */

public class BJ_10989_수정렬하기3 {

	public static void main(String[] args) throws IOException {
		int maxNum = 0;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ));
		
		int n = Integer.parseInt( br.readLine().trim() );
		
		int[] number = new int[n];
		int[] sortNum = new int[n];
		
        // Input && MaxNum
		for(int i = 0; i < n; i++) {
			number[i] = Integer.parseInt( br.readLine().trim() );
			maxNum = number[i] > maxNum ? number[i] : maxNum;
		}
		
        // Counting array maxNum declaration && init
		int[] cntArr = new int[maxNum]; 
		for(int i = 0; i < n; i++) {
			cntArr[ number[i] - 1 ]++;
		}
		
        // Cumulative sum
		for(int i = 1; i < cntArr.length; i++) {
			cntArr[i] += cntArr[i - 1];
		}
		
        // Sorting
		for(int i = 0; i < n; i++) {
			sortNum[ cntArr[ number[i] - 1 ] - 1 ] = number[i];
			cntArr[ number[i] - 1 ]--;
		}
		
        // Output
		for(int i = 0; i < n; i++) {
			bw.write( sortNum[i] + "\n" );
		}
		
		br.close();
		bw.close();
	}
}
