package uploaded;

import java.util.Scanner;

/* Backjoon2739 (구구단)
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 9보다 작거나 같다.
 * 출력
 * 출력형식과 같게 N*1부터 N*9까지 출력한다.
 */
public class Baekjoon2739 {

	public static void main(String[] args) {
		int N;
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		
		for(int i = 1; i <= 9; i++) {
			System.out.println(N + " * " + i + " = " + N * i);
		}
	}
}
