package solved.submit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon17144 {

	static final int AIR_PURI = -1;
	
	static int R, C, T, airY[];
	static int[][] map;
	static List<Dust> list, tmpList;
	
	static int dy[] = { -1, 1,  0, 0 };
	static int dx[] = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		airY = new int[2];
		map = new int[R][C];
		list = new ArrayList<Dust>();
		tmpList = new ArrayList<Dust>();
		
		for (int y = 0; y < R; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < C; x++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp != 0) {
					// 공기 청정기 맵에 놓기
					// 두 칸 차지, 아래 부분이 나오면 좌표는 다시 덮어씀
					if(temp == AIR_PURI) {
						map[y][x] = AIR_PURI;
						airY[0] = y - 1;
						airY[1] = y;
					}
					// 먼지는 맵에 놓지 않고 리스트에 담음
					else list.add(new Dust(y, x, temp));
				}
			}
		}
		
		// T시간 만큼 반복
		while(T-- > 0) {
			// 미세먼지 확산
			spreadDust();
			
			// 맵에 확산된 미세먼지 리스트에 업데이트 
			updateList();
			
			// 공기청정기 작동 - 먼지 이동
			moveDust();
		}

		// list에 먼지 전부 합산 출력
		System.out.println( sumOfDustCnt() );
		
		in.close();
	}
	
	private static void spreadDust() {
		for(Dust dust : list) {
			int totCnt = 0;
			
			for (int d = 0; d < 4; d++) {
				int ny = dust.y + dy[d];
				int nx = dust.x + dx[d];
				
				// 좌표 범위 체크, 공기청정기로 확산 불가능
				if( !(0 <= ny && ny < R && 0 <= nx && nx < C) || map[ny][nx] == AIR_PURI) continue;
				
				int dustCnt = dust.c / 5;
				
				map[ny][nx] += dustCnt; // 확산
				totCnt += dustCnt; // 확산값 누적
				tmpList.add(new Dust(ny, nx, 0)); // 임시 리스트 저장, 먼지양은 필요X
			}
			
			// 확산된 미세먼지 빼주기
			dust.c -= totCnt;
		}
	}
	
	private static void updateList() {
		// 리스트 목록 먼저 업데이트
		for(Dust dust : list) {
			dust.c += map[dust.y][dust.x];
			map[dust.y][dust.x] = 0;
		}
		
		// 리스트 목록에 없는 먼지들 리스트 추가
		for (Dust tmp : tmpList) {
			// 0이 아니면 리스트에 추가
			if (map[tmp.y][tmp.x] != 0) {
				list.add(new Dust(tmp.y, tmp.x, map[tmp.y][tmp.x]));
				map[tmp.y][tmp.x] = 0;
			}
		}
		
		// 임시 리스트 초기화
		tmpList.clear(); 
	}
	
	private static void moveDust() {
		for (Iterator<Dust> iter = list.iterator(); iter.hasNext();) {
			Dust dust = iter.next();
			
			// 우측 이동
			if(dust.x < C - 1 && (dust.y == airY[0] || dust.y == airY[1]) ) dust.x++;
			// 좌측 이동
			else if(dust.x > 0 && (dust.y == 0 || dust.y == R - 1) ) dust.x--;
			// 공기청정기 방향X
			else if(dust.x == C - 1) {
				if(dust.y < airY[1]) dust.y--;
				else dust.y++;
			}
			// 공기청정기 방향
			else if(dust.x == 0){
				if(dust.y < airY[1]) dust.y++;
				else dust.y--;
				
				// 공기청정기에 들어가면 먼지 제거
				if(map[dust.y][dust.x] == AIR_PURI) iter.remove();
			}
			
		}
	}
	
	private static int sumOfDustCnt() {
		int dustCnt = 0;
		
		for (Dust dust : list) {
			dustCnt += dust.c;
		}
		
		return dustCnt;
	}

	// 먼지 클래스
	static class Dust {
		int y, x, c;

		public Dust(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
	}

}
