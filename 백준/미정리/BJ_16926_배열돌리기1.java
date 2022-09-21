package BJ_algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1 {
	static int N, M, R;
	static int[][] area;
	
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));   
		BufferedWriter  bw  = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder   sb = new StringBuilder(); 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		area = new int[N][M];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				area[y][x] = Integer.parseInt(st.nextToken());				
			}
		}

		rotate();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				sb.append(area[y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
	//                 우->하->좌->상
	static int[] dy = { 0, 1,  0, -1};
	static int[] dx = { 1, 0, -1,  0};
	static int sy, ey, sx, ex; // 색상 구분을 위해 static 변수로
	
	private static void rotate() {
		int rotateCnt = Math.min(N, M) / 2;
		
		// 회전 구간
		for (int i = 0; i < rotateCnt; i++) {
			sy = i; ey = N - i;
			sx = i; ex = M - i;
			// R만큼 회전
			for (int j = 0; j < R; j++) {
				int dir = 0;
				int y = i, x = i + 1; 
				int temp = area[y][x]; // 시작 지점에 들어갈 값

				// 시작 다음지점 부터 시작
				// 시작 지점으로 돌아오면 종료
				while( x != i || y != i ) { 
					// ny, nx 범위 테스트 임시값
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					// 범위를 벗어나면 방향 바꾸기
					if(ny < sy || ny >= ey || nx < sx || nx >= ex) {
						dir++;
						if(dir > 3) dir = 0;
					}
					// 값 옮기고 좌표 증가
					area[y][x] = area[y + dy[dir]][x + dx[dir]];
					y += dy[dir]; x += dx[dir];
				}
				
				area[y][x] = temp; // 시작 지점에 들어갈 값 넣음
			}
			
		}
	}
}
