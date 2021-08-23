package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// memory : 20204KB / time : 308ms
// 중첩 List 사용
public class Baekjoon1260_ArrayList {
	
	// 정점의 개수, 간선의 개수, 시작 정점 번호
	static int N, M, V;

	static List< List<Integer> > headList;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		headList = new ArrayList<List<Integer>>();
		
		for (int i = 0; i <= N; i++) {
			headList.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			headList.get(from).add(to);
			headList.get(to).add(from);
		}
		
		// 작은 번호 부터 순회하기 위해 정렬하기
		for (int i = 1; i <= N; i++) {
			Collections.sort(headList.get(i));
		}
		
		dfs(V, new boolean[N + 1]);
		sb.setCharAt(sb.length() - 1, '\n'); // 마지막 공백 개행문자로 바꾸기
		bfs();
		
		System.out.println(sb.toString());
		br.close();
	}
	

	private static void dfs(int curr, boolean[] visit) {
		
		sb.append(curr).append(" ");
		visit[curr] = true;
		
		for (int i = 0; i < headList.get(curr).size(); i++) {
			int num = headList.get(curr).get(i);
			// 방문하지 않았다면
			if( !visit[num] ) {
				dfs(num, visit);
			}
		}
		
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		
		// 첫번째 정점에 대한 처리
		queue.offer(V);
		visit[V] = true;
		
		while( !queue.isEmpty() ) {
			int curr = queue.poll();
			sb.append(curr).append(" ");
			
			for (int i = 0; i < headList.get(curr).size(); i++) {
				int num = headList.get(curr).get(i);
				// 방문하지 않았다면
				if( !visit[num] ) {
					queue.offer(num);
					visit[num] = true;					
				}
			}
		}
		
		sb.append("\n");
	}
	
}
