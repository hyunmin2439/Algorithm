package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * Memory: 14,272KB / Time: 128ms
 */

public class BJ_2149_암호해독 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String key = in.readLine();
		String cyphertext = in.readLine();
		
		int textLen = cyphertext.length() / key.length();
		
		Node[] plaintext = new Node[key.length()];
		
		for(int i = 0; i < key.length(); i++) {
			plaintext[i] = new Node(key.charAt(i), i, textLen);
		}
		
		Arrays.sort(plaintext, (a, b) -> a.keyChar - b.keyChar );
		
		int idx = 0;
		for(int i = 0; i < key.length(); i++) {
			for(int j = 0; j < textLen; j++, idx++) {
				plaintext[i].text[j] = cyphertext.charAt(idx);
			}
		}
		
		Arrays.sort(plaintext, (a, b) -> a.idx - b.idx );
		
		idx = 0;
		for(int i = 0; i < cyphertext.length();) {
			for(int j = 0; j < key.length(); j++, i++) {
				System.out.print(plaintext[j].text[idx]);
			}
			idx++;
		}
		
		
		in.close();
	}

	static class Node {
		char keyChar;
		int idx;
		char[] text;
		
		public Node(char keyChar, int idx, int length) {
			this.keyChar = keyChar;
			this.idx = idx;
			text = new char[length];
		}

	}
}
