package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1864_문어숫자 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] changeChar = { '-', '\\', '(', '@', '?', '>', '&', '%', '/' };
		
		int num;
		while(true) {
			char[] octoNum = in.readLine().toCharArray();
			
			if(octoNum[0] == '#') break;
			
			num = 0;
			for(int i = octoNum.length - 1; i >= 0; i--) {
				
				for(int j = 0; j <= 8; j++) {
					if(octoNum[octoNum.length - 1 - i] == changeChar[j]) {
						if(j == 8)
							num += -1 * Math.pow(8, i);
						else
							num += j * Math.pow(8, i);
					}
					
				}
			}
			
			System.out.println(num);
		}

		
		in.close();
	}

}
