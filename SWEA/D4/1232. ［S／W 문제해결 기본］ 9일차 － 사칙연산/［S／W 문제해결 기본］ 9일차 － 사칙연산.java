import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(200);
		StringTokenizer st;
		
		String tmp;
		Node[] btree;
		int t = 0, N, idx, left, right;
		
		while(t++ < 10) {
			N = Integer.parseInt(in.readLine());
			btree = new Node[N + 1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine());
				idx = Integer.parseInt(st.nextToken());
				tmp = st.nextToken();
				
				if(tmp.charAt(0) < '0') {
					left = Integer.parseInt(st.nextToken());
					right = Integer.parseInt(st.nextToken());
					btree[idx] = new Node(idx, tmp.charAt(0), left, right);
				}
				else
					btree[idx] = new Node(idx, Integer.parseInt(tmp));
			}
			
			sb.append("#").append(t).append(" ")
			.append((long)preOrder(btree, N, 1))
			.append("\n");
		}
		out.write(sb.toString());
		out.flush();
	}
	
	private static double preOrder(Node[] btree, int N, int idx) {
		switch(btree[idx].inst) {
		case '*':
			return preOrder(btree, N, btree[idx].left) * preOrder(btree, N, btree[idx].right);
		case '+':
			return preOrder(btree, N, btree[idx].left) + preOrder(btree, N, btree[idx].right);
		case '-':
			return preOrder(btree, N, btree[idx].left) - preOrder(btree, N, btree[idx].right);
		case '/':
			return preOrder(btree, N, btree[idx].left) / preOrder(btree, N, btree[idx].right);
		default: // 숫자면 inst 0
			return btree[idx].num;
		}
	}
	
	static class Node {
		int idx, left, right;
		char inst;
		int num;
		
		public Node(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
		
		public Node(int idx, char inst, int left, int right) {
			this.idx = idx;
			this.inst = inst;
			this.left = left;
			this.right = right;
		}
	}
}