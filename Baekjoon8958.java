package com.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 입력
 * 첫줄 : 테스트케이스 개수
 * 이후 : 각 줄마다 테스트케이스 개수 만큼의 
 * O, X로만 이루어진 문자열 (0 < String < 80)
 * 
 * ex)
 * OOXXXX = 1 + 2 + 0 + 0 + 0 + 0 = 3
 * OXOOOO = 1 + 0 + 1 + 2 + 3 + 4 = 11
 * 
 * 출력
 * 각줄 마다 점수
 */

public class Baekjoon8958 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out));
		
		try {
			int testCnt = Integer.parseInt( br.readLine() );
			
			for (int i = 0; i < testCnt; i++) {
				int score = 0, scoreCnt = 1;
				String[] line = br.readLine().split("");
				
				for(int j = 0; j < line.length; j++) {
					
					if( line[j].equals("O") ) {
						score += scoreCnt;
						scoreCnt++;
					}
					else scoreCnt = 1;
					
				}
				
				bw.write(score + "\n");
			}
			
			bw.flush();
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
