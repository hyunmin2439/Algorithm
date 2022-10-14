import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dy = { 0, -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Shark> list = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			k = Integer.parseInt(st.nextToken()),
			sharkPos[][] = new int[N + 1][N + 1],
			smell[][][] = new int[N + 1][N + 1][2];
		
		list.add(new Shark(0, 0, 0)); // dummy
		
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= N; x++) {
				sharkPos[y][x] = Integer.parseInt(st.nextToken());
				if(sharkPos[y][x] > 0) {
					list.add(new Shark(y, x, sharkPos[y][x]));
				}
			}
		}
		
		Collections.sort(list);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= M; i++) {
			list.get(i).setDirection(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i <= M; i++) {
			for(int d = 1; d <= 4; d++) {
				list.get(i).setMovePriority(new StringTokenizer(br.readLine()), d);
			}
		}
		
		System.out.print( simulation(list, smell, sharkPos, N, k) );
	}
	
	private static int simulation(List<Shark> list, int smell[][][], int sharkPos[][], int N, int k) {
		
		int sec = 0;
		
		// 초기 위치에 냄새뿌리기
		spreadSmell(list, smell, k);

		
		// 최대 1000초까지 시뮬레이션
		while(list.size() > 2 && ++sec <= 1000) {
			moveShark(list, smell, sharkPos, N, k);
			decreaseSmell(smell, N);
			spreadSmell(list, smell, k);
		}
		
		return sec > 1000 ? -1 : sec;
	}
	
	// 상어 이동 및 냄새 뿌리는 메서드
	private static void moveShark(List<Shark> list, int smell[][][], int sharkPos[][], int N, int k) {
		int ny, nx, selIdx = 0, emptyIdx = 0, smellIdx = 0;
		Iterator<Shark> iter = list.iterator();
		iter.next(); // 0번 dummy 상어 제외
		Shark shark;
		
		while(iter.hasNext()) {
			shark = iter.next();
			selIdx = 0;
			emptyIdx = 0; 
			smellIdx = 0;
			
			// shark.mp[바라보는 방향][1 ~ 4]
			// 바라보고 있는 방향의 우선순위에 순서로 냄새 없는 칸과 자신의 냄새 있는 칸 방향을 기록
			for(int i = 1; i <= 4; i++) {
				ny = shark.y + dy[ shark.mp[shark.d][i] ];
				nx = shark.x + dx[ shark.mp[shark.d][i] ];
				
				if( !(1 <= ny && ny <= N && 1 <= nx && nx <= N) ) continue;
				
				if( emptyIdx == 0 && smell[ny][nx][0] == 0 ) emptyIdx = i;
				
				if( smellIdx == 0 && smell[ny][nx][0] == shark.num ) smellIdx = i;
			}
			
			selIdx = emptyIdx != 0 ? emptyIdx : smellIdx;
			
			// 현재 위치가 자신의 번호가 맞으면 지움
			// 다른 상어가 자신의 위치에 와있을 수도 있기 때문에
			if( sharkPos[shark.y][shark.x] == shark.num )
				sharkPos[shark.y][shark.x] = 0;
			
			// 위치 이동
			shark.y += dy[ shark.mp[shark.d][selIdx] ];
			shark.x += dx[ shark.mp[shark.d][selIdx] ];
			shark.d = shark.mp[shark.d][selIdx]; // 바라보는 방향 변경
			
			// 번호 순으로 정렬되어 있기 때문에 순서대로 이동
			// 자신보다 작은 상어는 이미 이동을 마쳤으니 상어 위치가 겹친 것
			// 따라서 현재 상어를 지우면 된다.
			if( sharkPos[shark.y][shark.x] != 0 && sharkPos[shark.y][shark.x] < shark.num ) {
				iter.remove();
				continue;
			}
			
			// 이동 위치 자신보다 번호가 크면 아직 이동 안한 것
			// 따라서 자신의 번호로 덮어씌워도 상관 없다.
			sharkPos[shark.y][shark.x] = shark.num;
		}
	}
	
	// 냄새 격자 전체 시간초-- 하는 메서드
	private static void decreaseSmell(int smell[][][], int N) {
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= N; x++) {
				if(smell[y][x][1] > 0 && --smell[y][x][1] == 0) {
					smell[y][x][0] = 0;
				}
			}
		}
	}
	
	// 상어 전체가 이동을 마치고 난 후에 냄새를 뿌려야 한다.
	// 상어 한마리가 이동 후 바로 냄새를 뿌리면 그 다음 번호의 상어의 이동에 영향을 준다.
	private static void spreadSmell(List<Shark> list, int smell[][][], int k) {
		Shark shark;
		
		for(int i = 1; i < list.size(); i++) {
			shark = list.get(i);
			smell[shark.y][shark.x][0] = shark.num;
			smell[shark.y][shark.x][1] = k;
		}
	}
}

class Shark implements Comparable<Shark> {
	int y, x, d, num;
	int[][] mp; // 이동 우선 순위
	
	public Shark() {}
	
	public Shark(int y, int x, int num) {
		this.y = y;
		this.x = x;
		this.num = num;
		mp = new int[5][5];
	}
	
	public void setDirection(int d) {
		this.d = d;
	}
	
	public void setMovePriority(StringTokenizer st, int d) {
		for(int i = 1; i < mp[d].length; i++) {
			mp[d][i] = Integer.parseInt(st.nextToken());
		}
	}
	
	@Override
	public int compareTo(Shark other) {
		return this.num - other.num;
	}
}