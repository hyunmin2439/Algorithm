import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	//							 ↑   ↖   ←   ↙  ↓  ↘  →   ↗  
	private static int[] dy = { -1, -1,  0,  1, 1, 1, 0, -1 };
	private static int[] dx = {  0, -1, -1, -1, 0, 1, 1,  1 };
	
	// 										←  ↖  ↑  ↗  →  ↘  ↓  ↙
	// 										1  2  3  4  5  6  7  8
	private static int[] changeDirec = { 0, 2, 1, 0, 7, 6, 5, 4, 3 };
	
	private static int maxCnt, maxMoved[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()),
			S = Integer.parseInt(st.nextToken()),
			N = 4, D = 8, y, x, d,
			smell[][] = new int[N][N],
			fishs[][][] = new int[N][N][D], // 각 위치마다 8방향
			tmp[][][] = new int[N][N][D],
			copyFishs[][][] = new int[N][N][D];
		Shark shark;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			d = changeDirec[ Integer.parseInt(st.nextToken()) ]; // 문제 풀이의 편의를 위해 방향값 조정
			
			fishs[y][x][d]++;
		}
		
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken()) - 1;
		x = Integer.parseInt(st.nextToken()) - 1;
		shark = new Shark(y, x);
		
		while(S-- > 0) {
			// 1. 미리 복제
			copyFishs(fishs, copyFishs, N, D);
			// 2. 물고기들 이동
			moveFishs(fishs, tmp, shark, smell, N, D);
			
			// 3. 상어 이동 경로 정하기
			maxCnt = -1; // 물고기를 한마리도 못먹을 경우가 있을 수도 있다.
			maxMoved = new int[3];
			moveShark(fishs, new int[3], N, D, shark.y, shark.x, 0, 0);
			// 정한 이동 경로대로 물고기 지우고 냄새 남기기
			afterSharkMoved(fishs, smell, maxMoved, shark, N, D);
			
			// 4. 냄새 감소
			decreaseSmell(smell, N);
			
			// 5. 물고기 복제본 물고기 리스트로 옮기기
			addCopyFishs(fishs, copyFishs, N, D);
		}

		System.out.println( countFishs(fishs, N, D) );
	}
	
	private static void copyFishs(int[][][] fishs, int[][][] copyFishs, int N, int D) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				for(int d = 0; d < D; d++) {
					copyFishs[y][x][d] = fishs[y][x][d];
				}
			}
		}
	}
	
	private static void moveShark(int[][][] fishs, int[] moved, int N, int D, int y, int x, int idx, int cnt) {
		if(idx == moved.length) {
			if(maxCnt < cnt) {
				maxCnt = cnt;
				for(int i = 0; i < moved.length; i++) {
					maxMoved[i] = moved[i];
				}
			}
			
			return;
		}
		
		int ny, nx, fishsCnt = 0, savedFishCnt[] = new int[D];
		for(int d = 0; d < D; d += 2) {
			ny = y + dy[d];
			nx = x + dx[d];
			
			if( !(0 <= ny && ny < N && 0 <= nx && nx < N) ) continue;
			
			moved[idx] = d;
			
			fishsCnt = 0;
			for(int d2 = 0; d2 < D; d2++) {
				fishsCnt += fishs[ny][nx][d2];
				savedFishCnt[d2] = fishs[ny][nx][d2];
				fishs[ny][nx][d2] = 0;
			}
			
			moveShark(fishs, moved, N, D, ny, nx, idx + 1, cnt + fishsCnt);
			
			for(int d2 = 0; d2 < D; d2++) {
				fishs[ny][nx][d2] = savedFishCnt[d2];
			}
		}
	}
	
	private static void afterSharkMoved(int[][][] fishs, int[][] smell, int[] moved, Shark shark, int N, int D) {
		int cnt = 0;
		for(int i = 0; i < moved.length; i++) {
			shark.y += dy[ moved[i] ];
			shark.x += dx[ moved[i] ];
			
			cnt = 0;
			
			for(int d = 0; d < D; d++) {
				cnt += fishs[shark.y][shark.x][d];
				fishs[shark.y][shark.x][d] = 0;
			}
			
			if(cnt > 0)
				smell[shark.y][shark.x] = 3;
		}
	}
	
	private static void moveFishs(int[][][] fishs, int[][][] tmp, Shark shark, int[][] smell, int N, int D) {
		int ny, nx, nd;

		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				for(int d = 0; d < D; d++) {
					if(fishs[y][x][d] == 0) continue;
					
					nd = d;
					for(int i = 0; i < 8; i++) {
						ny = y + dy[nd];
						nx = x + dx[nd];
						
						if( !(0 <= ny && ny < N && 0 <= nx && nx < N) 
								|| smell[ny][nx] > 0 || (ny == shark.y && nx == shark.x) ) {
							
							if(++nd >= D) nd = 0;
							
							if(i == 7)
								tmp[y][x][d] += fishs[y][x][d]; // 이동 못하면 제자리
							
							continue;
						}
						
						tmp[ny][nx][nd] += fishs[y][x][d];
						break;
					}
				}
			}
		}
		
		// fishs로 옮기기 및 tmp 배열 초기화
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				for(int d = 0; d < D; d++) {
					fishs[y][x][d] = tmp[y][x][d];
					tmp[y][x][d] = 0;
				}
			}
		}
	}
	
	private static void decreaseSmell(int[][] smell, int N) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(--smell[y][x] < 0) smell[y][x] = 0;
			}
		}
	}
	
	private static void addCopyFishs(int[][][] fishs, int[][][] copyFishs, int N, int D) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				for(int d = 0; d < D; d++) {
					fishs[y][x][d] += copyFishs[y][x][d];
				}
			}
		}
	}
	
	private static int countFishs(int[][][] fishs, int N, int D) {
		int cnt = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				for(int d = 0; d < D; d++) {
					cnt += fishs[y][x][d];
				}
			}
		}
		
		return cnt;
	}
}

class Shark {
	int y, x;
	
	public Shark(int y, int x) {
		this.y = y;
		this.x = x;
	}
}