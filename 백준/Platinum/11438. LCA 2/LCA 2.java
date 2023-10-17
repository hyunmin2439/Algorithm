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
		// ※ 주의 : 반복문 순서를 i -> k 순으로 하면 틀림
		for(int k = 1; k < maxE; k++) {	
			for(int i = 2; i <= N; i++) {
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
			for(int k = maxE - 1; k >= 0; k--) {
				// depth가 나는 만큼의 부모로 이동하기 위해서 2^k 승 만큼 이동
				if(depth[b] - depth[a] >= (1 << k)) {
					b = parent[b][k];
				}
			}
		
		if(a == b)
			return a; // 같으면 a 리턴
			
		for(int k = maxE - 1; k >= 0; k--) {
			// maxE부터 확인하는 이유 -> 부모가 없다면 0 -> 즉, 그 위에 부모가 없는것
			// 2^k -> 2^(k-1) 부모를 차례대로 확인하면서 다르다면 위치를 옮겨서 확인
			// 노드가 16개 이고 한쪽으로 쏠려있는 편향된 트리라고 한다면 1 ~ 16노드까지 쭉 연결되어 있을 것. (maxE = 4)
			// 16에서 1를 찾아가기 위해서는 2^3 -> 2^2 -> 2^1 -> 2^0 부모를 찾아가면 1에 도달
			if(parent[a][k] != parent[b][k]) {
				a = parent[a][k];
				b = parent[b][k];
			}
		}

		// Lowest Common Ancestor의 자식을 찾아가는 것.
		// 예제에서 a = 11, b = 14라고 한다면 최종적으로 a = 2, b = 3에서 종료
		// a의 첫번째 부모(Lowest Common Ancestor)를 return 해줌
		return parent[a][0];
	}

}
