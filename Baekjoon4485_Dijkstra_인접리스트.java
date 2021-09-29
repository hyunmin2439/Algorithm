package solved.submit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 다익스트라 알고리즘을 사용

// 각 좌표 (0, 0), (0, 1) ... (N-1, N-1), 0 ~ N 정점으로 생각

// 각 정점에서 사방 탐색으로 인접 리스트를 만듬

// 다익스트라 알고리즘 이용

// 모든 정점을 방문할 때까지 최소비용 계산


// Memory:32232KB / Time:660ms
public class Baekjoon4485_Dijkstra_인접리스트 {

	static final int INF = 1_000_000;
	
	static int t, N, vCnt, res;
	static int[][] map;

	static List<LinkedList<int[]>> edgeList; // 0 : 정점, 1 : 비용
	
	static int[] dy = { -1, 1,  0, 0 };
	static int[] dx = {  0, 0, -1, 1 };
	static int[] dn;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			N = Integer.parseInt(in.readLine());
			
			if(N == 0) break; // 종료 조건
			
			vCnt = N * N; // 정점의 개수
			res = Integer.MAX_VALUE; // 결과값
			map = new int[N][N]; // 비용정보
			dn = new int[] { -N, N, -1, 1 }; // 정점 생성시 필요한 배열
			
			edgeList = new ArrayList<>();
			
			for (int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 간선 리스트 만들기
			makeEdge();
			
			// 다익스트라
			dijkstra();
			
			// 결과 출력
			System.out.println("Problem " + ++t + ": " + res);
		}
		
		in.close();
	}

	private static void makeEdge() {
		
		// 각 정점 간선 리스트 생성
		for (int i = 0; i < vCnt; i++) {
			edgeList.add(new LinkedList<>());
		}
		
		// 각 정점에서 사방 탐색 간선 리스트 생성
		int num = 0; // 정점 번호
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				
				// 사방 탐색
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					// 좌표 범위 체크
					if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
					
					// num 정점 -> 상하좌우에 따른 정점 번호, 비용
					edgeList.get(num).add(new int[] { num + dn[d], map[ny][nx]} );
				}
				
				num++; // 정점 번호 증가
			}
		}
		
	}

	private static void dijkstra() {		
		boolean[] visit = new boolean[vCnt];
		int[] minEdge = new int[vCnt];
		
		// 모든 노드로 갈 수 없다고 초기 세팅
		Arrays.fill(minEdge, INF);
		
		// 시작 정점 비용 더하고 시작
		minEdge[0] = map[0][0];
		
		// 반복될 횟수 = 정점수
		int circ = vCnt;
		
		while( circ-- > 0 ) {
			int v = 0; // 선택된 정점
			int vMin = INF; // 선택된 정점의 비용
			
			for (int i = 0; i < vCnt; i++) {
				if( !visit[i] && vMin > minEdge[i] ) {
					v = i;
					vMin = minEdge[i];
				}
			}
			
			// 방문처리
			visit[ v ] = true;
			
			// v정점에서 갈 수 있는 다른 정점들
			for (int[] n : edgeList.get(v)) {
				// min(n[0]정점에 가는 비용, v 정점을 거쳐서 n[0]정점에 가는 비용)
				minEdge[ n[0] ] = Math.min(minEdge[ n[0] ], vMin + n[1] );
			}
		}

		res = minEdge[vCnt - 1]; // 결과
	}
}
