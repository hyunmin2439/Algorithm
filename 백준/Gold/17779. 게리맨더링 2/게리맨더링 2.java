import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()),
			min = Integer.MAX_VALUE,
			popul[][] = new int[N + 1][N + 1],
			town[][] = new int[N + 1][N + 1];
		
		StringTokenizer st;
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				popul[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int d1 = N; d1 >= 1; d1--) {
			for(int d2 = N; d2 >= 1; d2--) {
				for(int x = N; x >= 1; x--) {
					for(int y = N; y >= 1; y--) {
						if( !(x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) ) continue;
						
						divideTown(town, N, x, y, d1, d2);
						
						min = Math.min(min, calDiffPopulation(town, popul, N));
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	private static int calDiffPopulation(int[][] town, int[][] popul, int N) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, acc[] = new int[6];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				acc[ town[r][c] ] += popul[r][c];
			}
		}
		
		for(int i = 1; i <= 5; i++) {
			min = Math.min(min, acc[i]);
			max = Math.max(max, acc[i]);
		}
		
		return max - min;
	}
	
	private static void divideTown(int[][] town, int N, int x, int y, int d1, int d2) {
		// 초기화
		for(int r = 1; r <= N; r++) {
			Arrays.fill(town[r], 0);
		}
		
		// 5 구역 경계선 만들기
		for(int d = 0; d <= d1; d++) {
			town[x + d][y - d] = 5;
			town[x + d2 + d][y + d2 - d] = 5;
		}
		
		for(int d = 0; d <= d2; d++) {
			town[x + d][y + d] = 5;
			town[x + d1 + d][y - d1 + d] = 5;
		}
		
		bfs(town, N, x + 1, y);
		
		for(int r = N - 1; r >= 2; r--) {
			for(int c = N - 1; c >= 2; c--) {
				if(town[r][c] == 0 && town[r + 1][c] == 5 && town[r][c + 1] == 5 && town[r - 1][c] == 5 && town[r][c - 1] == 5)
					town[r][c] = 5;
			}
		}
		
		// 1 ~ 4 구역
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(town[r][c] != 0) continue;
				
				if(1 <= r && r < x + d1 && 1 <= c && c <= y)
					town[r][c] = 1;
				else if(1 <= r && r <= x + d2 && y < c && c <= N)
					town[r][c] = 2;
				else if(x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2)
					town[r][c] = 3;
				else if(x + d2 < r && r <= N && y - d1 + d2 <= c && c <= N)
					town[r][c] = 4;
			}
		}
	}
	
	private static void bfs(int[][] town, int N, int startR, int startC) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		int[] dr = { -1, 0, 1, 0 },
			  dc = { 0, -1, 0, 1 };
		
		queue.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		town[startR][startC] = 5;
		
		while( !queue.isEmpty() ) {
			int[] pos = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = pos[0] + dr[d];
				int nc = pos[1] + dc[d];
				
				if( !(1 <= nr && nr <= N && 1 <= nc && nc <= N) || visited[nr][nc] || town[nr][nc] == 5 ) continue;
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				town[nr][nc] = 5;
			}
		}
	}

}