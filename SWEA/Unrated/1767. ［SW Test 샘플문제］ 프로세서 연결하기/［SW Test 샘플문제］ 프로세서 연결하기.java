import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static int[] dy = { -1, 0, 1, 0 },
						 dx = { 0, -1, 0, 1 };
	
	private static int N, coreCnt, 
					   maxConnCnt, minCnt, 
					   pos[][];
	
	private static boolean[][] isFilled;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(100);
		StringTokenizer st;
		int t = 0, T;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			N = Integer.parseInt(in.readLine());
			isFilled = new boolean[N][N];
			pos = new int[12][2];
			coreCnt = maxConnCnt = 0;
			minCnt = Integer.MAX_VALUE;
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(in.readLine());
				for(int x = 0; x < N; x++) {
					if("1".equals(st.nextToken())) {
						isFilled[y][x] = true;
						
						if(y == 0 || x == 0) continue;
						pos[coreCnt][0] = y;
						pos[coreCnt][1] = x;
						coreCnt++;
					}
				}
			}

			dfs(0, 0, 0);
			sb.append("#").append(t).append(" ").append(minCnt).append("\n");
		}
		out.write(sb.toString());
		out.flush();
	}
	
	private static void dfs(int connCnt, int idx, int cnt) {
		// 최대 연결된 Core개수 > 전체 Core 개수 - 현재idx + 현재 연결한 코어 개수
		// 즉, 앞으로 연결 가능한 모든 코어를 연결해도 이전 시뮬레이션들 한 결과 최대 연결된 Core을 넘지 못하면
		// ex) 앞선 시뮬레이션 연결된 코어 3 > 전체 코어 개수 5 - idx 3 + 현재 연결한 코어 개수 0 => return
		if(maxConnCnt > coreCnt - idx + connCnt) return;
		
		if(idx == coreCnt) {
			if(maxConnCnt < connCnt) {
				maxConnCnt = connCnt;				
				minCnt = cnt;
			}
			else if(maxConnCnt == connCnt)
				minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		for(int d = 0; d < dy.length; d++) {
			if( check(pos[idx][0], pos[idx][1], d) ) {
				dfs(connCnt + 1, idx + 1, cnt + toggle(pos[idx][0], pos[idx][1], d));
				toggle(pos[idx][0], pos[idx][1], d);
			}
			else
				dfs(connCnt, idx + 1, cnt);
		}
	}
	
	// 연결 가능한 방향인지 체크
	private static boolean check(int y, int x, int d) {
		while(true) {
			y += dy[d];
			x += dx[d];
			if( isOut(y, x) ) return true;
			if(isFilled[y][x]) return false;
		}
	}
	
	// 전선을 연결하고 해제 하는 메서드
	private static int toggle(int y, int x, int d) {
		int cnt = 0;
		
		while(true) {
			y += dy[d];
			x += dx[d];
			if( isOut(y, x) ) break;
			isFilled[y][x] = !isFilled[y][x];
			cnt++;
		}
		
		return cnt;
	}
	
	// 범위 밖으로 나갔는지 체크
	private static boolean isOut(int y, int x) {
		return !(0 <= y && y < N && 0 <= x && x < N);
	}
}