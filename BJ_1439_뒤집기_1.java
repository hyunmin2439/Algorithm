package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 1, 0 둘 다 뒤집어 보고 적은 횟수를 출력하기.
 * 
 * Memory:14216ms / Time:136ms
 */

public class BJ_1439_뒤집기_1 {

	static char[] str;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		str = in.readLine().toCharArray();
		
		int zero = calcNumOfReverse('0');
		int one = calcNumOfReverse('1');
		
		System.out.print( zero < one ? zero : one );
		in.close();
	}

	private static int calcNumOfReverse(char reverseChar) {
		int numOfReverse = 0;
		
		for(int i = 0; i < str.length; i++) {
			
			// 같은 문자면
			if(str[i] == reverseChar) {
				
				// 다른 문자가 나올때까지 증가.
				while(i < str.length && str[i] == reverseChar) i++;
				
				// 뒤집는 횟수 한번 증가.
				numOfReverse++;
			}
		}
		
		return numOfReverse;
	}
	
}
