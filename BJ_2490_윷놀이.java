package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2490_윷놀이 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] count = new int[2];
		StringTokenizer st = null;
		String res = "";
		
		for(int i = 0; i < 3; i++) {
			count[0] = count[1] = 0;
			st = new StringTokenizer(in.readLine());
			
			for(int j = 0; j < 4; j++) {
				count[ Integer.parseInt(st.nextToken()) ]++;				
			}
			
			switch(count[0]) {
			case 0: res += "E\n"; break;
			case 1: res += "A\n"; break;
			case 2: res += "B\n"; break;
			case 3: res += "C\n"; break;
			case 4: res += "D\n"; break;
			}	
		}
		
		System.out.print(res);
		
		in.close();
	}

}
