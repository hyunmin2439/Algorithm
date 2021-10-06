package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 게리멘더링
 * 
 * 주어진 그래프를 두 그룹으로 나누어 두 그룹의 인구 차이를 최소로 하는 문제
 * 
 * 부분집합 생성 : Binary Counting 기법 사용
 * 
 * 각 그래프의 모든 정점이 연결되었는지 확인 : BFS 탐색
 * 
 * 두번째 풀이 : 첫번째 풀이에서 List를 제거한 풀이
 * 
 * List 대신 bitMask를 통해 두 그룹이 나눠져 있다고 생각하고 처리
 * 
 * 또한 bitMask 1자리를 dummy로 둬서 i + 1과 같은 번거로운 코드처리 줄임
 * 
 * Memory:16268KB / Time:144ms
 */

public class Baekjoon17471_두번째풀이 {
	
	static int N, flag, min;
	static int[] population;
	static boolean[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		min = Integer.MAX_VALUE;
		
		population = new int[N + 1]; // 0:dummy
		adjMatrix = new boolean[N + 1][N + 1]; // 0:dummy
		
		// 인구수 입력받기
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접한 구역 입력받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			
			int K = Integer.parseInt(st.nextToken()); // 인접한 구역수
			
			for (int k = 0; k < K; k++) {
				int j = Integer.parseInt(st.nextToken());
				
				adjMatrix[i][j] = true; // i구역과 j구역은 인접해있다.
				
				// 무방향 그래프라서 반대쪽도 해줘야 하지만 어짜피 입력으로 반대쪽이 주어진다.
				// adjMatrix[j][i] = true;
			}
		}
		
		int end = (int)Math.pow(2, N + 1) - 2; // bitMask = 2^N - 1 - 1(모두 선택하는 경우의 수), bit 1자리 dummy
		while(++flag <= end) {
			int[] num = splitSet(); // 두 정점 하나씩 가져오기
			
			// 두 구역 인접하는지 확인하고 인접하지 않으면 건너뛰기
			if( !isAdjacent(num[0], flag) ) continue;
			if( !isAdjacent(num[1], ~flag) ) continue; // flag비트 뒤집어서 넘겨주기
			
			// 두 구역의 인원 수 구해서 최소값 갱신
			min = Math.min(min, getPopulDiff());
		}
		
		// min값이 갱신되지 않았으면 두 구역으로 나눌 수 없는 것
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
		in.close();
	}

	private static int getPopulDiff() {
		int aPopul = 0, bPopul = 0;
		
		// a, b구역 인원수 구하기
		for (int i = 1; i <= N; i++) {
			if( (flag & 1 << i) != 0 ) aPopul += population[i];
			else bPopul += population[i];
		}
		
		return Math.abs(aPopul - bPopul);
	}

	private static boolean isAdjacent(int v, int flag) {
		boolean[] visit = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		// 첫 노드 처리
		visit[v] = true;
		queue.offer(v);
		
		while( !queue.isEmpty() ) {
			v = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				// 같은 그룹이 아닌 것 제외 시키기
				if( (flag & 1 << i) == 0 ) continue;

				// n에서 i로 갈 수 없거나, 방문했으면 제외(자기 자신도 제외됨)
				if( !adjMatrix[v][i] || visit[i] ) continue;
				
				visit[i] = true; // 방문처리
				queue.offer(i); // 큐에 담기
			}
		}
		
		// 방문처리 완료후 
		for (int i = 1; i <= N; i++) {
			// 같은 그룹인데 방문하지 않았으면 연결되지 않았다는 것
			if( (flag & 1 << i) != 0 && !visit[i] ) return false;
		}
		
		return true;
	}

	// bitMask에 따라 a, b 구역으로 나누는 메서드
	private static int[] splitSet() {
		int[] num = new int[2];
		
		// a 구역 선택된 정점 하나 가져오기
		for (int i = 1; i <= N; i++) {
			if( (flag & 1 << i) != 0 ) {
				num[0] = i;
				break;
			}
		}
		
		// b 구역 선택된 정점 하나 가져오기
		for (int i = 1; i <= N; i++) {
			if( (flag & 1 << i) == 0 ) {
				num[1] = i;
				break;
			}
		}
		
		return num;
	}

}
