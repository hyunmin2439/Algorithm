package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14460KB / Time:136ms
public class BJ_14499_주사위굴리기 {

	private static int N, M, X, Y, K, inst[], map[][];
	
	//                             동 서 북 남
	private static int[] dy = { 0, 0, 0, -1, 1 };
	private static int[] dx = { 0, 1, -1, 0, 0 };
	
	public static void main(String[] args) throws Exception {
		input();
		
		Dice dice = new Dice(Y, X); 
		
		StringBuilder sb = new StringBuilder();
		
		int ny, nx;
		for(int i = 0; i < K; i++) {
			ny = dice.y + dy[ inst[i] ];
			nx = dice.x + dx[ inst[i] ];
			
			if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) continue;
			
			// 주사위 이동
			dice.y = ny;
			dice.x = nx;
			
			dice.rotate( inst[i] ); // 주사위 회전
			
			// 이동한 칸 0일 경우 주사위의 바닥 숫자 칸에 복사
			if( map[ny][nx] == 0 ) map[ny][nx] = dice.bottom;
			// 이동한 칸 0이 아닐 경우 칸의 숫자 주사위 바닥에 복사
			// 칸에 있는 수는 0
			else {
				dice.bottom = map[ny][nx];
				map[ny][nx] = 0;
			}
			
			sb.append(dice.top).append('\n');
		}
		
		System.out.print(sb);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken()); // 문제에선 반대로 되어있음
		X = Integer.parseInt(st.nextToken()); // Y == x / X == y
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		inst = new int[K];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < K; i++) {
			inst[i] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
	}

	static class Dice {
		int y, x;
		
		int up, left, right, down, top, bottom; // 초기 주사위 전부 0
		
		public Dice(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		private void rotate(int direction) {
			int tmp;
			
			switch(direction) {
			case 1:
				tmp = top;
				top = left;
				left = bottom;
				bottom = right;
				right = tmp;
				break;
			case 2:
				tmp = top;
				top = right;
				right = bottom;
				bottom = left;
				left = tmp;
				break;
			case 3:
				tmp = top;
				top = down;
				down = bottom;
				bottom = up;
				up = tmp;
				break;
			case 4:
				tmp = top;
				top = up;
				up = bottom;
				bottom = down;
				down = tmp;
				break;
			}
		}
	}
}
