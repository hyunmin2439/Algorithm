package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14889 {
	
	static int N, half, min = Integer.MAX_VALUE;
	static int[][] scoreBoard;
	static boolean[] select;
	
	static BufferedReader br;
	static BufferedWriter bw;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		scoreBoard = new int[N + 1][N + 1];
		select = new boolean[N + 1];
		half = N / 2;
		
		input();
		
		comb(1, 0);
		
		bw.write(String.valueOf(min));
		br.close();
		bw.close();
	}

	private static void input() throws IOException {
		for (int y = 1; y <= N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 두사람의 다른 점수 미리 더해두기
			for (int x = 1; x <= N; x++) {
				if(y == x) 
					st.nextToken(); // 0 값, 읽고 버리기
				else if(y < x) 
					scoreBoard[y][x] += Integer.parseInt(st.nextToken());
				else 
					scoreBoard[x][y] += Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void comb(int idx, int cnt) {
		if(idx > N) return;
		
		if(cnt == half) {
			score();
		}
		
		select[idx] = true;
		comb(idx + 1, cnt + 1); // 선택 O
		select[idx] = false;
		comb(idx + 1, cnt); // 선택 X
	}

	private static void score() {
		int[] cnt = new int[2];
		int[][] team = new int[2][half];
		int[] score = new int[2];
		
		// select 참조 팀 분배
		for (int i = 1; i <= N; i++) {
			if(select[i]) team[0][cnt[0]++] = i;
			else team[1][cnt[1]++] = i;
		}
		
		for (int i = 0; i < half; i++) {
			for (int j = i + 1; j < half; j++) {
				// 팀원의 번호를 좌표값으로 하여
				int y1 = team[0][i], x1 = team[0][j];
				int y2 = team[1][i], x2 = team[1][j];
				// 점수를 계산
				score[0] += scoreBoard[y1][x1];
				score[1] += scoreBoard[y2][x2];
			}
		}
		// 최소값
		min = Math.min(min, Math.abs(score[0] - score[1]));
	}

}
