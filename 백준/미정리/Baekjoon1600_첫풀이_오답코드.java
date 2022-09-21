package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// visit배열을 따로 선언 하지 않고 map 안에서 처리
// 하지만 어떤 칸 y, x 좌표에 도착했을 때 그 곳으로 가는 방법이 최선이 아닐 수 있다.
// 2차원 배열로 visit처리를 하면 서로 다른 경로로 방문한 것을 구분할 수가 없어
// 한 좌표에 방문에 대한 것만 처리하기 때문에 다른 더 최선의 경로오는 방법을 막아버린다.
// 때문에 오답이 발생

public class Baekjoon1600_첫풀이_오답코드 {

	static final int obstacle = 1;
	static final int monVisited = -1; // 원숭이 이동방법으로 방문
	static final int horVisited = -2; // 말의 이동방법으로 방문
	static final int bothVisited = -3; // 두가지 방법으로 다 방문
	
	static int K, W, H;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		
		for (int y = 0; y < H; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < W; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		br.close();
	}

	// 원숭이 이동 반경
	static int[] my = { -1, 1,  0, 0 };
	static int[] mx = {  0, 0, -1, 1 };
	
	// 말 이동 반경
	static int[] hy = { -2, -2, -1, -1,  1, 1,  2, 2 };
	static int[] hx = { -1,  1, -2,  2, -2, 2, -1, 1 };
	
	private static void bfs() {
		Queue<Monkey> queue = new LinkedList<>();
		
		queue.offer(new Monkey(0, 0, K, 0));
		
		while( !queue.isEmpty() ) {
			Monkey mon = queue.poll();
			
			// 만약 도착했다면 끝내기
			// bfs는 먼저 도착한 것이 최단거리
			if(mon.y == H - 1 && mon.x == W - 1) {
				System.out.println(mon.move);
				return;
			}
			
			// 원숭이 이동 방식
			for (int i = 0; i < my.length; i++) {
				int ny = mon.y + my[i];
				int nx = mon.x + mx[i];
				
				// 범위를 벗어나면
				if( !(0 <= ny && ny < H && 0 <= nx && nx < W) ) continue;
				
				// 장애물이 있거나 이미 방문한 곳이라면
				if( map[ny][nx] == obstacle 
					|| map[ny][nx] == monVisited 
					|| map[ny][nx] == bothVisited ) continue;
				
				queue.offer(new Monkey(ny, nx, mon.k, mon.move + 1));
				
				map[ny][nx] = (map[ny][nx] == horVisited) ? bothVisited : monVisited;
			}
			
			// 말 이동 방식
			
			// k번을 다 썼다면 말 이동방식으로 불가능
			if(mon.k == 0) continue;
			
			for (int i = 0; i < hy.length; i++) {
				int ny = mon.y + hy[i];
				int nx = mon.x + hx[i];
				
				// 범위를 벗어나면
				if( !(0 <= ny && ny < H && 0 <= nx && nx < W) ) continue;
				
				// 장애물이 있거나 이미 방문한 곳이라면
				if( map[ny][nx] == obstacle 
					|| map[ny][nx] == horVisited 
					|| map[ny][nx] == bothVisited ) continue;
				
				queue.offer(new Monkey(ny, nx, mon.k -1, mon.move + 1));
				
				map[ny][nx] = (map[ny][nx] == monVisited) ? bothVisited : horVisited;
			}
			
		}
		
		System.out.println(-1);
	}

	static class Monkey {
		// 좌표, 남은횟수, 이동횟수
		int y, x, k, move;

		public Monkey(int y, int x, int k, int move) {
			super();
			this.y = y;
			this.x = x;
			this.k = k;
			this.move = move;
		}
		
	}
}