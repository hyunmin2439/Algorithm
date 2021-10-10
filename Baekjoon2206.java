package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 벽 부수고 이동하기
 * 
 * 최단거리를 구하는 문제
 * 
 * 단, 추가 조건으로 벽이 있을 경우 1개의 벽을 부수고 이동할 수 있다.
 * 
 * 클래스로 만든 Node에 k 멤버변수를 추가해 시작시 1로 설정해두고 출발.
 * 
 * 벽이 있으면 k = 0으로 만들고 벽을 부수고 이동 한다.
 * 
 * 벽을 부술 수 있는 k 횟수를 사용시 벽을 부수고 이동하지 못한다.
 * 
 * k라는 변수가 추가 되었기 때문에 visited 배열은 3차원 배열을 사용.
 * 
 * bfs를 사용하여 제일 처음 목표지점에 도착하면 이동거리를 출력하고 종료하고,
 * 
 * 만약 bfs를 계속 돌면서 방문할 수 있는 지점을 다 방문하고도 목표지점에 도착하지 못하면,
 * 
 * 목표지점으로 갈 수 없는 것임으로 -1을 출력하고 종료한다.
 */

public class Baekjoon2206 {

	static final char WALL = '1';
	
	static int N, M;
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][];
		
		// 맵 정보 입력
		for (int y = 0; y < N; y++) {
			map[y] = in.readLine().toCharArray();
		}
		
		// 목표지점 도착 못했으면 -1 출력
		if( !isArrive(map) )
			System.out.println(-1);
		
		in.close();
	}

	private static boolean isArrive(char[][] map) {
		Queue<Node> queue = new LinkedList<>();
		// [1][][] 벽을 부수지 않고 방문 / [0][][] 벽을 부수고 방문
		boolean[][][] visited = new boolean[2][N][M];
		
		visited[1][0][0] = true;
		queue.offer(new Node(0, 0, 1, 1)); // 거리 시작점 포함
		
		while( !queue.isEmpty() ) {
			Node n = queue.poll();
			
			// 목표지점 도착
			if(n.y == N - 1 && n.x == M - 1) {
				// 이동 거리 출력
				System.out.print(n.d);
				return true;
			}
			
			// 사방 탐색
			for (int d = 0; d < 4; d++) {
				int ny = n.y + dy[d];
				int nx = n.x + dx[d];
				
				// 좌표 범위 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				// 방문 했거나, 벽을 부술 수 없는데 벽이면
				if(visited[n.k][ny][nx] || (n.k == 0 && map[ny][nx] == WALL) ) continue;
				
				// 벽이면 부수고 이동
				if(map[ny][nx] == WALL) {
					visited[n.k - 1][ny][nx] = true;
					queue.offer(new Node(ny, nx, n.k - 1, n.d + 1));
				}
				// 비어있으면 그냥 이동
				else {
					visited[n.k][ny][nx] = true;
					queue.offer(new Node(ny, nx, n.k, n.d + 1));					
				}
			}
			
		}
		
		// 목표지점 도착 못함
		return false;
	}
	
	static class Node {
		int y, x, k, d; // k : 남은 벽 부술 수 있는 횟수, d : 이동 거리

		public Node(int y, int x, int k, int d) {
			this.y = y;
			this.x = x;
			this.k = k;
			this.d = d;
		}
		
	}

}
