package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 간략 풀이
 * 
 * 1. BFS로 나무들까지의 거리를 모두 구해놓기
 * 
 * 2. 우선순위 큐로 나무들까지의 거리가 가장 먼 경로로 돌아가서 헛간에 도착시 종료
 * 
 * 
 * 오답 발생 경우
 * 
 * 1. 시간 초과
 * - 오답 코드 참조
 * 
 * 2. 메모리 초과가 난 경우
 * - 나무까지 거리를 구하는 로직에서 발생
 * - 여기서 가장 오래 동안 시간을 보냄
 * - (1) 나무 위치는 0 / 다른 위치는 BFS 탐색으로 나무까지 거리를 구함
 * - (2) 나무 위치에서 BFS 탐색, 거리 min값
 * 
 * 해법은 간단했지만 헷갈려서 오랜 시간 허비
 * 위의 경우에서는 O(n^2)으로 모든 좌표를 탐색하여 BFS
 * 하지만, tree 위치를 모두 Queue에 담아두고 BFS 탐색을 하면 BFS 한번으로 모든 거리 구하기 가능
 * 
 * Memory:35,204KB / Time:380ms
 */

public class BJ_2917_늑대사냥꾼_정답 {

	private static final int INF = 10000;
	private static final char WOLF = 'V', HUT = 'J', TREE = '+';
	
	private static int[] dy = { -1, 1, 0, 0 }, 
						dx = { 0, 0, -1, 1 };	
	
	private static Node wolf;
	private static int N, M, distMap[][];
	private static char[][] map;
	
	private static List<Node> trees;
	
	public static void main(String[] args) throws Exception {
		input();
		
		getDistToTree();
		
		int answer = bfs();
		
		System.out.println(answer);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		distMap = new int[N][M];
		trees = new ArrayList<>();
		
		for(int y = 0; y < N; y++) {
			map[y] = in.readLine().toCharArray();
			
			for(int x = 0; x < M; x++) {
				if(map[y][x] != TREE) {
					distMap[y][x] = INF;
					
					if(map[y][x] == WOLF) wolf = new Node(y, x, 0);					
				}
				else {
					distMap[y][x] = 0;
					trees.add(new Node(y, x, 0));
				}
			}
		}
		
		in.close();
	}
	
	private static int bfs() {		
		PriorityQueue<Node> queue = new PriorityQueue<>( (a, b) -> b.dist - a.dist );
		boolean[][] visited = new boolean[N][M];
		int ny, nx;
		
		queue.offer( new Node(wolf.y, wolf.x, distMap[wolf.y][wolf.x]) ); 
		visited[wolf.y][wolf.x] = true;
		
		Node curr;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			if(map[curr.y][curr.x] == HUT) 
				return curr.dist;
			
			for(int d = 0; d < 4; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if( visited[ny][nx] ) continue;
				
				visited[ny][nx] = true;
				
				queue.offer( new Node(ny, nx, Math.min(curr.dist, distMap[ny][nx])));
			}
		}
		
		return 0;
	}
	
	private static void getDistToTree() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		Node curr;
		int ny, nx;
		
		queue.addAll(trees);
		
		for(int i = 0; i < trees.size(); i++) {
			curr = trees.get(i);
			visited[curr.y][curr.x] = true;
		}
		
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if( visited[ny][nx] || map[ny][nx] == TREE ) continue;
								
				visited[ny][nx] = true;
				
				distMap[ny][nx] = curr.dist + 1;
				
				queue.offer(new Node(ny, nx, curr.dist + 1));
			}
		}
	}

	private static class Node {
		int y, x, dist;
		
		public Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}
}
