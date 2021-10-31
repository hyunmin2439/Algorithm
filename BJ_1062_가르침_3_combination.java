import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 순서가 상관 없는 조합 문제인데, 순열로 푸니 시간초과가 남.
 * 
 * 조합으로 푸니 시간초과가 나지 않고 문제 해결.
 * 
 * 모든 단어 앞 뒤에 anta, tica가 붙으니 K가 5 미만이면 읽을 수 있는 단어가 없음.
 * 
 * 또한 K가 26이면 알파벳 종류가 26개이므로 모든 단어를 읽을 수 있음.
 * 
 * 때문에 5 <= K < 26, 일때만 조합을 찾는다.
 * 
 * backtracking보단 조합문제라고 생각 됨.
 * 
 * Memory:294928KB / Time:592ms
 */

public class BJ_1062_가르침_3_combination {
	
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
		
		// 26가지 알파벳 읽을 수 있으면 모든 단어 읽을 수 있음
		if(K == 26) {
			System.out.println(N);
			return;
		}
		
		K -= 5; // 기본 5가지 알파벳 빼고 가르칠 수 있는 알파벳 수
		backtracking(0, 0);
		
		System.out.print(maxNumOfReadableWord);
		
		in.close();
	}

	private static void backtracking(int start, int idx) {
		if(K == idx) {
			maxNumOfReadableWord = Math.max(maxNumOfReadableWord, getNumOfReadableWord());
			return;
		}
		
		for(int i = start; i < numOfAlphabet; i++) {
			if( readable[i] ) continue;
			
			readable[i] = true;
			
			backtracking(i + 1, idx + 1);
			
			readable[i] = false;
		}
	}

	private static int getNumOfReadableWord() {
		int numOfReadableWord = 0;
		
		for(int i = 0; i < N; i++) {
			boolean isReadable = true;
			char[] word = words[i].toCharArray();
			
			for(int j = 0; j < word.length; j++) {
				// 문자의 해당 알파벳을 읽을 수 없으면
				if( !readable[ word[j] - 'a' ] ) {
					isReadable = false;
					break;
				}
			}
			
			if( isReadable ) numOfReadableWord++;
		}
		
		return numOfReadableWord;
	}
	
}
