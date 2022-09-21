package BJ_algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon17406 {
	static int N, M, K, min = Integer.MAX_VALUE;
	static int[][] area;
	static int[][] backup;
	static int[][] rcs;
	
	static int[] permu;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));   
		BufferedWriter  bw  = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		area = new int[N][M];
		backup = new int[N][M];
		rcs = new int[K][3];
		permu = new int[K];
		selected = new boolean[K];
		
		// 기존의 배열 입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				backup[y][x] = area[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// rcs 값 입력
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rcs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0);
		
		bw.write(String.valueOf(min));
		br.close();
		bw.close();
	}
	
	private static void printArea() {
		System.out.println();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				System.out.print(area[y][x] + " ");
			}
			System.out.println();
		}
	}

	private static void recur(int cnt) {
		if(cnt == K) {
			rotateAndCalc();
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			permu[cnt] = i;
			recur(cnt + 1);
			selected[i] = false;
		}
	}
	
	private static void rotateAndCalc() {
		// 정해진 순서대로 순열 돌기
		for (int i = 0; i < K; i++) {
			rotate(permu[i]);
		}
		printArea();

		// 계산
		for (int y = 0; y < N; y++) {
			int sum = 0;
			for (int x = 0; x < M; x++) {
				// 값 계산 후 원래대로 되돌리기
				sum += area[y][x];
				area[y][x] = backup[y][x];
			}
			min = Math.min(min, sum);
		}
	}

	//  			   하->우->상->좌
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int sy, ey, sx, ex; // 색상 구분을 위해 static 변수로

	private static void rotate(int idx) {
		// 0부터 시작하기 때문에 r c -1씩
		int r = rcs[idx][0] - 1;
		int c = rcs[idx][1] - 1;
		int s = rcs[idx][2];
		
		sy = r - s; ey = r + s;
		sx = c - s; ex = c + s;
		
		// 회전 구간
		for (int i = 0; i < s; i++) {
			int dir = 0;
			int y = sy + dy[dir], x = sx + dx[dir];
			int temp = area[y][x];

			// 시작 다음지점 부터 시작
			// 시작 지점으로 돌아오면 종료
			while (y != sy || x != sx) {
				// ny, nx 범위 테스트 임시값
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				// 범위를 벗어나면 방향 바꾸기
				if (ny < sy || ny > ey || nx < sx || nx > ex) {
					dir++;
					if (dir > 3)
						dir = 0;
				}
				// 값 옮기고 좌표 증가
				area[y][x] = area[y + dy[dir]][x + dx[dir]];
				y += dy[dir]; x += dx[dir];
			}

			area[y][x] = temp; // 시작 지점에 들어갈 값 넣음
			sy++; ey--; sx++; ex--; // 내부 사각형 돌기위해 범위 좁히기
		} // 회전 구간 end
	} // rotate method end
}
