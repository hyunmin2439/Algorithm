import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	
	private static final int MINE = -1;
	private static int[] dy = { -1, -1, -1, 0, 1, 1,  1,  0 },
						 dx = { -1,  0,  1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(200);
		
		Queue<int[]> queue = new LinkedList<>();
		int t = 0, T, N, mineCnt = 0, ny, nx, map[][];
		char[] str;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			mineCnt = 0;
			
			for(int y = 0; y < N; y++) {
				str = in.readLine().toCharArray();
				for(int x = 0; x < N; x++) {
					if(str[x] != '*') continue;
					map[y][x] = -1;
					mineCnt++;
					
					for(int d = 0; d < dy.length; d++) {
						ny = y + dy[d];
						nx = x + dx[d];
						
						if( isOut(N, ny, nx) || 
							map[ny][nx] == MINE ) continue;
						
						map[ny][nx]++; // 마인 숫자 미리 표시
					}
				}	
			}

			 sb.append("#").append(t).append(" ")
			 .append(simulation(queue, map, N, mineCnt))
			 .append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static int simulation(Queue<int[]> queue, int[][] map, int N, int mineCnt) {
		boolean[][] visited = new boolean[N][N];
		int clickCNt = 0, leftCellCnt = N * N - mineCnt;

		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(map[y][x] == 0) {
					leftCellCnt -= bfs(queue, visited, map, N, y, x);
					clickCNt++;
				}
					
			}
		}
		
		return clickCNt + leftCellCnt;
	}
	
	private static int bfs(Queue<int[]> queue, boolean[][] visited, int[][] map, int N, int y, int x) {
		int cnt = 1, ny, nx, pos[];
		
		visited[y][x] = true;
		queue.offer(new int[] {y, x});
		
		while( !queue.isEmpty() ) {
			pos = queue.poll();
			
			if( map[pos[0]][pos[1]] != 0 ) continue;
			
			for(int d = 0; d < dy.length; d++) {
				ny = pos[0] + dy[d];
				nx = pos[1] + dx[d];
				
				if( isOut(N, ny, nx) || 
					visited[ny][nx] || 
					map[ny][nx] == MINE ) continue;
				
				cnt++;
				visited[ny][nx] = true;
				queue.offer(new int[] {ny, nx});
			}
		}
		
		return cnt;
	}
	
	private static boolean isOut(int N, int y, int x) {
		return !(0 <= y && y < N && 0 <= x && x < N);
	}
}