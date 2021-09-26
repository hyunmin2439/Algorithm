package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
블록 그룹 : 일반 색상 한개 이상 포함, 색상 모두 같아야 함
무지개 포함 가능 / 검은색 블록 제외 / 개수 2개 이상

블록 그룹 기준 : 무지개 빼고 행 작은것, 열 작은것

오토플레이(블록 그룹이 존재하는 동안 계속 반복

1. 크기가 가장 큰 블록 그룹
여러개일 경우 무지개 블록 많은 것 우선, 행 큰 것 우선, 열 큰 것 우선

2. 1에서 찾은 블록 제거 B(블록수)^2 점수 얻음

3. 격자에 중력 작용

4. 격자가 90도 반시계 방향회전

5. 다시 격자 중력 작용

중력 작용시 검은색 블록 제외 모든 블록 행 번호가 큰 칸으로 이동
다른 블록 만나기 전까지나 격자의 경계를 만나기 전까지
*/

// 복잡한 시뮬레이션 문제

// 1. bfs를 통해 블록 그룹을 구함.
// 2. 블록 그룹이 가장 큰 블록 그룹인지 체크
// 3. 중력, 회전, 중력 작용

// memory:18440 / time:188ms
public class Baekjoon21609 {
	
	static final int EMPTY = -2;
	static final int RAINBOW = 0;

	static int N, M, score;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	// 찾은 블록 그룹중 선택된 그룹
	static int sSize, srSize; // 총개수, 무지개 개수
	static List<Block> selGroup;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 1.블록 그룹 찾기
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					// 방문X, 컬러 블럭만 방문
					if( !visit[y][x] && map[y][x] > 0) {
						// 받은 블럭그룹 체크 선택된 그룹 바꾸기
						selectGroup( bfs(y, x) );
					}
				}
			}
			
			// 선택된 블록 그룹이 없으면 종료
			if(selGroup == null) break; 

			// 2.블록그룹 제거
			removeBlockGroup();
			// printMap()
			
			// 3.중력 작용
			gravity();
			// printMap()
			
			// 4.반 시계 90도 회전
			rotate();
			// printMap()
			
			// 5.중력 작용
			gravity();
			// printMap()
		}
		
		System.out.println(score);
		in.close();
	}

	private static List<Block> bfs(int y, int x) {
		int color = map[y][x];
		List<Block> group = new ArrayList<>();
		Queue<Block> queue = new LinkedList<>();
		
		visit[y][x] = true;
		group.add(new Block(y, x, map[y][x]));
		queue.offer(new Block(y, x, map[y][x]));
		
		while( !queue.isEmpty() ) {
			Block block = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = block.y + dy[d];
				int nx = block.x + dx[d];
				
				// 좌표 범위 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
				// 방문 체크, 색상 체크
				if( visit[ny][nx] || !(map[ny][nx] == RAINBOW || map[ny][nx] == color) ) continue;
				
				visit[ny][nx] = true; // 방문 처리
				group.add(new Block(ny, nx, map[ny][nx])); // 그룹에 담기
				queue.offer(new Block(ny, nx, map[ny][nx])); // 큐에 담기
			}
		}
		
		return group;
	}
	
	private static void selectGroup(List<Block> group) {
		// bfs를 통해 넘어온 group이 null일 수는 없음
		int size = group.size(), rSize = 0;

		// 1개의 블록그룹이면 패스
		if(size == 1) return;
		
		// 무지개 블록 방문 초기화 및 개수 세기
		for (Block block : group) {
			if( block.c == RAINBOW ) {
				visit[block.y][block.x] = false;
				rSize++;
			}
		}
		
		// 그룹 정렬해서 기준 블록 앞으로 두기
		Collections.sort(group);
		
		// 선택된 그룹이 있으면
		if(selGroup != null) {
			
			// 크기가 가장 큰 블록 우선
			if(selGroup.size() > group.size()) return;
			else if(selGroup.size() == group.size()) {
				
				// 무지개 블록 많은 것 우선
				if(srSize > rSize) return;
				else if(srSize == rSize) {
					
					// 행 큰 것 우선, 열 큰 것 우선
					Block sBlock = selGroup.get(0), block = group.get(0);	
					if(sBlock.y > block.y) return;
					else if(sBlock.y == block.y) {
						if(sBlock.x > block.x) return;
					}
				}
			}
			
		}
		
		// 앞에 조건들에 안 걸리고 내려오면 바꾸기
		sSize = size; 
		srSize = rSize;
		selGroup = group;
	}

	private static void removeBlockGroup() {
		// 맵에서 제거
		for (Block block : selGroup) {
			map[block.y][block.x] = EMPTY;
		}
				
		// 점수 합산
		score += sSize * sSize;
		
		// 초기화
		selGroup = null;
		sSize = 0;
		srSize = 0;
		
		// 방문 초기화
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				visit[y][x] = false;
			}
		}
	}
	
	private static void gravity() {
		// 제일 밑줄 바로 윗줄부터 시작
		for (int y = N - 2; y >= 0; y--) {
			for (int x = 0; x < N; x++) {
				
				// 블랙 타일이거나 비어있는 타일이면
				if(map[y][x] < 0) continue;
				
				int i = 1;
				while( y + i < N && map[y + i][x] == EMPTY ) i++;

//				System.out.println((y + i - 1) + ", " + x + " <- " + y + ", " + x);
				// 이동한 경우에만
				if(y + i - 1 != y) {
					map[y + i - 1][x] = map[y][x];
					map[y][x] = EMPTY;
				}
			}
		}
	}

	private static void rotate() {
		int[][] temp = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				temp[(N - 1) - x][y] = map[y][x];
			}
		}
		
		map = temp;
	}
	
	private static void printMap() {
		System.out.println();
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
		
		System.out.println(score + "\n");
	}

	static class Block implements Comparable<Block> {
		int y, x, c; // color

		public Block(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}
		
		@Override
		public int compareTo(Block other) {
			if(this.c == other.c) {
				if(this.y == other.y)
					return this.x - other.x;
				
				return this.y - other.y;
			}
			
			return other.c - this.c;
		}

		@Override
		public String toString() {
			return "Block [y=" + y + ", x=" + x + ", c=" + c + "]";
		}
		
	}
	
}
