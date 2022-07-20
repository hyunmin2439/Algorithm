import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = in.readLine().toCharArray();
		
		int answer = 10;
		
		for(int i = 1; i < arr.length; i++) {
			if(arr[i - 1] != arr[i])
				answer += 10;
			else
				answer += 5;
		}

		System.out.print(answer);
	}
	
}