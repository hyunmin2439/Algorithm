import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		int[] input = input();
		
		int[] answer = bfs(input[0], input[1]);
		
		System.out.print(answer[0] + "\n" + answer[1]);
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		in.close();
		
		return new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
	}

	private static int[] bfs(int N, int K) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[100_001];
		
		queue.offer(new Node(N, 0));
		visited[N] = true;
		
		int minSec = 100_000, cnt = 0;
		Node curr;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			visited[curr.X] = true;
			
			if(curr.sec > minSec) continue;
			
			if(curr.X == K) {
				minSec = curr.sec;
				cnt++;
			}
			
			if( curr.X - 1 >= 0 && !visited[curr.X -  1] )
				queue.offer(new Node(curr.X - 1, curr.sec + 1));
			
			if( curr.X + 1 <= 100_000 && !visited[curr.X + 1])
				queue.offer(new Node(curr.X + 1, curr.sec + 1));
			
			if( curr.X * 2 <= 100_000 && !visited[curr.X * 2])
				queue.offer(new Node(curr.X * 2, curr.sec + 1));
		}
		
		return new int[] { minSec, cnt };
	}
	
	static class Node {
		int X, sec;
		
		public Node(int X, int sec) {
			this.X = X;
			this.sec = sec;
		}
	}
}