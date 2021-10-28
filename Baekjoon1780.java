import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 분할 정복(Top - down)
 * 
 * Memory:309908KB / Time:896ms
 */

public class Baekjoon1780 {
	
	static int N;
	static int[] numOfPaper; // 0:-1 / 1:0 / 2:1
	static int[][] matrix;
	
	static boolean[][] visited;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		numOfPaper = new int[3];
		matrix = new int[N][N];
		
		queue = new LinkedList<>();
		
		for(int y = 0; y < N; y++) {
			StringTokenizer st = new StringTokenizer(in.readLine());			
			for(int x = 0; x < N; x++) {
				matrix[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, N);
		
		for(int i = 0; i < 3; i++)
			System.out.println(numOfPaper[i]);
		
		in.close();
	}

	// 재귀 메서드
	private static void recur(int sy, int sx, int n) {
		if( n == 0 ) return;
		
		if( isAllSame(sy, sx, n) ) {
			numOfPaper[ matrix[sy][sx] + 1 ]++;
			return;
		}
		
		int ey = sy + n;
		int ex = sx + n;
		
		for( int y = sy; y < ey; y += (n / 3) ) {
			for( int x = sx; x < ex; x += (n / 3) ) {
				recur(y, x, n / 3);
			}
		}
	}

	// 모두 같은 숫자 인지 체크
	private static boolean isAllSame(int sy, int sx, int n) {
		int ey = sy + n;
		int ex = sx + n;
		int num = matrix[sy][sx];
		
		for( int y = sy; y < ey; y++ ) {
			for( int x = sx; x < ex; x++ ) {
				if(matrix[y][x] != num) 
					return false;
			}
		}
		
		return true;
	}

}
