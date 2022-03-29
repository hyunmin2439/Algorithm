package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2145_숫자놀이 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 10;
		String num;
		while(true) {
			sum = 10;
			num = in.readLine();
			
			if("0".equals(num)) break;
			
			while(sum > 9) {
				sum = 0;
				
				for(int i = 0; i < num.length(); i++) {
					sum += num.charAt(i) - '0';
				}
				
				num = String.valueOf(sum);
			}
			
			System.out.println(sum);
		}
		
		in.close();

	}
}
