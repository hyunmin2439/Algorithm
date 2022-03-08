package solved;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2441_별찍기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++)
				sb.append(" ");
			for(int j = i; j < N; j++)
				sb.append("*");
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
