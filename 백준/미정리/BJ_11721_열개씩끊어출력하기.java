package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11721_열개씩끊어출력하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		char[] str = in.readLine().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		while(i < str.length) {
			for(int j = 0; i < str.length && j < 10; i++, j++)
				sb.append(str[i]);
			
			sb.append('\n');
		}
		
		System.out.print(sb);
	}

}
