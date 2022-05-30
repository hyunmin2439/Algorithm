import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int V, E;
	static int[] parent;
	static Edge[] list;
	
	public static void main(String[] args) throws Exception {
		input();
		
		Arrays.sort(list);
		
		int idx = 0, cnt = 0, totCost = 0;;
		
		while(cnt < V - 1) {
			if( union(list[idx].v1, list[idx].v2) ) {
				totCost += list[idx].c;
				cnt++;
			}
			idx++;
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
	
	public static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}
	
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