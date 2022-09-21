package myAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_10845_큐 {

	static int N;
	static String inst; 
	static StringBuilder sb;
	static StringTokenizer st;
	static Deque<Integer> deque;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sb = new StringBuilder();
		deque = new LinkedList<>();
		
		N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			execute( st.nextToken() );
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static void execute(String exe) {
		switch(exe) {
		case "push": 
			deque.offer(Integer.parseInt(st.nextToken()));
			break;
		case "pop":
			if( deque.isEmpty() ) sb.append("-1\n");
			else sb.append(deque.poll() + "\n");
			break;
		case "size":
			sb.append(deque.size() + "\n");
			break;
		case "empty":
			if(deque.isEmpty()) sb.append("1\n");
			else sb.append("0\n");
			break;
		case "front":
			if( deque.isEmpty() ) sb.append("-1\n");
			else sb.append(deque.peekFirst() + "\n");
			break;
		case "back":
			if( deque.isEmpty() ) sb.append("-1\n");
			else sb.append(deque.peekLast() + "\n");
			break;
		}
	}

}
