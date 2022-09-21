package uploaded;

import java.util.Scanner;

/* BJ_14681
 * 문제
 * 사분면은 1부터 4까지 번호를 갖는다. "Quadrant n"은 "제n사분면"이라는 뜻이다.
 * 점의 좌표를 입력받아 그 점이 어느 사분면에 속하는지 알아내는 프로그램을 작성하시오. 
 * 단, x좌표와 y좌표는 모두 양수나 음수라고 가정한다.
 * 
 * 입력
 * 첫 줄에는 정수 x가 주어진다. (−1000 ≤ x ≤ 1000; x ≠ 0) 
 * 다음 줄에는 정수 y가 주어진다. (−1000 ≤ y ≤ 1000; y ≠ 0)
 * 
 * 출력
 * 점 (x, y)의 사분면 번호(1, 2, 3, 4 중 하나)를 출력한다.
 */

public class BJ_14681_사분면고르기 {

	public static void main(String[] args) {
		int x, y, qn;
		Scanner in = new Scanner(System.in);
		
		x = in.nextInt(); y = in.nextInt();
		
		if(x > 0) {
			if(y > 0) qn = 1;
			else 	  qn = 4;
		}
		else {
			if(y > 0) qn = 2;
			else	  qn = 3;
		}
		
		System.out.println(qn);
	}

}
