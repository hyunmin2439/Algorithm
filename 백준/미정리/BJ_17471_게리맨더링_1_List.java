package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
 * 첫 풀이 : List를 사용하였지만 굳이 List를 사용할 필요는 없어 보인다.
 * 
 * Memory:16228KB / Time:164ms
 */

public class BJ_17471_게리맨더링_1_List {
	
	static int N, flag, min;
	static int[] population;
	static boolean[][] adjMatrix;

	static List<Integer> aList, bList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		min = Integer.MAX_VALUE;
		
		population = new int[N + 1]; // 0:dummy
		adjMatrix = new boolean[N + 1][N + 1];
		
		aList = new ArrayList<>();
		bList = new ArrayList<>();
		
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
		
		int end = (int)Math.pow(2, N) - 2; // bitMask = 2^N - 1 - 1(모두 선택하는 경우의 수)
		while(++flag <= end) {
			// 초기화 하고 시작
			aList.clear();
			bList.clear();
			
			splitSet(); // 두 구역으로 나누기
			
			// 두 구역 인접하는지 확인하고 인접하지 않으면 건너뛰기
			if( !isAdjacent(aList) ) continue; 
			if( !isAdjacent(bList) ) continue; 
			
			// 두 구역의 인원 수 구해서 최소값 갱신
			min = Math.min(min, getPopulDiff());
		}
		
		// min값이 갱신되지 않았으면 두 구역으로 나눌 수 없는 것
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
		
		in.close();
	}

	private static int getPopulDiff() {
		int aPopul = 0, bPopul = 0;
		
		// a구역 인원수 구하기
		for (int a : aList) {
			aPopul += population[a];
		}
		
		// b구역 인원수 구하기
		for (int b : bList) {
			bPopul += population[b];
		}
		
		return Math.abs(aPopul - bPopul);
	}

	private static boolean isAdjacent(List<Integer> list) {
		boolean[] visit = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		// 첫 노드 처리
		int n = list.get(0);
		visit[n] = true;
		queue.offer(n);
		
		while( !queue.isEmpty() ) {
			n = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				// n에서 i로 갈 수 없거나, 자기 자신, 방문했으면 제외
				if( !adjMatrix[n][i] || n == i || visit[i] ) continue;
				
				// 자신의 리스트가 아닌 것 제외 시키기
				boolean check = false;
				for (int j = 0, size = list.size(); j < size; j++) {
					if(i == list.get(j)) {
						check = true;
						break;
					}
				}
				
				if( !check ) continue;
				
				visit[i] = true; // 방문처리
				queue.offer(i); // 큐에 담기
			}
		}
		
		// 방문처리 완료후 list에 담긴 노드와 비교
		for (int i = 0, size = list.size(); i < size; i++) {
			// list의 담긴 노드가 방문하지 않았으면 연결되지 않았다는 것
			if( !visit[list.get(i)] ) return false;
		}
		
		return true;
	}

	// bitMask에 따라 a, b 구역으로 나누는 메서드
	private static void splitSet() {
		for (int i = 0; i < N; i++) {
			if( (flag & 1 << i) != 0 ) aList.add(i + 1);
			else bList.add(i + 1);
		}
	}

}
