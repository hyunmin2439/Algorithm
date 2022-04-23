package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 완탐 방식
 * 
 * 각 위치 마다 나무 까지 거리를 구한 후
 * 
 * bfs로 완탐
 */

public class BJ_2917_늑대사냥꾼_오답 {

	private static char WOLF = 'V', HUT = 'J', TREE = '+';
	
	private static int N, M, distMap[][];
	private static Node wolf, hut;
	private static List<Node> trees;
	
	private static int[] dy = { -1, 1, 0, 0 }, 
						 dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		char[][] map = input();
		
		init(map);
		
		int answer = bfs(map);
		
		System.out.println(answer);
	}
	
	private static int bfs(char[][] map) {
		int answer = -1, ny, nx;
		Pos curr;
		
		Queue<Pos> queue = new LinkedList<>();
		
		queue.offer( new Pos(wolf.y, wolf.x, new boolean[N][M], distMap[wolf.y][wolf.x]) ); 
		queue.peek().visited[wolf.y][wolf.x] = true;
		
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			curr.visited[curr.y][curr.x] = true;
			
			if(map[curr.y][curr.x] == HUT) {
				answer = Math.max(answer, curr.minDist);
				continue;
			}
			
			for(int d = 0; d < 4; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if( curr.visited[ny][nx] ) continue;
				
				if( answer >= Math.min(curr.minDist, distMap[ny][nx]) ) continue;
				
				queue.offer(new Pos(ny, nx, copyBoolArr(curr.visited), Math.min(curr.minDist, distMap[ny][nx])));
			}
		}
		
		return answer;
	}
	
	private static boolean[][] copyBoolArr(boolean[][] arr) {
		boolean[][] newArr = new boolean[arr.length][];
		
		for(int y = 0; y < arr.length; y++) {
			newArr[y] = arr[y].clone();
		}
		
		return newArr;
	}
	
	private static void init(char[][] map) {
		Node tree;
		
		distMap = new int[N][M];
		trees = new ArrayList<>();
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(map[y][x] == WOLF) wolf = new Node(y, x);
				else if(map[y][x] == HUT) hut = new Node(y, x);
				else if(map[y][x] == TREE) trees.add(new Node(y, x));
			}
		}
		
		// 각 위치 나무와의 거리 계산
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(map[y][x] == TREE) distMap[y][x] = 0;
				else {
					distMap[y][x] = 10000;
					
					for(int k = 0; k < trees.size(); k++) {
						tree = trees.get(k);
						
						distMap[y][x] = Math.min(distMap[y][x], Math.abs(y - tree.y) + Math.abs(x - tree.x));
					}
				}
			}
		}
	}

	private static char[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][];
		
		for(int y = 0; y < N; y++) {
			map[y] = in.readLine().toCharArray();
		}
		
		in.close();
		
		return map;
	}

	private static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}
		
	}
	
	private static class Pos {
		int y, x, minDist;
		boolean[][] visited;
		
		public Pos(int y, int x, boolean[][] visited, int minDist) {
			this.y = y;
			this.x = x;
			this.visited = visited;
			this.minDist = minDist;
		}
	}
}
