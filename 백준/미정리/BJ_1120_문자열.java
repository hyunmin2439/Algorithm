package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀로 단어를 붙여서 나갈시 시간초과남
// 때문에 A 앞 뒤에는 같은 단어를 붙일 수 있으니
// B의 몇번째 index에 위치 시키느냐에 따라 글자수 차이 결정됨

public class BJ_1120_문자열 {

	static int min;
	static String A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = st.nextToken();
		B = st.nextToken();
		
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i <= B.length() - A.length(); i++) {
			min = Math.min( min, diff(i) );
		}
		
		System.out.println(min);
		br.close();
	}
	
	private static int diff(int i) {
		int cnt = 0;
		
		for (int j = 0; j < A.length(); j++) {
			if(A.charAt(j) != B.charAt(i + j)) cnt++;
		}
		
		return cnt;
	}

}
