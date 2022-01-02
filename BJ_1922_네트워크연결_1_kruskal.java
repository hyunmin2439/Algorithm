package notSolved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// Memory:51312KB / Time:736ms

public class BJ_1922_네트워크연결_1_kruskal {

	static int N, M;
	
	static int[] parent;
	
	static List<Edge> edgeList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		// 간선리스트 비용순을 정렬하기
		Collections.sort(edgeList);
		
		int numOfConnected = 0, totCost = 0;
		
		for(int i = 0; i < M; i++) {
			Edge edge = edgeList.get(i);
			
			// 사이클을 이루지 않으면
			if( union(edge.v1, edge.v2) ) {
				// 총비용 더하기, 연결된 네트워크 선 수
				totCost += edge.c;
				numOfConnected++;
			}
			
			if(numOfConnected == N - 1) break;
		}

		System.out.print(totCost);
	}
	
	private static void input() throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		edgeList = new LinkedList<>();
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(v1, v2, c));
		}
		
		in.close();
	}
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}

	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) return false;
		
		if(aParent > bParent) 
			parent[bParent] = aParent;
		else
			parent[aParent] = bParent;
		
		return true;
	}
	
	static class Edge implements Comparable<Edge> {
		int v1, v2, c;

		public Edge(int v1, int v2, int c) {
			this.v1 = v1;
			this.v2 = v2;
			this.c = c;
		}

		@Override
		public int compareTo(Edge other) {
			return this.c - other.c;
		}
	}

}
