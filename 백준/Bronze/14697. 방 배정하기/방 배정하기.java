import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		in.close();
		
		for(int a = 0; a <= N; a += A) {
			for(int b = 0; b <= N; b += B) {
				for(int c = 0; c <= N; c += C) {
					if(a + b + c == N) {
						System.out.print(1);
						return;
					}
				}
			}
		}
		
		System.out.print(0);
	}
}
