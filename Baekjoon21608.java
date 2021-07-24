package solved.notsubmit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon21608 {
	static int N, studCnt, studNum, satis;
	static int[][] classRoom;
	static int[][] favor;
	static int[] dy = { -1, 1,  0, 0}; // 상 하 좌 우
	static int[] dx = {  0, 0, -1, 1};
	static Pos pos;
	static List<Pos> list = new ArrayList<>();
	
	static class Pos {
		int y;
		int x;
		int cnt; // 인접 카운트
		
		public Pos(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		// input & init
		N = Integer.parseInt(br.readLine().trim());
		studCnt = N * N;
		classRoom = new int[N + 1][N + 1];
		favor = new int[studCnt + 1][4];
		
		for(int i = 1; i <= studCnt; i++) {
			// input & init
			list.clear();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			studNum = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) 
				favor[studNum][j] = Integer.parseInt(st.nextToken());
			
			// 학생들 자리 지정
			
			if(i == 1)
				pos = new Pos(2, 2, 0);
			else
				placement();
			classRoom[pos.y][pos.x] = studNum;
		}
		
		favorArrayCheck();
		classRoomCheck();
		
		addSatis();
		
		
		bw.write(String.valueOf(satis));
		br.close();
		bw.close();
	}
	
	///////////////////// process /////////////////////
	private static void placement() {
		
		System.out.println("\n" + studNum + "번 학생의 자리 선정 과정");
		
		// 1. 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= N; x++) {
				if(classRoom[y][x] != 0) continue;
				int cnt = countFavorStud(y, x, 1);
				enqueue(y, x, cnt);
			}
		}
		
		testCourse(1); // 과정 테스트
		
		// 2. 비어있는 칸이 가장 많은 칸으로 자리를 정한다
		int size = list.size();
		if(size == 1) {
			pos = list.get(0);
			testCourse(2); // 과정 테스트
			return;
		}
		else {
			List<Pos> tmpQue = new ArrayList<>(list);
			list.clear();
			for(int i = 0; i < size; i++) {
				Pos tmp = tmpQue.get(i);
				int cnt = countFavorStud(tmp.y, tmp.x, 2);
				enqueue(tmp.y, tmp.x, cnt);
			}
		}
		
		testCourse(2); // 과정 테스트
	
		// 3. 행의 번호가 가장 작은 칸, 열의 번호가 가장 작은 칸
		size = list.size();
		if(size == 1) {
			pos = list.get(0);
			testCourse(3); // 과정 테스트
			return;
		}
		else {
			pos = list.get(0); // 정렬 필요X, list index:0가 가장 작은 y x를 가지고 있음
			testCourse(3); // 과정 테스트
		}
		
	}
	
	private static int countFavorStud(int y, int x, int sw) {
		int cnt = 0;
		
		// 4방향 각각 체크
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위 벗어나면 아랫 문장 실행 X
			if(ny < 1 || nx < 1 || ny > N || nx > N) continue;
			
			// 인접한 좋아하는 학생 세기
			switch(sw) {
			case 1:
				for(int j = 0; j < 4; j++) {
					if(classRoom[ny][nx] == favor[studNum][j]) cnt++;
				}
				break;
			case 2:
				if(classRoom[ny][nx] == 0) cnt++;
				break;
			}
			
		}
		
		return cnt;
	}

	private static void enqueue(int y, int x, int cnt) {
		if( list.isEmpty() ) list.add( new Pos(y, x, cnt) );
		else {
			Pos tmp = list.get(0);
			if( tmp.cnt == cnt ) list.add( new Pos(y, x, cnt) );
			else if( tmp.cnt < cnt ) {
				list.clear();
				list.add( new Pos(y, x, cnt) );
			}
		}
	}
	
	private static void addSatis() {
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= N; x++) {
				studNum = classRoom[y][x];
				
				switch(countFavorStud(y, x, 1)) {
				// 0 인경우 점수 추가 X
				case 1: satis += 1; break;
				case 2: satis += 10; break;
				case 3: satis += 100; break;
				case 4: satis += 1000; break;
				}
			}
		}
	}
	
	///////////////////// test list /////////////////////
	private static void favorArrayCheck() {
		for (int i = 1; i <= studCnt; i++) {
			System.out.print(i + "번 학생이 좋아하는 사람 : ");
			for (int j = 0; j <= N; j++) {
				System.out.print(favor[i][j] + ", ");
			}
			System.out.println();
		}
	}

	private static void classRoomCheck() {
		System.out.println("좌석 배치도");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print( classRoom[i][j] + " " );
			}
			System.out.println();
		}
	}
	
	private static void testCourse(int i) {
		System.out.println(i + "번과정 끝난 후");
		for(Pos tmp : list) {
			System.out.println(i + "yx:(" + tmp.y + "," + tmp.x + "), cnt : " + tmp.cnt);
		}
		System.out.println("---------------------");
	}

}
