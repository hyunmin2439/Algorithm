package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * n
 * n개의 숫자 공백 없이
 * 
 * ex)
 * 5
 * 54321
 * 
 * 출력 : 15
 * 
 * 출력
 * n개의 숫자 합
 */

public class BJ_11720_숫자의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int sum = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		br.readLine();
		String[] line = br.readLine().split("");
		
		for(String str : line) {
			sum += Integer.parseInt(str.trim());
		}
		
		bw.write(sum + "");
		bw.flush();
		br.close();
		bw.close();
	}

}
