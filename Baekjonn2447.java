import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 국비지원 학원을 다녔을 때 풀려고 했던 문제
 * 
 * 하지만 그때는 알고리즘 실력이 부족해 제대로 풀지 못했따.
 * 
 * 한참을 까먹고 미루고 있다가 근 4달만에 풀었다.
 * 
 * 알고리즘 실력이 많이 성장했다고 느낀 문제이다.
 * 
 * Memory:47260KB / Time:452ms
 */

public class Baekjonn2447 {

	static int N;
	static char[][] matrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		matrix = new char[N][N];
		
		recur(0, 0);
		
		for (int y = 0; y < N; y++) {
			System.out.println(matrix[y]);
		}
		
		in.close();
	}

	private static void recur(int y, int x) {
		// 좌표 체크
		if( !(y < N && x < N) ) return;
		
		// visit 체크
		if( matrix[y][x] != 0 ) return;
		
		// 별 세팅
		matrix[y][x] = isEmptySpace(y, x) ? ' ' : '*';
		
		// 재귀
		recur(y + 1, x);
		recur(y, x + 1);
	}

	private static boolean isEmptySpace(int y, int x) {
		int start = 1, end = 2;

		for (int i = 3; i <= N; i *= 3) {
			int modY = y % i;
			int modX = x % i;
			
			if( (start <= modY && modY < end) 
					&& (start <= modX && modX < end) )
				return true;
				
			start *= 3; 
			end = start * 2;
		}
		
		return false;
	}
	
}
