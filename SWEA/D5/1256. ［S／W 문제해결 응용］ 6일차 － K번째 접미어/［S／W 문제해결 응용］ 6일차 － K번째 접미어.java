import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	private static final int ALPHABET_MAX_SIZE = 26;
	private static boolean isEnd;
	private static int cnt, K, finalIdx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		Node head, curr;
		char[] str, ans = new char[401];
		int t = 0, T;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < T) {
			K = Integer.parseInt(in.readLine());
			str = in.readLine().toCharArray();
			head = new Node();
			isEnd = false;
			cnt = 0;
			finalIdx = 0;
			
			for(int start = 0; start < str.length; start++) {
				curr = head;
				for(int i = start; i < str.length; i++) {
					curr = curr.addNode(str[i]);
				}
				curr.isTerminal = true;
			}
			
			dfs(head, ans, 'a', 0);
			
			sb.append("#").append(t).append(" ");
			if(!isEnd) sb.append("none\n");
			else {
				for(int i = 1; i <= finalIdx; i++) {
					sb.append(ans[i]);
				}
				sb.append("\n");
			}
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	private static void dfs(Node curr, char[] ans, char c, int idx) {
		if(isEnd) return;
		
		ans[idx] = c;
		
		if(curr.isTerminal) cnt++;
		
		if(cnt == K) {
			isEnd = true;
			finalIdx = idx;
			return;
		}
		
		for(int i = 0; i < ALPHABET_MAX_SIZE; i++) {
			if(curr.child[i] == null) continue;
			dfs(curr.child[i], ans, (char)(i + 'a'), idx + 1);
		}
	}
	
	static class Node {
		Node[] child;
		boolean isTerminal;
		
		public Node() {
			this.child = new Node[ALPHABET_MAX_SIZE];
		}
		
		public Node addNode(char c) {
			if(child[c - 'a'] == null)
				this.child[c - 'a'] = new Node();
			
			return this.child[c - 'a'];
		}
	}

}