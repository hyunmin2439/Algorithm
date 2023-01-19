import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static int MAX_NODE_SIZE = 10001;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(200);
		StringTokenizer st;
		
		String tmp;
		Node[] btree = new Node[MAX_NODE_SIZE];
		int t = 0, T, V, E, findVtx1, findVtx2, parent, child, sameNum, cnt;
		
		T = Integer.parseInt(in.readLine());
		
		while(t++ < 10) {
			st = new StringTokenizer(in.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			findVtx1 = Integer.parseInt(st.nextToken());
			findVtx2 = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i <= V; i++)
				btree[i] = new Node(i);
			
			st = new StringTokenizer(in.readLine());
			for(int i = 1; i <= E; i++) {
				parent = Integer.parseInt(st.nextToken());
				child = Integer.parseInt(st.nextToken());
				btree[child].allocParent(parent);
				btree[parent].allocChild(child);
			}
			
			calcLevel(btree, 1, 0);
			sameNum = getSameParent(btree, findVtx1, findVtx2);
			cnt = getTreeNodeCnt(btree, sameNum);
			
			sb.append("#").append(t).append(" ")
			.append(sameNum).append(" ").append(cnt)
			.append("\n");
		}
		out.write(sb.toString());
		out.flush();
	}
	
	private static void calcLevel(Node[] btree, int idx, int level) {
		btree[idx].level = level;
		
		if(btree[idx].left == 0) return;
		calcLevel(btree, btree[idx].left, level + 1);
		
		if(btree[idx].right == 0) return;
		calcLevel(btree, btree[idx].right, level + 1);
	}
	
	private static int getSameParent(Node[] btree, int vtx1, int vtx2) {
		while(vtx1 != vtx2) {
			if(btree[vtx1].level == btree[vtx2].level) {
				vtx1 = btree[vtx1].parent;
				vtx2 = btree[vtx2].parent;
			}
			else if(btree[vtx1].level > btree[vtx2].level)
				vtx1 = btree[vtx1].parent;
			else 
				vtx2 = btree[vtx2].parent;
		}
		
		return vtx1;
	}
	
	private static int getTreeNodeCnt(Node[] btree, int idx) {
		if(btree[idx].left == 0 && btree[idx].right == 0)
			return 1;
		else if(btree[idx].left == 0)
			return 1 + getTreeNodeCnt(btree, btree[idx].right);
		else if(btree[idx].right == 0)
			return 1 + getTreeNodeCnt(btree, btree[idx].left);
		
		return 1 + getTreeNodeCnt(btree, btree[idx].left) + getTreeNodeCnt(btree, btree[idx].right);
	}
	
	static class Node {
		int idx, parent, left, right, level;
		
		public Node(int idx) {
			this.idx = idx;
		}
		
		public void allocParent(int parent) {
			this.parent = parent;
		}
		
		public void allocChild(int child) {
			if(this.left == 0) this.left = child;
			else this.right = child;
		}
	}
}