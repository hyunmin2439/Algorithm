import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory: 67,384KB / Time:316ms
 */

public class BJ_16918_봄버맨 {

	private static int R, C, N;
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		Boom[][] map = input();
		
		int time = 1;
		
		while(time < N) {
			time++;
			decreaseTime(map);
			if(time % 2 == 0)
				setBoom(map); // 폭탄 설치
			else
				boom(map); // 폭탄 터짐
		}
		
		printMap(map);
	}
	
	private static Boom[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		Boom[][] map = new Boom[R][C];
		
		char[] line;
		for(int y = 0; y < R; y++) {
			line = in.readLine().toCharArray();
			for(int x = 0; x < C; x++) {
				// 초기 1초 아무동작 안하기 때문 시작을 1초로 시작, 따라서 2초로 설정
				if(line[x] == 'O') 
					map[y][x] = new Boom(y, x, 2); 
			}
		}
		
		in.close();
		
		return map;
	}
	
	private static void decreaseTime(Boom[][] map) {
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(map[y][x] != null)
					map[y][x].sec--;
			}
		}
	}
	
	private static void setBoom(Boom[][] map) {
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(map[y][x] == null)
					map[y][x] = new Boom(y, x, 3);
			}
		}
	}
	
	private static void boom(Boom[][] map) {
		int ny, nx;
		
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(map[y][x] == null || map[y][x].sec != 0) continue;
				
				map[y][x] = null; // 제거
				
				for(int d = 0; d < dy.length; d++) {
					ny = y + dy[d];
					nx = x + dx[d];
					
					if( !(0 <= ny && ny < R && 0 <= nx && nx < C) ) continue;
					
					// 폭탄이 없거나 같이 터질 폭탄이면
					if(map[ny][nx] == null || map[ny][nx].sec == 0) continue;
					
					map[ny][nx] = null; // 제거
				}
			}
		}
	}
	
	private static void printMap(Boom[][] map) {
		StringBuilder sb = new StringBuilder();
		
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(map[y][x] != null)
					sb.append('O');
				else
					sb.append('.');
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
	
	static class Boom {
		int y, x, sec;
		
		public Boom(int y, int x, int sec) {
			this.y = y;
			this.x = x;
			this.sec = sec;
		}
	}

}
