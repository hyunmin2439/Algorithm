import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		boolean[] fixed = new boolean[N + 1];
		for(int i = 0; i < M; i++) {
			fixed[ Integer.parseInt(in.readLine()) ] = true;
		}
		
		in.close();
		
		long[] fib = new long[N + 1];
		
		fib[0] = fib[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		
		int idx = 0, answer = 1;
		
		for(int i = 1; i <= N; i++) {
			if( fixed[i] ) {
				answer *= fib[i - idx - 1];
				idx = i;
			}
		}
		
		System.out.print(answer *= fib[N - idx]);
	}

}