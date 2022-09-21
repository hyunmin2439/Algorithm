import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// Memory:26,272KB / Time:324ms
public class BJ_11279_최대힙 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(in.readLine()); // 연산 개수
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> heap = new PriorityQueue<>( Collections.reverseOrder() );
		for(int i = 0; i < N; i++) {
			int acc = Integer.parseInt(in.readLine());
			
			if(acc > 0)
				heap.offer(acc);
			else
				sb.append( heap.isEmpty() ? 0 : heap.poll() ).append('\n');
		}
		
		System.out.print(sb);
		
		in.close();
	}

}
