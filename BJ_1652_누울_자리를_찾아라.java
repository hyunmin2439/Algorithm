package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1652_누울_자리를_찾아라 {

	private static int N;
	private static boolean[][] isBlocked;
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	private static void solve() {
		int row = 0, col = 0;
		
		int cnt;
		for(int y = 0; y < N; y++) {
			cnt = 0;
			for(int x = 0; x < N; x++) {
				if( ! isBlocked[y][x] ) cnt++;
				else {
					row = cnt > 1 ? row + 1 : row;
					cnt = 0;
				}
			}
			
			row = cnt > 1 ? row + 1 : row;
		}
		
		for(int x = 0; x < N; x++) {
			cnt = 0;
			for(int y = 0; y < N; y++) {
				if( ! isBlocked[y][x] ) cnt++;
				else {
					col = cnt > 1 ? col + 1 : col;
					cnt = 0;
				}
			}
			
			col = cnt > 1 ? col + 1 : col;
		}
		
		System.out.print(row + " " + col);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		isBlocked = new boolean[N][N];
		
		char[] line = null;
		for(int y = 0; y < N; y++) {
			line = in.readLine().toCharArray();
			for(int x = 0; x < N; x++) {
				if(line[x] == 'X') 
					isBlocked[y][x] = true;
			}
		}
		
		in.close();
	}
}