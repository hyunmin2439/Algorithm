package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Union Find or Disjoint Set
 * 
 * Union Find 알고리즘만 알고 있다면 쉽게 풀 수 있는 문제
 * 
 * 인접행렬 또는 리스트의 노드들끼리 연결 정보로
 * 
 * 최종 도달하는 부모의 정보를 저장한다.
 * 
 * 이를 통해서 각각 다른 노드들 끼리 연결이 되어있는지 알 수 있다.
 * 
 * 행선지 차례 차례 같은 부모를 가지는지 검사하여 결과를 출력.
 * 
 * Memory:17456ms / Time:188ms
 */

public class BJ_1976_여행가자_1_UnionFind {

	static int N, M;
	
	static int[] travel, parent;
	static boolean[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		travel = new int[M];
		parent = new int[N + 1];
		adjMatrix = new boolean[N + 1][N + 1];
		
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			
			for(int j = 1; j <= N; j++) {
				if( st.nextToken().equals("1") )
					adjMatrix[i][j] = true;
			}
			
			parent[i] = i;
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < M; i++) {
			travel[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접행렬로 노드끼리 연결 및 부모 노드 저장
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j && adjMatrix[i][j])
					union(i, j);
			}
		}
		
		for(int i = 0; i < M - 1; i++) {
			if(find(travel[i]) != find(travel[i + 1])) {
				System.out.print("NO");
				return;
			}
		}
		
		System.out.print("YES");
		
		in.close();
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}

	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) 
			return true;
		
		// 한쪽으로 편향되어 탐색 시간이 길어지지 않게
		if(aParent > bParent)
			parent[b] = aParent;
		else
			parent[a] = bParent;
		
		return false;
	}
}
