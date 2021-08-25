package uploaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon10988 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		int res = 1, i = 0, n = line.length() - 1;
		
		while(i < n) {
			
			if(line.charAt(i) != line.charAt(n)) {
				res = 0;
				break;
			}
			
			i++; n--;
		}
		
		System.out.println(res);
		br.close();
	}

}
