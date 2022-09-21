package com.algo;

import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon2920 {
	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader( new InputStreamReader( System.in ) );
		bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		//bw.write(mySolve(br));
		otherSolve();
		
		br.close();
		bw.close();
	}
	// memory:14268KB  time : 136ms
	static String mySolve() throws IOException {
		String res = "ascending";
		StringTokenizer st = new StringTokenizer(br.readLine());

		int i = 2, sw = 1;
		if (Integer.parseInt(st.nextToken()) == 8) { // first number
			res = "descending";
			i = 7;
			sw *= -1;
		}

		for (; 0 < i && i <= 8; i += sw) {
			if (Integer.parseInt(st.nextToken()) != i) {
				res = "mixed";
				break;
			}
		}
		return res;
	}
	// memory:14212KB  time : 132ms
	static void otherSolve() throws IOException {
		String str = br.readLine();
		if(str.equals("1 2 3 4 5 6 7 8")) bw.write("ascending");
		else if(str.equals("8 7 6 5 4 3 2 1")) bw.write("descending");
		else bw.write("mixed");
	}
}
