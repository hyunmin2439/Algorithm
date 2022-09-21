import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 트라이(Trie) Algorithm
 * 
 *  1. 기본적으로 트리구조.
 *  
 *  2. root가 존재(시작)하며 아래로 자식 Node가 존재.
 *  
 *  3. 글자의 각 문자 하나 하나가 노드가 되어 트리구조로 연결 됨.
 *  
 *  4. ACE, AB, ABC, AD라는 4개의 문자가 집합에 포함되어 있는 간단한 예시.
 *  
 *  ex)
 *  			A
 *  	C		B(O)	D(O)
 *  	E(O)	C(O)
 *  
 *  5. 문제에서 집합 S에 대해 N개의 문자열을 입력받을 add 메서드 재귀 구조로 구현
 *  
 *  6. M개의 문자열에 대해서 존재여부를 체크할 checkExist 메서드 재귀 구조로 구현
 *  
 *  7. AB, ABC와 같이 일부가 같은 문자열이 집합 S에 포함될 수 있으므로, isExist 변수로 문자열 존재 여부 판단.
 * 
 * Memory:561164KB / Time:2144ms
 */

public class BJ_14425_문자열집합_1_Trie {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		Node root = new Node();
		
		for(int i = 0; i < N; i++) {
			root.add(0, in.readLine());
		}
		
		int cnt = 0;
		for(int i = 0; i < S; i++) {
			if(root.checkExist(0, in.readLine())) cnt++;
		}
		
		System.out.print(cnt);
		
		in.close();
	}
	

	static class Node {
		private boolean isExist;
		private char text;
		private List<Node> list;
		
		public Node() {
			this.list = new ArrayList<>();
		}
		
		public Node(char text) {
			this.text = text;
			this.list = new ArrayList<>();
		}
		
		// 같은 문자인지 판단
		private boolean equals(char c) {
			return text == c;
		}
		
		// 문자열 재귀구조로 삽입
		private void add(int idx, String str) {
			if(idx == str.length()) {
				isExist = true; // 해당 문자열이 존재
				return;
			}
			
			char c = str.charAt(idx);
			
			for (Node node : list) {
				if( node.equals(c) ) {
					node.add(idx + 1, str);
					return;
				}
			}
			
			list.add(new Node(c));
			list.get(list.size() - 1).add(idx + 1, str);
		}
		
		// 문자열 존재여부 재귀구조로 판단
		private boolean checkExist(int idx, String str) {
			if(idx == str.length()) 
				return isExist; // 문자열 존재 여부
			
			char c = str.charAt(idx);
			
			for (Node node : list) {
				if( node.equals(c) )
					return node.checkExist(idx + 1, str);
			}
			
			return false;
		}
	}
}
