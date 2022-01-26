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
 * Memory:15752KB / Time:184ms
 */
public class BJ_1326_폴짝폴짝_2_BFS {

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
		
		visited[0] = true;
		
		int base = 0, queueSize = 0, rotationNum = 0;
		
		// queue가 비지 않았고 end를 방문 하지 않았을때 까지만
		while( !queue.isEmpty() && !visited[end] ) {
			queueSize = queue.size();
			rotationNum++;
			
			// 큐 사이즈 만큼 회전 후 rotaionNum 증가시키기 위해
			for(int i = 0; i < queueSize; i++) {
				base = queue.poll();
				
				// for문 두번 돌리는 대신 base % bridge[base]로 갈 수 있는 최저점부터 끝까지
				for(int j = base % bridge[base]; j <= N; j += bridge[base]) {
					// 방문한 곳이거나 원래 다리 위치이면
					if( visited[j] || base == j ) continue;
					
					dp[j] = rotationNum;
					visited[j] = true;
					queue.offer(j);
				}
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
