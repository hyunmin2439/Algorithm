package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 Z
public class Baekjoon1074 {
	
	static int N, R, C, ans;
	static boolean exit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		N = (int)Math.pow(2, N);
		
		recur(N - 1, N - 1, N / 2, N * N - 1);
		
		System.out.println(ans);
		br.close();
	}

	private static void recur(int r, int c, int n, int num) {
		
		// 기저 조건
		if(exit) return;
		
		if(r == R && c == C) {
			ans = num;
			exit = true;
			return;
		}
		
//		0  1  4  5
//		2  3  6  7
//		8  9 12 13
//	   10 11 14 15
		
//		현재 15위치라고 하면
		int nr = r, nc = c;
		
		if(r - 1 == R) { // 현재 영역중에 바로 위쪽에 있는지? 
			nr -= 1;	 // ex) 13
			num -= 2;
		}
		else if(nr - n >= R) { // 위쪽 영역에 있는지? 
			nr -= n;		   // ex) 4 ~ 7
			num -= 2 * n * n;
		}
		
		if(c - 1 == C) { // 현재 영역중에 바로 왼쪽에 있는지? 
			nc -= 1;	 // ex) 14, 위쪽 if문 걸렸다면 12
			num -= 1;
		}
		else if(nc - n >= C) { // 왼쪽 영역에 있는지? 
			nc -= n;		   // ex) 8 ~ 11, 위쪽 else if문 걸렸다면 0 ~ 3
			num -= n * n;
		}
		
		recur(nr, nc, n / 2, num);
	}

}
