package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// memory : 18588KB / time : 236ms
public class Baekjoon1260_AdjList {
	
	// 정점의 개수, 간선의 개수, 시작 정점 번호
	static int N, M, V;

	static Node[] headList;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		headList = new Node[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			add(from, to);
			add(to, from);
		}
		
		dfs(V, new boolean[N + 1]);
		sb.setCharAt(sb.length() - 1, '\n'); // 마지막 공백 개행문자로 바꾸기
		bfs();
		
		System.out.println(sb.toString());
		br.close();
	}
	
	// 정점 번호에 따라 순서대로 넣기 위한 알고리즘
	private static void add(int from, int to) {
		Node newNode = new Node(to, null);
		Node curr = headList[from];
		
		// 공백 리스트이거나 첫번째 노드가 새로운 노드보다 큰 경우
		if(curr == null || curr.vertex > newNode.vertex) {
			newNode.link = curr;
			headList[from] = newNode;
			return;
		}
		else {
			while(true) {
				// 다음 노드가 없으면 현재 링크에 새로운 노드 연결
				if(curr.link == null) {
					curr.link = newNode;
					return;
				}
				// 새로운 노드의 정점보다 다음 노드의 정점이 더 크면
				else if(newNode.vertex <= curr.link.vertex) {
					// 현재 노드와 다음 노드 사이에 새로운 노드 넣기
					newNode.link = curr.link;
					curr.link = newNode;
					return;
				}
				
				curr = curr.link;
			}
		}
		
	}


	private static void dfs(int curr, boolean[] visit) {	
		// 현재 정점에 대한 처리
		sb.append(curr).append(" ");
		visit[curr] = true;
		
		for (Node node = headList[curr]; node != null; node = node.link ) {
			// 방문하지 않았으면 재귀
			if( !visit[node.vertex] )
				dfs(node.vertex, visit);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		
		// 첫 정점에 대한 처리
		queue.offer(V);
		visit[V] = true;
		
		// 큐가 빌때까지 반복
		while( !queue.isEmpty() ) {
			int curr = queue.poll();
			sb.append(curr).append(" ");
			
			// 인접리스트 순회
			for (Node node = headList[curr]; node != null; node = node.link ) {
				// 방문하지 않았으면 큐에 넣기
				if( !visit[node.vertex] ) {
					queue.offer(node.vertex);
					visit[node.vertex] = true;					
				}
			}
		}
	}
	
	static class Node {
		int vertex;
		Node link;
		
		public Node(int vertex, Node edge) {
			super();
			this.vertex = vertex;
			this.link = edge;
		}
	}
	
}
