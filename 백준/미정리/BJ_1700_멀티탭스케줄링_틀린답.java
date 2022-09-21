package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 가장 오래동안 쓰는 것을 남겨두고 빼는 방식을 택함.
 * 
 * 오답 케이스
 * 3 14
 * 1 4 3 2 5 4 3 2 5 3 4 2 3 4
 * 
 * 출력:5
 * 정답:4
 * 
 * 가장 늦게 쓰는 콘센트를 뽑아야 한다.
 * 
 */

public class BJ_1700_멀티탭스케줄링_틀린답 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		
		int[] order = new int[K], priority = new int[K + 1];
		
		Arrays.fill(priority, -1);
		
		// 입력
		for(int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
			priority[ order[i] ] = Math.max(priority[i], i); // 가장 늦게 나오는 index 저장
		}
		
		Node[] product = new Node[K + 1];
		for(int i = 1; i <= K; i++) {
			if(priority[i] != -1) 
				product[i] = new Node(i, priority[i]);
		}
		
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		
		int cnt = 0;
		for(int i = 0; i < K; i++) {
			if( pqueue.contains(product[ order[i] ]) ) continue;
			
			if(pqueue.size() >= N) {
				pqueue.poll();
				cnt++;
			}
			
			pqueue.offer( product[ order[i] ]);
		}
		
		System.out.print(cnt);
		
		in.close();
	}
	
	static class Node implements Comparable<Node> {
		int num, priority;
		
		public Node(int num, int priority) {
			this.num = num;
			this.priority = priority;
		}
		
		public int compareTo(Node other) {
			return this.priority - other.priority;
		}
	}
}
