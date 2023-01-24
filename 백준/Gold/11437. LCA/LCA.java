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
		int N, M, a, b, depth[], parent[];
		
		N = Integer.parseInt(in.readLine());
		
		depth = new int[N + 1];
		parent = new int[N + 1];

		for(int i = 0; i <= N; i++)
			list.add(new ArrayList<>());
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		dfs(list, new boolean[N + 1], parent, depth, 1, 0);
		
		M = Integer.parseInt(in.readLine());
		
		while(M-- > 0) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			out.write(lca(parent, depth, a, b) + "\n");
		}
		
		out.flush();
	}
	
	private static void dfs(List<List<Integer>> list, boolean[] visited, int[] parent, int[] depth, int curr, int cnt) {
		List<Integer> children = list.get(curr);
		int child;
		
		depth[curr] = cnt;
		visited[curr] = true;
		
		for(int i = 0; i < children.size(); i++) {
			child = children.get(i);
			
			if(visited[child]) continue;
			
			parent[child] = curr;
			dfs(list, visited, parent, depth, child, cnt + 1);
		}
	}
	
	private static int lca(int[] parent, int[] depth, int a, int b) {
		int tmp = a;
		
		// depth가 큰쪽이 항상 a가 되도록 변경
		if(depth[a] < depth[b]) {
			a = b;
			b = tmp;
		}
		
		while(depth[a] > depth[b]) a = parent[a];
		
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

}