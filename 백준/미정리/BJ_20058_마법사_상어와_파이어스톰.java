package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 방법
 * 
 * 1. 회전 로직
 * - parameter(시작 위치(sy, sx), 길이)
 * - 0, 0을 기준으로 회전한다고 로직을 작성후
 * - sy, sx를 각각 더해주면 문제와 같은 회전 로직이 완성
 * 
 * 2. 감소시키는 로직에서 문제가 발생했음

 * - 한칸씩 감소 시켜 나가면 다음과 같은 오류가 발생
 *   8 1 6 3 ... -> 7 1 6 3 -> 7 0 6 3 -> 7 0 5 3
 *   0 0 5 ...      0 0 5      0 0 5      0 0 5
 * 
 * - 6은 감소 대상이 아니였지만 한칸씩 감소 처리를 해 오류 발생
 * 
 * - 리스트 좌표를 모아두고 한번에 감소
 * 
 * 3. 얼음 덩어리는 bfs
 * 
 * Memory: 23,008KB / Time:368ms
 */

public class BJ_20058_마법사_상어와_파이어스톰 {

	private static int e, N, Q, list[];
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		int[][][] map = input();
				
		int[] L = new int[e + 1];
		for(int i = 0; i <= e; i++) {
			L[i] = (int)Math.pow(2, i);
		}
		
		for(int i = 0; i < Q; i++) {
			for(int y = 0; y < N; y += L[ list[i] ]) {
				for(int x = 0; x < N; x += L[ list[i] ]) {
					// 회전
					rotate(map[(i + 1) % 2], map[i % 2], y, x, L[ list[i] ]);
					
//					printMap(map[(i + 1) % 2]);
				}
			}
			
			decreaseIce(map[(i + 1) % 2]);
			
//			printMap(map[(i + 1) % 2]);
		}
		
		int idx = Q % 2, sumIce = 0, maxPieceOfIce = 0;
		boolean[][] visited = new boolean[N][N];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				sumIce += map[idx][y][x];
				
				if( !visited[y][x] && map[idx][y][x] != 0)
					maxPieceOfIce = Math.max(maxPieceOfIce, bfs(map[idx], visited, y, x));
			}
		}
		
		System.out.println(sumIce);
		System.out.print(maxPieceOfIce);
	}
	
	private static int[][][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		e = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int)Math.pow(2, e);
		
		int[][][] map = new int[2][N][N];
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				map[0][y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new int[Q];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < Q; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return map;
	}
	
	private static void rotate(int[][] map, int[][] piece, int sy, int sx, int len) {
		for(int y = 0; y < len; y++) {
			for(int x = 0; x < len; x++) {
				map[sy + x][sx + len - y - 1] = piece[sy + y][sx + x];
			}
		}
	}
	
	private static void decreaseIce(int[][] map) {
		int ny, nx, cnt = 0;
		
		List<Node> decreaseList = new ArrayList<>();
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(map[y][x] == 0) continue;
				
				cnt = 0;
				
				for(int d = 0; d < dy.length; d++) {
					ny = y + dy[d];
					nx = x + dx[d];
					
					if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
					
					if( map[ny][nx] > 0 ) cnt++;
				}
				
				if(cnt < 3) decreaseList.add(new Node(y, x));
			}
		}
		
		Node tmp;
		for(int i = 0; i < decreaseList.size(); i++) {
			tmp = decreaseList.get(i);
			map[tmp.y][tmp.x]--;
		}
	}
	
	private static int bfs(int[][] map, boolean[][] visited, int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(y, x));
		visited[y][x] = true;
		
		int ny, nx, cnt = 1;
		Node curr;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			for(int d = 0; d < dy.length; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				
				if( visited[ny][nx] || map[ny][nx] == 0 ) continue;
				
				queue.add(new Node(ny, nx));
				visited[ny][nx] = true;
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static void printMap(int[][] map) {
		System.out.println("----------------------------------");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.printf("%3d", map[y][x]);
			}
			System.out.println();
		}
	}

	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
