import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2587_대표값2 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int max = 0;
		int[] arr = new int[5];
		
		for(int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(in.readLine());
			max += arr[i];
		}
		
		Arrays.sort(arr);
		
		System.out.println(max / 5);
		System.out.println(arr[2]);
		
		in.close();
		
	}

}
