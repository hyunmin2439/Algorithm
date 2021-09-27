package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// MST문제
// 노드 수가 적으므로 Prim이 유리할 수 있음
// 하지만 둘다 해봐야 아는 문제

// 비용 c (1 ≤ c ≤ 10,000)

public class Baekjoon1922_Prim {
	
	static final int INFINITY = 1_000_000;

	static int N, M;
	static int[][] adjMatrix;
	static boolean[] visit;
	static int[] minEdge;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		adjMatrix = new int[N][N];
		visit = new boolean[N];
		minEdge = new int[N];
		
		// 도달 못하는 곳 체크를 위해서
		for (int i = 0; i < N; i++) {
			Arrays.fill(adjMatrix[i], INFINITY);
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = c;
			adjMatrix[b][a] = c;
		}
		
		prim();
		
		in.close();
	}

	private static void prim() {
		int cost = 0;
		int cnt = 1, V = 0, min = INFINITY;
		
		// 0번 정점부터 시작
		visit[0] = true;
		minEdge[0] = 0;
		
		for (int i = 1; i < N; i++) {
			minEdge[i] = adjMatrix[0][i];
		}
		
		System.out.println(Arrays.toString(minEdge));
		
		while(cnt < N) {
			min = INFINITY;
			
			// 가장 비용이 적은 정점 선택
			for (int i = 1; i < N; i++) {
				if( !visit[i] && min > minEdge[i] ) {
					V = i;
					min = minEdge[i];
				}
			}
			
			System.out.println("선택정점:" + V + " min:" + min);
			// 방문 처리, 비용 더하기
			cnt++;
			visit[V] = true;
			cost += minEdge[V];
			
			// 최소값 갱신
			for (int i = 0; i < N; i++) {
				if(visit[i]) continue;
				
				if(minEdge[i] > adjMatrix[V][i]) {
					minEdge[i] = adjMatrix[V][i];
				}
			}
			
			System.out.println(Arrays.toString(minEdge));
		}

		System.out.println(cost);
	}

}
