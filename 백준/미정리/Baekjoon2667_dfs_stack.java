package myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//memory : 14324KB  time : 160ms
public class Baekjoon2667_dfs_stack {

	static int N;
	static boolean[][] area;
	static Stack<Node> stack = new Stack<>();
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
				if(area[y][x]) dfs(y, x);
			}
		}
		
		// 정렬
		Collections.sort(list);
		
		// 출력
		System.out.println( list.size() );
		list.forEach( System.out::println );
		
		br.close();
	}

	private static void dfs(int y, int x) {
		// 시작 첫번째 집에 대한 처리
		int cnt = 1;
		area[y][x] = false;
		stack.push(new Node(y, x));

		// 스택이 빌때 까지 반복
		while (!stack.isEmpty()) {
			Node node = stack.pop();

			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];

				// 범위 체크
				if (!(0 <= ny && ny < N && 0 <= nx && nx < N))
					continue;

				// 집이 있다면 노드에 추가
				if (area[ny][nx]) {
					cnt++;
					area[ny][nx] = false;
					stack.push(new Node(ny, nx));
				}
			}
		}

		list.add(cnt);
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
