package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Dijkstra 사용 최단 경로 구하기

// Memory:110768KB / Time:2704
public class BJ_1753_최단경로_1_Dijkstra {
	
	static final int INFINITY = 1_000_000;
	
	static int V, E, S; // 정점개수, 간선개수, 시작 정점
	static boolean[] visit;
	static int[] minDist;
	
	static List<ArrayList<Edge>> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(in.readLine());
		
		visit = new boolean[V + 1];
		minDist = new int[V + 1];
		
		edges = new ArrayList<>();
		
		for (int i = 0; i <= V; i++) {
			edges.add(new ArrayList<>());
			minDist[i] = INFINITY;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges.get(u).add(new Edge(v, w));
		}
		
		dijkstra();
		
		for (int i = 1; i <= V; i++) {
			System.out.println(minDist[i] != INFINITY ? minDist[i] : "INF");
		}
		
		in.close();
	}

	private static void dijkstra() {
		int cnt = 1, u = S, min = INFINITY;
		
		// 시작 정점 정보 갱신
		visit[S] = true;
		minDist[S] = 0;
		
		// 시작 정점에서 다른 정점까지 비용 갱신
		for(Edge e : edges.get(S)) {
			// 간선 여러개 존재
			if(minDist[e.v] > e.w) {
				minDist[e.v] = e.w;
			}
		}
		
		while(cnt < V) {
			min = INFINITY;
			
			for (int i = 1; i <= V; i++) {
				// 방문한 정점이 아니고 그 정점으로 가는 비용이 작으면
				if(!visit[i] && min > minDist[i]) {
					u = i;
					min = minDist[i];
				}
			}
			
			// 방문처리
			visit[u] = true;
			cnt++;
			
			for (Edge e : edges.get(u)) {
				// s -> v로 가는 비용 > s -> u -> v 가는 비용
				if(minDist[e.v] > minDist[u] + e.w) {
					minDist[e.v] = minDist[u] + e.w;
				}
			}
		}
	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
