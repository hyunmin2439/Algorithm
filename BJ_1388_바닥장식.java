package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:14268KB / Time:132ms
 */

public class BJ_1388_바닥장식 {

	private static int N, M;
	private static char[][] ground;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		int numOfBoard = 0;
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(!visited[y][x]) {
					visit(y, x);
					numOfBoard++;
				}
			}
		}
		
		return numOfBoard;
	}

	private static void visit(int y, int x) {
		if(ground[y][x] == '-') {
			for(int i = x; i < M; i++) {
				if(ground[y][i] == '|') break;
				
				visited[y][i] = true;
			}
		}
		else {
			for(int i = y; i < N; i++) {
				if(ground[i][x] == '-') break;
				
				visited[i][x] = true;
			}
		}
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ground = new char[N][];
		visited = new boolean[N][M];
		
		for(int y = 0; y < N; y++) {
			ground[y] = in.readLine().toCharArray();
		}
		
		in.close();
	}
}
