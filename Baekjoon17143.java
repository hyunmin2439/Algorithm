package solved.submit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon17143 {

	static int R, C, M, score;

	static Shark[][] sea;
	
	// 				  dummy  상 하 우 좌
	static int[] dir = { 0, -1, 1, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sea = new Shark[R + 1][C + 1];
		
		// 상어 정보 입력 받음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sea[r][c] = new Shark(s, d, z);
		}
		
//		printMap(0);
		
		// 낚시왕 낚시 시작
		for (int c = 1; c <= C; c++) {
			// 상어 잡기
			fishing(c);
			
			// 상어 이동
			moveShark();
			
//			printMap(c);
		}
		System.out.println(score);
		
		in.close();
	}
	
	// 디버깅용
	private static void printMap(int s) {
		System.out.println(s + "초 후");
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				System.out.print( sea[r][c] != null ? "0" : "X");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 점수 누적
	private static void fishing(int c) {
		for (int r = 1; r <= R; r++) {
			if( sea[r][c] != null ) {
				score += sea[r][c].z;
				sea[r][c] = null;
				break;
			}
		}
	}
	
	// 상어 움직이는 메서드
	private static void moveShark() {
		Shark[][] moved = new Shark[R + 1][C + 1];
		
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if( sea[r][c] != null ) {
					int[] p;
					
					if(sea[r][c].d <= 2) // 상 하 이동
						p = getMovedPos(sea[r][c], r, c, 0, R);
					else // 우 좌 이동
						p = getMovedPos(sea[r][c], r, c, 1, C);
					
					// 기존 상어가 존재하지 않거나, 존재하면 이동하려는 상어가 크다면 옮기기
					if( moved[ p[0] ][ p[1] ] == null || moved[ p[0] ][ p[1] ].z < sea[r][c].z ) {
						moved[ p[0] ][ p[1] ] = sea[r][c];						
					}
				}
			}
		}
		
		sea = moved;
	}

	/** 상어가 움직인 후 방향 변경 및 좌표 값을 리턴해주는 메서드이다.
	 * @param shark : 현재 움직이는 상어 클래스
	 * @param r : 움직이기 전 행 좌표 값
	 * @param c : 움직이기 전 열 좌표 값
	 * @param d : 방향값을 계산하기 위한 변수
	 * @param M : 조건문에 따라 R(행) 또는 C(열) 값을 가지는 변수
	 * @return : 상어가 움직이 후 좌표 값을 리턴
	 */
	private static int[] getMovedPos(Shark shark, int r, int c, int d, int M) {
		int pos[] = {r, c}; // 변경된 위치를 담을 변수
		
		int move = shark.s % (M * 2 - 2); // 제자리로 돌아오는 경우 빼고 나머지 이동 부분만 계산
		
		for (int i = 0; i < move; i++) {
			// 상어가 경계선에서 바깥으로 바라보고 있을 수도 있기 때문에 먼저 방향전환을 해야한다.
			if((pos[d] + dir[shark.d]) < 1 || pos[d] + dir[shark.d] > M) 
				// 들어오는 d값에 따라 방향 전환을 하는 부분
				shark.d = ( shark.d % 2 == 0 ? 1 + (d * 2) : 2 + (d * 2) );

			pos[d] += dir[shark.d];
		}

		return pos;
	}

	static class Shark {
		int s, d, z; // 속력, 이동 방향, 크기

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}
