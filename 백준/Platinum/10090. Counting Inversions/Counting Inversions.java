import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N;
		Node[] perm = new Node[1000000];
	
		N = Integer.parseInt(in.readLine());
		
		st = new StringTokenizer(in.readLine());
		
		for(int i = 0; i < N; i++) {
			perm[i] = new Node(Integer.parseInt(st.nextToken()));
		}
		
		mergeSort(perm, 0, N);
		out.write(String.valueOf(answer));
		out.flush();
	}
	
	static Node mergeSort(Node[] perm, int low, int high) {
		if(high - low == 1) return perm[low];
		
		Node left, right, first, res;
		int mid = (low + high) >> 1;
		
		left = mergeSort(perm, low, mid);
		right = mergeSort(perm, mid, high);
 		
 		if(left.num > right.num) {
 			res = right;
 			right = right.next;
 			answer += mid - low;
 		}
 		else {
			res = left;
			left = left.next;
			low++;
 		}
 		
 		first = res;
 		
 		while(left != null && right != null) {
 			if(left.num > right.num) {
 				res.next = right;
 				right = right.next;
 				answer += mid - low;
 			}
 			else {
 				res.next = left;
 				left = left.next;
 				low++;
 			}
 			res = res.next;
 		}
 		
 		while(left != null) {
 			res.next = left;
 			left = left.next;
 			res = res.next;
 		}
 		
 		while(right != null) {
 			res.next = right;
 			right = right.next;
 			res = res.next;
 		}
		
		return first;
	}
	
	static class Node {
		int num;
		Node next;
		
		public Node(int num) {
			this.num = num;
		}
	}
}