import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

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
			
			visited[i] = true;
			
			for (int j = 0; j < N; j++) {
				if(adjMatrix[i][j]) {
					cnt++;
					queue.offer(j);
					visited[j] = true;
				}
			}
			
			cnt += bfs(i);
			
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
		
		in.close();
	}

	private static int bfs(int i) {
		int cnt = 0;
		queue.offer(i);
		
		while( !queue.isEmpty() ) {
			int n = queue.poll();
			
			for (int j = 0; j < N; j++) {
				if(visited[j]) continue;

				if( !adjMatrix[n][j] ) continue;

				cnt++;
				
				visited[j] = true;
			}
		}
		
		return cnt;
	}
}