package com.algo;

import java.io.*;

/* 크로아티아 알파벳 개수
 * 입력
 * 알파벳 + 크로아티아 알파벳 ( <= 100)
 * 
 * 크로아티아 알파벳
 * c=
 * c-
 * dz=
 * d-
 * lj
 * nj
 * s=
 * z=
 * 
 * 
 */

// 2글자씩 검사하다가dz를 만나면 뒤에가 =인지 확인
public class Baekjoon2941 {
	static String[] line;

	public static void main(String[] args) throws IOException {
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/*
		line = br.readLine().trim().split("");
		
		for(int i = 0; i < line.length; i++) {
			switch( croCheck( i ) ) {
			case 0: cnt ++; break;
			case 1: cnt ++; i++; break;
			case 2: cnt ++; i+=2; break;
			}
			
		}
		
		bw.write( cnt + "" );
		br.close();
		bw.close();
		*/
		
		// 간단하게 하는 방법
		String line = br.readLine().trim();
		String r = line.replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=", "n");
		// replace( )와 달리 인자값으로 ("정규식", "치환할 문자열")
		
		bw.write( r.length() + "" );
		br.close();
		bw.close();
	}

	private static int croCheck(int i) {
		if( i + 1 >= line.length ) return 0;
		
		switch( line[i] + line[i + 1] ) {
		case "c-": case "c=": case "d-":
		case "lj": case "nj": case "s=": case "z=":
			return 1;
		case "dz":
			if( i + 2 < line.length 
					&& line[i + 2].equals("=") ) {
				return 2;	
			}
		default:
			return 0;
		}
	}
}
