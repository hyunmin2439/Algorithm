package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1120 {

	static int min;
	static String B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		B = st.nextToken();
		
		min = Integer.MAX_VALUE;
		recur(A, B.length() - A.length());
		
		System.out.println(min);
		br.close();
	}

	private static void recur(String a, int len) {
		if(len == 0) {
			min = Math.min( min, diff(a) );
			return;
		}
		
		for (char c = 'a'; c <= 'z'; c++) {
			recur(a + c, len - 1);
			recur(c + a, len - 1);
		}
	}
	
	private static int diff(String a) {
		int cnt = 0;
		for (int i = 0; i < a.length(); i++) {
			if(a.charAt(i) != B.charAt(i)) cnt++;
		}
		
		return cnt;
	}

}
