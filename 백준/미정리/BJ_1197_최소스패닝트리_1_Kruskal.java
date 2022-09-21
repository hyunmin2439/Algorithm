package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Kruskal 알고리즘
 * 
 * 1. 간선정보 입력받기
 * 
 * 2. 가중치 값으로 정렬
 * 
 * 3. 정렬된 간선들을 차례대로 순회하며 사이클 발생되는 간선은 패스
 * 
 * 4. 사이클 발생하지 않는 간선들을 선택
 * 
 * 5. 최종 총 정점개수 - 1의 간선들이 선택되면 MST(Minimum Spanning Tree) 완성
 * 
 * Memory:50468KB / Time:700ms
 */

public class BJ_1197_최소스패닝트리_1_Kruskal {

	static int V, E;
	static int[] parent;
	static Edge[] list;
	
	public static void main(String[] args) throws Exception {
		input();
		
		// 간선리스트 정렬
		Arrays.sort(list);
		
		int idx = 0, cnt = 0, totCost = 0;;
		
		// 정점개수 - 1개의 간선이 선택될 때 까지
		while(cnt < V - 1) {
			
			// 두 정점이 사이클을 이루지 않는다면
			if( union(list[idx].v1, list[idx].v2) ) {
				totCost += list[idx].c; // 총 가중치 더하기
				cnt++; // 선택된 간선 개수 추가
			}
			idx++; // 다음 간선으로
		}
		
		System.out.print(totCost);
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1]; // 0: dummy
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		list = new Edge[E];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[i] = new Edge(v1, v2, c);
		}
		
		in.close();
	}
	
	// 해당정점 부모를 찾는 함수
	public static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	// 두 정점이 사이클을 이루는지 확인
	public static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) return false;
		
		if(aParent > bParent) parent[bParent] = aParent;
		else parent[aParent] = bParent;
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
