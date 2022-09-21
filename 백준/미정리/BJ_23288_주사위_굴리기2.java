import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


// Memory:14,532KB / Time:136ms
public class BJ_23288_주사위_굴리기2 {
	
	private static int N, M, K;
	
	private static final int EAST = 0, SOUTH = 1, WEST = 2, NORTH = 3, UP = 4, DOWN = 5;
	
	private static int[] dy = { 0, 1, 0, -1 };
	private static int[] dx = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] score = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if( ! visited[y][x] )
					bfs(score, map, visited, new Node(y, x));
			}
		}
		
		int totScore = simulation(score, map, K);
		System.out.println(totScore);

		in.close();
		
	}
	
	private static int simulation(int[][] score, int[][] map, int K) {
		int ny, nx, totScore = 0;
		Dice dice = new Dice(0, 0, EAST);
		
		while(K-- > 0) {
			ny = dice.y + dy[dice.dir];
			nx = dice.x + dx[dice.dir];
			
			// 맵 밖으로 나갈경우 방향 전환
			if( !(0 <= ny && ny < N && 0 <= nx && nx < M) ) {
				switch(dice.dir) {
				case EAST: dice.dir = WEST; break;
				case WEST: dice.dir = EAST; break;
				case SOUTH: dice.dir = NORTH; break;
				case NORTH: dice.dir = SOUTH; break;
				}
				
				ny = dice.y + dy[dice.dir];
				nx = dice.x + dx[dice.dir];
			};
			
			dice.y = ny;
			dice.x = nx;
			dice.move();
			totScore += score[ny][nx];
			
			if(dice.num[DOWN] < map[ny][nx])
				dice.rotate(-1); // 반시계
			else if(dice.num[DOWN] > map[ny][nx])
				dice.rotate(1); // 시계
		}
		
		return totScore;
	}
	
	private static void bfs(int[][] score, int[][] map, boolean[][] visited, Node start) {
		Queue<Node> queue = new LinkedList<>();
		List<Node> list = new ArrayList<>();
		visited[start.y][start.x] = true;
		queue.offer(start);
		list.add(start);
		
		Node curr, addNode;
		int ny, nx, cnt = 1;
		
		while( ! queue.isEmpty() ) {
			curr = queue.poll();
			
			for(int d = 0; d < dy.length; d++) {
				ny = curr.y + dy[d];
				nx = curr.x + dx[d];
				
				if( !(0 <= ny && ny < N && 0 <= nx && nx < M) || 
					visited[ny][nx] || map[ny][nx] != map[start.y][start.x] ) continue;
				
				visited[ny][nx] = true;
				addNode = new Node(ny, nx);
				queue.offer(addNode);
				list.add(addNode);
				cnt++;
			}
		}
		
		// 최종 점수 갱신
		final int finalScore =  cnt * map[start.y][start.x];
		
		list.forEach( node -> {
			score[node.y][node.x] = finalScore;
		});
	}

	static class Node {
		int y, x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class Dice extends Node {
		int dir, num[];
		
		public Dice(int y, int x, int dir) {
			super(y, x);
			this.dir = dir;
			num = new int[6];
			num[UP] = 1;
			num[NORTH] = 2;
			num[EAST] = 3;
			num[SOUTH] = 5;
			num[WEST] = 4;
			num[DOWN] = 6;
		}
		
		private void rotate(int dir) {
			switch(dir) {
			case -1: 
				this.dir--; 
				if(this.dir < 0) this.dir = NORTH;
				break; // 반시계;
			case 1:
				this.dir++;
				if(this.dir > 3) this.dir = EAST;
				break;
			}
			
		}
		
		private void move() {
			int tmp;
			
			switch(this.dir) {
			case EAST:
				tmp = num[DOWN];
				num[DOWN] = num[EAST];
				num[EAST] = num[UP];
				num[UP] = num[WEST];
				num[WEST] = tmp;
				break;
			case WEST: 
				tmp = num[DOWN];
				num[DOWN] = num[WEST];
				num[WEST] = num[UP];
				num[UP] = num[EAST];
				num[EAST] = tmp;
				break;
			case NORTH:
				tmp = num[NORTH];
				num[NORTH] = num[UP];
				num[UP] = num[SOUTH];
				num[SOUTH] = num[DOWN];
				num[DOWN] = tmp;
				break;
			case SOUTH: 
				tmp = num[DOWN];
				num[DOWN] = num[SOUTH];
				num[SOUTH] = num[UP];
				num[UP] = num[NORTH];
				num[NORTH] = tmp;
				break;
			}
		}
	}
}
