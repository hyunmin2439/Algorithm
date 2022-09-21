package no_upload;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1919_애너그램만들기 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] count = new int[2][26];
		
		char[] word = null;
		for(int i = 0; i < 2; i++) {
			word = in.readLine().toCharArray();
			
			for(int j = 0; j < word.length; j++) {
				count[i][word[j] - 'a']++;
			}
		}
		
		int res = 0;
		for(int i = 0; i < 26; i++) {
			res += Math.abs(count[0][i] - count[1][i]);
		}
		
		System.out.print(res);
	}

}
