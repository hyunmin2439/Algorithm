import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 도시 a에서 b로 가는 길이 여러갈래라는 조건이 붙은 Floyd
 * 
 * 이 조건만 주의하면 단순히 Floyd 알고리즘을 적용하면 쉽게 풀 수 있는 문제
 * 
 * Memory:42396KB / Time:512ms
 */

public class BJ_11404_플로이드 {

	static final int INF = 100_000_000;
	
	static int N, M;
	
	static int[][] matrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		matrix = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(matrix[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			int a = Integer.parseInt(st.nextToken()); // 도시 a
			int b = Integer.parseInt(st.nextToken()); // 도시 b
			int cost = Integer.parseInt(st.nextToken()); // 비용
			
			// 도시 a에서 b로 가는길이 여러갈래 일수도 있음
			matrix[a][b] = Math.min(matrix[a][b], cost);
		}
		
		ployd();
		
		printMatrix();
		
		in.close();
	}

	private static void ployd() {
		for(int k = 1; k <= N; k++) { // 경유지
			for(int a = 1; a <= N; a++) { // a도시
				if( a == k ) continue;
				for(int b = 1; b <= N; b++) { // b도시
					if( b == k || b == a) continue;
					matrix[a][b] = Math.min(matrix[a][b], matrix[a][k] + matrix[k][b]);
				}
			}
		}
	}

	private static void printMatrix() {
		StringBuilder sb = new StringBuilder();
		
		for(int a = 1; a <= N; a++) { // a도시
			for(int b = 1; b <= N; b++) { // b도시
				// 도시 a에서 b로 갈 수 없는 경우
				if( matrix[a][b] >= INF ) matrix[a][b] = 0;
				
				sb.append(matrix[a][b]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}
