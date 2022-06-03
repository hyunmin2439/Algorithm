import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, inCome[];
	
	public static void main(String[] args) throws Exception {
		List<List<Integer>> adjList = input();
		
		int[] answer = topology(adjList);
		
		for(int i = 0; i < N; i++) {
			System.out.print(answer[i] + " ");
		}
	}
	
	private static List<List<Integer>> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inCome = new int[N + 1];
		
		List<List<Integer>> adjList = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			adjList.add(new LinkedList<>());
		}
		
		int a, b;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			inCome[b]++;
			adjList.get(a).add(b);
		}
		
		in.close();
		
		return adjList;
	}
	
	private static int[] topology(List<List<Integer>> adjList) {
		PriorityQueue<Integer> pqueue = new PriorityQueue<>();
		List<Integer> list;
		int cnt = 0, a, b;
		int[] answer = new int[N];
		
		// 진입 차수 0인 문제 전부 우선순위 큐에 담기
		for(int i = 1; i <= N; i++) {
			if(inCome[i] == 0) 
				pqueue.add(i);
		}
		
		while(cnt < N) {
			// 우선순위 큐이기 때문에 쉬운문제 부터 꺼내옴
			a = pqueue.poll();
			answer[cnt++] = a;
			
			// 연결된 간선을 제거하면서 진입차수를 감소
			// -> 감소하고 진입 차수가 0이 된다면 우선순위 큐에 담기
			list = adjList.get(a);
			for(int i = 0; i < list.size(); i++) {
				b = list.get(i);
				if(--inCome[b] == 0)
					pqueue.offer(b);
			}
		}
		
		return answer;
	}
}