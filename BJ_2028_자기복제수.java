package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Memory:14172ms / Time:124ms
public class BJ_2028_자기복제수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < T; i++) {
			int num = Integer.parseInt(in.readLine());
			
			int digit = (int)( Math.log10(num) + 1 );
			
			int squareNum = (int)Math.pow(num, 2);
			
			int remainNum = (int)Math.pow(10, digit);
			
			System.out.println( num == squareNum % remainNum ? "YES" : "NO" );
		}
		
		in.close();
	}

}
