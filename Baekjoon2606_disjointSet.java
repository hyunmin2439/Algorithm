package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// memory:14224 / time:148ms
public class Baekjoon2606_disjointSet {

	static int N, link, res;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 컴퓨터 대수
		N = Integer.parseInt(br.readLine());
		// 연결된 컴퓨터 대수
		link = Integer.parseInt(br.readLine());
		
		makeSet();
		
		for (int i = 0; i < link; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int parentOne = findParent(1);
		// 1번과 같은 집합인지 계산
		for (int i = 2; i <= N; i++) {
			if(findParent(i) == parentOne) res++;
		}
		
		System.out.println(res);
		br.close();
	}

	private static void makeSet() {
		parents = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	private static int findParent(int x) {
		if(x == parents[x]) return x;
		return parents[x] = findParent(parents[x]);
	}
	
	private static void union(int a, int b) {
		int parentA = findParent(a);
		int parentB = findParent(b);
		
		if(parentA == parentB) return;
		
		if(parentA < parentB) parents[parentA] = parentB;
		else parents[parentB] = parentA;
	}

}
