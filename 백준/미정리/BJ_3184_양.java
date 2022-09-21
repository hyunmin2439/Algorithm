import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3184_양 {

	private static int R, C;
	
	private static final char SHEEP = 'o', WOLF = 'v', FENCE = '#';
	private static final int[] dy = { -1, 1, 0, 0 };
	private static final int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		char[][] map = input();
		
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		List<Node> sheep = new ArrayList<>();
		List<Node> wolf = new ArrayList<>();
		
		int tmp[], sheepCnt = 0, wolfCnt = 0;
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if( !visited[y][x] ) {
					tmp = bfs(map, queue, visited, sheep, wolf, new Node(y, x));
					sheepCnt += tmp[0];
					wolfCnt += tmp[1];
				}
				
			}
		}
		
		System.out.print(sheepCnt + " " + wolfCnt);
	}
	
	private static int[] bfs(char[][] map, Queue<Node> queue, boolean[][] visited, List<Node> sheep, List<Node> wolf, Node curr) {
		sheep.clear();
		wolf.clear();
		
		queue.offer(curr);
		visited[curr.y][curr.x] = true;
		if(map[curr.y][curr.x] == SHEEP)
			sheep.add(curr);
		else if(map[curr.y][curr.x] == WOLF)
			wolf.add(curr);
		
		int ny, nx;
		Node node = null;
		while( !queue.isEmpty() ) {
			curr = queue.poll();
			
			for(int d = 0; d < dy.length; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < R && 0 <= nx && nx < C) ) continue;
				
				if( visited[ny][nx] || map[ny][nx] == FENCE ) continue;

				visited[ny][nx] = true;
				node = new Node(ny, nx);
				queue.offer(node);
				
				if(map[ny][nx] == SHEEP)
					sheep.add(node);
				else if(map[ny][nx] == WOLF)
					wolf.add(node);
			}
		}
		
		if(sheep.size() > wolf.size())
			wolf.clear();
		else
			sheep.clear();
		
		return new int[]{ sheep.size(), wolf.size() };
	}
	
	private static char[][] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][];
		
		for(int y = 0; y < R; y++) {
			map[y] = in.readLine().toCharArray();
		}
		
		return map;
	}

	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
