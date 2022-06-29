import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()) * 2 - 1;		
		char[] arr = in.readLine().toCharArray();
		in.close();
		
		for(int i = 1; i < N; i += 2) {
			if( arr[i] == arr[i + 1] ) {
				System.out.print("No");
				return;
			}
		}
		
		System.out.print("Yes");
	}
}