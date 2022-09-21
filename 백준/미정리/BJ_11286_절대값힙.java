package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_11286_절대값힙 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		
		PriorityQueue<Integer> pqueue = 
				new PriorityQueue<>( 
						(a, b) -> {
							if(Math.abs(a) != Math.abs(b))
								return Math.abs(a) - Math.abs(b);
							else
								return a - b;
						}
				);
		
		int num;
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(in.readLine());
			
			if(num == 0) {				
				if( pqueue.isEmpty() )
					sb.append(0).append('\n');
				else
					sb.append(pqueue.poll()).append('\n');
			}
			else
				pqueue.offer(num);
		}
		
		System.out.print(sb);
		
		in.close();
	}
	
}
