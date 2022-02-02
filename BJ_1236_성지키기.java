package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:14256KB / Time:124ms
 */
public class BJ_1236_성지키기 {

	private static int N, M;
	private static char[][] castle;
	
	public static void main(String[] args) throws Exception {
		input();
		
		int res = solve();
		
		System.out.print(res);
	}
	
	private static int solve() {
		boolean[] isExistInRow = new boolean[N];
		boolean[] isExistInCol = new boolean[M];
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(castle[y][x] == 'X') {
					isExistInRow[y] = true;
					isExistInCol[x] = true;
				}
			}
		}
		
		int numOfNotExistInRow = 0, numOfNotExistInCol = 0;
		for(int y = 0; y < N; y++) {
			if(!isExistInRow[y]) numOfNotExistInRow++;
		}
		for(int x = 0; x < M; x++) {
			if(!isExistInCol[x]) numOfNotExistInCol++;
		}
		
		// 3행 X, 4열 X => (3, 4)에 경비원 세우면 된다.
		// 때문에 행과 열, 둘 중 큰 값으로 결과값
		return Math.max(numOfNotExistInRow, numOfNotExistInCol);
	}
	
	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		castle = new char[N][];
		
		for(int y = 0; y < N; y++) {
			castle[y] = in.readLine().toCharArray();
		}
		
		in.close();
	}

}
