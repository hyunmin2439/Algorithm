package solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9656_돌게임2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		System.out.print(N % 2 == 0 ? "SK" : "CY");
	}

}
