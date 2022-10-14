import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static final int BLOCKED = -1;

	private static int[] dy = { -1, 0, 0, 1 };
	private static int[] dx = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			K = Integer.parseInt(st.nextToken()),
			startY, startX, destY, destX, dist;
		
		PriorityQueue<Taxi> pqueue = new PriorityQueue<>();
		List<Customer> list = new LinkedList<>();
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		int[][] map = new int[N + 1][N + 1];
		Customer customer;
		Taxi taxi;
		
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= N; x++) {
				if("1".equals(st.nextToken()))
					map[y][x] = -1;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		
		taxi = new Taxi(startY, startX, K);
		
		list.add(new Customer(0, 0, 0, 0)); // dummy
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			startY = Integer.parseInt(st.nextToken());
			startX = Integer.parseInt(st.nextToken());
			destY = Integer.parseInt(st.nextToken());
			destX = Integer.parseInt(st.nextToken());
			
			map[startY][startX] = i;
			
			list.add(new Customer(startY, startX, destY, destX));
			list.get(i).dist = getDistance(queue, visited, map, N, startY, startX, destY, destX);
		}
		
		while(M-- > 0) {
			int[] find = findCustomer(pqueue, queue, visited, map, taxi, N);
			
			// 태울 손님은 남아 있는데 길이 막혀 가지 못함
			if(find[0] == 0) {
				taxi.k = -1;
				break;
			}
			
			// 찾은 손님의 정보
			customer = list.get(find[0]);
			
			// 손님의 위치까지 이동
			taxi.k -= (find[1] + customer.dist);
			
			// 연료 없으면 불가능
			if(taxi.k < 0) {
				taxi.k = -1;
				break;
			}
			
			map[customer.startY][customer.startX] = 0;
			taxi.y = customer.destY;
			taxi.x = customer.destX;
			
			taxi.k += customer.dist * 2;
		}
		
		System.out.println(taxi.k);
	}
	
	/**
	 * 손님의 목적지까지 거리 구하는 메서드
	 */
	private static int getDistance(Queue<int[]> queue, boolean[][] visited, int[][] map, 
			int N, int startY, int startX, int destY, int destX) {
		
		if(startY == destY && startX == destX) return 0;
		
		int size = 0, dist = 1, ny, nx, pos[];
		
		queue.clear();
		queue.offer(new int[] {startY, startX});
		visited[startY][startX] = true;
		
		while( !queue.isEmpty() ) {
			size = queue.size();
			
			for(int i = 0; i < size; i++) {
				pos = queue.poll();
				
				for(int d = 0; d < dy.length; d++) {
					ny = pos[0] + dy[d];
					nx = pos[1] + dx[d];
					
					if( !(1 <= ny && ny <= N && 1 <= nx && nx <= N) 
							|| visited[ny][nx] || map[ny][nx] == BLOCKED ) continue;
					
					if(ny == destY && nx == destX) {
						resetVisited(visited, N);
						return dist;
					}
					
					queue.offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
			
			dist++;
		}
		
		resetVisited(visited, N);
		return 100_000_000; // 길이 막혀 지나가지 못한다면
	}
	
	/**
	 * 택시 위치에서 가장 가까운 손님을 찾는 메서드
	 * 거리가 같은 손님을 우선순위큐에 넣고 행, 열 우선순위로 손님을 선택
	 */
	private static int[] findCustomer(PriorityQueue<Taxi> pqueue, Queue<int[]> queue, boolean[][] visited, int[][] map, Taxi taxi, int N) {
		if(map[taxi.y][taxi.x] > 0) return new int[] {map[taxi.y][taxi.x], 0}; // 승객 번호, 거리
		
		Taxi findCustomer;
		int size = 0, dist = 1, ny, nx, pos[];
		
		pqueue.clear();
		queue.clear();
		queue.offer(new int[] {taxi.y, taxi.x});
		visited[taxi.y][taxi.x] = true;
		
		while( !queue.isEmpty() ) {
			size = queue.size();
			
			for(int i = 0; i < size; i++) {
				pos = queue.poll();
				
				for(int d = 0; d < dy.length; d++) {
					ny = pos[0] + dy[d];
					nx = pos[1] + dx[d];
					
					if( !(1 <= ny && ny <= N && 1 <= nx && nx <= N) 
							|| visited[ny][nx] || map[ny][nx] == BLOCKED ) continue;

					if(map[ny][nx] > 0) {
						pqueue.offer(new Taxi(ny, nx, dist));
					}
					
					queue.offer(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
			
			// 같은 거리상의 손님들 중 행, 열 우선 순위 높은 손님
			if( !pqueue.isEmpty() ) {
				resetVisited(visited, N);
				findCustomer = pqueue.poll();
				return new int[] {map[findCustomer.y][findCustomer.x], findCustomer.k};
			}
			
			dist++;
		}
		
		resetVisited(visited, N);
		return new int[] {0, 0};
	}
	
	private static void resetVisited(boolean[][] visited, int N) {
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= N; x++) {
				visited[y][x] = false;
			}
		}
	}
}

class Customer {
	int startY, startX, destY, destX, dist;
	
	public Customer(int startY, int startX, int destY, int destX) {
		this.startY = startY;
		this.startX = startX;
		this.destY = destY;
		this.destX = destX;
	}
}

class Taxi implements Comparable<Taxi> {
	int y, x, k;
	
	public Taxi(int y, int x, int k) {
		this.y = y;
		this.x = x;
		this.k = k;
	}

	@Override
	public int compareTo(Taxi other) {
		if(this.k != other.k)
			return this.k - other.k;
		if(this.y != other.y)
			return this.y - other.y;
		return this.x - other.x;
	}
}