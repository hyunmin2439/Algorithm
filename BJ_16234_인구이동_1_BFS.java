package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동_1_BFS {

	static int N, L, R, day, totPopulOfUnion;
	static boolean isDone; // 인구이동이 종료되었는지
	
	static int[][] population; // 나라별 인구수
	static boolean[][] visited; // 방문체크
	
	static Queue<Pos> queue;
	static List<Pos> unionList; // 연합리스트
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		// main Process
		// 인구이동이 종료되지 않았으면
		while( !isDone ) {
			isDone = true; // 종료되었다고 가정
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					if( !visited[y][x] ) {
						bfs(y, x); // 연합이 될 국가 조사
						
						if(unionList.size() > 1) {
							isDone = false; // 인구이동 있으므로 아직 끝나지 않음
							movePopul(); // 인구 이동
						}
					}
				}
			}
			
			// 인구이동이 있었으면 날짜증가
			if( !isDone ) day++;
			
			resetVisited(); // 방문 배열 초기화
		}
		
		System.out.print(day);
	}
	
	private static void movePopul() {
		int avgPopul = totPopulOfUnion / unionList.size();
		
		for (Pos pos : unionList) {
			population[pos.y][pos.x] = avgPopul;
		}
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		queue = new LinkedList<>();
		unionList = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		population = new int[N][N];
		visited = new boolean[N][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				population[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
	}

	private static void bfs(int y, int x) {
		unionList.clear(); // 이전 목록 초기화
		totPopulOfUnion = 0; // 연합 총 인구수 초기화
		
		Pos pos = new Pos(y, x, population[y][x]);
		queue.offer(pos);
		unionList.add(pos);
		totPopulOfUnion += pos.popul;
		visited[y][x] = true;
		
		while( !queue.isEmpty() ) {
			pos = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int ny = pos.y + dy[d];
				int nx = pos.x + dx[d];
				
				// 좌표를 벗어난다면
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				
				// 이미 방문했던 곳이라면
				if( visited[ny][nx] ) continue;
				
				// 인구 차이가 L R 사이가 아니라면
				int diffPopul = Math.abs(pos.popul - population[ny][nx]);
				if( !(L <= diffPopul && diffPopul <= R) ) continue;
				
				// 위 3가지 조건을 만족했을때 연합 추가
				Pos newPos = new Pos(ny, nx, population[ny][nx]);
				queue.offer(newPos);
				unionList.add(newPos);
				totPopulOfUnion += newPos.popul;
				visited[ny][nx] = true;
			}
		}
		
	}
	
	private static void resetVisited() {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				visited[y][x] = false;
			}
		}
	}

	private static void printPopulation() {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.print(population[y][x] + " ");
			}
			System.out.println();
		}
	}
	
	static class Pos {
		int y, x, popul;

		public Pos(int y, int x, int popul) {
			this.y = y;
			this.x = x;
			this.popul = popul;
		}
	}
}
