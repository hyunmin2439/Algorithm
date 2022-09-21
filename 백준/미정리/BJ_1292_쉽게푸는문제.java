package com.algo;

import java.io.*;

public class BJ_1292_쉽게푸는문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int[] arr = new int[1001];
		
		String[] line = br.readLine().split(" ");
		int A = Integer.parseInt(line[0]);
		int B = Integer.parseInt(line[1]);
	
		int idx = 1;
		int num = 1;
		while(idx <= 1000) {
			
			for (int i = 1; i <= num && idx <= 1000; i++) {
				arr[idx] = num;
				idx++;
			}
			
			num++;
		}
		
		int sum = 0;
		for (int i = A; i <= B; i++) {
			sum += arr[i];
		}

		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
	}

}
