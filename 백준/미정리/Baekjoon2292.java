package com.algo;

import java.io.*;

/*
 * 육각형 벌집
 *    5
 * 4     6
 *    1
 * 3     7 
 *    2
 *10     8
 *    9
 *    
 * 입력
 * num (1 <= num <= 1,000,000,000)
 * 
 * 출력
 * 입력으로 주어진 방까지 최소 개수의 방을 지나서
 * 갈 때 몇개의 방을 지나는지 출력
 */

public class Baekjoon2292 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int cnt = 1, res = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt( br.readLine() );
		
		while(true) {
			if(num <= res) break;
			res += cnt * 6;
			cnt++;
		}
		
//		다른사람 풀이
//		int end= sc.nextInt();
//		int i = 0;
//		while (3 * i * (i + 1) + 1 < end) {
//			i++;
//		}
//		System.out.println(i + 1);
		 
		
		bw.write(cnt + "");
		br.close();
		bw.close();
	}
}
