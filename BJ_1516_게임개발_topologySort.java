package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 해설
 * https://light-caption-620.notion.site/1516-5e81c9665f3643b28b75937507866416
 * 
 * Memory:26936KB / Time:344ms
 */

public class BJ_1516_게임개발_topologySort {

	private static int N;
	private static int[] inDegreeList;
	private static int[][] buildTimeList;
	private static ArrayList<ArrayList<Integer>> prevBuildList;
	
	public static void main(String[] args) throws Exception {
		input();
		
		topologySort();
		
		output();
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		// init : 편의를 위해 0는 dummy -> 건물번호 1번부터 시작
		inDegreeList = new int[N + 1];
		buildTimeList = new int[2][N + 1];
		prevBuildList = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			prevBuildList.add(new ArrayList<>());
		}
		
		// building input
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			int prevBuild = 0, inDegreeCnt = 0;
			
			st = new StringTokenizer(in.readLine());
			// 0:기본 건설 시간, 1:먼저 지어져야 할 건물들을 포함한 누적 건설 시간
			buildTimeList[0][i] = buildTimeList[1][i] = Integer.parseInt(st.nextToken());
			
			while( true ) {
				// 먼저 지어져야 할 건물 번호
				prevBuild = Integer.parseInt(st.nextToken());
				
				if(prevBuild == -1) break;
				
				inDegreeCnt++; // 진입 차수 증가
				
				// prevBuild 번호의 이후의 지어질 번호는 i
				prevBuildList.get(prevBuild).add(i);
			}
			
			// 진입차수 저장
			inDegreeList[i] = inDegreeCnt;
		}
		
		in.close();
	}
	
	private static void topologySort() {
		Queue<Integer> queue = new LinkedList<>(); // 진입 차수가 0인 정점을 담기 위한 큐
		Queue<Integer> vtxList = new LinkedList<>(); // 방문 여부를 위해 모든 정점을 담고 시작할 큐
		
		int vtx;
		
		for(int i = 1; i <= N; i++) {
			vtxList.offer(i);
		}
		
		// 모든 정점을 큐에 넣고 꺼낼 때 까지 반복
		for(int i = 1; i <= N; i++) {
			
			// 방문 하지 않은 정점들(줄 세워지지 않은 정점)
			for(int j = 0; j < vtxList.size(); j++) {
				vtx = vtxList.poll();
				
				// 진입 차수가 0이 아니라면
				if( inDegreeList[vtx] != 0 ) {
					vtxList.offer(vtx);
					continue;
				}
				
				// 큐에 넣기
				queue.offer(vtx);
			}
			
			// 큐에서 정점 하나 꺼내기
			vtx = queue.poll();
			
			// 해당 정점에서 갈 수 있는 정점들의 진입 차수 및 건설 시간 수정
			for (int destVtx : prevBuildList.get(vtx)) {
				inDegreeList[destVtx]--;
				
				// 여러 정점으로 부터 destVtx에 올 수 있기 때문에 max값을 넣어줘야 한다.
				// buildTimeList[0]에는 건물의 원래buildTime을 가지고 있고 buildTimeList[1]에는 바뀌고 있는 buildTime을 가지고 있다.
				buildTimeList[1][destVtx] = Math.max(buildTimeList[1][destVtx], buildTimeList[0][destVtx] + buildTimeList[1][vtx]);
			}
		}
		
	}

	private static void output() {
		for(int i = 1; i <= N; i++) {
			System.out.println(buildTimeList[1][i]);
		}
	}
}
