import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Memory: 16,168KB / Time:164ms
 */
public class BJ_2660_회장뽑기_Floyd {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(in.readLine());
		
		int INF = 100, a, b, adjMatrix[][] = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(adjMatrix[i], INF);
		}
		
		while(true) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1) break;
			
			adjMatrix[a][b] = adjMatrix[b][a] = 1;
		}

		Floyd(adjMatrix, N);
		
		
		int minScore = INF, currMax = 0;
		List<Integer> list = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			currMax = 0;
			
			for(int j = 1; j <= N; j++) {
				if(i == j) continue; // 자기 자신 제외
				
				currMax = Math.max(currMax, adjMatrix[i][j]); // 최대값이 그 사람의 점수
			}
			
			if(minScore >= currMax) {
				if(minScore > currMax) {
					list.clear();
					minScore = currMax;					
				}
				
				list.add(i);
			}
		}
		
		System.out.println(minScore + " " + list.size());
		list.forEach( el -> System.out.print(el + " ") );
		
		in.close();
	}
	
	private static void Floyd(int[][] adjMatrix, int N) {
		for(int k = 1; k <= N; k++) {			
			for(int i = 1; i <= N; i++) {
				if(k == i) continue;
				for(int j = 1; j <= N; j++) {
					if(k == j || i == j) continue;
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
	}

}
