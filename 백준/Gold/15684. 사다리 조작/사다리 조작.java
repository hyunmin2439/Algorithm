import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int[] dh = { 1, 1, 1 };
	private static int[] dw = { 0, 1, -1 };
	private static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<int[]> list = new ArrayList<>();
		
		int N = Integer.parseInt(st.nextToken()),
			M = Integer.parseInt(st.nextToken()),
			H = Integer.parseInt(st.nextToken()),
			ladder[][] = new int[H + 2][N + 1],
			a, b;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			ladder[a][b] = 1; // 오른쪽
			ladder[a][b + 1] = 2; // 왼쪽
		}
		
		for(int h = 1; h <= H; h++) {
			for(int w = 1; w < N; w++) {
				// 자신의 양 옆에 아무 선도 없으면 놓을 수 있는 위치
				if(ladder[h][w] == 0 && ladder[h][w + 1] == 0)
					list.add(new int[]{h, w});
			}
		}
		
		for(int i = 1; i <= N; i++) {
			ladder[H + 1][i] = i; // 도착지점
		}
		
		// 새로운 선 없이도 올바른 케이스
		if( testCorrectCase(ladder, H, N) ) {
			System.out.print(0);
			return;
		}
		
		// 3개 선을 추가한 케이스까지 dfs
		for(int r = 1; r <= 3; r++) {
			dfs(list, ladder, H, N, 0, 0, r);
			
			if(flag) {
				System.out.print(r);
				return;
			}
		}
		
		System.out.print(-1);
	}
	
	private static void dfs(List<int[]> list, int[][] ladder, int H, int N, int startIdx, int cnt, int R) {
		if(flag) return;
		
		if(cnt == R) {
			if( testCorrectCase(ladder, H, N) ) flag = true;
			
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			int[] p = list.get(i);
			
			if(ladder[p[0]][p[1]] != 0 || ladder[p[0]][p[1] + 1] != 0) continue;
			
			ladder[p[0]][p[1]] = 1;
			ladder[p[0]][p[1] + 1] = 2;
			
			dfs(list, ladder, H, N, i + 1, cnt + 1, R);
			
			ladder[p[0]][p[1]] = 0;
			ladder[p[0]][p[1] + 1] = 0;
		}
	}
	
	private static boolean testCorrectCase(int[][] ladder, int H, int N) {
		int h, w, nh, nw;
		
		for(int i = 1; i <= N; i++) {
			h = 1;
			w = i;
			
			while(h <= H) {
				nh = h + dh[ ladder[h][w] ];
				nw = w + dw[ ladder[h][w] ];
				h = nh;
				w = nw;
			}
			
			if(i != ladder[h][w]) return false;
		}
		
		return true;
	}
}