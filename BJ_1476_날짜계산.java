package solved;

import java.io.*;
import java.util.StringTokenizer;

/*
 * Input
 * e s m (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19)
 * 
 * e = 1 && s = 1 && m = 1 => y = 1
 * e = 1 && s = 16 && m = 16 => y = 16
 * e = 1 && s = 2 && m = 3 => y = 5266
 * e = 15 && s = 28 && m = 19 => y = 7980
 * 
 * Output
 * y
 */

public class BJ_1476_날짜계산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		//String y = firstSolve( br );
		String y = secondSolve( br );
		
		bw.write(y);
		br.close();
		bw.close();
	}

	// memory : 14216KB second : 224ms
	private static String secondSolve(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		int e = Integer.parseInt(st.nextToken()) - 1;
		int s = Integer.parseInt(st.nextToken()) - 1;
		int m = Integer.parseInt(st.nextToken()) - 1;
		
		int y = 0;
		
		// Takes more time to calculate the rest
		while( !(y % 15 == e 
				&& y % 28 == s 
				&& y % 19 == m) ) {
			y++;
		}
		
		return String.valueOf(y + 1);
	}

	// memory : 14324KB second : 128ms
	private static String firstSolve(BufferedReader br) throws IOException {
		int y = 1;
		int e = 1, s = 1, m = 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int iE = Integer.parseInt(st.nextToken());			
		int iS = Integer.parseInt(st.nextToken());			
		int iM = Integer.parseInt(st.nextToken());
		
		while( !(e == iE && s == iS && m == iM) ) {
			y++;
			e++; s++; m++;
			
			if(e > 15) e = 1;
			if(s > 28) s = 1;
			if(m > 19) m = 1;
		}
		
		return String.valueOf(y);
	}

}
