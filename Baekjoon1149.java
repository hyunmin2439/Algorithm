package uploaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 첫 풀이시 Grid 방법으로 풀려고 시도
// rgb를 class로 만들어 색상, 비용 저장
// 이 rgb class를 각 라인마다 정렬하여
// 1 ~ N까지 차례대로 앞의 집과 색상이 같지 않으면
// 비용을 더하는 식으로 하였지만 실패.
// Grid로 오해하기 쉬운 문제

// DP 방법으로 현재 집 색상을 선택하였을 때
// 앞집까지 고려된 최적 비용을 통해 현재 집 최적비용 계산
// 차례차례 내려가면서 마지막 N번째 집까지 계산하고 나면
// N번째 3가지 경우중 가장 작은 최적비용을 선택.

public class Baekjoon1149 {
	
	static int N;
	static int[][] rgb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		rgb = new int[N + 1][3];

		// 0: dummy겸 1번 집부터 점화식 적용
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 0:빨, 1:초, 2:파
			for (int j = 0; j < 3; j++) {
				// 큰 값 채우기 : 비교를 위해
				rgb[i][j] = Integer.MAX_VALUE;
				
				// 비용 입력
				int cost = Integer.parseInt(st.nextToken());
				
				// 이전 집들까지 고려된 최적 비용을 통해 현재 집의 최적 비용 계산
				for (int k = 0; k < 3; k++) {
					
					// 이전 집 색상과 현재집 색상이 같지 않을 경우
					if(j != k) {
						// ex) min( i-1집(1:초록]) + i집(0:빨강), i-1집(2:파랑) + i집(0:빨강) )
						rgb[i][j] = Math.min(rgb[i][j], rgb[i - 1][k] + cost);						
					}
				}
			}
		}
		
		// 더 깔끔한 코드
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			rgb[i][0] = Math.min(rgb[i - 1][1], rgb[i - 1][2]) + Integer.parseInt(st.nextToken());
			rgb[i][1] = Math.min(rgb[i - 1][0], rgb[i - 1][2]) + Integer.parseInt(st.nextToken());
			rgb[i][2] = Math.min(rgb[i - 1][0], rgb[i - 1][1]) + Integer.parseInt(st.nextToken());
		}
		
		// 최종 N번집까지 고려된 최적비용 중 제일 작은 최적비용 출력
		System.out.println( Math.min(rgb[N][0], Math.min(rgb[N][1], rgb[N][2]) ) );
		
		br.close();
	}

}
