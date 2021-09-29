package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘을 사용

// 각 좌표 (0, 0), (0, 1) ... (N-1, N-1), 0 ~ N 정점으로 생각

// 이전 풀이에서는 인접리스트를 만들어 사용했지만, 

// 시간복잡도만 높아지고 인접리스트를 만들어 사용할 필요가 없다.

// 정점, 정점번호가 있어야 한다는 무의식 중 고정관념 때문에 인접리스트로 바꿔 품

// 각 그리드 하나하나 마다 정점으로 생각하고, 그리드의 값이 비용이라고 생각하고 풀면 된다

// visit 체크를 사용해서 풀었으나, visit 체크를 안 한 것이 메모리, 시간 복잡도가 줄어듬.

// if( dp[ny][nx] <= dp[e.y][e.x] + map[ny][nx]) 비용 체크부분이 visit 체크 역할을 함

// 비용 체크 부분에서 방문한 곳은 이미 적은 비용으로 갱신되어 있는 상태

// 다른 길로 둘러서 갈 경우 그 값은 더 적은 비용이 될 수가 없음, 때문에 visit체크하지 않아도 무방

// Memory:19476KB / Time:252ms
public class Baekjoon4485_Dijkstra_최적풀이 {

	static final int INF = 1_000_000;
	
	static int t, N;
	static int[][] map, dp;
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(in.readLine());
			
			if(N == 0) break; // 종료 조건
			
			map = new int[N][N]; // 비용정보
			dp = new int[N][N]; // 해당 정점으로 가는 누적비용
			
			for (int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					dp[y][x] = INF;
				}
			}
			
			// 다익스트라
			dijkstra();

			// 결과 출력
			System.out.println("Problem " + ++t + ": " + dp[N-1][N-1]);
		}
		
		in.close();
	}

	private static void dijkstra() {
		// 다익스트라는 Greedy Algorithm
		// 정점으로 가는 비용이 가장 작은 것을 우선으로 선택
		PriorityQueue<Edge> queue = new PriorityQueue<>( (a, b) -> a.c - b.c );
		
		// 0번 정점부터 시작
		dp[0][0] = map[0][0];
		queue.offer(new Edge(0, 0, map[0][0]));
		
		while( !queue.isEmpty() ) {
			// 선택된 정점
			Edge e = queue.poll();
			
			// 사방탐색 : 갈 수 있는 정점
			for (int d = 0; d < 4; d++) {
				int ny = e.y + dy[d];
				int nx = e.x + dx[d];
				
				// 좌표 범위 체크 및 방문 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				
				// ny,nx 좌표의 누적비용이 e정점을 거쳐서 가는 비용보다 작거나 같으면
				if( dp[ny][nx] <= dp[e.y][e.x] + map[ny][nx]) continue;
				
				// e정점을 거쳐서 가는 것이 더 적은 비용 -> 갱신
				dp[ny][nx] = dp[e.y][e.x] + map[ny][nx];
				queue.offer(new Edge(ny, nx, dp[ny][nx]));
			}
		}
	}
	
	// 간선 - 정점, 좌표(y, x)로 간다 어떤 것으로 생각해도 무방
	static class Edge {
		int y, x, c;

		public Edge(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
		
	}
	
}