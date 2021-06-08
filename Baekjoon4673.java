package com.algo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/*
 * 셀프넘버
 * 
 * 입력 X
 * 
 * d(33) = 33 + 3 + 3 = 39
 * d(39) = 39 + 3 + 9 = 51
 * d(51) = 51 + 5 + 1 = 57
 * ...
 * d(d(d(d(n)))))....... d(n) <= 100_00
 * 
 * 이때, n을 d(n)의 생성자라고 한다.
 * 33은 39의 생성자, 39는 51의 생성자
 * 
 * 생성자가 없는 숫자를 셀프 넘버
 * 1, 3, 5, 7, 9...
 * 
 * 출력
 * 셀프 넘버 <= 10,000, 한줄에 하나씩 출력
 */

public class Baekjoon4673 {
	static final int maxNum = 10000;
	static boolean[] selfNum = new boolean[10_037];

	public static void main(String[] args) {
		init();
		for(int i = 1; i <= maxNum; i++)
			checkSelfNum(i);
		output();

	}
	static void init() {
		for(int i = 1; i <= maxNum; i++)
			selfNum[i] = true;
	}
	
	static void checkSelfNum(int num) {
		int dNum = num;
		dNum += num / 1000; num %= 1000; // 1,000 자리
		dNum += num / 100; num %= 100; // 100 자리
		dNum += num / 10; num %= 10; // 10 자리
		dNum += num; // 1자리
		selfNum[dNum] = false;
	}
	
	static void output() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));		
		try {
			for(int i = 1; i <= maxNum; i++) {
				if(selfNum[i]) bw.write(i + "\n");
			}
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}