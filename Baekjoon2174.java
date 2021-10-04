package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 단순한 시뮬레이션 문제이나 꽤나 고생한 문제

// 하지만, delta dy 부분 E S 부분에 ,를 빠트려 0 -1로 표시

// 여러 테케들이 문제 없이 돌아가서 수정하는데 시간이 좀 걸림

// 또한 출력에 공백을 빠트려, 이 부분도 수정하는데 시간일 걸림

// 앞으로 코드 작성시나 출력문을 작성할 때 조금 더 꼼꼼히 확인이 필요함

public class Baekjoon2174 {

	static int Y, X, N, M;
	static int map[][];
	static List<Robot> list;
	
	//					N  E  S   W
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		X = Integer.parseInt(st.nextToken()); // 가로
		Y = Integer.parseInt(st.nextToken()); // 세로
		
		map = new int[Y + 1][X + 1]; // 로봇 위치 표시 맵

		st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 로봇 개수
		M = Integer.parseInt(st.nextToken()); // 명령 수
		
		list = new ArrayList<>(); // 로봇 리스트
		list.add(new Robot(0, 0, 0)); // dummy, 로봇 번호 1번부터
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken()); // 로봇 개수
			int y = Integer.parseInt(st.nextToken()); // 명령 수
			char c = st.nextToken().charAt(0); // 방향		
			int d = getDirection(c); // 방향 숫자
			
			map[y][x] = i; // 로봇 위치 표시
			list.add(new Robot(x, y, d)); // 로봇 리스트에 추가
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken()); // 로봇 번호
			char inst = st.nextToken().charAt(0); // 명령 종류
			int cnt = Integer.parseInt(st.nextToken()); // 반복 횟수
			
			// 명령 메소드에 전달, 충돌 여부 체크
			// 이상 있으면 바로 종료
			if( isCrush(num, inst, cnt) ) return;
		}
		
		// 모든 명령 수행 후 이상 없으면 OK 표시
		System.out.print("OK");
		
		in.close();
	}
	
	private static boolean isCrush(int num, char inst, int cnt) {
		Robot robot = list.get(num);
		int rCnt = cnt % 4; // 4번 회전하면 제자리
		
		switch(inst) {
		case 'L': // 로봇 왼쪽 회전
			for(int i = 0; i < rCnt; i++) {
				robot.d -= 1;
				if(robot.d == -1) robot.d = 3;
			}
			break;
		case 'R': // 로봇 오른쪽 회전
			for(int i = 0; i < rCnt; i++) {
				robot.d += 1;
				if(robot.d == 4) robot.d = 0;
			}
			break;
		case 'F': // 로봇 전진
			map[robot.y][robot.x] = 0; // 이동 전 로봇 위치 제거
			
			for (int i = 0; i < cnt; i++) {
				robot.x += dx[robot.d];
				robot.y += dy[robot.d];
				
				// 로봇 맵을 벗어났는지 체크
				if( !(1 <= robot.x && robot.x <= X && 1 <= robot.y && robot.y <= Y) ) {
					System.out.println("Robot " + num + " crashes into the wall");
					return true;			
				}
				
				// 충돌 여부 체크
				if( map[robot.y][robot.x] != 0 ) {
					System.out.println("Robot " + num + " crashes into robot " + map[robot.y][robot.x]);
					return true;
				}
			}
			
			map[robot.y][robot.x] = num; // 이동 후 로봇 위치 표시
			break;
		}
		
		return false;
	}

	// 문자로 입력받은 방향을 숫자로 변환
	private static int getDirection(char c) {
		switch(c) {
		case 'N': return 0;
		case 'E': return 1;
		case 'S': return 2;
		default: return 3;
		}
	}

	static class Robot {
		int x, y, d; // 좌표, 방향

		public Robot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}

}