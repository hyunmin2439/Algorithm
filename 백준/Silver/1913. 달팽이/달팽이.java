import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int findNum = Integer.parseInt(in.readLine());
		in.close();
		
		int[][] mat = new int[N][N];
		
		int[] findPos = snail(mat, N, findNum);
		
		printMatrix(mat, N, findPos[0] + 1, findPos[1] + 1);
	}
	
	private static int[] snail(int[][] mat, int N, int findNum) {
		final int[] dy = { -1, 0, 1,  0 }; // 상, 우, 하, 좌
		final int[] dx = {  0, 1, 0, -1 };
		
		int findY = 0, findX = 0;
		int y = N / 2, x = N / 2, num = 1, dir = -1, cnt = 0, max = 0;
		
		while(true) {
			mat[y][x] = num;

			if(findNum == num) {
				findY = y;
				findX = x;
			}
			
			if(y == 0 && x == 0) break;
			
			if(cnt == max) {
				cnt = 0;
				dir = ++dir > 3 ? 0 : dir;
				if(dir == 0 || dir == 2) max++;
			}
			
			num++;
			cnt++;
			y += dy[dir];
			x += dx[dir];
		}
		
		return new int[] {findY, findX};
	}
	
	private static void printMatrix(int[][] mat, int N, int ty, int tx) {
		StringBuilder sb = new StringBuilder();
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				sb.append(mat[y][x]).append(' ');
			}
			sb.append('\n');
		}
		
		sb.append(ty).append(' ').append(tx);
		
		
		System.out.print(sb);
	}
}