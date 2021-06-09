package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * T (1 <= T <= 1,000)
 * R S ( 1 <= R(int) <= 8, 1 <= S(String) < 20)
 * 
 * 출력
 * P (S의 각 단어 R만큼 반복 출력)
 * 
 * ex)
 * 2
 * 3 ABC
 * 5 /HTP
 * 
 * AAABBBCCC
 * /////HHHHHTTTTTPPPPP
 */

public class Baekjoon2675 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T, R;
		String[] S;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력
		T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			R = br.read() - 48; // ASCII 48 = 0
			br.read(); // 공백
			S = br.readLine().trim().split("");		
			
			for(int j = 0; j < S.length; j++) {
				for(int k = 0; k < R; k++)
					bw.write( S[j] );
			}
			bw.write( "\n" );
		}
		
		// 출력
		bw.flush();
		br.close();
		bw.close();
	}
}
