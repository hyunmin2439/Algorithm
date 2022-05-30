import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int numOfAlphabet = 26;
	
	static int N, K, maxNumOfReadableWord;
	
	static boolean[] readable, teachAlphabet;
	
	static String[] words;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		readable = new boolean[numOfAlphabet];
		words = new String[N];
		
		for(int i = 0; i < N; i++) {
			words[i] = in.readLine().substring(4);
			words[i] = words[i].substring(0, words[i].length() - 4);
			
		}

        readable['a' - 'a'] = readable['c' - 'a'] = readable['i' - 'a'] = readable['n' - 'a'] = readable['t' - 'a'] = true;

		if(K < 5) {
			System.out.print(0);
			return;
		}
		if(K == 26) {
			System.out.println(N);
			return;
		}
		
		K -= 5;
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