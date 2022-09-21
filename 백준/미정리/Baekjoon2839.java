package solved;

import java.io.*;

/*
 * 아직 못풀음
 * 
 * 3kg 5kg 봉지
 * ex) 18kg => 5kg : 3개 3kg : 1개
 * 
 * 입력
 * n (3 <= n <= 5000)
 * 
 * 출력
 * 최소 개수, 정확한 n kg 안되면 -1
 * 
 * dp문제
 */


public class Baekjoon2839 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n;
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		// input
		n = Integer.parseInt( br.readLine().trim() );
		int[][] sugar = new int[2][n + 1];
		
		//init
		for(int i = 0; i <= n; i++) {
			sugar[0][i] = -1; sugar[1][i] = -1;
			
			if( i % 3 == 0 ) sugar[0][i] = i / 3;
			
			if( i % 5 == 0 ) sugar[1][i] = i / 5;
		}
		
		// process
		for (int i = 3; i <= n; i++) {
			int min = 9999;
			int[] tmp = new int[3];
			
			tmp[0] = sugar[0][i];
			
			if( i >= 3 ) tmp[1] = sugar[0][i - 3] + 1;
			
			if( i >= 5 ) tmp[2] = sugar[1][i - 5] + 1;
			
			for(int j = 0; j < tmp.length; j++) {
				if( 0 < tmp[j] && tmp[j] < min) 
					min = tmp[j];
			}

			if(min == 9999) sugar[1][i] = -1;
			else sugar[1][i] = min;
		}
		
		// output test
//		for (int i = 0; i <= n; i++) {
//			System.out.print(i + "  ");
//		}		
//		
//		System.out.println();
//		
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j <= n; j++) {
//				System.out.print(sugar[i][j] + "  ");
//			}
//			System.out.println();
//		}
		
		bw.write( String.valueOf(sugar[1][n]) );
		
		br.close();
		bw.close();
		
		
		////////////////////another solve////////////////////
		// 3kg-- l(3 of count)++;  
		// 5 of multiple break;
		// l + 5 of multiple count
		
		int l = 0, ans = -1;
		while( n >= 0 ) {
			if( n % 5 == 0 ) {
				ans = ( n / 5 ) + l;
				break;
			}
			n = n - 3;
			l++;
		}
		System.out.println(ans);
		
		
	}

}
