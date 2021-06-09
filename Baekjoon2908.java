package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * A B (A, B 세자리수이며 0이 포함X)
 * 
 * 출력
 * 숫자를 거꾸로 읽어 비교하여 큰값 출력
 * 
 * ex)
 * 입력:734 893
 * 출력:437
 */

public class Baekjoon2908 {

	public static void main(String[] args) throws IOException {
		int mulNum = 100, reuslt;
		int[] iNum = new int[2];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] line = br.readLine().split(" ");
		
		for(int i = 2; i > -1; i--) {
			iNum[0] += ( line[0].charAt(i) - 48 ) * mulNum;
			iNum[1] += ( line[1].charAt(i) - 48 ) * mulNum;
			mulNum /= 10;
		}
		bw.write( ( iNum[0] > iNum[1] ? iNum[0] : iNum[1] ) + "" );
		
		bw.flush();
		br.close();
		bw.close();
	}

}
