package solved.submit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 전체를 순회하면서 bfs로 방문한 지점 체크
// 전체 bfs 횟수 = 구역 수
public class BJ_10026_적록색약_BFS {

	static int N;
	static char[][] nomal;
	static char[][] weak;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nomal = new char[N][];
		weak = new char[N][];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			// 정상적인 사람의 그림 입력
			nomal[i] = line.toCharArray();
			// 적록색약인 사람의 그림 G -> R 바꿔서 입력
			weak[i] = line.replace('G', 'R').toCharArray();
		}
		
		System.out.print(around(nomal) + " " + around(weak));
		br.close();
	}
	
	private static int around(char[][] map) {
		int res = 0;
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if(map[y][x] != 'V') {
					bfs(map, new int[]{y, x});
					res++;
				}
			}
		}
		
		return res;
	}

	private static void bfs(char[][] map, int[] s) {
		Queue<int[]> queue = new LinkedList<>(); // 현재 구역 좌표 저장할 큐
		
		// 매개변수로 받은 시작 좌표 큐에 담고 색깔 저장
		queue.offer(s);
		char color = map[s[0]][s[1]];
		map[s[0]][s[1]] = 'V'; // 방문 체크
		
		// 현재 구역 순회
		while( !queue.isEmpty() ) {
			s = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = s[0] + dy[i];
				int nx = s[1] + dx[i];
				
				// 좌표 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N)) continue;
				
				// 같은 색상이 아니라면 큐에 넣지 않음
				if (map[ny][nx] != color) continue;
				
				// 같은 색상이면 현재 큐에 넣기
				queue.offer(new int[] {ny, nx});
				map[ny][nx] = 'V'; // 방문 체크
			}
			
		}
		
	}

}
