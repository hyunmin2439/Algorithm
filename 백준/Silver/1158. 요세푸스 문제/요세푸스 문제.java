import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in) );
		BufferedWriter bw = new BufferedWriter( new OutputStreamWriter(System.out) );
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt( st.nextToken() );
		int k = Integer.parseInt( st.nextToken() );
		
		String result = secondSolve(n, k);
		
		bw.write(result);
		br.close();
		bw.close();
		
	}

	private static String secondSolve(int n, int k) {
		StringBuilder sb = new StringBuilder("<");
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		for(int i = 1; i <= n; i++) {
			list.add(i);
		}
		
		while(list.size() != 1) {
			for(int i = 0; i < k - 1; i++) {
				list.add(list.poll());
			}
			sb.append(list.poll() + ", ");
		}
		
		sb.append(list.poll() + ">");
		
		return sb.toString();
	}
}