package notSolved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1543_문서검색 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String document = in.readLine();
		String findWord = in.readLine();
		
		int res = 0, length = document.length(), findLength = findWord.length();
		
		for(int i = 0; i <= length - findLength; i++) {
			if(document.substring(i, i + findLength).equals(findWord)) {
				res++;
				i += findLength - 1;
			}
		}
		
		System.out.print(res);
	}
}