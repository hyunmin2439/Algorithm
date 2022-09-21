package uploaded;

import java.util.Scanner;

/* BACKJOON 2798 (BlackJack)
 * 
 * 입력
 * 첫째 줄에 카드의 개수 N(3 ≤ N ≤ 100)과 M(10 ≤ M ≤ 300,000)이 주어진다. 
 * 둘째 줄에는 카드에 쓰여 있는 수가 주어지며, 이 값은 100,000을 넘지 않는 양의 정수이다.
 * 합이 M을 넘지 않는 카드 3장을 찾을 수 있는 경우만 입력으로 주어진다.
 * 
 * 출력
 * 첫째 줄에 M을 넘지 않으면서 M에 최대한 가까운 카드 3장의 합을 출력한다.
 */

public class Baekjoon2798 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N, M;
		int max = 0;
		int sum = 0;
		
		N = in.nextInt();
		M = in.nextInt();
		
		int[] num = new int[N];
		
		for(int i = 0; i < N; i++) {
			num[i] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				for(int k = j + 1; k < N; k++) {
					sum = num[i] + num[j] + num[k];
					if(max < sum && sum <= M ) max = sum;
				}
			}
		}
		
		System.out.println(max);
	}
}