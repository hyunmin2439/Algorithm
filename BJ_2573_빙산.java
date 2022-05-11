import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Memory:111,616KB / Time:596ms
 */

public class BJ_2573_빙산 {

	private static final int SEA = 0;
	private static int cnt, N, M, arr[][];
	
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		List<Node> list = input();
		
		int year = 0;
		
		while(cnt <= 1 && list.size() > 0) {
			// 1. 얼음 녹는 것
			melt(list);
			
			year++;
			
			// 2. 얼음 두덩어리 이상 분리 됐는지 확인
			cnt = 0;
			boolean[][] visited = new boolean[N][M];
			list.forEach( node -> {
				if( !visited[node.y][node.x] ) {
					bfs(visited, node);
					cnt++;
				}
			});
		}
		
		System.out.print(list.size() == 0 ? 0 : year);
	}
	
	private static List<Node> input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
				
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		List<Node> list = new LinkedList<>();
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
				
				if(arr[y][x] > 0)
					list.add(new Node(y, x));
			}
		}
		
		in.close();
		
		return list;
	}
	
	private static void melt(List<Node> list) {
		List<DelNode> meltingList = new ArrayList<>();
		
		int cnt = 0;
		for(Iterator<Node> iter = list.iterator(); iter.hasNext(); ) {
			Node node = iter.next();
			
			cnt = getNumOf4WaySea(node);
			
			if( cnt > 0 ) {
				cnt = arr[node.y][node.x] - cnt;
				
				if(cnt <= 0) {
					iter.remove();
					cnt = 0;
				}
				
				meltingList.add(new DelNode(node.y, node.x, cnt));
			}
		}
		
		meltingList.forEach( node -> arr[node.y][node.x] = node.cnt );
	}
	
	private static int getNumOf4WaySea(Node node) {
		int cnt = 0;
		
		int ny, nx;
		for(int d = 0; d < 4; d++) {
			ny = node.y + dy[d];
			nx = node.x + dx[d];
			if( isOutArr(ny, nx) || arr[ny][nx] != SEA ) continue;
			cnt++;
		}
		
		return cnt;
	}
	
	private static boolean isOutArr(int y, int x) {
		return !(0 <= y && y < N && 0 <= x && x < M);
	}
	
	private static void bfs(boolean[][] visited, Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		visited[node.y][node.x] = true;
		
		int ny, nx;
		while( !queue.isEmpty() ) {
			node = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				ny = node.y + dy[d];
				nx = node.x + dx[d];
				
				if( isOutArr(ny, nx) || visited[ny][nx] || arr[ny][nx] == SEA ) continue;
				
				queue.offer(new Node(ny, nx));
				visited[ny][nx] = true;
			}
		}
	}
	
	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class DelNode extends Node{
		int cnt;
		
		public DelNode(int y, int x, int cnt) {
			super(y, x);
			this.cnt = cnt < 0 ? 0 : cnt;
		}
	}
}
