import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Memory: 110,540KB / Time:452ms
 */
public class BJ_5052_전화번호_목록 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		
		int n;
		boolean consistency;
		char[] tel;
		Node head;
		while(t-- > 0) {
			n = Integer.parseInt(in.readLine());
			consistency = true;
			head = new Node('H', false);
			for(int i = 0; i < n; i++) {
				tel = in.readLine().toCharArray();
				
				if(consistency) // 일관성이 있을 때만 trie 메서드 작동
					if( !addTrie(head, tel, 0) )
						consistency = false; // 일관성 없다고 나오면 이후 메서드 동작하지 않게
			}
			
			System.out.println( consistency ? "YES" : "NO" );
		}
		
		in.close();
	}

	private static boolean addTrie(Node parent, char[] tel, int idx) {
		if(idx == tel.length) {
			// 마지막 노드이거나 자식 노드가 존재하면 일관성 없는 경우
			if(parent.isEnd || !parent.child.isEmpty())
				return false;
			
			// 일관성 있는 경우
			parent.isEnd = true;
			return true;
		}
		
		Node childNode = null;
		
		// num이 같은 자식 노드 찾기
		for(int i = 0; i < parent.child.size(); i++)
			if(parent.child.get(i).num == tel[idx]) {
				childNode = parent.child.get(i);
				
				if(childNode.isEnd) return false; // 마지막 노드를 거쳐가면 일관성 없는 경우
				break;
			}
		
		// 해당 하는 자식이 없으면 새로운 자식 만들어 추가
		if(childNode == null) {
			childNode = new Node(tel[idx], false);
			parent.child.add(childNode);
		}
		
		return addTrie(childNode, tel, idx + 1);
	}

	static class Node {
		char num;
		boolean isEnd; // 끝지점인지
		List<Node> child;
		
		public Node(char num, boolean isEnd) {
			this.num = num;
			this.isEnd = isEnd;
			this.child = new LinkedList<>();
		}
		
		public Node getEqualsNode(char num) {
			for(int i = 0; i < child.size(); i++) {
				if(child.get(i).num == num)
					return child.get(i);
			}
			
			return null;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", isEnd=" + isEnd + ", child=" + child + "]";
		}
		
	}
}
