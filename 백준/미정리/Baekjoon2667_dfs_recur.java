package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//memory : 14220KB  time : 164ms
public class Baekjoon2667_dfs_recur {

	static int N, cnt;
	static boolean[][] area;
	static List<Integer> list = new ArrayList<>();
	
	//  			    상  하  좌 우
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		area = new boolean[N][N];
		
		// 배열 입력
		for (int y = 0; y < N; y++) {
			char[] temp = br.readLine().toCharArray();
			for (int x = 0; x < N; x++) {
				if(temp[x] == '1') area[y][x] = true;
			}
		}
		
		// 배열탐색
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (area[y][x]) {
					recur(y, x);
					list.add(cnt);
					cnt = 0;
				}
			}
		}
		
		// 정렬
		Collections.sort(list);
		
		// 출력
		System.out.println( list.size() );
		list.forEach( System.out::println );
		
		br.close();
	}

	// 재귀 호출 방식
	private static void recur(int y, int x) {
		area[y][x] = false;
		cnt++;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			// 범위 체크
			if (!(0 <= ny && ny < N && 0 <= nx && nx < N)) continue;

			// 집이 있다면 재귀 호출
			if (area[ny][nx]) recur(ny, nx);
		}
	}
	
	static class Node {
		int y;
		int x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		// 디버깅용
		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}
		
	}
}
