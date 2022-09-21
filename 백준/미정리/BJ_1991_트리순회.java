package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Memory:14236KB / Time:132ms
public class BJ_1991_트리순회 {

	private static int N;
	
	public static void main(String[] args) throws Exception {
		Node[] tree = input();
		StringBuilder sb = new StringBuilder();
		
		preorder(tree, 0, sb);
		sb.append('\n');
		inorder(tree, 0, sb);
		sb.append('\n');
		postorder(tree, 0, sb);
		
		System.out.print(sb);
	}
	
	private static void preorder(Node[] tree, int idx, StringBuilder sb) {		
		sb.append(tree[idx].alphabet);
		
		if(tree[idx].left != null)
			preorder(tree, tree[idx].left.alphabet - 'A', sb);
		
		if(tree[idx].right != null)
			preorder(tree, tree[idx].right.alphabet - 'A', sb);

		return;
	}
	
	private static void inorder(Node[] tree, int idx, StringBuilder sb) {
		if(tree[idx].left != null)
			inorder(tree, tree[idx].left.alphabet - 'A', sb);
		
		sb.append(tree[idx].alphabet);
		
		if(tree[idx].right != null)
			inorder(tree, tree[idx].right.alphabet - 'A', sb);

		return;
	}
	
	private static void postorder(Node[] tree, int idx, StringBuilder sb) {
		if(tree[idx].left != null)
			postorder(tree, tree[idx].left.alphabet - 'A', sb);
		
		if(tree[idx].right != null)
			postorder(tree, tree[idx].right.alphabet - 'A', sb);
		
		sb.append(tree[idx].alphabet);
		
		return;
	}
	
	private static Node[] input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		
		Node[] tree = new Node[N];
		
		for(int i = 0; i < N; i++) {
			tree[i] = new Node((char)('A' + i));
		}
		
		StringTokenizer st = null;
		
		int parent, left, right;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			
			parent = st.nextToken().charAt(0) - 'A';
			
			left = st.nextToken().charAt(0);
			if(left != '.') {
				left = left -'A';
				tree[parent].left = tree[left];
			}
			
			right = st.nextToken().charAt(0);
			if(right != '.') {
				right = right -'A';
				tree[parent].right = tree[right];
			}
		}
		
		in.close();
		
		return tree;
	}

	static class Node {
		char alphabet;
		Node left;
		Node right;
		
		public Node(char alphabet) {
			this.alphabet = alphabet;
		}
	}
}
