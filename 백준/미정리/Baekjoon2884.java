package uploaded;

import java.util.Scanner;

/* Backjoon2884
 * 문제
 * 입력이 주어지면 45분 이른 시간 출력하기.
 * 
 * 입력
 * 첫째 줄에 두 정수 H와 M이 주어진다. (0 ≤ H ≤ 23, 0 ≤ M ≤ 59) 
 * H시 M분을 의미한다. 입력 시간은 24시간 표현을 사용한다. 
 * 24시간 표현에서 하루의 시작은 0:0(자정)이고, 끝은 23:59(다음날 자정 1분 전)이다. 
 * 시간을 나타낼 때, 불필요한 0은 사용하지 않는다. (00:00 이런 것 X)
 * 
 * 출력
 * 첫째 줄에 상근이가 창영이의 방법을 사용할 때, 설정해야 하는 알람 시간을 출력한다. 
 * (입력과 같은 형태로 출력하면 된다.)
 */

public class Baekjoon2884 {

	public static void main(String[] args) {
		int H, M;
		
		Scanner in = new Scanner(System.in);
		
		H = in.nextInt(); M = in.nextInt();
		
		if(M >= 45) M -= 45;
		else {
			if(H != 0) H -= 1;
			else H = 23; 
			M += 15;
		}
		
		System.out.println(H + " " + M);
	}

}
