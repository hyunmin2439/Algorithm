package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * BFS를 사용한 풀이.
 * 
 * 첫 풀이시 A - B - C - D 와 같이 연결되는 
 * 
 * 친구의 한계가 없는 줄 알고 풀었는데 틀렸다.
 * 
 * 문제 조건을 다시 보니 A - B - C와 같이
 * 
 * 친구의 친구의 관계만 Count
 * 
 * Memory:14900KB / Time:156ms
 */

public class BJ_1058_친구_BFS {

	static int N, ans;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		queue = new LinkedList<>();
		
		N = Integer.parseInt(in.readLine());
		adjMatrix = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] YN = in.readLine().split("");
			for (int j = 0; j < N; j++) {
				if(YN[j].charAt(0) == 'Y') 
					adjMatrix[i][j] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			visited = new boolean[N];
			
			visited[i] = true; // 자기 자신은 아니니 미리 체크
			
			// 친구인 사람 찾기
			for (int j = 0; j < N; j++) {
				if(adjMatrix[i][j]) {
					cnt++; // 친구 카운트
					queue.offer(j); // 친구 큐 목록에 넣어놓기
					visited[j] = true;
				}
			}
			
			cnt += bfs(i);
			
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
		
		in.close();
	}

	// A - i - C, i를 통해 C 관계가 형성되는 친구 구함
	private static int bfs(int i) {
		int cnt = 0;
		queue.offer(i);
		
		while( !queue.isEmpty() ) {
			int n = queue.poll();
			
			for (int j = 0; j < N; j++) {
				// 방문한 곳 제외
				if(visited[j]) continue;
				
				// 친구가 아니면 제외
				if( !adjMatrix[n][j] ) continue;
				
				// 2-친구 count
				cnt++;
				
				// 방문한 친구
				visited[j] = true;
			}
		}
		
		return cnt;
	}

}
