import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] matrix = new char[N][], answer = new char[N][];
		
		for(int y = 0; y < N; y++) {
			matrix[y] = in.readLine().toCharArray();
		}
		for(int y = 0; y < N; y++) {
			answer[y] = in.readLine().toCharArray();
		}
		
		int cnt = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] != answer[y][x]) {
					flipOver(matrix, y, x);
					cnt++;
				}
			}
		}
		
		if( !isSame(matrix, answer) ) cnt = -1;
		
		System.out.print(cnt);
	}
	
	private static boolean isSame(char[][] matrix, char[][] answer) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				if(matrix[y][x] != answer[y][x])
					return false;
			}
		}
		
		return true;
	}
	
	private static void flipOver(char[][] matrix, int sy, int sx) {
		int endY = sy + 3, endX = sx + 3;
		
		if(endY > N || endX > M) return;
		
		for(int y = sy ; y < endY; y++) {
			for(int x = sx; x < endX; x++) {
				matrix[y][x] = matrix[y][x] == '1' ? '0' : '1';
			}
		}
	}
}
