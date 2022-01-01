package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 단순한 구현문제
* 
* Memory:15064KB / Time:148ms
*/

public class BJ_14890_경사로_1 {

	static int N, L;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int numOfPossiblePass = 0;
		
		for(int y = 0; y < N * 2; y++) {
			if( isPossiblePass(y) )
				numOfPossiblePass++;
		}
		
		System.out.print( numOfPossiblePass );
	}

	private static boolean isPossiblePass(int y) {
		boolean[] isPutSlope = new boolean[N];
		
		for(int x = 1; x < N; x++) {
			int diffHeight = Math.abs(map[y][x - 1] - map[y][x]);
			
			// 높이 차이가 1이상 나면 지나갈 수 없는 길
			if( diffHeight > 1 ) return false;
			
			// 높이 차가 없으면 경사로를 놓을 필요가 없음
			if( diffHeight == 0 ) continue;
			
			// 왼쪽 길이 더 낮으면
			if( map[y][x - 1] < map[y][x] ) {
				
				// 왼쪽에 경사로를 놓을 자리가 없으면
				if( x - L < 0 ) return false;
				
				int height = map[y][x - 1]; // 높이
				
				for(int i = 0; i < L; i++) {
					// 높이가 다르면 경사로 설치 불가
					if( height != map[y][x - 1 - i] ) return false;
					
					// 경사로가 설치되어 있으면
					if( isPutSlope[ x - 1 - i ] ) return false;
					
					// 경사로 설치 표시
					isPutSlope[ x - 1 - i ] = true;
				}
			}
			// 오른쪽 길이 더 낮으면
			else {
				// 오른쪽에 경사로를 놓을 자리가 없으면
				if( x - 1 + L >= N ) return false;
				
				int height = map[y][x]; // 높이
				
				for(int i = 0; i < L; i++) {
					// 높이가 다르면 경사로 설치 불가
					if( height != map[y][x + i] ) return false;
					
					// 경사로가 설치되어 있으면
					if( isPutSlope[ x + i ] ) return false;
					
					// 경사로 설치 표시
					isPutSlope[ x + i ] = true;
				}
				
				x += L - 1; // 경사로 설치한 구간 패스
			}
		}
		
		return true;
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N * 2][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y = N; y < N * 2; y++) {
			for(int x = 0; x < N; x++) {
				map[y][x] = map[x][y - N];
			}
		}
				
		in.close();
	}

}
