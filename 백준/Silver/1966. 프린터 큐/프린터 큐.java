import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		List<Integer> list = new LinkedList<>();
		Queue<Node> queue = new LinkedList<>();
		int N, M, T, priority;
		
		T = Integer.parseInt(in.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(in.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				priority = Integer.parseInt(st.nextToken());
				queue.add( new Node(priority, i) );
				list.add( priority );
			}
			
			Collections.sort(list);
			sb.append( getPrintOrder(list, queue, M) ).append('\n');
			queue.clear();
			list.clear();
		}
		
		System.out.print(sb);

		in.close();
	}
	
	private static int getPrintOrder(List<Integer> list, Queue<Node> queue, int M) {
		int order = 1, idx = list.size() - 1;
		
		while( !queue.isEmpty() ) {
			if( queue.peek().priority == list.get(idx) ) {
				if( queue.poll().idx == M )
					return order;
				idx--;
				order++;
			}
			else queue.offer( queue.poll() );
		}
		
		return order;
	}

	static class Node {
		int priority, idx;
		
		public Node(int priority, int idx) {
			this.priority = priority;
			this.idx = idx;
		}
	}
}
