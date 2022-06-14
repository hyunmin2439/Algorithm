import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뱀을 타는게 이득인 경우도 있다.
 * 
 * ex) 정답: 4
 * 2 1
 * 2 60
 * 30 98
 * 65 25
 */
public class Main {

	public static void main(String[] args) throws Exception{
		int[] board = input();
		
		int minMove = bfs(board);
		
		System.out.println(minMove);
	}
	
	private static int[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		
		int[] board = new int[101];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			board[ Integer.parseInt(st.nextToken()) ] = Integer.parseInt(st.nextToken());
		}
		
		in.close();
		
		return board;
	}
	
	private static int bfs(int[] board) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];
		
		queue.offer(new Node(1, 0));
		visited[1] = true;
		
		int next = 1;
		Node node;
		while( !queue.isEmpty() ) {
			node = queue.poll();
			
			for(int i = 1; i <= 6; i++) {
				next  = node.num + i;
				
				if(next == 100) return node.move + 1;
				
				if(visited[next]) continue;
				
				next = board[next] != 0 ? board[next] : next;
				visited[next] = visited[board[next]] = true;
				
				queue.offer( new Node(next, node.move + 1) );
			}			
		}
		
		return -1;
	}
	
	static class Node {
		int num, move;
		
		public Node(int num, int move) {
			this.num = num;
			this.move = move;
		}
	}
}