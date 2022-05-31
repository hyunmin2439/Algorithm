import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N, from, dest;
	
	private static final int INF = 100_000_001;
	
	public static void main(String[] args) throws Exception {
		int[][] adjMatrix = input();
		
		dijkstra(adjMatrix);
	}
	
	private static void dijkstra(int[][] adjMatrix) {
		int[] dist = adjMatrix[from].clone();
		boolean[] used = new boolean[N + 1];
		int cnt = 1, minIdx = 0, minDist = Integer.MAX_VALUE;
		
		used[from] = true;
		
		while(cnt < N) {
			minDist = Integer.MAX_VALUE;
			
			// 방문하지 않았고 거리가 최단인 정점 선택
			for(int i = 1; i <= N; i++) {
				if( !used[i] && dist[i] < minDist) {
					minDist = dist[i];
					minIdx = i;
				}
			}
			
			// 방문 처리 및 방문한 정점 개수 추가
			cnt++;
			used[minIdx] = true;
			
			// 거리 최소 값으로 갱신
			for(int i = 1; i <= N; i++) {
				if(from == i || minIdx == i) continue;
				dist[i] = Math.min(dist[i], dist[minIdx] + adjMatrix[minIdx][i]);
			}
		}

		System.out.println(dist[dest]);
	}

	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int M, A, B, cost;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		int[][] adjMatrix = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(adjMatrix[i], INF);			
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			// 같은 출발점, 도착점 다른 비용의 값이 들어올 수 있음
			adjMatrix[A][B] = Math.min(adjMatrix[A][B], cost);
		}
		
		st = new StringTokenizer(in.readLine());
		from = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		
		in.close();
		
		return adjMatrix;
	}
	
}