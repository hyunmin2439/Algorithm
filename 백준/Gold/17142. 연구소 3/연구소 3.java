import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	
	private static final int EMPTY = 0,
							  WALL = 1;
	
	private static int minSec,
					   emptyCnt, // 빈칸의 개수
					   dy[] = { -1, 0, 1, 0 },
					   dx[] = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Virus> list = new ArrayList<>();
		Queue<Virus> queue = new LinkedList<>();
		
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			map[][] = new int[N][N];
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				switch(map[y][x]) {
					case 0: emptyCnt++; break;
					case 2: list.add(new Virus(y, x)); break;
				}	
			}
		}
		
		minSec = Integer.MAX_VALUE;
		comb(list, queue, new boolean[N][N], map, new int[M], N, M, 0, 0);
		
		System.out.print(minSec == Integer.MAX_VALUE ? -1 : minSec);
	}

	private static void comb(List<Virus> list, Queue<Virus> queue, boolean[][] visited, int[][] map, int[] select, int N, int M, int startIdx, int cnt) {
		if(cnt == M) {
			queue.clear();
			
			for(int i = 0; i < select.length; i++)
				queue.offer(list.get(select[i]));
			
			minSec = Math.min(minSec, bfs(queue, map, visited, N, emptyCnt));
			
			initVisited(visited, N);
			return;
		}
		
		for(int i = startIdx; i < list.size(); i++) {
			select[cnt] = i;
			comb(list, queue, visited, map, select, N, M, i + 1, cnt + 1);
		}
	}
	
	private static int bfs(Queue<Virus> queue, int[][] map, boolean[][] visited, int N, int emptyCnt) {
		Virus virus;
		int ny = 0, nx = 0, sec = 0, size = queue.size();
		
		// 초기 바이러스들 방문 체크
		for(int i = 0; i < size; i++) {
			virus = queue.poll();
			visited[virus.y][virus.x] = true;
			queue.offer(virus);
		}
		
		while( !queue.isEmpty() ) {
			if(emptyCnt == 0) return sec;
			size = queue.size();
			sec++;
			
			for(int i = 0; i < size; i++) {
				virus = queue.poll();
				
				for(int d = 0; d < dy.length; d++) {
					ny = virus.y + dy[d];
					nx = virus.x + dx[d];
					
					if( !(0 <= ny && ny < N && 0 <= nx && nx < N) || visited[ny][nx] || map[ny][nx] == WALL ) continue;
					
					if(map[ny][nx] == EMPTY) emptyCnt--;
					
					queue.offer(new Virus(ny, nx));
					visited[ny][nx] = true;
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	private static void initVisited(boolean[][] visited, int N) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				visited[y][x] = false;
			}
		}
	}
}

class Virus {
	int y, x;
	
	public Virus(int y, int x) {
		this.y = y;
		this.x = x;
	}
}