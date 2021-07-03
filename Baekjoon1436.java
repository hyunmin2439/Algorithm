package com.algo;

import java.io.*;

/*
 * Input
 * n ( 1 <= n <= 10,000 )
 * 
 * Output
 * The 'n'h number including "666"
 */

public class Baekjoon1436 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out ));
		
		int n = Integer.parseInt( br.readLine().trim() );
		
		int mvTitle = checkNumber(n);
		
		bw.write( String.valueOf(mvTitle) );
		br.close();
		bw.close();
	}

	private static int checkNumber(int n) {
		int cnt = 1, mvTitle = 666;
		
		while(true) {
			if(cnt == n) break;
			
			mvTitle++;
			if( String.valueOf(mvTitle).contains("666") ) {
				cnt++;				
			}
		}
		
		return mvTitle;
	}

}
