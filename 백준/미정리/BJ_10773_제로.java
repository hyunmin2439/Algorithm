package solved.submit;

import java.io.*;
import java.util.Stack;

/*
 * Input
 * k (1 <= k <= 100,000)
 * input int 1
 * input int 2
 * ...
 * input int k
 * 
 * Process
 * while( k ){
 *	  if(input == 0) pop
 *	  else push
 * }
 * 
 * Ouput
 * Sum of the remaining numbers in stack
 */

public class BJ_10773_제로 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( System.out) );
		
		int k = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		while(k-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if(num != 0)
				stack.push(num);
			else
				stack.pop();
		}
		
		int sum = 0;
		while( !stack.empty() ) {
			sum += stack.pop();
		}
		
		bw.write(String.valueOf(sum));
		br.close();
		bw.close();
	}

}
