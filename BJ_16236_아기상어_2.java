package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs를 통해 먹을 수 있는 물고기 탐색
// bfs 탐색 중 먹을 수 있는 최소 물고기 보다 거리가 먼 곳은 가지치기
// PriorityQueue를 통해 거리 짧고 y, x값이 작은 물고기 가져옴
// 상어 위치 및 상태 변경
// 더 이상 물고기를 먹을 수 없을 때까지 반복

// memory:14728KB / time:136ms
public class BJ_16236_아기상어_2 {

	static int N, time;
	static int[][] map;
	static Fish shark;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static Queue<Fish> queue;
	static PriorityQueue<Fish> pqueue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		queue = new LinkedList<>();
		pqueue = new PriorityQueue<>();
		
		for (int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				if(map[y][x] == 9) shark = new Fish(y, x, 2, 0);
			}
		}
		// debugging용
//		System.out.println("상어 " + shark.y + " " + shark.x + " 위치로 이동 먹은수:" + shark.de + " 크기:" + shark.size + " 이동거리:" + time );
//		printMap();
		
		while(true) {
			Fish fish = bfs();
			
			// 더이상 먹을 물고기가 없다면
			if(fish == null) break;
			
			shark.eatFish(fish.y, fish.x, fish.de);
			
			// debugging용
//			System.out.println("상어 " + shark.y + " " + shark.x + " 위치로 이동 먹은수:" + shark.de + " 크기:" + shark.size + " 이동거리:" + time );
//			printMap();
		}
		
		System.out.println(time);
		
		br.close();
	}
	
	private static void printMap() {
		System.out.println();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static Fish bfs() {
		int minDist = Integer.MAX_VALUE;
		
		boolean visit[][] = new boolean[N][N];
		// 메모리가 초과가 난다면 new로 새로 생성하는 것 보다 초기화를 하는것이 낫다.
		
		queue.clear();
		pqueue.clear();
		
		visit[shark.y][shark.x] = true;
		queue.offer(new Fish(shark.y, shark.x, 0));
		
		while( !queue.isEmpty() ) {
			Fish fish = queue.poll();
			
			// 해당 위치가 물고기 일 때
			if( 1 <= map[fish.y][fish.x] && map[fish.y][fish.x] <= 6 ) {
				// 물고기 크기가 상어보다 작고 거리가 최고 거리보다 작거나 같을 때
				if(shark.size > fish.size && fish.de <= minDist) {
					minDist = fish.de;
					pqueue.offer(fish);
				}
			}
			
			// 거리가 더 멀어진다면 더 이상 큐에 추가할 필요가 없음
			if(fish.de >= minDist) continue;
			
			for (int i = 0; i < dy.length; i++) {
				int ny = fish.y + dy[i];
				int nx = fish.x + dx[i];
				
				// 범위 벗어나면
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				
				// 상어보다 큰 물고기가 있거나 방문한 곳이라면
				if( shark.size < map[ny][nx] || visit[ny][nx]) continue;
				
				visit[ny][nx] = true;
				queue.offer(new Fish(ny, nx, map[ny][nx], fish.de + 1));
			}
		}
		
		return pqueue.poll();
	}
	
	static class Fish implements Comparable<Fish> {
		// 좌표, 크기, de : 물고기(거리), 상어(먹은 물고기 수)
		int y, x, size, de;

		public Fish(int y, int x, int de) {
			this.y = y;
			this.x = x;
			this.de = de;
		}
		
		public Fish(int y, int x, int size, int de) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.de = de;
		}
		
		
		@Override
		public int compareTo(Fish other) {
			if(this.de == other.de) {
				if(this.y == other.y) 
					return this.x - other.x;
				
				return this.y - other.y;
			}
			
			return this.de - other.de;
		}
		
		// 상어의 메소드
		public void eatFish(int y, int x, int dist) {
			// 상어 위치 옮기기
			map[shark.y][shark.x] = 0;
			
			this.y = y;
			this.x = x;
			
			map[shark.y][shark.x] = 9;
			
			de++;
			
			if(size == de) {
				size++;
				de = 0;
			}
			
			time += dist;
		}
	}
}
