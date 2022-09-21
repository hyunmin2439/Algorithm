package solved;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Input
 * N (1 <= N <= 500,000)
 * 
 * Queue(1, 2, ... , N)
 * 
 * while(true)
 * 	dequeue
 * 	if(queue.isEmpty()) break;
 *  enqueue <- dequeue
 * 
 * Ouput
 * last card Number
 */

public class BJ_2164_카드2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out ) );
		
		Queue<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			queue.offer(i);
		}

		int number = 0;
		while( true ) {
			number = queue.poll();
			if( queue.isEmpty() ) break;
			queue.offer(queue.poll());
		}
		
		bw.write(String.valueOf(number));
		br.close();
		bw.close();
	}

}
