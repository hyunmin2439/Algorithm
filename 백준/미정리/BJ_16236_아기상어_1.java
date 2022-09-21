package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 첫 풀이 시 자신보다 큰 물고기 위를 못 지나간다는 조건을 놓침.
// 때문에 단순히 좌표값을 빼서 거리계산을 했으나 예제에서 틀림

// 이후 거리 계산에 bfs추가, 최종 풀었으나 코드가 복잡함
// list를 이용하여 거리에 따라 정렬하는 부분 불필요

public class BJ_16236_아기상어_1 {

	static int N, move;
	static List<Fish> list;
	static int[][]map;
	static Shark shark;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		shark = new Shark();
		list = new LinkedList<>();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				switch(map[y][x]) {
				case 0: break;
				case 9: shark.y = y; shark.x = x; map[y][x] = 0; break;
				default: list.add(new Fish(y, x, map[y][x])); break;
				}
				
			}
		}
		// debugging용
		System.out.println("상어 " + shark.y + " " + shark.x + " 위치로 이동 먹은수:" + shark.eat + " 크기:" + shark.size + " 이동거리:" + move );
		
		findFish();
		
		System.out.println(move);
		br.close();
	}

	private static void findFish() {
		boolean isEat = true;
		
		// 물고기를 먹었다면 계속 반복
		while(isEat) {
			isEat = false;
			
			// 이동한 상어의 위치에 따라 거리 다시 조정
			list.forEach( fish -> setDistance(fish) );
			
			// 정렬하기
			Collections.sort(list);
			
			// 먹을 물고기 찾기
			for (Iterator<Fish> iterator = list.iterator(); iterator.hasNext();) {
				Fish fish = iterator.next();
				
				// 이동할 수 없는 위치면
				if(fish.d >= 10000) continue;
				
				// 상어 사이즈가 물고기 사이즈 보다 크면
				if(shark.size > fish.size) {
					// 먹음 표시
					isEat = true;
					
					// 상어 정보 업데이트
					setSharkInfo(fish); 
					
					// 물고기 리스트에서 제거
					iterator.remove(); 
					break;
				}
			}
		}
	}
	
	private static void setSharkInfo(Fish fish) {
		// 물고기 먹기
		shark.eat++;
		
		// 상어 크기 증가
		if(shark.size == shark.eat) {
			shark.size++;
			shark.eat = 0;
		}
		
		// 이동거리 증가
		move += fish.d;
		
		// 상어 좌표 바꾸기
		shark.y = fish.y;
		shark.x = fish.x;
		
		// 맵에서 물고기 지우기
		map[shark.y][shark.x] = 0;
		
		// debugging용
		System.out.println("상어 " + shark.y + " " + shark.x + " 위치로 이동 먹은수:" + shark.eat + " 크기:" + shark.size + " 이동거리:" + move );
	}

	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = { 0,  0, -1, 1 };
	
	private static void setDistance(Fish fish) {
		boolean[][] visit = new boolean[N][N];
		Queue<Shark> queue = new LinkedList<>();
		queue.offer(new Shark(shark.y, shark.x, 0));
		visit[shark.y][shark.x] = true;
		
		while( !queue.isEmpty() ) {
			Shark temp = queue.poll();
			
			if(temp.y == fish.y && temp.x == fish.x) {
				fish.d = temp.move;
				return;
			}
			
			for (int i = 0; i < dy.length; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				
				// 범위를 벗어나거나 이미 방문한 곳이라면
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) || visit[ny][nx] ) continue;
				
				if( shark.size < map[ny][nx] ) continue;
				
				visit[ny][nx] = true;
				queue.offer(new Shark(ny, nx, temp.move + 1));
			}
			
		}
		
		fish.d = 10000; // 큰값
	}
	
	static class Fish implements Comparable<Fish>{
		// 좌표, 거리, 크기
		int y, x, d, size;

		public Fish(int y, int x, int size) {
			this.y = y;
			this.x = x;
			this.size = size;
		}

		@Override
		public int compareTo(Fish other) {
			if(this.d == other.d) {
				if(this.y == other.y)
					return this.x - other.x;
				
				return this.y - other.y;
			}
			
			return this.d - other.d;
		}
	}
	
	static class Shark {
		int y, x, size, eat, move;

		public Shark() {
			this.size = 2;
		}
		
		// eat 이동횟수로 활용
		public Shark(int y, int x, int move) {
			this.y = y;
			this.x = x;
			this.move = move;
		}
	}
}
