package uploaded;

import java.util.Scanner;

/* Backjoon10950 
 * for문 A + B 합
*/

public class BJ_10950_AaddB3 {

	public static void main(String[] args) {
		int T, A, B;
		Scanner in = new Scanner(System.in);
		
		T = in.nextInt();
		
		for(int i = 0; i < T; i++) {
			A = in.nextInt();
			B = in.nextInt();
			
			System.out.println(A + B);
		}
	}
}
