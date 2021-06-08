package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * a ~ z, A ~ Z, 0 ~ 9
 * 
 * 출력
 * ASCII값
 */

public class Baekjoon11654 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char c = br.readLine().trim().charAt(0);
		int asc = c;
		
		bw.write(asc + "");
		bw.flush();
		br.close();
		bw.close();
	}

}
