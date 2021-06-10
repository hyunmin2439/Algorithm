package com.algo;

import java.io.*;

/*
 * 입력
 * S ( 1 <= S(String) <= 1,000,000)
 * 
 * 출력
 * 가장 많이 사용된 알파벳 대문자 출력
 * 단, 여러 개 존재할 경우 ?로 출력
 */


public class Baekjoon1157 {
	final static int alen = 26;
	static int[][] alphaNum = new int[2][26];

	public static void main(String[] args) throws IOException {
		//////////////////////////////// 다른 사람이 짠 코드 ////////////////////////////////
		int b[] = new int[26];
		int c = System.in.read();
		while(c > 64) {  // 공백의 ASCII : 32, 공백들어오면 종료
			if(c < 91) { // 대문자일 경우
				b[c - 65]++;
			} else { 	 // 소문자일 경우
				b[c - 97]++;
			}
			c = System.in.read();
		}
		
		int max = -1;
		int maxN = 0;
		for(int i = 0; i < b.length; i++) {
			if(b[i] > max) {
				max = b[i];
				maxN = i;
			} else if(b[i] == max) { // max가 같은 것이 있으면, 낮은 값이 같은 것이 있다고 해도
									 // 위의 max값을 찾는 곳에서 다른 max값으로 바뀌기 때문에 상관이 없음.
				maxN = -2; // ?의 ASCII 63 => -2 + 65
			}
		}
		System.out.println( (char)( maxN + 65 ) );
		///////////////////////////////////////////////////////////////////////////////////////////
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine().trim().toUpperCase(); // 공백 없애고 다 대문자로 받음
		
		init();
		alphaCnt( s );
		sort();
		//testOutput();
		
		bw.write( output() );
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static char output() {
		if(alphaNum[1][0] == alphaNum[1][1]) return '?';
		else return (char)alphaNum[0][0];
	}

	private static void testOutput() {
		for(int i = 0; i < alen; i++) {
			System.out.print( (char)alphaNum[0][i] + " " );
		}
		System.out.println();
		for(int i = 0; i < alen; i++) {
			System.out.print( alphaNum[1][i] + " " );
		}
	}

	private static void init() {
		for(char c = 'A'; c <= 'Z'; c++) {
			alphaNum[0][c - 65] = c;
		}
	}

	private static void alphaCnt(String s) {
		int length = s.length();
		
		for(int i = 0; i < length; i++) {
			alphaNum[1][s.charAt(i) - 65]++;
		}
	}

	private static void sort() {
		int[][] tmp = new int[2][1];
		
		for(int i = 0; i < alen - 1; i++) {
			for(int j = 0; j < alen - 1 - i; j++) {
				if(alphaNum[1][j] < alphaNum[1][j + 1]) {
					tmp[0][0] 			= alphaNum[0][j];
					tmp[1][0] 			= alphaNum[1][j];
					
					alphaNum[0][j] 		= alphaNum[0][j + 1];	
					alphaNum[1][j]  	= alphaNum[1][j + 1];
					
					alphaNum[0][j + 1] 	= tmp[0][0];	
					alphaNum[1][j + 1] 	= tmp[1][0];
				}
			}
		}
	}
}