package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * S (2 <= S(String) <= 15)
 * 
 * 처리
 * 1			 2초
 * 2 : A,B,C	 3초
 * 3 : D,E,F	 4초
 * 4 : G,H,I	 5초
 * 5 : J,K,L	 6초
 * 6 : M,N,O	 7초
 * 7 : P,Q,R,S	 8초
 * 8 : T,U,V	 9초
 * 9 : W,X,Y,Z	10초
 * 0			11초
 * 
 * 출력
 * S를 숫자에 대입 걸리는 시간
 */

public class Baekjoon5622 {

	public static void main(String[] args) throws IOException {
		int result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] line = br.readLine().trim().split("");
		
		for(int i = 0; i < line.length; i++) {
			result += checkAlpha( line[i].charAt(0) );
		}
		
		bw.write(result + "");
		bw.flush();
		br.close();
		bw.close();
	}

	private static int checkAlpha(char c) {
		int i = 0;
		
		switch(c) {
		
		case 'A': case 'B': case 'C':
			i = 3; break;
		case 'D': case 'E': case 'F':
			i = 4; break;
		case 'G': case 'H': case 'I':
			i = 5; break;
		case 'J': case 'K': case 'L':
			i = 6; break;
		case 'M': case 'N': case 'O':
			i = 7; break;
		case 'P': case 'Q': case 'R': case 'S':
			i = 8; break;
		case 'T': case 'U': case 'V':
			i = 9; break;
		case 'W': case 'X': case 'Y': case 'Z':
			i = 10;
		}
		
		return i;
	}

}
