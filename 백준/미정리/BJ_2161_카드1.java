package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2161_카드1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while( true ) {
			sb.append( queue.poll() ).append(' ');
			
			if( queue.isEmpty() ) break;
			
			queue.offer( queue.poll() );
		}
		
		System.out.print(sb);
	}

}
