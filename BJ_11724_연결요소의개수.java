package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. union-find 알고리즘 
// 2. visit check로 집합의 수

public class BJ_11724_연결요소의개수 {

	static int N, M, res;
	static int[] parents;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		make();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		// 집합의 수를 visit check로 count
		for (int i = 1; i <= N; i++) {
			int p = find(parents[i]);
			if(!visit[p]) {
				res++;
				visit[p] = true;
			}
		}
		
		System.out.println(res);
	}

	private static void make() {
		parents = new int[N + 1];
		visit = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot > bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
	}
}
