import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 4시간 소요.
 * 
 * 테두리 감소할 때 간단한 방법 대신 y, x 좌표를 이동시켜 풀었다.
 * 
 * 맵 넓이가 2, 2일때 ArrayOutOfBound에러 발생
 * 
 * 간단하게 전체 탐색을 쓰는 것이 효율적일지도 모름
 * 
 * 이 에러 잡는데 40분 소요
 * 
 * 코드를 짤 때 꼼꼼하게 확인하고 짤 것
 * 
 * 또한 조금 더 속도감 있게 풀것
 * 
 *  Memory:17,944KB / Time:220ms
 */
public class BJ_23289_온풍기_안녕 {

	private static final int RIGHT = 1, LEFT = 2, UP = 3, DOWN = 4;
	
	private static int R, C, K, W;
	
	//                                           우           좌            상           하
	private static int[][] dy = { {0, 0, 0}, {-1, 0, 1}, {-1,  0,  1}, {-1, -1, -1}, { 1, 1, 1} };
	private static int[][] dx = { {0, 0, 0}, { 1, 1, 1}, {-1, -1, -1}, {-1,  0,  1}, {-1, 0, 1} };
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/input23288.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] room = new int[R + 1][C + 1];
		int[][] block = new int[R + 1][C + 1];
		
		List<Node> chkList = new ArrayList<>();
		List<Tmp> tmpList = new ArrayList<>();
		
		for(int y = 1; y <= R; y++) {
			st = new StringTokenizer(in.readLine());
			for(int x = 1; x <= C; x++) {
				room[y][x] = Integer.parseInt(st.nextToken());
				
				if(room[y][x] >= 1) {
					
					if(room[y][x] == 5)
						chkList.add(new Node(y, x));
					else
						tmpList.add(new Tmp(y, x, room[y][x]));
					
					room[y][x] = 0;
				}
			}			
		}
		
		W = Integer.parseInt(in.readLine());
		
		int y, x, wall;
		for(int i = 0; i < W; i++) {
			st = new StringTokenizer(in.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			wall = Integer.parseInt(st.nextToken()) == 0 ? 2 : 1;
			block[y][x] = block[y][x] == 0 ? wall : 3; // 3은 위쪽, 오른쪽 벽이 다 있는 것.
		}
		in.close();
		
		// 온풍기 바람위치 초기화
		for(int i = 0; i < tmpList.size(); i++) {
			bfs(block, tmpList.get(i));			
		}
		
		simulation(room, block, chkList, tmpList);
	}
	
	private static void simulation(int[][] room, int[][] block, List<Node> chkList, List<Tmp> tmpList) {
		int eatCnt = 0;
		Node node;
		Tmp tmp;
		
		while(eatCnt < 101) {
			// 1. 온풍기
			for(int i = 0; i < tmpList.size(); i++) {
				tmp = tmpList.get(i);
				
				for(int j = 0; j < tmp.list.size(); j++) {
					node = tmp.list.get(j);
					room[node.y][node.x] += node.tmp;
				}
			}
			
			// 2. 온도 조절
			room = tmpControl(block, room);
			
			// 3. 바깥쪽 칸 온도 감소
			decreaseOutLine(room);
			
			// 4. 초콜릿 먹기
			eatCnt++;
			
			if( isEnd(room, chkList) ) break;
		}
		
		System.out.println(eatCnt);
//		printRoom(room);
	}
	
	private static void bfs(int[][] block, Tmp tmp) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R + 1][C + 1];
		
		int ny = tmp.y + dy[tmp.dir][1];
		int nx = tmp.x + dx[tmp.dir][1];
		
		// 좌표를 나갔거나, 바람이 벽에 막히면
		if( isOutRoom(ny, nx) || ! isPassWind(block, tmp.y, tmp.x, tmp.dir, 1) ) return;
		
		Node curr = new Node(ny, nx, 5), addNode = null;
		tmp.list.add(curr);
		queue.offer(curr);
		visited[ny][nx] = true;
		
		while( ! queue.isEmpty() ) {
			curr = queue.poll();
			
			if(curr.tmp == 1) continue;
			
			for(int i = 0; i < dy[tmp.dir].length; i++) {
				ny = curr.y + dy[tmp.dir][i];
				nx = curr.x + dx[tmp.dir][i];
				
				// 좌표를 나갔거나, 방문했거나, 바람이 벽에 막히면
				if( isOutRoom(ny, nx) || visited[ny][nx] || ! isPassWind(block, curr.y, curr.x, tmp.dir, i) ) continue;
				
				addNode = new Node(ny, nx, curr.tmp - 1);
				tmp.list.add(addNode);
				queue.offer(addNode);
				visited[ny][nx] = true;
			}
		}
	}
	
	private static boolean isOutRoom(int y, int x) {
		return ! (1 <= y && y <= R && 1 <= x && x <= C);
	}

	private static boolean isPassWind(int[][] block, int y, int x, int dir, int pos) {
		switch(dir) {
			case RIGHT: // 우측
				switch(pos) {
					case 0: // 위
						return ! ( (y - 1 >= 1 && block[y - 1][x] % 2 == 1) || block[y][x] >= 2 );
					case 1: // 중간
						return ! (block[y][x] % 2 == 1);
					case 2: // 아래
						return ! (y + 1 <= R && block[y + 1][x] >= 1 );
				}
				break;
			case LEFT: // 좌
				switch(pos) {
				case 0: // 위
					return ! ( (y - 1 >= 1 && x - 1 >= 1 && block[y - 1][x - 1] % 2 == 1) || block[y][x] >= 2);
				case 1: // 중간
					return ! (x - 1 >= 1 && block[y][x - 1] % 2 == 1);
				case 2: // 아래
					return ! ( (y + 1 <= R && x - 1 >= 1 && block[y + 1][x - 1] % 2 == 1) || (y + 1 <= R && block[y + 1][x] >= 2) );
			}
			break;
			case UP: // 상
				switch(pos) {
				case 0: // 좌측
					return ! ( x - 1 >= 1 && block[y][x - 1] >= 1);
				case 1: // 중간
					return ! (block[y][x] >= 2);
				case 2: // 우측
					return ! (block[y][x] % 2 == 1 || (x + 1 <= C && block[y][x + 1] >= 2) );
			}
			break;
			case DOWN: // 하
				switch(pos) {
				case 0: // 좌측
					return ! ( (x - 1 >= 1 && block[y][x - 1] % 2 == 1) || (y + 1 <= R && x - 1 >= 1 && block[y + 1][x - 1] >= 2) );
				case 1: // 중간
					return ! (y + 1 <= R && block[y + 1][x] >= 2);
				case 2: // 우측
					return ! (block[y][x] % 2 == 1 || (y + 1 <= R && x + 1 <= C && block[y + 1][x + 1] >= 2) );
			}
			break;
		}

		
		return true;
	}
	
	private static int[][] tmpControl(int[][] block, int[][] room) {
		int[][] newRoom = new int[R + 1][C + 1];
		
		for(int y = 0; y <= R; y++) {
			newRoom[y] = room[y].clone();
		}
		
		int ny, nx, tmpVal;
		for(int y = 1; y <= R; y++) {
			for(int x = 1; x <= C; x++) {

				if(room[y][x] == 0) continue;
				
				for(int d = 1; d <= 4; d++) {
					
					ny = y + dy[d][1];
					nx = x + dx[d][1];
					
					if( isOutRoom(ny, nx) || 
						! isPassWind(block, y, x, d, 1) ||
						room[y][x] - room[ny][nx] < 4 ) continue;
					
					tmpVal = (room[y][x] - room[ny][nx]) / 4;
					
					newRoom[y][x] -= tmpVal;
					newRoom[ny][nx] += tmpVal;
				}
			}
		}
		
		return newRoom;
	}
	
	private static void decreaseOutLine(int[][] room) {
		for(int y = 1; y <= R; y++) {
			for(int x = 1; x <= C; x++) {
				if( y == 1 || x == 1 || y == R || x == C )
					if(room[y][x] > 0) room[y][x]--;
			}
		}
	}
	
	private static boolean isEnd(int[][] room, List<Node> chkList) {
		Node node;
		
		for(int i = 0; i < chkList.size(); i++) {
			node = chkList.get(i);
			if(room[node.y][node.x] < K) return false;
		}
		
		return true;
	}
	
	private static void printRoom(int[][] room) {
		for (int y = 1; y <= R; y++) {
			for (int x = 1; x <= C; x++) {
				System.out.printf("%2d ", room[y][x]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Node {
		int y, x, tmp;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		public Node(int y, int x, int tmp) {
			this.y = y;
			this.x = x;
			this.tmp = tmp;
		}
	}
	
	static class Tmp {
		int y, x, dir;
		List<Node> list;
		
		public Tmp(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			list = new ArrayList<>();
		}
	}
}
