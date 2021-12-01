import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2167_2차원배열의합 {

	static int N, M, K;
	static int[][] matrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		matrix = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		K = Integer.parseInt(in.readLine());
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(in.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for(int a = i ; a <= x; a++) {
				for(int b = j ; b <= y; b++) {
					sum += matrix[a][b];
				}
			}
			System.out.println(sum);
		}
		
		in.close();
	}

}
