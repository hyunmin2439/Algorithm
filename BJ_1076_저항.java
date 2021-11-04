package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 저항 3개 색 이용해서 몇 옴인지 구하는 문제
 * 
 * 처음 두개 색은 저항의 값, 마지막은 곱해야 하는 값
 * 
 * ex) yellow(4), violet(7), red(value:100)였다면 47 * 100 => 저항의 값은 4,700
 * 
 * int 범위 넘어가기 때문에 long
 */

public class BJ_1076_저항 {

	static String[] color = { "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white" };
	static long[] value = { 1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		long om[] = new long[3];
		
		for (int i = 0; i < 3; i++) {
			String colorStr = in.readLine();
			for (int j = 0; j < 10; j++) {
				if(colorStr.equals(color[j])) {
					om[i] = j;
					break;
				}
			}
		}
		
		om[2] = value[ (int) om[2] ];
		
		System.out.println( (om[0] * 10 + om[1]) * om[2] );
		
		in.close();
	}

}
