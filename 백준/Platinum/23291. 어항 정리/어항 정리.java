import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), 
			K = Integer.parseInt(st.nextToken());
		int[][] bowl = new int[N + 1][N + 1];
		int[] pos;
		int cnt = 0, answer = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			bowl[0][i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			// 1. 물고기 수가 가장 적은 어항에 물고기 한마리를 넣는다. (여러곳이면 모두)
			first(bowl, N);
			
			// 2 - 1.가장 왼쪽에 있는 어항을 오른쪽에 있는 어항 위에 올린다.
			bowl[1][1] = bowl[0][0];
			bowl[0][0] = 0;
			
			// 2 - 2. 2개 이상 쌓여있는 어항을 모두 공중 부양 시킨 다음 전체를 시계 방향 90도 회전
			//    공중 부양 시킨 어항을 바닥에 있는 어항의 위에 올려 놓는다.
			//    공중 부양 시킨 어항 중 가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을 때 까지 반복
			pos = second(bowl, N);
			
			// 3. 공중 부양이 끝나면 어항의 있는 물고기 수를 조절
			//    모든 인접한 두 어항에 대해 물고기 수의 차이 구한다.
			//    이 차이를 5로 나눈 몫을 d, d가 0보다 크면 
			//    두 어항 중 물고기 수가 많은 곳에 있는 물고기 d 마리를 적은 곳에 보낸다.
			//    이 과정은 모든 인접한 칸에 대해서 동시에 발생
			third(bowl, pos, N);
			
			// 4. 어항을 바닥에 일렬로 놓기, 가장왼쪽, 아래에 있는 어항부터 위에 있는 어항까지 순서대로
			fourth(bowl, pos, N);
			
			// 5. N/2를 공중 부양 시계 방향으로 180도 회전 어항 위에 두기 -> 두번 반복
			pos = fifth(bowl, N);
			
			// 6. 물고기 수 조절 작업을 다시 수행 후 일렬로 놓기
			third(bowl, pos, N);
			fourth(bowl, pos, N);
			
			cnt = minMaxDiff(bowl, N);
			
			answer++;
			
			if(cnt <= K) break;
		}
		
		System.out.println(answer);
	}

	private static void first(int[][] bowl, int N) {
		List<Integer> list = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			if(min > bowl[0][i]) {
				min = bowl[0][i];
				list.clear();
				list.add(i);
			}
			else if(min == bowl[0][i])
				list.add(i);
		}
		
		list.forEach( i -> bowl[0][i] += 1 );
	}
	
	private static int[] second(int[][] bowl, int N) {
		int currX = 1, yLen = 2, xLen = 1, sy, sx, ey, ex;
		
		// while
		while(currX + xLen + yLen - 1 < N) {	
			sy = 1;
			ey = xLen;
			sx = currX + xLen;
			ex = currX + xLen + yLen - 1;
			
			for(int y = sy; y <= ey; y++) {
				for(int x = sx; x <= ex; x++) {
					bowl[y][x] = bowl[x - (currX + xLen)][currX + xLen - y];
					bowl[x - (currX + xLen)][currX + xLen - y] = 0;
				}
			}
			
			currX += xLen;
			if(yLen == xLen) yLen++;
			else xLen++;
		}
		
		return new int[] { currX, yLen, xLen }; // 다음 물고기 수 조절 때 사용
	}

	
	private static void third(int[][] bowl, int[] pos, int N) {
		int sx = pos[0], ey = pos[1], ny, nx, rd, mok;
		boolean[][][] visited = new boolean[ey][N - sx][4];
		int[][] moveFishs = new int[ey][N - sx];
		
		for(int y = 0; y < ey; y++) {
			for(int x = sx; x < N; x++) {
				if(bowl[y][x] == 0) continue;
				
				for(int d = 0; d < 4; d++) {
					ny = y + dy[d];
					nx = x + dx[d];
					rd = (d + 2) % 4;
					
					if( !(0 <= ny && ny < ey && sx <= nx && nx < N) ) continue;
					
					// [y][x] -> [ny][nx] 비교했는지
					// [y][x]에서는 d 방향 [ny][nx]는 rd 반대방향 체크
					if( visited[ny][nx - sx][rd] || bowl[ny][nx] == 0 ) continue;
					
					mok = Math.abs(bowl[y][x] - bowl[ny][nx]) / 5;
					
					if(mok == 0) continue;
					
					if(bowl[y][x] > bowl[ny][nx]) {
						moveFishs[y][x - sx] -= mok;
						moveFishs[ny][nx - sx] += mok;
					}
					else {
						moveFishs[y][x - sx] += mok;
						moveFishs[ny][nx - sx] -= mok;
					}
					
					visited[y][x - sx][d] = visited[ny][nx - sx][rd] = true;
				}
			}
		}
		
		for(int y = 0; y < ey; y++) {
			for(int x = sx; x < N; x++) {
				bowl[y][x] += moveFishs[y][x - sx];
			}
		}
		
	}
	
	private static void fourth(int[][] bowl, int[] pos, int N) {
		int num = 0, sx = pos[0], ey = pos[1], ex = sx + pos[2];
		
		for(int x = sx; x < ex; x++) {
			for(int y = 0; y < ey; y++) {
				// 값을 바꾸고 이후 0으로 
				bowl[0][num++] = bowl[y][x];
				bowl[y][x] = 0;
			}
		}
	}
	
	private static int[] fifth(int[][] bowl, int N) {
		int sx = 0, xLen = N / 2, nyLen = 2;
		
		for(int i = 1; i <= 2; i++) {
			for(int y = 0; y < i; y++) {
				for(int x = 0; x < xLen; x++) {
					bowl[nyLen - y - 1][N - x - 1] = bowl[y][sx + x];
					bowl[y][sx + x] = 0;
				}
			}
			
			if(i == 1) {
				sx = N / 2;
				xLen /= 2;
				nyLen *= 2;
			}
		}
		
		return new int[] { sx + xLen, nyLen, xLen }; // 다음 물고기 수 조절 때 사용
	}
	
	private static int minMaxDiff(int[][] bowl, int N) {
		int min = bowl[0][0], max = bowl[0][0];
		
		for(int x = 1; x < N; x++) {
			min = Math.min(min, bowl[0][x]);
			max = Math.max(max, bowl[0][x]);
		}
		
		return max - min;
	}
}