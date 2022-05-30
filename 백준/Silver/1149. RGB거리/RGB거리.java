import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] rgb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		rgb = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			rgb[i][0] = Math.min(rgb[i - 1][1], rgb[i - 1][2]) + Integer.parseInt(st.nextToken());
			rgb[i][1] = Math.min(rgb[i - 1][0], rgb[i - 1][2]) + Integer.parseInt(st.nextToken());
			rgb[i][2] = Math.min(rgb[i - 1][0], rgb[i - 1][1]) + Integer.parseInt(st.nextToken());
		}
		
		System.out.println( Math.min(rgb[N][0], Math.min(rgb[N][1], rgb[N][2]) ) );
		
		br.close();
	}

}