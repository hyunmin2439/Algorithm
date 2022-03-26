package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10808_알파벳개수 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[] alpha = new int[26];
        char[] res = in.readLine().toCharArray();
        
        for(int i = 0; i < res.length; i++) {
        	alpha[ res[i] - 'a' ]++;
        }
        
        for(int i = 0; i < 26; i++) {
        	System.out.print(alpha[i] + " ");
        }
        
		in.close();
	}

}
