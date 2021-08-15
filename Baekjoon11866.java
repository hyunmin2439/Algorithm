package myAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon11866 {

	static int N, K;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder("<");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while( !queue.isEmpty() ) {
			for (int i = 0; i < K - 1; i++) {
				queue.offer(queue.poll());
			}
			
			sb.append(queue.poll() + ", ");
		}
		
		// 마지막 , 지우기
		sb.setLength(sb.length() - 2);
		sb.append(">");
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}
}
