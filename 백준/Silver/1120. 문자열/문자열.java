import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int min;
	static String A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = st.nextToken();
		B = st.nextToken();
		
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i <= B.length() - A.length(); i++) {
			min = Math.min( min, diff(i) );
		}
		
		System.out.println(min);
		br.close();
	}
	
	private static int diff(int i) {
		int cnt = 0;
		
		for (int j = 0; j < A.length(); j++) {
			if(A.charAt(j) != B.charAt(i + j)) cnt++;
		}
		
		return cnt;
	}
}