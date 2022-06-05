import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		long N = Long.parseLong(new BufferedReader(new InputStreamReader(System.in)).readLine());
		
		System.out.println(N % 2 == 0 ? "CY" : "SK");
	}
}
