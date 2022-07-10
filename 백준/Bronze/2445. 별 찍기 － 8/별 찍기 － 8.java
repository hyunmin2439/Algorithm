import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		int N = Integer
				.parseInt(new BufferedReader(new InputStreamReader(System.in))
						.readLine());
		
		StringBuilder sb = new StringBuilder();
		int star = 0, space = N * 2, sw = 1;
		
		for(int k = 0; k < 2; k++) {
			for(int i = 0; i < N; i++) {
				star += sw;
				space -= sw * 2;
				
				for(int j = 0; j < star; j++)
					sb.append('*');
				for(int j = 0; j < space; j++)
					sb.append(' ');
				for(int j = 0; j < star; j++)
					sb.append('*');
				sb.append('\n');

			}
			
			N--;
			sw *= -1;
		}
		
		System.out.print(sb);
	}

}