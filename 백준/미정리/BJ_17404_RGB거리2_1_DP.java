package solved.notsubmit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 조건 1. i번째 집은 i - 1, i + 1번 집의 색과 같지 않아야 한다.
 * 
 * 조건 2. 1번째 집과 N번째 집은 색이 같으면 안된다.
 * 
 * 점화식
 * 
 * if(j != k) dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + house[i][j]);
 * 
 * 점화식을 말로 풀이하자면 i번 집이 j색상으로 칠해진 최소값을 가지고 있는 배열이다.
 * 
 * 즉, i - 1과 i의 색상이 같지만 않다면 앞서 무슨 색을 칠했던 
 * 
 * i번째 집이 j(0:R, 1:G, 2:B)색상으로 칠해진 최소값을 가진다.
 * 
 * 이렇게만 한다면 조건1은 i + 1집의 색상을 고려하지 않더라도 만족한다.
 * 
 * 문제는 조건 2가 까다로운 부분인데, 첫 집의 색상을 고정한 체로 DP를 3번 구해서 최소값을 구하면 된다.
 * 
 * 따라서 아래와 같이 0번째 집의 색상을 고정한체 dp[0][j]를 초기화하고 아래 조건을 추가하였다.
 *  
 * if(i == 1 && j == f) continue; / if(i == N - 1 && j == f) continue;
 * 	
 * Memory:14548KB / Time:144ms
 */

public class BJ_17404_RGB거리2_1_DP {

	private static final int INF = 1_000_000_000;
	
	private static int N, minTotCost, house[][], dp[][];
	
	public static void main(String[] args) throws Exception {
		input();
		
		// 첫 집 색상 고정
		for(int f = 0; f < 3; f++) {
			
			// 고정한 색상의 값으로 dp 초기화
			for(int i = 0; i < 3; i++) {
				dp[0][i] = house[0][f];
			}
			
			for(int i = 1; i < N; i++) {
				
				for(int j = 0; j < 3; j++) {
					dp[i][j] = INF; // 기본 최대값으로 설정
					
					// 0번째 집과 1번째 집의 색상이 같으면 안됨.
					if(i == 1 && j == f) continue;
					
					// 0번째 집과 N - 1번째 집 색상이 같으면 안됨.
					if(i == N - 1 && j == f) continue;
					
					for(int k = 0; k < 3; k++) {
						// 이전 집 색상이랑 같으면 안됨.
						if(j == k) continue;
						
						dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + house[i][j]);
					}
				}
				
			}
			
			// 최소값
			for(int i = 0; i < 3; i++) {
				minTotCost = Math.min(minTotCost, dp[N - 1][i]);
			}
		}
		
		System.out.print( minTotCost );
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		minTotCost = Integer.MAX_VALUE;
		N = Integer.parseInt(in.readLine());
		house = new int[N][3]; // 집을 칠하는데 드는 비용
		dp = new int[N][3]; // 누적 최소값을 저장하는 배열
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		in.close();
	}
	
}
