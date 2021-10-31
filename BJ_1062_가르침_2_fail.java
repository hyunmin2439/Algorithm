import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Backtracking 실패
 * 
 * 가르쳐야 하는 알파벳 리스트를 만들고 backtracking
 * 
 * 완전 탐색해야 하는 알파벳 리스트를 줄였으나
 * 
 * 이것 역시 시간 초과로 실패함.
 * 
 * 다른 방법으로 푸는 생각의 전환이 필요할 듯 함.
 */

public class BJ_1062_가르침_2_fail {
	
	static final int numOfAlphabet = 26;
	
	static int N, K, maxNumOfReadableWord, numOfTeachAlphabet;
	
	static boolean[] readable, teachAlphabet;
	
	static String[] words;
	
	static List<Integer> teachList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		readable = new boolean[numOfAlphabet];
		teachAlphabet = new boolean[numOfAlphabet]; // 가르쳐야 할 알파벳
		words = new String[N];
		teachList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			words[i] = in.readLine().substring(4); // anta 빼고 받기
			words[i] = words[i].substring(0, words[i].length() - 4); // tica 빼기
			
			int wordLength = words[i].length();
			for(int j = 0; j < wordLength; j++) {
				teachAlphabet[ words[i].charAt(j) - 'a' ] = true; // 가르쳐야 할 알파벳으로 체크
			}
		}
		// 기본 5가지 알파벳 못 읽으면 읽을 수 있는 단어 없음
		if(K < 5) {
			System.out.print(0);
			return;
		}

		// 남극의 모든 단어 anta 시작, tica로 끝난다 -> a c i n t 기본 사용
		readable['a' - 'a'] = readable['c' - 'a'] = readable['i' - 'a'] = readable['n' - 'a'] = readable['t' - 'a'] = true;
		
		
		// 가르쳐야 할 알파벳 리스트 만들기
		for(int i = 0; i < numOfAlphabet; i++) {
			if( teachAlphabet[i] ) teachList.add(i);
		}
		
		K -= 5; // 기본 5가지 알파벳 빼고 가르칠 수 있는 알파벳 수
		numOfTeachAlphabet = teachList.size(); // 가르쳐야 하는 알파벳 종류 수
		
		backtracking(0);
		
		System.out.print(maxNumOfReadableWord);
		
		in.close();
	}

	private static void backtracking(int idx) {
		if(K == idx) {
			maxNumOfReadableWord = Math.max(maxNumOfReadableWord, getNumOfReadableWord());
			return;
		}
		
		for(int i = 0; i < numOfTeachAlphabet; i++) {
			int alphaIdx = teachList.get(i);
			
			if( readable[alphaIdx] ) continue;
			
			readable[alphaIdx] = true;
			
			backtracking(idx + 1);
			
			readable[alphaIdx] = false;
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
