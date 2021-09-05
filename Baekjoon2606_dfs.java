package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// memory:14168KB / time:132ms
// disjointSet을 이용한 방법이랑 큰 차이가 나진 않음.
public class Baekjoon2606_dfs {

	static int N, link, res;
	static boolean[] visit;
	static boolean[][] adjMatrix;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 컴퓨터 대수
		N = Integer.parseInt(br.readLine());
		// 연결된 컴퓨터 대수
		link = Integer.parseInt(br.readLine());
		
		visit = new boolean[N + 1]; // 0: dummy
		adjMatrix = new boolean[N + 1][N + 1]; // 0: dummy
		
		for (int i = 0; i < link; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = adjMatrix[b][a] = true;
		}
		
		dfs(1);
		
		System.out.println(res);
		br.close();
	}

	private static void dfs(int x) {
		visit[x] = true;
		
		for (int i = 1; i <= N; i++) {
			if( !visit[i] && adjMatrix[x][i] ) {
				res++;
				visit[i] = true;
				dfs(i);
			}
		}
	}
}
