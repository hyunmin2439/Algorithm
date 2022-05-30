import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Memory:14292KB / 136ms
 */

public class BJ_1051_숫자정사각형 {

	static int N, M;
	
	static char[][] square;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		square = new char[N][];
		for(int y = 0; y < N; y++) {
			square[y] = in.readLine().toCharArray();
		}
		
		int maxArea = findMaxAreaSquare();

		System.out.print(maxArea);
		
		in.close();
	}

	private static int findMaxAreaSquare() {
		int length = 0, maxLength = 0; // 정사각형 길이, 최대 정사각형 길이
		
		while( !(length >= N || length >= M) ) {
			
			// 꼭지점 숫자가 같은 사각형이 있으면 length값으로 최대값 변경
			if( isSquare(++length) ) maxLength = length;
			
		}
		maxLength++;
		
		return maxLength * maxLength;
	}

	private static boolean isSquare(int length) {
		
		for(int y = 0; y < N - length; y++) {
			for(int x = 0; x < M  - length; x++) {
	
				if(square[y][x] == square[y][x + length] // 윗 꼭지점
				&& square[y + length][x] == square[y + length][x + length]  // 아래꼭지점
				&& square[y][x] == square[y + length][x] ) // 윗꼭지점, 아래꼭지점끼리만 같을 수도 있기 때문에 한번더 비교
					return true;
			}
		}
		
		return false;
	}
	
	

}
