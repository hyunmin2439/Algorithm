package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon2636 {

	static final int air = 0;
	static final int cheese = 1;
	static final int visited = -1;
	
	static int row, col, time, cnt;
	static int[][] pan;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		pan = new int[row][col];
		
		for (int y = 0; y < row; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < col; x++) {
				pan[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(time + "\n" + cnt);
		
		br.close();
	}
	
	static int[] dy = { -1, 1, 0, 0};
	static int[] dx = { 0, 0, -1, 1};

	private static void bfs() {
		Queue<int[]> airQue = new LinkedList<>();
		Queue<int[]> cheeseQue = new LinkedList<>();
		
		airQue.add(new int[] {0, 0});
		pan[0][0] = visited; // 방문 체크
		
		while(true) {
			
			// 공기에서 치즈 찾는 과정
			while( !airQue.isEmpty() ) {
				int y = airQue.peek()[0];
				int x = airQue.poll()[1];
				
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					
					// 좌표가 범위 벗어나거나 방문한 곳이면
					if( !(0 <= ny && ny < row && 0 <= nx && nx < col) || pan[ny][nx] == visited ) continue;
					
					if(pan[ny][nx] == air) airQue.offer(new int[] {ny, nx});
					else if(pan[ny][nx] == cheese) cheeseQue.offer(new int[] {ny, nx});
					
					pan[ny][nx] = visited; // 방문 체크
				}
				
			}
			
			// 치즈가 하나도 없으면 종료
			if(cheeseQue.size() == 0) return;
			cnt = cheeseQue.size();
			time++;
			
			// 치즈 -> 공기
			while( !cheeseQue.isEmpty() ) airQue.offer(cheeseQue.poll());
		}
		
	}
}
