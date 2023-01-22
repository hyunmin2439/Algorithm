import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		PriorityQueue<Edge> pqueue = new PriorityQueue<>( (a, b) -> Double.compare(a.dist, b.dist));
		Edge edge;
		int t = 0, T, N, cnt, parent[], x[], y[];
		double E, answer;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			
			parent = new int[N];
			x = new int[N];
			y = new int[N];
			pqueue.clear();
			cnt = 1;
			answer = 0;
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(in.readLine());
			
			for(int i = 0; i < N; i++) {
				parent[i] = i;
				for(int j = i + 1; j < N; j++) {
					pqueue.offer(new Edge(i, j, x[i], y[i], x[j], y[j]));
				}
			}
			
			while(cnt < N) {
				edge = pqueue.poll();
				
				if(!union(parent, edge.v1, edge.v2)) continue;
				
				cnt++;
				answer += edge.dist;
			}
			
			sb.append("#").append(t).append(" ")
			.append(Math.round(E * answer))
			.append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	public static int find(int[] parent, int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent, parent[x]);
	}
	
	public static boolean union(int[] parent, int a, int b) {
		int aParent = find(parent, a),
			bParent = find(parent, b);
		
		if(aParent == bParent) return false;
		
		if(aParent < bParent)
			parent[bParent] = aParent;
		else
			parent[aParent] = bParent;
		
		return true;
	}
	
	static class Edge {
		int v1, v2;
		double dist;
		
		public Edge(int v1, int v2, int x1, int y1, int x2, int y2) {
			this.v1 = v1;
			this.v2 = v2;
			this.dist = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
		}
	}
}