package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 스도쿠
 * 
 * 가능한 모든 숫자를 대입하여 스도쿠를 완성하는 완전 탐색 문제
 * 
 * 출력에서 조건이 사전식으로 앞서는 것을 출력하라고 되어 있으니 모든 답을 구할 필요는 없다.
 * 
 * 답이 구해지면 탐색을 그만두면 된다. 또한 속도를 위해서 빈칸에 넣을 숫자를 모두 선택하고 
 * 
 * 마지막에 대입해보는 것이 아닌 불가능한 숫자를 제외하고 탐색을 하면 된다. => 가지치기, 백트래킹
 * 
 * 끝까지 가봐야지 결과를 알 수 있으므로 DFS탐색을 사용하여 문제를 풀이
 * 
 * 첫번째 풀이 시도에는 가지치기를 할 때, 숫자 하나를 대입하고 가로, 세로, 구역을 체크하여
 * 
 * 조건에 어긋나면 다음 숫자, 다시 조건 체크, 다시 다음 숫자... 방식으로 가지를 쳤다.
 * 
 * 하지만 이런 방식은 완성할 때까지 조건을 여러번 체크하여 속도가 굉장히 느리다.
 * 
 * 반대로 생각해서 숫자를 넣고 조건을 체크하는 것이 아닌 조건을 먼저 체크하여
 * 
 * 넣을 수 있는 숫자들만 넣어보는 방식으로 진행을 하는 것이 더욱 효율적.
 * 
 * 또한 구역을 체크하기 위한 부분의 코드의 단순화가 조금 헷갈렸던 문제
 * 
 * int ny = y / 3 * 3; 이 단순한 한줄이 sy(start y) ex(end x)로 복잡하게 생각했던 부분을 단순하게 만들어 줌
 * 
 * Memory: / Time:
 */

public class Baekjoon2239 {

	static final int N = 9;
	static int[][] map;
	static boolean isEnd;
	
	static List<int[]> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][N];
		list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			char[] line = in.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = line[j] - '0';
				// 빈칸(0)이면 list에 추가해놓기
				if(map[i][j] == 0) list.add(new int[] {i, j});
			}
		}
		
		dfs(0); // dfs 탐색 시작
		
		in.close();
	}

	private static void dfs(int idx) {
		// 스도쿠 완료
		if( isEnd ) return;
		
		// 빈칸의 숫자를 다 채웠으면
		if(idx == list.size()) {
			isEnd = true;
			printMap(); // 결과출력
			return;
		}
		
		int y = list.get(idx)[0];
		int x = list.get(idx)[1];
		
		// 사용 가능한 숫자 목록 구하기
		boolean[] isUnAvail = checkNum(y, x);
		
		for (int i = 1; i <= N; i++) {
			// 사용 불가능 숫자 건너뛰기(가지치기)
			if( isUnAvail[i] ) continue;
			
			map[y][x] = i; // i 숫자 사용
			dfs(idx + 1);
			map[y][x] = 0; // 백트래킹
		}
	}

	private static boolean[] checkNum(int y, int x) {
		boolean[] isUnAvail = new boolean[N + 1]; // 0:dummy
		
		// 행과 열에 있는 숫자들 사용불가능 표시, 0은 dummy 덮어쓰면 됨
		for (int i = 0; i < N; i++) {
			isUnAvail[ map[y][i] ] = true; // 열 체크
			isUnAvail[ map[i][x] ] = true; // 행 체크
		}
		
		// 각각 9칸의 구역 체크
		int ny = y / 3 * 3; // y 시작 지점 ex) y = 5 -> ny = 3 ~ 5
		int nx = x / 3 * 3; // x 시작 지점 ex) x = 1 -> nx = 0 ~ 2
		
		for (int i = ny; i < ny + 3; i++) {
			for (int j = nx; j < nx + 3; j++) {
				isUnAvail[ map[i][j] ] = true;
			}
		}
		
		return isUnAvail;
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
