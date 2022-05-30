import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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
		int length = 0, maxLength = 0;
		
		while( !(length >= N || length >= M) ) {
			if( isSquare(++length) ) maxLength = length;
		}
		maxLength++;
		
		return maxLength * maxLength;
	}

	private static boolean isSquare(int length) {
		
		for(int y = 0; y < N - length; y++) {
			for(int x = 0; x < M  - length; x++) {
	
				if(square[y][x] == square[y][x + length]
				&& square[y + length][x] == square[y + length][x + length]
				&& square[y][x] == square[y + length][x] )
					return true;
			}
		}
		
		return false;
	}
	
	

}