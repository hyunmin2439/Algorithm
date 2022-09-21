package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1526_가장_큰_금민수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		in.close();
		
		String strX;
		int x = N;
		
		while(true) {
			strX = String.valueOf(x);
			
			if( isAnswer(strX) ) break;
			
			x--;
		}
		
		System.out.println(x);
	}
	
	private static boolean isAnswer(String strX) {
		char num;
		
		for(int i = 0; i < strX.length(); i++) {
			num = strX.charAt(i);
			
			if( !(num == '4' || num == '7') ) return false;
		}
		
		return true;
	}

}
