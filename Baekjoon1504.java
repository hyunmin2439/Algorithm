package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 특정한 최단 경로
 * 
 * 가중치가 있는 최단경로, A, B 정점을 방문하는 최단경로를 구하는 문제
 * 
 * Dijkstra Algorithm을 사용
 * 
 * 1번 정점, 주어진 두 정점(A, B)에서 다른 정점으로 최단경로를 구한 뒤
 * 
 * 1 -> A -> B -> N / 1 -> B -> A -> N 두 경로 중에서
 * 
 * 어느 경로가 더 빠른지 비교하여 최단 경로를 출력한다.
 * 
 * Memory:69192 / Time:736ms
 */

public class Baekjoon1504 {

	static final int INF = 100_000_000;
	
	static int N, E; // N:정점개수, E:간선 개수
	
	static int[] startVtx;
	static int[][] minDist;
	static List<ArrayList<Edge>> list;
	static PriorityQueue<Edge> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		init();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.get(v1).add(new Edge(v2, c)); // 간선 추가
			list.get(v2).add(new Edge(v1, c)); // 양방향이기 때문에 반대경로도 추가
		}
		
		st = new StringTokenizer(in.readLine());
		startVtx = new int[3];
		startVtx[0] = 1;
		startVtx[1] = Integer.parseInt(st.nextToken());
		startVtx[2] = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 3; i++) {
			dijkstra(i);			
		}
		
		printPath();
		
		in.close();
	}

	private static void init() {
		// 거리 배열 초기화
		minDist = new int[3][N + 1];
		for (int i = 0; i < 3; i++) {
			Arrays.fill(minDist[i], INF);
		}
		
		// list 초기화
		list = new ArrayList<>();
		int size = N + 1;
		for (int i = 0; i < size; i++) {
			list.add(new ArrayList<Edge>()); // 0: dummy
		}
		
		// 우선순위 큐 초기화
		queue = new PriorityQueue<>( (e1, e2) -> e1.c - e2.c );
	}

	private static void dijkstra(int idx) {
		boolean[] visited = new boolean[N + 1];
		
		int sv = startVtx[idx];
		minDist[idx][sv] = 0; // 시작 정점:0
		queue.offer(new Edge(sv, 0));
		
		while( !queue.isEmpty() ) {
			Edge se = queue.poll(); // 가중치 값 최소인 간선 선택
			
			if( visited[se.v] ) continue; // 이미 선택된 정점이면
			
			visited[se.v] = true; // 정점 선택 표시
			
			// 선택된 간선으로 갈 수 있는 정점에서 다른 정점으로 가는 간선들
			for (Edge ne : list.get(se.v)) {
				// se.v정점을 거쳐서 ne.v로 가는 비용이 더 적으면
				if(minDist[idx][ne.v] > minDist[idx][se.v] + ne.c) {
					minDist[idx][ne.v] = minDist[idx][se.v] + ne.c;
					queue.offer(new Edge(ne.v, minDist[idx][ne.v]));
				}
			}
		}
	}
	
	private static void printPath() {
		// 1 -> A -> B -> N
		int path1 = minDist[0][ startVtx[1] ] + minDist[1][ startVtx[2] ] + minDist[2][N];
		// 1 -> B -> A -> N
		int path2 = minDist[0][ startVtx[2] ] + minDist[2][ startVtx[1] ] + minDist[1][N];
		
		int minPath = Math.min(path1, path2); // 최단 경로
		
		System.out.println( (minPath >= INF) ? -1 : minPath ); // INF보다 같거나 큰 값은 경로가 없다는 의미
	}

	static class Edge {
		int v, c; // v2:이동하는 정점, c:비용

		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
