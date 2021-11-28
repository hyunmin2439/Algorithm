import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// Memory:15940KB / Time:216ms
public class BJ_11656_접미사배열 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String line = in.readLine();
		
		int length = line.length();
		String[] arr = new String[length];
		
		
		for(int i = 0; i < length; i++) {
			arr[i] = line.substring(i);
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < length; i++) {
			System.out.println(arr[i]);
		}
		
		
		in.close();
	}

}
