package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * N ( 1 <= N <= 1000)
 * 
 * 111 : 공차 0로 등차수열
 * 246 : 공차 2로 등차수열
 * 379 : 3 - 7 != 7 - 9 공차수열X
 * 
 * 출력
 * 1 <= 한수의 총 개수 <= N
 */

public class BJ_1065_한수 {
	
	public static int checkHanNumber(int n) {
		int cnt = 99;
		int[] digit = new int[3];
		
		if(n < 99) return n;
		
		else if (n < 111) return cnt;
		
		else {
			for(int i = 111; i <= n; i++) {
				digit[0] = i / 100;
				digit[1] = i % 100 / 10;
				digit[2] = i % 10;
				
				if( (digit[0] - digit[1]) 
						== (digit[1] - digit[2]) )
					cnt++;
			}			
		}
		
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		bw.write( checkHanNumber(n) + "" );
		bw.flush();
		br.close();
		bw.close();
	}

}
