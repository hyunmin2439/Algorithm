package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon1100 {
	
	static int res;
	static final int len = 8;
	static boolean pan[][] = new boolean[len][len];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int y = 0; y < len; y++) {
			String line = br.readLine();
			for (int x = 0; x < len; x++) {
				if(line.charAt(x) == 'F')
					pan[y][x] = true;
			}
		}
		
		for (int y = 0; y < len; y++) {
			for (int x = 0; x < len; x++) {
				if(y % 2 == 0) {
					if(x % 2 == 0 && pan[y][x]) res++;
				}
				else {
					if(x % 2 == 1 && pan[y][x]) res++;
				}
			}
		}
		
		System.out.println(res);
		br.close();
	}

}
