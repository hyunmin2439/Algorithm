package solved;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Josephus problem
 * 
 * Input
 * n k (1 <= k <= n <= 5,000)
 * 
 * Process
 * 1. It is in a circle from No.1 to No.N
 * 2. Remove the K-th in order.
 * 3. Continue the 1 & 2 steps.
 * 4. Continue until all are removed.
 * 
 * ex) 7 3
 * <3, 6, 2, 7, 5, 1, 4>
 * 
 * Output the Josephus permutations 
 * as shown in the example above.
 */

public class Baekjoon1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt( st.nextToken() );
		int k = Integer.parseInt( st.nextToken() );
		
		String result = mySolve(n, k);
		//String result = otherSolve(n, k);
		
		bw.write(result);
		br.close();
		bw.close();
		
	}

	// memory:295908KB / time:604ms
	private static String otherSolve(int n, int k) {
		StringBuilder sb = new StringBuilder("<");
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		for(int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		while(list.size() != 1) {
			for(int i = 0; i < k - 1; i++) {
				list.add(list.poll()); // Sent the first element to the back
			}
			sb.append(list.poll() + ", "); // first element remove
		}
		
		sb.append(list.poll() + ">");
		
		return sb.toString();
	}

	// memory:17116KB / time:708ms
	private static String mySolve(int n, int k) {
		StringBuilder sb = new StringBuilder("<");
		
		int index = -1, count = n;
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		
		while(count > 0 ) {
			for(int i = 0; i < k; i++) {
				index ++;
				if(index > n - 1) index = 0;
				if(arr[index] == -1) i--;
			}
			
			sb.append(arr[index] + ", ");
			arr[index] = -1;
			
			count--;
		}
		sb.delete(sb.length() - 2, sb.length());
		
		sb.append(">");
		
		return sb.toString();
	}
	
}
