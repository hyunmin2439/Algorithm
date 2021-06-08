package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * S (String && S.length < 100 && a ~ z)
 * 
 * 출력
 * a b c d ... z 각 알파벳이 처음 등장하는 위치
 * 알파벳이 포함되어 있지 않다면 -1
 * 
 * a : 97 ~ z : 122
 */

public class Baekjoon10809 {

	public static void main(String[] args) throws IOException {
		int[] alphaPos = new int[26];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		/* 시간 152ms
		// init
		String[] line = br.readLine().trim().split("");
		 
		for(int i = 0; i < 26; i++) {
			alphaPos[i] = -1;
		}
		
		for(int i = 0; i < line.length; i++) {
			int idx = line[i].charAt(0) - 97;
			
			if( alphaPos[ idx ] == -1 )
				alphaPos[ idx ] = i;
		}
		
		for(int i = 0; i < 26; i++) {
			bw.write( alphaPos[i] + " " );
		}
		*/
		
		// 간단하게 : 시간 232ms
		// 이중 for문이라 시간이 더 느린듯
		String line = br.readLine();
		
		for(char c = 'a'; c <= 'z'; c++)
			bw.write( line.indexOf( c )+ " " );
			// 문자열에서 해당 단어의 위치를 찾음.
		
		bw.flush();
		br.close();
		bw.close();
	}

}
