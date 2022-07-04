import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		long S = Long.parseLong(in.readLine());
		in.close();
		
		int num = 1, cnt = 0;
		while(S >= num) {
			S -= num++;
			cnt++;
		}
		
		System.out.print(cnt);
	}

}