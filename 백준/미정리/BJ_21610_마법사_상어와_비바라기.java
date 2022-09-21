import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Memory:18,468KB / Time:236ms
 */
public class BJ_21610_마법사_상어와_비바라기 {

	static int N, M, cast[][];
	
	static final int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static final int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		int[][] map = input();
		
		List<Node> list= new ArrayList<>();
		boolean[][] visited = null;
		// 0. 첫 구름 위치에 생성
		list.add(new Node(N - 1, 0));
		list.add(new Node(N - 1, 1));
		list.add(new Node(N - 2, 0));
		list.add(new Node(N - 2, 1));
		
		for(int i = 0; i < M; i++) {
			// 1. boolean 배열 초기화
			visited = new boolean[N][N];
			// 2. 이동, 물양 증가, 해당 위치 bool 배열 체크
			move(list, map, visited, i); // 현재 메소드 조금 애매
			// 3. 구름 사라짐, list clear
			list.clear();
			// 4. 대각선 물이 있는 만큼 물양 증가. ( 3. bool 배열 활용)
			copy(map, visited);
			// 5. boolean false이고 물양 2이상인 곳 체크하여 구름 생성, -2
			make(list, map, visited);
		}
		
		// 결과 출력
		System.out.println(getAnswer(map));
//		printMap(map);
	}
	
	static void move(List<Node> list, int[][] map, boolean[][] visited, int idx) {
		Node cloud;
		
		int ny, nx;
		for(int i = 0; i < list.size(); i++) {
			cloud = list.get(i);
			
			ny = (N + cloud.y + dy[ cast[idx][0] ] * cast[idx][1]) % N;
			nx = (N + cloud.x + dx[ cast[idx][0] ] * cast[idx][1]) % N;
			
			map[ny][nx]++;
			visited[ny][nx] = true;
		}
	}
	
	static void copy(int[][] map, boolean[][] visited) {
		int ny, nx, cnt = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if( !visited[y][x] ) continue;
				
				cnt = 0;
				
				for(int d = 2; d <= 8; d += 2) {
					ny = y + dy[d];
					nx = x + dx[d];
					
					if( outMap(ny, nx) ) continue;
					
					if( map[ny][nx] > 0 ) cnt++;
				}
				
				map[y][x] += cnt;
			}
		}
	}
	
	static boolean outMap(int y, int x) {
		return !(0 <= y && y < N && 0 <= x && x < N);
	}
	
	static void make(List<Node> list, int[][] map, boolean[][] visited) {
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if( visited[y][x] || map[y][x] < 2 ) continue;
				
				list.add(new Node(y, x));
				map[y][x] -= 2;
			}
		}
	}
	
	static int getAnswer(int[][] map) {
		int answer = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				answer += map[y][x];
			}
		}
		
		return answer;
	}
	
	static void printMap(int[][] map) {
		System.out.println("---------------------");
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				System.out.printf("%3d ", map[y][x]);
			}
			System.out.println();
		}
	}
	
	static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		cast = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			cast[i][0] = Integer.parseInt(st.nextToken());
			cast[i][1] = Integer.parseInt(st.nextToken()) % N; // N만큼 이동 시 제자리
		}
		
		in.close();
		
		return map;
	}

	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
