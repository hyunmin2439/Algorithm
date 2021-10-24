import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP
 * 
 * 최소, 최대 누적값을 계산하면서 입력 받음
 * 
 * Memory:51760KB / Time:424ms
 */

public class Baekjoon2096 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[][] dpMin = new int[N + 1][3];
		int[][] dpMax = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				
				switch(j) {
				case 0 :
					min = Math.min(dpMin[i - 1][j], dpMin[i - 1][j + 1]);
					max = Math.max(dpMax[i - 1][j], dpMax[i - 1][j + 1]);
					break;
				case 1 :
					min = Math.min(dpMin[i - 1][j - 1], Math.min(dpMin[i - 1][j], dpMin[i - 1][j + 1]) );
					max = Math.max(dpMax[i - 1][j - 1], Math.max(dpMax[i - 1][j], dpMax[i - 1][j + 1]) );
					break;
				case 2 :
					min = Math.min(dpMin[i - 1][j - 1], dpMin[i - 1][j]);
					max = Math.max(dpMax[i - 1][j - 1], dpMax[i - 1][j]);
					break;
				}
				
				dpMin[i][j] = min + num;
				dpMax[i][j] = max + num;
			}
		}
		
		int min = Math.min(dpMin[N][0], Math.min(dpMin[N][1], dpMin[N][2]) );
		int max = Math.max(dpMax[N][0], Math.max(dpMax[N][1], dpMax[N][2]) );
		
		System.out.println(max + " " + min);
		in.close();
	}

}
