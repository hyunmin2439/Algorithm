package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1600 {

	static int K, W, H;
	static boolean[][] map;
	static boolean[][][] visit;
	
	static int[] my = { -1, 1,  0, 0 };
	static int[] mx = {  0, 0, -1, 1 };
	
	static int[] hy = { -2, -2, -1, -1,  1, 1,  2, 2 };
	static int[] hx = { -1,  1, -2,  2, -2, 2, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		visit = new boolean[K + 1][H][W];
		
		for (int y = 0; y < H; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < W; x++) {
				if(Integer.parseInt(st.nextToken()) == 1) 
					map[y][x] = true;
			}
		}
		
		bfs();
		
		br.close();
	}

	private static void bfs() {
		Queue<Monkey> queue = new LinkedList<>();
		
		visit[K][0][0] = true;
		queue.offer(new Monkey(0, 0, K, 0));
		
		while( !queue.isEmpty() ) {
			Monkey mon = queue.poll();
			
			// 도착지 도착
			if(mon.y == H - 1 && mon.x == W - 1) {
				System.out.println(mon.m);
				return;
			}
			
			// 원숭이 이동 방식
			for (int i = 0; i < my.length; i++) {
				int ny = mon.y + my[i];
				int nx = mon.x + mx[i];
				
				// 범위를 벗어나면
				if( !(0 <= ny && ny < H && 0 <= nx && nx < W) ) continue;
				
				// 방문 했거나, 벽이 있으면
				if( visit[mon.k][ny][nx] || map[ny][nx] ) continue;
				
				visit[mon.k][ny][nx] = true;
				queue.offer(new Monkey(ny, nx, mon.k, mon.m + 1));
			}
			
			// 말 이동 횟수가 남아있지 않으면
			if(mon.k == 0) continue;
			
			// 말 이동 방식
			for (int i = 0; i < hy.length; i++) {
				int ny = mon.y + hy[i];
				int nx = mon.x + hx[i];
				
				// 범위를 벗어나면
				if( !(0 <= ny && ny < H && 0 <= nx && nx < W) ) continue;
				
				// 방문 했거나, 벽이 있으면
				if( visit[mon.k - 1][ny][nx] || map[ny][nx] ) continue;
				
				visit[mon.k - 1][ny][nx] = true;
				queue.offer(new Monkey(ny, nx, mon.k - 1, mon.m + 1));
			}
			
		}
		
		// 도착지로 가지 못함
		System.out.println(-1);
	}

	static class Monkey {
		// 좌표, 남은 k, 이동 수
		int y, x, k, m;

		public Monkey(int y, int x, int k, int m) {
			this.y = y;
			this.x = x;
			this.k = k;
			this.m = m;
		}
	}
}