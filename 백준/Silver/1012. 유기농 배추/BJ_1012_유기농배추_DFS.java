package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 유기농 배추
// 전체를 완전탐색하면서 DFS
public class BJ_1012_유기농배추_DFS {

    // T 테스트 케이스, N * M 행렬, K 배추 개수
	static int T, N, M, K;
	
	static boolean[][] field;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			 
			field = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			int cnt = 0; // 필요한 최소 배추 흰지렁이 수
			
			// field 전체 순회
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					// 배추가 있으면
					if(field[y][x]) {
						dfs(y, x);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
		br.close();
	}

	private static void dfs(int y, int x) {
		field[y][x] = false;
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// ny, nx가 범위를 벗어나거나, ny nx에 배추가 없을 경우
			if( !(0 <= ny && ny < N && 0 <= nx && nx < M && field[ny][nx]) ) continue;
			
			dfs(ny, nx);
		}
	}

}
