package solved;

import java.util.Scanner;

/* Backjoon8393
 * 1부터 N까지의 합
 */

public class Backjoon8393 {

	public static void main(String[] args) {
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.println( n * (1 + n) / 2 );
	}

}
