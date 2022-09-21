package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 노션 링크
 * https://light-caption-620.notion.site/1326-7b5539c798974456a004c867041fd2d5
 * 
 * Memory:15560KB / Time:184ms
 */
public class BJ_1326_폴짝폴짝_3_BFS {

	private static int N, start, end, bridge[];
	
	public static void main(String[] args) throws Exception {		
		input();
		
		int res = solve();
		
		System.out.print(res);
	}

	private static int solve() {
		int[] dp = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true; // 시작점 방문 처리 -> 시작점 == 도착점 대비
		
		int curr = 0; // 현재 출발점
		// queue가 비지 않았고 end를 방문 하지 않았을때 까지만
		while( !queue.isEmpty() && !visited[end] ) {
			curr = queue.poll(); 
			
			for(int i = curr - bridge[curr]; i >= 1; i -= bridge[curr]) {
				// 방문한 곳이
				if( visited[i] ) continue;
				
				dp[i] = dp[curr] + 1;
				visited[i] = true;
				queue.offer(i);
			}
			
			for(int i = curr + bridge[curr]; i <= N; i += bridge[curr]) {
				// 방문한 곳
				if( visited[i] ) continue;
				
				dp[i] = dp[curr] + 1;
				visited[i] = true;
				queue.offer(i);
			}
		}
		
		// end에 도착 못했으면 -1
		return visited[end] ? dp[end] : -1;
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		bridge = new int[N + 1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
			bridge[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		in.close();
	}

}
