package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 15683 감시
public class BJ_15683_감시 {

	// N * M 맵 / K : CCTV 최대개수 / res : 사각지대 최소 크기
	static int N, M, K, res;
	static int[][] origMap; // 원본 맵
	
	// 방향 		    상 우  하 좌
	static int[] dy = { -1, 0, 1,  0 };
	static int[] dx = {  0, 1, 0, -1 };
	
	static List<CCTV> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input & init
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		origMap = new int[N][M];
		res = Integer.MAX_VALUE;
		list = new ArrayList<>();
		
		// map 입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				origMap[y][x] = Integer.parseInt(st.nextToken());
				if(0 < origMap[y][x] && origMap[y][x] < 6) 
					list.add(new CCTV(y, x, origMap[y][x]));
			}		
		}
		K = list.size();
				
		// process
		recur(0);
		
		System.out.print(res);
		br.close();
	}
	
	private static void recur(int idx) {
		// 기저조건
		if( idx == K ) {
			res = Math.min(res, blindSpot());
			return;
		}
		
		// 재귀
		CCTV cctv = list.get(idx);
		for (int i = 0; i < cctv.m; i++) {
			cctv.d = i;
			recur(idx + 1);
		}
	}
	
	// 사각지대 개수를 계산하는 메서드
	private static int blindSpot() {
		int cnt = 0; // 사각지대 개수
		int map[][] = copyMap();
		
		// 감시영역 채우기
		for (int i = 0; i < K; i++) {
			observe(map, list.get(i));
		}
		
		// 사각지대 개수 세기
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if(map[y][x] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	// 맵 복제본 만드는 메소드
	private static int[][] copyMap() {
		int map[][] = new int[N][];
		
		for (int i = 0; i < N; i++) {
			map[i] = origMap[i].clone(); // 복사
		}
		
		return map;
	}
	
	// 맵 감시영역 채우는 메소드
	private static void observe(int[][] map, CCTV cctv) {
		switch(cctv.k) {
		case 1: // 한 방향 감시 카메라
			fill(map, cctv.y, cctv.x, cctv.d);
			break;
		case 2: // 양 방향 감시 카메라
			fill(map, cctv.y, cctv.x, cctv.d);
			fill(map, cctv.y, cctv.x, cctv.d + 2);
			break;
		case 3:  // 직각 방향 감시 카메라
			fill(map, cctv.y, cctv.x, cctv.d);
			fill(map, cctv.y, cctv.x, cctv.d == 3 ? 0 : cctv.d + 1);
			break;
		case 4: // 세 방향 감시 카메라
			for (int i = 0; i < 4; i++) {
				// 4종류 방향 종류에 따라 그 방향 빼고 나머지 돌기
				if(i != cctv.d)
					fill(map, cctv.y, cctv.x, i);
			}
			break;
		case 5: // 네 방향 감시 카메라
			for (int i = 0; i < 4; i++) {
				fill(map, cctv.y, cctv.x, i);
			}
			break;
		}
	}
	
	// 감시 영역 채우기
	private static void fill(int[][] map, int y, int x, int d) {		
		while(true) {
			// 방향에 따른 좌표 이동
			y += dy[d]; x += dx[d];
			
			// 범위 벗어나면 멈춤
			if( !(0 <= y && y < N && 0 <= x && x < M ) ) break;
			
			// 벽만나면 멈춤
			if( map[y][x] == 6) break;
			
			// 채우기
			map[y][x] = 9;
		}
	}
	
	// CCTV 정보를 담기 위한 클래스
	static class CCTV {
		int y;
		int x;
		int k; // CCTV 1 ~ 5 종류
		int m; // K에 따른 방향 종류 최대값
		int d; // 방향 0 상 1 우 2 하 3 좌
		
		public CCTV(int y, int x, int k) {
			super();
			this.y = y;
			this.x = x;
			this.k = k;
			this.d = 0;
			
			switch(k) {
			case 1: case 3: case 4:
				this.m = 4; break;
			case 2: 
				this.m = 2; break;
			case 5: 
				this.m = 1; break;
			}
		}
		
		@Override
		public String toString() {
			return "CCTV [y=" + y + ", x=" + x + ", d=" + d + "]";
		}
		
	}
}
