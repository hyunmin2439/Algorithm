import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), ",");
		
		int answer = 0;
		
		while( st.hasMoreTokens() )
			answer += Integer.parseInt(st.nextToken());
		
		System.out.print(answer);
	}
}