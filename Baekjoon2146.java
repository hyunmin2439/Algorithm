package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// map을 bfs로 탐색하며 섬의 위치좌표를 담은 ArrayList를 섬 ArrayList에 추가

// 이후 4중 for문을 통해 각 섬의 위치 거리 값을 계산

// 이중 가장 작은 값을 결과값으로 출력

public class Baekjoon2146 {

	static int N;
	static char[][] map;
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	static ArrayList<ArrayList<Node>> ildList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().replace(" ", "").toCharArray();
		}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if(map[y][x] == '1') {
					bfs(y, x);
				}
			}
		}
		
		int minDist = Integer.MAX_VALUE; // 결과값
		int size = ildList.size();
		for (int i = 0; i < size - 1; i++) {
			ArrayList<Node> ild1 = ildList.get(i); // 섬1 좌표 리스트
			for (int j = i + 1; j < size; j++) {
				ArrayList<Node> ild2 = ildList.get(j); // 섬2 좌표 리스트
				
				// 거리 값 계산
				for (int a = 0; a < ild1.size(); a++) {
					Node pos1 = ild1.get(a);
					for (int b = 0; b < ild2.size(); b++) {
						minDist = Math.min(minDist, distance(pos1, ild2.get(b)));
					}
				}
				
			}
		}
		
		System.out.println(minDist);
		br.close();
	}
	
	private static int distance(Node a, Node b) {
		// 놓여질 다리의 길이이기 때문에 -1
		return Math.abs(a.y - b.y) + Math.abs(a.x - b.x) - 1;
	}

	private static void bfs(int y, int x) {
		Queue<Node> queue = new LinkedList<>();
		ArrayList<Node> island = new ArrayList<>();
		
		map[y][x] = '2'; // 방문 체크
		queue.offer(new Node(y, x));
		island.add(new Node(y, x)); // 섬의 위치 추가
		
		while( !queue.isEmpty() ) {
			Node temp = queue.poll();
			
			for (int i = 0; i < dy.length; i++) {
				int ny = temp.y + dy[i];
				int nx = temp.x + dx[i];
				
				// 범위 체크, 바다 또는 방문한 위치인지 체크
				if( !(0 <= ny && ny < N && 0 <= nx && nx < N && map[ny][nx] == '1') ) continue;
				
				map[ny][nx] = '2';
				queue.offer(new Node(ny, nx));
				island.add(new Node(ny, nx));  // 섬의 위치 추가
			}
		}
		
		ildList.add(island); // 섬 리스트에 추가
	}
	
	static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

}
