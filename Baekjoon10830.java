package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon10830 {

	static final int MOD = 1000;	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int[][] matrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 계산
		matrix = pow(matrix, B);
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		in.close();
	}

	private static int[][] pow(int[][] matrix, long e) {
		int[][] mul = new int[N][N];
		
		// 초기값 단위 행렬로 초기화
		for (int i = 0; i < N; i++)
			mul[i][i] = 1;
		
		while(e > 0) {
			if(e % 2 == 1)
				mul = mulMatrix(mul, matrix);

			matrix = mulMatrix(matrix, matrix);
			
			e /= 2;			
		}
		
		return mul;
	}

	private static int[][] mulMatrix(int[][] a, int[][] b) {
		int[][] mul = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					mul[i][j] += a[i][k] * b[k][j];
					mul[i][j] %= MOD;
				}
			}
		}
		
		return mul;
	}
	
}