import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		List<List<Integer>> list = new ArrayList<>();
		int N, M, a, b, maxE, depth[], parent[][];
		
		N = Integer.parseInt(in.readLine());
		
		maxE = (int)Math.ceil(log2(N));
		depth = new int[N + 1];
		parent = new int[N + 1][maxE];
		depth[0] = -1; // dfs로 트리 만드고 depth 계산시 depth[1]을 0으로 만들기 위함

		for(int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		dfs(list, new boolean[N + 1], parent, depth, 1);
		
		// 2^k 부모들 세팅
		for(int k = 1; k < maxE; k++) {	
			for(int i = 1; i <= N; i++) {
				parent[i][k] = parent[parent[i][k - 1]][k - 1];
			}
		}
		
		M = Integer.parseInt(in.readLine());
		
		while(M-- > 0) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			out.write(lca(parent, depth, a, b, maxE) + "\n");
		}
		
		out.flush();
	}
	
	private static double log2(double x) {
		return Math.log(x) / Math.log(2);
	}
	
	private static void dfs(List<List<Integer>> list, boolean[] visited, int[][] parent, int[] depth, int curr) {
		int child;
		
		depth[curr] = depth[parent[curr][0]] + 1;
		visited[curr] = true;
		
		List<Integer> children = list.get(curr);
		for(int i = 0; i < children.size(); i++) {
			child = children.get(i);
			
			if(visited[child]) continue;
			
			parent[child][0] = curr;
			dfs(list, visited, parent, depth, child);
		}
	}
	
	private static int lca(int[][] parent, int[] depth, int a, int b, int maxE) {
		// depth가 큰쪽이 항상 b가 되도록 변경
		if(depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		if(depth[a] != depth[b])
			for(int e = maxE - 1; e >= 0; e--) {
				if(depth[b] - depth[a] >= (1 << e)) {
					b = parent[b][e];
				}
			}
		
		if(a == b)
			return a; // 같으면 a 리턴
			
		for(int e = maxE - 1; e >= 0; e--) {
			if(parent[a][e] != parent[b][e]) {
				a = parent[a][e];
				b = parent[b][e];
			}
		}
		
		return parent[a][0];
	}

}