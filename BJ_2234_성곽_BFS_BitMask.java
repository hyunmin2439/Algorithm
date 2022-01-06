package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS + Bit Mask
 * 
 * 벽의 존재 여부를 판단하는 것은 문제 자체에 힌트가 있다.
 * 
 * 벽이 있는 위치에 대한 정보를 숫자합으로 나타내고, 이진수 각 비트로 생각하라는 힌트를 주었다.
 * 
 * 때문에 Bit Mask에 대한 개념을 알고만 있다면 이를 응용하면 되는 문제.
 * 
 * 1번의 답은 BFS를 돌린 횟수를 체크하면 방의 개수가 나오고,
 * 
 * 2번의 답은 BFS를 돌릴 때, 큐에 들어가는 노드 개수를 체크하면 방의 크기가 나온다.
 * 
 * 이에 대한 최대값을 구하면 2번의 답이 된다.
 * 
 * 3번 답을 도출해 내는 것이 굉장히 어려웠는데, 처음에는 3차원 visited 배열을 두고,
 * 
 * 2번 BFS 돌리면서 3번의 답을 같이 도출하려고 생각했으나, 
 * 
 * +1차원의 visited 배열의 크기를 어떻게 정의할 것인가가 문제가 됨.
 * 
 * 그 후 생각한게 벽을 하나씩 제거하고 BFS를 돌리는 것이었는데, 시간 초과가 날 것 같았으나,
 * 
 * 처음 제출하니 메모리 초과가 남. => 벽하나를 제거할 때마다 처음부터 끝까지 BFS를 돌림.
 * 
 * 처음부터 끝까지 BFS를 돌릴 필요가 없다. 벽을 제거한 좌표 중심으로 BFS를 하니 통과가 됨.
 * 
 * Memory:125,884KB / Time:436ms
 */

public class BJ_2234_성곽_BFS_BitMask {

	static int N, M, numOfRooms, biggestRoomSize, biggestTwoRoomSize;
	static int[][] castle;
	static boolean[][] visited;
	static Queue<Node> queue;
	
	// 					서 북 동 남
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	
	public static void main(String[] args) throws Exception {
		int roomSize = 0;
		
		input();
		
		// 벽을 없애기 전 전체 BFS
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				// 방문하지 않았으면
				if( !visited[y][x] ) {
					numOfRooms++; // 방 개수 추가
					
					roomSize = bfs(y, x); // 방 크기
					
					biggestRoomSize = Math.max(biggestRoomSize, roomSize); // 방크기 큰 값 대체
				}
			}
		}
		
		resetVistedArray(); // 방문 배열 초기화
		
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				
				for(int d = 0; d < 4; d++) {
					// 벽 없으면 넘어감
					if( (castle[y][x] & 1 << d) == 0 ) continue;
					
					// y = 0 : 북쪽 / y = M - 1 : 남쪽 / x = 0 : 서쪽 / x = N -1 : 동쪽 제외하고
					if( (y == 0 && d == 1) || (y == M - 1 && d == 3)
					|| (x == 0 && d == 0) || (x == N - 1 && d == 2) ) continue;
					
					castle[y][x] -= 1 << d; // 벽 없애기
					
					roomSize = bfs(y, x); // 벽 하나 없애고 벽 없어진 곳 중심 bfs 돌리기
					
					biggestTwoRoomSize = Math.max(biggestTwoRoomSize, roomSize); // 방크기 큰 값 대체
					
					castle[y][x] += 1 << d; // 벽 되돌리기
					
					resetVistedArray(); // 방문 배열 초기화
				}
			}
		}
		
		// 결과 출력
		System.out.print(numOfRooms + "\n" + biggestRoomSize + "\n" + biggestTwoRoomSize);
	}
	
	private static void resetVistedArray() {
		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				visited[y][x] = false;
			}
		}
	}

	private static int bfs(int y, int x) {
		int roomSize = 1;
		
		// 첫 시작 지점 노드 추가 및 방문 체크
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		
		while( !queue.isEmpty() ) {
			Node node = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				// 벽의 존재 여부가 이진수 비트로 표현, 0이 아니면 벽이 존재 가지 못함
				if( (castle[node.y][node.x] & 1 << d) != 0 ) continue;
				
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				
				// 좌표 범위 체크
				if( !(0 <= ny && ny < M && 0 <= nx && nx < N) ) continue;
				
				// 방문 했던 곳이면 패스
				if( visited[ny][nx] ) continue;
				
				// 큐에 노드 추가
				queue.offer(new Node(ny, nx));
				visited[ny][nx] = true;
				roomSize++;
			}
		}
		
		return roomSize;
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		castle = new int[M][N];
		visited = new boolean[M][N];
		queue = new LinkedList<>();
		
		for(int y = 0; y < M; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < N; x++) {
				castle[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
