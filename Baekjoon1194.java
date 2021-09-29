import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 이전의 풀었던 말이 되고픈 원숭이와 유사한 개념 포함

// visit 체크를 2차원으로만 풀다가, 3차원으로 처음 풀어본 문제

// 그것과 마찬가지로 이 문제도 3차원 visit 체크를 해야함

// 열쇠 종류가 6개가 있기 때문에 단순하게 visit[N][M][6 + 1]을 하면 될 것 같지만 아님

// 열쇠를 a번, b번만 가지고 있을 수도 있고, a, b, f를 가지고 있을 수도 있다.

// 이 처럼 여러가지의 경우의 수가 있다. 이러한 경우의 수 종류는 2^6 = 64가지

// 열쇠를 하나도 가지고 있지 않다면 000000 => 0, 열쇠를 전부 다가지고 있다면 111111 => 63

// 때문에 visit[N][M][64]로 visit 배열을 생성하면 된다.

// 이후에는 bfs로 탐색하며, 열쇠를 획득하면 bitMask기법으로 처리를 하면 된다.

// 이러한 visit 배열을 생성하는 부분만 어렵고 헷갈리는 부분이며 bfs로 시뮬레이션하는 부분은 크게 어려운 부분은 아님

// Memory:16692KB / Time:144ms
public class Baekjoon1194 {

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final char START = '0';
	static final char EXIT = '1';
	
	static Node start;
	static int N, M, min;
	static char map[][];
	static boolean visit[][][];
		
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new char[N][M];
		visit = new boolean[N][M][64];
		
		for (int y = 0; y < N; y++) {
			String input = in.readLine();
			for (int x = 0; x < M; x++) {
				map[y][x] = input.charAt(x);
				
				if(map[y][x] == START) 
					start = new Node(y, x, 0, 0);
			}
		}
		
		bfs();
		
		// 결과 출력
		System.out.print(min != Integer.MAX_VALUE ? min : -1);
		
		in.close();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		// 시작 위치 큐에 담기
		queue.offer(start);
		// 방문처리
		visit[start.y][start.x][start.hasKey] = true;
		
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			
			// 출구 도달
			if(map[node.y][node.x] == EXIT) {
				min = node.move;
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				int hasKey = node.hasKey;
				int doorCnt = -1;
				
				// 좌표 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
				
				// 방문 체크
				if( visit[ny][nx][hasKey]) continue;
				
				// 빈칸이 아니면 조건 체크
				if( map[ny][nx] != EMPTY) {
					
					// 벽인지 체크
					if(map[ny][nx] == WALL) continue;
					
					// 몇번째 문인지 체크
					if('A' <= map[ny][nx] && map[ny][nx] <= 'F' ) {
						doorCnt = map[ny][nx] - 'A';
					}
					
					// 현재 위치가 문이고 해당하는 키가 없으면 넘어가기
					if( doorCnt != -1 && (hasKey & (1 << doorCnt)) == 0) continue;
					
					// 열쇠 지점인지 체크
					if('a' <= map[ny][nx] && map[ny][nx] <= 'f') {
						hasKey |= ( 1 << (map[ny][nx] - 'a') ); // 열쇠 등록
					}
					
				}
				
				// 디버깅
				// System.out.println("y:" + ny + " x:" + nx + " 지점으로 이동 " + map[ny][nx] + " " + hasKey + " " + (node.move + 1));
				
				// 큐에 담기
				queue.offer(new Node(ny, nx, hasKey, node.move + 1));
				
				// 방문처리
				visit[ny][nx][hasKey] = true;
			}
			
		}
		
	}

	static class Node {
		int y, x, hasKey, move;
		// hasKey 열쇠 : f e d c b a, bitMask
		
		public Node(int y, int x, int hasKey, int move) {
			this.y = y;
			this.x = x;
			this.hasKey = hasKey;
			this.move = move;
		}
		
	}
}
