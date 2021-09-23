package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//KMP 알고리즘(Knuth–Morris–Pratt Algorithm)

public class Baekjoon1786 {

	static int cnt;
	static int[] pi;
	static char[] text, pattern;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		text = in.readLine().toCharArray();
		pattern = in.readLine().toCharArray();
		pi = new int[pattern.length];
		sb = new StringBuilder();
		
		getPi();
		
		kmp(); // 문자열 찾기 알고리즘
		
		System.out.println(cnt);
		System.out.print(sb);
		
		in.close();
	}

	// 부분일치 테이블 만들기
	private static void getPi() {
		// i: 접미사, j: 접두사 포인터
		for (int i = 1, j = 0; i < pattern.length; i++) {

			// j에서 틀린 것, j - 1까지는 맞는 문자열이니 j의 포인터를 이전 테이블표를 참조해 옮겨줌
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
			// j가 0 까지 돌게 하면 pi[-1] Runtime 예외상황 발생.
			
			// 일치할 경우 j 증가 다음 문자 비교
			if(pattern[i] == pattern[j]) pi[i] = ++j;
			// else pi[i] = 0; // 기본 초기화 0이라서 굳이 할 필요 X
		}
		
	}

	private static void kmp() {
		for (int i = 0, j = 0; i < text.length; i++) {
			// j = 0면 Runtime에러, pi[0] = 0
			while(j > 0 && text[i] != pattern[j]) j = pi[j - 1];
			
			if(text[i] == pattern[j]) {
				// 완전일치
				if(j == pattern.length - 1) {
					cnt++;
					// 시작 위치 인덱스 : 1부터 시작
					sb.append( i + 2 - pattern.length).append(" ");
					// j까지 일치
					j = pi[j];
				}
				// 부분일치
				else j++;					
			}
		}
	}

}
