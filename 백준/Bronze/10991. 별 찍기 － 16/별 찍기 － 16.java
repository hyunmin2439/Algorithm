import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		int N = Integer
				.parseInt(new BufferedReader(new InputStreamReader(System.in))
						.readLine());
		
		StringBuilder answer = new StringBuilder();
		StringBuilder line = new StringBuilder();
		line.append("*");
		
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > i; j--)
				answer.append(' ');
			answer.append(line).append('\n');
			line.append(" *");
		}
		
		System.out.print(answer);
	}
}