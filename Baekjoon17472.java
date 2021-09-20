package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// memory:14308KB / time:128ms
public class Baekjoon17472 {
	
	static final int SEA = 0;
	static final int LAND = 9; // 섬의 개수 6이하
	
	static int N, M, isldIdx, result;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] parent;
	
	static Queue<Node> queue;
	static List<Node> vtxList;
	static List<Node> edgeList;
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		
		queue = new LinkedList<>();
		vtxList = new ArrayList<>(); // 가져오기만 함
		edgeList = new LinkedList<>(); // 간선 정렬
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				// 나중에 섬의 구분을 위해 상수값 9로 대체
				if(Integer.parseInt(st.nextToken()) == 1)
					map[y][x] = LAND;
			}
		}
		
		// 섬 각자 구분 bfs
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if( visit[y][x] || map[y][x] != LAND) continue;
				isldIdx++;
				bfs(y, x);
			}
		}
		
//		printMap();
		
		// 간선 리스트 만들기
		int vtxLen = vtxList.size();
		for (int i = 0; i < vtxLen; i++) {
			makeEdge(vtxList.get(i));
		}
		
		// 간선 길이 순으로 정렬
		Collections.sort(edgeList);
		
		// Kruskal 사용 MST 만들기
		int edgeCnt = kruskal();
		
		// 모든 섬 연결 불가능 -1, 가능하면 결과 출력
		System.out.println( edgeCnt == isldIdx - 1 ? result : -1 );
		
		br.close();
	}

	// 섬 각자 구분 bfs
	private static void bfs(int y, int x) {
		addVertex(y, x);
		
		while( !queue.isEmpty() ) {
			Node pos = queue.poll();
			
			for (int i = 0; i < dy.length; i++) {
				int ny = pos.y + dy[i];
				int nx = pos.x + dx[i];
				
				// 범위 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				// 방문한 곳이거나 땅이 아니면
				if( visit[ny][nx] || map[ny][nx] != LAND ) continue;
				
				addVertex(ny, nx);
			}
		}
	}
	
	private static void addVertex(int y, int x) {
		Node vtx = new Node(y, x, isldIdx);
		
		map[y][x] = isldIdx;
		visit[y][x] = true;
		
		queue.offer(vtx);
		vtxList.add(vtx);
	}
	
	// 간선 리스트 만들기
	private static void makeEdge(Node vtx) {
		for (int i = 0; i < dy.length; i++) {
			int len = 0;
			int ny = vtx.y;
			int nx = vtx.x;
			
			while( true ) {
				len++;
				ny += dy[i];
				nx += dx[i];
				
				// 범위 벗어나면 멈추기
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) break;
				
				// 다른 섬을 만나면
				if(map[ny][nx] != SEA) {
					// 자신과 같은 섬이거나 다리 길이가 2보다 작으면
					// 다른 섬에 도달할 때까지 길이라서 3 이하로 체크
					if(map[ny][nx] == vtx.c || len < 3) break;
					
					// 두섬 연결하는 간선 만들어서 리스트에 추가, 길이 -1해서 넣어주기
					edgeList.add(new Node(map[vtx.y][vtx.x], map[ny][nx], len - 1));
					
					break; // 간선을 넣고도 브레이크를 안하면 계속 돌게 됨, 이것을 안넣어서 계속 틀림
				}
			}

		}
	}
	
	// kruskal 사용 섬 연결
	private static int kruskal() {
		makeSet();
		
		int edgeCnt = 0; // 연결된 간선 수
		int edgeLen = edgeList.size();
		
		for (int i = 0; i < edgeLen; i++) {
			if(edgeCnt == isldIdx - 1) break;
			
			Node edge = edgeList.get(i);
			if(union(edge.y, edge.x)) {
				edgeCnt++;
				
				// 디버깅용
//				System.out.println(edge.y + "섬과 " + edge.x + "섬을 " + edge.c + "비용으로 연결");
				result += edge.c;
			}
		}
		
		return edgeCnt;
	}
	
	// Kruskal에 사용할 Union-find
	private static void makeSet() {
		parent = new int[isldIdx + 1];
		
		for (int i = 1; i <= isldIdx; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static boolean union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if(aParent == bParent) return false;
		
		if(aParent > bParent) parent[bParent] = aParent;
		else parent[aParent] = bParent;
		
		return true;
	}

	// 디버깅 용
	private static void printMap() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}
	
	// 섬의 좌표, 간선 두가지 용도 활용
	static class Node implements Comparable<Node> {
		int y, x, c;

		public Node(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
		
		@Override
		public int compareTo(Node other) {
			return this.c - other.c;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", c=" + c + "]";
		}
		
	}

}
