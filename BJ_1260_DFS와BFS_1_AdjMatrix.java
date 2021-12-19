package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//memory : 20372KB / time : 272ms
public class BJ_1260_DFS와BFS_1_AdjMatrix {
	
	// 정점의 개수, 간선의 개수, 시작 정점 번호
	static int N, M, V;

	static boolean[][] matrix;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		matrix = new boolean[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			matrix[from][to] = matrix[to][from] = true;
		}
		
		dfs(V, new boolean[N + 1]);
		sb.append("\n");
		bfs();
		
		System.out.println(sb.toString());
		br.close();
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		
		queue.offer(V);
		visit[V] = true;
		
		while( !queue.isEmpty() ) {
			int curr = queue.poll();
			sb.append(curr).append(" ");
			
			for (int i = 1; i <= N; i++) {
				// 방문하지 않았고, 연결되어 있는 정점이면
				if( !visit[i] && matrix[curr][i] ) {
					queue.offer(i);
					visit[i] = true;
				}
			}
		}
		
		sb.setLength(sb.length() - 1); // 마지막 공백지우기
		sb.append("\n");
	}
	
	private static void dfs(int curr, boolean[] visit) {
		
		sb.append(curr).append(" ");
		visit[curr] = true;
		
		for (int i = 1; i <= N; i++) {
			// 방문하지 않았고, 연결되어 있는 정점이면
			if( !visit[i] && matrix[curr][i] ) {
				dfs(i, visit);
			}
		}
	}

}
