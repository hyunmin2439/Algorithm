package solving;

import java.util.Scanner;

public class BJ_2444_별찍기7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(), length = N * 2 - 1, middle = N - 1, space= N - 1, star = 1, sw = 1;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			if(middle == i) sw *= -1;
			
			for(int j = 0; j < space; j++) {
				sb.append(' ');				
			}
			
			for(int j = 0; j < star; j++) {
				sb.append('*');
			}
			
			sb.append('\n');
			
			space -= sw;
			star = star + sw * 2;
		}
		
		System.out.print(sb);
	}

}
