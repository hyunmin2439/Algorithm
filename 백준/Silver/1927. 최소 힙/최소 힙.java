import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pqueue = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		int x;
		while(N-- > 0) {
			x = Integer.parseInt(in.readLine());
			
			if(x == 0)
				if(pqueue.isEmpty())
					sb.append(0).append('\n');
				else
					sb.append(pqueue.poll()).append('\n');
			else
				pqueue.offer(x);
		}
		
		System.out.print(sb);
		
		in.close();
	}
}