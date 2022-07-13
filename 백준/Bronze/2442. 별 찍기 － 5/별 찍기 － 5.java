import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		int N = Integer
				.parseInt(new BufferedReader(new InputStreamReader(System.in))
						.readLine());
		
		StringBuilder answer = new StringBuilder(),
						star = new StringBuilder();
		
		star.append('*');
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++)
				answer.append(' ');
			
			answer.append(star).append('\n');
			star.append("**");
		}
		
		System.out.print(answer);
	}
}