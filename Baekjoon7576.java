package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon7576 {

	static int N, M, remain, day;
	static int[][] box;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static List<Node> list = new ArrayList<>(); // 익은 토마토 위치
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// init input
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로(열)
		N = Integer.parseInt(st.nextToken()); // 세로(행)
		
		box = new int[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				box[y][x] = Integer.parseInt(st.nextToken());
				if(box[y][x] == 0) 
					remain++; // 익지 않은 토마토 개수 체크
				else if(box[y][x] == 1) 
					list.add(new Node(y, x)); // 익은 토마토 위치 담기
			}
		}
		
		if(remain != 0) bfs();
		if(remain == 0) System.out.print(day);
		else System.out.print(-1);
		br.close();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();

		while( remain > 0 ) {
			int today = 0; // 익은 토마토 개수 세기 위함
			queue.addAll(list);
			list.clear();
			
			// 하루 사이클
			while( !queue.isEmpty() ) {
				Node pos = queue.poll();
				
				for (int i = 0; i < dy.length; i++) {
					int ny = pos.y + dy[i];
					int nx = pos.x + dx[i];
					
					if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
					
					if(box[ny][nx] == 0) {
						list.add(new Node(ny, nx));
						box[ny][nx] = 1;
						remain--;
						today++;
					}
				} // for end
			} // 하루 사이클 end
			
			// 오늘 익은 토마토 개수가 없으면
			if(today == 0) break;
			day++;
		}
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
