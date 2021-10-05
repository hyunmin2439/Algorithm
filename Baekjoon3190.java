import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 
 * 뱀(Dummy Game)
 * 
 * 오래된 지렁이 게임을 모티브로 한 시뮬레이션 게임
 * 
 * 이동을 하면서 사과를 먹으면 자신의 몸이 길어지고
 * 
 * 이동하다 벽이나 자신의 몸에 부딪히면 끝나는 게임
 * 
 * 시뮬레이션 자체는 어려운 문제는 아니나, 
 * 
 * 어떠한 자료구조를 사용해서 문제를 단순화 시킬지가 고민되는 문제
 * 
 * 양방향 큐인 Dequeue(Double-ended Queue)를 사용하기 아주 적합한 문제
 * 
 * 이동을 하며 몸의 길이를 늘렸다가, 사과를 먹지 못하면 몸을 다시 줄임
 * 
 * 즉, 이동하면 앞쪽에 좌표를 넣고, 사과를 먹지 못하면 뒤쪽의 좌표를 빼는 식으로 풀이
 *
 * Memory:14508KB / Time:140ms
 */

public class Baekjoon3190 {

	static final int EMPTY = 0, SNAKE = 1, APPLE = 2;
	
	// N:맵 크기, K:사과 수, L:방향 전환 수, time:현재지난시간
	static int N, K, L, time;
	
	// map:맵, rotation:방향 전환 정보
	static int[][] map, rotation;
	
	// 처음 뱀의 머리 우측을 향하고 있으므로 0는 우로 시작
	//				    우 하 좌  상
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine()); // 맵 크기
		K = Integer.parseInt(in.readLine()); // 사과 개수
		
		map = new int[N + 1][N + 1]; // 0 : dummy
		
		// 사과 위치 입력받기
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			map[y][x] = APPLE;
		}
		
		L = Integer.parseInt(in.readLine()); // 뱀의 방향 전환 횟수
		
		rotation = new int [L][2];
		
		// 뱀의 방향 변환 정보 입력받기
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			rotation[i][0] = Integer.parseInt(st.nextToken()); // 몇초 후 회전
			rotation[i][1] = st.nextToken().charAt(0) == 'L' ? -1 : 1; // L(왼쪽):-1, D(오른쪽):1로 입력
		}
		
		playGame();
		
		System.out.println(time); // 결과값 출력
		in.close();
	}

	private static void playGame() {
		// 뱀의 몸 위치를 저장할 양방향 큐
		Deque<int[]> snake = new LinkedList<>();

		// 뱀의 현재 머리 위치, d:뱀의 현재방향, 수행할 회전정보 위치
		int ny = 1, nx = 1, d = 0, idx = 0;
		
		// 뱀의 초기 세팅
		map[ny][nx] = SNAKE; // 시작 위치 뱀
		snake.offer(new int[] {ny, nx}); // 뱀의 위치 정보 저장
		
		// 충돌 전 까지 반복
		while(true) {
			// 방향 전환
			if(idx < L && rotation[idx][0] == time) {
				d += rotation[idx][1]; // 회전
				
				if(d < 0) d = 3;
				else if(d > 3) d = 0;
				
				idx++; // 다음 회전정보
			}
			
			// 머리 이동
			ny += dy[d];
			nx += dx[d];
					
			time++; // 시간 지남
			
			// 벽에 충돌 하거나 자기 몸과 부딛히면 게임을 끝낸다.
			if( !(1 <= ny && ny <= N && 1 <= nx && nx <= N) || map[ny][nx] == SNAKE ) return;
			
			// 사과를 먹지 못하면 몸길이 다시 줄이기
			if(map[ny][nx] != APPLE) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = EMPTY;
			}
			
			// 머리 이동 표시
			map[ny][nx] = SNAKE;
			snake.offerFirst(new int[] {ny, nx});
			
		}
	}
	
}
