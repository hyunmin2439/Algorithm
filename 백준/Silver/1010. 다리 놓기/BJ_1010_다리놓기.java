package solved;

import java.io.*;

/*
 * Input
 * t
 * n m (0 < n <= m <= 30)
 * ... t-line
 * 
 * 1. There are N sites to the west of the river 
 *    and M sites to the east.
 * 2. Connect up to one leg to one site
 * 3. Build n bridges, legs cannot overlap.
 * 
 * Output
 * The number of cases in which a bridge 
 * can be built under a given condition.
 */

public class BJ_1010_다리놓기 {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int t = Integer.parseInt(br.readLine());
		
		while(t > 0) {
			long num = 1;
			String[] line = br.readLine().split(" ");
			
			int n = Integer.parseInt(line[0]);
			int m = Integer.parseInt(line[1]);
			
			int pNum = m - n;
			
			for(int i = 1; i <= n; i++) {
				num *= i + pNum;
				num /= i;
			}

			t--;
			
			sb.append(num + "\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

}
