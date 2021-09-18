package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon14502 {

	static final int SAFE = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	
	static int N, M, safeCnt, resCnt;
	static int[][] map;
	static int[] perm;
	
	static Queue<Pos> queue;
	static List<Pos> safeList, virusList;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		safeList = new ArrayList<>();
		virusList = new ArrayList<>();
		
		map = new int[N][M];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				if(map[y][x] == SAFE) {
					safeList.add(new Pos(y, x));
					safeCnt++;
				}
				else if(map[y][x] == VIRUS) {
					virusList.add(new Pos(y, x));
				}
			}
		}
		
		// nextPermutation 위한 배열 초기화
		perm = new int[safeCnt];
		Arrays.fill(perm, safeCnt - 3, safeCnt, 1);
		
		do {
			// 벽세우는 메서드
			changeWall();
			
			// 바이러스 퍼지고, safe 영역 받아와서 최대 값
			resCnt = Math.max(resCnt, bfs());
			
			// 벽 다시 되돌리는 메서드
			changeWall();
			
		} while(nextPermutation());
		
		System.out.println(resCnt);
		
		br.close();
	}
	
	private static void changeWall() {
		for (int i = 0; i < safeCnt; i++) {
			if(perm[i] == 1) {
				Pos pos = safeList.get(i);
				
				map[pos.y][pos.x] = map[pos.y][pos.x] == SAFE ? WALL : SAFE;
			}
		}
	}
	
	private static int bfs() {
		int safeArea = safeCnt - 3; // 벽 3개 세웠으니 -3
		boolean[][] visit = new boolean[N][M];
		
		// 바이러스 위치 큐에 추가
		for (int i = 0; i < virusList.size(); i++) {
			Pos pos = virusList.get(i);
			queue.offer(pos);
			visit[pos.y][pos.x] = true;
		}
		
		while( !queue.isEmpty() ) {
			Pos pos = queue.poll();
			
			for (int i = 0; i < dy.length; i++) {
				int ny = pos.y + dy[i];
				int nx = pos.x + dx[i];
				
				// 범위 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				// 방문한 곳이거나 벽이면
				if( visit[ny][nx] || map[ny][nx] == WALL ) continue;

				safeArea--;
				visit[ny][nx] = true;
				queue.offer(new Pos(ny, nx));
			}
		}
		
		return safeArea;
	}

	private static boolean nextPermutation() {
		int N = safeCnt - 1;
		
		int i = N;
		while(i > 0 && perm[i - 1] >= perm[i]) i--;
		
		if(i == 0) return false;
		
		int j = N;
		while(perm[i - 1] >= perm[j]) j--;
		swap(i - 1, j);
		
		int k = N;
		while(i < k) {
			swap(i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int temp = perm[i];
		perm[i] = perm[j];
		perm[j] = temp;
	}

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
