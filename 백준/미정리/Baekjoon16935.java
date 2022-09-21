package BJ_algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon16935 {
	static int N, M, R, sw; // sw : 90도 회전시 변환
	static int[][][] area = new int[2][][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));   
		BufferedWriter  bw  = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder   sb = new StringBuilder(); 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		area[0] = new int[N][M];
		area[1] = new int[M][N];
		
		// 기존 배열 입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				area[sw][y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 연산 읽어서 배열 회전하기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			instruct(Integer.parseInt(st.nextToken()));
		}
		
		// StringBuilder에 추가
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				sb.append(area[sw][y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void instruct(int num) {
		switch (num) {
		case 1: inst1(); break;
		case 2: inst2(); break;
		case 3: inst3(); break;
		case 4: inst4(); break;
		case 5: inst5(); break;
		case 6: inst6(); break;
		}
	}

	// 상하 반전
	private static void inst1() {
		int sy = 0, ey = N - 1;
		
		while(ey - sy > 0) {
			// 행 단위로 복제 위치 바꾸기
			int[] temp = area[sw][sy].clone(); 
			area[sw][sy] = area[sw][ey];
			area[sw][ey] = temp;
			sy++; ey--;
		}
	}
	
	// 좌우 반전
	private static void inst2() {
		int sx = 0, ex = M - 1;
		
		while(ex - sx > 0) {
			for (int y = 0; y < N; y++) {
				int temp = area[sw][y][sx];
				area[sw][y][sx] = area[sw][y][ex];
				area[sw][y][ex] = temp;
			}
			sx++; ex--;
		}
	}

	// 오른쪽 90도 회전
	private static void inst3() {
		int ch = sw == 0 ? 1 : 0;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				area[ch][x][N - 1 - y] = area[sw][y][x];
			}
		}
		
		sw = ch; // 현재 사용중 행렬 바꾸기
		changeRowCol();
	}


	// 왼쪽 90도 회전
	private static void inst4() {
		int ch = sw == 0 ? 1 : 0;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				area[ch][M - 1 - x][y] = area[sw][y][x];
			}
		}
		
		sw = ch;
		changeRowCol();
	}
	
	// 행렬 바꾸기
		private static void changeRowCol() {
			int temp = M;
			M = N;
			N = temp;
		}
	
	// 사등분 시계 방향 회전
	private static void inst5() {
		int hy = N / 2, hx = M / 2;
		
		for (int y = 0; y < hy; y++) {
			for (int x = 0; x < hx; x++) {
				int temp = area[sw][y][x];
				area[sw][y][x] = area[sw][hy + y][x]; // 1 <- 4
				area[sw][hy + y][x] = area[sw][hy + y][hx + x]; // 4 <- 3
				area[sw][hy + y][hx + x] = area[sw][y][hx + x]; // 3 <- 2
				area[sw][y][hx + x] = temp; // 2 <- 1
			}
		}
	}
	
	// 사등분 반시계 방향 회전
	private static void inst6() {
		int hy = N / 2, hx = M / 2;

		for (int y = 0; y < hy; y++) {
			for (int x = 0; x < hx; x++) {
				int temp = area[sw][y][x];
				area[sw][y][x] = area[sw][y][hx + x]; // 1 <- 2
				area[sw][y][hx + x] = area[sw][hy + y][hx + x]; // 2 <- 3
				area[sw][hy + y][hx + x] = area[sw][hy + y][x];// 3 <- 4
				area[sw][hy + y][x] = temp; // 4 <- 1
			}
		}
	}
}
