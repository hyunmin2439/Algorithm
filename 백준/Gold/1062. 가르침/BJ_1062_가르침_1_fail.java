import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Backtracking 실패
 * 
 * 가지치기가 전혀 들어가지 않음.
 * 
 * 가장 간단한 완전 탐색으로 풀려는 시도.
 * 
 * 시간 초과 발생.
 */

public class BJ_1062_가르침_1_fail {
	
	static final int numOfAlphabet = 26;
	
	static int N, K, maxNumOfReadableWord;
	
	static boolean[] readable;
	
	static String[] words;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		readable = new boolean[numOfAlphabet];
		words = new String[N];
		for(int i = 0; i < N; i++) {
			words[i] = in.readLine().substring(4); // anta 빼고 받기
			words[i] = words[i].substring(0, words[i].length() - 4); // tica 빼기
		}
		
		// 남극의 모든 단어 anta 시작, tica로 끝난다 -> a c i n t 기본 사용
		readable['a' - 'a'] = readable['c' - 'a'] = readable['i' - 'a'] = readable['n' - 'a'] = readable['t' - 'a'] = true;
		
		// 기본 5가지 알파벳 못 읽으면 읽을 수 있는 단어 없음
		if(K < 5) {
			System.out.print(0);
			return;
		}
		
		K -= 5; // 기본 5가지 알파벳 빼고 가르칠 수 있는 알파벳 수
		backtracking(0);
		
		System.out.print(maxNumOfReadableWord);
		
		in.close();
	}

	private static void backtracking(int idx) {
		if(K == idx) {
			maxNumOfReadableWord = Math.max(maxNumOfReadableWord, getNumOfReadableWord());
			return;
		}
		
		for(int i = 0; i < numOfAlphabet; i++) {
			if( readable[i] ) continue;
			
			readable[i] = true;
			
			backtracking(idx + 1);
			
			readable[i] = false;
		}
	}

	private static int getNumOfReadableWord() {
		int numOfReadableWord = 0;
		
		for(int i = 0; i < N; i++) {
			boolean isReadable = true;
			String word = words[i];
			int wordLength = word.length();
			
			for(int j = 0; j < wordLength; j++) {
				// 문자의 해당 알파벳을 읽을 수 없으면
				if( !readable[ word.charAt(j) - 'a' ] ) {
					isReadable = false;
					break;
				}
			}
			
			if( isReadable ) numOfReadableWord++;
		}
		
		return numOfReadableWord;
	}
	
}
