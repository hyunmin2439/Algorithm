package com.ssafy.algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon9205 {

	static final int infinity = 1_000_000;
	
	static int T, N;
	static int[][] input;
	static int[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// input
			N = Integer.parseInt(br.readLine()) + 2;
			input = new int[N][2];
			adjMatrix = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				input[i][0] = Integer.parseInt(st.nextToken());
				input[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// make adjust Matrix
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(i == j) continue;
					
					int needCnt = getBeerNeed(i, j);
					
					// 필요한 맥주수가 20병을 넘는다면 가지 못함
					if(needCnt > 20) adjMatrix[i][j] = infinity;
					else adjMatrix[i][j] = needCnt;
				}
			}
			
			ployd();
			
			System.out.println(goToFestival() ? "happy" : "sad");
		}
		
		br.close();
	}
	
	// 최대 얻을 수 있는 맥주 수 보다 작거나 같으면 갈 수 있다.
	private static boolean goToFestival() {
		return adjMatrix[N - 1][0] / 20 <= N - 1;
	}

	private static void ployd() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(k == i) continue;
				for (int j = 0; j < N; j++) {
					if(k == j || i == j) continue;
					
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
					
				}
			}
		}
	}

	private static int getBeerNeed(int i, int j) {
		// 거리를 50으로 나누면 필요한 맥주 수 얻을 수 있음.
		// 단, 소수 점 이하의 값이 나올 수 있기 때문에 올림처리 해야 함.
		return (int)Math.ceil((Math.abs(input[i][0] - input[j][0]) + Math.abs(input[i][1] - input[j][1])) / 50.0);
	}
	
}
