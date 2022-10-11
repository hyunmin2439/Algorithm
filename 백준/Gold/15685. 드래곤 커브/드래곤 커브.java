import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[] dy = { 0, -1, 0, 1 };
	private static int[] dx = { 1, 0, -1, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[][] map = new boolean[101][101];
		int[][][] dg = makeDragonCurveAllGeneration();
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			drawDragonCurve(map, dg, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int cnt = countSquare(map);
		
		System.out.print(cnt);
	}
	
	private static int[][][] makeDragonCurveAllGeneration() {
		int[][][] dg = new int[4][11][]; // 4방향 11 세대까지
		int[] nd = { 1, 2, 3, 0 }; // 0 -> 1 -> 2 -> 3 -> 0
		int pvLen = 0;
		
		// initialize
		for(int d = 0; d < 4; d++) {
			for(int g = 0; g < dg[d].length; g++) {
				dg[d][g] = new int[(int)Math.pow(2, g)]; // 세대가 증가할수록 이전 세대 * 2
				dg[d][g][0] = d;
			}
		}

		for(int d = 0; d < 4; d++) {
			for(int g = 1; g < dg[d].length; g++) {
				pvLen = dg[d][g - 1].length; // 이전 세대 길이
				for(int i = 1; i <= pvLen; i++) {
					// 이전 세대 마지막 점을 기준으로 회전하기 때문에
					// 이전 세대의 마지막 값 부터 첫 번째 값까지 들어감
					dg[d][g][ pvLen + i - 1 ] = nd[ dg[d][g - 1][ pvLen - i ] ];
					
					// 모든 세대에 같은 값을
					for(int j = g + 1; j < dg[d].length; j++) {
						dg[d][j][ pvLen + i - 1 ] = dg[d][g][ pvLen + i - 1 ];
					}
				}
			}
		}
		
		return dg;
	}
	
	private static void drawDragonCurve(boolean[][] map, int[][][] dg, int x, int y, int d, int g) {
		map[y][x] = true;
		
		for(int i = 0; i < dg[d][g].length; i++) {
			y += dy[ dg[d][g][i] ];
			x += dx[ dg[d][g][i] ];
			map[y][x] = true;
		}
	}
	
	private static int countSquare(boolean[][] map) {
		int cnt = 0;
		for(int y = 1; y < map.length; y++) {
			for(int x = 1; x < map[y].length; x++) {
				if( map[y - 1][x - 1] && map[y][x - 1] && map[y - 1][x] && map[y][x] )
					cnt++;
			}
		}
		
		return cnt;
	}
}