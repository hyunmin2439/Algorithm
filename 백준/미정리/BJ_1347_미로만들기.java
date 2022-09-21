package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Memory:14292KB / Time:140ms
public class BJ_1347_미로만들기 {

	private static int N;
	
	//					북 동 남 서
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 }; 
	
	public static void main(String[] args) throws Exception {
		char[] movement = input();
		
		boolean[][] visited = checkVisited(movement); // 방문 지점 확인
		
		solve(visited); // 미로 출력
	}
	
	private static char[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		char[] movement = in.readLine().toCharArray();
		
		in.close();
		
		return movement;
	}
	
	private static boolean[][] checkVisited(char[] movement) {
		int d = 2; // 남쪽을 보고 시작
		int y = N - 1, x = N - 1; // 중앙에서 시작
		
		// 중앙에서 시작
		// N * 2 => F만 있을 경우 최대 갈 수 있는 거리
		boolean[][] visited = new boolean[N * 2][N * 2];
		
		visited[y][x] = true; // 시작 지점
		
		// 움직임에 따라서 방문 체크
		for(int i = 0; i < N; i++) {
			switch(movement[i]) {
			case 'F': 
				y += dy[d]; 
				x += dx[d];
				visited[y][x] = true;
				break;
			case 'L':
				if(--d < 0) d = 3;
				break;
			case 'R':
				if(++d > 3) d = 0;
				break;
			}
		}
		
		return visited;
	}
	
	private static void solve(boolean[][] visited) {
		int N2 = N * 2, minY = N2, minX = N2, maxY = 0, maxX = 0;
		
		for(int y = 0; y < N * 2; y++) {
			for(int x = 0; x < N * 2; x++) {
				if(visited[y][x]) {
					minY = y < minY ? y : minY;
					minX = x < minX ? x : minX;
					maxY = y > maxY ? y : maxY;
					maxX = x > maxX ? x : maxX;
				}
			}
		}
		
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				System.out.print(visited[y][x] ? '.' : '#');
			}
			System.out.println();
		}
	}
}
