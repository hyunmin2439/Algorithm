import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		StringTokenizer st;
		while(T-- > 0) {
			st = new StringTokenizer(in.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			int res = pow(num, e);
			
			System.out.println( res == 0 ? 10 : res );
		}
		
		in.close();
	}

	private static int pow(int num, int e) {
		int ans = 1;
		
		while(e > 0) {
			if(e % 2 == 1)
				ans = (ans * num) % 10;
			
			e >>= 1;
			num = (num * num) % 10;
		}
		
		return ans % 10;
	}
}