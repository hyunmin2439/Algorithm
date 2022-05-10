import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Memory:18,512KB / Time:228ms
 */
public class BJ_2638_치즈 {

	private static final int AIR = 0, CHEESE = 1, OUTAIR = 2;
	private static int N, M, paper[][];
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		List<Node> cheese = input();
		 
		/**
		 *  모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정
		 *  따라서 (0,0)에는 치즈가 없으므로 외부공기
		 */
		
		int time = 0;
		// 처음 외부공기로 bfs init
		setOutAir(new boolean[N][M], new Node(0, 0));
		while(cheese.size() > 0) {
			List<Node> deleteList = deleteCheese(cheese);
			
			// 치즈 외부 공기로 바꾸면서 bfs
			boolean[][] visited = new boolean[N][M];
			deleteList.forEach( pos -> setOutAir(visited, pos) );
			
			time++;
		}
		
		System.out.print(time);
	}
	
	private static List<Node> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		List<Node> cheese = new LinkedList<>();
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				paper[y][x] = Integer.parseInt(st.nextToken());
				
				if( paper[y][x] == CHEESE)
					cheese.add(new Node(y, x));
			}
		}
		
		in.close();
		
		return cheese;
	}
	
	private static void setOutAir(boolean[][] visited, Node pos) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(pos);
		visited[pos.y][pos.x] = true;
		paper[pos.y][pos.x] = OUTAIR;
		
		int ny, nx;
		while( !queue.isEmpty() ) {
			pos = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				ny = pos.y + dy[d];
				nx = pos.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ||
						visited[ny][nx] || paper[ny][nx] == CHEESE || paper[ny][nx] == OUTAIR)
					continue;
				
				queue.offer(new Node(ny, nx));
				visited[ny][nx] = true;
				paper[ny][nx] = OUTAIR;
			}
		}
	}
	
	private static List<Node> deleteCheese(List<Node> cheese) {
		List<Node> deleteList = new ArrayList<>();
		
		int ny, nx, cnt = 0;
		for (Iterator<Node> iterator = cheese.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			
			cnt = 0;
			for(int d = 0; d < 4; d++) {
				ny = node.y + dy[d];
				nx = node.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ||
						paper[ny][nx] == AIR || paper[ny][nx] == CHEESE )
					continue;
				
				cnt++;
			}
			
			
			if(cnt > 1)	{
				deleteList.add( node );
				iterator.remove();			
			}
		}
		
		return deleteList;
	}
	

	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
