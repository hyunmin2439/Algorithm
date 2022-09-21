package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * copyArea를 작성할 때 clone() 메서드를 사용했으나 문제가 생김.
 * 
 * 1차원 배열 안에 각 객체가 주소 값을 가지고 있기 때문에 DeepCopy가 안된다.
 * 
 * 때문에 for문을 돌면서 new로 객체를 새로 생성하면서 값을 넣어서 copy를 해야 한다.
 * 
 * 이 부분의 문제인지 모르고 다른 부분을 자꾸 수정하다가 굉장히 애를 먹은 문제.
 * 
 * Memory: 14364KB / Time:132ms
 */
public class BJ_19236_청소년상어_1_DFS {

	private static final int EMPTY = -1, SHARK = 0;
	private static int maxEatNum;
	
	// 							 ↑,  ↖,  ←,  ↙, ↓, ↘, →,  ↗
	private static int[] dy = { -1, -1,  0,  1, 1, 1, 0, -1 };
	private static int[] dx = {  0, -1, -1, -1, 0, 1, 1,  1 };
	
	public static void main(String[] args) throws Exception {
		Fish[][] area = input();
		
		// 0, 0의 물고기 먹으면서 시작
		Shark shark = new Shark(0, 0, area[0][0].dir, area[0][0].num);
		
		dfs(area, 0, 0, shark);
		
		System.out.print(maxEatNum);
	}
	
	private static void dfs(Fish[][] originArea, int prevY, int prevX, Shark shark) {
		// Fish 배열 복제
		Fish[][] area = copyArea(originArea, shark);
		
		// 상어 물고기 먹기
		eatFish(area, prevY, prevX, shark);
		
		// printArea(area);
		
		maxEatNum = Math.max(maxEatNum, shark.eatNum);
		
		// 물고기의 이동
		moveFish(area);
		
		// 상어의 이동 (재귀)
		moveShark(area, shark);
	}
	
	private static Fish[][] copyArea(Fish[][] originArea, Shark shark) {
		Fish[][] area = new Fish[4][4];
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				if(originArea[y][x].num == SHARK)
					area[y][x] = new Shark(shark.y, shark.x, shark.dir, shark.eatNum);
				else
					area[y][x] = new Fish(originArea[y][x].y, originArea[y][x].x, originArea[y][x].num, originArea[y][x].dir);
			}
		}
		
		return area;
	}

	private static void eatFish(Fish[][] area, int prevY, int prevX, Shark shark) {
		area[prevY][prevX] = new Fish(prevY, prevX, EMPTY, -1);
		area[shark.y][shark.x] = shark;
	}

	private static void moveFish(Fish[][] area) {
		Fish fish, other;
		
		// 번호 순으로 물고기를 이동시키기 위해 우선순위큐 생성
		PriorityQueue<Fish> pqueue = createPQueue(area);
		
		while( !pqueue.isEmpty() ) {
			fish = pqueue.poll();
			
			for(int d = 0; d < 8; d++) {
				int dir = (fish.dir + d) % 8; // 8방향
				int ny = fish.y + dy[dir];
				int nx = fish.x + dx[dir];
				
				if( !(0 <= ny && ny < 4 && 0 <= nx && nx < 4) ) continue;
				
				if( area[ny][nx].num == SHARK ) continue;
				
				other = area[ny][nx];
				area[ny][nx] = area[fish.y][fish.x];
				area[fish.y][fish.x] = other;
				
				other.y = fish.y;
				other.x = fish.x;
				
				fish.y = ny;
				fish.x = nx;
				fish.dir = dir;
				
				break;
			}
			
		}
	}
	
	private static PriorityQueue<Fish> createPQueue(Fish[][] area) {
		PriorityQueue<Fish> pqueue = new PriorityQueue<>( (a,b) -> a.num - b.num );
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				// 상어, 물고기 없는 것은 빼고 넣기
				if( area[y][x].num == SHARK || area[y][x].num == EMPTY ) continue;
				
				pqueue.offer(area[y][x]);
			}
		}
		
		return pqueue;
	}
	
	private static void moveShark(Fish[][] area, Shark shark) {
		int i = 0;
		
		while(true) {
			i++;
			int ny = shark.y + dy[shark.dir] * i;
			int nx = shark.x + dx[shark.dir] * i;
			
			// 좌표 벗어나면 끝
			if( !(0 <= ny && ny < 4 && 0 <= nx && nx < 4) ) return;
			
			// 비어있으면 못감
			if( area[ny][nx].num == EMPTY ) continue;
			
			Shark newShark = new Shark(ny, nx, area[ny][nx].dir, shark.eatNum + area[ny][nx].num);
			dfs(area, shark.y, shark.x, newShark);
		}
	}
	
	private static Fish[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		Fish[][] area = new Fish[4][4];
		
		for(int y = 0; y < 4; y++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int x = 0; x < 4; x++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				area[y][x] = new Fish(y, x, num, dir);
			}
		}
		
		in.close();
		
		return area;
	}
	
	private static void printArea(Fish[][] area) {
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				System.out.print( area[y][x] + " ");
			}
			System.out.println();
		}
		
		System.out.println("--------------------------------------------");
	}

	static class Fish {
		int y, x, num, dir;

		public Fish(int y, int x, int num, int dir) {
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "[num=" + num + ", dir=" + dir + "]";
		}
	}
	
	static class Shark extends Fish {
		int eatNum;
		
		public Shark(int y, int x, int dir, int eatNum) {
			super(y, x, SHARK, dir);
			this.eatNum = eatNum;
		}

		@Override
		public String toString() {
			return "[eat=" + eatNum + ", dir=" + dir + "]";
		}
	}
}
