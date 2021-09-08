package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 문제
// 방문했던 곳이라면 똑같이 반복됨 -> 쳐내기
// BFS이기 때문에 목적지에 도착하면 그것이 최소 경로
public class Baekjoon1697 {

	static int maxLen = 100_001;
	static int N, K;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new boolean[maxLen];
		
		System.out.println(bfs());
		br.close();
	}

	private static int bfs() {
		int sec = 0;
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(N);
		visit[N] = true;
		
		while( !queue.isEmpty() ) {
			int len = queue.size();
			
			for (int i = 0; i < len; i++) {
				Integer x = queue.poll();
				
				if(x == K) return sec;
				
				if(x * 2 < maxLen && !visit[x * 2]) {
					queue.offer(x * 2);
					visit[x * 2] = true;
				}
				if(x + 1 < maxLen && !visit[x + 1]) {
					queue.offer(x + 1);
					visit[x + 1] = true;
				}
				if(x - 1 >= 0 && !visit[x - 1]) {
					queue.offer(x - 1);
					visit[x - 1] = true;
				}
			}
			
			sec++;
		}
		
		return sec;
	}
	
}
