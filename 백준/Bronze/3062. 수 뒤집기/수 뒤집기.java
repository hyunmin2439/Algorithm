import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		String tmp;
		int rSum;
		char[] arr;
		boolean flag = true;
		for(int i = 0; i < N; i++) {
			flag = true;
			tmp = in.readLine();
			rSum = Integer.parseInt(tmp) + Integer.parseInt(new StringBuffer(tmp).reverse().toString());
			arr = String.valueOf(rSum).toCharArray();
			
			for(int j = 0; j < arr.length / 2; j++) {
				if(arr[j] != arr[arr.length - j - 1])
					flag = false;
			}
			
			System.out.println( flag ? "YES" : "NO" );
		}
	}

}