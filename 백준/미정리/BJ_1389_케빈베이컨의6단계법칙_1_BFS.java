package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * BFS를 이용한 풀이방법.
 * 
 * 인접 행렬, 우선순위 큐, BFS를 사용하여 풀이.
 * 
 * 인접 행렬에 값은 케빈 베이컨의 수를 나타낸다.
 * 
 * 만약 케빈 베이컨의 수가 0이면 BFS로 케빈 베이컨의 수를 알아낸다.
 * 
 * 이때, 아래 예시와 같이 여러 갈래가 있을 수 있는데
 * 
 * ex) 1 -> 3 -> 4 -> 2 : 4 / 1 -> 4 -> 2 : 3
 * 
 * 적은 값을 선택하기 위해 케빈 베이컨의 수를 사용한다.
 * 
 * 이때, 또 다른 주의할 점으로는 큐에 넣을 때 방문 체크를 하면
 * 
 * 낮은 값을 선택하지 못할 가능성이 있으므로, 
 * 
 * 해당 유저의 친구 관계를 탐색할 때 방문체크를 해야 함.
 * 
 * Memory:39620KB / Time:288ms
 */

public class BJ_1389_케빈베이컨의6단계법칙_1_BFS {

	static int N, M; // 유저 수, 친구 관계 수
	static int minUser, minNumOfKevinBacon; // 가장 작은 케빈 베이컨의 수를 가진 유저번호, 수
	
	static int[][] adjMatrix; // 케빈 베이컨의 수
	
	static PriorityQueue<Node> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		queue = new PriorityQueue<>();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minNumOfKevinBacon = Integer.MAX_VALUE;
		
		adjMatrix = new int[N + 1][N + 1];
		
		// 친구 관계 연결
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = adjMatrix[b][a] = 1; // 친구면 케빈 베이컨 수 1
		}
		
		for(int i = 1; i <= N; i++) {
			int totNumOfKevinBacon = 0;
			
			for(int j = 1; j <= N; j++) {
				if(i == j) continue; // 동일 인물
				
				// 친구가 아니면
				if(adjMatrix[i][j] == 0)
					findNumOfKevinBacon(i, j); // 케빈 베이컨의 수 찾기
				
				totNumOfKevinBacon += adjMatrix[i][j]; // 업데이트
			}
			
			// 같으면 번호가 작은 사람이기 때문에 바꾸지 않음.
			if(totNumOfKevinBacon < minNumOfKevinBacon) {
				minUser = i; // 번호 저장
				minNumOfKevinBacon = totNumOfKevinBacon; // 작은 값 저장
			}
		}
		
		System.out.print(minUser);
		in.close();
	}

	private static void findNumOfKevinBacon(int userNum, int findOtherNum) {
		boolean[] visited = new boolean[N + 1];
		Node curr = null;
		
		queue.clear(); // 초기화
		queue.offer( new Node(userNum, 0) );
		
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			if( curr.userNum == findOtherNum ) break; // 해당하는 유저를 찾았으면
			
			if( visited[curr.userNum] ) continue; // 탐색했던 유저면 넘어가기.
			
			visited[curr.userNum] = true; // 방문 체크 유의
			
			for(int i = 1; i <= N; i++) {
				// 현재 유저와 i유저의 케빈 베이컨 수를 모르면
				if( adjMatrix[curr.userNum][i] == 0 ) continue;
				
				queue.offer(new Node(i, curr.numOfKevinBacon + adjMatrix[curr.userNum][i]));
			}
		}
		
		adjMatrix[userNum][findOtherNum] = adjMatrix[findOtherNum][userNum] = curr.numOfKevinBacon;
	}

	static class Node implements Comparable<Node> {
		int userNum, numOfKevinBacon; // 유저번호, 케빈 베이컨의 수

		public Node(int userNum, int numOfKevinBacon) {
			this.userNum = userNum;
			this.numOfKevinBacon = numOfKevinBacon;
		}

		@Override
		public int compareTo(Node other) {
			// 케빈 베이컨 수가 낮은 것 부터 꺼내와야지 제대로 된 수를 알 수 있음
			return this.numOfKevinBacon - other.numOfKevinBacon;
		}
	}
	
}
