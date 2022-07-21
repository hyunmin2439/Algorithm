import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 0의 포함 개수는 2 * 5 = 10
 * 
 * 즉, 10이 몇번 곱해졌는지에 따라 0의 개수가 결정 됨.
 * 
 * 하지만 2는 5보다 작기에 항상 2는 항상 5보다 많이 곱해진다. (지수가 높다)
 * 
 * 따라서 5가 몇번 곱해졌는지를 구하면 0의 개수를 구할 수 있다.
 * 
 * 1  ~  4! => 0
 * 5  ~  9! => 1
 * 10 ~ 14! => 2
 * ...
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int count = 0, N = Integer.parseInt(in.readLine());
		
		while(N >= 5) {
			count += N / 5;
			N /= 5;
		}
		
		System.out.print(count);
	}
	
}