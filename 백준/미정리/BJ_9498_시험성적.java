package uploaded;

import java.util.Scanner;

/* Backjoon9498 (시험성적)
 * 문제
 * 시험 점수를 입력받아 90 ~ 100점은 A, 80 ~ 89점은 B, 70 ~ 79점은 C, 
 * 60 ~ 69점은 D, 나머지 점수는 F를 출력하는 프로그램을 작성하시오.
 * 
 * 입력
 * 시험 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
 * 
 * 출력
 * 시험 성적을 출력한다.
 */

public class BJ_9498_시험성적 {
	public static void main(String[] args) {
		int 	score;
		char 	grade = 'F';
		
		Scanner in = new Scanner(System.in);
		score = in.nextInt();
		
		switch(score / 10) {
		case 10: case 9: grade = 'A'; break;
		case 8: 		 grade = 'B'; break;
		case 7: 		 grade = 'C'; break;
		case 6: 		 grade = 'D'; break;
		}
		
		System.out.println(grade);
	}
}
