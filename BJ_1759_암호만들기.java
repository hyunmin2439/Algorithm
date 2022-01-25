package solved.submit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호 조건
// 1. 최소 한개의 모음(a, e, i, o, u)
// 2. 최소 두개의 자음
// 3. 단어의 알파벳이 오름차순

// 입력으로 주어지는 알파벳 소문자이며 중복 없음

public class BJ_1759_암호만들기 {

	static int L, C;
	static char[] arr;
	static int[] input;
	static char[] vowel = { 'a', 'e', 'i', 'o', 'u' }; // 모음 리스트
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 사이 공백 지우고 char배열로 변경
		arr = br.readLine().replace(" ", "").toCharArray();

		// 3. 단어의 알파벳이 오름차순 
		// 정렬을 해서 암호를 만들면 매번 체크하지 않아도 됨
		Arrays.sort(arr);
		
		input = new int[C];
		
		for (int i = C - 1; i >= C - L; i--) {
			input[i] = 1;
		}
		
		do {
			check( makePW() );
			
		}while(np());
		
		bw.write(sb.toString());
		br.close();
		bw.close();
	}

	private static String makePW() {
		String pass = "";
		
		for (int i = 0; i < C; i++) {
			if(input[i] == 1) pass += arr[i];
		}
		
		return pass;
	}
	
	private static void check(String pword) {
		int voCnt = 0; 
		
		// 모음 개수 세기
		for (int i = 0; i < vowel.length; i++) {
			if(pword.indexOf(vowel[i]) != -1) voCnt++;
		}
		
		// 1. 모음 개수가 0개면 암호X
		if(voCnt == 0) return;
		
		// 2. 자음 개수가 1개 이하이면 암호X
		if(pword.length() - voCnt < 2) return;

		// 조건 통과시 StringBuilder에 추가
		// 순서가 거꾸로 출력되므로 StringBuilder 앞에 추가해주기
		sb.insert(0, pword + "\n");
	}

	private static boolean np() {
		
		int i = C - 1;
		while(i > 0 && input[i - 1] >= input[i]) i--;
		
		if(i == 0) return false;
		
		int j = C - 1;
		while(input[i - 1] >= input[j]) j--;
		swap(i - 1, j);
		
		int k = C - 1;
		while(i < k) {
			swap(i++, k--);
		}
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
}
