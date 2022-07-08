import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		System.out.print( Integer.parseInt(st.nextToken()) * (Integer.parseInt(st.nextToken()) - 1) + 1 );
	}
}