import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

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
		
		findMatchingLetter();
		
		System.out.println(cnt);
		System.out.print(sb.toString());
		
		in.close();
	}

	private static void getPi() {
		
		for (int i = 1, j = 0; i < pattern.length; i++) {
			
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
			
			if(pattern[i] == pattern[j]) pi[i] = ++j;
		}
		
	}

	private static void findMatchingLetter() {
		for (int i = 0, j = 0; i < text.length; i++) {
						
			while(j > 0 && text[i] != pattern[j]) j = pi[j - 1];
			
			if(text[i] == pattern[j]) {
				if(j == pattern.length - 1) {
					cnt++;
					sb.append( (i + 2) - pattern.length).append(" ");
					j = pi[j];
				} else {
					j++;					
				}
			}
		}
	}
}