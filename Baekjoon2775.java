package com.algo;

import java.io.*;

public class Baekjoon2775 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int t, k, n; // k : TestCase   k : floor   n : apartment number
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] apartment = new int[15][14];
		for (int i = 0; i < apartment[0].length; i++) {
			apartment[0][i] = i + 1;
		}
		
		for(int i = 1; i < apartment.length; i++) {
			for(int j = 0; j < apartment[i].length; j++) {
				if(j == 0) apartment[i][j] = 1;
				else apartment[i][j] = apartment[i - 1][j] + apartment[i][j - 1];
			}			
		}
		
		t = Integer.parseInt( br.readLine() );
		
		for (int i = 0; i < t; i++) {
			k = Integer.parseInt( br.readLine() );
			n = Integer.parseInt( br.readLine() ) - 1;
			
			bw.write( String.valueOf( apartment[k][n] ) + "\n" );
		}
		
		br.close();
		bw.close();
	}
}
