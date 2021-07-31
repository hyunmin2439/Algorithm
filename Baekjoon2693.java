package com.algo;

import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2693 {
	
	static int N, M, priority;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] A = new int[10];
			for (int i = 0; i < 10; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(A);
			
			sb.append(A[7] + "\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
