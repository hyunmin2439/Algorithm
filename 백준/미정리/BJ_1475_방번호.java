package com.algo;

import java.io.*;

/*
 * Input
 * n (room Number, 0 <= n <= 1,000,000)
 * 
 * Process
 * One set contains numbers 0 through 9.
 * Six can be used by reversing nine, 
 * nine can be used by reversing six.
 * 
 * Output
 * setCnt : Number of Sets Required
 */

public class BJ_1475_방번호 {

	public static void main(String[] args) throws IOException {
		int setCnt = 0;
		int[] numArr = new int[9]; // numArr[6] => 6, 9 count
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		String n = br.readLine();
		int length = n.length();
		
		int i = 0;
		while( i < length ) {
			int idx = n.charAt(i) - '0';
			
			if(idx == 9) idx = 6; // input 9 => 6 change
			numArr[idx]++; 
			
			if( idx == 6 ) {
				if( Math.round(numArr[6] / 2.0) > setCnt )
					setCnt++;
			}
			else {
				if(numArr[idx] > setCnt) setCnt++;
			}
			
			i++;
		}
		
		bw.write(String.valueOf(setCnt));
		
		br.close();
		bw.close();
	}

}
