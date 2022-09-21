import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_18258_큐2 {
	
	private static int INF = 1_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		Deque<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(in.readLine());
		
		int num, result;
		String inst;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			inst = st.nextToken();
			num = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
			
			result = queueInst(queue, inst, num);
			
			if(result != INF)
				sb.append(result).append('\n');
		}
		
		System.out.print(sb);
		in.close();
	}
	
	private static int queueInst(Deque<Integer> queue, String inst, int num) {
		switch(inst) {
		case "push":
			queue.offer(num);
			return INF;
		case "pop":
			return queue.isEmpty() ? -1 : queue.poll();
		case "size":
			return queue.size();
		case "empty":
			return queue.isEmpty() ? 1 : 0;
		case "front":
			return queue.isEmpty() ? -1 : queue.peek();
		case "back":
			return queue.isEmpty() ? -1 : queue.peekLast();
		}
		
		return INF;
	}

}
