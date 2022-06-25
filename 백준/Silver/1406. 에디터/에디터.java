import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// double LinkedList로 직접 구현
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Node cursor = new Node('0'); // 끝을 가르키는 노드
		String str = in.readLine();
		int M = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < str.length(); i++) {
			cursor.addNode( str.charAt(i) );
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			
			switch(st.nextToken()) {
			case "L":
				cursor = cursor.getPrevNode();
				break;
			case "D":
				cursor = cursor.getNextNode();
				break;
			case "B":
				cursor.deleteNode();
				break;
			case "P":
				cursor.addNode( st.nextToken().charAt(0) );
				break;
			}
		}
		
		cursor.print();
	}

	static class Node {
		private char c;
		private Node prev, next;
		
		public Node(char c) {
			this.c = c;
		}
		
		public Node getPrevNode() {
			if(this.prev == null) return this;
			return prev;
		}
		
		public Node getNextNode() {
			if(this.next == null) return this;
			return next;
		}
		
		public void deleteNode() {
			if(prev == null) return;
			
			Node prev = this.prev;
			
			this.prev = prev.prev; // null이면 null 들어감
			
			if(prev.prev != null)
				prev.prev.next = this;
		}
		
		public void addNode(char c) {
			Node newNode = new Node(c);

			if(this.prev != null) {
				Node prevNode = this.prev;
				prevNode.next = newNode;
				newNode.prev = prevNode;		
			}
			
			this.prev = newNode;
			newNode.next = this;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			
			Node cursor = this;
			
			while( cursor.prev != null ) cursor = cursor.prev;
			
			// 끝 노드는 next가 없으니 출력에 포함되지 않음
			while( cursor.next != null ) {
				sb.append(cursor.c);
				cursor = cursor.next;
			}
			
			System.out.print(sb);
		}
	}
}