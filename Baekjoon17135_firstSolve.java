package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 1. N × M
// 2. 칸에 적의 수는 최대 하나
// 3. (N+1번 행)의 모든 칸에는 성
// 4. 궁수 3명, 하나의 칸 1명 궁수
// 5. 모든 궁수는 동시에 공격, D이하, 가장 왼쪽
// 6. 같은 적이 여러 궁수에게 공격당할 수 있음
// 7. 적이 이동, 아래로 한 칸
// 8. 성이 있는 칸으로 이동한 경우에는 게임에서 제외
// 9. 모든 적이 격자판에서 제외 게임 종료 -> 종료조건
// 10. D는 |r1-r2| + |c1-c2|

// Input
// 행 N, 열 M 사거리 D
// 0101010...

// OUTPUT 제거할 수 있는 적의 최대 수

public class Baekjoon17135_firstSolve {
	
	// posY : 현재 Y라인, eCnt : 남은 적의 수, maxKill : 최대 킬수
	static int N, M, D, posY, eCnt, maxKill;
	static int[] posX, posT; // posX : 궁수의 X위치, posT : NP를 이용 궁수의 위치 잡을 배열
	static boolean[][] map; // 게임 맵
	static List<Enemy> origin; // 적 리스트 원본

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		D = Integer.parseInt(st.nextToken()); 
		
		posX = new int[3];
		posT = new int[M];
		map = new boolean[N][M];
		origin = new LinkedList<>();
		
		// 적 위치 초기화
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				if(st.nextToken().equals("1")) {
					map[y][x] = true; // 적 위치 입력
					origin.add(new Enemy(y, x)); // 적 생성 넣기
				}
			}
		}
		
		eCnt = origin.size(); // 적의 수 체크
		
		// Next Permitation을 위한 궁수 위치 초기화
		for (int i = M - 1; i >= M - 3; i--) {
			posT[i] = 1;
		}
		
		do {
			posY = N - 1; // 궁수 Y위치 초기화
			posInit(); // 궁수 X위치 담기
			playGame(); // 게임 시작
			reviveEnemy(); // 적 되살리기
		}while(np());
		
		System.out.print(maxKill);
		br.close();
	}
	
	private static void posInit() {
		int idx = 0;
		for (int i = 0; i < M; i++) {
			// 궁수 위치 잡아주기
			if(posT[i] == 1) {
				posX[idx] = i;
				idx++;
			}
		}
	}

	// 게임 시작
	private static void playGame() {
		int kill = 0;
		List<Enemy> eList = new LinkedList<>(origin);
		
		// 적이 더 이상 없을 때 까지
		while( !eList.isEmpty() ) {
			Enemy[] target = new Enemy[3];
			
			selectTarget(target, eList);  // 궁수 공격대상 선정
			
			kill += attack(target, eList); // 공격
			
			moveCheck(eList); // 맵이동 & 범위 벗어난 적 죽음 처리
		}
		
		// 최대 죽인 적 수 갱신
		maxKill = Math.max(maxKill, kill); 
	}
	
	private static void selectTarget(Enemy[] target, List<Enemy> eList) {
		Enemy e; // 적 임시변수
		
		// 각 궁수마다 타켓 지정
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < eList.size(); j++) {
				e = eList.get(j);
				
				// 적과 궁수의 거리 계산 (궁수는 한칸 아래 있음)
				e.d = Math.abs(e.y - (posY + 1)) + Math.abs(e.x - posX[i]);
			}
			
			// 우선 순위에 따라 정렬
			Collections.sort(eList);
			
			// 사정거리가 된다면 공격 대상 지정
			e = eList.get(0);
			if(e.d <= D) target[i] = e;
		}
	}
	
	private static int attack(Enemy[] target, List<Enemy> eList) {
		int kill = 0;
		Enemy e; // 적 임시 변수
		
		// 궁수들 공격
		for (int i = 0; i < 3; i++) {
			e = target[i];
			// 공격할 적이 있고 그 적이 죽지 않았으면 (궁수가 같은적 공격 가능)
			if (e != null && !e.isDead) {
				e.isDead = true; // 적 죽은 것으로 처리
				kill++; // 죽인 적의 수 증가
			}
		}
		
		// 같은 적 공격 가능하기 때문에 궁수 공격 다 하고 queue에서 제거
		for (int i = 0; i < 3; i++) {
			e = target[i];
			if(e != null) eList.remove(target[i]);
		}
		
		return kill;
	}
	
	private static void moveCheck(List<Enemy> eList) {
		posY--; // 적들의 이동 == 궁수 위치 이동
		
		// 뒤에서 부터 돌아야지 정상적인 삭제 가능
		for (int i = eList.size() - 1; i > -1 ; i--) {
			if(eList.get(i).y > posY) { // 범위를 벗어나면
				eList.remove(i);
			}
		}
	}
	
	// 새로운 조합 하기전 적 되살리기
	private static void reviveEnemy() {
		for (int i = 0; i < eCnt; i++) {
			origin.get(i).isDead = false; // 적 되살리기
		}
	}

	// Next Permitation 
	private static boolean np() {
		
		// 꼭대기에서 떨어지는 지점 찾기 => i - 1
		int i = M - 1;
		while(i > 0 && posT[i - 1] >= posT[i]) i--;
		
		if(i == 0) return false;
		
		// 떨어지는 지점 보다 큰 첫번째 지점 찾아서 swap
		int j = M - 1;
		while(posT[i - 1] >= posT[j]) j--;
		swap(i - 1, j);
		
		// j 뒷라인 정렬
		int k = M - 1;
		while(i < k) {
			swap(i++, k--);
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = posT[i];
		posT[i] = posT[j];
		posT[j] = temp;		
	}
	
	static class Enemy implements Comparable<Enemy> {
		int y;
		int x;
		int d;
		boolean isDead;
		
		Enemy(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		@Override
		public int compareTo(Enemy other) {
			// 궁수와의 거리가 같다면 왼쪽에 있는 적
			if(this.d != other.d) return this.d - other.d;
			return this.x - other.x;
		}
		
		// 같은지 다른지 판단하기 위한 hashcode와 equals
		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Enemy) {
				Enemy other = (Enemy) obj;
				// 좌표가 같으면 같은 적
				if(this.y == other.y && this.x == other.x) return true;
			}
			return false;
		}
		
		// 디버깅용
		@Override
		public String toString() {
			return "Enemy [y=" + y + ", x=" + x + ", d=" + d + ", isDead=" + isDead + "]";
		}
	}
	
}