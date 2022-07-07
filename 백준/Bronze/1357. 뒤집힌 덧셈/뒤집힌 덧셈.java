import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		String x = new StringBuffer(st.nextToken()).reverse().toString();
		String y = new StringBuffer(st.nextToken()).reverse().toString();
		in.close();

		int ans = Integer.parseInt(x) + Integer.parseInt(y);
		String ansStr = new StringBuffer(String.valueOf(ans)).reverse().toString();
				
		System.out.print( Integer.parseInt(ansStr) );
	}

}