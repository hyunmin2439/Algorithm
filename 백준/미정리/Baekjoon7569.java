package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 토마토
 * 
 * 기초적인 3차원 탐색 문제
 * 
 * 이때까지 푼 대부분의 문제는 2차원 탐색 문제를 풀었다.
 * 
 * 추가적인 조건으로 인한 3차원 visited 배열은 써보긴 했지만,
 * 
 * 명확하게 3차원을 탐색하는 문제는 이 문제가 처음이다.
 * 
 * 이전에는 감이 안잡혀서 못 풀고 있었지만,
 * 
 * 이번에 다시 풀어보니 생각보다 굉장히 간단한 문제
 * 
 * 일반적인 탐색 문제와 다를 것 없이 생각하면 된다. 
 * 
 * 상하좌우 4방향 탐색에서 위 아래가 추가된 6방향 탐색을 하면 된다.
 * 
 * 탐색할 구간이 조금 늘어난 것일 뿐 가장 기초적인 탐색문제이다.
 * 
 * 머리속으로 3차원을 그리려고 하면 감이 잘 안잡힌다.
 * 
 * 위 아래 탐색은 2차원 배열을 옮겨다닌다고 간단하게 생각하면 더 쉽다.
 * 
 * Memory:123722 / Time:740ms
 */

public class Baekjoon7569 {
	
//					  빈곳	   익지 않은  익은  토마토
	static final int EMPTY = -1, YET = 0, DONE = 1;

	static int M, N, H, cnt, day; // cnt : 익지않은 토마토 개수, day : 지난 날짜
	static int[][][] box;
	static boolean[][][] visited;
	
	static List<Node> list; // 익은 토마토의 좌표를 담을 리스트
	
//					    위 아래 상 하  좌 우
	static int[] dh = { -1, 1,  0, 0,  0, 0};
	static int[] dy = {  0, 0, -1, 1,  0, 0};
	static int[] dx = {  0, 0,  0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][N][M];
		visited = new boolean[H][N][M];
		
		list = new ArrayList<>();
		
		for (int h = 0; h < H; h++) {
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(in.readLine());
				for (int x = 0; x < M; x++) {
					box[h][y][x] = Integer.parseInt(st.nextToken());
					if(box[h][y][x] == YET) cnt++; // 아직 익지 않은 토마토 개수 세기
					else if(box[h][y][x] == DONE) list.add(new Node(h, y, x)); // 익은 토마토 좌표 저장
				}
			}
		}
		
		if(cnt != 0) bfs(); // 익지 않은 토마토가 없으면 BFS탐색 불 필요
		
		System.out.println(cnt == 0 ? day : -1);
		
		in.close();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		// 익은 토마토 위치 전부 큐에 담기
		for (Node n : list) {
			queue.offer(n);
			visited[n.h][n.y][n.x] = true;
		}
		
		while(!queue.isEmpty()) {
			int roop = queue.size();
			
			// 큐에 day 정보를 담지 않고 큐의 사이즈 만큼 돌고 날짜 증가 -> 큐가 빌때까지 반복
			while(roop-- > 0) {
				Node n = queue.poll(); // 하나씩 꺼내기
				
				for (int d = 0; d < dh.length; d++) {
					int nh = n.h + dh[d];
					int ny = n.y + dy[d];
					int nx = n.x + dx[d];
					
					// 좌표가 범위를 벗어나면
					if( !(0 <= nh && nh < H && 0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
					
					// 비어 있거나 방문했으면
					if( box[nh][ny][nx] == EMPTY || visited[nh][ny][nx] ) continue;
					
					cnt--; // 안 익은 토마토 개수 감소
					box[nh][ny][nx] = DONE; // 익은 토마토로 바꾸기
					visited[nh][ny][nx] = true; // 방문 처리
					queue.offer(new Node(nh, ny, nx)); // 큐에담기
				}
			}
			
			day++;
		}
		
		day--; // 토마토들이 다 익었어도 마지막에 들어간 좌표가 한번더 돌기 때문에 --해줘야 한다
	}
	
	static class Node {
		int h, y, x;

		public Node(int h, int y, int x) {
			this.h = h;
			this.y = y;
			this.x = x;
		}
		
	}

}
