package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Floyd-Warshall Algorithm
 * 
 * BFS를 사용하면 굉장히 복잡함.
 * 
 * 간단하게 Floyd-Warshall 알고리즘으로 풀 수 있음.
 * 
 * Memory:14136KB / Time:124ms
 */

public class BJ_1389_케빈베이컨의6단계법칙_2_Floyd {

	static final int MAX = 100; // N은 최대 100, 케빈 베이컨 수 100 나올 수 없음
	
	static int N, M; // 유저 수, 친구 관계 수
	static int minUser, minNumOfKevinBacon; // 가장 작은 케빈 베이컨의 수를 가진 유저번호, 수
	
	static int[][] adjMatrix; // 친구 관계 나타냄
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		minNumOfKevinBacon = Integer.MAX_VALUE;
		
		adjMatrix = new int[N + 1][N + 1];
		
		// 최대값으로 초기화 => 나올 수 없는 값 => 케빈 베이컨 수를 모르는 것
		for(int i = 1; i <= N; i++) {
			Arrays.fill(adjMatrix[i], MAX);			
		}
		
		// 친구 관계 연결
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = adjMatrix[b][a] = 1; // 친구면 케빈 베이컨 수 1
		}
		
		floyd();
		
		for(int i = 1; i <= N; i++) {
			int totNumOfKevinBacon = 0;
			
			for(int j = 1; j <= N; j++) {
				totNumOfKevinBacon += adjMatrix[i][j];
			}
			
			// 같으면 번호가 작은 사람이기 때문에 바꾸지 않음.
			if(totNumOfKevinBacon < minNumOfKevinBacon) {
				minUser = i; // 번호 저장
				minNumOfKevinBacon = totNumOfKevinBacon; // 작은 값 저장
			}
		}
		
		System.out.print(minUser);
		in.close();
	}

	private static void floyd() {
		
		for(int k = 1; k <= N; k++) { // 경유하는 유저 번호
			for(int i = 1; i <= N; i++) {
				if(i == k) continue;
				
				for(int j = 1; j <= N; j++) {
					if(j == i || j == k) continue;
					
					// k 친구를 경유
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
	}

}
