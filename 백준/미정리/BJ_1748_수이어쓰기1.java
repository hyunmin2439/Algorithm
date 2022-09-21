package uploaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
 * 1 ~ 9, 10 ~ 99, 100 ~ 999 단위로 자리수가 같음
 * 
 * 999의 경우 900 90 9 단위로 자리수를 계산해서 더 해주면 된다.
 * 
 * ex1 ) 999 = 900 * 3 + 90 * 2 + 9 * 1 = 2889
 * 
 * ex2 ) 172 = 73 * 3 + 90 * 2 + 9 * 1 = 408
 * 
 * Memory:14140KB / Time:116ms
*/
public class BJ_1748_수이어쓰기1 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		long res = 0;
		
		int digit = 1, subNum = 9;
		
		while(true) {
			if(N <= subNum) {
				res += N * digit;
				break;
			}
			else if(N > subNum) {
				N -= subNum;
				res += subNum * digit;
			}
			
			digit += 1;
			subNum *= 10;
		}
		
		System.out.print(res);
		
		in.close();
	}

}