package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_20492_세금 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		System.out.print((int)(N * 0.78) + " ");
		System.out.print((int)(N - N * 0.2 * 0.22));
	}

}
