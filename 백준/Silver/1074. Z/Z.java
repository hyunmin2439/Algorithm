import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, R, C, ans;
	static boolean exit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		N = (int)Math.pow(2, N);
		
		recur(N - 1, N - 1, N / 2, N * N - 1);
		
		System.out.println(ans);
		br.close();
	}

	private static void recur(int r, int c, int n, int num) {
		
		if(exit) return;
		
		if(r == R && c == C) {
			ans = num;
			exit = true;
			return;
		}
		
		int nr = r, nc = c;
		
		if(r - 1 == R) {
			nr -= 1;
			num -= 2;
		}
		else if(nr - n >= R) {
			nr -= n;
			num -= 2 * n * n;
		}
		
		if(c - 1 == C) {
			nc -= 1;
			num -= 1;
		}
		else if(nc - n >= C) {
			nc -= n;
			num -= n * n;
		}
		
		recur(nr, nc, n / 2, num);
	}
}