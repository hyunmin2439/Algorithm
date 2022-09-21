package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Notion 알고리즘 설명
 * https://hyunmin2439.notion.site/11725-a0f2e4da84594bf2bf3fdaf9ba2e822c
 * 
 * Memory:65,284KB / Time:864ms
 */
public class BJ_11725_트리의부모찾기 {

	private static int N, parent[];
	private static boolean[] visited;
	private static List<List<Integer>> tree;
	
	public static void main(String[] args) throws Exception {
		input();

		solve();
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		tree = new ArrayList<List<Integer>>();
		StringTokenizer st = null;

		N = Integer.parseInt(in.readLine());
		parent = new int[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		
		int node1, node2;
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());
			
			tree.get(node1).add(node2);
			tree.get(node2).add(node1);
		}
		
		in.close();
	}
	
	public static void solve() {
		// 1이 Root 노드, 1부터 ~ N까지
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) dfs(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i <= N; i++) {
			sb.append(parent[i]).append('\n');
		}
		
		System.out.print(sb);
	}
	
	private static void dfs(int x) {
		if(visited[x]) return; // 탐색한 곳 X
		
		visited[x] = true; // 방문 체크
		
		for(int i : tree.get(x)) {
			if(visited[i]) continue; // 탐색한 곳 X
			
			parent[i] = x; // 부모 표시
			dfs(i); // 재귀
		}
	}
	
}
