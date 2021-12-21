package com.algo;

import java.io.*;

/*
 *    1   2   3   4   5
 * 1 1/1 1/2 1/3 1/4 ...
 * 2 2/1 2/2 2/3
 * 3 3/1 3/2 3/3
 * 4 4/1 ...
 * 5 
 * 
 * 1/1 -> 1/2 -> 2/1 -> 3/1 -> 2/2 -> … 
 * 1번,   2번,   3번,   4번,   5번, … 분수
 * 
 * 몇번째 분수
 * 
 * 입력
 * 첫째 줄에 X(1 ≤ X ≤ 10,000,000)가 주어진다.
 * 
 * 출력
 * 첫째 줄에 분수를 출력한다.
 * 
 * 등차수열의 합
 * 1 + 2 + ... + i = i * (1 + i) / 2
 * n * (1 + n) / 2 >= x
 * 
 */

public class BJ_1193_분수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int x = Integer.parseInt( br.readLine() );
		int n = 1, res = 0;
		
		while(res < x) {
			res = (1 + n) * n / 2;
			n++;
		}
		n--;
		
		//System.out.println("res:" + res + ", x:" + x + ", n:" + n);
		
		int i, j;
		if( n % 2 == 0 ) {
			i = n - (res - x);
			j = n - i + 1;
		}
		else {
			j = n - (res - x);
			i = n - j + 1;
		}
		
		//System.out.println("i:" + i + ", j:" + j);
		
		bw.write(i + "/" + j);
		
		br.close();
		bw.close();
	}

}
