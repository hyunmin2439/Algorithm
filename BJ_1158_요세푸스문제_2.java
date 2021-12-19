package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1158_요세푸스문제_2 {

	static int N, K;
	static Queue<Integer> queue;
	
	static BufferedReader  br;
	static BufferedWriter  bw;
	static StringTokenizer st;
	static StringBuilder   sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		sb.append("<");
		while( !queue.isEmpty() ) {
			for (int i = 0; i < K - 1; i++) queue.offer(queue.poll());
			sb.append(queue.poll()).append(",").append(" ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
