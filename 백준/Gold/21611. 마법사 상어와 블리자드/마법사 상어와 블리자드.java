import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static final int EMPTY = 0;
	private static long answer = 0;
	
	// dy[0] : 마법방향 / dy[1] : 달팽이맵 방향
	private static int[][] dy = { {0, -1, 1, 0, 0}, {0, 0, 1, 0, -1} };
	private static int[][] dx = { {0, 0, 0, -1, 1}, {0, -1, 0, 1, 0} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			map[][] = new int[N + 1][N + 1],
			mid = (N + 1) / 2, d, s;
		
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			// 1. 마법사 상어 블리자드 마법 사용
			magic(map, mid, d, s);
			// 구슬 빈공간 채우기
			moveMarble(map, mid);
			// 2. 구슬 폭발 발생하면 구슬 옮기고 다시 폭발 반복
			while( boom(map, mid) ) 
				moveMarble(map, mid);
			// 3. 구슬 변화
			change(map, mid);
		}
		
		System.out.print(answer);
	}
	
	private static void magic(int[][] map, int mid, int d, int s) {
		int y = mid, x = mid;
		while(s-- > 0) {
			y += dy[0][d];
			x += dx[0][d];
			map[y][x] = 0;
		}
	}
	
	private static void moveMarble(int[][] map, int mid) {
		int y = mid, x = mid, d = 0, cnt = 0, cycle = 0, move[];
		
		while( !(y == 1 && x == 1) ) {
			if(cnt == cycle) {
				cnt = 0;
				if(++d > 4) d = 1;
				if(d == 1 || d == 3) cycle++;
			}
			
			y += dy[1][d];
			x += dx[1][d];
			cnt++;
			
			if(map[y][x] == EMPTY) {
				// 구슬 있는 위치 찾기
				move = findNextMarble(map, y, x, d, cnt, cycle);
				
				if(move[0] == 0) break; // 더 이상 움직일 구슬이 없음.
				
				map[y][x] = map[move[0]][move[1]];
				map[move[0]][move[1]] = 0;
			}
		}
	}
	
	private static int[] findNextMarble(int[][] map, int y, int x, int d, int cnt, int cycle) {
		while( !(y == 1 && x == 1) ) {
			if(cnt == cycle) {
				cnt = 0;
				if(++d > 4) d = 1;
				if(d == 1 || d == 3) cycle++;
			}
			
			y += dy[1][d];
			x += dx[1][d];
			cnt++;
			
			if(map[y][x] != EMPTY)
				return new int[] {y, x};
		}
		
		return new int[] {0, 0};
	}
	
	private static boolean boom(int[][] map, int mid) {
		List<int[]> removeList = new ArrayList<>();
		List<int[]> tmpList = new ArrayList<>();
		int y = mid, x = mid, d = 0, cnt = 0, cycle = 0, curr = 0;
		
		while( !(y == 1 && x == 1) ) {
			if(cnt == cycle) {
				cnt = 0;
				if(++d > 4) d = 1;
				if(d == 1 || d == 3) cycle++;
			}
			
			y += dy[1][d];
			x += dx[1][d];
			cnt++;
			
			if(map[y][x] == 0) break;
			
			if(curr != map[y][x]) {
				if(tmpList.size() >= 4) {
					answer += curr * tmpList.size();
					removeList.addAll(tmpList);
				}
				tmpList.clear();
				curr = map[y][x];
			}

			tmpList.add(new int[] {y, x});
		}
		
		// 마지막 까지 순환하고 추가 안된 구슬 있으면 추가
		if(tmpList.size() >= 4) {
			answer += curr * tmpList.size();
			removeList.addAll(tmpList);
		}
		// 폭발리스트가 비어 있으면 더 이상 폭발하는 구슬이 없음
		if( removeList.isEmpty() ) return false;
		
		removeList.forEach( m -> map[m[0]][m[1]] = 0 );
		return true;
	}

	private static void change(int[][] map, int mid) {
		List<Integer> list = new ArrayList<>();
		int y = mid, x = mid - 1, d = 2, cnt = 0, cycle = 1, curr = map[y][x], currCnt = 1;
		
		while( !(y == 1 && x == 1) ) {
			if(cnt == cycle) {
				cnt = 0;
				if(++d > 4) d = 1;
				if(d == 1 || d == 3) cycle++;
			}
			
			y += dy[1][d];
			x += dx[1][d];
			cnt++;
			
			if(map[y][x] == 0) break;
			
			if(curr == map[y][x]) currCnt++;
			else {
				list.add(currCnt);
				list.add(curr);
				curr = map[y][x];
				currCnt = 1;
			}
		}
		
		if(curr != 0) {
			list.add(currCnt);
			list.add(curr);
		}
		
		y = mid; x = mid; d = 0; cnt = 0; cycle = 0; curr = 0;
		
		while( !(y == 1 && x == 1) ) {
			if(cnt == cycle) {
				cnt = 0;
				if(++d > 4) d = 1;
				if(d == 1 || d == 3) cycle++;
			}
			
			y += dy[1][d];
			x += dx[1][d];
			cnt++;
			
			if(curr < list.size())
				map[y][x] = list.get(curr++);
			else
				map[y][x] = 0;
		}
	}
	
}