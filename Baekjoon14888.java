package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14888 {

	static int N, max, min;
	static int[] num;
	static int[] calCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		num = new int[N];
		calCnt = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			calCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫번째 값 부터 넣은채로 시작
		recur(1, num[0]);
		
		System.out.print(max + "\n" + min);
		br.close();
	}
	
	private static void recur(int idx, int res) {
		if(idx == N) {
			max = Math.max(max, res);
			min = Math.min(min, res);
		}
		
		for (int i = 0; i < 4; i++) {
			if(calCnt[i] == 0) continue;
			
			calCnt[i]--;
			recur(idx + 1, calc(res, i, num[idx]));
			calCnt[i]++;
		}
	}
	
	private static int calc(int num1, int i, int num2) {
		
		switch(i) {
		case 0: num1 += num2; break;
		case 1: num1 -= num2; break;
		case 2: num1 *= num2; break;
		case 3: num1 /= num2; break;
		}
		
		return num1;
	}
}
