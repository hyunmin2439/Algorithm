package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Floyd – Warshall algorithm
 * 
 * Memory : 20944KB / Time : 2528ms
 */

public class BJ_1238_파티 {

	private static final int INF = 1_000_000_000;
	private static int N, M, X;
	private static int[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		input();
		
		floyd();
		
		output();
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N + 1][N + 1]; // 0 dummy : 집 번호 1번부터 시작
		
		// 모든 길 큰 값으로 갈 수 없는 길로 초기화
		for(int i = 0; i <= N; i++) {
			Arrays.fill(adjMatrix[i], INF);
		}
		
		int start, dest, time;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			start = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			adjMatrix[start][dest] = time;
		}
		
		in.close();
	}
	
	private static void floyd() {
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
	
	private static void output() {
		int currTime = 0, maxTime = 0;
		
		for(int i = 1; i <= N; i++) {
			// 이미 X 위치에 있는 학생 빼고
			if(i == X) continue;

			currTime = adjMatrix[i][X] + adjMatrix[X][i];
			
			maxTime = Math.max(currTime, maxTime);
		}
		
		System.out.print(maxTime);
	}

}
