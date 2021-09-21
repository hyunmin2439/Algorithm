package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 도착지에서 역으로 모든 지점 방문
// 노드에 거리를 담아서 방문할 때 마다 거리 바꾸기
// 최종적으로 도달할 수 없는 지점은 !visit && map[y][x] == LAND
// 체크 해서 -1로 다 바꾸기

public class Baekjoon14940 {

	static final int NOT_LAND = 0;
	static final int LAND = 1;
	
	static int N, M;
	static int[][] map;
	static Node dest;
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		// 입력
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				// 도착점 미리 좌표 저장
				if(map[y][x] == 2) {
					dest = new Node(y, x, 0);
					visit[dest.y][dest.x] = true;
				}
				// 미리 방문 표시 하기
				else if (map[y][x] == NOT_LAND) {
					visit[y][x] = true;
				}
			}
		}
		
		// bfs로 최단거리 계산
		bfs();
		
		// 도착 못한 지점 -1로 바꾸기
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if(!visit[y][x] && map[y][x] == LAND)
					map[y][x] = -1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				sb.append(map[y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	static int[] dy = { -1, 1,  0, 0};
	static int[] dx = {  0, 0, -1, 1};
	
	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		// 도착점으로 부터 최단 거리 계산하기
		queue.offer(dest);
		map[dest.y][dest.x] = dest.d;
		
		while(!queue.isEmpty()) {
			Node pos = queue.poll();
			
			for (int i = 0; i < dy.length; i++) {
				int ny = pos.y + dy[i];
				int nx = pos.x + dx[i];
				
				// 좌표 벗어 나거나, 방문했으면
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) || visit[ny][nx] ) continue;
				
				queue.offer(new Node(ny, nx, pos.d + 1));
				map[ny][nx] = pos.d + 1;
				visit[ny][nx] = true;
			}
		}
	}

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		
	}
}
