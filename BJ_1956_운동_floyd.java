import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:62,472KB / Time:792ms
 */

public class BJ_1956_운동_floyd {

	private static final int INF = 10_000_000;
	private static int V;
	
	public static void main(String[] args) throws Exception {
		int[][] dist = input();
		floyd(dist);
		int answer = getMinDistCycle(dist);
		System.out.print(answer >= INF ? -1 : answer);
	}
	
	private static int[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] dist = new int[V + 1][V + 1];
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				dist[i][j] = INF;
			}
		}
		
		int a, b, c;
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			dist[a][b] = c;
		}
		
		in.close();
		
		return dist;
	}
	
	private static void floyd(int[][] dist) {
		for(int k = 1; k <= V; k++) {
			for(int i = 1; i <= V; i++) {
				if(k == i) continue;
				for(int j = 1; j <= V; j++) {
					if(k == j || i == j) continue;
					
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
	
	private static int getMinDistCycle(int[][] dist) {
		int minDist = INF;
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				minDist = Math.min(minDist, dist[i][j] + dist[j][i]);
			}
		}
		
		return minDist;
	}
}
