import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:18584KB / Time:372ms

public class BJ_2740_행렬곱셈_1 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N][M];
		
		inputMatrix(A, st, in);
		
		st = new StringTokenizer(in.readLine());
		st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		
		int[][] B = new int[M][K];
		
		inputMatrix(B, st, in);
		
		int[][] C = calcMatrix(A, B);
		
		printMatrix(C);
		
		in.close();
	}
	
	private static void inputMatrix(int[][] matrix, StringTokenizer st, BufferedReader in) throws Exception {
		for(int i = 0; i < matrix.length; i++) {
			st = new StringTokenizer(in.readLine());
			
			for(int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static int[][] calcMatrix(int[][] A, int[][] B) {
		int[][] C = new int[A.length][B[0].length];
		
		for(int k = 0; k < A[0].length; k++) {
			
			for(int i = 0; i < C.length; i++) {
				for(int j = 0; j < C[0].length; j++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		return C;
	}

	private static void printMatrix(int[][] C) {
		for(int i = 0; i < C.length; i++) {
			for(int j = 0; j < C[0].length; j++) {
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
	}

}
