import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Memory:18136KB / Time:424ms
public class BJ_11403_경로찾기 {

	static int N;
	static int[][] adjMatrix, printMatrix;
	
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		queue = new LinkedList<>();
		N = Integer.parseInt(in.readLine());
		
		adjMatrix = new int[N][N];
		printMatrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				printMatrix[i][j] = adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			bfs(i);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(printMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
		in.close();
	}

	private static void bfs(int num) {
		boolean[] visited = new boolean[N];
		
		queue.offer(num);
		
		while( !queue.isEmpty() ) {
			int i = queue.poll();
			
			for (int j = 0; j < N; j++) {
				if( visited[j] ) continue;
				
				if( adjMatrix[i][j] == 0 ) continue;
				
				visited[j] = true;
				printMatrix[num][j] = 1;
				queue.offer(j);
			}
		}
	}

}
