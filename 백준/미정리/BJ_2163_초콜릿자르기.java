import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2163_초콜릿자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in) );
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
        System.out.println(M * N - 1);
        
		in.close();
	}
}