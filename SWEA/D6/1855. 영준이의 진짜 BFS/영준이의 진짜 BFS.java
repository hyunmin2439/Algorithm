import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int MAX_NODE_CNT = 100_001;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		List<List<Integer>> edges = new ArrayList<>(MAX_NODE_CNT);
		
		int t = 0, T, N, K, p,
			queue[] = new int[MAX_NODE_CNT],
			depth[] = new int[MAX_NODE_CNT],
			ancestor[][] = new int[MAX_NODE_CNT][getMaxK(MAX_NODE_CNT)];
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			K = getMaxK(N);
			
			edges.clear();
			for(int i = 0; i <= 1; i++)
				edges.add(new ArrayList<>());
			
			st = new StringTokenizer(in.readLine());
			for(int i = 2; i <= N; i++) {
				p = Integer.parseInt(st.nextToken());
				ancestor[i][0] = p;
				depth[i] = depth[p] + 1;
				
				edges.add(new ArrayList<>());
				edges.get(p).add(i);
			}
			
			setAncestor(ancestor, K, N);
			
			sb.append("#").append(t).append(" ")
			.append(bfs(edges, queue, ancestor, depth, K, N))
			.append("\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static int getMaxK(int N) {
		return (int)Math.ceil(Math.log(N) / Math.log(2));
	}
	
	private static void setAncestor(int[][] ancestor, int K, int N) {
		for(int k = 1; k < K; k++) {
			for(int i = 2; i <= N; i++) {
				ancestor[i][k] = ancestor[ ancestor[i][k - 1] ][k - 1];
			}
		}
	}
	
	private static long bfs(List<List<Integer>> edges, int[] queue, int[][] ancestor, int[] depth, int K, int N) {
		List<Integer> children;
		int front = -1, rear = -1, 
			curr = 1, prev = 1;
		long tot = 0;
		
		rear = (rear + 1) % MAX_NODE_CNT;
		queue[rear] = 1;
		
		while(front != rear) {
			prev = curr;
			front = (front + 1) % MAX_NODE_CNT;
			curr = queue[front];
			
			tot += lca(ancestor, depth, K, prev, curr);
			
			children = edges.get(curr);
			
			for(int i = 0; i < children.size(); i++) {
				rear = (rear + 1) % MAX_NODE_CNT;
				queue[rear] = children.get(i);
			}
		}
		
		return tot;
	}
	
	private static long lca(int[][] ancestor, int[] depth, int K, int prev, int curr) {
		long ans = depth[prev] + depth[curr];
		
		// prev의 depth가 항상 작도록
		if(depth[prev] > depth[curr]) {
			int tmp = prev;
			prev = curr;
			curr = tmp;
		}
		
		if(depth[prev] != depth[curr])
			for(int k = K - 1; k >= 0; k--) {
				if(depth[curr] - depth[prev] >= 1 << k) {
					curr = ancestor[curr][k];
				}
			}
		
		if(prev == curr) return ans - depth[prev] * 2;
		
		for(int k = K - 1; k >= 0; k--) {
			if(ancestor[prev][k] != ancestor[curr][k]) {
				prev = ancestor[prev][k];
				curr = ancestor[curr][k];
			}
		}
		
		return ans - depth[ancestor[prev][0]] * 2;
	}
}