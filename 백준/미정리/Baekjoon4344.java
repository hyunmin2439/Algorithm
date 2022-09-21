package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * C : 테스트 케이스의 수
 * 각 테스트 케이스 줄마다
 * N(1 <= N <= 1000) N명의 score (0 <= score <= 100)
 * 
 * 5
 * 5 50 50 70 80 100
 * 7 100 95 90 80 70 60 50
 * 3 70 90 80
 * 3 70 90 81
 * 9 100 99 98 97 96 95 94 93 91
 * 
 * 출력
 * 각 케이스 마다 평균을 넘는 학생들의 비율
 * 반올림하여 소수점 셋째 자리까지 출력
 */

public class Baekjoon4344 {

	public static void main(String[] args) {
		int C, N, cnt = 0;
		float avg = 0;
		String fmt = "%.3f%%\n";
		String[] line;
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
		
		try {
			C = Integer.parseInt( br.readLine() );
			
			for(int i = 0; i < C; i++) {
				cnt = 0; avg = 0;
				
				line = br.readLine().split(" ");

				N = Integer.parseInt( line[0].trim() );
				int[] score = new int[N];
				
				for(int j = 0; j < N; j++) {
					score[j] = Integer.parseInt( line[ j + 1 ].trim() );
					avg += score[j];
				}
				
				avg /= N;
				
				for(int j = 0; j < N; j++) if( score[j] > avg ) cnt++;
				
				avg = (float)cnt / N * 100;
				
				bw.write( String.format(fmt, avg));
			}
			
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			
		}
	}

}
