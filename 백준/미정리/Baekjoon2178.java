package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS로는 시간초과가 나서 풀지 못하는 문제
// BFS는 첫 도착이 항상 최단거리임을 보장하기 때문에 그것을 활용하여 푸는 문제

public class Baekjoon2178 {

	static int N, M, move;
	static boolean[][] maze;
	
	//					상 하 좌 우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new boolean[N][M];
		for (int y = 0; y < N; y++) {
			String line = br.readLine();
			for (int x = 0; x < M; x++) {
				if(line.charAt(x) == '1') maze[y][x] = true;					
			}			
		}
		
		findExit();
		
		System.out.println(move);
		br.close();
	}

			
	private static void findExit() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer( new Node(0, 0, 1) );
		maze[0][0] = false;
		
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			
			// 도착, BFS는 첫 도착이 항상 최단 거리를 보장
			if(node.y == N - 1 && node.x == M - 1) {
				move = node.c;
				return;
			}
			
			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				
				// 범위 벗어나는지 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				if(maze[ny][nx]) {
					queue.offer(new Node(ny, nx, node.c + 1)); // 이동 횟수 +1
					// 방문 체크, 큐에 들어가면 체크, 큐에서 나올때 
					// 체크를 하면 큐에 노드 중복해서 들어갈 경우가 생길 수 있다.
					maze[ny][nx] = false;
				}
			}
		}
	}

	static class Node {
		int y, x, c; // c 이동 횟수

		public Node(int y, int x, int c) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}
}
