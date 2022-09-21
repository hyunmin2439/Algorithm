package solved;

import java.io.*;

/*
 * Input
 * n 		(2  <=  n   <= 50)
 * x1 y1	(10 <= x, y <= 200)
 * x2 y2
 * ...
 * xn yn
 * 
 * x1 > x2 && y1 > y2 rank (1 > 2)
 * x2 > x3 && y2 < y3 rank (2 == 3)
 * ...
 * 
 * Output
 * rank of n
 * 
 * ex)
 * Input
 * 5
 * 55 185
 * 58 183
 * 88 186
 * 60 175
 * 46 155
 * 
 * Output
 * 2 2 1 2 5
 */

public class Baekjoon7568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int cnt = 0;
		String[] line;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		int n = Integer.parseInt( br.readLine().trim() );
		int[][] heiWet = new int[n][3]; // height / weight / rank
		
		// Input && Init
		while(cnt < n) {
			line = br.readLine().trim().split(" ");
			heiWet[cnt][0] = Integer.parseInt( line[0].trim() );
			heiWet[cnt][1] = Integer.parseInt( line[1].trim() );
			heiWet[cnt][2] = 1;
			
			cnt++;
		}
		
		// Process
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(heiWet[i][0] < heiWet[j][0] &&
						heiWet[i][1] < heiWet[j][1]) heiWet[i][2]++;
			}
		}
		
		// Output
		for(int i = 0; i < n; i++) {
			bw.write(heiWet[i][2] + " ");
		}
		
		br.close();
		bw.close();
	}

}
